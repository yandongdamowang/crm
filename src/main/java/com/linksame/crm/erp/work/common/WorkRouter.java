package com.linksame.crm.erp.work.common;

import com.linksame.crm.erp.work.controller.*;
import com.jfinal.config.Routes;
import com.linksame.crm.erp.work.controller.*;

public class WorkRouter extends Routes {
    @Override
    public void config() {
        addInterceptor(new WorkInterceptor());
        add("/work", WorkController.class);
        add("/task", TaskController.class);
        add("/taskLabel", LabelController.class);
        add("/workbench", WorkbenchController.class);
        add("/workTrash", TrashController.class);
    }

}
