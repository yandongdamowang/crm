package com.linksame.crm.erp.pmp.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.utils.R;

import java.util.List;

/**
 * @author ZhangJie
 * @ClassName PmpContractPaymentService
 * @date2020/3/28 18:47
 * @Description TODO
 **/
public class PmpContractPaymentService {

    public List<PmpContractPayment> findByContractId(Long contractId) {
        List<PmpContractPayment> pmpContractPayments = PmpContractPayment.dao.find(Db.getSql("pmp.contractPayment.queryByContractId"), contractId);
        return pmpContractPayments;
    }

    public R update(JSONObject object) {
        return null;
    }
}
