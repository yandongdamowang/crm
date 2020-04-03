package com.linksame.crm.erp.pmp.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.erp.pmp.service.PmpContractPaymentService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangJie
 * @ClassName PmpContractPaymentController
 * @date2020/3/28 18:46
 * @Description TODO
 **/
public class PmpContractPaymentController extends Controller {
    @Inject
    private PmpContractPaymentService pmpContractPaymentService;


    /**
     * 更新账单
     */
    public void update(){
        String[] billIds = getParaValues("billId");
        List<PmpContractPayment> pmpContractPayments = new ArrayList<>();
        for (int i = 0 ; i <billIds.length ; i++){
            PmpContractPayment model = getModel(PmpContractPayment.class, "PmpContractPayment[" + i + "]");
            pmpContractPayments.add(model);
        }
    }
}
