package com.yunqixie.controller;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.yunqixie.common.RequestErrorConfig;
import com.yunqixie.domain.dto.UserDAO;
import com.yunqixie.service.UserService;
import com.yunqixie.utils.ResponseUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/info" , method = RequestMethod.GET)
    public String userInfo(@RequestParam("uid") int uid){

        UserDAO userDAO = userService.getUserDAO(uid);
        if (userDAO == null){
            return ResponseUtil.failed(RequestErrorConfig.UID_NOT_FOUND,"uid is invalided");
        }
        return ResponseUtil.successWithModel(userDAO);
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public String userLogin(@RequestParam("openid") String openid){
        /*
        *  根据用户操作
        * */
        UserDAO userDAO = userService.getUserDAOWithOpenid(openid);
        if (userDAO == null){
            return ResponseUtil.failed(RequestErrorConfig.OPENID_NOT_FOUND,"openid is invalided");
        }
        return ResponseUtil.successWithModel(userDAO);
    }

    @RequestMapping(value = "/bind",method = RequestMethod.POST)
    public String userBind(@RequestParam("openid") String openid ,@RequestParam("unionid") String unionid ,
                           @RequestParam("nickname") String nickname ,@RequestParam("avatar")  String avatar ,
                           @RequestParam("country") String country , @RequestParam("province") String province ,
                           @RequestParam("city") String city ,@RequestParam("sex")  int sex,
                           @RequestParam("birthday") String birthday ,@RequestParam("mobile") String mobile){


        int uid = userService.getUid(openid);
        if (uid > 0){

        }else {
            uid = userService.insertUserDao(openid, unionid, nickname, avatar, country, province, city, sex, birthday, mobile);
        }

        UserDAO userDAO = new UserDAO();
        userDAO.setUid(uid);
        return ResponseUtil.successWithModel(userDAO);
    }

}
