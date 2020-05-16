package com.linksame.crm.erp.work.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * @author Ivan
 * @date 2020/5/7 15:49
 * @Description 任务冲刺表
 */
@SuppressWarnings("serial")
public abstract class BaseTaskSprint<M extends BaseTaskSprint<M>> extends Model<M> implements IBean {

	public void setSprintId(Integer sprintId) {
		set("sprint_id", sprintId);
	}

	public Integer getSprintId() {
		return getInt("sprint_id");
	}

	public void setName(String name) {
		set("name", name);
	}

	public String getName() {
		return getStr("name");
	}

	public void setAimsName(String aimsName) {
		set("aims_name", aimsName);
	}

	public String getAimsName() {
		return getStr("aims_name");
	}

	public void setCreateUserId(Long createUserId) {
		set("create_user_id", createUserId);
	}

	public Long getCreateUserId() {
		return getLong("create_user_id");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setStartTime(java.util.Date startTime) {
		set("start_time", startTime);
	}

	public java.util.Date getStartTime() {
		return get("start_time");
	}

	public void setEndTime(java.util.Date endTime) {
		set("end_time", endTime);
	}

	public java.util.Date getEndTime() {
		return get("end_time");
	}

	public void setStatus(Integer status) {
		set("status", status);
	}

	public Integer getStatus() {
		return getInt("status");
	}

	public void setIsDel(Integer isDel) {
		set("is_del", isDel);
	}

	public Integer getIsDel() {
		return getInt("is_del");
	}
}
