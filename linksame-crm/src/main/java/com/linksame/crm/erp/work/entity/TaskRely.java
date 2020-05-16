package com.linksame.crm.erp.work.entity;

import com.linksame.crm.erp.work.entity.base.BaseTaskRely;

/**
 * @author Ivan
 * @date 2020/4/17 10:32
 * @Description 任务依赖表
 */
@SuppressWarnings("serial")
public class TaskRely extends BaseTaskRely<TaskRely> {
	public static final TaskRely dao = new TaskRely().dao();
}
