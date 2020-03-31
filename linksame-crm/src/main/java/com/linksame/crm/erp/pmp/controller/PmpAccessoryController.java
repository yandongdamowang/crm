package com.linksame.crm.erp.pmp.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.linksame.crm.erp.pmp.service.PmpAccessoryService;

/**
 * @author ZhangJie
 * @ClassName PmpAccessoryController
 * @date2020/3/28 20:05
 * @Description TODO
 **/
public class PmpAccessoryController extends Controller {
    @Inject
    private PmpAccessoryService pmpAccessoryService;
}
