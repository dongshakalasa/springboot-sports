package com.ves.Contorller.provider;

import com.ves.Service.User.PayService;
import com.ves.pojo.Address;
import com.ves.pojo.Cart;
import com.ves.pojo.Product;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pay")
public class PayContorller {

    @Autowired
    PayService payService;

    /**
     * 初始信息
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/info")
    public Result selectCartInfo(HttpSession session, HttpServletRequest request) {
        Map<String,Object> returnMap = new HashMap();
        //解析uuid
        String token = request.getHeader("TOKEN");
        String userUUid = (String) session.getAttribute(token);
        //获取购物车选中商品
        List<Cart> carts = payService.selectCartInfo(userUUid);
        for(Cart cart : carts) {
            int id = cart.getTcr_id();
            int productId = cart.getTcr_gs_id();
            List<String> values = payService.selectCartAttribute(id);
            Product product = payService.selectCartProduct(productId);
            cart.setProduct(product);
            cart.setAttribute(values);
        }
        // 查询用户地址
        Address address = payService.selectAddressOne(userUUid);
        //整理返回
        returnMap.put("productList",carts);
        returnMap.put("address",address);
        return new Result(200,returnMap,"ok");
    }
}
