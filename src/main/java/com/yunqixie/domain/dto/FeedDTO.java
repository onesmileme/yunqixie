package com.yunqixie.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class FeedDTO {

    TweetDTO tweetDTO;

    List<ZanDTO> zanList;

    List<CommentDTO> commentList;

    int zanCount;

    int commentCount;

}
