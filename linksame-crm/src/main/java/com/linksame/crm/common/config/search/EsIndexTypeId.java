package com.linksame.crm.common.config.search;

/**
 * @author ZhangJie
 * @ClassName EsIndexTypeId
 * @date2020/3/24 19:12
 * @Description TODO
 **/
public interface EsIndexTypeId<T> {

        /**
         * 类型名，必须指定
         * @return 该对象的索引
         */
        String index();
        /**
         * 类型名，必须指定
         * @return 该对象的类型
         */
        String type();
        /**
         * 索引唯一id，必须指定
         * @return 该对象的Id
         */
        T id();
    }
