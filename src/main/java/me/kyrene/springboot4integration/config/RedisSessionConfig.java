package me.kyrene.springboot4integration.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by wanglin on 2018/1/10.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=60*60)//1h后过期
public class RedisSessionConfig {

}
