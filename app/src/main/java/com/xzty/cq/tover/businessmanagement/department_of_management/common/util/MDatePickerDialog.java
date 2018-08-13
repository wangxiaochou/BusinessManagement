package com.xzty.cq.tover.businessmanagement.department_of_management.common.util;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MDatePickerDialog{

    private DatePickerDialog datePickerDialog;

    private Calendar c = Calendar.getInstance();

    private EditText editText;

    private int year = c.get(Calendar.YEAR);
    private int month = c.get(Calendar.MONTH);
    private int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

    private DatePickerDialog.OnDateSetListener  dateSetListener = new DatePickerDialog.OnDateSetListener(){

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            StringBuffer sb = new StringBuffer();
            sb.append(year).append("-");
            sb.append( (month+1) < 10 ? "0" + (month + 1) : (month + 1)).append("-");
            sb.append(dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth);
            editText.setText(sb);
        }
    };

  /*  public MDatePickerDialog(@NonNull Context context, @Nullable OnDateSetListener listener, int year, int month, int dayOfMonth) {
        super(context, listener, year, month, dayOfMonth);
    }*/

    public MDatePickerDialog(@NonNull Context context, EditText editText) {
        this.editText = editText;
        datePickerDialog = new DatePickerDialog(context,dateSetListener,year,month,dayOfMonth);
    }

    public void showDialog(){
        this.datePickerDialog.show();
    }
}
