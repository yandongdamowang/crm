package com.linksame.crm.erp.admin.controller;

import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.common.minio.service.MinioServicce;
import com.linksame.crm.erp.admin.entity.AdminFile;
import com.linksame.crm.erp.admin.entity.AdminFileLog;
import com.linksame.crm.erp.admin.service.AdminFileService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;

@Api(description="附件接口管理")
public class AdminFileController extends Controller {

    @Inject
    private AdminFileService adminFileService;

    /**
     * 附件查询
     * @param basePageRequest   分页对象
     */
    @ApiOperation(methods= RequestMethod.POST, description="附件分页列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="basePageRequest", description="条件构造对象")
    })
    @Permissions({"file:fileManage:index"})
    public void getFileList(BasePageRequest<AdminFile> basePageRequest){
        renderJson(adminFileService.getFileList(basePageRequest));
    }

    /**
     * 上传附件(本地上传)
     */
    @ApiOperation(methods= RequestMethod.POST, description="附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name="file", description="附件"),
            @ApiImplicitParam(name="adminFile", description="附件对象")
    })
    public void upload(@Para("") AdminFile adminFile) {
        renderJson(adminFileService.upload(getFile("file"),adminFile));
    }

    /**
     * 批量附件上传
     */
    @ApiOperation(methods= RequestMethod.POST, description="批量附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name="files", description="附件集合")
    })
    public void batchUpload(@Para("") AdminFile adminFile){
        renderJson(adminFileService.batchUpload(getFiles("files"),adminFile));
    }

    /**
     * 附件更新版本
     */
    @ApiOperation(methods= RequestMethod.POST, description="更换附件版本")
    @ApiImplicitParams({
            @ApiImplicitParam(name="file", description="附件"),
            @ApiImplicitParam(name="adminFile", description="附件对象")
    })
    @Permissions({"file:fileManage:changeVersion"})
    public void changeVersion(@Para("") AdminFile adminFile){
        renderJson(adminFileService.changeVersion(getFile("file"),adminFile));
    }

    /**
     * 编辑版本信息
     * @param adminFile 附件对象
     */
    @ApiOperation(methods= RequestMethod.POST, description="编辑版本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="adminFile", description="附件对象")
    })
    @Permissions({"file:fileManage:editVersion"})
    public void editVersionData(@Para("") AdminFile adminFile){
        AdminFile file = AdminFile.dao.findById(adminFile.getFileId());
        //保存日志
        AdminFileLog adminFileLog = new AdminFileLog();
        adminFileLog.setUserId(BaseUtil.getUser().getUserId());
        adminFileLog.setFileId(adminFile.getFileId());
        adminFileLog.setContent("用户 " + BaseUtil.getUser().getRealname() + " 将附件 " + file.getCompositionName() + " 版本备注更新为: " + adminFile.getFileRemark());
        adminFileService.saveFileLog(adminFileLog);
        renderJson(adminFile.update() ? R.ok() : R.error());
    }

    /**
     * 设置主版本附件
     */
    @ApiOperation(methods= RequestMethod.POST, description="设置主版本附件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileId", description="附件ID")
    })
    @Permissions({"file:fileManage:toMain"})
    public void editVersionToMain(){
        renderJson(adminFileService.editVersionToMain(getInt("fileId")));
    }

    /**
     * 查询附件详情
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询附件详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileId", description="附件ID")
    })
    @Permissions({"file:fileManage:read"})
    public void queryFileInfo(){
        renderJson(adminFileService.queryFileInfo(getInt("fileId")));
    }

    /**
     * 通过批次ID查询(任务, 合同, 回款等业务通过批次ID查询)
     */
    /*@ApiOperation(methods= RequestMethod.POST, description="通过批次ID查询(任务, 合同, 回款等业务通过批次ID查询)")
    @ApiImplicitParams({
            @ApiImplicitParam(name="batchId", description="批次ID")
    })
    public void queryByBatchId(){
        renderJson(adminFileService.queryByBatchId(getPara("batchId")));
    }*/

    /**
     * 通过ID查询
     */
    @ApiOperation(methods= RequestMethod.POST, description="通过ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileId", description="附件ID")
    })
    public void queryById(){
        renderJson(adminFileService.queryById(getPara("fileId")));
    }

    /**
     * 通过附件ID将附件放入回收站
     */
    @ApiOperation(methods= RequestMethod.POST, description="通过附件ID将附件放入回收站")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileId", description="附件ID")
    })
    @Permissions({"file:fileManage:recycle"})
    public void addRecycleById() {
        renderJson(adminFileService.addRecycleById(getPara("fileId")));
    }

    //TODO 批量加入回收站, 业务层待编码
    /*public void addRecycleByIds() {
        renderJson(adminFileService.addRecycleByIds(getPara("fileIds")));
    }*/

    /**
     * 通过ID删除
     */
    @ApiOperation(methods= RequestMethod.POST, description="通过ID删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileId", description="附件ID")
    })
    @Permissions({"file:fileManage:delete"})
    public void removeById() throws Exception {
        renderJson(adminFileService.removeById(getInt("fileId")));
    }

    /**
     * 通过批次ID删除
     */
    @ApiOperation(methods= RequestMethod.POST, description="通过批次ID删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name="batchId", description="批次ID")
    })
    public void removeByBatchId(){
        renderJson(adminFileService.removeByBatchId(getPara("batchId")));
    }

    /**
     * 重命名文件(暂未使用)
     */
    @ApiOperation(methods= RequestMethod.POST, description="重命名文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileId", description="附件ID"),
            @ApiImplicitParam(name="compositionName", description="附件名称")
    })
    @Permissions({"file:fileManage:rename"})
    public void renameFile(@Para("") AdminFile adminFile){
        renderJson(adminFileService.renameFileById(adminFile));
    }

    /**
     * 下载文件
     */
    @ApiOperation(methods= RequestMethod.POST, description="下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileId", description="附件ID")
    })
    @Permissions({"file:fileManage:down"})
    public void downFile() {
        AdminFile adminFile = AdminFile.dao.findFirst("SELECT * FROM `admin_file` where file_id = ?", getInt("fileId"));
        if (adminFile != null) {
            //保存日志
            AdminFileLog adminFileLog = new AdminFileLog();
            adminFileLog.setUserId(BaseUtil.getUser().getUserId());
            adminFileLog.setFileId(adminFile.getFileId());
            adminFileLog.setContent("用户 " + BaseUtil.getUser().getRealname() + " 下载了文件");
            adminFileService.saveFileLog(adminFileLog);
            MinioServicce.downloadFile(adminFile.getFileName(),getResponse());
        }
        renderNull();
    }

    /**
     * 查询用户网盘附件(通过用户编号查询附件)
     */
    /*@ApiOperation(methods= RequestMethod.POST, description="查询用户网盘附件(当前用户)")
    public void queryByUserId(){
        renderJson(adminFileService.queryByUserId());
    }*/

    /**
     * 网盘上传
     */
    @ApiOperation(methods= RequestMethod.POST, description="网盘上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileIds", description="附件ID数组"),
            @ApiImplicitParam(name="batchId", description="批次ID"),
            @ApiImplicitParam(name="folderId", description="文件夹ID"),
            @ApiImplicitParam(name="workId", description="项目ID")
    })
    public void netdiskUpload(){
        renderJson(adminFileService.netdiskUpload(getPara("fileIds"), getInt("folderId"), getInt("workId"), getPara("batchId")));
    }

    /**
     * 移动附件
     */
    @ApiOperation(methods= RequestMethod.POST, description="移动附件")
    @ApiImplicitParams({
            @ApiImplicitParam(name="fileIds", description="附件ID数组"),
            @ApiImplicitParam(name="batchId", description="批次ID"),
            @ApiImplicitParam(name="folderId", description="文件夹ID"),
            @ApiImplicitParam(name="workId", description="项目ID")
    })
    public void mobileFiles(){
        getInt("folderId");
        renderJson(adminFileService.mobileFiles(getPara("fileIds"), getInt("folderId"), getInt("workId"), getPara("batchId")));
    }

    /**
     * 查询附件日志
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询附件日志")
    public void queryFileLog(BasePageRequest<AdminFileLog> basePageRequest){
        renderJson(adminFileService.queryFileLog(basePageRequest));
    }
}
