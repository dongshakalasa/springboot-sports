package com.ves.Service.User;

import com.ves.Mapper.AddressMapper;
import com.ves.Mapper.UserMapper;
import com.ves.pojo.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    AddressMapper addressMapper;

    /**
     * 判断是否存在此账户
     */
    public Boolean judgeAccount(String account){
        return userMapper.judgeAccount(account)==null;
    }
    /**
     * 注册
     * @param account
     * @param password
     * @param uuid
     * @param imgUrl
     * @return
     */
    public Boolean createUser(String account,String password,String uuid,String imgUrl,String name){
        Integer n = userMapper.createUser(name,account,password,uuid,imgUrl);
        if(n > 0) {
            return true;
        }else {
            return false;
        }
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
     * 根据uuid查询用户信息
     * @param uuid
     * @return
     */
    public Map<String,Object> getUserInfo(String uuid){
        return userMapper.getUserInfo(uuid);
    }

    /**
     * 根据uuid查询用户地址信息
     * @param uuid
     * @return
     */
    public List<Address> getUserAddress(String uuid) {
        return addressMapper.selectUserAddress(uuid);
    }

    /**
     * 根据id查询用户地址信息
     * @param id
     * @return
     */
    public Address getUserAddressInfo(int id) {
        return addressMapper.selectUserAddressById(id);
    }

    /**
     * 更新用户地址信息
     * @param addressInfo
     * @return
     */
    public int updateAddressInfo(Map addressInfo) {
        return addressMapper.updateAddressInfo(addressInfo);
    }

    /**
     * 删除地址
     * @param id
     * @return
     */
    public int deleteAddress(int id) {
        return addressMapper.deleteAddress(id);
    }

}
