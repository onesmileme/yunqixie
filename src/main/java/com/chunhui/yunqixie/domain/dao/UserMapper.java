package com.chunhui.yunqixie.domain.dao;

import com.chunhui.yunqixie.domain.dto.UserDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where uid = #{uid};")
    public UserDAO getUserDAO(int uid);

    @Select("insert into user (openid ,unionid,wechat_nickname,nickname,avatar , country , province , city , sex , " +
            "ctime , birthday , mobile , is_robot) values(#{openid},#{unionid},#{wechat_nickname},#{nickname}," +
            "#{avatar},#{country},#{province},#{city},#{sex},#{ctime},#{birthday},#{mobile},#{is_robot});")
    public int insertUser(String openid , String unionid ,String wechat_name , String nickname , String avatar ,
                          String country , String province , String city , int sex,
                          String birthday , String mobile , int is_robot);
}
