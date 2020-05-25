package com.linksame.crm.common.constant;

import com.linksame.crm.utils.BaseUtil;

import java.io.Serializable;

/**
 * 一些基础配置
 */
public class BaseConstant implements Serializable {

    public static final String NAME = "CRM";

    public static final String VERSION = "1.4.0";

    private static final long serialVersionUID = 1L;
    /**
     * 默认的上传文件路径
     * TODO 默认配置文件路径
     */
    public final static String UPLOAD_PATH = BaseUtil.isWindows() ? "E:\\upload\\" : "/usr/local/upload/";


    /**
     * 角色类型列表
     */
    public static final Integer[] ROLE_TYPES = {1, 2, 7, 8, 0};

    /**
     * 超级管理员的roleId
     */
    public static final Integer SUPER_ADMIN_ROLE_ID = 1;

    /**
     * 最终的超级管理员ID，不可被删除
     */
    public static final Long SUPER_ADMIN_USER_ID = 3L;
    /**
     * 查询数据权限递归次数
     */
    public static final int AUTH_DATA_RECURSION_NUM = 20;

    /**
     * 用户登录成功
     */
    public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
    /**
     * 用户登录错误缓存key
     */
    public static final String USER_LOGIN_ERROR_KEY = "LOGIN_ERROR_USER_";

    /**
     * 手机登录时验证码缓存key
     */
    public static final String PHONE_LOGIN_KEY = "LOGIN_PHONE_";

    /**
     * 项目管理员角色ID
     */
    public  static  Integer WORK_ADMIN_ROLE_ID ;
    /**
     * 每个项目管理员角色ID
     */
    public static  Integer SMALL_WORK_ADMIN_ROLE_ID ;

    /**
     * 每个项目编辑角色ID
     */
    public static  Integer SMALL_WORK_EDIT_ROLE_ID;

    /**
     * Swagger请求地址
     */
    public static final String SWAGGER_PATH = BaseUtil.isWindows() ? "127.0.0.1" : "192.168.1.174";

    /**
     * Swagger标题
     */
    public final static String SWAGGER_TITLE = "邻盛CRM Swagger Api";

    /**
     * Swagger描述
     */
    public static final String SWAGGER_DESCRIPTION = "全宇宙最牛逼的Jfinal集成Swagger接口文档系统";

    /**
     * Minio连接地址
     */
    public static final String MINIO_URL = "http://192.168.1.174:9001";

    /**
     * Minio 连接账号/密码
     */
    public static final String MINIO_KEY = "linksame";

    /**
     * Minio文件存储----Bucket_Name
     */
    public static final String BUCKET_NAME = "crm";

}
