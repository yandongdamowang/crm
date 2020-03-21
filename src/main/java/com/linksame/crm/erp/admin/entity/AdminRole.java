package com.linksame.crm.erp.admin.entity;

import com.linksame.crm.erp.admin.entity.base.BaseAdminRole;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class AdminRole extends BaseAdminRole<AdminRole> {
	public static final AdminRole dao = new AdminRole().dao();

	private String menuIds;

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

}
