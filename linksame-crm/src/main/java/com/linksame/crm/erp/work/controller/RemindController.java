package com.linksame.crm.erp.work.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.erp.work.entity.TaskRemind;
import com.linksame.crm.erp.work.service.RemindService;

/**
 * @author Ivan
 * @date 2020/4/1 12:47
 * @Description 任务提醒控制层
 */
public class RemindController extends Controller {
    @Inject
    private RemindService remindService;

    /**
     * @author Ivan
     * 设置任务提醒
     * @param taskRemind 任务提醒对象
     */
    public void setRemind(@Para("") TaskRemind taskRemind){
        renderJson(remindService.setRemind(taskRemind));
    }

    /**
     * @author Ivan
     * 根据任务id查询任务提醒数据
     * @param taskId 任务编号
     */
    public void queryByTaskId(@Para("taskId")Integer taskId){
        renderJson(remindService.queryByTaskId(taskId));
    }
}
