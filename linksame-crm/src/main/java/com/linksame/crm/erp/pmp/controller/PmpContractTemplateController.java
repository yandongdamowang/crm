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
import com.linksame.crm.erp.pmp.entity.PmpContract;
import com.linksame.crm.erp.pmp.entity.PmpContractPayment;
import com.linksame.crm.erp.pmp.entity.PmpContractTemplate;
import com.linksame.crm.erp.pmp.service.PmpContractService;
import com.linksame.crm.erp.pmp.service.PmpContractTemplateService;

import java.util.ArrayList;
import java.util.List;

public class PmpContractTemplateController extends Controller {

    @Inject
    private PmpContractTemplateService pmpContractTemplateService;
    /**
     *
     * @param basePageRequest 分页 或者列表查询
     */
    public void queryList(BasePageRequest<PmpContractTemplate> basePageRequest){
        renderJson(pmpContractTemplateService.queryList(basePageRequest));
    }

    /**
     *
     *  新增模板
     */
    @Permissions("project:contractTemplate:save")
    public void addOrUpdate(){
        String data = getRawData();
        JSONObject jsonObject = JSON.parseObject(data);
        PmpContractTemplate pmpContractTemplate = jsonObject.getObject("pmpContractTemplate",PmpContractTemplate.class);

        renderJson(pmpContractTemplateService.addOrUpdate(pmpContractTemplate));
    }


    /**
     *
     * @param templateIds 模板IDs
     */
    @Permissions("project:contractTemplate:delete")
    public void delete(@Para("contractType")String contractType){
        renderJson(pmpContractTemplateService.delete(contractType));
    }

}
