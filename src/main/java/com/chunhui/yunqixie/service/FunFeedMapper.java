package com.chunhui.yunqixie.service;

import com.chunhui.yunqixie.domain.dao.FunFeedDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FunFeedMapper {

    @Select("select * from funfeed")
    List<FunFeedDAO> getAllFeeds();

    @Select("select * from funfeed where uid = '#{uid}'")
    List<FunFeedDAO> getUserFeeds(String uid);

}
