package com.ves.Service.merchant;

import com.ves.Mapper.MerchantMapper;
import com.ves.Mapper.UserMapper;
import com.ves.pojo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    MerchantMapper merchantMapper;

    /**
     * 判断是否存在此账户
     */
    public Boolean judgeAccountMerChant(String account){
        return userMapper.judgeAccountMerChant(account)==null;
    }

    /**
     * 获取用户密码
     */
    public String getPswd(String account){
        return userMapper.getPswd(account);
    }

    /**
     * 获取uuid
     */
    public String getUuid(String account){
        return userMapper.getUUid(account);
    }

    /**
     * 根据uuid获取商户信息
     * @param uuid
     * @return
     */
    public Merchant getMerchantInfo(String uuid) {
        return merchantMapper.getMerchant(uuid);
    }

}
