package com.ves.Mapper;

import com.ves.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("select * from t_category;")
    List<Category> getCategoryName();

    @Select("select tcy_name from t_category")
    List<String> selectCategoryName();
}
