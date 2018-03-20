package com.yunqixie.service;

import com.yunqixie.domain.dao.RelationMapper;
import com.yunqixie.domain.dao.UserMapper;
import com.yunqixie.domain.dto.RelationDTO;
import com.yunqixie.domain.dto.UserDTO;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.UserDataHandler;

import java.sql.Time;
import java.sql.Timestamp;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RelationMapper relationMapper;

    public UserDTO getUserDTO(int uid) {

        if (uid <= 0) {
            return null;
        }
        return userMapper.getUserDTO(uid);
    }

    public UserDTO getUserDTOWithOpenid(String openid) {
        if (openid == null || openid.length() == 0) {
            return null;
        }
        return userMapper.getUserDTOWithOpenid(openid);
    }

    public int insertUserDao(String openid, String unionid, String nickname, String avatar,
                             String country, String province, String city, int sex,
                             String birthday, String mobile) {

        try{
            if (openid.length() == 0 || unionid.length() == 0 || nickname.length() == 0 ||
                    avatar.length() == 0 ){
                return -1;
            }
        }catch (NullPointerException e){
            return -1;
        }

        if (country == null ){
            country = "";
        }
        if (province == null){
            province = "";
        }
        if (city == null){
            city = "";
        }

        try {

            UserDTO userDTO = new UserDTO();
            userDTO.setOpenid(openid);
            userDTO.setUnionid(unionid);
            userDTO.setNickname(nickname);
            userDTO.setWechat_nickname(nickname);
            userDTO.setAvatar(avatar);
            userDTO.setCountry(country);
            userDTO.setProvince(province);
            userDTO.setCity(city);
            userDTO.setSex(sex);
            userDTO.setMobile(mobile != null ? mobile:"");

            Timestamp timestamp = null;
            try {
                if (birthday != null) {
                    timestamp = Timestamp.valueOf(birthday);
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("birthday is: "+birthday);
                timestamp = new Timestamp(0);
            }
            userDTO.setBirthday(timestamp);
            int result = userMapper.insertUserWithUserDTO(userDTO);

            if (result >= 0) {
                int uid = userMapper.getUid(openid);
                return uid;
            }
        } catch (DuplicateKeyException sqlException) {
            System.out.println(sqlException);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return -1;
    }

    public int insertUserWithUserDTO(UserDTO userDAO) {
        try {
            if (userDAO.getCountry() == null){
                userDAO.setCountry("");
            }
            if (userDAO.getProvince() == null){
                userDAO.setProvince("");
            }
            if (userDAO.getCity() == null){
                userDAO.setCity("");
            }
            if (userDAO.getAvatar() == null){
                userDAO.setAvatar("");
            }
            if (userDAO.getNickname() == null){
                userDAO.setNickname("");
            }
            if (userDAO.getWechat_nickname() == null){
                userDAO.setWechat_nickname(userDAO.getNickname());
            }
            if (userDAO.getMobile() == null){
                userDAO.setMobile("");
            }

            int result = userMapper.insertUserWithUserDTO(userDAO);

            if (result >= 0) {
                int uid = userMapper.getUid(userDAO.getOpenid());
                return uid;
            }
        } catch (DuplicateKeyException sqlException) {
            System.out.println(sqlException);
        } catch (MyBatisSystemException be) {
            System.out.println(be.getLocalizedMessage());
            System.out.println(be.getStackTrace());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }

        return -1;
    }

    public int getUid(String openid) {
        return userMapper.getUid(openid);
    }

    public int updateUser(int uid, String openid, String unionid, String nickname, String avatar,
                          String country, String province, String city, int sex,
                          String birthday, String mobile) {
        return userMapper.updateUser(uid, openid, unionid, nickname, nickname, avatar, country, province, city,
                sex, birthday, mobile, 0);
    }

    public int updateUserWithUserDTO(UserDTO userDAO) {
        return userMapper.updateUserWithUserDTO(userDAO);
    }


    /**
     * add follow
     * @return follow type
     */
    public int follow(int uid, int toUid) {

        RelationDTO relationDTO = relationMapper.checkFollow(uid, toUid);
        if (relationDTO != null) {
            if (relationDTO.getA_uid() == uid && (relationDTO.getType() & RelationDTO.RELATION_A_FOLLOW_B) != 0 ||
                    relationDTO.getB_uid() == uid && (relationDTO.getType() & RelationDTO.RELATION_B_FOLLOW_A) != 0) {
                //already followd
                return 0;
            }

            if (relationDTO.getA_uid() == uid) {
                relationDTO.setType(relationDTO.getType() | RelationDTO.RELATION_A_FOLLOW_B);
            } else {
                relationDTO.setType(relationDTO.getType() | RelationDTO.RELATION_B_FOLLOW_A);
            }
            relationMapper.updateFollow(relationDTO);
        } else {
            relationMapper.addFollow(uid, toUid, RelationDTO.RELATION_A_FOLLOW_B);
        }

        return relationDTO.getType();
    }

    /**
     *
     * @param uid
     * @param toUid
     * @return follow type
     */
    public int unfollow(int uid, int toUid) {

        RelationDTO relationDTO = relationMapper.checkFollow(uid, toUid);
        if (relationDTO == null) {
            return -1;
        }
        if (relationDTO.getA_uid() == uid) {
            relationDTO.setType(relationDTO.getType() & ~RelationDTO.RELATION_A_FOLLOW_B);
        } else {
            relationDTO.setType(relationDTO.getType() & ~RelationDTO.RELATION_B_FOLLOW_A);
        }

        if (relationDTO.getType() == 0) {
            //remove recode
            relationMapper.removefollow(relationDTO.getA_uid(), relationDTO.getB_uid());
        } else {
            relationMapper.updateFollow(relationDTO);
        }

        return relationDTO.getType();
    }

}
