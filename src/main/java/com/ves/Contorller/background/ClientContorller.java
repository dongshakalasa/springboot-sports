package com.ves.Contorller.background;

import com.ves.Service.Admin.ClientService;
import com.ves.pojo.Merchant;
import com.ves.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/client")
public class ClientContorller {

    @Autowired
    ClientService clientService;

    // 商家管理
    @GetMapping("/merchant/{page}/{limit}")
    public Result selectMerchantInfoList(@PathVariable int page,@PathVariable int limit){
        Map<String,Object> map = new HashMap<>();
        int pages = (page - 1) * limit;
        List<Merchant> merchants = clientService.selectMerchantInfoList(pages,limit);
        int total = clientService.selectUserInfoNumber();
        map.put("merchants",merchants);
        map.put("total",total);
        return new Result(200,map,"ok");
    }

    // 用户管理
    @GetMapping("/user/{page}/{limit}")
    public Result selectUserInfoList(@PathVariable int page,@PathVariable int limit){
        Map<String,Object> map = new HashMap<>();
        int pages = (page - 1) * limit;
        List<Map<String, Object>> userInfoList = clientService.selectUserInfoList(pages,limit);
        int total = clientService.selectUserInfoNumber();
        map.put("userInfoList",userInfoList);
        map.put("total",total);
        return new Result(200,map,"ok");
    }
}
