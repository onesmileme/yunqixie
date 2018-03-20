package com.yunqixie.service;

import com.yunqixie.domain.dao.CommentMapper;
import com.yunqixie.domain.dao.TweetMapper;
import com.yunqixie.domain.dao.UserMapper;
import com.yunqixie.domain.dao.ZanMapper;
import com.yunqixie.domain.dto.CommentDTO;
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

    public int delete(int tid , int uid){

        try{

            TweetDTO tweetDTO = this.getTweet(tid);
            if (tweetDTO != null && tweetDTO.getUid() == uid){
                tweetMapper.deleteTweet(tid);
            }else{
                return -2;
            }

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

        return 0;
    }

    public TweetDTO getTweet(int tid){

        return tweetMapper.getTweet(tid);
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
            int zid = zanMapper.addZan(zanDTO);
            zanDTO.setZid(zid);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }


        return zanDTO.getZid();

    }

    public int unzan(int tid , int uid ){

        try {
            return zanMapper.cancalZan(tid, uid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public  int doComment(CommentDTO commentDTO){

        return commentMapper.doCommentWithModel(commentDTO);
    }

    public  int delComment(int cid , int tid , int uid) {

        CommentDTO commentDTO = commentMapper.getCommeent(cid,tid,cid);
        if (commentDTO == null){
            return  -1;//not exists
        }

        int ret = commentMapper.delComment(cid);
        return ret;
    }

}
