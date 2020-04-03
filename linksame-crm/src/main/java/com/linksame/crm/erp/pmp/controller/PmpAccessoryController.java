package com.linksame.crm.erp.pmp.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.pmp.entity.PmpAccessory;
import com.linksame.crm.erp.pmp.service.PmpAccessoryService;

/**
 * @author ZhangJie
 * @ClassName PmpAccessoryController
 * @date2020/3/28 20:05
 * @Description 附件
 **/
public class PmpAccessoryController extends Controller {
    @Inject
    private PmpAccessoryService pmpAccessoryService;

    /**
     *
     * @param basePageRequest 根据附件ID 查询详情
     */
    public void queryList(BasePageRequest<PmpAccessory> basePageRequest){
        renderJson(pmpAccessoryService.queryList(basePageRequest));
    }
}
