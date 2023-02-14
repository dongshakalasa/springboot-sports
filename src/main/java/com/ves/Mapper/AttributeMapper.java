package com.ves.Mapper;

import com.ves.pojo.Attribute;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttributeMapper {

    /**
     *查询商品对应的所有属性名
     */
    @Select("select DISTINCT(taev_name) from t_attribute where pt_id = #{id};")
    List<String> selectAttributeName(int id);

    /**
     * 查询商品的可选属性值
     */
    @Select("select taev_id, taev_value from t_attribute where pt_id = #{id} and taev_name = #{name};")
    List<Attribute> selectAttributeList(@Param("id") int id,@Param("name") String name);

    /**
     * 查询属性名
     */
    @Select("select taen_name from t_attributename;")
    List<String> selectAttributeNameList();

    /**
     * 删除商品属性
     * @param id
     * @return
     */
    @Delete("delete from t_attribute where pt_id = #{id}")
    int deleteAttribute(int id);

    /**
     * 添加商品属性
     * @param id
     * @param name
     * @param value
     * @return
     */
    @Insert("insert into t_attribute(pt_id, taev_name, taev_value) values(#{id},#{name},#{value})")
    int insertAttribute(@Param("id")int id,@Param("name") String name,@Param("value") String value);
}
