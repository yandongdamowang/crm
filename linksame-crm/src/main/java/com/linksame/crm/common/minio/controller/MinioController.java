package com.linksame.crm.common.minio.controller;

import cn.hutool.core.io.FileUtil;
import com.jfinal.core.Controller;
import com.jfinal.kit.LogKit;
import com.jfinal.upload.UploadFile;
import com.linksame.crm.common.minio.service.MinioServicce;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Ivan
 * @date 2020/4/6 15:42
 * @Description minio接口测试
 */
@Api(description="Minio文件上传服务")
public class MinioController extends Controller {

    /**
     * 上传文件
     */
    @ApiOperation(methods= RequestMethod.POST, description="上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="file", description="文件")
    })
    public void uploadFile() {
        UploadFile file = getFile("file");
        Map<String, String> map = MinioServicce.uploadFile(file);

        renderJson(map);
    }

    /**
     * 下载文件
     */
    @ApiOperation(methods= RequestMethod.POST, description="下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileName", description="文件名")
    })
    public void downloadFile() {
        String fileName = get("fileName");
        HttpServletResponse response = getResponse();
        MinioServicce.downloadFile(fileName, response);

        renderNull();
    }

    /**
     * 获取图片路径
     */
    @ApiOperation(methods= RequestMethod.POST, description="获取图片路径")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileName", description="文件名")
    })
    public void getImgUrl() {
        String fileName = get("fileName");
        //获取文件后缀
        String suffix = FileUtil.extName(fileName);
        LogKit.info("文件后缀: " + suffix);
        String url = MinioServicce.getImgUrl(suffix, fileName);

        renderJson(url.trim());
    }


}
