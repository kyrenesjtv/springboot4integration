package me.kyrene.springboot4integration.rabbitMQ.sender;

import me.kyrene.springboot4integration.rabbitMQ.config.RabbitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wanglin on 2018/1/18.
 */
@Component
public class FanoutSender {

    @Autowired
    private RabbitUtils rabbitUtils;

    public void send() {
        String msgString = "FanoutSender :aaaa-----------------";
        System.out.println(msgString);
        this.rabbitUtils.send("fanoutExchange", "", msgString);
    }
}
