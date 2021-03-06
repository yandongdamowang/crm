package com.linksame.crm.erp.crm.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCrmContacts<M extends BaseCrmContacts<M>> extends Model<M> implements IBean {

	public void setContactsId(java.lang.Integer contactsId) {
		set("contacts_id", contactsId);
	}
	
	public java.lang.Integer getContactsId() {
		return getInt("contacts_id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public void setNextTime(java.util.Date nextTime) {
		set("next_time", nextTime);
	}
	
	public java.util.Date getNextTime() {
		return get("next_time");
	}

	public void setMobile(java.lang.String mobile) {
		set("mobile", mobile);
	}
	
	public java.lang.String getMobile() {
		return getStr("mobile");
	}

	public void setTelephone(java.lang.String telephone) {
		set("telephone", telephone);
	}
	
	public java.lang.String getTelephone() {
		return getStr("telephone");
	}

	public void setEmail(java.lang.String email) {
		set("email", email);
	}
	
	public java.lang.String getEmail() {
		return getStr("email");
	}

	public void setPost(java.lang.String post) {
		set("post", post);
	}
	
	public java.lang.String getPost() {
		return getStr("post");
	}

	public void setCustomerId(java.lang.Integer customerId) {
		set("customer_id", customerId);
	}
	
	public java.lang.Integer getCustomerId() {
		return getInt("customer_id");
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

}
