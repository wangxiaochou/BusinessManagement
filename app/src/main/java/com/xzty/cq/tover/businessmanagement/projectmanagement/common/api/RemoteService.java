package com.xzty.cq.tover.businessmanagement.projectmanagement.common.api;

import com.xzty.cq.tover.businessmanagement.common.model.ReqLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspLogin;
import com.xzty.cq.tover.businessmanagement.common.model.RspModel;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model.GetMeetingProjectDetail;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_detail.model.ReqMeetingProjectDetail;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.bean.ProjectTaskList_List;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.Emp;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspApplyList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.apply.RspPartList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.back.RspChooseBack;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.collect.RspCollect;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.RspBuyInfo;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.deliver.RspPurchase;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.income.RspReceiveChoose;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.income.RspReceiveNota;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.pick.RspPickOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.part.model.use.RspUseChoose;
import com.xzty.cq.tover.businessmanagement.projectmanagement.project.all.RspProjectListModel;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.AuditDetailsFixOrDamage;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.ReqAuditDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.back.RspAuditList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.ReqBackApply;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.ReqReceive;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackApply;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspBackList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspDiliverDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspOtherDiliver;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceive;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceiveDetais;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.other.RspReceiveList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.ReqDiliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.ReqDitribute;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.ReqIn;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspAllDistribute;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDemage;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDiliverOrder;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistributeOrderDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.RspDistributeOrderList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.pickdepartment.ToolBDInRequest;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.ReqToolApply;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.ReqToolCommit;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmDetails;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspAffirmList;
import com.xzty.cq.tover.businessmanagement.projectmanagement.tool.model.projectmanage.RspToolApplyList;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * author zzl
 * Created 2018/5/2.
 * explain 请求接口
 */

public interface RemoteService {
    /**
     *    登录接口
     *
     * @return
     */
    @POST("Androidlogin/androidlogin")
    Observable<RspModel<RspLogin>> login(@Body ReqLogin reqLogin);

    @GET("Androiditem/androidblur")
    Observable<RspModel<List<RspProjectListModel>>> getProject(@Query("employId") String id
            , @Query("projectName") String name);

    @GET("Androidapply/androidapplylst")
    Observable<RspModel<List<RspApplyList>>> getPartList(@Query("projectId") String projectId,
                                                         @Query("applyOrderBeginTime") String applyOrderBeginTime,
                                                         @Query("applyPerson") String applyPerson,
                                                         @Query("applyOrderEndTime") String applyOrderEndTime,
                                                         @Query("applyOrderState") String applyOrderState);

    /**
     * 获得所有构件
     *
     * @param projectId
     * @param partName
     * @param partNo
     * @param state
     * @param partBatch
     * @return
     */
    @GET("Androidapply/androidchoice")
    Observable<RspModel<List<RspPartList>>> queryAllPart(@Query("projectId") String projectId,
                                                         @Query("partName") String partName,
                                                         @Query("partNo") String partNo,
                                                         @Query("state") String state,
                                                         @Query("partBatch") String partBatch);

    /**
     * 申请构件提交
     *
     * @param multipartBody
     * @return
     */
    @POST("Androidapply/androidapplyadd")
    Observable<RspModel<RspLogin>> commitPart(@Body MultipartBody multipartBody);

    /**
     * 申请详情
     *
     * @param id
     * @return
     */
    @GET("Androidapply/androidapplydetail")
    Observable<RspModel<List<RspApplyDetails>>> details(@Query("applyId") String id);

    /**
     * 查询申请单构件详细信息
     *
     * @param projectId
     * @param partId
     * @return
     */
    @GET("Androidpurchase/androidpartdetail")
    Observable<RspModel<List<RspPartList>>> queryPartDetails(@Query("projectId") String projectId, @Query("partId") String partId);


    /**
     * 申请总汇页面
     *
     * @param maps
     * @return
     */
    @GET("Androidpurchase/androidapplylst")
    Observable<RspModel<List<RspCollect>>> getCollect(@QueryMap Map<String, String> maps);

    /**
     * 确认申请
     *
     * @param applyId
     * @return
     */
    @GET("Androidapply/androidchangestatus")
    Observable<RspModel<List<RspLogin>>> notaApply(@Query("applyId") String applyId);

    /**
     * 采购部-采购分配-查询出所有采购人
     *
     * @return
     */
    @GET("Androidpurchase/androidpurchaserchoice")
    Observable<RspModel<List<Emp>>> queryAllBuyer();

    /**
     * 分配
     *
     * @param maps
     * @return
     */
    @Multipart
    @POST("Androidpurchase/androidallotorderadd")
    Observable<RspModel<RspLogin>> collectSave(@PartMap Map<String, RequestBody> maps);

    /**
     * 获取采购单
     *
     * @param maps
     * @return
     */
    @GET("Androidpurchase/androidpurchaseorder")
    Observable<RspModel<List<RspPickOrder>>> queryPurchaseList(@QueryMap Map<String, String> maps);

    /**
     * 采购单确认
     *
     * @param distId
     * @return
     */
    @GET("Androidpurchase/androidpurchaseorderstate")
    Observable<RspModel<List<RspLogin>>> affirmPurchase(@Query("distId") String distId);

    /**
     * 采购单详情
     */
    @GET("Androidpurchase/androidpurchaseorderdetail")
    Observable<RspModel<List<RspPickList>>> getDistOrderPart(@Query("distId") String distId);

    /**
     * 填写采购信息
     *
     * @param maps
     * @return
     */
    @Multipart
    @POST("Androidout/androidoutorderadd")
    Observable<RspModel<RspLogin>> overPurchase(@PartMap Map<String, RequestBody> maps);

    /**
     * 采购单详细查看
     *
     * @param distId
     * @return
     */
    @GET("Androidpurchase/androidpurchaseorderdetaillst")
    Observable<RspModel<List<RspPickList>>> purcgaseDetails(@Query("distId") String distId);

    /**
     * 发货单数据
     *
     * @param maps
     * @return
     */
    @GET("Androidout/androidoutorderlst")
    Observable<RspModel<List<RspPurchase>>> getPurchaseList(@QueryMap Map<String, String> maps);

    /**
     * 填写发货信息
     *
     * @param outId
     * @return
     */
    @GET("Androidout/androidoutorderdetail")
    Observable<RspModel<List<RspBuyInfo>>> getBuyInfo(@Query("outId") String outId);

    /**
     * 发货信息填写  保存
     *
     * @param mList
     * @param maps
     * @return
     */
    @Multipart
    @POST("Androidout/androidoutorderedit")
    Observable<RspModel<List<RspLogin>>> shipments(@Part List<MultipartBody.Part> mList, @PartMap Map<String, RequestBody> maps);

    /**
     * 查看收货单
     *
     * @param projectId
     * @param userId
     * @param beginOutTime
     * @param endOutTime
     * @param orderState
     * @return
     */
    @GET("Androidcollect/androidoutorderlst")
    Observable<RspModel<List<RspReceiveNota>>> queryReceiveNote(@Query("projectId") String projectId, @Query("userId") String userId, @Query("beginOutTime") String beginOutTime, @Query("endOutTime") String endOutTime, @Query("orderState") String orderState);

    @GET("Androidcollect/androidoutorderdetail")
    Observable<RspModel<List<RspReceiveChoose>>> getArriveInfo(@Query("outId") String outId);


    /**
     * 填写收货单
     *
     * @param list
     * @param field
     * @return
     */
    @Multipart
    @POST("Androidcollect/androidcollectorderadd")
    Observable<RspModel<List<RspLogin>>> cargeSave(@Part List<MultipartBody.Part> list,
                                                   @PartMap() Map<String, RequestBody> field);

    /**
     * 构件问题反馈
     *
     * @param picList
     * @param maps
     * @return
     */
    @Multipart
    @POST("Androidcollect/androidfeedback")
    Observable<RspModel<RspLogin>> partFeedBack(@Part List<MultipartBody.Part> picList, @PartMap() Map<String, RequestBody> maps);


    /**
     * 领用选择构件页面
     *
     * @param maps
     * @return
     */
    @GET("Androidpick/androidtotaldetail")
    Observable<RspModel<List<RspUseChoose>>> queryChoosePart(@QueryMap Map<String, String> maps);

    /**
     * 领用信息填写页面
     *
     * @param mList
     * @param map
     * @return
     */
    @Multipart
    @POST("Androidpick/androidpickorderadd")
    Observable<RspModel<RspLogin>>receiveInfo(@Part List<MultipartBody.Part> mList, @PartMap() Map<String, RequestBody> map);

    /**
     * 退回部分中的选择构件
     *
     * @param maps
     * @return
     */
    @GET("Androidback/androidquerypickdetail")
    Observable<RspModel<List<RspChooseBack>>> backList(@QueryMap Map<String, String> maps);

    /**
     * 退回构件信息填写
     *
     * @param mList
     * @param map
     * @return
     */
    @Multipart
    @POST("Androidback/androidbackorderadd")
    Observable<RspModel<RspLogin>> backInfo(@Part List<MultipartBody.Part> mList, @PartMap() Map<String, RequestBody> map);

    /**
     * 提量
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Androidraise/androidedittimeandcount")
    Observable<RspModel<RspLogin>> promoteList(@Field("partList")String s);
    //机具部分

    //机具列表
    @GET("Androidapplytool/toolandroidchoice")
    Observable<RspModel<List<RspToolApplyList>>> getToolList(@Query("searchToolName") String search);

    //机具-项管部-提交申请单
    @POST("Androidapplytool/toolandroidapplyadd")
    Observable<RspModel<List<RspLogin>>> SubmitApply(@Body ReqToolApply request);

    //机具-项管部-待确认申请单列表
    @GET("Androiditemmanagetool/xgtoolandroidapplylst")
    Observable<RspModel<List<RspAffirmList>>> getToolApplyConfirmList(@Query("projectId") String id);

    //机具-项管部-待确认申请单详情
    @GET("Androiditemmanagetool/xgtoolandroidapplydetail")
    Observable<RspModel<List<RspAffirmDetails>>> getToolApplyConfirmDetail(@Query("TOOL_APPLY_ID") int id);

    //机具-拒绝申请
    @GET("Androidapplytool/toolchangestatus")
    Observable<RspModel<List<RspLogin>>> refuseApply(@Query("ids") String jsonS);

    //机具-项管部-提交确认申请单
    @POST("Androiditemmanagetool/xgtoolandroidmoveout")
    Observable<RspModel<List<RspLogin>>> mdSubmitApplyConfirm(@Body ReqToolCommit request);

    //机具-项管部-返库待审核列表
    @GET("Androidbackdepottool/xgandroidtoolbackconfirm")
    Observable<RspModel<List<RspAuditList>>> getBackConfirmList(@Query("projectId") String projectId);

    //机具-项管部-返库待审核详情
    @GET("Androidbackdepottool/xgandroidtoolbackconfirmdetail")
    Observable<RspModel<List<RspAuditDetails>>> getBackConfirmDetail(@Query("toolBackId") int toolBackId);

    //机具-项管部-返库审核
    @POST("Androidbackdepottool/xgandroidtoolbackdepot")
    Observable<RspModel<RspLogin>> mdSubmitBackConfirm(@Body ReqAuditDetails submit);

    //机具-返库维修/报废
    @POST("Androidbackdepottool/androidtoolbackchangestatus")
    Observable<RspModel<RspLogin>> backFixOrDamage(@Body AuditDetailsFixOrDamage fixOrDamage);

    //机具-采购部-待确认申请单列表
    @GET("Androidpurchaseunittool/cgtoolandroidapplylst")
    Observable<RspModel<List<RspAffirmList>>> getToolBDApplyConfirmList(@Query("projectId") int id);


    //机具-采购部-待确认申请单详情
    @GET("Androidpurchaseunittool/cgtoolandroidapplydetail")
    Observable<RspModel<List<RspAffirmDetails>>> getToolBDApplyConfirmDetail(@Query("toolApplyId") int id);

    //机具-采购部-提交确认申请单
    @POST("Androidpurchaseunittool/cgtoolandroidmoveout")
    Observable<RspModel<List<RspLogin>>> bdSubmitApplyConfirm(@Body ReqToolCommit request);

    //机具-采购部-采购分配列表
    @GET("Androidpurchaseunittool/cgtoolandroidallotlst")
    Observable<RspModel<RspAllDistribute>> getToolBDBuyAssignList(@Query("projectId") String id);

    //机具-采购部-采购分配提交
    @POST("Androidpurchaseunittool/cgtoolandroidallotadd")
    Observable<RspModel<RspLogin>> bdSubmitToolBuyAssign(@Body ReqDitribute request);

    //机具-采购部-采购分配单列表
    @GET("Androidpurchaseunittool/cgtoolandroidpurchaseorder")
    Observable<RspModel<List<RspDistributeOrderList>>> getBDToolBuyAssign(@Query("eplyNum") String eplyNumber, @Query("projectId") String projectId);

    //机具-采购部-采购分配单详情
    @GET("Androidpurchaseunittool/cgtoolandoridpurchasedetail")
    Observable<RspModel<List<RspDistributeOrderDetails>>> getBDToolBuyAssignDetail(@Query("toolDistId") int toolDistId);


    //机具-采购部-采购分配单确认
    @GET("Androidpurchaseunittool/cgtoolchangestatus")
    Observable<RspModel<RspLogin>> bdToolBuyAssignConfirm(@Query("toolDistId") int toolDistId);

    //机具-采购部-入库-报废编号复用
    @GET("Androidpurchaseunittool/cgscraptool")
    Observable<RspModel<List<RspDemage>>> getDamageNumber();

    //机具-采购部-入库
    @POST("Androidpurchaseunittool/cgtoolandroidstorage")
    Observable<RspModel<RspLogin>> bdSubmitIn(@Body ToolBDInRequest request);

    //机具-采购部-入库后待发货
    @POST("Androidpurchaseunittool/cgtoolandroidaddoutmove")
    Observable<RspModel<RspLogin>> bdInWaitOut(@Body ReqIn request);

    //机具-采购部-待发货列表
    @GET("Androidpurchaseunittool/cgtoolandroidoutlst")
    Observable<RspModel<List<RspDiliverOrder>>> getToolBDOutList(@Query("projectId") String id);

    //机具-采购部-发货
    @POST("Androidpurchaseunittool/cgtoolandroidout")
    Observable<RspModel<RspLogin>> sendToolBDOut(@Body ReqDiliverOrder request);

    //机具-项管部-待发货列表
    @GET("Androiditemmanagetool/xgtoolandroidoutlst")
    Observable<RspModel<List<RspDiliverOrder>>> getToolOutList(@Query("projectId") String id);

    //机具-项管部-发货
    @POST("Androiditemmanagetool/xgtoolandroidout")
    Observable<RspModel<RspLogin>> sendToolOut(@Body ReqDiliverOrder request);

    //机具-采购部-返库待审核列表
    @GET("Androidbackdepottool/cgandroidtoolbackconfirm")
    Observable<RspModel<List<RspAuditList>>> bdGetBackConfirmList(@Query("projectId") String projectId);

    //机具-采购部-返库待审核详情
    @GET("Androidbackdepottool/cgandroidtoolbackconfirmdetail")
    Observable<RspModel<List<RspAuditDetails>>> bdGetBackConfirmDetail(@Query("toolBackId") int toolBackId);

    //机具-采购部-返库审核
    @POST("Androidbackdepottool/cgandroidtoolbackdepot")
    Observable<RspModel<RspLogin>> bdSubmitBackConfirm(@Body ReqAuditDetails submit);

    //机具-其他-发货单
    @GET("Androiditemmanagetool/xgtoolandroidoutorder")
    Observable<RspModel<List<RspOtherDiliver>>> getOutList(@Query("projectId") String id);

    //机具-其他-发货单详情
    @GET("Androiditemmanagetool/xgtoolandroidoutorderdetail")
    Observable<RspModel<List<RspDiliverDetails>>> getOutDetail(@Query("toolOutId") int id);

    //机具-其他-待收货列表
    @GET("Androidcollecttool/androidtooloutdetaillst")
    Observable<RspModel<List<RspReceive>>> oWaitGet(@Query("projectId") String id);

    //机具-其他-收货
    @POST("Androidcollecttool/androidtoolcollectorderadd")
    Observable<RspModel<RspLogin>> oGet(@Body ReqReceive request);

    //机具-其他-收货单列表
    @GET("Androidcollecttool/androidtoolcollectorder")
    Observable<RspModel<List<RspReceiveList>>> oGetList(@Query("projectId") String id);

    //机具-其他-收货单详情
    @GET("Androidcollecttool/androidtoolcollectorderdetail")
    Observable<RspModel<List<RspReceiveDetais>>> oGetListDetail(@Query("collectId") int id);

    //机具-其他-返库列表
    @GET("Androidbackdepottool/androidtoolcollectdetail")
    Observable<RspModel<List<RspBackApply>>> oGetBackList(@Query("projectId") String id);

    //机具-其他-返库申请
    @POST("Androidbackdepottool/androidtoolbackorderadd")
    Observable<RspModel<RspLogin>> onBack(@Body ReqBackApply request);

    //机具-其他-返库单列表
    @GET("Androidbackdepottool/androidtoolbackorderlst")
    Observable<RspModel<List<RspBackList>>> oGetBackListList(@Query("projectId") String id);

    //机具-其他-返库单详情
    @GET("Androidbackdepottool/androidtoolbackorderdetail")
    Observable<RspModel<List<RspBackDetails>>> oGetBackListDetail(@Query("toolBackId") int id);


    /**
     * 创建时间：2018年8月6号
     * 创建人：王令
     * **/

    //项管部-项目任务-周例会清单
    @GET("Androidweektask/projectmeetlst")
    Observable<RspModel<ProjectTaskList_List>> getId(@Query("id") int id);

    //项管部-项目任务-周例会项目任务
    @POST("Androidweektask/projectmeetdetail")
    Observable<RspModel<GetMeetingProjectDetail>> oMPDGet(@Body ReqMeetingProjectDetail request);


}
