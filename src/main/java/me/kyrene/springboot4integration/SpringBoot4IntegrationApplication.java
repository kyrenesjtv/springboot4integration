package me.kyrene.springboot4integration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@EnableAutoConfiguration
@EnableTransactionManagement(proxyTargetClass = true)//开启注解并且使用cglib代理
@SpringBootApplication
@ComponentScan
@MapperScan("me.kyrene.springboot4integration.DAO") // 用于扫描mybatis的Mapper
public class SpringBoot4IntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot4IntegrationApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")//加载application.propertis
	public DataSource dataSource() {
		return new DruidDataSource();
	}

	/**
	 * 扫描 mapper文件
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	/**
	 * 事务管理
	 * @return
	 */
	@Bean(name = "TMManager1")
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}