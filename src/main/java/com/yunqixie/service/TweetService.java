package com.yunqixie.service;

import com.yunqixie.domain.dao.CommentMapper;
import com.yunqixie.domain.dao.TweetMapper;
import com.yunqixie.domain.dao.UserMapper;
import com.yunqixie.domain.dao.ZanMapper;
import com.yunqixie.domain.dto.TweetDTO;
import com.yunqixie.domain.dto.UserDTO;
import com.yunqixie.domain.dto.ZanDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetService {

    @Autowired
    TweetMapper tweetMapper;

    @Autowired
    ZanMapper zanMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;


    public int publish(int uid , String content , String images ){

        return tweetMapper.publishTweet(uid,0,content,images);
    }

    public int zan(int tid , int uid){

        ZanDTO zanDTO = zanMapper.getZan(tid,uid);
        if (zanDTO != null){
            //already zaned
            return 0;
        }
        UserDTO userDAO = userMapper.getUserDTO(uid);
        if (userDAO == null){
            return -1;
        }
        TweetDTO tweetDTO = tweetMapper.getTweet(tid);
        if (tweetDTO == null){
            return -1;
        }

        zanDTO = new ZanDTO();
        zanDTO.setTid(tid);
        zanDTO.setUid(uid);
        zanDTO.setTo_uid(tweetDTO.getUid());
        zanDTO.setUsername(userDAO.getNickname());

        try {
            zanMapper.addZan(zanDTO);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }


        return 0;

    }

    public int unzan(int tid , int uid ){

        try {
            return zanMapper.cancalZan(tid, uid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

}
