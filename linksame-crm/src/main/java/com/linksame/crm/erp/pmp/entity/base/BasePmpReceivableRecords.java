package com.linksame.crm.erp.pmp.entity.base;


import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;

import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("serial")
public abstract class BasePmpReceivableRecords<M extends BasePmpReceivableRecords<M>> extends Model<M> implements IBean {

    public void setReceivableRecordsId(java.lang.Long receivableRecordsId) {
        set("receivable_records_id", receivableRecordsId);
    }

    public java.lang.Long getReceivableRecordsId() {
        return getLong("receivable_records_id");
    }

    public void setContractId(java.lang.Long contractId) {
        set("contract_id", contractId);
    }

    public java.lang.Long getContractId() {
        return getLong("contract_id");
    }

    public void setReceivableType(java.lang.String receivableType) {
        set("receivable_type", receivableType);
    }

    public java.lang.String getReceivableType() {
        return getStr("receivable_type");
    }

    public void setUpdateTime(Date updateTime) {
        set("update_time", updateTime);
    }

    public Date getUpdateTime() {
        return get("update_time");
    }

    public void setCreatedTime(Date createdTime) {
        set("created_time", createdTime);
    }

    public Date getCreatedTime() {
        return get("created_time");
    }

    public void setCustomerId(java.lang.Long customerId) {
        set("customer_id", customerId);
    }

    public java.lang.Long getCustomerId() {
        return getLong("customer_id");
    }

    public void setReceivableNumber(java.lang.String receivableNumber) {
        set("receivable_number", receivableNumber);
    }

    public java.lang.String getReceivableNumber() {
        return getStr("receivable_number");
    }

    public void setReceivableAmount(java.math.BigDecimal receivableAmount) {
        set("receivable_amount", receivableAmount);
    }

    public java.math.BigDecimal getReceivableAmount() {
        return get("receivable_amount");
    }

    public void setRemark(java.lang.String remark) {
        set("remark", remark);
    }

    public java.lang.String getRemark() {
        return getStr("remark");
    }

    public void setState(java.lang.String state) {
        set("state", state);
    }

    public java.lang.String getState() {
        return getStr("state");
    }

    public void setPaymentMethod(java.lang.String paymentMethod) {
        set("payment_method", paymentMethod);
    }

    public java.lang.String getPaymentMethod() {
        return getStr("payment_method");
    }

    public void setCollectingCompany(java.lang.String collectingCompany) {
        set("collecting_company", collectingCompany);
    }

    public java.lang.String getCollectingCompany() {
        return getStr("collecting_company");
    }

    public void setCollectionBank(java.lang.String collectionBank) {
        set("collection_bank", collectionBank);
    }

    public java.lang.String getCollectionBank() {
        return getStr("collection_bank");
    }

    public void setCollectionAccount(java.lang.String collectionAccount) {
        set("collection_account", collectionAccount);
    }

    public java.lang.String getCollectionAccount() {
        return getStr("collection_account");
    }



    public void setCollectionNumber(java.lang.String collectionNumber) {
        set("collection_number", collectionNumber);
    }

    public java.lang.String getCollectionNumber() {
        return getStr("collection_number");
    }

    public void setCollectingTime(Date collectingTime) {
        set("collecting_time", collectingTime);
    }

    public Date getCollectingTime() {
        return get("collecting_time");
    }

    public void setAgent(java.lang.Integer agent) {
        set("agent", agent);
    }

    public java.lang.Integer getAgent() {
        return getInt("agent");
    }

    public void setBatchId(java.lang.String batchId) {
        set("batch_id", batchId);
    }

    public java.lang.String getBatchId() {
        return getStr("batch_id");
    }

    public void setStatus(java.lang.Integer status) {
        set("status", status);
    }

    public java.lang.Integer getStatus() {
        return getInt("status");
    }

}
