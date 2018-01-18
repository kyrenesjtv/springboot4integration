package me.kyrene.springboot4integration.rabbitMQ.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by wanglin on 2018/1/18.
 */
@Component
@RabbitListener(queues = "topic.message")
public class TopicMessage01 {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("TopicMessage  : " +msg);
    }
}
