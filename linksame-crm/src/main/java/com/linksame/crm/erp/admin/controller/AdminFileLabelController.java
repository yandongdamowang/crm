package com.linksame.crm.erp.admin.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.plugin.activerecord.Db;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.admin.entity.AdminFile;
import com.linksame.crm.erp.admin.entity.AdminFileLabel;
import com.linksame.crm.erp.admin.entity.AdminFileType;
import com.linksame.crm.erp.admin.service.AdminFileLabelService;
import com.linksame.crm.erp.admin.service.AdminFileService;
import com.linksame.crm.erp.admin.service.AdminFileTypeService;
import com.linksame.crm.utils.R;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;

import java.util.List;

/**
 * @author Ivan
 * @date 2020/5/4 13:53
 * @Description 附件类型控制层
 */
@Api(description="附件标签接口管理")
public class AdminFileLabelController extends Controller {

    @Inject
    private AdminFileLabelService adminFileLabelService;

    /**
     * 附件标签列表
     */
    @ApiOperation(methods= RequestMethod.POST, description="附件标签列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pageType", description="0查询列表 非0分页列表")
    })
    public void queryList(BasePageRequest<AdminFileLabel> basePageRequest){
        renderJson(adminFileLabelService.queryList(basePageRequest));
    }

    /**
     * 设置标签类型
     * @param adminFileLabel 附件标签对象
     */
    @ApiOperation(methods= RequestMethod.POST, description="设置标签类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name="adminFileLabel", description="附件标签对象")
    })
    public void setLabel(@Para("") AdminFileLabel adminFileLabel){
        renderJson(adminFileLabelService.setLabel(adminFileLabel));
    }

    /**
     * 删除附件标签
     * @param labelId    附件标签ID
     */
    @ApiOperation(methods= RequestMethod.POST, description="删除文件类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name="labelId", description="附件标签ID")
    })
    public void delLabel(Integer labelId){
        renderJson(adminFileLabelService.delLabel(labelId));
    }

    /**
     * 启用标签
     * @param labelId    附件标签ID
     */
    @ApiOperation(methods= RequestMethod.POST, description="启用标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name="labelId", description="附件标签ID")
    })
    public void enableLabel(Integer labelId){
        renderJson(adminFileLabelService.enableLabel(labelId));
    }

    /**
     * 禁用标签
     * @param labelId    附件标签ID
     */
    @ApiOperation(methods= RequestMethod.POST, description="禁用标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name="labelId", description="附件标签ID")
    })
    public void disableLabel(Integer labelId){
        renderJson(adminFileLabelService.disableLabel(labelId));
    }
}
