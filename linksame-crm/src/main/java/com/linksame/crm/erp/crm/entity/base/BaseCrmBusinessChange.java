package com.linksame.crm.erp.crm.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCrmBusinessChange<M extends BaseCrmBusinessChange<M>> extends Model<M> implements IBean {

	public void setChangeId(Integer changeId) {
		set("change_id", changeId);
	}
	
	public Integer getChangeId() {
		return getInt("change_id");
	}

	public void setBusinessId(Integer businessId) {
		set("business_id", businessId);
	}
	
	public Integer getBusinessId() {
		return getInt("business_id");
	}

	public void setStatusId(Integer statusId) {
		set("status_id", statusId);
	}
	
	public Integer getStatusId() {
		return getInt("status_id");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setCreateUserId(Long createUserId) {
		set("create_user_id", createUserId);
	}
	
	public Long getCreateUserId() {
		return getLong("create_user_id");
	}

}
