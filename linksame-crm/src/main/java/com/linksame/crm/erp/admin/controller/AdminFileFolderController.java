package com.linksame.crm.erp.admin.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.annotation.NotBlank;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.erp.admin.entity.AdminFileFolder;
import com.linksame.crm.erp.admin.service.AdminFileFolderService;
import com.linksame.crm.utils.R;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;

/**
 * @author Ivan
 * @date 2020/4/17 12:43
 * @Description 附件文件夹控制层
 */
@Api(description="附件文件夹接口管理")
public class AdminFileFolderController extends Controller {

    @Inject
    private AdminFileFolderService adminFileFolderService;

    /**
     * 创建文件夹
     * @param adminFileFolder   文件夹
     */
    @ApiOperation(methods= RequestMethod.POST, description="创建文件夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name="adminFileFolder", description="文件夹对象(第一层文件夹folderPid可传0, 不可传空字符串)")
    })
    @NotBlank({ "folderName" })
    @Permissions({"file:folderManage:create"})
    public void createFolder(@Para("") AdminFileFolder adminFileFolder){
        renderJson(adminFileFolderService.createFolder(adminFileFolder));
    }

    /**
     * 查询文件夹(层级展示)
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询文件夹(层级展示)")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ifUser", description="是否根据当前用户查询文件目录树(0=是, 不传或非0=否)")
    })
    public void queryFolder(){
        renderJson(adminFileFolderService.queryFolder(getInt("ifUser")));
    }

    /**
     * 文件夹重命名
     */
    @ApiOperation(methods= RequestMethod.POST, description="文件夹重命名")
    @ApiImplicitParams({
            @ApiImplicitParam(name="folderId", description="文件夹编号"),
            @ApiImplicitParam(name="folderName", description="文件夹名称")
    })
    @NotBlank({ "folderName" })
    @Permissions({"file:folderManage:rename"})
    public void renameFolder(@Para("") AdminFileFolder adminFileFolder){
        renderJson(adminFileFolder.update() ? R.ok() : R.error());
    }

    /**
     * 移动文件夹
     */
    @ApiOperation(methods= RequestMethod.POST, description="移动文件夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name="folderId", description="文件夹编号"),
            @ApiImplicitParam(name="folderPid", description="文件夹父编号")
    })
    @Permissions({"file:folderManage:mobile"})
    public void mobileFolder(@Para("") AdminFileFolder adminFileFolder){
        renderJson(adminFileFolder.update() ? R.ok() : R.error());
    }

    /**
     * 删除文件夹
     */
    @ApiOperation(methods= RequestMethod.POST, description="删除文件夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name="adminFileFolder", description="文件夹对象")
    })
    @Permissions({"file:folderManage:delete"})
    public void deleteFolder(){
        adminFileFolderService.deleteFolder(getInt("folderId"));
        renderJson(R.ok());
    }

}
