package com.yunqixie.domain.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FeedListDTO {

    List<FeedDTO> tweetList;

    boolean hasMore;

}
