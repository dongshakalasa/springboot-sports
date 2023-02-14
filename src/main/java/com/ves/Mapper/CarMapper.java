package com.ves.Mapper;

import com.ves.pojo.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarMapper {

    //t_car

    /**
     * 添加购物车
     * @param carInfo
     * @return
     */
    int insertCart(Cart carInfo);

    /**
     * 查询购物车信息
     * @param uuid
     * @return
     */
    @Select("select tcr_id,tcr_gs_id,tcr_number,tcr_state from t_car where tcr_uuid = #{uuid};")
    List<Cart> selectCartInfo(String uuid);

    /**
     * 选中商品
     * @param uuid
     * @return
     */
    @Select("select tcr_id,tcr_gs_id,tcr_number,tcr_state from t_car where tcr_uuid = #{uuid} and tcr_state =1;")
    List<Cart> selectCartInfoState(String uuid);

    //car_attribute

    /**
     * 添加选择属性
     * @param id
     * @param attribute
     * @return
     */
    @Insert("insert into car_attribute(tcr_id, tcr_ae_value) values(#{id},#{attribute})")
    int insertCartAttribute(@Param("id")int id,@Param("attribute")String attribute);

    /**
     * 查询商品选择属性
     * @param id
     * @return
     */
    @Select("select tcr_ae_value from car_attribute where tcr_id = #{id};")
    List<String> selectCartAttribute(int id);

    /**
     * 切换商品选中状态
     */
    @Update("update t_car set tcr_state = #{state} where tcr_id = #{id}")
    Integer updateCarGoodsState(@Param("id") int id,@Param("state") int state);

    /**
     * 修改商品数量
     * @param id
     * @param number
     * @return
     */
    @Update("update t_car set tcr_number = tcr_number + #{number} where tcr_id = #{id}")
    Integer updateCarGoodsNumber(@Param("id") int id,@Param("number") int number);

    /**
     * 删除选中的所有商品
     */
    @Delete("delete from t_car where tcr_uuid = #{uuid} and tcr_state = 1")
    Integer deleteCarGoodsList(@Param("uuid") String uuid);

    /**
     * 全选反选
     * @param uuid
     * @param state
     * @return
     */
    @Update("update t_car set tcr_state = #{state} where tcr_uuid = #{uuid}")
    Integer updateCarAllCarGoodsState(@Param("uuid")String uuid,@Param("state") int state);
}
