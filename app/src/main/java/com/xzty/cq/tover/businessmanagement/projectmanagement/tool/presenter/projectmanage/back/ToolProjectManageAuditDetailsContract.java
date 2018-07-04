package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.projectmanage.back;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/28.
 * explain
 */

public interface ToolProjectManageAuditDetailsContract {
    interface View extends BaseContract.View<ToolProjectManageAuditDetailsContract.Presenter>{
        void seccess(List<RspAuditDetails> list);

        void clickSucceess(List<RspAuditDetails> list,String chooseString);

        void submitSuccess();
    }

    interface Presenter extends BaseContract.Presenter{
        void getData(int id);

        void submit(List<RspAuditDetails> list,int backId);

        void fixClick(List<RspAuditDetails> list,int position);

        void demageClick(List<RspAuditDetails> list,int position);

    }
}
