package com.ves.Mapper;

import com.ves.pojo.Sort;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SortMapper {

    @Select("select * from t_sort where cy_name = #{name}")
    List<Sort> selectSortByCategoryName(String name);

    @Select("select * from t_sort where cy_name = #{name} limit #{page},#{limit}")
    List<Sort> selectSortLimit(@Param("name")String name,@Param("page")int page,@Param("limit")int limit);

    @Delete("delete from t_sort where tst_id = #{id}")
    int deleteSort(int id);
}
