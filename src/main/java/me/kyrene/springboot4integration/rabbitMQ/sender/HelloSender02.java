//package me.kyrene.springboot4integration.rabbitMQ.sender;
//
//import me.kyrene.springboot4integration.rabbitMQ.config.RabbitUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
///**
// * Created by wanglin on 2018/1/17.
// */
//@Component
//public class HelloSender02 {
//
//    @Autowired
//    private RabbitUtils rabbitUtils;
//
//    public void send() {
//        String sendMsg = "hello " + new Date();
//        System.out.println("HelloSender02 : " + sendMsg);
//        this.rabbitUtils.send("hello2", sendMsg);
//    }
//
//    public void send(String msg) {
//        String sendMsg = "****-"+msg+"-*****"+ new Date();
//        System.out.println("HelloSender02 : " + sendMsg);
//        this.rabbitUtils.send("hello2", sendMsg);
//    }
//}
