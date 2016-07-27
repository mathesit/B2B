package com.rever.rever_b2b.model;

/**
 * Created by Oviya on 7/26/2016.
 */
public class TopBrands {
    private String brand,product,serial,consumer;

    public TopBrands(String brand,String product,String serial,String consumer){
        this.brand = brand;
        this.product = product;
        this.serial = serial;
        this.consumer = consumer;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }
    public String getProduct(){
        return product;
    }
    public void setProduct(String product){
        this.product = product;
    }
    public String getSerial(){
        return  serial;
    }
    public void setSerial(String serial){
        this.serial = serial;
    }
    public String getConsumer(){
        return consumer;
    }
    public void setConsumer(String consumer){
        this.consumer = consumer;
    }
}
