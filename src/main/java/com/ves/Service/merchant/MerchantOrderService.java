package com.ves.Service.merchant;

import com.ves.Mapper.CarMapper;
import com.ves.Mapper.OrderMapper;
import com.ves.Mapper.ProductMapper;
import com.ves.pojo.Order;
import com.ves.pojo.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantOrderService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    OrderMapper orderMapper;

    /**
     * 查询商家所有商品
     * @param uuid
     * @return
     */
    public List<Product> selectMerchantList(String uuid) {
        return productMapper.selectMerchantAllList(uuid);
    }

    // 查询商家订单,状态分
    public List<Order> selectOrderList(int id,int state) {
        return orderMapper.selectMerchantOrderInfo(id,state);
    }

    // 全部订单
    public List<Order> selectOrderALlList(int id) {
        return orderMapper.selectMerchantOrderAllInfo(id);
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
}
