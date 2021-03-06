package com.linksame.crm.erp.pmp.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.util.Date;


/**
 * @author ZhangJie
 * @ClassName BasePmpContractPaymentRecord
 * @date2020/4/2 12:14
 * @Description TODO
 **/
@SuppressWarnings("serial")
public abstract class BasePmpContractPaymentRecord<M extends BasePmpContractPaymentRecord<M>> extends Model<M> implements IBean {

    public void setPaymentRecordId(java.lang.Long paymentRecordId) {
        set("payment_record_id", paymentRecordId);
    }

    public java.lang.Long getPaymentRecordId() {
        return getLong("payment_record_id");
    }

    public void setPaymentClause(java.lang.String paymentClause) {
        set("payment_clause", paymentClause);
    }

    public java.lang.String getPaymentClause() {
        return getStr("payment_clause");
    }

    public void setTotalMoney(java.math.BigDecimal totalMoney) {
        set("total_money", totalMoney);
    }

    public java.math.BigDecimal getTotalMoney() {
        return get("total_money");
    }

    public void setPaymentNumber(java.lang.String paymentNumber) {
        set("payment_number", paymentNumber);
    }

    public java.lang.String getPaymentNumber() {
        return getStr("payment_number");
    }

    public void setCustomerId(java.lang.Integer customerId) {
        set("customer_id", customerId);
    }

    public java.lang.Integer getCustomerId() {
        return getInt("customer_id");
    }

    public void setContractId(java.lang.Long contractId) {
        set("contract_id", contractId);
    }

    public java.lang.Long getContractId() {
        return getLong("contract_id");
    }

    public void setAdvanceRatio(java.lang.Integer advanceRatio) {
        set("advance_ratio", advanceRatio);
    }

    public java.lang.Integer getAdvanceRatio() {
        return getInt("advance_ratio");
    }

    public void setAmountAdvanced(java.math.BigDecimal amountAdvanced) {
        set("amount_advanced", amountAdvanced);
    }

    public java.math.BigDecimal getAmountAdvanced() {
        return get("amount_advanced");
    }

    public void setPracticalRatio(java.lang.Integer practicalRatio) {
        set("practical_ratio", practicalRatio);
    }

    public java.lang.Integer getPracticalRatio() {
        return getInt("practical_ratio");
    }

    public void setPracticaAdvanced(java.math.BigDecimal practicaAdvanced) {
        set("practica_advanced", practicaAdvanced);
    }

    public java.math.BigDecimal getPracticaAdvanced() {
        return get("practica_advanced");
    }

    public void setPaymentTime(Date paymentTime) {
        set("payment_time", paymentTime);
    }

    public Date getPaymentTime() {
        return get("payment_time");
    }

    public void setPaymentType(java.lang.String paymentType) {
        set("payment_type", paymentType);
    }

    public java.lang.String getPaymentType() {
        return getStr("payment_type");
    }

    public void setResponsiblePerson(java.lang.String responsiblePerson) {
        set("responsible_person", responsiblePerson);
    }

    public java.lang.String getResponsiblePerson() {
        return getStr("responsible_person");
    }

    public void setRemark(java.lang.String remark) {
        set("remark", remark);
    }

    public java.lang.String getRemark() {
        return getStr("remark");
    }

    public void setUpdateTime(Date updateTime) {
        set("update_time", updateTime);
    }

    public Date getUpdateTime() {
        return get("update_time");
    }

    public void setCreateTime(Date createTime) {
        set("create_time", createTime);
    }

    public Date getCreateTime() {
        return get("create_time");
    }

    public void setPlannedPaymentTime(Date plannedPaymentTime) {
        set("planned_payment_time", plannedPaymentTime);
    }

    public Date getPlannedPaymentTime() {
        return get("planned_payment_time");
    }

    public void setDeduction(java.math.BigDecimal deduction) {
        set("deduction", deduction);
    }

    public java.math.BigDecimal getDeduction() {
        return get("deduction");
    }

    public void setBatchId(java.lang.String batchId) {
        set("batch_id", batchId);
    }

    public java.lang.String getBatchId() {
        return getStr("batch_id");
    }

}
