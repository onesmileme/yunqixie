package com.yunqixie.domain.dao;

import com.yunqixie.domain.dto.RelationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RelationMapper {

    @Select("select * from relation where a_uid=#{uid} and type = 2 or " +
            "b_uid=#{uid} and type = 1 or a_uid=#{uid} and type = 3;")
    List<RelationDTO> getFans(int uid); //get users that follow uid

    @Select("select * from relation where b_uid=#{uid} and type = 2 or " +
            "a_uid=#{uid} and type = 1 or a_uid=#{uid} and type = 3;")
    List<RelationDTO> getFollower(int uid);//get users that uid follows

    @Select("select * from relation where a_uid = #{a_uid} and b_uid = #{b_uid} or " +
            "a_uid = #{b_uid} and b_uid = #{a_uid};")
    RelationDTO checkFollow(@Param("a_uid") int a_uid , @Param("b_uid")int b_uid);

    @Update("insert into relation(a_uid , b_uid , type) values(#{a_uid},#{b_uid}, #{type}); ")
    int addFollow(@Param("a_uid") int a_uid ,@Param("b_uid") int b_uid ,@Param("type") int type); // a follow b

    @Update("insert into relation(a_uid , b_uid , type) values(#{a_uid},#{b_uid}, #{type}); ")
    int addFollowWithModel(RelationDTO relationDTO);

    @Update("update relation set type = #{type} where a_uid = #{a_uid} and b_uid=#{b_uid};")
    int updateFollow(RelationDTO relationDTO);

    @Update("delete from relation where a_uid = #{a_uid} and b_uid=#{b_uid};")
    int removefollow(@Param("a_uid") int a_uid ,@Param("b_uid") int b_uid); //a unfollow b

}
