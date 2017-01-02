// Hello.cpp : Defines the entry point for the DLL application.
//

#include "stdafx.h"
#include "com_kdyzm_gentleman_intelligentcontroller_nativeImpl_NativeImpl.h"
#include <mmdeviceapi.h> 
#include <endpointvolume.h>
#include <audioclient.h>
#include <windows.h>

#pragma hdrstop
#pragma argsused

#include <tchar.h>
#include <stdio.h>
#include <conio.h>
#include <commctrl.h>

#pragma comment(lib, "Winmm.lib")


BOOL APIENTRY DllMain( HANDLE hModule, 
                       DWORD  ul_reason_for_call, 
                       LPVOID lpReserved
					 )
{
    switch (ul_reason_for_call)
	{
		case DLL_PROCESS_ATTACH:
		case DLL_THREAD_ATTACH:
		case DLL_THREAD_DETACH:
		case DLL_PROCESS_DETACH:
			break;
    }
    return TRUE;
}


/*
* Class:     NativeImpl
* Method:    systemVoiceUp
* Signature: ()Ljava/lang/Boolean;
*/
JNIEXPORT jboolean JNICALL Java_com_kdyzm_gentleman_intelligentcontroller_nativeImpl_NativeImpl_systemVoiceUp
(JNIEnv *, jobject){
	printf("Callback System Voice Up!\n");
	
	unsigned char result = false;
	
	keybd_event(VK_VOLUME_UP, MapVirtualKey(VK_VOLUME_UP, 0), KEYEVENTF_EXTENDEDKEY, 0);
	keybd_event(VK_VOLUME_UP, MapVirtualKey(VK_VOLUME_UP, 0), KEYEVENTF_EXTENDEDKEY | KEYEVENTF_KEYUP, 0);

	return (jboolean)result;
}

/*
* Class:     NativeImpl
* Method:    systemVoiceDown
* Signature: ()Ljava/lang/Boolean;
*/
JNIEXPORT jboolean JNICALL Java_com_kdyzm_gentleman_intelligentcontroller_nativeImpl_NativeImpl_systemVoiceDown
(JNIEnv *, jobject)
{
	printf("Call System Voice Down !\n");
	unsigned char result = true;
	keybd_event(VK_VOLUME_DOWN, MapVirtualKey(VK_VOLUME_DOWN, 0), KEYEVENTF_EXTENDEDKEY, 0);
	keybd_event(VK_VOLUME_DOWN, MapVirtualKey(VK_VOLUME_DOWN, 0), KEYEVENTF_EXTENDEDKEY | KEYEVENTF_KEYUP, 0);
	return (jboolean)result;
}


/*
* Class:     NativeImpl
* Method:    systemVoiceMute
* Signature: ()Z
*/
JNIEXPORT jboolean JNICALL Java_com_kdyzm_gentleman_intelligentcontroller_nativeImpl_NativeImpl_systemVoiceMute
(JNIEnv *, jobject){
	printf("Call System Voice Mute !\n");
	unsigned char result = true;
	keybd_event(VK_VOLUME_MUTE, MapVirtualKey(VK_VOLUME_MUTE, 0), KEYEVENTF_EXTENDEDKEY, 0);
	keybd_event(VK_VOLUME_MUTE, MapVirtualKey(VK_VOLUME_MUTE, 0), KEYEVENTF_EXTENDEDKEY | KEYEVENTF_KEYUP, 0);
	return (jboolean)result;
}
