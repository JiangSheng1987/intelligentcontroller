package com.kdyzm.gentleman.intelligentcontroller.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitProperty {
	private static Logger logger = LoggerFactory.getLogger(InitProperty.class);
	private static Properties initProperty = new Properties();
	static {
		try {
			initProperty.load(new InputStreamReader(new FileInputStream(new File("config/init.properties")), "utf-8"));
		} catch (IOException e) {
			logger.error("initProperties load faild !", e);
		}
	}
	public static Boolean PROXY_ENABLE = Boolean.parseBoolean((String) initProperty.get("proxy.enable"));
	public static String PROXY_TYPE = initProperty.getProperty("proxy.type").trim();
	public static Boolean PROXY_HTTP_AUTHENTICATION_ENABLE = Boolean
			.parseBoolean((String) initProperty.get("proxy.http.authentication.enable"));
	public static Boolean PROXY_HTTP_SSL_ENABLE = Boolean
			.parseBoolean((String) initProperty.get("proxy.http.ssl.enable"));
	public static String PROXY_HTTP_IP = initProperty.getProperty("proxy.http.ip").trim();
	public static Integer PROXY_HTTP_PORT = Integer.parseInt((String) initProperty.get("proxy.http.port"));
	public static String PROXY_HTTP_AUTHENTICATION_USERNAME = initProperty
			.getProperty("proxy.http.authentication.username").trim();
	public static String PROXY_HTTP_AUTHENTICATION_PASSWORD = initProperty
			.getProperty("proxy.http.authentication.password").trim();
	public static String PROXY_SOCKET_IP = initProperty.getProperty("proxy.socket.ip").trim();
	public static Integer PROXY_SOCKET_PORT = Integer.parseInt((String) initProperty.get("proxy.socket.port"));

	public static Integer HTTP_READTIMEOUT = Integer.parseInt(initProperty.getProperty("http.readtimeout"));
	public static Integer HTTP_CONNECTIONTIMEOUT = Integer.parseInt(initProperty.getProperty("http.connectiontimeout"));
	public static Boolean HTTP_ISBUFFERREQUESTBODY = Boolean.parseBoolean("http.isbufferrequestbody");
}
