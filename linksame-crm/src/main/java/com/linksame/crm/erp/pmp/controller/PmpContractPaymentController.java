package com.linksame.crm.erp.pmp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.admin.service.AdminExamineRecordService;
import com.linksame.crm.erp.crm.common.CrmEnum;
import com.linksame.crm.erp.crm.service.CrmRecordService;
import com.linksame.crm.erp.pmp.entity.PmpContract;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.erp.pmp.service.PmpContractPaymentService;
import com.linksame.crm.utils.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangJie
 * @ClassName PmpContractPaymentController
 * @date2020/3/28 18:46
 * @Description 预付款
 **/
public class PmpContractPaymentController extends Controller {
    @Inject
    private PmpContractPaymentService pmpContractPaymentService;

    @Inject
    private AdminExamineRecordService examineRecordService;

    @Inject
    private CrmRecordService crmRecordService;
    /**
     * 更新账单
     */
    @Permissions("project:advance:update")
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
     * 更新账单
     */
    @Permissions("project:advance:update")
    public void update(){
        String data = getRawData();
        JSONObject jsonObject1 = JSON.parseObject(data);
        PmpContractPayment bills = jsonObject1.getObject("pmpContractPayment",PmpContractPayment.class);
        renderJson(pmpContractPaymentService.update(bills));

    }



    /**
     * 付款审批
     */
    public void requestPayment(){
        String data = getRawData();
        Long checkUserId = getLong("checkUserId");//添加或者审核人
        JSONObject jsonObject1 = JSON.parseObject(data);
        PmpContractPayment pmpContractPayment = jsonObject1.getObject("pmpContractPayment",PmpContractPayment.class);
        Map<String, Integer> map = examineRecordService.saveExamineRecord(2, checkUserId, pmpContractPayment.getOwnerUserId(), null, null);
        if (map.get("status") == 0) {
            renderJson(R.error("没有启动的审核步骤，不能添加！"));
        } else {
            pmpContractPayment.setExamineRecordId(map.get("id"));
        }
        if (pmpContractPayment.getCheckStatus() != null && pmpContractPayment.getCheckStatus() == 5) {
            pmpContractPayment.setCheckStatus(5);
        } else {
            pmpContractPayment.setCheckStatus(0);
        }
        crmRecordService.updateRecord(new PmpContractPayment().dao().findById(pmpContractPayment.getContractId()), pmpContractPayment, CrmEnum.PMP_PAYMENT);

        pmpContractPayment.setUpdateTime(new Date(System.currentTimeMillis()));
        boolean update = pmpContractPayment.update();
        renderJson();

    }
    /**
     * 月份查询账单
     */
    @Permissions("project:advance:index")
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
     *
     * @param basePageRequest 预付款列表
     */
    @Permissions("project:advance:index")
    public void queryAdvanceList(BasePageRequest<PmpContractPayment> basePageRequest){
        renderJson(pmpContractPaymentService.queryAdvanceList(basePageRequest));
    }
    /**
     *
     *  根据ID查询预付款详情
     */
    @Permissions("project:advance:read")
    public void queryAdvanceBybillId(@Para("billId")Long billId){
        renderJson(pmpContractPaymentService.queryAdvanceBybillId(billId));
    }
    /**
     *
     *  设置优先级
     */

    @Permissions("project:advance:priority")
    public void setPriority(@Para("billId")Long billId, @Para("priority")String priority){
        renderJson(pmpContractPaymentService.setPriority(billId,priority));
    }
    /**
     *
     *  查询合同付款记录
     */
    @Permissions("project:advance:index")
    public void queryPaymentDetail(@Para("contractId")Long contractId){
        renderJson(pmpContractPaymentService.queryPaymentDetail(contractId));
    }
    /**
     *
     *  付款记录报表
     */
    @Permissions("project:advance:dashBoard")
    public void paymentReport(){
        String rawData = getRawData();
        JSONObject jsonObject = JSON.parseObject(getRawData());
        renderJson(pmpContractPaymentService.paymentReport(jsonObject));
    }
}
