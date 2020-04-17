package com.linksame.crm.erp.work.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * @author Ivan
 * @date 2020/4/17 10:32
 * @Description 任务依赖表
 */
@SuppressWarnings("serial")
public abstract class BaseTaskRely<M extends BaseTaskRely<M>> extends Model<M> implements IBean {

	public void setRelyId(Integer relyId) {
		set("rely_id", relyId);
	}

	public Integer getRelyId() {
		return getInt("rely_id");
	}

	public void setPreTaskId(Integer preTaskId) {
		set("pre_task_id", preTaskId);
	}

	public Integer getPreTaskId() {
		return getInt("pre_task_id");
	}

	public void setTaskId(Integer taskId) {
		set("task_id", taskId);
	}

	public Integer getTaskId() {
		return getInt("task_id");
	}

}
