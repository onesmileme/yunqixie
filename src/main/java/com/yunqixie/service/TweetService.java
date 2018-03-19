package com.yunqixie.service;

import com.yunqixie.domain.dao.TweetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetService {

    @Autowired
    TweetMapper tweetMapper;

    public int publish(int uid , String content , String images ){

        return tweetMapper.publishTweet(uid,0,content,images);
    }

}
