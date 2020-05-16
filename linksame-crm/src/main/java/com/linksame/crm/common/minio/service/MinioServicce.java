package com.linksame.crm.common.minio.service;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.jfinal.kit.LogKit;
import com.jfinal.upload.UploadFile;
import com.linksame.crm.common.constant.BaseConstant;
import com.linksame.crm.erp.admin.entity.AdminFile;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import org.xmlpull.v1.XmlPullParserException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author Ivan
 * @date 2020/4/6 12:59
 * @Description Minio  文件操作
 */
public class MinioServicce {

    public static MinioClient minioClient;

    //minio连接实例
    static {
        try {
            minioClient = new MinioClient(BaseConstant.MINIO_URL, BaseConstant.MINIO_KEY, BaseConstant.MINIO_KEY);
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断bucket是否存在
     * @param bucket    桶名
     * @return          true/false
     */
    public static boolean getBucketExists(String bucket) {
        boolean isExist = false;
        try {
            isExist = minioClient.bucketExists(bucket);
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
     * 判断资源对象是否存在
     * @param fileName  对象名称
     * @return          true/false
     */
    public static boolean getObjectExist(String fileName) {
        boolean isExist = false;
        try{
            isExist = minioClient.statObject(BaseConstant.BUCKET_NAME, fileName) != null;
            if(isExist){
                LogKit.info("对象" + fileName + "存在!");
            } else {
                LogKit.info("对象" + fileName + "不存在!");
            }
        } catch (Exception e) {
            LogKit.error("判断" + fileName + "是否存在出现异常!");
        }

        return isExist;
    }

    /**
     * 上传文件
     * @param file  文件
     * @return      AdminFile 桶名, 新文件名, 旧文件名
     */
    public static AdminFile uploadFile(UploadFile file) {
        //判断桶是否存在, 若不存在则进入异常, 打印日志信息
        getBucketExists(BaseConstant.BUCKET_NAME);
        //执行上传
        AdminFile adminFile = new AdminFile();
        FileInputStream bi = null;
        try {
            //计时开始
            long startTime = System.currentTimeMillis();

            bi = new FileInputStream(file.getFile());
            //重新命名文件名，采用uuid,避免文件名中带有‘-’导致解析出错
            String newFileName = IdUtil.simpleUUID() + StrUtil.DOT + FileUtil.extName(file.getOriginalFileName());
            //将文件上传到minio-oss服务器中
            minioClient.putObject(BaseConstant.BUCKET_NAME, newFileName, bi, file.getContentType());
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
     * 下载文件(下载到本地)
     * @param fileName  文件名
     * @param response  HttpServletResponse
     */
    public static void downloadFile(String fileName, HttpServletResponse response) {
        //判断对象是否存在, 若不存在进入异常, 记录日志
        getObjectExist(fileName);

        InputStream inputStream = null;
        try{
            inputStream = minioClient.getObject(BaseConstant.BUCKET_NAME, fileName);
            String[] fileSplit = fileName.split("/");
            String name = fileSplit[fileSplit.length-1];

            //以流形式返回
            response.setContentType("application/octet-stream; charset=UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(name, "UTF-8"));
            IoUtil.copy(inputStream, response.getOutputStream());
            LogKit.info("下载成功");
        } catch (Exception e) {
            LogKit.error("下载文件失败: ", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取图片文件请求地址
     * @param fileName    文件名
     * @param suffix      后缀名
     * @return            图片文件请求地址
     */
    public static String getImgUrl(String fileName, String suffix){
        //判断对象是否存在, 若不存在进入异常, 记录日志
        getObjectExist(fileName);
        //定义访问地址
        String urlString = "";
        //处理图片类型
        if(!suffix.equalsIgnoreCase("gif") && !suffix.equalsIgnoreCase("png")
                && !suffix.equalsIgnoreCase("jpg") && !suffix.equalsIgnoreCase("jpeg")) {
            throw new RuntimeException("只能处理gif/png/jpg/jpeg格式图片");
        }
        try{
            urlString = minioClient.presignedGetObject(BaseConstant.BUCKET_NAME, fileName);
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
        //判断对象是否存在, 若不存在进入异常, 记录日志
        getObjectExist(fileName);
        //删除
        minioClient.removeObject(BaseConstant.BUCKET_NAME, fileName);
    }

    /**
     * 批量删除文件
     * @param fileNameList  文件名list
     */
    public static void batchRemoveFile(List<String> fileNameList) {
        try {
            // 删除文件桶里的多个对象
            for (Result<DeleteError> errorResult: minioClient.removeObject(BaseConstant.BUCKET_NAME, fileNameList)) {
                DeleteError error = errorResult.get();
                LogKit.error("Failed to remove '" + error.objectName() + "'. Error:" + error.message());
            }
        } catch (Exception e) {
            LogKit.error("Error: " + e);
        }
    }

    /**  <<<<<------------------------------------断点续传/下载接口------------------------------------------->>>>>  **/


    /**
     * 删除一个未完整上传的对象
     * @param fileName      存储桶里的对象名称
     */
    public static void removeIncompleteUpload(String fileName){
        //判断对象是否存在, 若不存在进入异常, 记录日志
        getObjectExist(fileName);
        try {
            //从存储桶中删除名为myobject的未完整上传的对象。
            minioClient.removeIncompleteUpload(BaseConstant.BUCKET_NAME, fileName);
            LogKit.error("successfully removed all incomplete upload session of " + BaseConstant.BUCKET_NAME + "/" + fileName);
        } catch(Exception e) {
            LogKit.error("Error occurred: " + e);
        }
    }

    /**
     * 下载对象指定区域的字节数组做为流(断点下载)
     * @param offset
     * @param length
     * @return
     */
    public static InputStream getObject(String fileName, long offset, Long length){
        InputStream stream = null;
        try {
            // 获取指定offset和length的"myobject"的输入流。
            stream = minioClient.getObject(BaseConstant.BUCKET_NAME, fileName, offset, length);
            // 读取输入流直到EOF并打印到控制台。
            byte[] buf = new byte[16384];
            int bytesRead;
            while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
                System.out.println(new String(buf, 0, bytesRead));
            }
            // 关闭流。
            stream.close();
        } catch (Exception e) {
            System.out.println("Error occurred: " + e);
        }
        return stream;
    }


    /**
     * 通过InputStream上传对象(断点续传)
     * @param fileName
     */
    public static void putObject(String fileName, UploadFile file, long offset, Long length){
        try {
            InputStream stream = getObject(fileName, offset, length);
            System.out.println(stream.toString());
            FileInputStream bi = new FileInputStream(file.getFile());
            StringBuilder builder = new StringBuilder();
            builder.append(stream.toString());
            builder.append(bi.toString());
            ByteArrayInputStream bais = new ByteArrayInputStream(builder.toString().getBytes("UTF-8"));
            // 创建对象
            minioClient.putObject(BaseConstant.BUCKET_NAME, fileName, bais, bais.available(), "application/octet-stream");
            bi.close();
            bais.close();
            System.out.println("myobject is uploaded successfully");
        } catch(Exception e) {
            System.out.println("Error occurred: " + e);
        }
    }

}
