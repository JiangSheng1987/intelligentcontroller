package com.kdyzm.gentleman.intelligentcontroller.rest.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kdyzm.gentleman.intelligentcontroller.api.rest.SystemController;
import com.kdyzm.gentleman.intelligentcontroller.model.response.ResponseModel;
import com.kdyzm.gentleman.intelligentcontroller.nativeImpl.NativeImpl;
import com.kdyzm.gentleman.intelligentcontroller.rest.impl.base.BaseResourceImpl;

@Controller
public class SystemControllerImpl extends BaseResourceImpl implements SystemController {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	@Autowired
	private NativeImpl nativeImpl;

	@Override
	public ResponseModel<Long> shutdown(String destLongTime) {
		Date dest = null;
		try {
			dest = sdf.parse(destLongTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return setFaildMsg(-1L);
		}
		Runtime runtime = Runtime.getRuntime();
		Date now = new Date();
		if (dest.before(now)) {
			logger.warn("unlegal dest time={}", sdf.format(dest));
			return setFaildMsg(-1L);
		}
		long time = dest.getTime() - now.getTime();
		try {
			runtime.exec("shutdown -s -t " + time / 1000);
		} catch (IOException e) {
			logger.error("", e);
			return setFaildMsg(-1L);
		}
		logger.info(time / 1000 / 60 + "");
		return setSuccessMsg(time / 1000 / 60);
	}

	@Override
	public ResponseModel<Boolean> cancelShutdown() {
		Runtime runtime = Runtime.getRuntime();
		try {
			runtime.exec("shutdown -a");
		} catch (IOException e) {
			logger.error("", e);
			return setFaildMsg(false);
		}
		return setSuccessMsg(true);
	}

	@Override
	public ResponseModel<Boolean> systemVoiceUp() {
		try {
			nativeImpl.systemVoiceUp();
		} catch (Exception e) {
			logger.error("", e);
			return setFaildMsg(false);
		}
		return setSuccessMsg(true);
	}

	@Override
	public ResponseModel<Boolean> systemVoiceDown() {
		try {
			nativeImpl.systemVoiceDown();
		} catch (Exception e) {
			logger.error("", e);
			return setFaildMsg(false);
		}
		return setSuccessMsg(true);
	}

	@Override
	public ResponseModel<Boolean> systemVoiceMute() {
		try {
			nativeImpl.systemVoiceMute();
		} catch (Exception e) {
			logger.error("", e);
			return setFaildMsg(false);
		}
		return setSuccessMsg(true);
	}

}
