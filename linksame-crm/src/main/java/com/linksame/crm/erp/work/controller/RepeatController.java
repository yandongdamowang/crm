package com.linksame.crm.erp.work.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.annotation.NotBlank;
import com.linksame.crm.erp.work.entity.TaskRepeat;
import com.linksame.crm.erp.work.service.TaskRepeatService;

/**
 * @author Ivan
 * @date 2020/5/20 11:34
 * @Description 重复任务
 */
public class RepeatController extends Controller {

    @Inject
    private TaskRepeatService taskRepeatService;

    /**
     * 设置重复任务
     * @param taskRepeat
     */
    @NotBlank({"taskId", "repeatType"})
    public void setRepeat(@Para("") TaskRepeat taskRepeat){
        renderJson(taskRepeatService.setRepeat(taskRepeat, getInt("taskId")));
    }

}
