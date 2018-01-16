package me.kyrene.springboot4integration.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by wanglin on 2018/1/16.
 *  spring 1.4 以后需要有这个.java
 */
@Configuration
//@ConfigurationProperties(prefix = "spring.druid.datasource",locations = "classpath:application.properties")
public class DruidDBConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(DruidDBConfig.class);

    @Value("${spring.datasource.url}")//这样子能把application.properties中的value给赋值
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("{spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Bean(name = "dataSource")     //声明其为Bean实例
    @Primary  //在同样的DataSource中，首先使用被标注的DataSource （意思就是覆盖其他的dataSource)
    public DataSource dataSource(){
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(this.username);
        datasource.setPassword(this.password);
        datasource.setDriverClassName(this.driverClassName);

        //configuration
        datasource.setInitialSize(this.initialSize);
        datasource.setMinIdle(this.minIdle);
        datasource.setMaxActive(this.maxActive);
        datasource.setMaxWait(this.maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        datasource.setValidationQuery(this.validationQuery);
        datasource.setTestWhileIdle(this.testWhileIdle);
        datasource.setTestOnBorrow(this.testOnBorrow);
        datasource.setTestOnReturn(this.testOnReturn);
        datasource.setPoolPreparedStatements(this.poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            LOGGER.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionProperties);

        return datasource;
    }
}
