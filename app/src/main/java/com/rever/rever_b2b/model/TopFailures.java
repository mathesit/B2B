package com.rever.rever_b2b.model;

/**
 * Created by Oviya on 7/26/2016.
 */
public class TopFailures {
    private int countFail;
    private String brandFail,modelFail,productFail;

    public TopFailures(int countFail,String brandFail,String modelFail,String productFail){
        this.countFail = countFail;
        this.brandFail = brandFail;
        this.modelFail = modelFail;
        this.productFail = productFail;
    }

    public int getCountFail(){
        return countFail;
    }
    public void setCountFail(int countFail){
        this.countFail = countFail;
    }
    public String getBrandFail(){
        return brandFail;
    }
    public void setBrandFail(String brandFail){
        this.brandFail = brandFail;
    }
    public String getModelFail(){
        return  modelFail;
    }
    public void setModelFail(String modelFail){
        this.modelFail = modelFail;
    }
    public String getProductFail(){
        return productFail;
    }
    public void setProductFail(String productFail){
        this.productFail = productFail;
    }
}
