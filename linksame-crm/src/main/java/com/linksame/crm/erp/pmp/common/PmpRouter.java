package com.linksame.crm.erp.pmp.common;

import com.jfinal.config.Routes;
import com.linksame.crm.erp.pmp.controller.PmpAccessoryController;
import com.linksame.crm.erp.pmp.controller.PmpContractController;
import com.linksame.crm.erp.pmp.controller.PmpContractPaymentController;
import com.linksame.crm.erp.pmp.controller.PmpContractPaymentRecordController;

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
        add("/pmpContract", PmpContractController.class);
        add("/pmpContractPayment", PmpContractPaymentController.class);
        add("/pmpAccessory", PmpAccessoryController.class);
        add("/pmpContractPaymentRecord", PmpContractPaymentRecordController.class);
    }
}
