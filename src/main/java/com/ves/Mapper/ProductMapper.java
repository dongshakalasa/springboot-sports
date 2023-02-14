package com.ves.Mapper;

import com.ves.pojo.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    /**
     * 查询推荐产品
     */
    @Select("select * from t_product where tpt_recommend = 1")
    List<Product> selectRecommend();

    /**
     * 查询评价前十的产品
     * @return
     */
    @Select("select * from t_product where tpt_state = 1 order by tpt_rate limit 10;")
    List<Product> selectProductOrderRate();

    /**
     * 查询销量前十的产品
     * @return
     */
    @Select("select * from t_product where tpt_state = 1 order by tpt_sales limit 10;")
    List<Product> selectProductOrderSales();

    /**
     * 查询分类热卖产品
     * @param name
     * @return
     */
    @Select("select * from t_product where tpt_state = 1 and tpt_category = #{name} order by tpt_sales limit 10;")
    List<Product> selectProduceByCategoryNameOrderSales(String name);

    /**
     * 分类推荐产品
     * @param name
     * @return
     */
    @Select("select * from t_product where tpt_state = 1 and tpt_category = #{name} order by tpt_rate limit 10;")
    List<Product> selectProduceByCategoryNameOrderRate(String name);

    /**
     * search查询
     * @param searchInfo
     * @return
     */
    List<Product> selectSearchProduct(Map searchInfo);

    /**
     * 根据商品id商品信息
     * @param id
     * @return
     */
    @Select("select tpt_id, tpt_img, tpt_price, tpt_text, tpt_brand, tpt_category, tpt_store,tpt_sort, tpt_rate from t_product where tpt_id = #{id} and tpt_state = 1 ;")
    Product selectProductById(int id);

    /**
     * 查询商品全部图片
     * @param id
     * @return
     */
    @Select("select pt_img from product_imgs where pt_id = #{id};")
    List<String> selectProductImg(int id);

    // merchant
    //查询商家商品分页
    List<Product> selectMerchantList(@Param("uuid")String uuid,@Param("page")int page,@Param("limit")int limit);

    // 查询商家所有商品
    @Select("select * from t_product where tpt_tmt_uuid = #{uuid}")
    List<Product> selectMerchantAllList(String uuid);

    // 查询商家商品数量
    @Select("select count(tpt_id) from t_product where tpt_tmt_uuid = #{uuid};")
    int selectMerchantNumber(String uuid);

    // 商品上架下架
    @Update("update t_product set tpt_state = #{state} where tpt_id = #{id}")
    int updateProductState(@Param("id")int id,@Param("state")int state);

    // 修改产品信息
    @Update("update t_product set tpt_img = #{tpt_img},tpt_price= #{tpt_price},tpt_text = #{tpt_text},tpt_brand = #{tpt_brand},tpt_category = #{tpt_category},tpt_sort = #{tpt_sort},tpt_store = #{tpt_store} where tpt_id = #{tpt_id}")
    int updateProduct(Product product);

    // 删除商品图片
    @Delete("DELETE from product_imgs where pt_id = #{id}")
    int deleteProductImg(int id);

    // 添加商品图片
    @Insert("insert into product_imgs(pt_id, pt_img) value (#{id},#{img})")
    int insertProductImg(@Param("id")int id,@Param("img")String img);

    int insertProductInfo(Product product);
}
