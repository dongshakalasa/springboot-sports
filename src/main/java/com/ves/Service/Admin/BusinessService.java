package com.ves.Service.Admin;

import com.ves.Mapper.AnnouncementMapper;
import com.ves.Mapper.MerchantMapper;
import com.ves.Mapper.UserMapper;
import com.ves.pojo.Merchant;
import com.ves.pojo.announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BusinessService {

    @Autowired
    MerchantMapper merchantMapper;
    @Autowired
    AnnouncementMapper announcementMapper;

    /**
     * 商家申请
     * @param page
     * @param limit
     * @return
     */
    public List<Merchant> selectMerchantApply(int page,int limit) {
        return merchantMapper.getMerchantApply(page,limit);
    }

    public int selectMerchantApplyNumber(){
        return merchantMapper.getMerchantApplyNumber();
    }

    /**
     * 全部公告
     * @return
     */
    public List<announcement> selectAnnouncement(int page,int limit){
         return announcementMapper.selectAnnouncementAll(page,limit);
    }

    /**
     * 全部公告数
     * @return
     */
    public int selectAnnouncementNumber(){
        return announcementMapper.selectAnnouncementAllNumber();
    }
}
