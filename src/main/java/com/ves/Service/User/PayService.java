package com.ves.Service.User;

import com.ves.Mapper.AddressMapper;
import com.ves.Mapper.CarMapper;
import com.ves.Mapper.ProductMapper;
import com.ves.pojo.Address;
import com.ves.pojo.Cart;
import com.ves.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayService {

    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CarMapper carMapper;
    @Autowired
    ProductMapper productMapper;

    /**
     * 查询用户地址
     */
    public Address selectAddressOne(String uuid) {
        return addressMapper.selectUserAddressOne(uuid);
    }

    /**
     * 获取选中商品
     * @param uuid
     * @return
     */
    public List<Cart> selectCartInfo(String uuid) {
        return carMapper.selectCartInfoState(uuid);
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
}
