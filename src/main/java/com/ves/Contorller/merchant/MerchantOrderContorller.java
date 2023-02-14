package com.ves.Contorller.merchant;

import com.ves.Service.merchant.MerchantOrderService;
import com.ves.pojo.Order;
import com.ves.pojo.Product;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/merchant/order")
public class MerchantOrderContorller {

    @Autowired
    MerchantOrderService orderService;

    /**
     * 查询商家产品
     * @param page
     * @param limit
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/{page}/{limit}/{state}")
    public Result selectMerchantList(@PathVariable int page, @PathVariable int limit,@PathVariable int state, HttpSession session, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();

        // 获取商家uuid
        String token = request.getHeader("TOKEN");
        String userUuid = (String) session.getAttribute(token);
        //查询商家所有商品
        List<Product> products = orderService.selectMerchantList(userUuid);
        // 订单集合
        List<Order> list = new ArrayList<>();
        // 根据商品id，查询相关订单
        for(Product p : products) {
            int id = p.getTpt_id();
            List<Order> orders = new ArrayList<>();
            if(state == 0) {
                orders = orderService.selectOrderALlList(id);
            }else {
                orders = orderService.selectOrderList(id,state);
            }
            for (Order order : orders) {
                int gsid = order.getTor_gs_id();
                int crid = order.getTor_cr_id();
                Product product = orderService.selectCartProduct(gsid);
                List<String> attributes = orderService.selectCartAttribute(crid);
                order.setAttribute(attributes);
                order.setProduct(product);
                list.add(order);
            }
        }
        int tou = (page-1)*limit;
        int wei = tou + limit;
        if(wei > list.size()) {
            wei = list.size();
        }
        List<Order> list2 = list.subList(tou, wei);
        map.put("orderList",list2);
        map.put("total",list.size());
        return new Result(200,map);
    }
}
