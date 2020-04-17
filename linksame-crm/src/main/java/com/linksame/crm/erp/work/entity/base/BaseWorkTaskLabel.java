package com.linksame.crm.erp.work.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseWorkTaskLabel<M extends BaseWorkTaskLabel<M>> extends Model<M> implements IBean {

	public void setLabelId(java.lang.Integer labelId) {
		set("label_id", labelId);
	}
	
	public java.lang.Integer getLabelId() {
		return getInt("label_id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setCreateUserId(java.lang.Long createUserId) {
		set("create_user_id", createUserId);
	}
	
	public java.lang.Long getCreateUserId() {
		return getLong("create_user_id");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public void setColor(java.lang.String color) {
		set("color", color);
	}
	
	public java.lang.String getColor() {
		return getStr("color");
	}

	public void setIsCommon(java.lang.Integer isCommon) {
		set("is_common", isCommon);
	}

	public java.lang.Integer getIsCommon() {
		return getInt("is_common");
	}
}
