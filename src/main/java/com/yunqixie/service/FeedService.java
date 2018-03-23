package com.yunqixie.service;

import com.yunqixie.domain.dao.CommentMapper;
import com.yunqixie.domain.dao.RelationMapper;
import com.yunqixie.domain.dao.TweetMapper;
import com.yunqixie.domain.dao.ZanMapper;
import com.yunqixie.domain.dto.*;
import com.yunqixie.tweetqueue.manager.TweetQueueManager;
import com.yunqixie.tweetqueue.model.TweetQueueModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FeedService {

    @Autowired
    TweetMapper tweetMapper;

    @Autowired
    ZanMapper zanMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    RelationMapper relationMapper;


    private static final int PAGE_ITEM_COUNT = 20;//每页读取 20项内容


    @Autowired
    public void setTweetMapper(TweetMapper tweetMapper){
        this.tweetMapper = tweetMapper;
        this.initTweetQueueManager();
    }

    public FeedListDTO getHotFeeds(int pageIndex, int uid) {

        TweetQueueManager manager = TweetQueueManager.sharedManager;

        List<TweetQueueModel> models = manager.getTweetsBetween(pageIndex * PAGE_ITEM_COUNT, (pageIndex + 1) * PAGE_ITEM_COUNT);
        if (models != null && models.size() > 0) {

            FeedListDTO feedsDTO = new FeedListDTO();
            List<FeedDTO> feedDTOS = new LinkedList<>();

            for (TweetQueueModel model : models) {
                FeedDTO feedDTO = new FeedDTO();

                feedDTO.setTweetDTO(model.getTweetDTO());
                List<ZanDTO> zans = zanMapper.getTweetZans(model.getTid());
                List<CommentDTO> comments = commentMapper.getTweetAllComments(model.getTid());
                if (zans != null) {
                    feedDTO.setZanList(zans);
                    feedDTO.setZanCount(zans.size());
                }
                if (comments != null) {
                    feedDTO.setCommentList(comments);
                    feedDTO.setCommentCount(comments.size());
                }

                feedDTOS.add(feedDTO);
            }

            feedsDTO.setTweetList(feedDTOS);

            feedsDTO.setHasMore(manager.getTweetQueueCount() > (pageIndex + 1) * PAGE_ITEM_COUNT);

            return feedsDTO;
        }

        return null;
    }

    /**
     * 获取置顶的推荐
     *
     * @return
     */
    public FeedListDTO getTopFeeds() {

        return null;
    }


    public List<FeedListDTO> getFriendsFeeds(int pn , int uid){


        List<RelationDTO> relationDTOS = relationMapper.getFollower(uid);

        if (relationDTOS != null){

            StringBuilder idsStr = new StringBuilder();

            for (RelationDTO relation : relationDTOS){
                int rid = relation.getA_uid() == uid ? relation.getA_uid() : relation.getB_uid();
                if(idsStr.length() == 0){
                    idsStr.append(rid);
                }else{
                    idsStr.append(","+rid);
                }
            }

            List<TweetDTO> tweetDTOS = tweetMapper.getTweetsByUsers(idsStr.toString());

        }

        return null;
    }

    private synchronized void initTweetQueueManager() {

        TweetQueueManager manager = TweetQueueManager.sharedManager;

        List<TweetDTO> tweets = tweetMapper.getAllTweets();

        manager.removeAllTweets();
        manager.addAllTweets(tweets);
    }

}
