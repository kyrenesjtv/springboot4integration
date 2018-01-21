//package me.kyrene.springboot4integration.rabbitMQ.sender;
//
//import me.kyrene.springboot4integration.pojo.User;
//import me.kyrene.springboot4integration.rabbitMQ.config.RabbitUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * Created by wanglin on 2018/1/17.
// */
//@Component
//public class UserSender01 {
//
//    @Autowired
//    private RabbitUtils rabbitUtils;
//
//    public void Send(User user){
//        System.out.println("UserSender01: "+user.getName()+"--------------");
//        rabbitUtils.send("user",user);
//    }
//}
