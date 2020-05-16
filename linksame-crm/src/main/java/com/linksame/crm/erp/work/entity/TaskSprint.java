package com.linksame.crm.erp.work.entity;


import com.linksame.crm.erp.work.entity.base.BaseTaskSprint;

/**
 * @author Ivan
 * @date 2020/5/7 15:49
 * @Description 任务冲刺表
 */
@SuppressWarnings("serial")
public class TaskSprint extends BaseTaskSprint<TaskSprint> {
	public static final TaskSprint dao = new TaskSprint().dao();
}
