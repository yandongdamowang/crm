package com.linksame.crm.erp.pmp.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.oa.entity.OaExamine;
import com.linksame.crm.erp.oa.entity.OaExamineRelation;
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
    @Permissions("project:contract:save")
    public void add(){
        String data = getRawData();
        JSONObject jsonObject = JSON.parseObject(data);
        PmpContract pmpContract = jsonObject.getObject("pmpContract",PmpContract.class);
        boolean notEmpty = StrUtil.isNotEmpty(pmpContract.getBatchId());
        pmpContract.setBatchId(notEmpty ? pmpContract.getBatchId() : IdUtil.simpleUUID());
        List<PmpContractPayment> pmpContractPayments = new ArrayList<>();
        JSONArray pmpContractPayments2 = jsonObject.getJSONArray("pmpContractPayments");
        for (Object o : pmpContractPayments2) {
            PmpContractPayment pmpContractPayment = JSON.parseObject(o.toString(), PmpContractPayment.class);
            pmpContractPayment.setBatchId(StrUtil.isNotEmpty(pmpContractPayment.getBatchId()) ? pmpContractPayment.getBatchId() : IdUtil.simpleUUID());
            pmpContractPayments.add(pmpContractPayment);
        }
        renderJson(pmpContractService.add(pmpContract,pmpContractPayments));
    }

    /**
     *
     *  查询审批记录
     */
    @Permissions("project:contract:read")
    public void contractApprove(){
        Integer examineRecordId = getInt("examineRecordId");

        renderJson(pmpContractService.contractApprove(examineRecordId));
    }
    /**
     *
     * @param contractIds 合同IDs
     */
    @Permissions("project:contract:delete")
    public void delete(@Para("contractIds")String contractIds){
        renderJson(pmpContractService.delete(contractIds));
    }

    /**
     *
     * 合同仪表盘
     */
    @Permissions("project:contract:dashBoard")
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
    @Permissions("project:contract:update")
    public void update(){
        JSONObject object= JSON.parseObject(getRawData());
        renderJson(pmpContractService.update(object));
    }
    /**
     *
     * @param contractId 根据合同ID 查询详情
     */
    @Permissions("project:contract:read")
    public void queryById(@Para("contractId")Long contractId){
        renderJson(pmpContractService.queryById(contractId));
    }
    /**
     *
     * @param basePageRequest 根据合同ID 查询详情
     */
    @Permissions("project:contract:index")
    public void queryList(BasePageRequest<PmpContract> basePageRequest){
        renderJson(pmpContractService.queryList(basePageRequest));
    }
}
