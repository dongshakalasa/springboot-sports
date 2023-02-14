package com.ves.pojo;

import lombok.Data;

@Data
public class Address {

    private int id;
    private String uuid;
    private String name;
    private String tel;
    private String province;
    private String city;
    private String county;
    private String addressDetail;
    private int isDefault;

}
