package com.linksame.crm.erp.admin.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * @author Ivan
 * @date 2020/4/23 13:21
 * @Description	短信
 */
@SuppressWarnings("serial")
public abstract class BaseAdminSms<M extends BaseAdminSms<M>> extends Model<M> implements IBean {

	public void setSmsId(Integer smsId) {
		set("sms_id", smsId);
	}

	public Integer getSmsId() {
		return getInt("sms_id");
	}

	public void setMobile(String mobile) {
		set("mobile", mobile);
	}

	public String getMobile() {
		return getStr("mobile");
	}

	public void setContent(String content) {
		set("content", content);
	}

	public String getContent() {
		return getStr("content");
	}

	public void setErrorcode(Integer errorcode) {
		set("errorcode", errorcode);
	}

	public Integer getErrorcode() {
		return getInt("errorcode");
	}

	public void setMessage(String message) {
		set("message", message);
	}

	public String getMessage() {
		return getStr("message");
	}

	public void setCode(Integer code) {
		set("code", code);
	}

	public Integer getCode() {
		return getInt("code");
	}

	public void setSendTime(Date sendTime) {
		set("send_time", sendTime);
	}

	public Date getSendTime() {
		return get("send_time");
	}

	public void setIsDel(Integer isDel) {
		set("is_del", isDel);
	}

	public Integer getIsDel() {
		return getInt("is_del");
	}

}
