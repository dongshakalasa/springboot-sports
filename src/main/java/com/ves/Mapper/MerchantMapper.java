package com.ves.Mapper;

import com.ves.pojo.Merchant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MerchantMapper {

    /**
     * 查询商户信息
     * @param uuid
     * @return
     */
    @Select("SELECT tmt_id, tmt_name, tmt_uuid, tmt_idcard, tmt_tel, tmt_img,tmt_time from t_merchant where tmt_uuid = #{uuid}")
    Merchant getMerchant(String uuid);

    /**
     * 查询全部商家分页
     * @return
     */
    @Select("select tmt_id, tmt_name, tmt_idcard, tmt_tel, tmt_img, tmt_time, tmt_state from t_merchant where tmt_state = 0 or tmt_state = 3 limit #{page},#{limit}")
    List<Merchant> getMerchantAll(@Param("page")int page, @Param("limit")int limit);

    /**
     * 商家数量
     * @return
     */
    @Select("select COUNT(tmt_id) from t_merchant where tmt_state = 0 or tmt_state = 3")
    int getMerchantAllNumber();

    /**
     * 查询全部商家分页
     * @return
     */
    @Select("select tmt_id, tmt_name, tmt_idcard, tmt_tel, tmt_img, tmt_time, tmt_state from t_merchant where tmt_state = 1")
    List<Merchant> getMerchantApply(@Param("page")int page, @Param("limit")int limit);

    /**
     * 查询全部商家分页
     * @return
     */
    @Select("select count(tmt_id) from t_merchant where tmt_state = 1")
    int getMerchantApplyNumber();
}
