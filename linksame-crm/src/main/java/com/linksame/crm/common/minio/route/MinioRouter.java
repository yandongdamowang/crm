package com.linksame.crm.common.minio.route;

import com.jfinal.config.Routes;
import com.linksame.crm.common.minio.controller.MinioController;
import com.linksame.crm.common.minio.interceptor.MinioInterceptor;

/**
 * @author Ivan
 * @date 2020/4/6 15:42
 * @Description minio路由
 */
public class MinioRouter extends Routes {
    @Override
    public void config() {
        addInterceptor(new MinioInterceptor());

        add("/minio", MinioController.class);
    }
}
