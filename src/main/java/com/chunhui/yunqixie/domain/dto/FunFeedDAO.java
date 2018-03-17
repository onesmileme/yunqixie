package com.chunhui.yunqixie.domain.dto;

import  lombok.Data;

import java.io.Serializable;

@Data
public class FunFeedDAO implements Serializable{

    protected String fid;
    protected String content;
    protected String uid;
    protected String time;


}
