package com.linksame.crm.erp.work.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.erp.work.entity.TaskRemind;
import com.linksame.crm.erp.work.service.RemindService;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;

/**
 * @author Ivan
 * @date 2020/4/1 12:47
 * @Description 任务提醒控制层
 */
@Api(description="任务提醒接口管理")
public class RemindController extends Controller {
    @Inject
    private RemindService remindService;

    /**
     * @author Ivan
     * 设置任务提醒
     * @param taskRemind 任务提醒对象
     */
    @ApiOperation(methods=RequestMethod.POST, description="设置任务提醒")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskRemind", description="任务提醒对象")
    })
    @Permissions({"project:taskManage:saveRemind"})
    public void setRemind(@Para("") TaskRemind taskRemind){
        JSONObject jsonObject = JSON.parseObject(getRawData());
        renderJson(remindService.setRemind(jsonObject));
    }

    /**
     * @author Ivan
     * 根据任务id查询任务提醒数据
     * @param taskId 任务编号
     */
    @ApiOperation(methods=RequestMethod.POST, description="根据任务id查询任务提醒数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", description="任务编号")
    })
    public void queryByTaskId(@Para("taskId")Integer taskId){
        renderJson(remindService.queryByTaskId(taskId));
    }
}
