package com.linksame.crm.erp.pmp.entity.base;

import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

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

    public void setProjectId(java.lang.Long projectId) {
        set("project_id", projectId);
    }

    public java.lang.Long getProjectId() {
        return getLong("project_id");
    }

    public void setPaymentName(java.lang.String paymentName) {
        set("payment_name", paymentName);
    }

    public java.lang.String getPaymentName() {
        return getStr("payment_name");
    }

    public void setPaymentClause(java.lang.String paymentClause) {
        set("payment_clause", paymentClause);
    }

    public java.lang.String getPaymentClause() {
        return getStr("payment_clause");
    }

    public void setCostPercentage(java.lang.String costPercentage) {
        set("cost_percentage", costPercentage);
    }

    public java.lang.String getCostPercentage() {
        return getStr("cost_percentage");
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

}
