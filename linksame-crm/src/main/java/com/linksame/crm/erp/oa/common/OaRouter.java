package com.linksame.crm.erp.oa.common;

import com.jfinal.config.Routes;
import com.linksame.crm.erp.oa.controller.*;
import com.linksame.crm.erp.oa.controller.*;

public class OaRouter extends Routes {
    @Override
    public void config() {
        addInterceptor(new OaInterceptor());
        add("/OaEvent", OaEventController.class);
        add("/OaAnnouncement", OaAnnouncementController.class);
        add("/OaExamine", OaExamineController.class);
        add("/OaExamineCategory", OaExamineCategoryController.class);
        add("/OaLog", OaLogController.class);
        add("/OaRecord", OaActionRecordController.class);
        add("/comment", OaCommentController.class);
        add("/OaBackLog", OaBackLogController.class);
    }
}
