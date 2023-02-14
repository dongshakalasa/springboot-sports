package com.ves.Contorller.provider;

import com.ves.Service.User.SearchService;
import com.ves.pojo.Product;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchContorller {

    @Autowired
    SearchService searchService;

    @RequestMapping("/list")
    public Result selectProduct(@RequestBody Map search) {
        //String keyword = (String) search.get("keyword");
        //String sort = (String) search.get("sort");
        //String brand = (String) search.get("brand");
        //String category = (String) search.get("category");
        //int order = (int) search.get("order");
        //System.out.println(keyword);
        //System.out.println(sort);
        //System.out.println(brand);
        //System.out.println(category);
        //System.out.println(order);
        List<Product> products = searchService.selectSearchProduct(search);
        return new Result(200,products,"请求成功");
    }
}
