package com.ves.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OrderRate {

    private int oe_id;
    private String oe_uuid;
    private int oe_rate;
    private int oe_gs_id;
    private String oe_text;
    private String oe_date;
    private List<String> imgs;
    private Map<String,Object> userInfo;
}
