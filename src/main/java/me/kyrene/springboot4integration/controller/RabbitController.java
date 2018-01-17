package me.kyrene.springboot4integration.controller;

import me.kyrene.springboot4integration.pojo.User;
import me.kyrene.springboot4integration.rabbitMQ.sender.HelloSender01;
import me.kyrene.springboot4integration.rabbitMQ.sender.HelloSender02;
import me.kyrene.springboot4integration.rabbitMQ.sender.UserSender01;
import me.kyrene.springboot4integration.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wanglin on 2018/1/17.
 */
@RestController
@RequestMapping(value = "/rabbit")
public class RabbitController {

    @Autowired
    private HelloSender01 helloSend01;

    @Autowired
    private HelloSender02 helloSend02;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserSender01 userSender01;

    /**
     * 一对一
     *
     * @return
     */
    @RequestMapping(value = "/oneToOne")
    public String oneToOne() {
//        helloSend01.send();
        helloSend01.send("linzai");
        return "OK";
    }

    /**
     * 一对多
     *
     * @return
     */
    @RequestMapping(value = "/oneToMany")
    public String oneToMany() {
        for (int i = 0; i < 10; i++) {
            helloSend01.send("linzai01");
        }
        return "OK";
    }

    /**
     * 多对多
     *
     * @return
     */
    @RequestMapping(value = "/manyToMany")
    public String manyToMany() {
        for (int i = 0; i < 10; i++) {
            helloSend01.send("linzai01");
            helloSend02.send("linzai02");
        }
        return "OK";
    }

    /**
     * 测试实体类
     * @return
     */
    @RequestMapping(value = "/user")
    public String userTest() {
        User user = userService.getUserByID(4L);
        userSender01.Send(user);
        return "OK";
    }

}
