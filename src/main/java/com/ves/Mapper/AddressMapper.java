package com.ves.Mapper;

import com.ves.pojo.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface AddressMapper {

    /**
     * 根据用户uuid获取地址信息
     * @param uuid
     * @return
     */
    @Select("select id, name, tel, province, city, county, addressDetail, isDefault from t_address where uuid = #{uuid};")
    List<Address> selectUserAddress(String uuid);

    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    @Select("select id, name, tel, province, city, county, addressDetail from t_address where id = #{id} ;")
    Address selectUserAddressById(int id);

    /**
     * 更新地址
     * @param addressInfo
     * @return
     */
    @Update("update t_address set name = #{name},tel = #{tel},province = #{province},city = #{city}, county = #{county},addressDetail = #{addressDetail} where id = #{id}")
    int updateAddressInfo(Map addressInfo);

    /**
     * 删除地址
     * @param id
     * @return
     */
    @Delete("delete from t_address where id = #{id}")
    int deleteAddress(int id);

    /**
     * 第一条地址信息
     */
    @Select("select id, uuid, name, tel, province, city, county, addressDetail from t_address where uuid = #{uuid} limit 1;")
    Address selectUserAddressOne(String uuid);
}
