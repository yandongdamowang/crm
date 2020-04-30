package com.linksame.crm.erp.pmp.controller;

import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpContractPaymentRecord;
import com.linksame.crm.erp.pmp.service.PmpContractPaymentRecordService;

/**
 * @author ZhangJie
 * @ClassName PmpContractPaymentRecordController
 * @date2020/4/2 12:17
 * @Description 付款记录
 **/
public class PmpContractPaymentRecordController extends Controller {

    @Inject
    private PmpContractPaymentRecordService contractPaymentRecordService;


    /**
     *
     * @param basePageRequest 付款记录分页
     */
    @Permissions("project:paymentRecord:index")
    public void queryList(BasePageRequest<PmpContractPaymentRecord> basePageRequest){
        renderJson(contractPaymentRecordService.queryList(basePageRequest));
    }
    /**
     *
     * @param paymentRecordId 根据付款记录ID 查询详情
     */
    @Permissions("project:paymentRecord:read")
    public void queryById(@Para("paymentRecordId")Long paymentRecordId){
        renderJson(contractPaymentRecordService.queryById(paymentRecordId));
    }
    /**
     *
     *  付款记录报表
     */
    @Permissions("project:paymentRecord:dashBoard")
    public void paymentReport(){
        renderJson(contractPaymentRecordService.paymentReport(JSON.parseObject(getRawData())));
    }
}
