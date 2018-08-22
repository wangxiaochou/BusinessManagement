package com.xzty.cq.tover.businessmanagement.new_mainlist.ing_fragment.view;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xzty.cq.tover.businessmanagement.R;
import com.xzty.cq.tover.businessmanagement.common.app.BaseFragment;
import com.xzty.cq.tover.businessmanagement.common.factory.Account;
import com.xzty.cq.tover.businessmanagement.new_bottomnavigation.NewBottomNavigation_Activity;
import com.xzty.cq.tover.businessmanagement.new_mainlist.all_fragment.view.All_Fragment_Adapter;
import com.xzty.cq.tover.businessmanagement.new_mainlist.ing_fragment.contract.Ing_Fragment_Contract;
import com.xzty.cq.tover.businessmanagement.new_mainlist.ing_fragment.presenter.Ing_Fragment_Presenter;
import com.xzty.cq.tover.businessmanagement.new_mainlist.model.NewRspProjectListModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author wl
 * Created 2018/08/21
 * explain 首页新项目列表NewProjectList_Ing_Fragment
 */

public class NewProjectList_Ing_Fragment extends BaseFragment
        implements Ing_Fragment_Contract.View,BaseQuickAdapter.OnItemClickListener,View.OnClickListener{

    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;

//    @BindView(R.id.swipe_refresh_header)
//    CustomRefreshHead swipe_refresh_header;

    @BindView(R.id.swipe_target)
    RecyclerView swipe_target;

    @BindView(R.id.et_projectName)
    EditText et_projectName;

    @BindView(R.id.btn_search)
    Button btn_search;

    private Ing_Fragment_Presenter mPresenter = new Ing_Fragment_Presenter(this);

    private RecyclerView.LayoutManager layoutManager;

    private All_Fragment_Adapter mAdapter;

    //数据
    private List<NewRspProjectListModel> mList;

    @Override
    public int getContentLayout() {
        return R.layout.new_projectlist_ing_fragment;
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

        //判断项目是否为在建项目
        List<NewRspProjectListModel> inBuild = new ArrayList<NewRspProjectListModel>();
        Log.e("数据量",mList.size()+"");
        for (NewRspProjectListModel model2:this.mList) {
            if (model2 != null){
                Log.e("是都在建",model2.toString());
                if (model2.getIsBuild() != 1){
                    Log.e("模型","未在建");
                }else {
                    inBuild.add(model2);
                    Log.e("模型", "在建");
                }
            }else {
                Log.e("模型", "空");
            }
        }

        Ing_Fragment_Adapter adapter = new Ing_Fragment_Adapter(R.layout.part_projectlist_recyle_item,inBuild);
        swipe_target.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void success(List<NewRspProjectListModel> model) {
        mList = model;
        setAdapter();
//        initRefresh();
    }

    @Override
    public void showError(String str) {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void setPresenter(Ing_Fragment_Contract.Presenter presenter) {

    }

    /**
     * item的点击事件
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Account.savaProjectId(NewProjectList_Ing_Fragment.this.getActivity(),mList.get(position).getProjectId() + "");
//        startActivity(new Intent(NewProjectListActivity.this, ManageActivity.class));
        startActivity(new Intent(NewProjectList_Ing_Fragment.this.getActivity(), NewBottomNavigation_Activity.class));
    }

//    /**
//     * 下拉刷新
//     */
//    private void initRefresh() {
//        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mAdapter.notifyDataSetChanged();
//                swipeToLoadLayout.setRefreshing(false);
//            }
//        });
//    }

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
