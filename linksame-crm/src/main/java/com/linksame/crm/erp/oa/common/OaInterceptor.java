package com.linksame.crm.erp.oa.common;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class OaInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        inv.invoke();
    }
}
