package com.yunqixie.service;

import com.yunqixie.domain.dao.CommentMapper;
import com.yunqixie.domain.dao.TweetMapper;
import com.yunqixie.domain.dao.UserMapper;
import com.yunqixie.domain.dao.ZanMapper;
import com.yunqixie.domain.dto.CommentDTO;
import com.yunqixie.domain.dto.TweetDTO;
import com.yunqixie.domain.dto.UserDTO;
import com.yunqixie.domain.dto.ZanDTO;
import com.yunqixie.tweetqueue.manager.TweetQueueManager;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

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

    private  org.slf4j.Logger logger = LoggerFactory.getLogger(TweetService.class);


    public int publish(int uid , String content , String images ){

        if (uid <= 0 || content == null ){
            return -1;
        }

        UserDTO userDTO = userMapper.getUserDTO(uid);
        if (userDTO == null){
            return -1;
        }

        TweetDTO tweetDTO = new TweetDTO();
        tweetDTO.setUid(uid);
        tweetDTO.setContent(content);
        tweetDTO.setImages(images != null ?images:"");
        tweetMapper.publishTweetWithDTO(tweetDTO);

        //current since not so enough tweets , every created tweet will put to hot news
        TweetQueueManager.sharedManager.addTweet(tweetDTO);

        return tweetDTO.getTid();
    }


    public int delete(int tid , int uid){

        try{

            TweetDTO tweetDTO = this.getTweet(tid);
            if (tweetDTO != null && tweetDTO.getUid() == uid){
                tweetMapper.deleteTweet(tid);
                //try remove queue tweet
                TweetQueueManager.sharedManager.removeTweet(tid);
            }else{
                return -2;
            }

        }catch (Exception e){
            logger.error(e.getLocalizedMessage());
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
            return zanDTO.getZid();
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
            logger.error(e.getLocalizedMessage(),e);
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

        if (commentDTO.getContent() == null || commentDTO.getFrom_uid() == 0 ||
                commentDTO.getTid() == 0){
            return -1;
        }

        commentMapper.doCommentWithModel(commentDTO);
        return commentDTO.getCid();
    }

    public  int delComment(int cid , int tid , int uid) {

        CommentDTO commentDTO = commentMapper.getCommentAndCheck(cid,tid,uid);
        if (commentDTO == null){
            return  -1;//not exists
        }

        int ret = commentMapper.delComment(cid);
        return ret;
    }

}
