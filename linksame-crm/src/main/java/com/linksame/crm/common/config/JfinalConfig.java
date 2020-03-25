package com.linksame.crm.common.config;

import cn.hutool.core.util.ClassLoaderUtil;
import com.jfinal.aop.Aop;
import com.jfinal.config.*;
import com.jfinal.core.paragetter.ParaProcessorBuilder;
import com.jfinal.ext.proxy.CglibProxyFactory;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.cron4j.Cron4jPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.druid.DruidStatViewHandler;
import com.jfinal.render.RenderManager;
import com.jfinal.template.Engine;
import com.linksame.crm.common.config.cache.CaffeineCache;
import com.linksame.crm.common.config.druid.DruidConfig;
import com.linksame.crm.common.config.json.ErpJsonFactory;
import com.linksame.crm.common.config.paragetter.BasePageRequest;
import com.linksame.crm.common.config.paragetter.MapParaGetter;
import com.linksame.crm.common.config.paragetter.PageParaGetter;
import com.linksame.crm.common.config.redis.RedisPlugin;
import com.linksame.crm.common.config.render.ErpRenderFactory;
import com.linksame.crm.common.config.search.ElasticSearchPlugin;
import com.linksame.crm.common.constant.BaseConstant;
import com.linksame.crm.common.interceptor.AuthInterceptor;
import com.linksame.crm.common.interceptor.ErpInterceptor;
import com.linksame.crm.erp._MappingKit;
import com.linksame.crm.erp.admin.common.AdminRouter;
import com.linksame.crm.erp.bi.common.BiRouter;
import com.linksame.crm.erp.crm.common.CrmRouter;
import com.linksame.crm.erp.oa.common.OaRouter;
import com.linksame.crm.erp.work.common.WorkRouter;
import com.linksame.crm.erp.work.service.WorkService;

import java.io.File;
import java.util.Map;

/**
 * API 引导式配置
 */
public class JfinalConfig extends JFinalConfig {

    public final static Prop prop = PropKit.use("config/crm-config.txt");
    /**
     * 配置常量
     */
    @Override
    public void configConstant(Constants me) {
        me.setDevMode(prop.getBoolean("jfinal.devMode", true));
        me.setInjectDependency(true);
        //设置上传文件到哪个目录
        if(ClassLoaderUtil.isPresent("com.jfinal.server.undertow.UndertowServer")){
            me.setBaseUploadPath(BaseConstant.UPLOAD_PATH);
            me.setBaseDownloadPath(BaseConstant.UPLOAD_PATH);
        }
        me.setJsonFactory(new ErpJsonFactory());
        //限制上传100M
        me.setMaxPostSize(104857600);
        //jfinal Can not get javax.tools.JavaCompiler, check whether "tools.jar" is in the environment variable CLASSPATH
        //添加注入Proxy动态代理配置(4.6之前配置方式)
        me.setProxyFactory(new CglibProxyFactory());
    }

    /**
     * 配置路由
     */
    @Override
    public void configRoute(Routes me) {
        me.add(new AdminRouter());
        me.add(new BiRouter());
        me.add(new CrmRouter());
        me.add(new OaRouter());
        me.add(new WorkRouter());
    }

    @Override
    public void configEngine(Engine me) {

    }

    /**
     * 配置插件
     */
    @Override
    public void configPlugin(Plugins me) {
        ParaProcessorBuilder.me.regist(BasePageRequest.class, PageParaGetter.class, null);
        ParaProcessorBuilder.me.regist(Map.class, MapParaGetter.class, null);
        // 配置 druid 数据库连接池插件
        DruidPlugin druidPlugin = createDruidPlugin();
        druidPlugin.setInitialSize(0);
        druidPlugin.setMinIdle(0);
        druidPlugin.setMaxActive(2000);
        druidPlugin.setTimeBetweenEvictionRunsMillis(5000);
        druidPlugin.setValidationQuery("select 1");
        druidPlugin.setTimeBetweenEvictionRunsMillis(60000);
        druidPlugin.setMinEvictableIdleTimeMillis(30000);
        me.add(druidPlugin);
        // 配置ActiveRecord插件
        ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
        arp.setCache(CaffeineCache.ME);
        arp.setDialect(new MysqlDialect());
        arp.setShowSql(true);
        arp.getEngine().addDirective("fori", CrmDirective.class,true);
        me.add(arp);
        //扫描sql模板
        getSqlTemplate(PathKit.getRootClassPath() + "/template", arp);
        //elasticSearch 插件
        ElasticSearchPlugin elasticSearchPlugin = new ElasticSearchPlugin();
        me.add(elasticSearchPlugin);
        //Redis以及缓存插件
        RedisPlugin redisPlugin=new RedisPlugin();
        me.add(redisPlugin);
        //cron定时器
        me.add(new Cron4jPlugin(PropKit.use("config/cron4j.txt")));

        //model映射
        _MappingKit.mapping(arp);
    }

    public static DruidPlugin createDruidPlugin() {
        return new DruidPlugin(prop.get("mysql.jdbcUrl"), prop.get("mysql.user"), prop.get("mysql.password").trim()).setInitialSize(1).setMinIdle(1).setMaxActive(2000).setTimeBetweenEvictionRunsMillis(5000).setValidationQuery("select 1").setTimeBetweenEvictionRunsMillis(60000).setMinEvictableIdleTimeMillis(30000).setFilters("stat,wall");
    }

    /**
     * 配置全局拦截器
     */
    @Override
    public void configInterceptor(Interceptors me) {
        //添加全局拦截器
        me.addGlobalActionInterceptor(new ErpInterceptor());
        me.add(new AuthInterceptor());
    }

    /**
     * 配置处理器
     */
    @Override
    public void configHandler(Handlers me) {
        //配置数据库监控
        me.add(new DruidStatViewHandler("/druid", new DruidConfig()));
        //自定义渲染工厂
        RenderManager.me().setRenderFactory(new ErpRenderFactory());
    }

    @Override
    public void onStart() {
        WorkService workService= Aop.get(WorkService.class);
        workService.initialization();
    }

    @Override
    public void onStop() {

    }

    private void getSqlTemplate(String path, ActiveRecordPlugin arp) {
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (File childFile : files) {
                    if (childFile.isDirectory()) {
                        getSqlTemplate(childFile.getAbsolutePath(), arp);
                    } else {
                        if (childFile.getName().toLowerCase().endsWith(".sql")) {
                            arp.addSqlTemplate(childFile.getAbsolutePath().replace(PathKit.getRootClassPath(), "").replace("\\", "/"));
                        }
                    }
                }
            }
        }
    }
}
