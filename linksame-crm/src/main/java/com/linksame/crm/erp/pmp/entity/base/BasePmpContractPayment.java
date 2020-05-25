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

    public void setBillId(java.lang.Long billId) {
        set("bill_id", billId);
    }

    public java.lang.Long getBillId() {
        return getLong("bill_id");
    }

    public void setContractId(java.lang.Long contractId) {
        set("contract_id", contractId);
    }

    public java.lang.Long getContractId() {
        return getLong("contract_id");
    }

    public void setCustomerId(java.lang.Integer customerId) {
        set("customer_id", customerId);
    }

    public java.lang.Integer getCustomerId() {
        return getInt("customer_id");
    }

    public void setProjectId(java.lang.Integer projectId) {
        set("project_id", projectId);
    }

    public java.lang.Integer getProjectId() {
        return getInt("project_id");
    }

    public void setPaymentName(java.lang.String paymentName) {
        set("payment_name", paymentName);
    }

    public java.lang.String getPaymentName() {
        return getStr("payment_name");
    }

    public void setPaymentClause(java.lang.Integer paymentClause) {
        set("payment_clause", paymentClause);
    }

    public java.lang.Integer getPaymentClause() {
        return getInt("payment_clause");
    }

    public void setCostPercentage(java.lang.Integer costPercentage) {
        set("cost_percentage", costPercentage);
    }

    public java.lang.Integer getCostPercentage() {
        return getInt("cost_percentage");
    }

    public void setMoney(java.math.BigDecimal money) {
        set("money", money);
    }

    public java.math.BigDecimal getMoney() {
        return get("money");
    }

    public void setPaymentStage(java.lang.Integer paymentStage) {
        set("payment_stage", paymentStage);
    }

    public java.lang.Integer getPaymentStage() {
        return getInt("payment_stage");
    }

    public void setPriority(java.lang.String priority) {
        set("priority", priority);
    }

    public java.lang.String getPriority() {
        return getStr("priority");
    }

    public void setPaymentNode(java.util.Date paymentNode) {
        set("payment_node", paymentNode);
    }

    public java.util.Date getPaymentNode() {
        return get("payment_node");
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

    public void setStatus(java.lang.String status) {
        set("status", status);
    }

    public java.lang.String getStatus() {
        return getStr("status");
    }

    public void setIsDeleted(java.lang.String isDeleted) {
        set("is_deleted", isDeleted);
    }

    public java.lang.String getIsDeleted() {
        return getStr("is_deleted");
    }

    public void setTradeForm(java.lang.String tradeForm) {
        set("trade_form", tradeForm);
    }

    public java.lang.String getTradeForm() {
        return getStr("trade_form");
    }

    public void setTradeStatus(java.lang.String tradeStatus) {
        set("trade_status", tradeStatus);
    }

    public java.lang.String getTradeStatus() {
        return getStr("trade_status");
    }

    public void setBatchId(java.lang.String batchId) {
        set("batch_id", batchId);
    }

    public java.lang.String getBatchId() {
        return getStr("batch_id");
    }

    public void setPracticalCostPercentage(java.lang.Integer practicalCostPercentage) {
        set("practical_cost_percentage", practicalCostPercentage);
    }

    public java.lang.Integer getPracticalCostPercentage() {
        return getInt("practical_cost_percentage");
    }

    public void setPracticalMoney(java.math.BigDecimal practicalMoney) {
        set("practical_money", practicalMoney);
    }

    public java.math.BigDecimal getPracticalMoney() {
        return get("practical_money");
    }

    public void setExamineRecordId(java.lang.Integer examineRecordId) {
        set("examine_record_id", examineRecordId);
    }

    public java.lang.Integer getExamineRecordId() {
        return getInt("examine_record_id");
    }

    public void setCheckStatus(java.lang.Integer checkStatus) {
        set("check_status", checkStatus);
    }

    public java.lang.Integer getCheckStatus() {
        return getInt("check_status");
    }

    public void setOwnerUserId(java.lang.Long ownerUserId) {
        set("owner_user_id", ownerUserId);
    }

    public java.lang.Long getOwnerUserId() {
        return getLong("owner_user_id");
    }

    public void setAppliedAmount(java.math.BigDecimal appliedAmount) {
        set("applied_amount", appliedAmount);
    }

    public java.math.BigDecimal getAppliedAmount() {
        return get("applied_amount");
    }

    public void setRemark(java.lang.String remark) {
        set("remark", remark);
    }

    public java.lang.String getRemark() {
        return getStr("remark");
    }

    public void setApplicantCompany(java.lang.String applicantCompany) {
        set("applicant_company", applicantCompany);
    }

    public java.lang.String getApplicantCompany() {
        return getStr("applicant_company");
    }

    public void setCardinalNumberId(java.lang.Long cardinalNumberId) {
        set("cardinal_number_id", cardinalNumberId);
    }

    public java.lang.Long getCardinalNumberId() {
        return getLong("cardinal_number_id");
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
