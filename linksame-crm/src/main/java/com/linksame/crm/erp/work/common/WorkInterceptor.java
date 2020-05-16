package com.linksame.crm.erp.work.common;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class WorkInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        inv.invoke();
    }
}
