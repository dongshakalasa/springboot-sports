package com.ves.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Cart {

    private int tcr_id;
    private String tcr_uuid;
    private int tcr_gs_id;
    private int tcr_number;
    private int tcr_state;
    private List<String> attribute;
    private Product product;
}
