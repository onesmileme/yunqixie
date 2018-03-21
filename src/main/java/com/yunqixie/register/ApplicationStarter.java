package com.yunqixie.register;

import com.yunqixie.domain.dao.TweetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStarter implements ApplicationListener {

    private  Boolean started = false;

    @Autowired
    private TweetMapper tweetMapper;

    public void onApplicationEvent(ApplicationEvent var1){

        synchronized (started){
            if (!started) {
                started = true;
                init();
            }
        }
    }
    private  void init(){

    }
}
