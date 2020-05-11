package com.linksame.crm.erp.work.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.work.entity.TaskSprint;
import com.linksame.crm.erp.work.service.SprintService;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;

/**
 * @author Ivan
 * @date 2020/5/7 15:56
 * @Description 任务冲刺控制层
 */
@Api(description="任务冲刺接口管理")
public class SprintController extends Controller {

    @Inject
    private SprintService sprintService;

    /**
     * 查询任务冲刺数据列表
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询任务冲刺数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="basePageRequest", description="请求对象")
    })
    public void queryList(BasePageRequest<TaskSprint> basePageRequest){
        renderJson(sprintService.queryList(basePageRequest));
    }

    /**
     * 添加/编辑任务冲刺
     */
    @ApiOperation(methods= RequestMethod.POST, description="添加任务冲刺")
    @ApiImplicitParams({
            @ApiImplicitParam(name="TaskSprint", description="任务冲刺对象")
    })
    public void setSprint(@Para("") TaskSprint taskSprint){
        renderJson(sprintService.setSprint(taskSprint));
    }

    /**
     * 删除任务冲刺
     */
    @ApiOperation(methods= RequestMethod.POST, description="删除任务冲刺")
    @ApiImplicitParams({
            @ApiImplicitParam(name="sprintId", description="任务冲刺ID")
    })
    public void delSprint(){
        renderJson(sprintService.delSprint(getInt("sprintId")));
    }

}
