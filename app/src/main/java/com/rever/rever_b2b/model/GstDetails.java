package com.rever.rever_b2b.model;

/**
 * Created by Oviya on 7/29/2016.
 */
public class GstDetails {
    private String gstPercent,gstCurrency;
    public GstDetails(String gstPercent,String gstCurrency){
        this.gstPercent = gstPercent;
        this.gstCurrency = gstCurrency;
    }

    public String getGstPercent(){
        return gstPercent;
    }
    public String getGstCurrency(){
        return  gstCurrency;
    }
    public void setGstPercent(String gstPercent){
        this.gstPercent = gstPercent;
    }
    public void setGstCurrency(String gstCurrency){
        this.gstCurrency = gstCurrency;
    }
}
