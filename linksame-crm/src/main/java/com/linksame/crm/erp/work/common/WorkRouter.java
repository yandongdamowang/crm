package com.linksame.crm.erp.work.common;

import com.linksame.crm.erp.work.controller.*;
import com.jfinal.config.Routes;

public class WorkRouter extends Routes {
    @Override
    public void config() {
        addInterceptor(new WorkInterceptor());
        add("/work", WorkController.class);
        add("/task", TaskController.class);
        add("/taskLabel", LabelController.class);
        add("/workbench", WorkbenchController.class);
        add("/workTrash", TrashController.class);
        add("/taskRemind", RemindController.class);
        add("/taskRely", RelyController.class);
        add("/taskSprint", SprintController.class);
    }
}
