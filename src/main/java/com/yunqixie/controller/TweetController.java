package com.yunqixie.controller;

import com.yunqixie.common.RequestErrorConfig;
import com.yunqixie.domain.dto.CommentDTO;
import com.yunqixie.domain.dto.TweetDTO;
import com.yunqixie.service.TweetService;
import com.yunqixie.utils.ResponseUtil;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("tweet")
public class TweetController {

    @Resource
    TweetService tweetService;

    @RequestMapping(value = "publish" , method = RequestMethod.POST)
    public String publishTweet(@RequestParam("uid") int uid , @RequestParam("content") String content ,
                               @RequestParam(value = "images" , required = false , defaultValue = "") String images){

        TweetDTO tweetDTO = null;
        try {
             int result = tweetService.publish(uid, content, images);
             if(result >= 0){
                 tweetDTO = new TweetDTO();
                 tweetDTO.setTid(result);
             }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.failed(RequestErrorConfig.SQL_EXCEPTION,e.getLocalizedMessage());
        }
        if (tweetDTO != null){
            return ResponseUtil.successWithModel(tweetDTO);
        }
        return ResponseUtil.failed(999,"CREATE TWEET FAILED");
    }

    @RequestMapping(value = "delete" , method = RequestMethod.POST)
    public String deleteTweet(@RequestParam("tid") int tid , @RequestParam("uid") int uid){

        int result = tweetService.delete(tid,uid);
        if (result == 0){
            return ResponseUtil.successWithModel(null);
        }
        String msg = null;
        if (result == -1){
            msg = "删除失败";
        }else if (result == -2){
            msg = "无删除权限";
        }else{
            msg = "未知错误";
        }
        return ResponseUtil.failed(result,msg);
    }

    @RequestMapping(value = "zan" , method = RequestMethod.POST)
    public String zanTweet(@RequestParam("tid") int tid , @RequestParam("uid")int uid){

        int zid = tweetService.zan(tid,uid);
        if (zid > 0){
            Map<String , Integer> zmap = new HashMap<>();
            zmap.put("zid",new Integer(zid));
            return ResponseUtil.successWithModel(zmap);
        }

        return ResponseUtil.failed(1000,"FAILED");
    }

    @RequestMapping(value = "cancelZan" , method = RequestMethod.POST)
    public String cancelZanTweet(@RequestParam("tid") int tid , @RequestParam("uid")int uid) {

        int result = tweetService.unzan(tid,uid);
        if (result >= 0){
            return ResponseUtil.successWithModel(null);
        }
        return ResponseUtil.failed(1000,"FAILED");
    }

    @RequestMapping(value = "comment",method = RequestMethod.POST)
    public String commentTweet(@RequestParam("tid") int tid , @RequestParam("uid") int uid ,
                               @RequestParam("comment") String comment ,
                               @RequestParam("to_cid") String toCid , @RequestParam("to_uid") String toUid){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setTid(tid);
        commentDTO.setFrom_uid(uid);
        if (toCid != null && toUid != null){
            //comment of comment
            commentDTO.setTo_cid(Integer.parseInt(toCid));
            commentDTO.setTo_uid(Integer.parseInt(toUid));
        }
        int code = 0;
        String msg = "";
        try {
            int ncid = tweetService.doComment(commentDTO);
            if (ncid >= 0) {
                Map<String, Integer> hmap = new HashMap<>();
                hmap.put("cid",Integer.valueOf(ncid));
                return ResponseUtil.successWithModel(hmap);
            }
            code = ncid;
            msg = "创建回复失败";
        }catch (Exception e){
            e.printStackTrace();
            msg = e.getLocalizedMessage();
            code = RequestErrorConfig.SQL_EXCEPTION;
        }
        return null;
    }

    @RequestMapping(value = "delComment", method = RequestMethod.POST)
    public String delCommentTweet(@RequestParam("tid") int tid , @RequestParam("uid") int uid ,
                                  @RequestParam("cid") int cid){

        int code = 0;
        String msg = null;
        try{
            int result = tweetService.delComment(cid,tid,uid);
            if (result == 0){
                return ResponseUtil.successWithModel(null);
            }else{
                code = result;
                if (code == -1){
                    msg = "权限错误";
                }else{
                    msg = "删除失败";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            code = RequestErrorConfig.SQL_EXCEPTION;
            msg = e.getLocalizedMessage();
        }
        return ResponseUtil.failed(code,msg);

    }


}
