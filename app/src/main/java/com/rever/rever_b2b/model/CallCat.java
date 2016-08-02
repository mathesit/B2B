package com.rever.rever_b2b.model;

/**
 * Created by Bharath on 7/11/2016.
 */
public class CallCat {

    private int cat_id;
    private String desc;

    public CallCat(int cat_id,String desc){
        this.cat_id =cat_id;
        this.desc=desc;

    }
    public void setCat_id(int cat_id){
        this.cat_id = cat_id;
    }
    public int getCat_id(){ return cat_id; }

    public String getdesc(){ return desc; }

    public void setDesc(String desc){
        this.cat_id = cat_id;
    }

}

