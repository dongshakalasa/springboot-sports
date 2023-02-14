package com.ves.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private int tpt_id;
    private String tpt_img;
    private String tpt_price;
    private String tpt_text;
    private String tpt_brand_img;
    private String tpt_brand;
    private String tpt_category;
    private String tpt_sort;
    private int tpt_store;
    private int tpt_sales;
    private int tpt_state;
    private Boolean tpt_collect;
    private String tpt_tmt_uuid;
    private List<String> imgs;
    private List<Object> attributeList;
}
