package com.linksame.crm.erp.admin.entity;

import com.linksame.crm.erp.admin.entity.base.BaseAdminSms;

/**
 * @author Ivan
 * @date 2020/4/23 13:21
 * @Description	短信
 */
@SuppressWarnings("serial")
public class AdminSms extends BaseAdminSms<AdminSms> {
	public static final AdminSms dao = new AdminSms().dao();
}
