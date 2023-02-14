package com.ves.Mapper;

import com.ves.pojo.Roll;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RollMapper {

    @Select("select rl_id,rl_img from t_roll")
    List<Roll> selectImgRoll();

    @Select("select rl_img from t_roll;")
    List<String> selectImgs();

    @Delete("delete from t_roll where rl_id = #{id}")
    int deleteRoll(int id);

    @Insert("insert into t_roll(rl_img) values(#{name})")
    int insertRoll(String name);
}
