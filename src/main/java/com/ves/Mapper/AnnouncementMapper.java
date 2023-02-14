package com.ves.Mapper;

import com.ves.pojo.announcement;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    @Select("select at_id, at_text, at_state,at_time from t_announcement where at_state = 1")
    announcement selectAnnouncement();

    @Select("select at_id, at_text,at_state, at_time from t_announcement limit #{page},#{limit}")
    List<announcement> selectAnnouncementAll(@Param("page")int page, @Param("limit")int limit);

    @Select("select count(at_id) from t_announcement ")
    int selectAnnouncementAllNumber();
}
