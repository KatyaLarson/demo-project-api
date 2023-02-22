package com.demo.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(allowGetters = true,allowSetters = true)
public class PUTFruit {
    private String name;
    private double price;
    private String category_url;
    private String vendor_url;



}
