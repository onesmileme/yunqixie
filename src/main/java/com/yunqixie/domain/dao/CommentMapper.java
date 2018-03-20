package com.yunqixie.domain.dao;

import com.yunqixie.domain.dto.CommentDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface CommentMapper {

    /*
    //only commentdto can get inserted cid
    @Insert("insert into comment(tid , from_uid , to_uid , to_cid) values(" +
            "#{tid},#{from_uid},#{to_uid},#{to_cid});")
    int doComment(@Param("tid") int tid ,@Param("from_uid") int from_uid ,
                  @Param("to_uid") int to_uid ,@Param("to_cid") int to_cid );
    */

    @Insert("insert into comment(tid , from_uid , to_uid , to_cid ,content) values(" +
            "#{tid},#{from_uid},#{to_uid},#{to_cid},#{content});")
    @Options(useGeneratedKeys = true , keyProperty = "cid",keyColumn = "cid")
    int doCommentWithModel(CommentDTO commentDTO);

    @Update("update comment set is_del=1 where cid = #{cid};")
    int delComment(@Param("cid") int cid);

    @Delete("delete from comment where cid = #{cid};")
    int delCommentForce(@Param("cid") int cid);

    @Select("select * from comment where cid = #{cid};")
    CommentDTO getComment(@Param("cid") int cid);

    @Select("select * from comment where cid = #{cid} and tid = #{tid} and from_uid = #{from_uid};")
    CommentDTO getCommentAndCheck(@Param("cid") int cid ,@Param("tid") int tid ,@Param("from_uid") int uid);

    @Select("select * from comment where tid = #{tid} order by ctime asec limit #{limit};")
    List<CommentDTO> getTweetCommentList(@Param("tid") int tid ,@Param("limit") int limit);
}
