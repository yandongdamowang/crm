package com.linksame.crm.erp.pmp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.erp.pmp.service.PmpContractPaymentService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangJie
 * @ClassName PmpContractPaymentController
 * @date2020/3/28 18:46
 * @Description 预付款
 **/
public class PmpContractPaymentController extends Controller {
    @Inject
    private PmpContractPaymentService pmpContractPaymentService;


    /**
     * 更新账单
     */
    public void updates(){
        String data = getRawData();
        JSONObject jsonObject1 = JSON.parseObject(data);
        List bills = jsonObject1.getObject("pmpContractPayments", List.class);
        List<PmpContractPayment> pmpContractPayments = new ArrayList<>();
        for (int i = 0 ; i <bills.size() ; i++){
            pmpContractPayments.add(JSON.parseObject(bills.get(i).toString(),PmpContractPayment.class));
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
//    /**
//     * 调整支付金额
//     */
//    public void updatePaymentAmount (){
//        //yyyy-mm
//        String month = getPara("month");
//        System.out.println(month.length());
//        if (month.length() < 8){
//            month = month +"-01";
//        }
//        renderJson(pmpContractPaymentService.queryPaymentByMonthly(month));
//    }


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
    public void queryAdvanceBybillId(@Para("billId")Long billId){
        renderJson(pmpContractPaymentService.queryAdvanceBybillId(billId));
    }
    /**
     *
     *  设置优先级
     */
    public void setPriority(@Para("billId")Long billId, @Para("priority")String priority){
        renderJson(pmpContractPaymentService.setPriority(billId,priority));
    }
    /**
     *
     *  查询合同付款记录
     */
    public void queryPaymentDetail(@Para("contractId")Long contractId){
        renderJson(pmpContractPaymentService.queryPaymentDetail(contractId));
    }
}
