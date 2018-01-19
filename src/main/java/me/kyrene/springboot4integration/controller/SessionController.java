package me.kyrene.springboot4integration.controller;

import me.kyrene.springboot4integration.controller.common.BaseController;
import me.kyrene.springboot4integration.utils.redis.JedisUtil;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class SessionController extends BaseController {

    @RequestMapping(value = "/putSession", method = RequestMethod.PUT)
    public Map<String, Integer> putSession(@RequestParam String name, @RequestParam Integer age) {
        Map<String, Integer> map = null;
        if (name != null && age != null) {
            map = new ConcurrentHashMap<>();
            super.getSession().setAttribute(name, age);
            map.put(name, age);
        }
        return map;
    }

    @RequestMapping(value = "/getSession", method = RequestMethod.GET)
    public String getSession(@RequestParam String name) {
        String result = null;
        if (name != null) {
            result = (String) super.getSession().getAttribute(name);
        }
        return result;
    }

    @RequestMapping(value = "/putRedis", method = RequestMethod.PUT)
    public String putRedis(@RequestParam String name, @RequestParam Integer age) {
        JedisUtil jedis = JedisUtil.getInstance();
        if (name != null && age != null) {
            jedis.set(name, String.valueOf(age));
        }
        return "SUCCESS";
    }

    @RequestMapping(value = "/getRedis", method = RequestMethod.GET)
    public String getRedis(@RequestParam String name) {
        JedisUtil jedis = JedisUtil.getInstance();
        String value = super.getParameter(name);
        String result = null;
        if (value != null) {
            result = jedis.get(name);
        }
        return result;
    }
}
