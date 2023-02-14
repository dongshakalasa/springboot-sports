package com.ves.Service.User;

import com.ves.Mapper.ProductMapper;
import com.ves.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SearchService {
    @Autowired
    ProductMapper productMapper;

    public List<Product> selectSearchProduct(Map searchinfo) {
        return productMapper.selectSearchProduct(searchinfo);
    }
}
