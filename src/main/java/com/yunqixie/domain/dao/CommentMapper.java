package com.yunqixie.domain.dao;

import com.yunqixie.domain.dto.CommentDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CommentMapper {

    @Insert("insert into comment(tid , from_uid , to_uid , to_cid) values(" +
            "#{tid},#{from_uid},#{to_uid},#{to_cid});")
    int doComment(@Param("tid") int tid ,@Param("from_uid") int from_uid ,
                  @Param("to_uid") int to_uid ,@Param("to_cid") int to_cid );

    @Insert("insert into comment(tid , from_uid , to_uid , to_cid) values(" +
            "#{tid},#{from_uid},#{to_uid},#{to_cid});")
    int doCommentWithModel(CommentDTO commentDTO);

    @Update("update comment set is_del=1 where cid = #{cid};")
    int delComment(int cid);

    @Delete("delete from comment where cid = #{cid};")
    int delCommentForce(int cid);

}
