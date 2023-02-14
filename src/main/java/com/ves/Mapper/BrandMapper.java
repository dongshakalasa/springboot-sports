package com.ves.Mapper;

import com.ves.pojo.Brand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BrandMapper {

    /**
     * 根据分类查询对应品牌
     * @param name
     * @return
     */
    @Select("select * from t_brand where cy_name = #{name}")
    List<Brand> selectBrandByCategoryName(String name);

    /**
     * 根据分类查询对应品牌分页
     * @param name
     * @return
     */
    @Select("select * from t_brand where cy_name = #{name} limit #{page},#{limit}")
    List<Brand> selectBrandByCategoryNameLimit(@Param("name") String name,@Param("page")int page,@Param("limit") int limit);



    /**
     * 查询品牌图标
     * @param brand
     * @return
     */
    @Select("select tbd_img from t_brand where tbd_name = #{brand};")
    String selectBrandImg(String brand);
}
