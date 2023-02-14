package com.ves.Contorller.provider;

import com.ves.Service.User.CollectService;
import com.ves.pojo.Product;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectContorller {

    @Autowired
    CollectService collectService;

    /**
     * 查询商品信息
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/info")
    public Result selectCollectAll(HttpSession session, HttpServletRequest request){
        String token = request.getHeader("TOKEN");
        String uuid = (String) session.getAttribute(token);
        List<Integer> ids = collectService.selectCollectAll(uuid);
        List<Product> products = new ArrayList<>();
        for(int id : ids) {
            Product product = collectService.selectProductInfo(id);
            products.add(product);
        }
        return new Result(200,products,"ok");
    }
}
