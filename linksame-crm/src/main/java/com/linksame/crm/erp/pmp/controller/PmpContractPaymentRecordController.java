package com.linksame.crm.erp.pmp.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpContractPaymentRecord;
import com.linksame.crm.erp.pmp.service.PmpContractPaymentRecordService;

/**
 * @author ZhangJie
 * @ClassName PmpContractPaymentRecordController
 * @date2020/4/2 12:17
 * @Description 付款
 **/
public class PmpContractPaymentRecordController extends Controller {

    @Inject
    private PmpContractPaymentRecordService contractPaymentRecordService;


    /**
     *
     * @param basePageRequest 根据付款ID 查询详情
     */
    public void queryList(BasePageRequest<PmpContractPaymentRecord> basePageRequest){
        renderJson(contractPaymentRecordService.queryList(basePageRequest));
    }
}
