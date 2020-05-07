package com.linksame.crm.erp.pmp.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

/**
 * @author ZhangJie
 * @ClassName BasePmpContractPayment
 * @date2020/3/28 18:38
 * @Description TODO
 **/
@SuppressWarnings("serial")
public abstract class BasePmpContractPayment<M extends BasePmpContractPayment<M>> extends Model<M> implements IBean {

    public void setBillId(Long billId) {
        set("bill_id", billId);
    }

    public Long getBillId() {
        return getLong("bill_id");
    }

    public void setContractId(Long contractId) {
        set("contract_id", contractId);
    }

    public Long getContractId() {
        return getLong("contract_id");
    }

    public void setProjectId(Long projectId) {
        set("project_id", projectId);
    }

    public Long getProjectId() {
        return getLong("project_id");
    }

    public void setPaymentName(String paymentName) {
        set("payment_name", paymentName);
    }

    public String getPaymentName() {
        return getStr("payment_name");
    }

    public void setPaymentClause(String paymentClause) {
        set("payment_clause", paymentClause);
    }

    public String getPaymentClause() {
        return getStr("payment_clause");
    }

    public void setCostPercentage(Long costPercentage) {
        set("cost_percentage", costPercentage);
    }

    public Long getCostPercentage() {
        return getLong("cost_percentage");
    }

    public void setMoney(java.math.BigDecimal money) {
        set("money", money);
    }

    public java.math.BigDecimal getMoney() {
        return get("money");
    }

    public void setAppliedAmount(java.math.BigDecimal appliedAmount) {
        set("applied_amount", appliedAmount);
    }

    public java.math.BigDecimal getAppliedAmount() {
        return get("applied_amount");
    }

    public void setPaymentStage(Integer paymentStage) {
        set("payment_stage", paymentStage);
    }

    public Integer getPaymentStage() {
        return getInt("payment_stage");
    }

    public void setPriority(String priority) {
        set("priority", priority);
    }

    public String getPriority() {
        return getStr("priority");
    }

    public void setPaymentNode(Date paymentNode) {
        set("payment_node", paymentNode);
    }

    public Date getPaymentNode() {
        return get("payment_node");
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

    public void setStatus(String status) {
        set("status", status);
    }

    public String getStatus() {
        return getStr("status");
    }

    public void setIsDeleted(String isDeleted) {
        set("is_deleted", isDeleted);
    }

    public String getIsDeleted() {
        return getStr("is_deleted");
    }

    public void setTradeForm(String tradeForm) {
        set("trade_form", tradeForm);
    }

    public String getTradeForm() {
        return getStr("trade_form");
    }

    public void setTradeStatus(String tradeStatus) {
        set("trade_status", tradeStatus);
    }

    public String getTradeStatus() {
        return getStr("trade_status");
    }

    public void setBatchId(String batchId) {
        set("batch_id", batchId);
    }

    public String getBatchId() {
        return getStr("batch_id");
    }

    public void setPracticalCostPercentage(Long practicalCostPercentage) {
        set("practical_cost_percentage", practicalCostPercentage);
    }

    public Long getPracticalCostPercentage() {
        return getLong("practical_cost_percentage");
    }

    public void setpracticalMoney(java.math.BigDecimal practicalMoney) {
        set("practical_money", practicalMoney);
    }

    public java.math.BigDecimal getPracticalMoney() {
        return get("practical_money");
    }

    public void setExamineRecordId(Integer examineRecordId) {
        set("examine_record_id", examineRecordId);
    }

    public Integer getExamineRecordId() {
        return getInt("examine_record_id");
    }

    public void setCheckStatus(Integer checkStatus) {
        set("check_status", checkStatus);
    }

    public Integer getCheckStatus() {
        return getInt("check_status");
    }

    public void setOwnerUserId(Long ownerUserId) {
        set("owner_user_id", ownerUserId);
    }

    public Long getOwnerUserId() {
        return getLong("owner_user_id");
    }

}
