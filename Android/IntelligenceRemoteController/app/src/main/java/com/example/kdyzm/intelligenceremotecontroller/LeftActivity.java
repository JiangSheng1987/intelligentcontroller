package com.example.kdyzm.intelligenceremotecontroller;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.kdyzm.intelligenceremotecontroller.consts.Type;
import com.example.kdyzm.intelligenceremotecontroller.impl.MyJsonHttpResponseHandler;
import com.loopj.android.http.AsyncHttpClient;

public class LeftActivity extends Fragment {

    private Button playOrStop;
    private Button next;
    private Button before;
    private Button voiceUp;
    private Button voiceDown;
    private View view;
    private String baseUrl;

    public static AsyncHttpClient client = new AsyncHttpClient();

    private void init(View v) {
        this.view=v;
        client.setTimeout(1000);
        client.setMaxRetriesAndTimeout(0,1000);
        playOrStop = (Button) v.findViewById(R.id.playOrStop);
        next = (Button) v.findViewById(R.id.next);
        before = (Button) v.findViewById(R.id.before);
        voiceUp = (Button) v.findViewById(R.id.voiceUp);
        voiceDown = (Button) v.findViewById(R.id.voiceDown);

        baseUrl = "http://192.168.0.10:8080";
    }

    private void showSuccessMsg(String msg){
        System.out.println(msg);
        Toast toast = Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    };
    private void showFaildMsg(String msg){
        Toast toast = Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT);
        toast.show();
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            boolean result = msg.getData().getBoolean("result");

            switch (Type.get(msg.getData().getInt("type"))){
                case PLAYORSTOP:
                    if(result) {
                        showSuccessMsg("播放/暂停");
                    }else{
                        showFaildMsg("播放/暂停失败！");
                    }
                    break;
                case NEXT:
                    if(result) {
//                        showSuccessMsg("下一曲");
                    }else{
                        showFaildMsg("播放下一曲失败！");
                    }
                    break;
                case BEFORE:
                    if(result) {
//                        showSuccessMsg("上一曲");
                    }else{
                        showFaildMsg("播放上一曲失败！");
                    }
                    break;
                case VOICEDOWN:
                    if(result) {
//                        showSuccessMsg("音量减小");
                    }else{
                        showFaildMsg("音量减小失败！");
                    }
                    break;
                case VOICEUP:
                    if(result) {
//                        showSuccessMsg("音量增加");
                    }else{
                        showFaildMsg("音量增加失败！");
                    }
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.activity_left,null);
//        setContentView(R.layout.activity_left);
        init(v);

        playOrStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = baseUrl + "/controller/play";
                client.get(url, new MyJsonHttpResponseHandler(Type.PLAYORSTOP,handler));
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = baseUrl + "/controller/next";
                client.get(url, new MyJsonHttpResponseHandler(Type.NEXT,handler));
            }
        });

        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = baseUrl + "/controller/before";
                client.get(url, new MyJsonHttpResponseHandler(Type.BEFORE,handler));
            }
        });

        voiceDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = baseUrl + "/controller/voicedown";
                client.get(url, new MyJsonHttpResponseHandler(Type.VOICEDOWN,handler));
            }
        });

        voiceUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = baseUrl + "/controller/voiceup";
                client.get(url, new MyJsonHttpResponseHandler(Type.VOICEUP,handler));
            }
        });


        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
