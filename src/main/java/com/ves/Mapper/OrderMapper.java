package com.ves.Mapper;

import com.ves.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    // 添加订单
    @Insert("insert into t_order(tor_uuid, tor_gs_id, tor_cr_id, tor_name, tor_address, tor_tel,tor_price,tor_number,tor_time) values(#{uuid},#{gsid},#{crid},#{name},#{address},#{tel},#{price},#{number},now()) ")
    int insertOrderInfo(Map orderInfo);

    // 根据用户uuid查询商品信息
    @Select("select tor_id, tor_gs_id, tor_cr_id, tor_name, tor_address, tor_tel, tor_time, tor_state,tor_price,tor_number from t_order where tor_uuid = #{uuid}")
    List<Order> selectOrderInfo(String uuid);

    // 更新订单状态
    @Update("update t_order set tor_state = #{state} where tor_id = #{id}")
    int updateOrderState(@Param("id")int id,@Param("state")int state);

    // 根据商品id，查询订单
    @Select("select tor_id, tor_gs_id, tor_cr_id, tor_name, tor_address, tor_tel, tor_time, tor_state,tor_price,tor_number from t_order where tor_gs_id = #{id} and tor_state = #{state}")
    List<Order> selectMerchantOrderInfo(@Param("id") int id,@Param("state")int state);

    // 根据商品id，查询全部订单
    @Select("select tor_id, tor_gs_id, tor_cr_id, tor_name, tor_address, tor_tel, tor_time, tor_state,tor_price,tor_number from t_order where tor_gs_id = #{id}")
    List<Order> selectMerchantOrderAllInfo(@Param("id") int id);
}
