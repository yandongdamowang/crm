package com.linksame.crm.erp.work.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.linksame.crm.erp.work.service.RelyService;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;

/**
 * @author Ivan
 * @date 2020/4/17 11:06
 * @Description 任务依赖控制层
 */
@Api(description="任务依赖接口管理")
public class RelyController extends Controller {

    @Inject
    private RelyService relyService;

    /**
     * 设置任务依赖
     */
    @ApiOperation(methods= RequestMethod.POST, description="设置任务依赖")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", description="任务ID"),
            @ApiImplicitParam(name="preTaskIds", description="依赖任务ID数组字符串(,号间隔)"),
            @ApiImplicitParam(name="relyFlag", description="标识(0前置任务 非0后置任务)")
    })
    public void setRely(){
        renderJson(relyService.setRely(getInt("taskId"),getPara("preTaskIds"),getInt("relyFlag")));
    }

    /**
     * 校验该任务前置任务是否已完成
     */
    @ApiOperation(methods= RequestMethod.POST, description="校验该任务前置任务是否已完成")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId", description="任务ID")
    })
    public void checkRely(){
        renderJson(relyService.checkRely(getInt("taskId")));
    }

}
