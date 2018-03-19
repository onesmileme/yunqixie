package com.yunqixie.service;

import com.yunqixie.domain.dao.UserMapper;
import com.yunqixie.domain.dto.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.UserDataHandler;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public UserDAO getUserDAO(int uid){

        if (uid <= 0){
            return null;
        }
        return userMapper.getUserDAO(uid);
    }

    public UserDAO getUserDAOWithOpenid(String openid){
        if (openid == null || openid.length() == 0){
            return null;
        }
        return userMapper.getUserDAOWithOpenid(openid);
    }

    public int insertUserDao(String openid , String unionid , String nickname , String avatar ,
                                 String country , String province , String city , int sex,
                                 String birthday , String mobile){

        int result = userMapper.insertUser(openid,unionid,nickname,nickname,avatar,country,
                province,city,sex,birthday,mobile,0);

        if (result == 0){
            int uid = userMapper.getUid(openid);
            return uid;
        }

        return -1;
    }

    public  int getUid(String openid){
        return userMapper.getUid(openid);
    }
    public  int updateUser(int uid ,  String openid , String unionid  , String nickname , String avatar ,
                           String country , String province , String city , int sex,
                           String birthday , String mobile ){
        return userMapper.updateUser(uid,openid,unionid,nickname,nickname,avatar,country,province,city,
                sex,birthday,mobile,0);
    }


}
