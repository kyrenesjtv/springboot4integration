package me.kyrene.springboot4integration.rabbitMQ.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wanglin on 2018/1/17.
 */
@Configuration
public class RabitConfig {

    @Bean
    public Queue helloQueue(){
        return new Queue("hello");//对应路由
    }

    @Bean
    public Queue helloQueue2(){
        return new Queue("hello2");
    }

    @Bean
    public Queue userQueue(){
        return new Queue("user");
    }
}
