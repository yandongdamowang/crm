package com.linksame.crm.erp.crm.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCrmLeads<M extends BaseCrmLeads<M>> extends Model<M> implements IBean {

	public void setLeadsId(java.lang.Integer leadsId) {
		set("leads_id", leadsId);
	}
	
	public java.lang.Integer getLeadsId() {
		return getInt("leads_id");
	}

	public void setIsTransform(java.lang.Integer isTransform) {
		set("is_transform", isTransform);
	}
	
	public java.lang.Integer getIsTransform() {
		return getInt("is_transform");
	}

	public void setFollowup(java.lang.Integer followup) {
		set("followup", followup);
	}
	
	public java.lang.Integer getFollowup() {
		return getInt("followup");
	}

	public void setLeadsName(java.lang.String leadsName) {
		set("leads_name", leadsName);
	}
	
	public java.lang.String getLeadsName() {
		return getStr("leads_name");
	}

	public void setCustomerId(java.lang.Integer customerId) {
		set("customer_id", customerId);
	}
	
	public java.lang.Integer getCustomerId() {
		return getInt("customer_id");
	}

	public void setNextTime(java.util.Date nextTime) {
		set("next_time", nextTime);
	}
	
	public java.util.Date getNextTime() {
		return get("next_time");
	}

	public void setTelephone(java.lang.String telephone) {
		set("telephone", telephone);
	}
	
	public java.lang.String getTelephone() {
		return getStr("telephone");
	}

	public void setMobile(java.lang.String mobile) {
		set("mobile", mobile);
	}
	
	public java.lang.String getMobile() {
		return getStr("mobile");
	}

	public void setAddress(java.lang.String address) {
		set("address", address);
	}
	
	public java.lang.String getAddress() {
		return getStr("address");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

	public void setCreateUserId(java.lang.Long createUserId) {
		set("create_user_id", createUserId);
	}
	
	public java.lang.Long getCreateUserId() {
		return getLong("create_user_id");
	}

	public void setOwnerUserId(java.lang.Long ownerUserId) {
		set("owner_user_id", ownerUserId);
	}
	
	public java.lang.Long getOwnerUserId() {
		return getLong("owner_user_id");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

	public void setBatchId(java.lang.String batchId) {
		set("batch_id", batchId);
	}
	
	public java.lang.String getBatchId() {
		return getStr("batch_id");
	}

	public M setLastContent(java.lang.String lastContent) {
		set("last_content", lastContent);
		return (M)this;
	}

	public java.lang.String getLastContent() {
		return getStr("last_content");
	}

}
