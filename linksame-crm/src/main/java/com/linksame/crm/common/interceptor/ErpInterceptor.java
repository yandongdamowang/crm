package com.linksame.crm.common.interceptor;

import cn.hutool.core.convert.BasicType;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.kit.StrKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import com.linksame.crm.common.annotation.HttpEnum;
import com.linksame.crm.common.annotation.NotBlank;
import com.linksame.crm.common.annotation.NotNullValidate;
import com.linksame.crm.common.annotation.RequestBody;
import com.linksame.crm.common.config.redis.RedisManager;
import com.linksame.crm.erp.admin.entity.AdminUser;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import java.lang.reflect.Parameter;
import java.util.Date;

public class ErpInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        try {
            Controller controller = invocation.getController();
            //截取swagger请求前缀
            String url = controller.getRequest().getRequestURI();
            String[] prefix = url.split("/");
            //swagger服务及短信验证码接口放行
            if(!(("swagger").equals(prefix[1])) && !(("sms").equals(prefix[1]))){
                String token = BaseUtil.getToken(controller.getRequest());
                AdminUser adminUser = RedisManager.getRedis().get(token);
                if (adminUser==null) {
                    controller.renderJson(R.error(302, "请先登录！"));
                    return;
                }
                BaseUtil.setUser(adminUser);
                //数据非空验证
                /*if(!this.notNullValidate(invocation)){
                    return;
                }*/
                //新增数据非空校验
                if(!this.notBlank(invocation)){
                    return;
                }
                this.modelToJson(invocation);
                BaseUtil.userExpire(token);
                invocation.invoke();
            } else {
                //数据转换json的处理
                this.modelToJson(invocation);
                invocation.invoke();
            }
        } catch (Exception e) {
            invocation.getController().renderJson(R.error("服务器响应异常"));
            Log.getLog(invocation.getController().getClass()).error("响应错误", e);
        } finally {
            BaseUtil.removeThreadLocal();
        }
    }

    //非空校验
    public boolean notBlank(Invocation inv){
        NotBlank annotation = inv.getMethod().getAnnotation(NotBlank.class);
        if (annotation != null && annotation.value().length > 0) {
            Controller controller = inv.getController();
            for (String name : annotation.value()) {
                // 判断是否为空
                String para = controller.getPara(name);
                if (StrKit.isBlank(para)) {
                    controller.renderJson(R.error("参数" + name + "不允许为空"));
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 数据非空校验
     */
    private boolean notNullValidate(Invocation inv) {
        NotNullValidate[] validates = inv.getMethod().getAnnotationsByType(NotNullValidate.class);
        Controller controller = inv.getController();
        if (ArrayUtil.isNotEmpty(validates)) {
            if (HttpEnum.PARA.equals(validates[0].type())) {
                for (NotNullValidate validate : validates) {
                    if (controller.getPara(validate.value()) == null) {
                        controller.renderJson(R.error(500, validate.message()));
                        return false;
                    }
                }
            } else if (HttpEnum.JSON.equals(validates[0].type())) {
                JSONObject jsonObject = JSON.parseObject(controller.getRawData());
                for (NotNullValidate validate : validates) {
                    if (!jsonObject.containsKey(validate.value()) || jsonObject.get(validate.value()) == null) {
                        controller.renderJson(R.error(500, validate.message()));
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private void modelToJson(Invocation inv){
        Parameter[] parameters = inv.getMethod().getParameters();
        JSONObject jsonObject = null;
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getAnnotation(RequestBody.class) != null) {
                if(jsonObject==null){
                    jsonObject=JSON.parseObject(inv.getController().getRawData());
                }
                //TODO 目前的处理是直接对整个json数据进行初始化
                Class clazz = parameters[i].getType();
                if (clazz.isAssignableFrom(Record.class)) {
                    inv.setArg(i, new Record().setColumns(jsonObject));
                }else if(BasicType.unWrap(clazz).isPrimitive()||clazz.isAssignableFrom(String.class)||clazz.isAssignableFrom(Date.class)){
                    String name=parameters[i].getName();
                    inv.setArg(i,jsonObject.getObject(name,clazz));
                } else {
                    inv.setArg(i, jsonObject.toJavaObject(clazz));
                }
            }
        }
    }
}
