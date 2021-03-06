package com.linksame.crm.erp.pmp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpContract;
import com.linksame.crm.erp.pmp.entity.PmpReceivableRecords;
import com.linksame.crm.erp.pmp.service.PmpReceivableRecordsServer;

/**
 * 回款记录
 */
public class PmpReceivableRecordsController extends Controller {
    @Inject
    private PmpReceivableRecordsServer pmpReceivableRecordsServer;

    /**
     * 根据条件分页 或者 查询 列表
     * @param basePageRequest 返回分页或者列表
     */
    @Permissions("project:collectionRecord:index")
    public void queryList(BasePageRequest<PmpReceivableRecords> basePageRequest){
        renderJson(pmpReceivableRecordsServer.queryList(basePageRequest));
    }


    /**
     *
     * @param receivableRecordsId 根据ID 查询详情
     */
    @Permissions("project:collectionRecord:read")
    public void queryById(@Para("receivableRecordsId")Long receivableRecordsId){
        renderJson(pmpReceivableRecordsServer.queryById(receivableRecordsId));
    }

    /**
     *
     * @param contractId 根据合同ID 查询回款信息
     */
    @Permissions("project:contract:read")
    public void queryByContractId(@Para("contractId")Long contractId){
        renderJson(pmpReceivableRecordsServer.queryByContractId(contractId));
    }

    /**
     * 新增 或者 修改
     */
    @Permissions({"project:collectionRecord:save"})
    public void addOrUpdate(){
        PmpReceivableRecords pmpReceivableRecords = JSON.parseObject(getRawData(),PmpReceivableRecords.class);
        renderJson(pmpReceivableRecordsServer.addOrUpdate(pmpReceivableRecords));
    }

    /**
     * 根据ID 删除
     * @param receivableRecordsIds 多个ID 使用 ‘，’隔开
     */
    @Permissions("project:collectionRecord:delete")
    public void deleteByIds(@Para("receivableRecordsIds")String receivableRecordsIds){
        renderJson(pmpReceivableRecordsServer.deleteByIds(receivableRecordsIds));
    }
}
