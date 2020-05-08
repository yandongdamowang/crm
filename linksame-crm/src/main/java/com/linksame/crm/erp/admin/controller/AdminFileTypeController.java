package com.linksame.crm.erp.admin.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.admin.entity.AdminFileType;
import com.linksame.crm.erp.admin.service.AdminFileTypeService;
import com.linksame.crm.utils.R;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;

/**
 * @author Ivan
 * @date 2020/4/20 10:53
 * @Description 附件类型控制层
 */
@Api(description="附件类型接口管理")
public class AdminFileTypeController extends Controller {

    @Inject
    private AdminFileTypeService adminFileTypeService;

    /**
     * 附件类型列表
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询附件类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageType", description="0查询列表 非0分页列表")
    })
    public void queryList(BasePageRequest<AdminFileType> basePageRequest){
        renderJson(adminFileTypeService.queryList(basePageRequest));
    }

    /**
     * 设置文件类型
     * @param adminFileType 文件类型对象
     */
    @ApiOperation(methods= RequestMethod.POST, description="设置文件类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name="adminFileType", description="文件类型对象")
    })
    @Permissions({"file:typeManage:edit"})
    public void setType(@Para("") AdminFileType adminFileType){
        renderJson(adminFileTypeService.setType(adminFileType));
    }

    /**
     * 删除文件类型
     * @param typeId    文件类型ID
     */
    @ApiOperation(methods= RequestMethod.POST, description="删除文件类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name="typeId", description="文件类型ID")
    })
    @Permissions({"file:typeManage:delete"})
    public void delType(Integer typeId){
        renderJson(AdminFileType.dao.deleteById(typeId) ? R.ok() : R.error());
    }
}
