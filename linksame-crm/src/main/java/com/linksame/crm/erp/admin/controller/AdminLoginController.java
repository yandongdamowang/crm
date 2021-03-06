package com.linksame.crm.erp.admin.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.aliyuncs.utils.StringUtils;
import com.jfinal.core.paragetter.Para;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.linksame.crm.common.config.redis.Redis;
import com.linksame.crm.common.config.redis.RedisManager;
import com.linksame.crm.common.constant.BaseConstant;
import com.linksame.crm.erp.admin.entity.AdminUser;
import com.linksame.crm.erp.admin.service.AdminRoleService;
import com.linksame.crm.utils.BaseUtil;
import com.linksame.crm.utils.R;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Db;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * 用户登录
 *
 * @author z
 */
@Clear
public class AdminLoginController extends Controller{


    @Inject
    private AdminRoleService adminRoleService;

    public final static Prop prop = PropKit.use("config/crm-config.txt");

    public void index(){
        redirect("/index.html");
    }

    /**
     * 用户登录
     * @param username  用户名
     * @param password  密码
     */
    public void login(@Para("username") String username, @Para("password") String password){
        String key = BaseConstant.USER_LOGIN_ERROR_KEY + username;//拼接Key 错误用户
        Redis redis= RedisManager.getRedis();
        long beforeTime = System.currentTimeMillis() - 60 * 5 * 1000;
        if(redis.exists(key)){
            if(redis.zcount(key, beforeTime, System.currentTimeMillis()) >= 5){
                Set zrevrange = redis.zrevrange(key, 4, 5);
                Long time = (Long) zrevrange.iterator().next() + 60 * 5 * 1000;
                long expire = (time - System.currentTimeMillis()) / 1000;
                renderJson(R.error("密码错误次数过多，请等" + expire + "秒后在重试！"));
                return;
            }
        }
        redis.zadd(key, System.currentTimeMillis(), System.currentTimeMillis());
        AdminUser user = AdminUser.dao.findFirst(Db.getSql("admin.user.queryByUserName"), username.trim());
        if(user == null){
            renderJson(R.error("用户名或密码错误！"));
            return;
        }
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            renderJson(R.error("账号或者密码不能为空！"));
            return;
        }
        if(user.getStatus() == 0){
            renderJson(R.error("账户被禁用！"));
            return;
        }
        if(BaseUtil.verify(username + password, user.getSalt(), user.getPassword())){
            if(user.getStatus() == 2){
                user.setStatus(1);
            }
            redis.del(key);
            String token = IdUtil.simpleUUID();
            user.setLastLoginIp(ServletUtil.getClientIP(getRequest()));
            user.setLastLoginTime(new Date());
            user.update();
            user.setRoles(adminRoleService.queryRoleIdsByUserId(user.getUserId()));
            redis.setex(token, 3600, user);
            redis.setex(BaseConstant.LOGIN_SUCCESS+username, 3600, user);
            Integer type=getParaToInt("type",1);
            BaseUtil.setToken(user.getUserId(),token,type);
            user.remove("password", "salt");
            renderJson(R.ok().put("Admin-Token", token).put("user", user).put("auth", adminRoleService.auth(user.getUserId())));
        }else{
            Log.getLog(getClass()).warn("用户登录失败");
            renderJson(R.error("用户名或密码错误！"));
            return;
        }

    }

    /**
     * 用户登录----手机验证码登录
     * @author Ivan
     * @param mobile    手机号
     * @param captcha   验证码
     */
    public void phoneLogin(@Para("mobile") String mobile, @Para("captcha") String captcha){
        AdminUser user = AdminUser.dao.findFirst(Db.getSql("admin.user.queryByMobile"), mobile.trim());
        if (user == null) {
            renderJson(R.error("该手机号码未注册！"));
            return;
        }
        if (user.getStatus() == 0) {
            renderJson(R.error("账户被禁用！"));
            return;
        }
        Redis redis= RedisManager.getRedis();
        String key = BaseConstant.PHONE_LOGIN_KEY + mobile;
        if (redis.exists(key)) {
            String redisRusult = redis.get(key);
            if(!redisRusult.equals(captcha)){
                renderJson(R.error("验证码输入错误！"));
                return;
            }
        } else {
            renderJson(R.error("验证码已失效！"));
            return;
        }
        if (user.getStatus() == 2) {
            user.setStatus(1);
        }
        //获取用户名缓存
        String usernameKey = BaseConstant.USER_LOGIN_ERROR_KEY + user.getUsername();
        redis.del(usernameKey);
        String token = IdUtil.simpleUUID();
        user.setLastLoginIp(ServletUtil.getClientIP(getRequest()));
        user.setLastLoginTime(new Date());
        user.update();
        user.setRoles(adminRoleService.queryRoleIdsByUserId(user.getUserId()));
        redis.setex(token, 3600, user);
        redis.setex(BaseConstant.LOGIN_SUCCESS+user.getUsername(), 3600, user);
        Integer type = getParaToInt("type", 1);
        BaseUtil.setToken(user.getUserId(), token, type);
        renderJson(R.ok().put("Admin-Token", token).put("user", user).put("auth", adminRoleService.auth(user.getUserId())));
    }

    /**
     * @author zhangzhiwei
     * 退出登录
     */
    public void logout(){
        String token = BaseUtil.getToken(getRequest());
        AdminUser o = (AdminUser)RedisManager.getRedis().get(token);
        String username = o.getUsername();
        String password = o.getPassword();
        if (username != null) {
            Object o1 = RedisManager.getRedis().get(BaseConstant.LOGIN_SUCCESS + username);
        }
        if(! StrUtil.isEmpty(token)){
            RedisManager.getRedis().del(token);
            removeCookie("Admin-Token");
            RedisManager.getRedis().del(BaseConstant.LOGIN_SUCCESS+username);
        }
        renderJson(R.ok());
    }

    public void version(){
        renderJson(R.ok().put("name", BaseConstant.NAME).put("version", BaseConstant.VERSION));
    }


    public void ping(){
        List<String> arrays = new ArrayList<>();
        Connection connection = null;
        try{
            connection = Db.use().getConfig().getConnection();
            if(connection != null){
                arrays.add("数据库连接成功");
            }
        }catch(Exception e){
            arrays.add("数据库连接异常");
        }finally{
            if(connection != null){
                try{
                    connection.close();
                }catch(SQLException e){
                    Log.getLog(getClass()).error("",e);
                }
            }

        }
        try{
            String ping = RedisManager.getRedis().ping();
            if("PONG".equals(ping)){
                arrays.add("Redis配置成功");
            }else{
                arrays.add("Redis配置失败");
            }
        }catch(Exception e){
            arrays.add("Redis配置失败");
        }
        renderJson(R.ok().put("data", arrays));
    }
}
