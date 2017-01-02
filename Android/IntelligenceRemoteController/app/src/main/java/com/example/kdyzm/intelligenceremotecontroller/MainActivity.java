package com.example.kdyzm.intelligenceremotecontroller;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by kdyzm on 2016/12/5.
 */

public class MainActivity extends TabActivity {
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabHost = getTabHost();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("left");
        spec1.setIndicator("音乐");
        Intent left = new Intent(this, LeftActivity.class);
        spec1.setContent(left);

        TabHost.TabSpec spec2 = tabHost.newTabSpec("right");
        spec2.setIndicator("系统管理");
        Intent right = new Intent(this, RightActivity.class);
        spec2.setContent(right);

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);
    }
}
