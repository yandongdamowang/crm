package com.linksame.crm.common.minio.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * @author Ivan
 * @date 2020/4/6 15:43
 * @Description Minio拦截器
 */
public class MinioInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        invocation.invoke();
    }
}
