package com.linksame.crm.erp.pmp.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.erp.pmp.entity.PmpContract;
import com.linksame.crm.erp.pmp.service.PmpContractService;

/**
 * @author ZhangJie
 * @ClassName PmpContractController
 * @date2020/3/28 12:50
 * @Description TODO
 **/
public class PmpContractController extends Controller {

    @Inject
    private PmpContractService pmpContractService;


    /**
     *
     * @param pmpContract 新增合同
     */
    public void add(@Para("") PmpContract pmpContract){
        renderJson(pmpContractService.add(pmpContract));
    }
    /**
     *
     * @param contractId 根据合同ID 查询详情
     */
    public void queryById(@Para("contractId")Long contractId){
        renderJson(pmpContractService.queryById(contractId));
    }
}
