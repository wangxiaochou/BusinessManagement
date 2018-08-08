package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.xzty.cq.tover.businessmanagement.R;

public class TaskAddProgressDialog extends Dialog {

    private EditText expectTime;
    private EditText content;

    public TaskAddProgressDialog(@NonNull Context context) {
        super(context);
    }

    public TaskAddProgressDialog(@NonNull Context context, EditText expectTime, EditText content) {
        super(context);
        this.expectTime = expectTime;
        this.content = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.task_assist_addprogress_dialog);
    }

}
