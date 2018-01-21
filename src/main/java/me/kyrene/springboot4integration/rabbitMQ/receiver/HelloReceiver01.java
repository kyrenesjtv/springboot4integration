//package me.kyrene.springboot4integration.rabbitMQ.receiver;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * Created by wanglin on 2018/1/17.
// */
//@Component
//@RabbitListener(queues = "hello")
//public class HelloReceiver01 {
//
//    @RabbitHandler
//    public void process(String hello){
//        System.out.println("HelloReceiver01: "+hello);
//    }
//}
