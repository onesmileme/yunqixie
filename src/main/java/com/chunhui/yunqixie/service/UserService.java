package com.chunhui.yunqixie.service;

import com.chunhui.yunqixie.domain.dao.UserMapper;
import com.chunhui.yunqixie.domain.dto.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.UserDataHandler;

public class UserService {

    @Autowired
    UserMapper userMapper;

    public UserDAO getUserDAO(int uid){

        if (uid <= 0){
            return null;
        }
        return userMapper.getUserDAO(uid);
    }

    public int insertUserDao(String openid , String unionid , String nickname , String avatar ,
                                 String country , String province , String city , int sex,
                                 String birthday , String mobile){

        return userMapper.insertUser(openid,unionid,nickname,nickname,avatar,country,
                province,city,sex,birthday,mobile,0);
    }
}
