package com.ves.Service.User;

import com.ves.Mapper.CollectMapper;
import com.ves.Mapper.ProductMapper;
import com.ves.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectService {

    @Autowired
    CollectMapper collectMapper;
    @Autowired
    ProductMapper productMapper;

    /**
     * 查询用户全部收藏
     * @param uuid
     * @return
     */
    public List<Integer> selectCollectAll(String uuid) {
        return collectMapper.selectCollectAll(uuid);
    }

    /**
     * 查询商品信息
     * @param id
     * @return
     */
    public Product selectProductInfo(int id) {
        return productMapper.selectProductById(id);
    }


}
