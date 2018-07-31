package com.xzty.cq.tover.businessmanagement.common.main.frg;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseFragment;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.common.main.activity.ManageActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.AllManageItem;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.all.PartProjactListAdapter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.all.ProjectListActivity;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.all.ProjectListContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.all.ProjectListPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.all.RspProjectListModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.CustomRefreshFoot;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.CustomRefreshHead;

import java.util.List;

import butterknife.BindView;

/**
 * author yq
 * date 2018/7/31
 * 管理模块--项目列表
 */
public class ManageFragment extends BaseFragment implements ProjectListContract.View,View.OnClickListener{

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

    private ProjectListPresenter mPresenter = new ProjectListPresenter(this);

    private RecyclerView.LayoutManager layoutManager;

    private PartProjactListAdapter mAdapter;

    //数据
    private List<RspProjectListModel> mList;

    @Override
    public int getContentLayout() {
        return R.layout.activity_project_list;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        tv_toolbarTitle.setText("项目列表");
        btn_search.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Account.load(this.getActivity());
        String projectName = et_projectName.getText().toString().trim();
        String projectId = Account.getemployId();
        mPresenter.search(projectId, projectName);
        layoutManager = new LinearLayoutManager(this.getActivity(), LinearLayout.VERTICAL, false);
    }

    @Override
    public void success(List<RspProjectListModel> model) {
        mList = model;
        layoutManager = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        swipe_target.setLayoutManager(layoutManager);
        mAdapter = new PartProjactListAdapter(R.layout.part_projectlist_recyle_item, mList);
        swipe_target.setAdapter(mAdapter);
        initRefresh();
        initLoadMore();
        initItemClick();
    }

    @Override
    public void showError(String str) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void setPresenter(ProjectListContract.Presenter presenter) {

    }

    /**
     * item的点击事件
     */
    private void initItemClick() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Account.savaProjectId(getActivity(), mList.get(position).getProjectId() + "");
                startActivity(new Intent(getActivity(), ManageActivity.class));
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

    /**
     * 搜索的点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        String projectName = et_projectName.getText().toString().trim();
        String projectId = Account.getemployId();
        mPresenter.search(projectId, projectName);
    }
}
