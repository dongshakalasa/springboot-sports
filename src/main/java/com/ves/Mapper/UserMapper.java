package com.ves.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    /**
     * 判断是否存在账户
     */
    @Select("select tur_uuid from t_user where tur_account = #{account} and tur_state != 2")
    String judgeAccount(String account);

    /**
     * 判断是否存在账户
     */
    @Select("select tur_uuid from t_user where tur_account = #{account} and tur_state = 3")
    String judgeAccountMerChant(String account);

    /**
     * 注册
     */
    @Insert("insert t_user(tur_uuid, tur_name, tur_account, tur_password, tur_img) values(#{uuid},#{name},#{account},#{password},#{imgUlr})")
    Integer createUser(@Param("name") String name, @Param("account") String account, @Param("password") String password, @Param("uuid") String uuid, @Param("imgUlr") String imgUlr);

    /**
     * 查询密码
     */
    @Select("select tur_password from t_user where tur_account = #{account};")
    String getPswd(String account);

    /**
     * 查询uuid
     * @param account
     * @return
     */
    @Select("select tur_uuid from t_user where tur_account = #{account}")
    String getUUid(String account);

    /**
     * 根据uuid查询用户信息
     * @param uuid
     * @return
     */
    @Select("select tur_name, tur_account, tur_img from t_user where tur_uuid = #{uuid};")
    Map<String,Object> getUserInfo(String uuid);

    // admin
    @Select("select tur_id,tur_name,tur_account, tur_img, tur_state from t_user limit #{page},#{limit}")
    List<Map<String,Object>> selectUserInfoALl(@Param("page")int page,@Param("limit")int limit);

    /**
     * 用户数量
     * @return
     */
    @Select("select COUNT(tur_id) from t_user")
    int getUserAllNumber();
}
