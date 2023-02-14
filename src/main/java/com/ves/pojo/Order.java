package com.ves.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Order {

    private int tor_id;
    private int tor_gs_id;
    private int tor_cr_id;
    private String tor_name;
    private String tor_address;
    private String tor_tel;
    private String tor_time;
    private int tor_state;
    private String tor_price;
    private String tor_number;
    private List<String> attribute;
    private Product product;
}
