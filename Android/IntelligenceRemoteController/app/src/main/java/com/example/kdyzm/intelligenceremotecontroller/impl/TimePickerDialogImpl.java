package com.example.kdyzm.intelligenceremotecontroller.impl;

import android.app.TimePickerDialog;
import android.content.Context;

/**
 * Created by kdyzm on 2016/12/5.
 */

public class TimePickerDialogImpl extends TimePickerDialog {
    public TimePickerDialogImpl(Context context, OnTimeSetListener listener, int hourOfDay, int minute, boolean is24HourView) {
        super(context, listener, hourOfDay, minute, is24HourView);
    }

    @Override
    protected void onStop() {

    }
}
