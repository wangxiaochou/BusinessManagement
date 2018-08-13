package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.contract.MeetingProjectDetail_Contract;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model.GetMeetingProjectDetail;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model.ReqMeetingProjectDetail;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.presenter.MeetingProjectDetailPresenter;

import butterknife.BindView;

/**
 * author wl
 * Created 2018/08/08
 * explain 周例会详细细节Activity
 */

public class MeetingProjectDetail_Activity extends ActivityPresenter<MeetingProjectDetail_Contract.Presenter> implements MeetingProjectDetail_Contract.View{

    @BindView(R.id.project_task_list_toolbar)
    Toolbar project_task_list_toolbar;

    @BindView(R.id.conference_host_text)
    TextView conference_host;
    @BindView(R.id.meeting_recorder_text)
    TextView meeting_recorder;
    @BindView(R.id.meeting_place_text)
    TextView meeting_place;
    @BindView(R.id.meeting_people_text)
    TextView meeting_people;
    @BindView(R.id.absentee_text)
    TextView absentee;
    @BindView(R.id.last_week_plan_text)
    TextView last_week_plan;
    @BindView(R.id.this_week_completed_text)
    TextView this_week_completed;
    @BindView(R.id.next_week_plan_text)
    TextView next_week_plan;
    @BindView(R.id.coordination_text)
    TextView coordination;
    @BindView(R.id.funding_situation_text)
    TextView funding_situation;
    @BindView(R.id.project_correction_text)
    TextView project_correction;


    private GetMeetingProjectDetail mlist;

    private int weeks;
    private int years;
    private String project_id;
    private String meet_time;


    public static final String TAG = "日志打印——令  ";

    @Override
    protected int getContentLayoutId() {
        return R.layout.project_task_detail_activity;
    }

    @Override
    protected void initData() {
        super.initData();
        Account.load(this);
        project_id = Account.getProjectId();
        weeks = getIntent().getExtras().getInt("weeks");
        years = getIntent().getExtras().getInt("years");
        ReqMeetingProjectDetail reqMeetingProjectDetail = new ReqMeetingProjectDetail(weeks,years,project_id);
        mPresenter.getData(reqMeetingProjectDetail);
        Log.e(TAG,String.format("这是发送给的服务器的三个参数   ——  "+ "周数"+ weeks + "——年份" + years + "——项目ID" + project_id,reqMeetingProjectDetail));

        meet_time = getIntent().getExtras().getString("meet_time").toString();
        project_task_list_toolbar.setTitle(meet_time + "日会议");

    }

    @Override
    protected void initWidget() {
        super.initWidget();
        initToolbar();

    }

    private void initToolbar() {
        setSupportActionBar(project_task_list_toolbar);
        //设置返回图标点击事件
        project_task_list_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    protected MeetingProjectDetail_Contract.Presenter initPresenter() {
        return new MeetingProjectDetailPresenter(this);
    }

    @Override
    public void showError(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(GetMeetingProjectDetail list) {
        mlist = list;

        conference_host.setText(mlist.getGetMeetingDetail().getPresenter());
        meeting_recorder.setText(mlist.getGetMeetingDetail().getNotekeeper());
        meeting_place.setText(mlist.getGetMeetingDetail().getMeet_Address());
        meeting_people.setText(mlist.getGetMeetingDetail().getConferee());
        if (mlist.getGetMeetingDetail().getAbsentee() == null){
            absentee.setText("无");
        }else{
            absentee.setText(mlist.getGetMeetingDetail().getAbsentee());
        }

        if (mlist.getGetMeetingProjectTaskDetail() == null){
            last_week_plan.setText("无");
            this_week_completed.setText("无");
            next_week_plan.setText("无");
            coordination.setText("无");
            funding_situation.setText("无");
            project_correction.setText("无");
        }else if (mlist.getGetMeetingProjectTaskDetail() != null){
            if (mlist.getGetMeetingProjectTaskDetail().getOld_Week_Task() == null){
                last_week_plan.setText("无");
            }else{
                last_week_plan.setText(mlist.getGetMeetingProjectTaskDetail().getOld_Week_Task());
            }

            if (mlist.getGetMeetingProjectTaskDetail().getWeek_Task() == null){
                this_week_completed.setText("无");
            }else{
                this_week_completed.setText(mlist.getGetMeetingProjectTaskDetail().getWeek_Task());
            }

            if (mlist.getGetMeetingProjectTaskDetail().getNext_Week_Task() == null){
                next_week_plan.setText("无");
            }else{
                next_week_plan.setText(mlist.getGetMeetingProjectTaskDetail().getNext_Week_Task());
            }

            if (mlist.getGetMeetingProjectTaskDetail().getMediate_Matters() == null){
                coordination.setText("无");
            }else{
                coordination.setText(mlist.getGetMeetingProjectTaskDetail().getMediate_Matters());
            }

            if (mlist.getGetMeetingProjectTaskDetail().getFund_Thing() == null){
                funding_situation.setText("无");
            }else{
                funding_situation.setText(mlist.getGetMeetingProjectTaskDetail().getFund_Thing());
            }

            if (mlist.getGetMeetingProjectTaskDetail().getAnalyse_Error() == null){
                project_correction.setText("无");
            }else{
                project_correction.setText(mlist.getGetMeetingProjectTaskDetail().getAnalyse_Error());
            }
        }
    }
}
