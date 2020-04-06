package com.linksame.crm.common.minio.controller;

import cn.hutool.core.io.FileUtil;
import com.jfinal.core.Controller;
import com.jfinal.kit.LogKit;
import com.jfinal.upload.UploadFile;
import com.linksame.crm.common.minio.service.MinioServicce;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @author Ivan
 * @date 2020/4/6 15:42
 * @Description minio接口测试
 */
public class MinioController extends Controller {

    /**
     * 上传文件
     * @throws FileNotFoundException
     */
    public void uploadFile() throws FileNotFoundException {
        UploadFile file = getFile("file");
        Map<String, String> map = MinioServicce.uploadFile(file);

        renderJson(map);
    }

    /**
     * 下载文件
     */
    public void downloadFile(){
        String fileName = get("fileName");
        HttpServletResponse response = getResponse();
        MinioServicce.downloadFile(fileName, response);
    }

    /**
     * 获取图片路径
     */
    public void getImgUrl() {
        String objectName = get("objectName");
        Integer outTime = getInt("outTime");
        HttpServletResponse response = getResponse();
        //获取文件后缀
        String suffix = FileUtil.extName(objectName);
        LogKit.info("文件后缀: " + suffix);
        String url = MinioServicce.getImgUrl(suffix, objectName, outTime, response);

        renderJson(url);
    }


}
