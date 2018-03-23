package com.yunqixie.domain.dao;

import com.yunqixie.domain.dto.TweetDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TweetMapper {


    /*
    //only use dto can get insert tid
    @Insert("insert into tweet(uid,type,content, images) values(#{uid},#{type},#{content},#{images});" +
            "select last_insert_id();")
    @Options(useGeneratedKeys = true , keyProperty = "tid" , keyColumn = "tid")
    int publishTweet(@Param("uid") int uid ,@Param("type") int type ,@Param("content") String content ,
                     @Param("images") String images);
    */
    @Insert("insert into tweet(uid,type,content, images) values(#{uid},#{type},#{content},#{images});")
    @Options(useGeneratedKeys = true , keyProperty = "tid" , keyColumn = "tid")
    int publishTweetWithDTO(TweetDTO tweetDTO);


    @Update("update table tweet set id_del= 1 where tid = #{tid};")
    int deleteTweet(int tid);

    @Delete("delete from tweet where tid = #{tid} ; delete from zan where tid = #{tid};")
    int forceDeleteTweet(int tid);


    @Select("select * from tweet where tid = #{tid};")
    TweetDTO getTweet(int tid);


    @Select("select * from tweet where is_del = 0 order by tid desc;")
    List<TweetDTO> getAllTweets(); //Stupid get all tweets

    @Select("select * from tweet where tid in (#{ids}) order by tid desc;")
    List<TweetDTO> getTweetsByUsers(@Param("ids") String ids);


}
