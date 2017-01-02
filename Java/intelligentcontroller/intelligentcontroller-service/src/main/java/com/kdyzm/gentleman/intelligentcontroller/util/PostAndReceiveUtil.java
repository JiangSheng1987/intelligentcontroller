package com.kdyzm.gentleman.intelligentcontroller.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.net.URLDecoder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.axis.encoding.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wsh
 * 
 */
@Component
public class PostAndReceiveUtil {
	private String utf8 = "UTF-8";
	private Logger logger = LoggerFactory.getLogger(getClass());

	public void main(String[] args) throws UnsupportedEncodingException {
		// 生成请求的request
		String content = "content";// TODO content为xml的请求报文
		content = Base64.encode(content.getBytes("utf-8"));// 报文使用utf-8编码并进行base64加密
		byte[] signBytes = getSign(content);// 获得签名串
		String sign = new String(Base64.encode(signBytes));// 签名串base64加密
		String request = new StringBuilder("content").append("=").append(content).append("&").append("sign").append("=")
				.append(sign).toString();// 拼装加密后的报文和加密后的签名串

		// 开始发送请求
		post("url", request); // TODO url填写请求地址

	}

	/**
	 * 发送请求
	 * 
	 * @param url
	 * @param paramContent
	 * @return
	 */
	public String post(String url, String paramContent) {
		// 声明返回
		String responseContent = null;
		try {
			// 初始化SSL证书
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(X509Certificate[] x509Certificates, String s)
						throws CertificateException {
				}

				@Override
				public void checkServerTrusted(X509Certificate[] x509Certificates, String s)
						throws CertificateException {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
			} }, new SecureRandom());

			HttpClient httpClient = new DefaultHttpClient();

			// 设置HOST验证，此处设置为允许所有配置
			SSLSocketFactory socketFactory = new SSLSocketFactory(context,
					SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", socketFactory, 443));

			// 设置httppost
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new StringEntity(paramContent));
			httpPost.addHeader("Content-Type", "application/xml;charset=utf-8");
			httpPost.addHeader("Accept", "*/*");

			logger.info(String.format("请求开始 url:%s", httpPost.getURI().toString()));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			responseContent = EntityUtils.toString(httpResponse.getEntity());
			logger.info(String.format("请求完毕 response:%s", responseContent));

		} catch (Exception e) {
			logger.error("httpClient请求异常:", e);
		}
		return responseContent;
	}

	@ResponseBody
	@RequestMapping(value = "/xxxx/yyyyyy")
	// TODO 配置接收请求的路径
	public String receive(HttpServletRequest request, @RequestParam(defaultValue = "") String document,
			@RequestBody String body) throws UnsupportedEncodingException {
		// 处理报文，获得加密后的content和加密后的sign
		body = URLDecoder.decode(body, "utf-8");
		String content = body.substring(body.indexOf("&content") + 9).replace("%3D", "=").replace("%2F", "/");
		String sign = body.substring(5, body.indexOf("&content"));
		sign = sign.replace("%3D", "=").replace("%2F", "/");

		// 对content使用utf-8编码进行解密：base64的decode
		content = new String(Base64.decode(content), utf8);
		// 对sign进行解密：base64的decode
		byte[] signBytes = Base64.decode(sign);

		// 验签
		boolean b = verify(content, signBytes);
		logger.info("验签结果：" + b);

		// 验签成功后进行对应的业务处理并返回结果 TODO
		return null;
	}

	/**
	 * 验签
	 * 
	 * @param content
	 * @param signBytes
	 * @return
	 */
	public boolean verify(String content, byte[] signBytes) {
		boolean result = false;
		try {
			Signature signature = Signature.getInstance("SHA/DSA");

			FileInputStream pubFileStream = null;
			ObjectInputStream pubInputStream = null;
			try {
				pubFileStream = new FileInputStream("publicKeyPath");// TODO
																		// 公钥文件路径
				pubInputStream = new ObjectInputStream(pubFileStream);
				signature.initVerify((PublicKey) pubInputStream.readObject());
			} catch (Exception e) {
				logger.error("获取publicKey异常", e);
			} finally {
				if (null != pubInputStream) {
					try {
						pubInputStream.close();
					} catch (IOException e1) {
						logger.error("关闭ObjectInputStream流异常", e1);
					}
				}
				if (null != pubFileStream) {
					try {
						pubFileStream.close();
					} catch (IOException e2) {
						logger.error("关闭FileInputStream流异常", e2);
					}
				}
				logger.info("完成加载publicKey");
			}

			MessageDigest messagedigest = MessageDigest.getInstance("SHA");
			byte[] digestBytes = messagedigest.digest(content.getBytes(utf8));
			signature.update(digestBytes);

			result = signature.verify(signBytes);
		} catch (Exception e) {
			logger.error("验签异常", e);
		}
		return result;
	}

	/**
	 * 获得签名串
	 * 
	 * @param content
	 * @return
	 */
	public byte[] getSign(String content) {
		byte[] digestBytes = new byte[] {};
		try {
			Signature signature = Signature.getInstance("SHA/DSA");

			FileInputStream priFileStream = null;
			ObjectInputStream priInputStream = null;
			try {
				priFileStream = new FileInputStream("privateKeyPath");// TODO
																		// 私钥文件路径
				priInputStream = new ObjectInputStream(priFileStream);
				signature.initSign((PrivateKey) priInputStream.readObject());
			} catch (Exception e) {
				logger.error("获取privateKey异常", e);
			} finally {
				if (null != priInputStream) {
					try {
						priInputStream.close();
					} catch (IOException e1) {
						logger.error("关闭ObjectInputStream流异常", e1);
					}
				}
				if (null != priFileStream) {
					try {
						priFileStream.close();
					} catch (IOException e2) {
						logger.error("关闭FileInputStream流异常", e2);
					}
				}
			}

			MessageDigest messagedigest = MessageDigest.getInstance("SHA");
			digestBytes = messagedigest.digest(content.getBytes(utf8));

			signature.update(digestBytes);
			return signature.sign();
		} catch (Exception e) {
			logger.error("加签异常", e);
		}
		return digestBytes;
	}
}
