package com.rever.rever_b2b.model;

/**
 * Created by Ramesh on 6/14/2016.
 */
public class EWTabClaimHistory {

//                "brand": "(SANDISK)",
//                "user_id": 2112950,
//                "model_name": "DJSKS",
//                "warranty_id": 243913,
//                "eq_stock_id": 187161,
//                "warranty_provider_id": 120175,
//                "warranty_provider_name": "StarShield Extended Warranty",
//                "purchased_date": "24/05/2016",
//                "max_claimable_amt": 90,
//                "email_id": "hema.devaki@gmail.com",
//                "ew_expiry_date": "23/08/2025",
//                "ew_months": 55,
//                "product_type": "MOBILE",
//                "total_claimed_amt": 0,
//                "claimable_balance": 90,
//                "serial_no": "THEDBDK",
//                "ew_start_date": "23/01/2021",
//                "warranty_no": "sf",
//                "consumer_name": "hema ",
//                "claim_history": []

    private Integer user_id,warranty_id;
    private String brand,
    model_name,eq_stock_id,warranty_provider_id,warranty_provider_name,purchased_date,max_claimable_amt,
    email_id,ew_expiry_date,ew_months,product_type,total_claimed_amt,claimable_balance,serial_no,ew_start_date,warranty_no,
            consumer_name,claim_history;

    public EWTabClaimHistory
            (Integer user_id,Integer warranty_id,String brand,String model_name,String eq_stock_id,
             String warranty_provider_id,String warranty_provider_name,String purchased_date,String max_claimable_amt,
             String email_id,String ew_expiry_date,String ew_months,String product_type,String total_claimed_amt,
             String claimable_balance,String serial_no,String ew_start_date,String warranty_no,
             String consumer_name,String claim_history) {

        this.brand = brand;
        this.user_id = user_id;
        this.model_name = model_name;
        this.warranty_id = warranty_id;
        this.eq_stock_id = eq_stock_id;
        this.warranty_provider_id = warranty_provider_id;
        this.warranty_provider_name = warranty_provider_name;
        this.purchased_date = purchased_date;
        this.max_claimable_amt = max_claimable_amt;
        this.email_id = email_id;
        this.ew_expiry_date = ew_expiry_date;
        this.ew_months = ew_months;
        this.product_type = product_type;
        this.total_claimed_amt = total_claimed_amt;
        this.claimable_balance = claimable_balance;
        this.serial_no = serial_no;
        this.ew_start_date = ew_start_date;
        this.warranty_no = warranty_no;
        this.consumer_name = consumer_name;
        this.claim_history = claim_history;
    }


    public String getbrand() { return brand;}
    public Integer getuser_id() { return user_id;}
    public String getmodel_name() { return model_name;}
    public Integer getwarranty_id() { return warranty_id;}
    public String geteq_stock_id() { return eq_stock_id;}
    public String getwarranty_provider_id() { return warranty_provider_id;}
    public String getwarranty_provider_name() { return warranty_provider_name;}
    public String getpurchased_date() { return purchased_date;}
    public String getmax_claimable_amt() { return max_claimable_amt;}
    public String getemail_id() { return email_id;}
    public String getew_expiry_date() { return ew_expiry_date;}
    public String getew_months() { return ew_months;}
    public String getproduct_type() { return product_type;}
    public String gettotal_claimed_amt() { return total_claimed_amt;}
    public String getclaimable_balance() { return claimable_balance;}
    public String getserial_no() { return serial_no;}
    public String getew_start_date() { return ew_start_date;}
    public String getwarranty_no() { return warranty_no;}
    public String getconsumer_name() { return consumer_name;}
    public String getclaim_history() { return claim_history;}


    public void setbrand(String brand){this.brand = brand; }
    public void setuser_id(Integer user_id){this.user_id = user_id; }
    public void setmodel_name(String model_name){this.model_name = model_name; }
    public void setwarranty_id(Integer warranty_id){this.warranty_id = warranty_id; }
    public void seteq_stock_id(String eq_stock_id){this.eq_stock_id = eq_stock_id; }
    public void setwarranty_provider_id(String warranty_provider_id){this.warranty_provider_id = warranty_provider_id; }
    public void setwarranty_provider_name(String warranty_provider_name){this.warranty_provider_name = warranty_provider_name; }
    public void setpurchased_date(String purchased_date){this.purchased_date = purchased_date; }
    public void setmax_claimable_amt(String max_claimable_amt){this.max_claimable_amt = max_claimable_amt; }
    public void setemail_id(String email_id){this.email_id = email_id; }
    public void setew_expiry_date(String ew_expiry_date){this.ew_expiry_date = ew_expiry_date; }
    public void setew_months(String ew_months){this.ew_months = ew_months; }
    public void setproduct_type(String product_type){this.product_type = product_type; }
    public void settotal_claimed_amt(String total_claimed_amt){this.total_claimed_amt = total_claimed_amt; }
    public void setclaimable_balance(String claimable_balance){this.claimable_balance = claimable_balance; }
    public void setserial_no(String serial_no){this.serial_no = serial_no; }
    public void setew_start_date(String ew_start_date){this.ew_start_date = ew_start_date; }
    public void setwarranty_no(String warranty_no){this.warranty_no = warranty_no; }
    public void setconsumer_name(String consumer_name){this.consumer_name = consumer_name; }
    public void setclaim_history(String claim_history){this.claim_history = claim_history; }



}
