package com.linksame.crm.erp.work.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

/**
 * @author Ivan
 * @date 2020/4/1 12:03
 * @Description 任务提醒表
 */
@SuppressWarnings("serial")
public abstract class BaseTaskRemind<M extends BaseTaskRemind<M>> extends Model<M> implements IBean {

	public void setRemindId(Integer remindId) {
		set("remind_id", remindId);
	}

	public Integer getRemindId() {
		return getInt("remind_id");
	}

	public void setRemindUserId(String remindUserId) {
		set("remind_user_id", remindUserId);
	}

	public String getRemindUserId() {
		return getStr("remind_user_id");
	}

	public void setRemindType(Integer remindType) {
		set("remind_type", remindType);
	}

	public Integer getRemindType() {
		return getInt("remind_type");
	}

	public void setRemindContent(String remindContent) {
		set("remind_content", remindContent);
	}

	public String getRemindContent() {
		return getStr("remind_content");
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public void setRemindTime(java.util.Date remindTime) {
		set("remind_time", remindTime);
	}

	public java.util.Date getRemindTime() {
		return get("remind_time");
	}

	public void setTaskId(Integer taskId) {
		set("task_id", taskId);
	}

	public Integer getTaskId() {
		return getInt("task_id");
	}

	public void setRemindStatus(Integer remindStatus) {
		set("remind_status", remindStatus);
	}

	public Integer getRemindStatus() {
		return getInt("remind_status");
	}

	public void setRemindIsstop(Integer remindIsstop) {
		set("remind_isstop", remindIsstop);
	}

	public Integer getRemindIsstop() {
		return getInt("remind_isstop");
	}

}
