package com.ves.Service.User;

import com.ves.Mapper.*;
import com.ves.pojo.*;
import com.ves.pojo.announcement;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    SortMapper sortMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    RollMapper rollMapper;
    @Autowired
    AnnouncementMapper announcementMapper;

    /**
     * 查询大分类的名称
     * @return
     */
    public List<Category> selectCategoryName() {
        return categoryMapper.getCategoryName();
    }

    /**
     * 根据大分类名查询小分类信息
     * @param name
     * @return
     */
    public List<Sort> selectSortListByCategoryName(String name){
        return sortMapper.selectSortByCategoryName(name);
    }

    /**
     * 根据分类名获取分类品牌
     * @param name
     * @return
     */
    public List<Brand> selectBrandListByCategoryName(String name) {
        return brandMapper.selectBrandByCategoryName(name);
    }

    /**
     * 查询评价前十的产品
     * @return
     */
    public List<Product> selectProductOrderRate() {
        return productMapper.selectProductOrderRate();
    }

    /**
     * 查询销量前十的产品
     * @return
     */
    public List<Product> selectProductOrderSales() {
        return productMapper.selectProductOrderSales();
    }

    /**
     * 查询分类热卖产品
     * @param name
     * @return
     */
    public List<Product> selectProduceByCategoryNameOrderSales(String name) {
        return productMapper.selectProduceByCategoryNameOrderSales(name);
    }

    /**
     * 分类推荐产品
     * @param name
     * @return
     */
    public List<Product> selectProduceByCategoryNameOrderRate(String name) {
        return productMapper.selectProduceByCategoryNameOrderRate(name);
    }

    /**
     * 轮播图
     */
    public List<String> selectRollImgs(){
        return rollMapper.selectImgs();
    }

    /**
     * 公告
     */
    public announcement selectAnnouncement(){
        return announcementMapper.selectAnnouncement();
    }

    /**
     * 推荐产品
     */
    public List<Product> selectRecommend(){
        return productMapper.selectRecommend();
    }

}
