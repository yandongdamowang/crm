package com.linksame.crm.erp.admin.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAdminDept<M extends BaseAdminDept<M>> extends Model<M> implements IBean {

	public void setDeptId(java.lang.Integer deptId) {
		set("dept_id", deptId);
	}
	
	public java.lang.Integer getDeptId() {
		return getInt("dept_id");
	}

	public void setPid(java.lang.Integer pid) {
		set("pid", pid);
	}
	
	public java.lang.Integer getPid() {
		return getInt("pid");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public void setNum(java.lang.Integer num) {
		set("num", num);
	}
	
	public java.lang.Integer getNum() {
		return getInt("num");
	}

	public void setRemark(java.lang.String remark) {
		set("remark", remark);
	}
	
	public java.lang.String getRemark() {
		return getStr("remark");
	}

}
