package com.chunhui.yunqixie.controller;

import com.chunhui.yunqixie.domain.dao.FunFeedDAO;
import com.chunhui.yunqixie.service.FunFeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private FunFeedMapper funFeedMapper;

    @RequestMapping("info")
    public  String info(){

        return "HAHA";
    }

    @RequestMapping("all")
    public String allfeeds(){

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
