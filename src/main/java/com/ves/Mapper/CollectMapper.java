package com.ves.Mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectMapper {

    /**
     * 查询商品是否收藏
     */
    @Select("select count(*) from t_collect where tct_uuid = #{uuid} and tct_gs_id = #{id};")
    int selectCollectJudge(@Param("uuid") String uuid,@Param("id") int id);

    /**
     * 添加收藏
     */
    @Insert("insert into t_collect(tct_uuid, tct_gs_id) values(#{uuid},#{id})")
    int insertCollect(@Param("uuid")String uuid,@Param("id")int id);

    /**
     * 删除收藏
     */
    @Delete("delete from t_collect where tct_uuid = #{uuid} and tct_gs_id = #{id}")
    int deleteCollect(@Param("uuid")String uuid,@Param("id")int id);

    /**
     * 查询用户全部收藏
     * @param uuid
     * @return
     */
    @Select("select tct_gs_id from t_collect where tct_uuid = #{uuid};")
    List<Integer> selectCollectAll(String uuid);
}
