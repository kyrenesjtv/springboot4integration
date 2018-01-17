package me.kyrene.springboot4integration.rabbitMQ.receiver;

import me.kyrene.springboot4integration.pojo.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by wanglin on 2018/1/17.
 */
@Component
@RabbitListener(queues = "user")
public class UserReceiver01 {

    @RabbitHandler
    public void process(User user){
        System.out.println("UserReceiver01: "+user.getName()+"------------");
    }

}
