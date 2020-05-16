package com.linksame.crm.erp.admin.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.erp.admin.entity.AdminSms;
import com.linksame.crm.erp.admin.service.AdminSmsService;
import live.autu.plugin.jfinal.swagger.annotation.Api;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParam;
import live.autu.plugin.jfinal.swagger.annotation.ApiImplicitParams;
import live.autu.plugin.jfinal.swagger.annotation.ApiOperation;
import live.autu.plugin.jfinal.swagger.config.RequestMethod;

/**
 * @author Ivan
 * @date 2020/4/23 17:37
 * @Description 短信服务管理
 */
@Api(description="短信服务管理")
public class AdminSmsController extends Controller {

    @Inject
    private AdminSmsService adminSmsService;

    /**
     * 查询短信列表
     * @param basePageRequest   构造对象
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询短信列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="basePageRequest", description="构造对象")
    })
    public void queryList(BasePageRequest<AdminSms> basePageRequest){
        renderJson(adminSmsService.queryList(basePageRequest));
    }

    /**
     * 发送短信
     * @param adminSms  短信对象
     */
    @ApiOperation(methods= RequestMethod.POST, description="发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name="adminSms", description="短信对象")
    })
    public void sendSms(@Para("") AdminSms adminSms){
        renderJson(adminSmsService.sendSms(adminSms));
    }

    /**
     * 发送服务验证码
     * @param mobile
     */
    @ApiOperation(methods= RequestMethod.POST, description="发送服务验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name="mobile", description="手机号码"),
            @ApiImplicitParam(name="seconds", description="获取验证码有效时间(单位:秒)")
    })
    public void sendCaptcha(String mobile, Integer seconds){
        renderJson(adminSmsService.sendCaptcha(mobile, seconds));
    }

    /**
     * 删除短信
     * @param smsId 短信ID
     */
    @ApiOperation(methods= RequestMethod.POST, description="删除短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name="smsId", description="短信ID")
    })
    public void delSms(Integer smsId){
        renderJson(AdminSms.dao.deleteById(smsId));
    }

    /**
     * 查询余额
     */
    @ApiOperation(methods= RequestMethod.POST, description="查询短信余额")
    public void queryBal(){
        renderJson(adminSmsService.queryBal());
    }

}
