package com.ves.Contorller.provider;

import com.ves.Service.User.HomeService;
import com.ves.pojo.*;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeContorller {

    @Autowired
    HomeService homeService;

    @GetMapping("/init")
    public Result init(){
        //返回数据集
        Map<String,Object> returMap = new HashMap<>();
        /**
         * 获取所有分类信息
         */
        List<Category> categorys = homeService.selectCategoryName();
        for (Category category : categorys) {
        String name = category.getTcy_name();
        //获取子分类
        List<Sort> sort = homeService.selectSortListByCategoryName(name);
        //获取分类品牌
        List<Brand> brand = homeService.selectBrandListByCategoryName(name);
        // 热销产品
        List<Product> productSales = homeService.selectProduceByCategoryNameOrderSales(name);
        // 推荐产品
        List<Product> productRate = homeService.selectProduceByCategoryNameOrderRate(name);
        category.setSort(sort);
        category.setBrand(brand);
        category.setProduceSales(productSales);
        category.setProduceRate(productRate);
    }
        /**
         * 推荐信息
         */
        List<Product> productRate = homeService.selectProductOrderRate();
        List<Product> productSales = homeService.selectProductOrderSales();

        /**
         * 轮播图
         */
        List<String> imgs = homeService.selectRollImgs();

        /**
         * 公告
         */
        announcement announcement = homeService.selectAnnouncement();

        returMap.put("productRate",productRate);
        returMap.put("productSales",productSales);
        returMap.put("categorys",categorys);
        returMap.put("rollList",imgs);
        returMap.put("announcement",announcement);

        return new Result(200,returMap,"请求成功");
    }

    @GetMapping("/recommend")
    public Result recommend(){
        List<Product> products = homeService.selectRecommend();
        return new Result(200,products,"ok");
    }
}
