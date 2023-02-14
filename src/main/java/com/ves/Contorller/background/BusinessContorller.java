package com.ves.Contorller.background;

import com.ves.Service.Admin.BusinessService;
import com.ves.pojo.Merchant;
import com.ves.pojo.announcement;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/business")
public class BusinessContorller {

    @Autowired
    BusinessService businessService;

    // 商家申请
    @GetMapping("/merchant/{page}/{limit}")
    public Result selectMerchantApply(@PathVariable int page, @PathVariable int limit){
        Map<String,Object> map = new HashMap<>();
        int pages = (page - 1) * limit;
        List<Merchant> merchants = businessService.selectMerchantApply(pages, limit);
        int total = businessService.selectMerchantApplyNumber();
        map.put("merchants",merchants);
        map.put("total",total);
        return new Result(200,map,"ok");
    }

    /**
     * 全部公告
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/announcement/{page}/{limit}")
    public Result selectAnnouncement(@PathVariable int page, @PathVariable int limit) {
        Map<String,Object> map = new HashMap<>();
        int pages = (page - 1) * limit;
        List<announcement> announcements = businessService.selectAnnouncement(pages, limit);
        int total = businessService.selectAnnouncementNumber();
        map.put("announcements",announcements);
        map.put("total",total);
        return new Result(200,map,"ok");
    }
}
