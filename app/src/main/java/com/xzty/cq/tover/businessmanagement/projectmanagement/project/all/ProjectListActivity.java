package com.xzty.cq.tover.businessmanagement.projectmanagement.project.all;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.factory.ActivityPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.AllManageItem;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.CustomRefreshFoot;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.CustomRefreshHead;

import java.util.List;

import butterknife.BindView;

/**
 * author zzl
 * Created 2018/5/3.
 * explain 工程列表
 */
public class ProjectListActivity extends ActivityPresenter<ProjectListContract.Presenter> implements ProjectListContract.View, View.OnClickListener {
    @BindView(R.id.tv_toolbarTitle)
    TextView tv_toolbarTitle;

    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    @BindView(R.id.swipe_refresh_header)
    CustomRefreshHead swipe_refresh_header;

    @BindView(R.id.swipe_load_more_footer)
    CustomRefreshFoot swipe_load_more_footer;

    @BindView(R.id.swipe_target)
    RecyclerView swipe_target;

    @BindView(R.id.et_projectName)
    EditText et_projectName;

    @BindView(R.id.btn_search)
    Button btn_search;

    private RecyclerView.LayoutManager layoutManager;

    private PartProjactListAdapter mAdapter;

    //数据
    private List<RspProjectListModel> mList;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_project_list;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("项目列表");
    }

    @Override
    protected void initData() {
        super.initData();
        Account.load(this);
        String projectName = et_projectName.getText().toString().trim();
        String projectId = Account.getemployId().toString();
        mPresenter.search(projectId, projectName);
        layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        btn_search.setOnClickListener(this);
    }

    /**
     * item的点击事件
     */
    private void initItemClick() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Account.savaProjectId(ProjectListActivity.this, mList.get(position).getProjectId() + "");
                startActivity(new Intent(ProjectListActivity.this, AllManageItem.class));
            }
        });
    }

    /**
     * 上拉加载更多
     */
    private void initLoadMore() {
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mAdapter.notifyDataSetChanged();
                swipeToLoadLayout.setLoadingMore(false);
            }
        });
    }

    /**
     * 下拉刷新
     */
    private void initRefresh() {
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.notifyDataSetChanged();
                swipeToLoadLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void showError(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }


    @Override
    protected ProjectListContract.Presenter initPresenter() {
        return new ProjectListPresenter(this);
    }

    @Override
    public void success(List<RspProjectListModel> model) {

        mList = model;
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        swipe_target.setLayoutManager(layoutManager);
        mAdapter = new PartProjactListAdapter(R.layout.part_projectlist_recyle_item, mList);
        swipe_target.setAdapter(mAdapter);
        initRefresh();
        initLoadMore();
        initItemClick();
    }

    /**
     * 搜索的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        String projectName = et_projectName.getText().toString().trim();
        String projectId = Account.getemployId().toString();
        mPresenter.search(projectId, projectName);
    }
}
