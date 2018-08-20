package com.xzty.cq.tover.businessmanagement.new_mainlist.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
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
import com.xzty.cq.tover.businessmanagement.new_bottomnavigation.NewBottomNavigation_Activity;
import com.xzty.cq.tover.businessmanagement.new_mainlist.contract.NewProjectListContract;
import com.xzty.cq.tover.businessmanagement.new_mainlist.model.NewRspProjectListModel;
import com.xzty.cq.tover.businessmanagement.new_mainlist.presenter.NewProjectListPresenter;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.CustomRefreshFoot;
import com.xzty.cq.tover.businessmanagement.projectmanagement.view.CustomRefreshHead;

import java.util.List;

import butterknife.BindView;

/**
 * author wl
 * Created 2018/08/20
 * explain 新项目列表Activity文件
 */

public class NewProjectListActivity extends ActivityPresenter<NewProjectListContract.Presenter>
        implements NewProjectListContract.View,BaseQuickAdapter.OnItemClickListener,View.OnClickListener{

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

    private NewProjectListPresenter mPresenter = new NewProjectListPresenter(this);

    private RecyclerView.LayoutManager layoutManager;

    private NewPartProjactListAdapter mAdapter;

    //数据
    private List<NewRspProjectListModel> mList;

    private long exitTime = 0;

    @Override
    public int getContentLayoutId() {
        return R.layout.new_mainlist;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tv_toolbarTitle.setText("项目列表");
        btn_search.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();
        Account.load(this);
        String projectName = et_projectName.getText().toString().trim();
        String projectId = Account.getemployId();
        mPresenter.search(projectId, projectName);
    }

    private void setAdapter(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        swipe_target.setLayoutManager(layoutManager);
        NewPartProjactListAdapter adapter = new NewPartProjactListAdapter(R.layout.part_projectlist_recyle_item,mList);
        swipe_target.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void success(List<NewRspProjectListModel> model) {
        mList = model;
        setAdapter();
        initRefresh();
        initLoadMore();
    }

    @Override
    public void showError(String str) {
    }

    @Override
    public void showLoading() {
    }

    @Override
    protected NewProjectListContract.Presenter initPresenter() {
        return new NewProjectListPresenter(this);
    }

    /**
     * item的点击事件
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Account.savaProjectId(NewProjectListActivity.this,mList.get(position).getProjectId() + "");
//        startActivity(new Intent(NewProjectListActivity.this, ManageActivity.class));
        startActivity(new Intent(NewProjectListActivity.this, NewBottomNavigation_Activity.class));
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
