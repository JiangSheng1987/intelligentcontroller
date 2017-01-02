package com.example.kdyzm.intelligenceremotecontroller.impl;

import android.os.Handler;

import com.example.kdyzm.intelligenceremotecontroller.consts.Type;
import com.example.kdyzm.intelligenceremotecontroller.utils.MessageUtils;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kdyzm on 2016/12/5.
 */

public class MyJsonHttpResponseHandler extends JsonHttpResponseHandler {
    private Type type;
    private Handler handler;
    public MyJsonHttpResponseHandler(Type type,Handler handler) {
        this.handler=handler;
        this.type = type;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        handler.sendMessage(MessageUtils.setSuccessMessage(type.value()));
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
        handler.sendMessage(MessageUtils.setSuccessMessage(type.value()));
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
        handler.sendMessage(MessageUtils.setSuccessMessage(type.value()));
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        handler.sendMessage(MessageUtils.setFaildMessage(type.value()));
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        handler.sendMessage(MessageUtils.setFaildMessage(type.value()));
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
        handler.sendMessage(MessageUtils.setFaildMessage(type.value()));
    }
}
