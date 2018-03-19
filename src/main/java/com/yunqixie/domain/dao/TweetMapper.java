package com.yunqixie.domain.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TweetMapper {


    @Insert("insert into tweet(uid,type,content, images) values(#{uid},#{type},#{content},#{images});")
    int publishTweet(int uid , int type , String content , String images);



}
