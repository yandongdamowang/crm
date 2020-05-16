package com.linksame.crm.erp.pmp.common;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * @author ZhangJie
 * @ClassName PmpContractInterceptor
 * @date2020/3/28 12:47
 * @Description TODO
 **/
public class PmpInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        invocation.invoke();
    }
}
