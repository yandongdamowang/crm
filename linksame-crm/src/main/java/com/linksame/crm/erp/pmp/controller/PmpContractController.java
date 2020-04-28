package com.linksame.crm.erp.pmp.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpContract;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.erp.pmp.service.PmpContractService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
     *  新增合同
     */
    @Permissions("pmp:contract:save")
    public void add(){
        String data = getRawData();
        PmpContract pmpContract = JSON.parseObject(data,PmpContract.class);
        JSONObject jsonObject1 = JSON.parseObject(data);
        List<PmpContractPayment> pmpContractPayments = new ArrayList<>();
        List pmpContractPayments1 = jsonObject1.getObject("pmpContractPayments", List.class);
        for (Object o : pmpContractPayments1) {
            pmpContractPayments.add(JSON.parseObject(o.toString(),PmpContractPayment.class));
        }
        pmpContract.setPmpContractPayment(pmpContractPayments);
        renderJson(pmpContractService.add(pmpContract));
    }

    /**
     *
     *  查询审批记录
     */
    @Permissions("pmp:contract:read")
    public void contractApprove(){
        Integer examineRecordId = getInt("examineRecordId");

        renderJson(pmpContractService.contractApprove(examineRecordId));
    }
    /**
     *
     * @param contractIds 合同IDs
     */
    @Permissions("pmp:contract:delete")
    public void delete(@Para("contractIds")String contractIds){
        renderJson(pmpContractService.delete(contractIds));
    }

    /**
     *
     * 合同仪表盘
     */
    @Permissions("pmp:contract:dashBoard")
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
    @Permissions("pmp:contract:update")
    public void update(){
        JSONObject object= JSON.parseObject(getRawData());
        renderJson(pmpContractService.update(object));
    }
    /**
     *
     * @param contractId 根据合同ID 查询详情
     */
    @Permissions("pmp:contract:read")
    public void queryById(@Para("contractId")Long contractId){
        renderJson(pmpContractService.queryById(contractId));
    }
    /**
     *
     * @param basePageRequest 根据合同ID 查询详情
     */
    @Permissions("pmp:contract:index")
    public void queryList(BasePageRequest<PmpContract> basePageRequest){
        renderJson(pmpContractService.queryList(basePageRequest));
    }
}
