package com.linksame.crm.erp.admin.entity;

import com.linksame.crm.erp.admin.entity.base.BaseAdminFileFolder;

/**
 * @author Ivan
 * @date 2020/4/17 12:34
 * @Description 附件文件夹
 */
@SuppressWarnings("serial")
public class AdminFileFolder extends BaseAdminFileFolder<AdminFileFolder> {
	public static final AdminFileFolder dao = new AdminFileFolder().dao();
}
