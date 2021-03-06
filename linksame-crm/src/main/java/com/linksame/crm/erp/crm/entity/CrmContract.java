package com.linksame.crm.erp.crm.entity;

import com.linksame.crm.erp.crm.entity.base.BaseCrmContract;

/**
 * Generated by JFinal.
 */
@SuppressWarnings("serial")
public class CrmContract extends BaseCrmContract<CrmContract> {
	public static final CrmContract dao = new CrmContract().dao();
	//移出方式（1.移除2.转为团队成员）
	private Integer transferType;
	//权限（1.只读2.只写）
	private Integer power;
	//变更模块（1.联系人2.商机3.合同）
	private String ids;
	private Long newOwnerUserId;
	private String memberIds;
	private String contractIds;

	public Integer getTransferType() {
		return transferType;
	}

	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}

	public Long getNewOwnerUserId() {
		return newOwnerUserId;
	}

	public void setNewOwnerUserId(Long newOwnerUserId) {
		this.newOwnerUserId = newOwnerUserId;
	}

	public String getContractIds() {
		return contractIds;
	}

	public void setContractIds(String contractIds) {
		this.contractIds = contractIds;
	}
}
