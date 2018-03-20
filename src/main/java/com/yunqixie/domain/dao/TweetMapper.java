package com.yunqixie.domain.dao;

import com.yunqixie.domain.dto.TweetDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TweetMapper {


    @Insert("insert into tweet(uid,type,content, images) values(#{uid},#{type},#{content},#{images});")
    int publishTweet(@Param("uid") int uid ,@Param("type") int type ,@Param("content") String content ,
                     @Param("images") String images);

    @Insert("insert into tweet(uid,type,content, images) values(#{uid},#{type},#{content},#{images});")
    int publishTweetWithDTO(TweetDTO tweetDTO);


    @Update("update table tweet set id_del= 1 where tid = #{tid};")
    int deleteTweet(int tid);

    @Delete("delete from tweet where tid = #{tid} ; delete from zan where tid = #{tid};")
    int forceDeleteTweet(int tid);


    @Select("select * from tweet where tid = #{tid};")
    TweetDTO getTweet(int tid);


}
