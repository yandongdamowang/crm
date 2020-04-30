package com.linksame.crm.erp.admin.entity;


import com.linksame.crm.erp.admin.entity.base.BaseAdminFileLog;

/**
 * @author Ivan
 * @date 2020/4/20 10:47
 * @Description 附件类型
 */
@SuppressWarnings("serial")
public class AdminFileLog extends BaseAdminFileLog<AdminFileLog> {
    public static final AdminFileLog dao = new AdminFileLog().dao();
}
