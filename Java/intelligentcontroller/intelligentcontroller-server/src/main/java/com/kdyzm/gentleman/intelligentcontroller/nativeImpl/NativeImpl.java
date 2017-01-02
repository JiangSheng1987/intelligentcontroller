package com.kdyzm.gentleman.intelligentcontroller.nativeImpl;

import org.springframework.stereotype.Service;

@Service
public class NativeImpl {
	static{
		System.loadLibrary("SystemController");
	}
	public native boolean systemVoiceUp();

	public native boolean systemVoiceDown();
	
	public native boolean systemVoiceMute();
}
