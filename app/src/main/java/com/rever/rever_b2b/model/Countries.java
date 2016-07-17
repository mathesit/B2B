package com.rever.rever_b2b.model;

/**
 * Created by Bharath on 7/16/2016.
 */
public class Countries {

    private int isd;
    private String country_code,country_name,currency_code;

    public Countries(int isd,String currency_code,String country_name,String country_code){
        this.currency_code =currency_code;
        this.country_name=country_name;
        this.country_code=country_code;

    }
    public int getisd(){ return isd; }
    public void setisd(int isd){
        this.isd = isd;
    }

    public String getcurrency_code(){ return currency_code; }
    public void setcurrency_code(String currency_code){
        this.currency_code = currency_code;
    }

    public String getcountry_code(){ return country_code; }
    public void setcountry_code(String country_code){
        this.country_code = country_code;
    }

    public String getcountry_name(){ return country_name; }
    public void setcountry_name(String country_name){
        this.country_name = country_name;
    }
}
