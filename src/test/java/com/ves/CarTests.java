package com.ves;

import com.ves.Mapper.*;
import com.ves.pojo.Attribute;
import com.ves.pojo.Order;
import com.ves.pojo.OrderRate;
import com.ves.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CarTests {
    @Autowired
    SortMapper sortMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AttributeMapper attributeMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderRateMapper orderRateMapper;
    @Autowired
    MerchantMapper merchantMapper;

    //@Test
    //void info(){
    //    System.out.println(merchantMapper.getInfo());
    //}
    @Test
    void bbbb(){
        // product对象
        Product product = new Product();
        //tpt_img
        product.setTpt_img("url");
        //tpt_price
        product.setTpt_price("tpt_price");
        //tpt_text
        product.setTpt_text("tpt_text");
        //tpt_brand
        product.setTpt_brand("tpt_brand");
        //tpt_category
        product.setTpt_category("tpt_category");
        //tpt_sort
        product.setTpt_sort("tpt_sort");
        //tpt_store
        product.setTpt_store(889);
        //tpt_tmt_uuid
        product.setTpt_tmt_uuid("userUUid");
        productMapper.insertProductInfo(product);
        System.out.println(product.getTpt_id());
    }

    @Test
    void aaa(){
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        System.out.println(list);
        List<Integer> list2 = list.subList(2, 4);
        System.out.println(list2);
    }

    @Test
    void ccc(){
        List<Product> products = productMapper.selectMerchantList("fb6d7513-e610-4260-ba66-43949279b281", 2, 5);
        System.out.println(products);
    }

    @Test
    void merchant(){
        System.out.println(merchantMapper.getMerchant("fb6d7513-e610-4260-ba66-fb6d7513-e610-4260-ba66-43949279b281"));
    }

    @Test
    void orderRate(){
        OrderRate or = new OrderRate();
        or.setOe_uuid("1223355");
        or.setOe_rate(3);
        or.setOe_gs_id(1);
        or.setOe_text("6666");
        orderRateMapper.insertOrderRate(or);
        System.out.println(or.getOe_id());
    }

    @Test
    void orderInfo(){
        List<Order> order = orderMapper.selectOrderInfo("fb6d7513-e610-4260-ba66-43949279b281");
        System.out.println(order);
    }

    @Test
    void name(){
        List<String> a = attributeMapper.selectAttributeName(0);
        System.out.println(a);
    }

    @Test
    void create(){
        Integer user = userMapper.createUser("945405077@qq.com", "945405077@qq.com", "147258369", "sdfgsdfgsdf", "http:sdklfasdlfasdlk.com");
        System.out.println(user);


    }

    @Test
    void selectSearch() {
        Map<String,Object> map = new HashMap<>();
        //map.put("keyword","测试");
        map.put("sort","足球");
        List<Product> products = productMapper.selectSearchProduct(map);
        System.out.println(products);
    }


    @Test
    void updateNumber(){
        //System.out.println(sortMapper.getAll());
    }

}
