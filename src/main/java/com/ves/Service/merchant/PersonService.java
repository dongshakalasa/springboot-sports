package com.ves.Service.merchant;

import com.ves.Mapper.MerchantMapper;
import com.ves.pojo.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    MerchantMapper merchantMapper;

    public Merchant selectMerchantInfo(String uuid){
        return merchantMapper.getMerchant(uuid);
    }
}
