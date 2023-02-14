package com.ves.Service.User;

import com.ves.Mapper.*;
import com.ves.pojo.Attribute;
import com.ves.pojo.OrderRate;
import com.ves.pojo.Product;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DetailService {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    AttributeMapper attributeMapper;
    @Autowired
    OrderRateMapper orderRateMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CollectMapper collectMapper;

    /**
     * 根据id查询商品信息
     */
    public Product selectProductById(int id) {
        return productMapper.selectProductById(id);
    }

    /**
     * 查询品牌图片
     */
    public String selectBrandImg(String brand) {
        return brandMapper.selectBrandImg(brand);
    }

    /**
     * 查询商品所有图片
     */
    public List<String> selectProductImg(int id) {
        return productMapper.selectProductImg(id);
    }

    /**
     * 查询商品可选属性名
     */
    public List<String> selectAttributeName(int id) {
        return attributeMapper.selectAttributeName(id);
    }

    /**
     * 查询商品可选属性值
     */
    public List<Attribute> selectAttributeList(int id,String name) {
        return attributeMapper.selectAttributeList(id,name);
    }

    /**
     * 查询商品评价信息
     */
    public List<OrderRate> selectOrderRate(int id) {
        return  orderRateMapper.selectOrderRate(id);
    }

    /**
     * 查询评价图片
     */
    public List<String> selectOrderRateImg(int oe_id) {
        return orderRateMapper.selectOrderRateImg(oe_id);
    }

    /**
     * 查询评价人信息
     */
    public Map<String,Object> getUserInfo(String uuid) {
        return userMapper.getUserInfo(uuid);
    }

    /**
     * 判断是否被收藏
     */
    public Boolean judgeCollect(String uuid,int id) {
        int flag = collectMapper.selectCollectJudge(uuid, id);
        if(flag > 0) {
            return true;
        }
        return false;
    }

    /**
     * 添加收藏
     */
    public Boolean insertCollect(String uuid,int id) {
        int flag = collectMapper.insertCollect(uuid, id);
        if(flag > 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除收藏
     */
    public Boolean deleteCollect(String uuid,int id) {
        int flag = collectMapper.deleteCollect(uuid, id);
        if(flag > 0) {
            return true;
        }
        return false;
    }

}
