package com.xzty.cq.tover.businessmanagement.projectmanagement.tool.presenter.other.diliver;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspDiliverDetails;

import java.util.List;

/**
 * author zzl
 * Created 2018/5/25.
 * explain
 */

public interface OtherDiliverDetailsContract{

    interface View extends BaseContract.View<OtherDiliverDetailsContract.Presenter>{
        void success(List<RspDiliverDetails> list);
    }

    interface Presenter extends BaseContract.Presenter{
        void getData(int outId);
    }
}
