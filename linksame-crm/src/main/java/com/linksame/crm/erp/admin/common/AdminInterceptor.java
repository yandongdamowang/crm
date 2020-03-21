package com.linksame.crm.erp.admin.common;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class AdminInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        invocation.invoke();
    }
}
