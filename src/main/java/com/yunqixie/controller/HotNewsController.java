package com.yunqixie.controller;

import com.yunqixie.common.RequestErrorConfig;
import com.yunqixie.domain.dto.HotFeedsDTO;
import com.yunqixie.service.FeedService;
import com.yunqixie.utils.ResponseUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("hotnews")
public class HotNewsController {


    @Resource
    FeedService feedService;

    @RequestMapping(value = "news" )
    public String getNews(@RequestParam(value = "pn" , required = false , defaultValue = "0") int pn , int uid){

        try {
            HotFeedsDTO feedsDTO = feedService.getHotFeeds(pn, uid);
            if (feedsDTO != null) {
                return ResponseUtil.successWithModel(feedsDTO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseUtil.failed(RequestErrorConfig.HOT_NEWS_NO_FEEDS,"NO DATA");
    }



}
