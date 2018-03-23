package com.yunqixie.tweetqueue.manager;

import com.yunqixie.domain.dto.TweetDTO;
import com.yunqixie.tweetqueue.model.TweetQueueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TweetQueueManager {

    public static TweetQueueManager sharedManager = new TweetQueueManager();
    private List<TweetQueueModel> tweetLists = new LinkedList<>();



    public synchronized int getTweetQueueCount(){
        return tweetLists.size();
    }

    public List<TweetQueueModel> getTweetsBetween(int from , int to){

        if (from >= tweetLists.size()){
            return null;
        }
        if (to > tweetLists.size()){
            to = tweetLists.size() ;
        }
        return tweetLists.subList(from,to);
    }

    public void addTweet(TweetDTO tweetDTO){

        TweetQueueModel model = new TweetQueueModel(tweetDTO);
        if (tweetLists.isEmpty()){
            tweetLists.add(model);
        }else {
            tweetLists.add(0, model);
        }
    }

    public void removeTweet(int tid){

        int i = 0;
        for (; i < tweetLists.size();i++){
            TweetQueueModel model = tweetLists.get(i);
            if (model.getTid() == tid){
                break;
            }
        }

        if (i < tweetLists.size()){
            tweetLists.remove(i);
        }

    }

    public void addAllTweets(List<TweetDTO> tweets){

        List<TweetQueueModel> list = new ArrayList<>(tweets.size());
        if (tweetLists.size() == 0) {
            for (TweetDTO tweet : tweets) {
                TweetQueueModel model = new TweetQueueModel(tweet);
                tweetLists.add(model);
            }
        }else{
            List<TweetQueueModel> models = new LinkedList<>();
            for (TweetDTO tweet : tweets) {
                TweetQueueModel model = new TweetQueueModel(tweet);
                models.add(model);
            }
            models.addAll(this.tweetLists);
            this.tweetLists = models;
        }

    }

    public void removeAllTweets(){

        tweetLists.clear();
    }

}
