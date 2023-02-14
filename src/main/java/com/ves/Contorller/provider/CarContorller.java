package com.ves.Contorller.provider;

import com.ves.Service.User.CarService;
import com.ves.pojo.Cart;
import com.ves.pojo.Product;
import com.ves.utils.Code;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CarContorller {

    @Autowired
    CarService carService;

    @PostMapping("/insert")
    public Result insertCart(@RequestBody Map carInfo, HttpSession session, HttpServletRequest request) {
        //整理参数
        String token = request.getHeader("TOKEN");
        String userUuid = (String) session.getAttribute(token);
        Cart cart = new Cart();
        cart.setTcr_gs_id((Integer) carInfo.get("id"));
        cart.setTcr_number((Integer) carInfo.get("number"));
        cart.setTcr_uuid(userUuid);
        //添加购物车
        carService.insertCart(cart);
        System.out.println(carInfo);
        int id = cart.getTcr_id();
        //商品属性
        List<String> attributeList = (List<String>) carInfo.get("attribute");
        for(String attribute : attributeList) {
            carService.insertCartAttribute(id,attribute);
        }
        return new Result(200,null,"ok");
    }

    @GetMapping("/info")
    public Result selectCartInfo(HttpSession session,HttpServletRequest request) {
        String token = request.getHeader("TOKEN");
        String userUUid = (String) session.getAttribute(token);
        List<Cart> carts = carService.selectCartInfo(userUUid);
        for(Cart cart : carts) {
            int id = cart.getTcr_id();
            int productId = cart.getTcr_gs_id();
            List<String> values = carService.selectCartAttribute(id);
            Product product = carService.selectCartProduct(productId);
            cart.setProduct(product);
            cart.setAttribute(values);
        }
        return new Result(200,carts,"ok");
    }

    @GetMapping("/checkCart/{id}/{state}")
    public Result updateCarGoodsState(@PathVariable int id,@PathVariable int state){
        //System.out.println(id+"--||--"+state);
        Integer flag = carService.updateCarGoodsState(id, state);
        if(flag == 1){
            return new Result(200,null, "ok");
        }else{
            return new Result(201,null, "error");
        }
    }

    /**
     * 修改购物车商品数量
     * @param id
     * @param number
     * @return
     */
    @GetMapping("/updateCart/{id}/{number}")
    public Result updateCarGoodsNumber(@PathVariable int id,@PathVariable int number) {
        Integer flag = carService.updateCarGoodsNumber(id, number);
        if(flag == 1){
            return new Result(Code.REQUEST_OK,null, "修改成功");
        }else{
            return new Result(Code.REQUEST_ERR,null, "修改失败，请稍后重试！");
        }
    }

    @DeleteMapping("/deleteCarts")
    public Result deleteCarGoodsList(HttpSession session,HttpServletRequest request) {
        String token = request.getHeader("token");
        String uuid = (String) session.getAttribute(token);
        Integer number = carService.deleteCarGoodsList(uuid);
        if(number > 0) {
            return new Result(200,null,"删除成功！");
        }else {
            return new Result(201,null,"删除失败！");
        }
    }

    @GetMapping("/all/{state}")
    public Result updateCarAllState(HttpSession session,HttpServletRequest request,@PathVariable int state) {
        String token = request.getHeader("TOKEN");
        String uuid = (String) session.getAttribute(token);
        int i = carService.updateCarAllCarGoodsState(uuid, state);
        if(i > 0) {
            return new Result(200,null,"ok");
        }
        return new Result(201,null,"error");
    }
}
