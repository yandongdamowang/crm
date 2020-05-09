package com.linksame.crm.common.minio.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.jfinal.kit.LogKit;
import com.jfinal.upload.UploadFile;
import com.linksame.crm.common.constant.BaseConstant;
import com.linksame.crm.common.minio.factory.MinioFactory;
import com.linksame.crm.erp.admin.entity.AdminFile;

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


    /**
     * 判断bucket是否存在
     * @param bucket    桶名
     * @return          true/false
     */
    public static boolean getBucketExists(String bucket) {
        boolean isExist = false;
        try {
            isExist = MinioFactory.getMinioClient().bucketExists(bucket);
            if(isExist){
                LogKit.info("Minio信息: 桶" + bucket + "存在!");
            } else {
                LogKit.info("Minio信息: 桶" + bucket + "不存在!");
            }
        } catch (Exception e) {
            LogKit.error("判断bucket是否存在出现异常!");
        }
        return isExist;
    }

    /**
     * 上传文件
     * @param file  文件
     * @return      Map<String, String> 桶名, 新文件名, 旧文件名
     */
    public static AdminFile uploadFile(UploadFile file) {
        AdminFile adminFile = new AdminFile();
        FileInputStream bi = null;
        try {
            //计时开始
            long startTime = System.currentTimeMillis();
            bi = new FileInputStream(file.getFile());

            //重新命名文件名，采用uuid,避免文件名中带有‘-’导致解析出错
            String newFileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFileName());
            //将文件上传到minio-oss服务器中
            MinioFactory.getMinioClient().putObject(BaseConstant.BUCKET_NAME, newFileName, bi, file.getContentType());
            //返回桶名、新文件名，旧文件名
            adminFile.setBucketName(BaseConstant.BUCKET_NAME);
            adminFile.setFileName(newFileName);
            adminFile.setOldName(file.getOriginalFileName());

            //计时结束
            long endTime = System.currentTimeMillis();

            LogKit.info("上传成功,文件名: " + newFileName + ", 执行时间：" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            LogKit.error("上传文件失败!");
        } finally {
            if (bi != null) {
                try {
                    bi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return adminFile;
    }

    /**
     * 下载文件
     * @param fileName  文件名
     * @param response  HttpServletResponse
     */
    public static void downloadFile(String fileName, HttpServletResponse response) {
        try{
            InputStream inputStream = MinioFactory.getMinioClient().getObject(BaseConstant.BUCKET_NAME,fileName);
            String[] fileSplit = fileName.split("/");
            String name = fileSplit[fileSplit.length-1];

            //以流形式返回
            response.setContentType("application/octet-stream; charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            IoUtil.copy(inputStream, response.getOutputStream());
            LogKit.info("下载成功");
        } catch (Exception e) {
            LogKit.error("下载文件失败: ", e);
        }
    }

    /**
     * 获取图片文件请求地址
     * @param fileName    文件名
     * @param suffix      后缀名
     * @return            图片文件请求地址
     */
    public static String getImgUrl(String fileName, String suffix){
        String urlString = "";

        //处理图片类型
        if(!suffix.equalsIgnoreCase("gif") && !suffix.equalsIgnoreCase("png")
                && !suffix.equalsIgnoreCase("jpg") && !suffix.equalsIgnoreCase("jpeg")) {
            throw new RuntimeException("只能处理gif/png/jpg/jpeg格式图片");
        }
        try{
            urlString = MinioFactory.getMinioClient().presignedGetObject(BaseConstant.BUCKET_NAME, fileName);
            LogKit.info("访问地址: " + urlString);
        }catch (Exception e){
            LogKit.error("获取图片url异常: ", e);
        }
        return urlString;
    }

    /**
     * 删除文件
     * @param fileName  文件名
     */
    public static void removeFile(String fileName) throws Exception {
        MinioFactory.getMinioClient().removeObject(BaseConstant.BUCKET_NAME, fileName);
    }


}
