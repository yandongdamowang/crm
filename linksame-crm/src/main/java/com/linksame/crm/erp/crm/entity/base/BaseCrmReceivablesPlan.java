package com.linksame.crm.erp.crm.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseCrmReceivablesPlan<M extends BaseCrmReceivablesPlan<M>> extends Model<M> implements IBean {

	public void setPlanId(java.lang.Integer planId) {
		set("plan_id", planId);
	}
	
	public java.lang.Integer getPlanId() {
		return getInt("plan_id");
	}

	public void setNum(java.lang.String num) {
		set("num", num);
	}
	
	public java.lang.String getNum() {
		return getStr("num");
	}

	public void setReceivablesId(java.lang.Integer receivablesId) {
		set("receivables_id", receivablesId);
	}
	
	public java.lang.Integer getReceivablesId() {
		return getInt("receivables_id");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public void setMoney(java.math.BigDecimal money) {
		set("money", money);
	}
	
	public java.math.BigDecimal getMoney() {
		return get("money");
	}

	public void setReturnDate(java.util.Date returnDate) {
		set("return_date", returnDate);
	}
	
	public java.util.Date getReturnDate() {
		return get("return_date");
	}

	public void setReturnType(java.lang.String returnType) {
		set("return_type", returnType);
	}
	
	public java.lang.String getReturnType() {
		return getStr("return_type");
	}

	public void setRemind(java.lang.Integer remind) {
		set("remind", remind);
	}
	
	public java.lang.Integer getRemind() {
		return getInt("remind");
	}

	public void setRemindDate(java.util.Date remindDate) {
		set("remind_date", remindDate);
	}
	
	public java.util.Date getRemindDate() {
		return get("remind_date");
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

	public void setFileBatch(java.lang.String fileBatch) {
		set("file_batch", fileBatch);
	}
	
	public java.lang.String getFileBatch() {
		return getStr("file_batch");
	}

	public void setContractId(java.lang.Integer contractId) {
		set("contract_id", contractId);
	}
	
	public java.lang.Integer getContractId() {
		return getInt("contract_id");
	}

	public void setCustomerId(java.lang.Integer customerId) {
		set("customer_id", customerId);
	}
	
	public java.lang.Integer getCustomerId() {
		return getInt("customer_id");
	}

}
