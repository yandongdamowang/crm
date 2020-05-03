package com.linksame.crm.erp.pmp.entity;

import com.linksame.crm.erp.pmp.entity.base.BasePmpContract;

import java.util.List;

/**
 * @author ZhangJie
 * @ClassName PmpContract
 * @date2020/3/28 12:48
 * @Description TODO
 **/
@SuppressWarnings("serial")
public class PmpContract extends BasePmpContract<PmpContract> {
    public static final PmpContract dao = new PmpContract().dao();

    private List<PmpContractPayment> pmpContractPayments;
    private String companyUserName;//公司签约人
    private String customerName;//供应商/承包商ID/客户
    private String contactsName;//客户签约人

    public void setCompanyUserName(String companyUserName) {
        this.companyUserName = companyUserName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getCompanyUserName() {
        return companyUserName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getContactsName() {
        return contactsName;
    }

    public List<PmpContractPayment> getPmpContractPayment() {
        return pmpContractPayments;
    }

    public void setPmpContractPayment(List<PmpContractPayment> pmpContractPayments) {
        this.pmpContractPayments = pmpContractPayments;
    }
}
