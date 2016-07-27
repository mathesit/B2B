package com.rever.rever_b2b.model;

/**
 * Created by Oviya on 7/22/2016.
 */
public class EWCaseLog {
    private String fail_count;
   // private String brand, model, prodType;

    public EWCaseLog(String fail_count){ //,String brand, String model, String prodType){
        this.fail_count = fail_count;
       /* this.brand =brand;
        this.model = model;
        this.prodType = prodType;*/
     }

    public String getFail_count(){
        return fail_count;
    }
/*    public String getBrand(){
        return  brand;
    }

    public String getModel(){
        return model;
    }
    public String getProdType(){
        return  prodType;
    }*/

    public void setFail_count(String fail_count){
        this.fail_count = fail_count;
    }
  /*  public void setBrand(String brand){
        this.brand = brand;
    }
    public void setModel(String model){
        this.model = model;
    }
    public void  setProdType(String prodType){
        this.prodType = prodType;
    }*/

}
