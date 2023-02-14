package com.ves.Contorller.background;

import com.ves.Service.Admin.SystemService;
import com.ves.pojo.Brand;
import com.ves.pojo.Roll;
import com.ves.pojo.Sort;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/system")
public class SystemContorller {

    @Autowired
    SystemService systemService;

    // brand
    @GetMapping("/brand/category/{page}/{limit}/{name}")
    public Result getBrandCategory(@PathVariable()int page, @PathVariable()int limit,@PathVariable()String name) {
        int pages = (page - 1) * limit;
        List<Brand> brands = systemService.selectBrandByCategoryNameLimit(name, pages, limit);
        return new Result(200,brands,"ok");
    }

    // platform
    @GetMapping("/platform/category/{page}/{limit}/{name}")
    public Result getPlatformCategory(@PathVariable()int page, @PathVariable()int limit,@PathVariable()String name) {
        Map<String,Object> map = new HashMap<>();
        int pages = (page - 1) * limit;
        List<Sort> sorts = systemService.selectSortLimit(name, pages, limit);
        int total = systemService.selectSortLimitNumber(name);
        map.put("sortList",sorts);
        map.put("total",total);
        return new Result(200,map,"ok");
    }
    @GetMapping("/platform/、/{id}")
    public Result deleteSort(@PathVariable int id) {
        int flag = systemService.delelteSort(id);
        if(flag > 0) {
            return new Result(200,null,"ok");
        }else {
            return new Result(201,null,"error");
        }
    }

    // roll
    @GetMapping("/roll")
    public Result getRollImgs() {
        List<Roll> rolls = systemService.selectRollList();
        return new Result(200,rolls,"ok");
    }

    @GetMapping("/roll/delete/{id}")
    public Result deleteRoll(@PathVariable int id) {
        int flag = systemService.deleteRoll(id);
        if(flag > 0) {
            return new Result(200,null,"ok");
        }else {
            return new Result(201,null,"error");
        }
    }

    @PostMapping("/roll/insert")
    public Result insertRoll(@RequestBody Map insertImg) {
        String name = (String) insertImg.get("img");
        int flag = systemService.insertRoll(name);
        if(flag >0) {
            return new Result(200,null,"ok");
        }else {
            return new Result(200,null,"error");
        }
    }
}
