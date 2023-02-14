package com.ves.Contorller.provider;

import com.ves.Service.User.DetailService;
import com.ves.pojo.Attribute;
import com.ves.pojo.OrderRate;
import com.ves.pojo.Product;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/detail")
public class DetailContorller {

    @Autowired
    DetailService detailService;

    /**
     * 初始化商品详情
     * @param id
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/{id}")
    public Result productDetail(@PathVariable int id, HttpSession session, HttpServletRequest request){
        //返回集
        Map<String,Object> returnMap = new HashMap<>();
        // 查询商品信息
        String token = request.getHeader("TOKEN");
        String userUuid = (String) session.getAttribute(token);
        Product product = detailService.selectProductById(id);
        List<String> imgs = detailService.selectProductImg(id);
        String brand = detailService.selectBrandImg(product.getTpt_brand());
        Boolean flag = detailService.judgeCollect(userUuid, product.getTpt_id());
        product.setTpt_brand_img(brand);
        product.setImgs(imgs);
        product.setTpt_collect(flag);
        //商品可选属性
        List<Object> attributeList = new ArrayList<>();
        List<String> names = detailService.selectAttributeName(id);
        for(String name : names) {
            Map<String,Object> attributeMap = new HashMap<>();
            List<Attribute> attributes = detailService.selectAttributeList(id, name);
            attributeMap.put("name",name);
            attributeMap.put("value",attributes);
            attributeList.add(attributeMap);
        }
        //评价信息
        List<OrderRate> orderRates = detailService.selectOrderRate(id);
        for(OrderRate orderRate : orderRates) {
            int orderRateOeId = orderRate.getOe_id();
            String orderRateUUid = orderRate.getOe_uuid();
            List<String> imgList = detailService.selectOrderRateImg(orderRateOeId);
            Map userInfo = detailService.getUserInfo(orderRateUUid);
            orderRate.setImgs(imgList);
            orderRate.setUserInfo(userInfo);
        }

        //返回值整理
        returnMap.put("productList",product);
        returnMap.put("productAttributeList",attributeList);
        returnMap.put("orderRateList",orderRates);
        return new Result(200,returnMap,"ok");
    }

    /**
     * 收藏
     * @param id
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/insertCollect/{id}")
    public Result insertCollect(@PathVariable int id, HttpSession session, HttpServletRequest request) {
        String token = request.getHeader("TOKEN");
        String userUUid = (String) session.getAttribute(token);
        Boolean flag = detailService.insertCollect(userUUid, id);
        if (flag) {

            return new Result(200,null,"ok");
        }
        return new Result(201,null,"error");
    }

    /**
     * 取消收藏
     * @param id
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/deleteCollect/{id}")
    public Result deleteCollect(@PathVariable int id, HttpSession session, HttpServletRequest request) {
        String token = request.getHeader("TOKEN");
        String userUUid = (String) session.getAttribute(token);
        Boolean flag = detailService.deleteCollect(userUUid, id);
        if (flag) {

            return new Result(200,null,"ok");
        }
        return new Result(201,null,"error");
    }
}
