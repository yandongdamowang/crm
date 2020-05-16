package com.linksame.crm.erp.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.admin.entity.AdminExamine;
import com.linksame.crm.erp.admin.service.AdminExamineService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;

/**
 * 审批流程
 * @author zxy
 */
public class AdminExamineController extends Controller {
    @Inject
    private AdminExamineService examineService;
    /**
     * 添加审批流程
     */
    @Permissions("manage:examineFlow:update")
    public void saveExamine(){
        JSONObject jsonObject = JSON.parseObject(getRawData());
        renderJson(examineService.saveExamine(jsonObject));
    }
    /**
     * 查询所有未删除审批流程
     */
    @Permissions("manage:examineFlow:update")
    public void queryAllExamine(BasePageRequest<AdminExamine> basePageRequest){
        renderJson(examineService.queryAllExamine(basePageRequest));
    }
    /**
     * 根据id查询审批流程 examineId 审批流程id
     * @author zxy
     */
    public void queryExamineById(){
        Integer examineId = getInt("examineId");
        renderJson(examineService.queryExamineById(examineId));
    }
    /**
     * 停用或删除审批流程
     * examineId 审批流程id
     * status 审批状态 1启用 0禁用 2 删除
     */
    @Permissions("manage:examineFlow:update")
    public void updateStatus(@Para("") AdminExamine adminExamine){
        renderJson(examineService.updateStatus(adminExamine));
    }
    /**
     * 查询当前启用审核流程步骤
     * categoryType 1 合同 2 回款
     */
    public void queryExaminStep(){
        Integer categoryType = getInt("categoryType");
        Integer id = getInt("id");
        renderJson(examineService.queryExaminStep(categoryType,id));
    }
}
