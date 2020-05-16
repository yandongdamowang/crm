package com.linksame.crm.erp.work.entity;

import com.linksame.crm.erp.work.entity.base.BaseTaskRemind;

/**
 * @author Ivan
 * @date 2020/4/1 12:03
 * @Description 任务提醒表
 */
@SuppressWarnings("serial")
public class TaskRemind extends BaseTaskRemind<TaskRemind> {
	public static final TaskRemind dao = new TaskRemind().dao();
}
