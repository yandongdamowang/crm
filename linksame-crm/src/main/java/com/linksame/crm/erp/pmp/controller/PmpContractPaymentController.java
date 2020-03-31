package com.linksame.crm.erp.pmp.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.linksame.crm.erp.pmp.service.PmpContractPaymentService;

/**
 * @author ZhangJie
 * @ClassName PmpContractPaymentController
 * @date2020/3/28 18:46
 * @Description TODO
 **/
public class PmpContractPaymentController extends Controller {
    @Inject
    private PmpContractPaymentService pmpContractPaymentService;
}
