package com.yunqixie.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

enum TweetType  {
    Normal(0),
    Ad(1);

    private int value;
    private TweetType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public TweetType valueOf(int value){
        switch (value){
            case 0 :
                return Normal;
            case 1:
                return Ad;
                default:
                    return null;
        }
    }
}

@Data
public class TweetDTO implements Serializable {

    private int uid;
    private int tid;
    private int type;
    private String content;
    private String images;
    private Timestamp ctime;
    private int is_del;

}
