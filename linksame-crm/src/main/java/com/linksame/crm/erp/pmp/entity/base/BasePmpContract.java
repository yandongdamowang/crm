package com.linksame.crm.erp.pmp.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author ZhangJie
 * @ClassName BasePmpContract
 * @date2020/3/28 12:48
 * @Description TODO
 **/
@SuppressWarnings("serial")
public abstract class BasePmpContract<M extends BasePmpContract<M>> extends Model<M> implements IBean {

    public void setContractId(java.lang.Long contractId) {
        set("contract_id", contractId);
    }

    public java.lang.Long getContractId() {
        return getLong("contract_id");
    }

    public void setContractNumber(java.lang.String contractNumber) {
        set("contract_number", contractNumber);
    }

    public java.lang.String getContractNumber() {
        return getStr("contract_number");
    }

    public void setFileNumber(java.lang.String fileNumber) {
        set("file_number", fileNumber);
    }

    public java.lang.String getFileNumber() {
        return getStr("file_number");
    }

    public void setPurchaseOrderNumber(java.lang.String purchaseOrderNumber) {
        set("purchase_order_number", purchaseOrderNumber);
    }

    public java.lang.String getPurchaseOrderNumber() {
        return getStr("purchase_order_number");
    }

    public void setMilestoneNodes(java.lang.Integer milestoneNodes) {
        set("milestone_nodes", milestoneNodes);
    }

    public java.lang.Integer getMilestoneNodes() {
        return getInt("milestone_nodes");
    }

    public void setContractName(java.lang.String contractName) {
        set("contract_name", contractName);
    }

    public java.lang.String getContractName() {
        return getStr("contract_name");
    }

    public void setDrpNumber(java.lang.String drpNumber) {
        set("drp_number", drpNumber);
    }

    public java.lang.String getDrpNumber() {
        return getStr("drp_number");
    }

    public void setStatus(java.lang.Integer status) {
        set("status", status);
    }

    public java.lang.Integer getStatus() {
        return getInt("status");
    }

    public void setProjectId(java.lang.Integer projectId) {
        set("project_id", projectId);
    }

    public java.lang.Integer getProjectId() {
        return getInt("project_id");
    }

    public void setRequiredSigningTime(java.util.Date requiredSigningTime) {
        set("required_signing_time", requiredSigningTime);
    }

    public java.util.Date getRequiredSigningTime() {
        return get("required_signing_time");
    }

    public void setActualSigningTime(java.util.Date actualSigningTime) {
        set("actual_signing_time", actualSigningTime);
    }

    public java.util.Date getActualSigningTime() {
        return get("actual_signing_time");
    }

    public void setPurchaseTime(java.util.Date purchaseTime) {
        set("purchase_time", purchaseTime);
    }

    public java.util.Date getPurchaseTime() {
        return get("purchase_time");
    }

    public void setBeginTime(java.util.Date beginTime) {
        set("begin_time", beginTime);
    }

    public java.util.Date getBeginTime() {
        return get("begin_time");
    }

    public void setEndTime(java.util.Date endTime) {
        set("end_time", endTime);
    }

    public java.util.Date getEndTime() {
        return get("end_time");
    }

    public void setCreationTime(java.util.Date creationTime) {
        set("creation_time", creationTime);
    }

    public java.util.Date getCreationTime() {
        return get("creation_time");
    }

    public void setUpdateTime(java.util.Date updateTime) {
        set("update_time", updateTime);
    }

    public java.util.Date getUpdateTime() {
        return get("update_time");
    }

    public void setIsDeleted(java.lang.String isDeleted) {
        set("is_deleted", isDeleted);
    }

    public java.lang.String getIsDeleted() {
        return getStr("is_deleted");
    }

    public void setMoney(java.math.BigDecimal money) {
        set("money", money);
    }

    public java.math.BigDecimal getMoney() {
        return get("money");
    }

    public void setContractPurchaseStartTime(java.util.Date contractPurchaseStartTime) {
        set("contract_purchase_start_time", contractPurchaseStartTime);
    }

    public java.util.Date getContractPurchaseStartTime() {
        return get("contract_purchase_start_time");
    }

    public void setContractPurchaseEndTime(java.util.Date contractPurchaseEndTime) {
        set("contract_purchase_end_time", contractPurchaseEndTime);
    }

    public java.util.Date getContractPurchaseEndTime() {
        return get("contract_purchase_end_time");
    }

    public void setBatchId(java.lang.String batchId) {
        set("batch_id", batchId);
    }

    public java.lang.String getBatchId() {
        return getStr("batch_id");
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

    public void setCustomerId(java.lang.Integer customerId) {
        set("customer_id", customerId);
    }

    public java.lang.Integer getCustomerId() {
        return getInt("customer_id");
    }

    public void setContactsId(java.lang.Integer contactsId) {
        set("contacts_id", contactsId);
    }

    public java.lang.Integer getContactsId() {
        return getInt("contacts_id");
    }

    public void setCompanyUserId(java.lang.Integer companyUserId) {
        set("company_user_id", companyUserId);
    }

    public java.lang.Integer getCompanyUserId() {
        return getInt("company_user_id");
    }

    public void setProprietor(java.lang.String proprietor) {
        set("proprietor", proprietor);
    }

    public java.lang.String getProprietor() {
        return getStr("proprietor");
    }

    public void setContractType(java.lang.Long contractType) {
        set("contract_type", contractType);
    }

    public java.lang.Long getContractType() {
        return getLong("contract_type");
    }

}