package com.linksame.crm.erp.pmp.entity;

import com.jfinal.plugin.activerecord.Model;
import com.linksame.crm.erp.pmp.entity.base.BasePmpContractPaymentRecord;

/**
 * @author ZhangJie
 * @ClassName PmpContractPaymentRecord
 * @date2020/4/2 12:14
 * @Description TODO
 **/
@SuppressWarnings("serial")
public class PmpContractPaymentRecord extends BasePmpContractPaymentRecord<PmpContractPaymentRecord> {
    public static final PmpContractPaymentRecord dao = new PmpContractPaymentRecord().dao();
}
