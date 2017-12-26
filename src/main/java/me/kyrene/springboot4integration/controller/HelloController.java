package me.kyrene.springboot4integration.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wanglin on 2017/12/26.
 */
@RestController//输出的时候是jason格式
public class HelloController {
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String hello(){
        return  "HELLO WORLD";
    }
}
