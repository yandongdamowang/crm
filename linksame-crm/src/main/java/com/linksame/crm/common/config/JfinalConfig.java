package com.linksame.crm.common.config;

import com.jfinal.aop.Aop;
import com.jfinal.config.*;
import com.jfinal.core.paragetter.ParaProcessorBuilder;
import com.jfinal.ext.proxy.CglibProxyFactory;
import com.jfinal.kit.LogKit;
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
import com.linksame.crm.common.minio.route.MinioRouter;
import com.linksame.crm.erp._MappingKit;
import com.linksame.crm.erp.admin.common.AdminRouter;
import com.linksame.crm.erp.bi.common.BiRouter;
import com.linksame.crm.erp.crm.common.CrmRouter;
import com.linksame.crm.erp.oa.common.OaRouter;
import com.linksame.crm.erp.pmp.common.PmpRouter;
import com.linksame.crm.erp.work.common.WorkRouter;
import com.linksame.crm.erp.work.service.WorkService;
import live.autu.plugin.jfinal.swagger.config.SwaggerPlugin;
import live.autu.plugin.jfinal.swagger.config.routes.SwaggerRoutes;
import live.autu.plugin.jfinal.swagger.model.SwaggerApiInfo;
import live.autu.plugin.jfinal.swagger.model.SwaggerDoc;
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
        /*if(ClassLoaderUtil.isPresent("com.jfinal.server.undertow.UndertowServer")){
            me.setBaseUploadPath(BaseConstant.UPLOAD_PATH);
            me.setBaseDownloadPath(BaseConstant.UPLOAD_PATH);
        }*/
        me.setJsonFactory(new ErpJsonFactory());
        //上传文件大小限制, 客户需求1个G以上, 设置为2G超出int值限制, 取int最大值
        me.setMaxPostSize(2147483647);
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
        me.add(new PmpRouter());

        me.add(new MinioRouter());
        me.add(new SwaggerRoutes());
    }

    @Override
    public void configEngine(Engine me) {
        me.setDevMode(true);
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
        druidPlugin.setInitialSize(15);
        druidPlugin.setMinIdle(0);
        druidPlugin.setMaxActive(2000);
        druidPlugin.setValidationQuery("select 1");
        //连接Idle最小连接30分钟，每15分钟检查一次
        druidPlugin.setTimeBetweenEvictionRunsMillis(900000);
        druidPlugin.setMinEvictableIdleTimeMillis(1800000);
        //连接超时设置
        druidPlugin.setMaxWait(600000);
        //每次连接操作数据库时，检测：该连接的有效性
        druidPlugin.setTestOnBorrow(true);
        druidPlugin.setTestOnReturn(false);
        druidPlugin.setTestWhileIdle(true);
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
        ElasticSearchPlugin searchPlugin = new ElasticSearchPlugin();
        me.add(searchPlugin);
        //Redis以及缓存插件
        RedisPlugin redisPlugin=new RedisPlugin();
        me.add(redisPlugin);
        //cron定时器
        me.add(new Cron4jPlugin(PropKit.use("config/cron4j.txt")));
        //swagger配置
        me.add(new SwaggerPlugin(new SwaggerDoc().setBasePath("/").setHost(BaseConstant.SWAGGER_PATH).setSwagger("2.0")
                .setInfo(new SwaggerApiInfo(BaseConstant.SWAGGER_DESCRIPTION, "1.0", BaseConstant.SWAGGER_TITLE, ""))));
        //model映射
        _MappingKit.mapping(arp);
    }

    public static DruidPlugin createDruidPlugin() {
        return new DruidPlugin(prop.get("mysql.jdbcUrl"), prop.get("mysql.user"), prop.get("mysql.password").trim()).setFilters("stat,wall");
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

        LogKit.info("数据库连接地址: " + prop.get("mysql.jdbcUrl"));
        LogKit.info("服务启动完成！");
    }

    @Override
    public void onStop() {
        LogKit.info("服务即将停止！");
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
