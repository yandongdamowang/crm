package com.linksame.crm.erp.admin.common;

import com.jfinal.config.Routes;
import com.linksame.crm.erp.admin.controller.*;
import com.linksame.crm.erp.admin.entity.AdminFileLabel;

public class AdminRouter extends Routes {
    @Override
    public void config() {
        addInterceptor(new AdminInterceptor());
        add("/", AdminLoginController.class);
        add("/system/user", AdminUserController.class);
        add("/system/dept", AdminDeptController.class);
        add("/system/menu", AdminMenuController.class);
        add("/system/role", AdminRoleController.class);
        add("/system/message", AdminMessageController.class);
        add("/file", AdminFileController.class);
        add("/type", AdminFileTypeController.class);
        add("/folder", AdminFileFolderController.class);
        add("/sms", AdminSmsController.class);
        add("/log", AdminFileLogController.class);
        add("/label", AdminFileLabelController.class);
        add("/field",AdminFieldController.class);
        add("/scene",AdminSceneController.class);
        add("/businessType", AdminBusinessTypeController.class);
        add("/achievement",AdminAchievementController.class);
        add("/sysConfig",AdminSysConfigController.class);
        add("/examine", AdminExamineController.class);
        add("/examineRecord", AdminExamineRecordController.class);
    }
}
