package com.yunqixie.service;

import com.yunqixie.domain.dao.UserMapper;
import com.yunqixie.domain.dto.UserDAO;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
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

        try {


            int result = userMapper.insertUser(openid, unionid, nickname, nickname, avatar, country,
                    province, city, sex, birthday, mobile, 0);

            if (result >= 0) {
                int uid = userMapper.getUid(openid);
                return uid;
            }
        } catch (DuplicateKeyException sqlException){
            System.out.println(sqlException);
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }

        return -1;
    }

    public int insertUserWithUserDAO(UserDAO userDAO){
        try{
            int result = userMapper.insertUserWithUserDAO(userDAO);

            if (result >= 0) {
                int uid = userMapper.getUid(userDAO.getOpenid());
                return uid;
            }
        } catch (DuplicateKeyException sqlException){
            System.out.println(sqlException);
        } catch (MyBatisSystemException be){
            System.out.println(be.getLocalizedMessage());
            System.out.println(be.getStackTrace());
        } catch (Exception e){
            System.out.println(e.getStackTrace());
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

    public int updateUserWithUserDAO(UserDAO userDAO){
        return userMapper.updateUserWithUserDAO(userDAO);
    }

}
