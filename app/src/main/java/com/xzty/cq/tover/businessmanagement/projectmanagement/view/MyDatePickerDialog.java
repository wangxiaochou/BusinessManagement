package com.xzty.cq.tover.businessmanagement.projectmanagement.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by liuchuandeo on 2018/2/5.
 */

public class MyDatePickerDialog extends DatePickerDialog {
    public MyDatePickerDialog(@NonNull Context context, @Nullable OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, listener, year, month, dayOfMonth);
    }

    @Override
    protected void onStop() {
//        super.onStop();
        //重写onStop,不执行父类onStop以解决onDateSetListener中onDateSet方法执行两次
    }

}
