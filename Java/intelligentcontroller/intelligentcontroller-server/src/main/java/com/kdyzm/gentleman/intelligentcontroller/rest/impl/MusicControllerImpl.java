package com.kdyzm.gentleman.intelligentcontroller.rest.impl;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.kdyzm.gentleman.intelligentcontroller.api.rest.MusicController;
import com.kdyzm.gentleman.intelligentcontroller.model.response.ResponseModel;
import com.kdyzm.gentleman.intelligentcontroller.rest.impl.base.BaseResourceImpl;

@Controller
public class MusicControllerImpl extends BaseResourceImpl implements MusicController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 下一曲  ctrl+alt+right
	 */
	@Override
	public ResponseModel<Boolean> next() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_RIGHT);

			robot.keyRelease(KeyEvent.VK_RIGHT);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_CONTROL);

		} catch (AWTException e) {
			logger.error("", e);
		}

		return setSuccessMsg(true);
	}

	/**
	 * 上一曲  ctrl+alt+left
	 */
	@Override
	public ResponseModel<Boolean> before() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_LEFT);

			robot.keyRelease(KeyEvent.VK_LEFT);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_CONTROL);

		} catch (AWTException e) {
			logger.error("", e);
		}

		return setSuccessMsg(true);
	}

	/**
	 * 停止 ctrl+alt+空格
	 */
	@Override
	public ResponseModel<Boolean> stop() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_SPACE);

			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_CONTROL);

		} catch (AWTException e) {
			logger.error("", e);
		}

		return setSuccessMsg(true);
	}

	/**
	 * 播放 ctrl+alt+空格
	 */
	@Override
	public ResponseModel<Boolean> play() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_SPACE);

			robot.keyRelease(KeyEvent.VK_SPACE);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_CONTROL);

		} catch (AWTException e) {
			logger.error("", e);
		}

		return setSuccessMsg(true);
	}

	/**
	 * 音量增强 ctrl+alt+上键
	 */
	@Override
	public ResponseModel<Boolean> voiceup() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_UP);

			robot.keyRelease(KeyEvent.VK_UP);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_CONTROL);

		} catch (AWTException e) {
			logger.error("", e);
		}

		return setSuccessMsg(true);
	}

	/**
	 * 音量减小 ctrl+alt+下键
	 */
	@Override
	public ResponseModel<Boolean> voicedown() {
		try {
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_DOWN);

			robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_CONTROL);

		} catch (AWTException e) {
			logger.error("", e);
		}

		return setSuccessMsg(true);
	}

}
