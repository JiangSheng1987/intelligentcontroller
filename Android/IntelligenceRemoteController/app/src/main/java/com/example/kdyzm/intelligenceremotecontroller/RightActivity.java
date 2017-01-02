package com.example.kdyzm.intelligenceremotecontroller;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kdyzm.intelligenceremotecontroller.consts.Type;
import com.example.kdyzm.intelligenceremotecontroller.impl.MyJsonHttpResponseHandler;
import com.example.kdyzm.intelligenceremotecontroller.impl.TimePickerDialogImpl;
import com.loopj.android.http.AsyncHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

/**
 * Created by kdyzm on 2016/12/5.
 */

public class RightActivity extends Fragment {

    private Button shutdown;
    private Button shutdown_cancel;
    private Button systemVoiceUp;
    private Button systemVoiceDown;
    private Button systemVoiceMute;

    DatePickerDialog datePickerDialog;

    TimePickerDialogImpl timePickerDialog;
    private String baseUrl;
    private SimpleDateFormat sdf;
    public static AsyncHttpClient client;
    private String selectDate;
    private View view;
    private void init(View view) {
        this.view=view;
        shutdown = (Button) view.findViewById(R.id.shutdown);
        shutdown_cancel = (Button) view.findViewById(R.id.shutdown_cancel);
        systemVoiceUp= (Button) view.findViewById(R.id.systemVoiceUp);
        systemVoiceDown= (Button) view.findViewById(R.id.systemVoiceDown);
        systemVoiceMute= (Button) view.findViewById(R.id.systemVoiceMute);
        Calendar calendar = Calendar.getInstance();
        client = new AsyncHttpClient();
        client.setTimeout(1000);
        client.setMaxRetriesAndTimeout(0,1000);
        sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                selectDate="";
                selectDate=year + "-" + (monthOfYear + 1) + "-"
                        + dayOfMonth;
                datePickerDialog.dismiss();
                timePickerDialog.show();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        timePickerDialog=new TimePickerDialogImpl(view.getContext(),new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                selectDate+=" "+hourOfDay+":"+minute;
                timePickerDialog.dismiss();
                String url = baseUrl + "/system/shutdown?date="+selectDate;
                client.get(url, new MyJsonHttpResponseHandler(Type.SHUTDOWN,handler) {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                        try {
                            Long result = response.getLong("data");
                            handler.sendMessage(setSuccessMessage(Type.SHUTDOWN.value(),result+""));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        handler.sendMessage(setFaildMessage(Type.SHUTDOWN.value()));
                    }
                });
            }
        },calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE),true);
        baseUrl = "http://192.168.0.10:8080";
    }

    public Message setSuccessMessage(int type,String data) {
        Message message = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putBoolean("result", true);
        bundle.putInt("type",type);
        bundle.putString("data",data);
        message.setData(bundle);
        return message;
    }

    public Message setFaildMessage(int type) {
        Message message = Message.obtain();
        Bundle bundle = new Bundle();
        bundle.putBoolean("result", false);
        bundle.putInt("type",type);
        message.setData(bundle);
        return message;
    }

    private void showSuccessMsg(String data){
        Toast toast=Toast.makeText(view.getContext(),data,Toast.LENGTH_SHORT);
        toast.show();
    }

    private void showFaildMsg(String msg){
        Toast toast=Toast.makeText(view.getContext(),msg,Toast.LENGTH_SHORT);
        toast.show();
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int type = msg.getData().getInt("type");
            boolean result=msg.getData().getBoolean("result");
            switch (Type.get(type)){
                case SHUTDOWN:
                    if(result){
                        String data=msg.getData().getString("data");
                        showSuccessMsg("还有 "+data+" 分钟关机！");
                    }else{
                        showFaildMsg("关机失败！");
                    }
                    break;
                case SHUTDOWN_CANCEL:
                    if(result) {
                        showSuccessMsg("已经取消关机！");
                    }else{
                        showFaildMsg("取消关机失败！");
                    }
                    break;
                case SYSTEM_VOICE_DOWN:
                    if (result){
//                        showSuccessMsg("");
                    }else{
                        showFaildMsg("降低系统音量失败！");
                    }
                    break;
                case SYSTEM_VOICE_UP:
                    if (result){
//                        showSuccessMsg("");
                    }else{
                        showFaildMsg("增强系统音量失败！");
                    }
                    break;
                case SYSTEM_VOICE_MUTE:
                    if (result){
//                        showSuccessMsg("");
                    }else{
                        showFaildMsg("系统音量静音失败！");
                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_right,null);
        init(view);
        shutdown_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = baseUrl + "/system/shutdown/cancel";
                client.get(url, new MyJsonHttpResponseHandler(Type.SHUTDOWN_CANCEL,handler) {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        handler.sendMessage(setSuccessMessage(Type.SHUTDOWN_CANCEL.value(),""));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        handler.sendMessage(setFaildMessage(Type.SHUTDOWN_CANCEL.value()));
                    }
                });
            }
        });

        shutdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        systemVoiceDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = baseUrl + "/system/voice/down";
                client.get(url, new MyJsonHttpResponseHandler(Type.SYSTEM_VOICE_DOWN,handler) {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        handler.sendMessage(setSuccessMessage(Type.SYSTEM_VOICE_DOWN.value(),""));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        handler.sendMessage(setFaildMessage(Type.SYSTEM_VOICE_DOWN.value()));
                    }
                });
            }
        });

        systemVoiceUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = baseUrl + "/system/voice/up";
                client.get(url, new MyJsonHttpResponseHandler(Type.SYSTEM_VOICE_UP,handler) {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        handler.sendMessage(setSuccessMessage(Type.SYSTEM_VOICE_UP.value(),""));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        handler.sendMessage(setFaildMessage(Type.SYSTEM_VOICE_UP.value()));
                    }
                });
            }
        });

        systemVoiceMute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = baseUrl + "/system/voice/mute";
                client.get(url, new MyJsonHttpResponseHandler(Type.SYSTEM_VOICE_MUTE,handler) {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        handler.sendMessage(setSuccessMessage(Type.SYSTEM_VOICE_MUTE.value(),""));
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                        handler.sendMessage(setFaildMessage(Type.SYSTEM_VOICE_MUTE.value()));
                    }
                });
            }
        });

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_right);

    }
}
