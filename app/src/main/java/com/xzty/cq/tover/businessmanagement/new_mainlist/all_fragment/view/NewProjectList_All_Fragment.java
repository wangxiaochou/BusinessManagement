package com.xzty.cq.tover.businessmanagement.new_mainlist.all_fragment.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseFragment;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.new_bottomnavigation.NewBottomNavigation_Activity;
import com.xzty.cq.tover.businessmanagement.new_mainlist.all_fragment.contract.All_Fragment_Contract;
import com.xzty.cq.tover.businessmanagement.new_mainlist.all_fragment.presenter.All_Fragment_Presenter;
import com.xzty.cq.tover.businessmanagement.new_mainlist.model.NewRspProjectListModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.CustomRefreshHead;

import java.util.List;

import butterknife.BindView;

/**
 * author wl
 * Created 2018/08/21
 * explain 首页新项目列表NewProjectList_All_Fragment
 */

public class NewProjectList_All_Fragment extends BaseFragment
        implements All_Fragment_Contract.View,BaseQuickAdapter.OnItemClickListener,View.OnClickListener{

    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

    @BindView(R.id.swipe_refresh_header)
    CustomRefreshHead swipe_refresh_header;

    @BindView(R.id.swipe_target)
    RecyclerView swipe_target;

    @BindView(R.id.et_projectName)
    EditText et_projectName;

    @BindView(R.id.btn_search)
    Button btn_search;

    private All_Fragment_Presenter mPresenter = new All_Fragment_Presenter(this);

    private RecyclerView.LayoutManager layoutManager;

    private All_Fragment_Adapter mAdapter;

    //数据
    private List<NewRspProjectListModel> mList;

    @Override
    public int getContentLayout() {
        return R.layout.new_projectlist_all_fragment;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        btn_search.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Account.load( this.getActivity());
        String projectName = et_projectName.getText().toString().trim();
        String projectId = Account.getemployId();
        mPresenter.search(projectId, projectName);
    }

    private void setAdapter(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity(), LinearLayout.VERTICAL, false);
        swipe_target.setLayoutManager(layoutManager);
        All_Fragment_Adapter adapter = new All_Fragment_Adapter(R.layout.part_projectlist_recyle_item,mList);
        swipe_target.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void success(List<NewRspProjectListModel> model) {
        mList = model;
        setAdapter();
        initRefresh();
    }

    @Override
    public void showError(String str) {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void setPresenter(All_Fragment_Contract.Presenter presenter) {

    }

    /**
     * item的点击事件
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Account.savaProjectId(NewProjectList_All_Fragment.this.getActivity(),mList.get(position).getProjectId() + "");
//        startActivity(new Intent(NewProjectListActivity.this, ManageActivity.class));
        startActivity(new Intent(NewProjectList_All_Fragment.this.getActivity(), NewBottomNavigation_Activity.class));
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
