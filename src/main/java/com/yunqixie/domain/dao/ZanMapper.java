package com.yunqixie.domain.dao;

import com.yunqixie.domain.dto.ZanDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ZanMapper {

    @Insert("insert into zan(tid,uid,to_uid,username) values(#{tid},#{uid},#{to_uid}" +
            ",#{username});")
    int addZan(ZanDTO zanDTO);

    @Delete("delete from zan where zid = #{zid};")
    int cancelZan(@Param("zid") int zid);

    @Delete("delete from zan where tid = #{tid} and uid = #{uid};")
    int cancalZan(@Param("tid") int tid ,@Param("uid") int uid);

    @Select("select * from zan where tid = #{tid} and uid = #{uid};")
    ZanDTO getZan(@Param("tid") int tid ,@Param("uid") int uid);

}
