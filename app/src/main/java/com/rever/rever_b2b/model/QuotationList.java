package com.rever.rever_b2b.model;

/**
 * Created by Oviya on 7/6/2016.
 */
public class QuotationList {
    private String quot_id,company_id,sr_id, sr_no, status, created_on ;

    public QuotationList(String quot_id, String company_id, String sr_id, String sr_no, String status, String created_on) {
        this.quot_id = quot_id;
        this.company_id = company_id;
        this.sr_id = sr_id;
        this.sr_no = sr_no;
        this.status = status;
        this.created_on = created_on;
    }

    public void setQuot_id(String quot_id){
        this.quot_id = quot_id;
    }
    public String getQuot_id(){
        return quot_id;
    }
    public void setCompany_id(String company_id){
        this.company_id = company_id;
    }
    public String getCompany_id(){
        return company_id;
    }
    public void setSr_id(String sr_id){
        this.sr_id=sr_id;
    }
    public String getSr_id(){
        return sr_id;
    }
    public void setSr_no(String sr_no){
        this.sr_no = sr_no;
    }
    public String getSr_no(){
        return  sr_no;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return  status;
    }
    public void setCreated_on(String created_on){
        this.created_on = created_on;
    }
    public String getCreated_on(){
        return  created_on;
    }



    }


