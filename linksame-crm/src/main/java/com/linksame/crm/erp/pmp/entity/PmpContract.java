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
    private String supplierName;
    private String agentName;
    private List<PmpAccessory> pmpAccessories;

    public List<PmpAccessory> getPmpAccessories() {
        return pmpAccessories;
    }

    public void setPmpAccessories(List<PmpAccessory> pmpAccessories) {
        this.pmpAccessories = pmpAccessories;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<PmpContractPayment> getPmpContractPayment() {
        return pmpContractPayments;
    }

    public void setPmpContractPayment(List<PmpContractPayment> pmpContractPayments) {
        this.pmpContractPayments = pmpContractPayments;
    }
}
