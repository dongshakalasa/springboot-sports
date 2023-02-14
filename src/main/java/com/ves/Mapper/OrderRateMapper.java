package com.ves.Mapper;

import com.ves.pojo.OrderRate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderRateMapper {

    /**
     * 查询商品评价
     * @param id
     * @return
     */
    @Select("select oe_id, oe_uuid, oe_rate, oe_gs_id, oe_text, oe_date from t_orderrate where oe_gs_id = #{id}")
    List<OrderRate> selectOrderRate(int id);

    /**
     * 查询评价所有图片
     * @param oe_id
     * @return
     */
    @Select("select oe_il_img from orderrate_imgs where oeil_id = #{id};")
    List<String> selectOrderRateImg(int oe_id);

    int insertOrderRate(OrderRate orderInfo);

    @Insert("insert into orderrate_imgs(oeil_id, oe_il_img) values(#{id},#{img}) ")
    void insertOrderRateImg(@Param("id")int id,@Param("img")String img);
}
