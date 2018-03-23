package com.yunqixie.tweetqueue.model;

import com.yunqixie.domain.dto.TweetDTO;
import lombok.Data;

@Data
public class TweetQueueModel {

    private  int tid;
    private TweetDTO tweetDTO;

    public TweetQueueModel(TweetDTO tweetDTO){
        this.tweetDTO = tweetDTO;
        this.tid = tweetDTO.getTid();
    }

    public  void setTweetDTD(TweetDTO tweetDTD){
        this.tweetDTO = tweetDTD;
        this.tid = tweetDTD.getTid();
    }
}
