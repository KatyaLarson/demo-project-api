package com.demo.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(allowSetters = true,ignoreUnknown = true,allowGetters = true)
public class Product {


    private String name;
    private double price;
    private String self_url;
    private    String category_url;
    private   String vendor_url;


    public int setId() {
        String id="";
        for (int i=0;i<self_url.length();i++){
            if (Character.isDigit(self_url.charAt(i))){
                id+=self_url.charAt(i);
            }
        }

        return Integer.parseInt(id);
    }








}
