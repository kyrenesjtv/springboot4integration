package me.kyrene.springboot4integration.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wanglin on 2018/1/19.
 */
@Component
public class BaseController {

    @Autowired
    private HttpServletRequest request ;

    protected HttpSession getSession() {
        return request.getSession();
    }
    protected String getParameter(String key){
        return request.getParameter(key);
    }
}
