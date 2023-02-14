package com.ves.Contorller.merchant;

import com.ves.Service.merchant.ProductService;
import com.ves.pojo.Attribute;
import com.ves.pojo.Brand;
import com.ves.pojo.Product;
import com.ves.pojo.Sort;
import com.ves.utils.QiniuUtils;
import com.ves.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/merchant/product")
public class ProductContorller {
    
    @Autowired
    ProductService productService;

    /**
     * 查询商家产品
     * @param page
     * @param limit
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/{page}/{limit}")
    public Result selectMerchantList(@PathVariable int page, @PathVariable int limit, HttpSession session, HttpServletRequest request) {
        Map<String,Object> map = new HashMap<>();

        String token = request.getHeader("TOKEN");
        String userUuid = (String) session.getAttribute(token);
        List<Product> products = productService.selectMerchantList(userUuid, (page - 1) * limit, limit);
        for(Product p : products) {
            int id = p.getTpt_id();
            p.setImgs(productService.selectProductImg(id));
            //商品可选属性
            List<Object> attributeList = new ArrayList<>();
            List<String> names = productService.selectAttributeName(id);
            for(String name : names) {
                Map<String,Object> attributeMap = new HashMap<>();
                List<Attribute> attributes = productService.selectAttributeList(id, name);
                attributeMap.put("name",name);
                attributeMap.put("value",attributes);
                attributeList.add(attributeMap);
            }
            p.setAttributeList(attributeList);
        }
        int number = productService.selectMerchantNumber(userUuid);

        map.put("products",products);
        map.put("total",number);
        return new Result(200,map);
    }

    /**
     * 商品上架与下架
     * @param id
     * @param state
     * @return
     */
    @GetMapping("/state/{id}/{state}")
    public Result updateProductState(@PathVariable int id,@PathVariable int state) {
        int flag = productService.updateProductState(id, state);
        if(flag > 0) {
            return new Result(200,null,"ok");
        }else {
            return new Result(201,null,"error");
        }
    }

    /**
     * 一级分类
     * @return
     */
    @GetMapping("/category")
    public Result selectCategoryName(){
        List<String> strings = productService.selectCategoryName();
        return new Result(200,strings,"ok");
    }

    /**
     * 二级分类
     * @param name
     * @return
     */
    @GetMapping("/sort/{name}")
    public Result selectSortName(@PathVariable String name){
        List<Sort> sorts = productService.selectSortByCategoryName(name);
        return new Result(200,sorts,"ok");
    }

    /**
     * 分类对应的品牌
     * @param name
     * @return
     */
    @GetMapping("/brand/{name}")
    public Result selectBrandName(@PathVariable String name) {
        List<Brand> brands = productService.selectBrandName(name);
        return new Result(200,brands,"ok");
    }

    /**
     * 查询平台属性
     */
    @GetMapping("/attributeName")
    public Result selectAttributeName(){
        List<String> attiruteNames = productService.selectAttributeNameList();
        return new Result(200,attiruteNames,"ok");
    }

    /**
     * 上传图片
     * @param myfile
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public Result save(@PathVariable MultipartFile myfile) throws IOException {
        QiniuUtils.upload2Qiniu(myfile.getBytes(),myfile.getOriginalFilename());
        return new Result(200,"ok");
    }

    /**
     * 修改产品
     */
    @PostMapping("/update")
    public Result updateProduct(@RequestBody Map form,HttpSession session,HttpServletRequest request) {


        // 整理参数
        System.out.println(form);

        // gs_id
        int id = (int) form.get("tpt_id");

        // 默认img
        List<Object> imgList = (List<Object>) form.get("imgs");
        Map<String,Object> img = (Map<String, Object>) imgList.get(0);
        String url = (String) img.get("url");

        //imgs
        //删除原数据
        productService.deleteProductImg(id);
        for(Object imglist : imgList) {
            Map<String,Object> imgsMap = (Map<String, Object>) imglist;
            String imgsUrl = (String) imgsMap.get("url");
            System.out.println(imgsUrl);
        //  添加新数据
            productService.insertProductImg(id,imgsUrl);
        }

        System.out.println(form);

        // product对象
        Product product = new Product();
            //tpt_img
            product.setTpt_img(url);
            //tpt_price
            product.setTpt_price((String) form.get("tpt_price"));
            //tpt_text
            product.setTpt_text((String) form.get("tpt_text"));
            //tpt_brand
            product.setTpt_brand((String) form.get("tpt_brand"));
            //tpt_category
            product.setTpt_category((String) form.get("tpt_category"));
            //tpt_sort
            product.setTpt_sort((String) form.get("tpt_sort"));
            //tpt_store
            product.setTpt_store((int) form.get("tpt_store"));
            //tpt_id
            product.setTpt_id(id);
        // 修改product
        productService.updateProduct(product);

        // attribute的更新
        List<Map<String,Object>> attributeList = (List<Map<String, Object>>) form.get("attributeList");
        //删除原数据
        productService.deleteAttribute(id);
        for(Map<String,Object> attributeMap : attributeList) {
            String name = (String) attributeMap.get("name");
            List<Map<String,Object>> values = (List<Map<String, Object>>) attributeMap.get("value");
            for(Map<String,Object> value : values) {
                String valueName = (String) value.get("taev_value");
                // 添加新数据
                productService.insertAttribute(id,name,valueName);
            }
        }

        return new Result(200,null);
    }

    @PostMapping("/insert")
    public Result insertProduct(@RequestBody Map form,HttpSession session,HttpServletRequest request){
        //uuid
        String token = request.getHeader("TOKEN");
        String userUUid = (String) session.getAttribute(token);

        System.out.println(form);

        // 默认img
        List<Object> imgList = (List<Object>) form.get("imgs");
        Map<String,Object> img = (Map<String, Object>) imgList.get(0);
        String url = (String) img.get("url");

        // product对象
        Product product = new Product();
        //tpt_img
        product.setTpt_img(url);
        //tpt_price
        product.setTpt_price((String) form.get("tpt_price"));
        //tpt_text
        product.setTpt_text((String) form.get("tpt_text"));
        //tpt_brand
        product.setTpt_brand((String) form.get("tpt_brand"));
        //tpt_category
        product.setTpt_category((String) form.get("tpt_category"));
        //tpt_sort
        product.setTpt_sort((String) form.get("tpt_sort"));
        //tpt_store
        product.setTpt_store((int) form.get("tpt_store"));
        //tpt_tmt_uuid
        product.setTpt_tmt_uuid(userUUid);
        System.out.println(product);

        //添加商品
        productService.insertProductInfo(product);
        //返回商品id
        int id = product.getTpt_id();

        //添加商品图片
        for(Object imglist : imgList) {
            Map<String,Object> imgsMap = (Map<String, Object>) imglist;
            String imgsUrl = (String) imgsMap.get("url");
            System.out.println(imgsUrl);
            //  添加新数据
            productService.insertProductImg(id,imgsUrl);
        }

        // attribute的更新
        List<Map<String,Object>> attributeList = (List<Map<String, Object>>) form.get("attributeList");

        for(Map<String,Object> attributeMap : attributeList) {
            String name = (String) attributeMap.get("name");
            List<Map<String,Object>> values = (List<Map<String, Object>>) attributeMap.get("value");
            for(Map<String,Object> value : values) {
                String valueName = (String) value.get("taev_value");
                // 添加新数据
                productService.insertAttribute(id,name,valueName);
            }
        }


        return new Result(200,null,"ok");
    }
}
