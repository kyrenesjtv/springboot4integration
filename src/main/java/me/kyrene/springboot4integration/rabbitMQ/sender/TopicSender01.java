//package me.kyrene.springboot4integration.rabbitMQ.sender;
//
//import me.kyrene.springboot4integration.rabbitMQ.config.RabbitUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Created by wanglin on 2018/1/18.
// */
//@Component
//public class TopicSender01 {
//
//    @Autowired
//    private RabbitUtils rabbitUtils;
//
//    public void send() {
//        String msg1 = "I am topic.mesaage msg ======";
//        System.out.println("sender : " + msg1);
//        this.rabbitUtils.send("exchange", "topic.message", msg1);
//
//        String msg2 = "I am topic.mesaages msg ########";
//        System.out.println("sender : " + msg2);
//        this.rabbitUtils.send("exchange", "topic.messages", msg2);
//    }
//}
