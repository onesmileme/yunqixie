package com.yunqixie.controller;

import com.yunqixie.service.TweetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("tweet")
public class TweetController {

    @Resource
    TweetService tweetService;

    @RequestMapping(value = "publish" , method = RequestMethod.POST)
    public String publishTweet(){


        return null;
    }
}
