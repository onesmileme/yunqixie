package com.yunqixie.controller;

import com.alibaba.fastjson.JSON;
import com.yunqixie.domain.dto.FunFeedDAO;
import com.yunqixie.domain.dao.FunFeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jws.WebParam;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Resource
    private FunFeedMapper funFeedMapper;

    @RequestMapping("info")
    public  String info(){

        return "HAHA";
    }

    @RequestMapping("all")
    public String allfeeds(@RequestParam(value = "name" , required = false, defaultValue = "default") String name){

        System.out.println("name is: "+name);

        StringBuilder feedHtml = new StringBuilder();

        feedHtml.append("<table border=\"1\"><thead><tr><th>FEEDID</th><th>CONTENT</th></thead>");
        feedHtml.append("<tbody>");
        List<FunFeedDAO> feeds = funFeedMapper.getAllFeeds();
        for (FunFeedDAO dao : feeds){
            feedHtml.append("<tr><td>"+dao.getFid()+"</td><td>"+dao.getContent()+"</td></tr>");
        }
        feedHtml.append("</tbody></table>");


        return feedHtml.toString();
    }
}
