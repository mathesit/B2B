package com.rever.rever_b2b.model;

/**
 * Created by Ramesh on 6/2/2016.
 */
public class EWDetails {
    private String warrantyId,warrantyNo,purchaseFrom,purchaseDate,warrantyStartDate,warrantyEndDate,email,consumerName,brandName,serialNo,productType,modelName;
    private int userId;

    /*   {
        "EW": [{
            "warranty_id": 243913,
            "warranty_no": "hdjd",
            "user_id": 2112950,
            "purchase_from": "120175",
            "purchase_dt": "May 24, 2016 8:10:57 AM",
            "warranty_start_date": "Jan 23, 2021 2:26:46 PM",
            "warranty_end_date": "Aug 23, 2025 2:26:46 PM",
            "email_id": "hema.devaki@gmail.com",
            "consumer_name": "hema ",
            "brand_name": "(SANDISK)",
            "serial_no": "THEDBDK",
            "product_type": "MOBILE",
            "model_name": "DJSKS"
            }]
         }
         */

    public EWDetails(int id,String warrantyId,String warrantyNo,String purchaseFrom,String purchaseDate,String warrantySDate,String warrantyEDate,String email,String consumerName,String brandName,String serialNo,String productType,String modelName) {
        this.userId = id;
        this.warrantyId = warrantyId;
        this.warrantyNo = warrantyNo;
        this.purchaseFrom = purchaseFrom;
        this.purchaseDate = purchaseDate;
        this.warrantyStartDate = warrantySDate;
        this.warrantyEndDate = warrantyEDate;
        this.email = email;
        this.consumerName = consumerName;
        this.brandName = brandName;
        this.serialNo = serialNo;
        this.productType = productType;
        this.modelName = modelName;
    }

    public int getUserId(){ return userId; }

    public String getwarrantyId(){ return warrantyId; }

    public String getwarrantyNo(){ return warrantyNo; }

    public String getpurchaseFrom(){ return purchaseFrom; }

    public String getpurchaseDate(){ return purchaseDate; }

    public String getwarrantyStartDate(){ return warrantyStartDate; }

    public String getEmail(){ return email; }

    public String getwarrantyEndDate(){ return warrantyEndDate; }

    public String getconsumerName(){ return consumerName; }

    public String getbrandName(){ return brandName; }

    public String getserialNo(){ return serialNo; }

    public String getproductType(){ return productType; }

    public String getmodelName(){ return modelName; }

    public void setUserId(int userId){ this.userId = userId; }

    public void getwarrantyId(int getwarrantyId){ this.warrantyId = warrantyId; }

    public void setwarrantyNo(String warrantyNo){ this.warrantyNo = warrantyNo; }

    public void setpurchaseFrom(String purchaseFrom){ this.purchaseFrom = purchaseFrom; }

    public void setpurchaseDate(String purchaseDate){ this.purchaseDate = purchaseDate; }

    public void setwarrantyStartDate(String warrantyStartDate){ this.warrantyStartDate = warrantyStartDate; }

    public void setEmail(String email){ this.email = email; }

    public void setwarrantyEndDate(String warrantyEndDate){ this.warrantyEndDate = warrantyEndDate; }

    public void setconsumerName(String consumerName){ this.consumerName = consumerName; }

    public void setbrandName(String brandName){ this.brandName = brandName; }

    public void setserialNo(String serialNo){ this.serialNo = serialNo; }

    public void setproductType(String productType){ this.productType = productType; }

    public void setmodelName(String modelName){ this.modelName = modelName; }

}

