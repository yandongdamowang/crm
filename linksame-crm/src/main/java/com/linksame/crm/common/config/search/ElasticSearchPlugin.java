package com.linksame.crm.common.config.search;

import com.alibaba.fastjson.JSON;
import com.jfinal.kit.LogKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.IPlugin;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.*;

import java.io.IOException;

/**
 * @author ZhangJie
 * @ClassName ElasticSearchPlugin
 * @date2020/3/24 12:11
 * @Description TODO
 **/
public class ElasticSearchPlugin implements IPlugin {
    private String ip;
    private int port;
    private String clusterName;
    int timout = 5*60*1000;
    private static RestHighLevelClient restHighLevelClient;
    public ElasticSearchPlugin(){
        Prop prop = PropKit.use("config/search.txt");
        this.ip = prop.get("elasticSearch.ip");
        this.port = prop.getInt("elasticSearch.port");
        this.clusterName = prop.get("elasticSearch.clusterName");
    }

    public static RestHighLevelClient getClient() {
        return restHighLevelClient;
    }

    @Override
    public boolean start() {
        RestClientBuilder builder = RestClient.builder(new HttpHost(this.ip, this.port, "http")).setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                builder.setConnectTimeout(5000);
                builder.setSocketTimeout(40000);
                builder.setConnectionRequestTimeout(1000);
                return builder;
            }
        }).setMaxRetryTimeoutMillis(timout);
        restHighLevelClient = new RestHighLevelClient(builder);
        try {
            boolean ping = restHighLevelClient.ping(RequestOptions.DEFAULT);
            System.out.println(ping);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestClient lowLevelClient = restHighLevelClient.getLowLevelClient();
        if (lowLevelClient.getNodes().size() > 0) {
            LogKit.info("没有节点可用于连接");
        }else {
            for (Node node : lowLevelClient.getNodes()){
                LogKit.info("节点信息："+node.toString());
            }
        }
        LogKit.info("ElasticsearchClient 连接成功,节点包括："+ JSON.toJSON(lowLevelClient.getNodes()));
        return true;
    }

    @Override
    public boolean stop() {
        if (restHighLevelClient != null){
            try {
                restHighLevelClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
