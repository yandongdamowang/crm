package com.linksame.crm.common.minio.controller;

import cn.hutool.core.io.FileUtil;
import com.jfinal.core.Controller;
import com.jfinal.kit.LogKit;
import com.jfinal.upload.UploadFile;
import com.linksame.crm.common.minio.service.MinioServicce;
import com.linksame.crm.erp.admin.entity.AdminFile;
import com.linksame.crm.utils.R;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
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
        AdminFile adminFile = MinioServicce.uploadFile(file);

        renderJson(R.ok().put("data", adminFile));
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



    /**
     * 断点续传测试
     */
    public void uploadFileByHttp(){
        String fileName = getPara("fileName");
        UploadFile file = getFile("file");
        //接着上传
        MinioServicce.putObject(fileName, file,10L, 40L);

        renderNull();
    }
}
