package com.ves.Service.Admin;

import com.ves.Mapper.MerchantMapper;
import com.ves.Mapper.UserMapper;
import com.ves.pojo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClientService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    MerchantMapper merchantMapper;

    /**
     * 查询所有用户分页
     * @return
     */
    public List<Map<String, Object>> selectUserInfoList(int page,int limit){
        return userMapper.selectUserInfoALl(page,limit);
    }

    /**
     * 用户数量
     * @return
     */
    public int selectUserInfoNumber(){
        return userMapper.getUserAllNumber();
    }


    /**
     * 查询全部商家
     * @return
     */
    public List<Merchant> selectMerchantInfoList(int page,int limit){
        return merchantMapper.getMerchantAll(page,limit);
    }

    /**
     * 商家数量
     * @return
     */
    public int selectMerchantInfoNumber(){
        return merchantMapper.getMerchantAllNumber();
    }

    /**
     * 商家申请
     * @param page
     * @param limit
     * @return
     */
    public List<Merchant> selectMerchantApply(int page,int limit) {
        return merchantMapper.getMerchantApply(page,limit);
    }
}
