package com.linksame.crm.erp.admin.controller;

import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.minio.service.MinioServicce;
import com.linksame.crm.erp.admin.entity.AdminFile;
import com.linksame.crm.erp.admin.service.AdminFileService;
import com.linksame.crm.utils.R;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;

@Api(description="附件接口管理")
public class AdminFileController extends Controller {

    @Inject
    private AdminFileService adminFileService;

    /*public void index(){
        renderJson(R.ok());
    }*/

    /**
     * 上传附件
     */
    @ApiOperation(methods= RequestMethod.POST, description="附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name="file", description="附件"),
            @ApiImplicitParam(name="batchId", description="批次ID")
    })
    public void upload() {
        renderJson(adminFileService.upload(getFile("file"),getPara("batchId")));
    }

    /**
     * 通过批次ID查询
     */
    @ApiOperation(methods= RequestMethod.POST, description="通过批次ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="batchId", description="批次ID")
    })
    public void queryByBatchId(){
        renderJson(R.ok().put("data", adminFileService.queryByBatchId(getPara("batchId"))));
    }

    /**
     * 通过ID查询
     */
    @ApiOperation(methods= RequestMethod.POST, description="通过ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", description="附件ID")
    })
    public void queryById(){
        renderJson(adminFileService.queryById(getPara("id")));
    }

    /**
     * 通过ID删除
     */
    @ApiOperation(methods= RequestMethod.POST, description="通过ID删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", description="附件ID")
    })
    public void removeById() throws Exception {
        renderJson(adminFileService.removeById(getPara("id")));
    }

    /**
     * 通过批次ID删除
     */
    @ApiOperation(methods= RequestMethod.POST, description="通过批次ID删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name="batchId", description="批次ID")
    })
    public void removeByBatchId(){
        adminFileService.removeByBatchId(getPara("batchId"));
        renderJson(R.ok());
    }

    /**
     * 重命名文件(暂未使用)
     */
    @ApiOperation(methods= RequestMethod.POST, description="重命名文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileId", description="附件ID"),
            @ApiImplicitParam(name="fileName", description="附件名称")
    })
    public void renameFileById(){
        AdminFile file=new AdminFile();
        file.setFileId(getInt("fileId"));
        file.setFileName(getPara("fileName"));
        renderJson(adminFileService.renameFileById(file)?R.ok():R.error());
    }

    /**
     * 下载文件
     */
    @ApiOperation(methods= RequestMethod.POST, description="下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileId", description="附件ID")
    })
    public void downFile(@Para("fileId") String fileId) {
        AdminFile adminFile = AdminFile.dao.findFirst("SELECT * FROM `admin_file` where file_id = ? LIMIT 0,1", fileId);
        if (adminFile != null) {
            MinioServicce.downloadFile(adminFile.getFileName(),getResponse());
        }
        renderNull();
    }
}
