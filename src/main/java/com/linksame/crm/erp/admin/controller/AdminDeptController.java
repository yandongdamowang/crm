package com.linksame.crm.erp.admin.controller;

import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.erp.admin.entity.AdminDept;
import com.linksame.crm.erp.admin.service.AdminDeptService;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;

/**
 * @author hmb
 */
public class AdminDeptController extends Controller {

    @Inject
    private AdminDeptService adminDeptService;

    /**
     * @author hmb
     * 设置部门
     * @param adminDept 部门对象
     */
    @Permissions("manage:users:deptSave")
    public void addDept(@Para("") AdminDept adminDept){
        renderJson(adminDeptService.setDept(adminDept));
    }

    @Permissions("manage:users:deptUpdate")
    public void updateDept(@Para("") AdminDept adminDept){
        renderJson(adminDeptService.setDept(adminDept));
    }

    /**
     * @author hmb
     * 查询部门tree列表
     */
    public void queryDeptTree(){
        String type = getPara("type");
        Integer id = getParaToInt("id");
        renderJson(R.ok().put("data",adminDeptService.queryDeptTree(type,id)));
    }

    /**
     * @author zhangzhiwie
     * 查询权限内部门
     */
    public void queryDeptByAuth(){
        renderJson(R.ok().put("data",adminDeptService.queryDeptByAuth(BaseUtil.getUser().getUserId())));
    }

    /**
     * @author hmb
     * 删除部门
     */
    @Permissions("manage:users:deptDelete")
    public void deleteDept(){
        String id = getPara("id");
        renderJson(adminDeptService.deleteDept(id));
    }
}
