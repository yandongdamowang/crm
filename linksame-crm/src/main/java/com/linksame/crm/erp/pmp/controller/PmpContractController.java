package com.linksame.crm.erp.pmp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpContract;
import com.linksame.crm.erp.pmp.service.PmpContractService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
     * 合同仪表盘
     */
    public void contractDashboard(){
        String startTime = getPara("startTime");
        String endTime = getPara("endTime");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        renderJson(pmpContractService.contractDashboard(LocalDate.parse(startTime,dateTimeFormatter),LocalDate.parse(endTime,dateTimeFormatter)));
    }

    /**
     *
     * 更新线索合同
     */
    public void update(){
        JSONObject object= JSON.parseObject(getRawData());
        renderJson(pmpContractService.update(object));
    }
    /**
     *
     * @param contractId 根据合同ID 查询详情
     */
    public void queryById(@Para("contractId")Long contractId){
        renderJson(pmpContractService.queryById(contractId));
    }
    /**
     *
     * @param basePageRequest 根据合同ID 查询详情
     */
    public void queryList(BasePageRequest<PmpContract> basePageRequest){
        renderJson(pmpContractService.queryList(basePageRequest));
    }
}
