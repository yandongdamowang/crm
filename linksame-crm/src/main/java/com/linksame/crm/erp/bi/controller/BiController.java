package com.linksame.crm.erp.bi.controller;

import com.linksame.crm.common.annotation.NotNullValidate;
import com.linksame.crm.common.annotation.Permissions;
import com.linksame.crm.erp.bi.service.BiService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;

import java.util.Date;

public class BiController extends Controller {
    @Inject
    private BiService biService;

    /**
     * @author zxy
     * 商业智能，销售漏斗
     */
    @Permissions("bi:business:read")
    public void funnelStatistics(){
        Date startTime = getDate("startTime");
        Date endTime = getDate("endTime");
        Long userId = getLong("userId");
        Integer productId = getInt("productId");
        Integer deptId = getInt("deptId");
        renderJson(biService.queryCrmBusinessStatistics(userId,deptId,productId,startTime,endTime));
    }

    /**
     * 产品销售情况统计
     * @author zxy
     * startTime 开始时间 endTime 结束时间 userId用户ID deptId部门ID
     */
    @Permissions("bi:product:read")
    public void productStatistics(){
        Date startTime = getDate("startTime");
        Date endTime = getDate("endTime");
        Long userId = getLong("userId");
        Integer deptId = getInt("deptId");
        renderJson(biService.queryProductSell(startTime,endTime,userId,deptId));
    }

    /**
     * 获取商业智能业绩目标完成情况
     * @author wyq
     */
    @Permissions("bi:achievement:read")
    @NotNullValidate(value = "year",message = "year不能为空")
    @NotNullValidate(value = "type",message = "type不能为空")
    public void taskCompleteStatistics(@Para("year")String year, @Para("type")Integer type, @Para("deptId")Integer deptId, @Para("userId")Integer userId){
        renderJson(biService.taskCompleteStatistics(year,type,deptId,userId));
    }
}
