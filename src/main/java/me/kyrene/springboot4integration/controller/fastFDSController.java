package me.kyrene.springboot4integration.controller;

import me.kyrene.springboot4integration.fastDFS.utils.FastDFSClientWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wanglin on 2018/1/22.
 */
@RestController(value = "fastDFS")
public class fastFDSController {
    @Autowired
    private FastDFSClientWrapper dfsClient;

    // 上传图片
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(file != null){
            String imgUrl = dfsClient.uploadFile(file);
            return "SUCCESS URL :"+ imgUrl;
        }
        return "Fail";

    }
}

