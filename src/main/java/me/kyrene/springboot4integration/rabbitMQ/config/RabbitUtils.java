package me.kyrene.springboot4integration.rabbitMQ.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wanglin on 2018/1/17.
 */
@Component
public class RabbitUtils {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送 key 是路由 message 信息
     * @param routingKey
     * @param message
     */
    public void send(String routingKey , Object message){
        this.amqpTemplate.convertAndSend(routingKey,message);
    }

    /**
     *
     * @param exchange 交换机
     * @param routingKey 路由
     * @param message 信息
     */
    public void send(String exchange,String routingKey,Object message){
        this.amqpTemplate.convertAndSend(exchange,routingKey, message);
    }
}
