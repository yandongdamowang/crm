package com.linksame.crm.erp.work.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.work.entity.Work;
import com.linksame.crm.erp.work.service.WorkService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.utils.AuthUtil;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;

/**
 * @author hmb
 */
@Api(description="项目接口管理")
public class WorkController extends Controller {

    @Inject
    private WorkService workService;

    /**
     * @author hmb
     * 设置项目
     * @param work 项目对象
     */
    @ApiOperation(methods= RequestMethod.POST, description="设置项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name="work", description="项目对象")
    })
    @Permissions({"project:projectManage:save"})
    public void setWork(@Para("") Work work){
        renderJson(workService.setWork(work));
    }

    /**
     * 通过项目编号查询项目详情
     */
    @ApiOperation(methods= RequestMethod.POST, description="通过项目编号查询项目详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="workId", description="项目编号")
    })
    public void getWorkById(){
        String workId = getPara("workId");
        renderJson(workService.getWorkById(workId));
    }

    /**
     * @author hmb
     * 删除项目
     */
    @ApiOperation(methods= RequestMethod.POST, description="删除项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name="workId", description="项目编号")
    })
    public void deleteWork(){
        String workId = getPara("workId");
        renderJson(workService.deleteWork(workId));
    }

    /**
     * @author hmb
     * 查询项目名列表
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询项目名列表")
    public void queryWorkNameList(){
        renderJson(workService.queryWorkNameList());
    }

    /**
     * @author hmb
     * 根据项目id查询任务板
     */
    @ApiOperation(methods= RequestMethod.POST, description="根据项目id查询任务板")
    @ApiImplicitParams({
            @ApiImplicitParam(name="workId", description="项目编号")
    })
    public void queryTaskByWorkId(){
        JSONObject jsonObject = JSON.parseObject(getRawData());
        renderJson(workService.queryTaskByWorkId(jsonObject));
    }

    /**
     * @author hmb
     * 根据项目id查询项目附件
     */
    @ApiOperation(methods= RequestMethod.POST, description="根据项目id查询项目附件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="workId", description="项目编号")
    })
    public void queryTaskFileByWorkId(BasePageRequest<JSONObject> pageRequest){
        renderJson(workService.queryTaskFileByWorkId(pageRequest));
    }

    /**
     * @author hmb
     * 查询归档项目列表
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询归档项目列表")
    public void queryArchiveWorkList(BasePageRequest pageRequest){
        renderJson(workService.queryArchiveWorkList(pageRequest));
    }

    /**
     * @author hmb
     * 项目统计
     */
    @ApiOperation(methods= RequestMethod.POST, description="项目统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name="workId", description="项目编号")
    })
    public void workStatistics(){
        String workId = getPara("workId");
        renderJson(workService.workStatistics(workId));
    }

    /**
     * @author hmb
     * 查询项目成员
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询项目成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name="workId", description="项目编号")
    })
    public void queryWorkOwnerList(){
        String workId = getPara("workId");
        renderJson(workService.queryWorkOwnerList(workId));
    }

    /**
     * @author hmb
     * 修改项目任务排序
     */
    @ApiOperation(methods= RequestMethod.POST, description="修改项目任务排序")
    public void updateOrder(){
        JSONObject jsonObject = JSON.parseObject(getRawData());
        renderJson(workService.updateOrder(jsonObject));
    }

    /**
     * @author hmb
     * 退出项目
     */
    @ApiOperation(methods= RequestMethod.POST, description="退出项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name="workId", description="项目编号")
    })
    public void leave(){
        String workId = getPara("workId");
        Long userId = BaseUtil.getUserId();
        renderJson(workService.leave(workId,userId));
    }

    /**
     * @author hmb
     * 删除项目成员
     */
    @ApiOperation(methods= RequestMethod.POST, description="删除项目成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name="workId", description="项目编号"),
            @ApiImplicitParam(name="ownerUserId", description="项目成员编号")
    })
    public void removeWorkOwnerUser(){
        String workId = getPara("workId");
        Long userId = getLong("ownerUserId");
        renderJson(workService.leave(workId,userId));
    }

    /**
     * 查询项目管理角色列表
     * @author wyq
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询项目管理角色列表")
    public void queryRoleList(){
        renderJson(workService.queryRoleList());
    }

    /**
     * @author wyq
     * 查询项目设置成员管理列表
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询项目设置成员管理列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="workId", description="项目编号")
    })
    public void queryOwnerRoleList(@Para("workId")Integer workId){
        renderJson(workService.queryOwnerRoleList(workId));
    }

    /**
     * @author wyq
     * 保存项目角色管理设置
     */
    @ApiOperation(methods= RequestMethod.POST, description="保存项目角色管理设置")
    public void setOwnerRole(){
        JSONObject jsonObject = JSONObject.parseObject(getRawData());
        renderJson(workService.setOwnerRole(jsonObject));
    }

    /**
     * @author hmb
     * 删除任务列表
     */
    @ApiOperation(methods= RequestMethod.POST, description="删除任务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="classId", description="任务分类编号"),
            @ApiImplicitParam(name="workId", description="项目编号")
    })
    public void deleteTaskList(){
        String classId = getPara("classId");
        String workId = getPara("workId");
        if(!AuthUtil.isWorkAuth(workId,"taskClass:delete")){
            renderJson(R.noAuth());
            return;
        }
        renderJson(workService.deleteTaskList(workId,classId));
    }

    /**
     * @author hmb
     * 归档已完成的任务
     */
    @ApiOperation(methods= RequestMethod.POST, description="归档已完成的任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="classId", description="任务分类编号")
    })
    public void archiveTask(){
        String classId = getPara("classId");
        renderJson(workService.archiveTask(classId));
    }

    /**
     * @author hmb
     * 归档任务
     */
    @ApiOperation(methods= RequestMethod.POST, description="归档任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="workId", description="项目编号")
    })
    public void archList(){
        String workId = getPara("workId");
        renderJson(workService.archList(workId));
    }

    /**
     * @author hmb
     * 移除项目成员
     */
    @ApiOperation(methods= RequestMethod.POST, description="移除项目成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name="workId", description="项目编号"),
            @ApiImplicitParam(name="ownerUserId", description="项目成员编号")
    })
    public void remove(@Para("ownerUserId") Integer ownerUserId,@Para("workId") Integer workId){
        renderJson(workService.remove(ownerUserId,workId));
    }

    /**
     * @author hmb
     * 项目class排序
     */
    @ApiOperation(methods= RequestMethod.POST, description="项目class排序")
    public void updateClassOrder(){
        JSONObject jsonObject = JSONObject.parseObject(getRawData());
        renderJson(workService.updateClassOrder(jsonObject));
    }


    /**
     * 撤销任务归档
     */
    @ApiOperation(methods= RequestMethod.POST, description="撤销任务归档")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", description="任务编号")
    })
    public void activation(@Para("taskId") Integer taskId){
        renderJson(workService.activation(taskId));
    }

    /**
     * @author wyq
     * 查询有项目模块查看权限的员工
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询有项目模块查看权限的员工")
    public void queryProjectUser(){
        renderJson(workService.queryProjectUser());
    }
}
