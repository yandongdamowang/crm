package com.linksame.crm.erp.pmp.common;

import com.jfinal.config.Routes;
import com.linksame.crm.erp.pmp.controller.PmpAccessoryController;
import com.linksame.crm.erp.pmp.controller.PmpContractController;
import com.linksame.crm.erp.pmp.controller.PmpContractPaymentController;

/**
 * @author ZhangJie
 * @ClassName PmpContractRouter
 * @date2020/3/28 12:46
 * @Description TODO
 **/
public class PmpRouter extends Routes {
    @Override
    public void config() {
        addInterceptor(new PmpInterceptor());
        add("/PmpContract", PmpContractController.class);
        add("/PmpContractPayment", PmpContractPaymentController.class);
        add("/PmpAccessory", PmpAccessoryController.class);
    }
}
