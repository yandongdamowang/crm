package com.linksame.crm.common.config.druid;

import com.linksame.crm.common.config.redis.RedisManager;
import com.linksame.crm.utils.BaseUtil;
import com.jfinal.plugin.druid.IDruidStatViewAuth;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangzhiwei
 * 配置druid的进入规则，暂定为只要登录就可查看
 */
public class DruidConfig implements IDruidStatViewAuth {
    @Override
    public boolean isPermitted(HttpServletRequest request) {
        String token = BaseUtil.getToken(request);
        return RedisManager.getRedis().exists(token);
    }
}
