package com.ves.Service.User;

import com.ves.Mapper.CarMapper;
import com.ves.Mapper.ProductMapper;
import com.ves.pojo.Cart;
import com.ves.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    @Autowired
    CarMapper carMapper;
    @Autowired
    ProductMapper productMapper;

    /**
     * 添加购物车
     * @param cart
     */
    public void insertCart(Cart cart) {
        carMapper.insertCart(cart);
    }

    /**
     * 添加商品属性
     * @param id
     * @param name
     */
    public void insertCartAttribute(int id,String name){
        carMapper.insertCartAttribute(id,name);
    }

    /**
     * 查询购物车商品
     * @param uuid
     * @return
     */
    public List<Cart> selectCartInfo(String uuid) {
        return carMapper.selectCartInfo(uuid);
    }

    /**
     * 查询商品对应属性
     * @param id
     * @return
     */
    public List<String> selectCartAttribute(int id){
        return carMapper.selectCartAttribute(id);
    }

    /**
     * 查询商品详细信息
     */
    public Product selectCartProduct(int id) {
        return productMapper.selectProductById(id);
    }

    /**
     * 更改商品中状态
     * @param id
     * @param state
     * @return
     */
    public int updateCarGoodsState(int id,int state){
        return carMapper.updateCarGoodsState(id,state);
    }

    /**
     * 更改商品数量
     * @param id
     * @param number
     * @return
     */
    public int updateCarGoodsNumber(int id,int number) {
        return carMapper.updateCarGoodsNumber(id,number);
    }

    /**
     * 删除选中商品
     * @param uuid
     * @return
     */
    public int deleteCarGoodsList(String uuid){
        return carMapper.deleteCarGoodsList(uuid);
    }

    /**
     * 全选反选
     * @param uuid
     * @param state
     * @return
     */
    public int updateCarAllCarGoodsState(String uuid,int state){
        return carMapper.updateCarAllCarGoodsState(uuid,state);
    }
}
