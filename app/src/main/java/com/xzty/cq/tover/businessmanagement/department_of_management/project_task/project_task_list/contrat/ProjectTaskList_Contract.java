package com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.contrat;

import com.xzty.cq.tover.businessmanagement.common.factory.BaseContract;
import com.xzty.cq.tover.businessmanagement.department_of_management.project_task.project_task_list.bean.ProjectTaskList_List;

import java.util.List;


/**
 * author wl
 * Created 2018/07/31
 * explain 项目任务列表contract契约类
 */

public interface ProjectTaskList_Contract {

    interface View extends BaseContract.View<ProjectTaskList_Contract.Presenter>{
        void success(List<ProjectTaskList_List> list);
    }

    interface Presenter extends BaseContract.Presenter{
        void getProjectTaskList(int id);
    }
}
