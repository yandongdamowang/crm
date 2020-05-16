package com.linksame.crm.erp.pmp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpBusiness;
import com.linksame.crm.erp.pmp.entity.PmpContractTemplate;
import com.linksame.crm.erp.pmp.service.PmpBusinessService;

public class PmpBusinessController extends Controller {

    @Inject
    private PmpBusinessService pmpContractBusinessService;

    /**
     *
     *  新增关联类型
     */
    public void addOrUpdate(){
        String data = getRawData();
        JSONObject jsonObject = JSON.parseObject(data);
        PmpBusiness pmpBusiness = jsonObject.getObject("pmpContractBusiness", PmpBusiness.class);
        renderJson(pmpContractBusinessService.addOrUpdate(pmpBusiness));

    }
    /**
     *
     * @param businessIds 业务IDs
     */
    public void delete(@Para("businessIds")String businessIds){
        renderJson(pmpContractBusinessService.delete(businessIds));
    }

    /**
     *
     * @param basePageRequest 分页 或者列表查询
     */
    @Permissions("project:contractTemplate:index")
    public void queryList(BasePageRequest<PmpBusiness> basePageRequest){
        renderJson(pmpContractBusinessService.queryList(basePageRequest));
    }


}
