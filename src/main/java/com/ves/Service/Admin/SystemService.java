package com.ves.Service.Admin;

import com.ves.Mapper.BrandMapper;
import com.ves.Mapper.RollMapper;
import com.ves.Mapper.SortMapper;
import com.ves.pojo.Brand;
import com.ves.pojo.Roll;
import com.ves.pojo.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemService {

    @Autowired
    BrandMapper brandMapper;
    @Autowired
    SortMapper sortMapper;
    @Autowired
    RollMapper rollMapper;

    // brand
    /**
     * 全部品牌
     * @param name
     * @param page
     * @param limit
     * @return
     */
    public List<Brand> selectBrandByCategoryNameLimit(String name,int page,int limit) {
        return brandMapper.selectBrandByCategoryNameLimit(name,page,limit);
    }

    // platform
    /**
     *小类分页
     * @param name
     * @param page
     * @param limit
     * @return
     */
    public List<Sort> selectSortLimit(String name,int page,int limit){
        return sortMapper.selectSortLimit(name,page,limit);
    }
    /**
     * 小类数量
     * @param name
     * @return
     */
    public int selectSortLimitNumber(String name) {
        List<Sort> sorts = sortMapper.selectSortByCategoryName(name);
        return sorts.size();
    }
    /**
     * 删除属性
     * @param id
     * @return
     */
    public int delelteSort(int id) {
        return sortMapper.deleteSort(id);
    }


    //roll
    /**
     * 获取滚动图片
     * @return
     */
    public List<Roll> selectRollList() {
        return rollMapper.selectImgRoll();
    }

    /**
     * 删除滚动图片
     * @param id
     * @return
     */
    public int deleteRoll(int id) {
        return rollMapper.deleteRoll(id);
    }
    public int insertRoll(String name) {
        return rollMapper.insertRoll(name);
    }
}
