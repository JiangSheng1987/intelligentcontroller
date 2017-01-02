package com.example.kdyzm.intelligenceremotecontroller.utils;

import android.os.Bundle;
import android.os.Message;

/**
 * Created by kdyzm on 2016/12/5.
 */

public class MessageUtils {
    public static Message setFaildMessage(int type) {
        Message message = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putBoolean("result", false);
        bundle.putInt("type", type);
        message.setData(bundle);
        return message;
    }

    public static Message setSuccessMessage(int type) {
        Message message = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putBoolean("result", true);
        bundle.putInt("type", type);
        message.setData(bundle);
        return message;
    }
}
