package com.linksame.crm.erp.pmp.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.time.LocalDate;

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

    public void setContractNumber(java.lang.String contractIdNumber) {
        set("contract_number", contractIdNumber);
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

    public void setMilestoneNodes(java.lang.Long milestoneNodes) {
        set("milestone_nodes", milestoneNodes);
    }

    public java.lang.Long getMilestoneNodes() {
        return getLong("milestone_nodes");
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

    public void setSupplierId(java.lang.Long supplierId) {
        set("supplier_id", supplierId);
    }

    public java.lang.Long getSupplierId() {
        return getLong("supplier_id");
    }

    public void setProjectId(java.lang.Long projectId) {
        set("project_id", projectId);
    }

    public java.lang.Long getProjectId() {
        return getLong("project_id");
    }

    public void setRequiredSigningTime(java.time.LocalDate requiredSigningTime) {
        set("required_signing_time", requiredSigningTime);
    }

    public java.time.LocalDate getRequiredSigningTime() {
        return get("required_signing_time");
    }

    public void setActualSigningTime(java.time.LocalDate actualSigningTime) {
        set("actual_signing_time", actualSigningTime);
    }

    public java.time.LocalDate getActualSigningTime() {
        return get("actual_signing_time");
    }

    public void setPurchaseTime(java.time.LocalDate purchaseTime) {
        set("purchase_time", purchaseTime);
    }

    public java.time.LocalDate getPurchaseTime() {
        return get("purchase_time");
    }

    public void setBeginTime(java.time.LocalDate beginTime) {
        set("begin_time", beginTime);
    }

    public java.time.LocalDate getBeginTime() {
        return get("begin_time");
    }

    public void setEndTime(java.time.LocalDate endTime) {
        set("end_time", endTime);
    }

    public java.time.LocalDate getEndTime() {
        return get("end_time");
    }

    public void setAgent(java.lang.Long agent) {
        set("agent", agent);
    }

    public java.lang.Long getAgent() {
        return getLong("agent");
    }

    public void setCreationTime(java.time.LocalDateTime creationTime) {
        set("creation_time", creationTime);
    }

    public java.time.LocalDateTime getCreationTime() {
        return get("creation_time");
    }

    public void setUpdateTime(java.time.LocalDateTime updateTime) {
        set("update_time", updateTime);
    }

    public java.time.LocalDateTime getUpdateTime() {
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

    public void setContractPurchaseStartTime(LocalDate contractPurchaseStartTime) {
        set("contract_purchase_start_time", contractPurchaseStartTime);
    }

    public LocalDate getContractPurchaseStartTime() {
        return get("contract_purchase_start_time");
    }

    public void setContractPurchaseEndTime(LocalDate contractPurchaseEndTime) {
        set("contract_purchase_end_time", contractPurchaseEndTime);
    }

    public LocalDate getContractPurchaseEndTime() {
        return get("contract_purchase_end_time");
    }

    public void setBatchId(java.lang.String batchId) {
        set("batch_id", batchId);
    }

    public java.lang.String getBatchId() {
        return getStr("batch_id");
    }

    public void setCheckStatus(java.lang.Integer checkStatus) {
        set("check_status", checkStatus);
    }

    public java.lang.Integer getCheckStatus() {
        return getInt("check_status");
    }

    public void setExamineRecordId(java.lang.Integer examineRecordId) {
        set("examine_record_id", examineRecordId);
    }

    public java.lang.Integer getExamineRecordId() {
        return getInt("examine_record_id");
    }

}