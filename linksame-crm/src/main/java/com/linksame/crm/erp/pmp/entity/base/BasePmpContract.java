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

    public void setContractId(Long contractId) {
        set("contract_id", contractId);
    }

    public Long getContractId() {
        return getLong("contract_id");
    }
    public void setContractType(Long contractType) {
        set("contract_type", contractType);
    }

    public Long getContractType() {
        return getLong("contract_type");
    }

    public void setContractNumber(String contractIdNumber) {
        set("contract_number", contractIdNumber);
    }

    public String getContractNumber() {
        return getStr("contract_number");
    }

    public void setFileNumber(String fileNumber) {
        set("file_number", fileNumber);
    }

    public String getFileNumber() {
        return getStr("file_number");
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        set("purchase_order_number", purchaseOrderNumber);
    }

    public String getPurchaseOrderNumber() {
        return getStr("purchase_order_number");
    }

    public void setMilestoneNodes(Integer milestoneNodes) {
        set("milestone_nodes", milestoneNodes);
    }

    public Integer getMilestoneNodes() {
        return getInt("milestone_nodes");
    }

    public void setContractName(String contractName) {
        set("contract_name", contractName);
    }

    public String getContractName() {
        return getStr("contract_name");
    }

    public void setDrpNumber(String drpNumber) {
        set("drp_number", drpNumber);
    }

    public String getDrpNumber() {
        return getStr("drp_number");
    }

    public void setStatus(Integer status) {
        set("status", status);
    }

    public Integer getStatus() {
        return getInt("status");
    }

    public void setProjectId(Integer projectId) {
        set("project_id", projectId);
    }

    public Integer getProjectId() {
        return getInt("project_id");
    }

    public void setRequiredSigningTime(Date requiredSigningTime) {
        set("required_signing_time", requiredSigningTime);
    }

    public Date getRequiredSigningTime() {
        return get("required_signing_time");
    }

    public void setActualSigningTime(Date actualSigningTime) {
        set("actual_signing_time", actualSigningTime);
    }

    public Date getActualSigningTime() {
        return get("actual_signing_time");
    }

    public void setPurchaseTime(Date purchaseTime) {
        set("purchase_time", purchaseTime);
    }

    public Date getPurchaseTime() {
        return get("purchase_time");
    }

    public void setBeginTime(Date beginTime) {
        set("begin_time", beginTime);
    }

    public Date getBeginTime() {
        return get("begin_time");
    }

    public void setEndTime(Date endTime) {
        set("end_time", endTime);
    }

    public Date getEndTime() {
        return get("end_time");
    }

    public void setCreationTime(Date creationTime) {
        set("creation_time", creationTime);
    }

    public Date getCreationTime() {
        return get("creation_time");
    }

    public void setUpdateTime(Date updateTime) {
        set("update_time", updateTime);
    }

    public Date getUpdateTime() {
        return get("update_time");
    }

    public void setIsDeleted(String isDeleted) {
        set("is_deleted", isDeleted);
    }

    public String getIsDeleted() {
        return getStr("is_deleted");
    }

    public void setMoney(java.math.BigDecimal money) {
        set("money", money);
    }

    public java.math.BigDecimal getMoney() {
        return get("money");
    }

    public void setContractPurchaseStartTime(Date contractPurchaseStartTime) {
        set("contract_purchase_start_time", contractPurchaseStartTime);
    }

    public Date getContractPurchaseStartTime() {
        return get("contract_purchase_start_time");
    }

    public void setContractPurchaseEndTime(Date contractPurchaseEndTime) {
        set("contract_purchase_end_time", contractPurchaseEndTime);
    }

    public Date getContractPurchaseEndTime() {
        return get("contract_purchase_end_time");
    }

    public void setBatchId(String batchId) {
        set("batch_id", batchId);
    }

    public String getBatchId() {
        return getStr("batch_id");
    }

    public void setCheckStatus(Integer checkStatus) {
        set("check_status", checkStatus);
    }

    public Integer getCheckStatus() {
        return getInt("check_status");
    }

    public void setExamineRecordId(Integer examineRecordId) {
        set("examine_record_id", examineRecordId);
    }

    public Integer getExamineRecordId() {
        return getInt("examine_record_id");
    }
    public void setCreateUserId(Long createUserId) {
        set("create_user_id", createUserId);
    }

    public Long getCreateUserId() {
        return getLong("create_user_id");
    }

    public void setOwnerUserId(Long ownerUserId) {
        set("owner_user_id", ownerUserId);
    }

    public Long getOwnerUserId() {
        return getLong("owner_user_id");
    }


    public void setCustomerId(Integer customerId) {
        set("customer_id", customerId);
    }

    public Integer getCustomerId() {
        return getInt("customer_id");
    }


    public void setContactsId(Integer contactsId) {
        set("contacts_id", contactsId);
    }

    public Integer getContactsId() {
        return getInt("contacts_id");
    }


    public void setCompanyUserId(Integer companyUserId) {
        set("company_user_id", companyUserId);
    }

    public Integer getCompanyUserId() {
        return getInt("company_user_id");
    }

    public void setRoUserId(String roUserId) {
        set("ro_user_id", roUserId);
    }

    public String getRoUserId() {
        return getStr("ro_user_id");
    }

    public void setRwUserId(String rwUserId) {
        set("rw_user_id", rwUserId);
    }

    public String getRwUserId() {
        return getStr("rw_user_id");
    }

}