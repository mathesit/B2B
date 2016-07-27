package com.rever.rever_b2b.model;

/**
 * Created by Bharath on 7/16/2016.
 */
public class Brands {

    private int brand_id;
    private String brand_name;

    public Brands(int brand_id,String brand_name){
        this.brand_id =brand_id;
        this.brand_name=brand_name;

    }
    public void setbrand_id(int brand_id){
        this.brand_id = brand_id;
    }
    public int getbrand_id(){ return brand_id; }

    public String getbrand_name(){ return brand_name; }
    public void setbrand_name(String brand_name){
        this.brand_name = brand_name;
    }
}
