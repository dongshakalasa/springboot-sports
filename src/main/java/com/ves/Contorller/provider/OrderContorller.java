package com.ves.Contorller.provider;

import com.ves.Service.User.OrderService;
import com.ves.pojo.Order;
import com.ves.pojo.OrderRate;
import com.ves.pojo.Product;
import com.ves.utils.QiniuUtils;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderContorller {

    @Autowired
    OrderService orderService;

    @PostMapping("/insert")
    public Result insertOrder(@RequestBody List<Object> info, HttpServletRequest request, HttpSession session) {
        String token = request.getHeader("TOKEN");
        String userUUid = (String) session.getAttribute(token);
        int i = 0;
        for (Object order : info) {
            Map<String, Object> orderInfo = (Map<String, Object>) order;
            orderInfo.put("uuid", userUUid);
            int number = orderService.insertOrder(orderInfo);
            i += number;
        }
        if (i > 0) {
            orderService.deleteCarGoodsList(userUUid);
            return new Result(200, null, "ok");
        } else {
            return new Result(201, null, "error");
        }

    }


    @GetMapping("/info")
    public Result getOrder(HttpSession session, HttpServletRequest request) {
        String token = request.getHeader("TOKEN");
        String userUUid = (String) session.getAttribute(token);
        //获取订单信息
        List<Order> orderList = orderService.selectOrderList(userUUid);
        for (Order order : orderList) {
            int gsid = order.getTor_gs_id();
            int crid = order.getTor_cr_id();
            Product product = orderService.selectCartProduct(gsid);
            List<String> attributes = orderService.selectCartAttribute(crid);
            order.setAttribute(attributes);
            order.setProduct(product);
        }
        return new Result(200, orderList);
    }

    @GetMapping("/state/{id}/{state}")
    public Result updateOrderState(@PathVariable int id, @PathVariable int state) {
        int i = orderService.updateOrderState(id, state);
        if (i > 0) {
            return new Result(200, null);
        }
        return new Result(201, null);
    }

    @PostMapping("/rate")
    public Result save(@PathVariable MultipartFile myfile) throws IOException {
        QiniuUtils.upload2Qiniu(myfile.getBytes(),myfile.getOriginalFilename());
        return new Result(200,"ok");
    }

    @PostMapping("/orderRate")
    public Result insertOrderRate(@RequestBody OrderRate rateInfo, HttpServletRequest request, HttpSession session) {
        String token = request.getHeader("TOKEN");
        String userUUid = (String) session.getAttribute(token);
        rateInfo.setOe_uuid(userUUid);
        int i = orderService.insertOrderRate(rateInfo);
        int id = rateInfo.getOe_id();
        for(String img : rateInfo.getImgs()) {
            System.out.println(1);
            orderService.insetOrderRateImg(id,img);
        }
        if(i>0) {
            return new Result(200,null);
        }
        return new Result(201,null);
    }
}
