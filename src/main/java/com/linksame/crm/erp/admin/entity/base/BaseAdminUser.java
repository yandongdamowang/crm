package com.linksame.crm.erp.admin.entity.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAdminUser<M extends BaseAdminUser<M>> extends Model<M> implements IBean {

	public void setUserId(java.lang.Long userId) {
		set("user_id", userId);
	}

	public java.lang.Long getUserId() {
		return getLong("user_id");
	}

	public void setUsername(java.lang.String username) {
		set("username", username);
	}

	public java.lang.String getUsername() {
		return getStr("username");
	}

	public void setPassword(java.lang.String password) {
		set("password", password);
	}

	public java.lang.String getPassword() {
		return getStr("password");
	}

	public void setSalt(java.lang.String salt) {
		set("salt", salt);
	}

	public java.lang.String getSalt() {
		return getStr("salt");
	}

	public void setImg(java.lang.String img) {
		set("img", img);
	}

	public java.lang.String getImg() {
		return getStr("img");
	}

	public void setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public void setRealname(java.lang.String realname) {
		set("realname", realname);
	}

	public java.lang.String getRealname() {
		return getStr("realname");
	}

	public void setNum(java.lang.String num) {
		set("num", num);
	}

	public java.lang.String getNum() {
		return getStr("num");
	}

	public void setMobile(java.lang.String mobile) {
		set("mobile", mobile);
	}

	public java.lang.String getMobile() {
		return getStr("mobile");
	}

	public void setEmail(java.lang.String email) {
		set("email", email);
	}

	public java.lang.String getEmail() {
		return getStr("email");
	}

	public void setSex(java.lang.Integer sex) {
		set("sex", sex);
	}

	public java.lang.Integer getSex() {
		return getInt("sex");
	}

	public void setDeptId(java.lang.Integer deptId) {
		set("dept_id", deptId);
	}

	public java.lang.Integer getDeptId() {
		return getInt("dept_id");
	}

	public void setPost(java.lang.String post) {
		set("post", post);
	}

	public java.lang.String getPost() {
		return getStr("post");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}

	public java.lang.Integer getStatus() {
		return getInt("status");
	}

	public void setParentId(java.lang.Long parentId) {
		set("parent_id", parentId);
	}

	public java.lang.Long getParentId() {
		return getLong("parent_id");
	}

	public void setLastLoginTime(java.util.Date lastLoginTime) {
		set("last_login_time", lastLoginTime);
	}

	public java.util.Date getLastLoginTime() {
		return get("last_login_time");
	}

	public void setLastLoginIp(java.lang.String lastLoginIp) {
		set("last_login_ip", lastLoginIp);
	}

	public java.lang.String getLastLoginIp() {
		return getStr("last_login_ip");
	}

}
