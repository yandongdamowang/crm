package com.linksame.crm.erp.pmp.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
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
        renderJson(pmpContractPaymentService.updatePaymentBill(pmpContractPayments));

    }
    /**
     * 月份查询账单
     */
    public void queryPaymentByMonthly(){
        //yyyy-mm
        String month = getPara("month");
        System.out.println(month.length());
        if (month.length() < 8){
            month = month +"-01";
        }
        renderJson(pmpContractPaymentService.queryPaymentByMonthly(month));
    }
    /**
     * 调整支付金额
     */
    public void updatePaymentAmount (){
        //yyyy-mm
        String month = getPara("month");
        System.out.println(month.length());
        if (month.length() < 8){
            month = month +"-01";
        }
        renderJson(pmpContractPaymentService.queryPaymentByMonthly(month));
    }


    /**
     *
     * @param basePageRequest 预付款列表
     */
    public void queryAdvanceList(BasePageRequest<PmpContractPayment> basePageRequest){
        renderJson(pmpContractPaymentService.queryAdvanceList(basePageRequest));
    }
    /**
     *
     *  根据ID查询预付款详情
     */
    public void queryAdvanceBybillId(){
        String billId = getPara("billId");
        renderJson(pmpContractPaymentService.queryAdvanceBybillId(billId));
    }
}
