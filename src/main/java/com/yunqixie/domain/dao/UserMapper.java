package com.yunqixie.domain.dao;

import com.yunqixie.domain.dto.UserDAO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    @Select("select * from user where uid = #{uid};")
    UserDAO getUserDAO(int uid);

    @Insert("insert into user (openid ,unionid,wechat_nickname,nickname,avatar , country , province , city , sex , " +
            "ctime , birthday , mobile , is_robot) values(#{openid},#{unionid},#{wechat_nickname},#{nickname}," +
            "#{avatar},#{country},#{province},#{city},#{sex},#{ctime},#{birthday},#{mobile},#{is_robot});")
    int insertUser(String openid , String unionid ,String wechat_name , String nickname , String avatar ,
                          String country , String province , String city , int sex,
                          String birthday , String mobile , int is_robot);

    @Select("select * from user where openid = #{openid};")
    UserDAO getUserDAOWithOpenid(String openid);

    @Select("select uid from user where openid = #{openid};")
    int getUid(String openid);

    @Update("update user set openid= #{openid} and unionid = #{unionid} and wechat_nickname= #{wechat_nickname} " +
            "and nickname = #{nickname} and avatar = #{avatar} and country = #{country} and provicen = #{province}" +
            "and city = #{city} and sex = #{sex} and birthday = #{birthday} and mobile = #{mobile} where uid = #{uid};")
    int updateUser(int uid ,  String openid , String unionid ,String wechat_name , String nickname , String avatar ,
                   String country , String province , String city , int sex,
                   String birthday , String mobile , int is_robot);


}
