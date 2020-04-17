package com.linksame.crm.erp.work.controller;

import cn.hutool.core.util.StrUtil;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.common.constant.BaseConstant;
import com.linksame.crm.erp.admin.service.AdminUserService;
import com.linksame.crm.erp.oa.common.OaEnum;
import com.linksame.crm.erp.work.entity.Task;
import com.linksame.crm.erp.work.entity.TaskRelation;
import com.linksame.crm.erp.work.entity.Work;
import com.linksame.crm.erp.work.entity.WorkTaskClass;
import com.linksame.crm.erp.work.service.TaskService;
import com.linksame.crm.utils.AuthUtil;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import com.linksame.crm.utils.TagUtil;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan
 * @date 2020/4/12 14:32
 * @Description 任务控制层
 */
@Api(description="任务接口管理")
public class TaskController extends Controller{

    @Inject
    private TaskService taskService;
    @Inject
    private AdminUserService userService;

    /**
     * @param taskClass 设置任务分类
     */
    @ApiOperation(methods= RequestMethod.POST, description="设置任务分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskClass", description="任务分类对象")
    })
    public void setTaskClass(@Para("") WorkTaskClass taskClass){
        renderJson(taskService.setTaskClass(taskClass));
    }

    /**
     * 交换任务列表排序
     */
    @ApiOperation(methods= RequestMethod.POST, description="交换任务列表排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name="originalClassId", description="原始分类id"),
            @ApiImplicitParam(name="targetClassId", description="目标分类id")
    })
    public void changeOrderTaskClass(){
        String originalClassId = getPara("originalClassId");
        String targetClassId = getPara("targetClassId");
        taskService.changeOrderTaskClass(originalClassId, targetClassId);
        renderJson(R.ok());
    }

    /**
     * @param task 设置任务
     */
    @ApiOperation(methods= RequestMethod.POST, description="设置任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="task", description="任务对象"),
            @ApiImplicitParam(name="customerIds", description="客户编号(逗号分隔)"),
            @ApiImplicitParam(name="contactsIds", description="联系人编号(逗号分隔)"),
            @ApiImplicitParam(name="businessIds", description="商机编号(逗号分隔)"),
            @ApiImplicitParam(name="contractIds", description="合同编号(逗号分隔)")
    })
    public void setTask(@Para("") Task task){
        boolean oaAuth = false;
        boolean workAuth = false;
        if(task.getPid() != null && task.getPid() != 0){
            oaAuth = AuthUtil.isOaAuth(OaEnum.TASK_TYPE_KEY.getTypes(), task.getPid());
        }
        if(task.getWorkId() != null){
            Integer isOpen = new Work().findById(task.getWorkId()).getIsOpen();
            workAuth = isOpen == 0 && ! AuthUtil.isWorkAuth(task.getWorkId().toString(), "task:save");
        }
        if(oaAuth || workAuth){
            renderJson(R.noAuth());
            return;
        }
        if(StrUtil.isNotEmpty(task.getOwnerUserId())){
            task.setOwnerUserId(TagUtil.fromString(task.getOwnerUserId()));
        }
        if(task.getStartTime() != null && task.getStopTime() != null){
            if(task.getStartTime().getTime() > task.getStopTime().getTime()){
                renderJson(R.error("开始时间不能大于结束时间"));
                return;
            }
        }
        String customerIds = getPara("customerIds");
        String contactsIds = getPara("contactsIds");
        String businessIds = getPara("businessIds");
        String contractIds = getPara("contractIds");
        TaskRelation taskRelation = new TaskRelation();
        if(customerIds != null || contactsIds != null || businessIds != null || contractIds != null){

            taskRelation.setBusinessIds(TagUtil.fromString(businessIds));
            taskRelation.setContactsIds(TagUtil.fromString(contactsIds));
            taskRelation.setContractIds(TagUtil.fromString(contractIds));
            taskRelation.setCustomerIds(TagUtil.fromString(customerIds));
        }
        renderJson(taskService.setTask(task, taskRelation));
    }


    /**
     * 查询任务列表
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="labelId", description="标签编号"),
            @ApiImplicitParam(name="ishidden", description="删除状态(0未删除 1删除)")
    })
    public void getTaskList(BasePageRequest basePageRequest){
        String labelId = getPara("labelId");
        String ishidden = getPara("ishidden");
        renderJson(taskService.getTaskList(basePageRequest, labelId, ishidden));
    }


    /**
     * 查询oa任务信息
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询oa任务信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", description="任务编号")
    })
    public void queryTaskInfo(){
        String taskId = getPara("taskId");
        boolean oaAuth = AuthUtil.isOaAuth(OaEnum.TASK_TYPE_KEY.getTypes(), Integer.valueOf(taskId));
        if(oaAuth){
            renderJson(R.noAuth());
            return;
        }
        renderJson(taskService.queryTaskInfo(taskId));
    }

    /**
     * 查询任务列表 oa
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询oa任务列表")
    public void queryTaskList(BasePageRequest<Task> basePageRequest){
        Integer type = getParaToInt("type");
        Integer status = getParaToInt("status");
        Integer priority = getParaToInt("priority");
        Integer date = getParaToInt("date");
        Integer mold = getParaToInt("mold");
        Long userId = getLong("userId");
        String name = get("search");
        List<Long> userIds = new ArrayList<>();
        if(mold == null){
            userIds.add(BaseUtil.getUser().getUserId());
        }else if(mold == 1 && userId == null){
            userIds = userService.queryUserIdsByParentId(BaseUtil.getUser().getUserId());
        }else{
            List<Long> list = userService.queryChileUserIds(BaseUtil.getUser().getUserId(), BaseConstant.AUTH_DATA_RECURSION_NUM);
            for(Long id : list){
                if(id.equals(userId)){
                    userIds.add(userId);
                }
            }
        }
        renderJson(taskService.getTaskList(type, status, priority, date, userIds, basePageRequest, name));
    }

    /**
     * 根据任务id查询活动日志 oa
     * taskId 任务id
     */
    @ApiOperation(methods= RequestMethod.POST, description="根据任务id查询oa活动日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", description="任务编号")
    })
    public void queryWorkTaskLog(){
        Integer taskId = getParaToInt("taskId");
        boolean oaAuth = AuthUtil.isOaAuth(OaEnum.TASK_TYPE_KEY.getTypes(), taskId);
        if(oaAuth){
            renderJson(R.noAuth());
            return;
        }
        renderJson(taskService.queryWorkTaskLog(taskId));
    }

    /**
     * 添加任务与业务关联
     */
    @ApiOperation(methods= RequestMethod.POST, description="添加任务与业务关联")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskRelation", description="任务关联对象")
    })
    public void saveTaskRelation(@Para("") TaskRelation taskRelation){
        renderJson(taskService.saveTaskRelation(taskRelation, BaseUtil.getUser().getUserId()));
    }

    /**
     * 删除任务
     * taskId 任务id
     */
    @ApiOperation(methods= RequestMethod.POST, description="删除任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", description="任务编号")
    })
    public void deleteTask(){
        Integer taskId = getParaToInt("taskId");
        renderJson(taskService.deleteTask(taskId));
    }

    /**
     * crm查询关联任务
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询关联任务列表")
    public void queryTaskRelation(@Para("") BasePageRequest<TaskRelation> basePageRequest){
        renderJson(taskService.queryTaskRelation(basePageRequest));
    }

    /**
     * 根据任务id归档任务
     * @param taskId 任务ID
     */
    @ApiOperation(methods= RequestMethod.POST, description="根据任务id归档任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", description="任务编号")
    })
    public void archiveByTaskId(@Para("taskId") Integer taskId){
        renderJson(taskService.archiveByTaskId(taskId));
    }

    /**
     * 根据任务名称模糊搜索任务列表
     */
    @ApiOperation(methods= RequestMethod.POST, description="根据任务名称模糊搜索任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="basePageRequest", description="请求对象")
    })
    public void queryTaskByName(BasePageRequest basePageRequest){
        renderJson(taskService.queryTaskByName(basePageRequest));
    }
}
