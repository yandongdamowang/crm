package com.linksame.crm.erp.admin.controller;

import com.jfinal.plugin.activerecord.Db;
import com.linksame.crm.common.constant.BaseConstant;
import com.linksame.crm.erp.admin.entity.AdminMenu;
import com.linksame.crm.erp.admin.service.AdminMenuService;
import com.linksame.crm.utils.R;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;

public class AdminMenuController extends Controller {
    @Inject
    private AdminMenuService adminMenuService;


    /**
     * @author wyq
     * 展示全部菜单
     */
    public void getAllMenuList(){
        renderJson(R.ok().put("data",adminMenuService.getAllMenuList(0,BaseConstant.AUTH_DATA_RECURSION_NUM)));
    }

    /**
     * 通过角色菜单查询菜单
     * @param roleType 角色类型
     */
    public void getMenuListByType(@Para("roleType") Integer roleType){
        renderJson(adminMenuService.getMenuListByType(roleType));
    }

    /**
     * @author hmb
     * 展示全部菜单
     */
    public void getWorkMenuList(){
        Integer workMenuId = Db.queryInt("select menu_id from `admin_menu` where parent_id = 0 and realm = 'work'");
        AdminMenu root = new AdminMenu().findById(workMenuId);
        root.put("childMenu",adminMenuService.getAllMenuList(root.getMenuId(),BaseConstant.AUTH_DATA_RECURSION_NUM));
        renderJson(R.ok().put("data",root));
    }
}
