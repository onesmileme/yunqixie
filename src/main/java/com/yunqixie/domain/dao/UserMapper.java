package com.yunqixie.domain.dao;

import com.yunqixie.domain.dto.UserDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where uid = #{uid};")
    UserDTO getUserDTO(int uid);

    @Insert("insert into user (openid ,unionid,wechat_nickname,nickname,avatar , country , province , city , sex , " +
            " birthday , mobile , is_robot) values(#{openid},#{unionid},#{wechat_nickname},#{nickname}," +
            "#{avatar},#{country},#{province},#{city},#{sex},#{birthday},#{mobile},#{is_robot});")
    int insertUser(@Param("openid") String openid ,@Param("unionid") String unionid ,
                   @Param("wechat_nickname") String wechat_name , @Param("nickname") String nickname ,
                   @Param("avatar") String avatar ,@Param("country") String country ,
                   @Param("province") String province ,@Param("city") String city ,@Param("sex") int sex,
                   @Param("birthday") String birthday ,@Param("mobile") String mobile ,@Param("is_robot") int is_robot);

    @Insert("insert into user (openid ,unionid,wechat_nickname,nickname,avatar , country , province , city , sex , " +
            " birthday , mobile ) values(#{openid},#{unionid},#{wechat_nickname},#{nickname}," +
            "#{avatar},#{country},#{province},#{city},#{sex},#{birthday},#{mobile});")
    int insertUserWithUserDTO(UserDTO userDAO);

    @Select("select * from user where openid = #{openid};")
    UserDTO getUserDTOWithOpenid(String openid);

    @Select("select uid from user where openid = #{openid};")
    int getUid(String openid);

    @Update("update user set openid= #{openid} , unionid = #{unionid} , wechat_nickname= #{wechat_nickname} " +
            ", nickname = #{nickname} , avatar = #{avatar} , country = #{country} , province = #{province}" +
            ", city = #{city} , sex = #{sex} , birthday = #{birthday} , mobile = #{mobile} where uid = #{uid};")
    int updateUser(@Param("uid") int uid , @Param("openid") String openid ,@Param("unionid") String unionid ,
                   @Param("wechat_nickname") String wechat_name ,@Param("nickname") String nickname ,
                   @Param("avatar") String avatar ,@Param("country") String country ,
                   @Param("province") String province ,@Param("city") String city ,@Param("sex") int sex,
                   @Param("birthday") String birthday ,@Param("mobile") String mobile ,@Param("is_robot") int is_robot);

    @Update("update user set openid= #{openid} , unionid = #{unionid} , wechat_nickname= #{wechat_nickname} " +
            ", nickname = #{nickname} , avatar = #{avatar} , country = #{country} , province = #{province}" +
            ", city = #{city} , sex = #{sex} , birthday = #{birthday} , mobile = #{mobile} where uid = #{uid};")
    int updateUserWithUserDTO(UserDTO user);

}
