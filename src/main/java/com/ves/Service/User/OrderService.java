package com.ves.Service.User;

import com.ves.Mapper.CarMapper;
import com.ves.Mapper.OrderMapper;
import com.ves.Mapper.OrderRateMapper;
import com.ves.Mapper.ProductMapper;
import com.ves.pojo.Order;
import com.ves.pojo.OrderRate;
import com.ves.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    @Autowired
    CarMapper carMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    OrderRateMapper orderRateMapper;

    /**
     * 清除购物车
     * @param uuid
     */
    public void deleteCarGoodsList(String uuid){
        carMapper.deleteCarGoodsList(uuid);
    }

    // 添加订单信息
    public int insertOrder(Map orderInfo) {
        return orderMapper.insertOrderInfo(orderInfo);
    }

    // 查询用户订单
    public List<Order> selectOrderList(String uuid) {
        return orderMapper.selectOrderInfo(uuid);
    }

    // 查询商品对应属性
    public List<String> selectCartAttribute(int id){
        return carMapper.selectCartAttribute(id);
    }

    // 查询商品详细信息
    public Product selectCartProduct(int id) {
        return productMapper.selectProductById(id);
    }

    // 更新状态
    public int updateOrderState(int id,int state) {
        return orderMapper.updateOrderState(id,state);
    }

    // 商品评价
    public int insertOrderRate(OrderRate orderInfo) {
        return orderRateMapper.insertOrderRate(orderInfo);
    }

    // 评价图片
    public void insetOrderRateImg(int id,String img) {
        orderRateMapper.insertOrderRateImg(id,img);
    }
}
