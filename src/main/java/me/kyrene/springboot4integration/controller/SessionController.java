package me.kyrene.springboot4integration.controller;

import me.kyrene.springboot4integration.utils.redis.JedisUtil;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wanglin on 2018/1/10.
 */
@RequestMapping(value = "/session")
@RestController
@EnableRedisHttpSession
public class SessionController {

    @RequestMapping(value = "/putSession", method = RequestMethod.PUT)
    public Map<String, String> putSession(HttpServletRequest request) {
        Map<String, String> map = new ConcurrentHashMap<>();
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        request.getSession().setAttribute(name, age);
        map.put(name, age);
        return map;
    }

    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public String getSession(HttpServletRequest request) {
        String name = request.getParameter("name");
        String attribute = (String) request.getSession().getAttribute(name);
        return attribute;
    }

    @RequestMapping(value = "/putRedis", method = RequestMethod.PUT)
    public String putRedis(HttpServletRequest request ) {
        JedisUtil jedis =JedisUtil.getInstance();
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        jedis.set(name,age);
        return "SUCCESS";
    }

    @RequestMapping(value = "/getRedis", method = RequestMethod.GET)
    public String getRedis(HttpServletRequest request ) {
        JedisUtil jedis =JedisUtil.getInstance();
        String name = request.getParameter("name");
        String s = jedis.get(name);
        return s;
    }
}
