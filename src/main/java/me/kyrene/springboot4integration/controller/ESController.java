//package me.kyrene.springboot4integration.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import me.kyrene.springboot4integration.elasticSearch.utils.ElasticsearchUtils;
//import me.kyrene.springboot4integration.pojo.User;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//import java.util.Map;
//
///**
// * Created by wanglin on 2018/1/20.
// */
//@RestController
//@RequestMapping(value = "/es")
//public class ESController {
//
//    private String index = "index";
//    private String type = "type";
//
//
//    @RequestMapping(value = "/putdata", method = RequestMethod.PUT)
//    public String putESData(){
//        User user = new User();
//        user.setId(11L);
//        user.setName("linzai");
//        user.setAge(22);
//
//        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(user);
//        String s = ElasticsearchUtils.addData(jsonObject, index, type, "1");
//        System.out.println(s);
//
//        return "SUCCESS";
//    }
//    @RequestMapping(value = "/getdata", method = RequestMethod.GET)
//    public String getESData(){
//        Map<String, Object> stringObjectMap = ElasticsearchUtils.searchDataById(index, type, "1", "");
//        System.out.println(stringObjectMap);
//        return "SUCCESS";
//    }
//}
