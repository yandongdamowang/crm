package com.linksame.crm.erp.work.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.linksame.crm.common.annotation.NotBlank;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.work.entity.TaskSprint;
import com.linksame.crm.erp.work.service.SprintService;
import com.linksame.crm.utils.R;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;

import java.util.List;

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

    /**
     * 根据各状态查询数量
     */
    public void qeuryCount(){
        List<Record> countList = Db.find("select count(*) as taskCount from task_sprint where is_del = 0 GROUP BY status ORDER BY status");
        if(CollectionUtil.isEmpty(countList)){
            renderJson(R.error("未查询到数据"));
        }

        renderJson(R.ok().put("data", countList));
    }

    /**
     * 查询任务冲刺数据详情(根据各状态组装list返回页面)
     */
    @NotBlank({"sprintId"})
    public void querySprintInfo(){
        renderJson(sprintService.querySprintInfo(getInt("sprintId")));
    }

}
