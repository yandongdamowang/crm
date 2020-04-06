package com.linksame.crm.common.minio.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.jfinal.kit.LogKit;
import com.jfinal.upload.UploadFile;
import com.linksame.crm.common.constant.BaseConstant;
import com.linksame.crm.common.minio.factory.MinioFactory;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan
 * @date 2020/4/6 12:59
 * @Description Minio  文件操作
 */
public class MinioServicce {

    //判断bucket是否存在
    public static boolean getBucketExists(String bucket) throws Exception{
        boolean isExist = MinioFactory.getMinioClient().bucketExists(bucket);
        if(isExist){
            LogKit.info("Minio信息: 桶" + bucket + "存在!");
        } else {
            LogKit.info("Minio信息: 桶" + bucket + "不存在!");
        }
        return isExist;
    }

    /**
     * 上传文件
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    public static Map<String, String> uploadFile(UploadFile file) throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(file.getFile());
        //重新命名文件名，采用uuid,避免文件名中带有‘-’导致解析出错
        String newFileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFileName());
        Map<String, String> resultMap = new HashMap<>();
        //返回桶名、新文件名，旧文件名
        resultMap.put("bucketName", BaseConstant.BUCKET_NAME);
        resultMap.put("newfileName", newFileName);
        resultMap.put("oldfileName", file.getOriginalFileName());
        try {
            //将文件上传到minio-oss服务器中
            MinioFactory.getMinioClient().putObject(BaseConstant.BUCKET_NAME, newFileName, inputStream, file.getContentType());
        } catch (Exception e) {
            LogKit.info("上传文件失败");
        }
        return resultMap;
    }

    /**
     * 下载文件
     * @param fileName
     * @param response
     */
    public static void downloadFile(String fileName, HttpServletResponse response){
        try (InputStream inputStream = MinioFactory.getMinioClient().getObject(BaseConstant.BUCKET_NAME,fileName)) {
            String[] fileSplit = fileName.split("/");
            String name = fileSplit[fileSplit.length-1];

            //以流形式返回
            response.setContentType("application/octet-stream; charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(name, "UTF-8"));
            IoUtil.copy(inputStream, response.getOutputStream());
        } catch (Exception e) {
            LogKit.error("文件读取异常", e);
        }
    }

    /**
     * 获取图片文件请求地址
     * @param objectName
     * @param outTime
     * @param response
     * @return
     */
    public static String getImgUrl(String suffix, String objectName, Integer outTime, HttpServletResponse response){
        String urlString = "";
        //处理图片类型
        if(!suffix.equalsIgnoreCase("gif") && !suffix.equalsIgnoreCase("png")
                && !suffix.equalsIgnoreCase("jpg") && !suffix.equalsIgnoreCase("jpeg")) {
            throw new RuntimeException("只能处理gif/png/jpg/jpeg格式图片");
        }
        try{
            urlString = MinioFactory.getMinioClient().presignedGetObject(BaseConstant.BUCKET_NAME, objectName, outTime);
            LogKit.info("访问地址: " + urlString);
        }catch (Exception e){
            LogKit.error("获取图片url异常", e);
        }
        return urlString;
    }

    /**
     * 关闭资源
     * @param io
     */
    public static void close(Closeable... io) {
        for (Closeable closeable : io) {
            if(closeable!=null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
