//package me.kyrene.springboot4integration.elasticSearch.config;
//
//import me.kyrene.springboot4integration.utils.PropertyUtil;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.collect.HppcMaps;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.InetAddress;
//import java.util.Properties;
//
///**
// * Created by wanglin on 2018/1/21.
// */
//@Configuration
//public class ElasticsearchConfig {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchConfig.class);
//
////    static {
////        Properties properties = PropertyUtil.loadProperties("application.properties");
////        String hostName = properties.getProperty("spring.elasticsearch.host");
////        String port = properties.getProperty("spring.elasticsearch.port");
////        String clusterName = properties.getProperty("spring.elasticsearch.cluster.name");
////        String poolSize = properties.getProperty("spring.elasticsearch.pool");
////    }
//    /**
//     * elk集群地址
//     */
//    @Value("${spring.elasticsearch.host}")
//    private String hostName;
//    /**
//     * 端口
//     */
//    @Value("${spring.elasticsearch.port}")
//    private String port;
//    /**
//     * 集群名称
//     */
//    @Value("${spring.elasticsearch.cluster.name}")
//    private String clusterName;
//
//    /**
//     * 连接池
//     */
//    @Value("${spring.elasticsearch.pool}")
//    private String poolSize;
//
//    @Bean
//    public TransportClient client() throws Exception {
//        TransportClient client=null;
//        try {
//        Settings esSettings = Settings.builder()
//                //.put("cluster.name", clusterName) 可以去掉 使用默认的
//                //.put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
//              //  .put("thread_pool.search.size", Integer.parseInt(poolSize))//增加线程池个数，暂时设为5
//                .build();
//
//          //  System.out.println("==================================="+"host"+hostName+"port"+port);
//            client = new PreBuiltTransportClient(esSettings).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostName), Integer.parseInt(port)));
//        }catch (Exception e){
//            LOGGER.info("elasticsearch TransportClient create error!!!", e);
//            return client;
//        }
//
//        return client;
//    }
//
//}
