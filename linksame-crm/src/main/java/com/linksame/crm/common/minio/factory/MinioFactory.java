package com.linksame.crm.common.minio.factory;

import com.linksame.crm.common.constant.BaseConstant;
import io.minio.MinioClient;

/**
 * @author Ivan
 * @date 2020/4/6 13:09
 * @Description Minio 连接实例工具
 */
public class MinioFactory {

    public static MinioClient MINIO_CLIENT;

    //获取连接
    public static MinioClient getMinioClient() throws Exception{
        MINIO_CLIENT = new MinioClient(BaseConstant.MINIO_URL, BaseConstant.MINIO_KEY, BaseConstant.MINIO_KEY);
        return MINIO_CLIENT;
    }
}
