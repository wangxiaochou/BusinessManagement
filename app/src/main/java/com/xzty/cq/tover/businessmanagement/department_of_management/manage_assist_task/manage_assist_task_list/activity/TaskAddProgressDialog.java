package com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.model.ReqAssistAddProgress;
import com.xzty.cq.tover.businessmanagement.department_of_management.manage_assist_task.manage_assist_task_list.presenter.TaskAddProgressPresenter;

import butterknife.BindView;

/**
 * author yq
 * date 2018/8/10
 * 添加任务进展的自定义弹窗
 */
public class TaskAddProgressDialog extends Dialog implements View.OnClickListener,RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.et_addprogress_expecttime)
    EditText expectTime;

    @BindView(R.id.et_addprogress_content)
    EditText content;

    @BindView(R.id.bt_addprogress_cancel)
    Button cancelBtn;

    @BindView(R.id.bt_addprogress_save)
    Button saveBtn;

    RadioGroup type;

    TextView chooseDateText;

    TextView contentText;

    private ReqAssistAddProgress raaProgress;

    private TaskAddProgressPresenter tapPresenter = new TaskAddProgressPresenter();

    public TaskAddProgressDialog(@NonNull Context context) {
        super(context);
    }

    public TaskAddProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public TaskAddProgressDialog(@NonNull Context context, int themeResId, ReqAssistAddProgress raaProgress) {
        super(context, themeResId);
        this.raaProgress = raaProgress;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置布局资源文件
        super.setContentView(R.layout.task_assist_addprogress_dialog);

        //绑定构件
        cancelBtn = findViewById(R.id.bt_addprogress_cancel);
        saveBtn = findViewById(R.id.bt_addprogress_save);
        expectTime = findViewById(R.id.et_addprogress_expecttime);
        content = findViewById(R.id.et_addprogress_content);
        type = findViewById(R.id.rg_add_progress_type);
        chooseDateText = findViewById(R.id.tv_addprogress_date);
        contentText = findViewById(R.id.tv_addprogress_content);

        //设置监听
        type.setOnCheckedChangeListener(this);
        cancelBtn.setOnClickListener(this);
        saveBtn.setOnClickListener(this);
    }

    //验证信息是否填写正确
    public ReqAssistAddProgress verifyData(){
        String expectTime = this.expectTime.getText().toString();
        String content = this.content.getText().toString().trim();
        int type = this.type.getCheckedRadioButtonId();
        if (TextUtils.isEmpty(expectTime)){
            Toast.makeText(super.getContext(),"请填写预计完成时间",Toast.LENGTH_SHORT).show();
            return null;
        }else {
            raaProgress.setExpect_time(expectTime);
        }
        if (TextUtils.isEmpty(content)){
            Toast.makeText(super.getContext(),"请填写进展内容",Toast.LENGTH_SHORT).show();
            return null;
        }else {
            raaProgress.setTrack_content(content);
        }
        if (type == R.id.rb_add_progress_normal){
            raaProgress.setType(0);
        }else if (type == R.id.rb_add_progress_overdue){
            raaProgress.setType(1);
        }
        return this.raaProgress;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_addprogress_cancel:
                this.dismiss();
                break;

            case R.id.bt_addprogress_save:
                ReqAssistAddProgress raaProgress = verifyData();
                if (raaProgress == null)
                    break;
                else {
                    tapPresenter.addTaskProgress(this,raaProgress);
                    break;
                }
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_add_progress_normal:
                chooseDateText.setText("预计完成时间：");
                contentText.setText("输入进展信息：");
                break;
            case R.id.rb_add_progress_overdue:
                chooseDateText.setText("选择延期时限：");
                contentText.setText("输入延期原因：");
                break;
        }
    }

    public void addSuccess(){
        Toast.makeText(super.getContext(),"添加任务进展成功",Toast.LENGTH_LONG).show();
        this.dismiss();
        getContext().startActivity(new Intent(getContext(), ManageAssistTaskActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
    }

    public void addFalse(String strRes){
        Toast.makeText(super.getContext(),"添加任务进展失败"+strRes,Toast.LENGTH_LONG).show();
    }

}
