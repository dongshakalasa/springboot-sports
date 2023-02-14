package com.ves.Service.merchant;

import com.ves.Mapper.*;
import com.ves.pojo.Attribute;
import com.ves.pojo.Brand;
import com.ves.pojo.Product;
import com.ves.pojo.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    AttributeMapper attributeMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    SortMapper sortMapper;
    @Autowired
    BrandMapper brandMapper;

    /**
     * 根据页码和条数查询商家商品
     * @param uuid
     * @param page
     * @param limit
     * @return
     */
    public List<Product> selectMerchantList(String uuid, int page, int limit) {
        return productMapper.selectMerchantList(uuid,page,limit);
    }

    /**
     * 查询商品所有图片
     */
    public List<String> selectProductImg(int id) {
        return productMapper.selectProductImg(id);
    }

    /**
     * 查询商品可选属性名
     */
    public List<String> selectAttributeName(int id) {
        return attributeMapper.selectAttributeName(id);
    }

    /**
     * 查询商品可选属性值
     */
    public List<Attribute> selectAttributeList(int id, String name) {
        return attributeMapper.selectAttributeList(id,name);
    }

    /**
     * 查询商家商品数量
     * @param uuid
     * @return
     */
    public int selectMerchantNumber(String uuid) {
        return productMapper.selectMerchantNumber(uuid);
    }

    /**
     * 商品上架与下架
     * @param id
     * @param state
     * @return
     */
    public int updateProductState(int id,int state) {
        return productMapper.updateProductState(id,state);
    }

    /**
     * 查询全部属性名
     * @return
     */
    public List<String> selectAttributeNameList(){
        return attributeMapper.selectAttributeNameList();
    }

    /**
     * 查询一级分类名
     * @return
     */
    public List<String> selectCategoryName(){
        return categoryMapper.selectCategoryName();
    }

    /**
     * 查询二级分类
     * @param name
     * @return
     */
    public List<Sort> selectSortByCategoryName(String name) {
        return sortMapper.selectSortByCategoryName(name);
    }

    /**
     * 查询分类对应的品牌
     * @param name
     * @return
     */
    public List<Brand> selectBrandName(String name) {
        return brandMapper.selectBrandByCategoryName(name);
    }

    /**
     * 删除商品属性
     */
    public int deleteAttribute(int id) {
        return attributeMapper.deleteAttribute(id);
    }

    /**
     * 添加商品属性
     * @param id
     * @param name
     * @param value
     * @return
     */
    public int insertAttribute(int id,String name,String value) {
        return attributeMapper.insertAttribute(id,name,value);
    }

    /**
     * 修改产品
     * @param p
     * @return
     */
    public int updateProduct(Product p) {
        return productMapper.updateProduct(p);
    }

    // 删除商品图片
    public int deleteProductImg(int id) {
        return productMapper.deleteProductImg(id);
    }

    // 添加商品图片
    public int insertProductImg(int id,String img) {
        return productMapper.insertProductImg(id,img);
    }

    /**
     * 添加商品
     * @param product
     * @return
     */
    public int insertProductInfo(Product product) {
        return productMapper.insertProductInfo(product);
    }

}
