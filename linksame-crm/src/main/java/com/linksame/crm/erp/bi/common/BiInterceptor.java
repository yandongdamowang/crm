package com.linksame.crm.erp.bi.common;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class BiInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        inv.invoke();
    }
}
