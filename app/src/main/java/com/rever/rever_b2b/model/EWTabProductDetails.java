package com.rever.rever_b2b.model;

/**
 * Created by Ramesh on 6/13/2016.
 */
public class EWTabProductDetails {    private Integer warrantyId;
    private int PrewwarrId;
    private String mw_warrantyId
            ,warrantyNo
            ,userId
            ,purchaseFrom
            ,purchaseDate
            ,prregDate
            ,ew_sDate
            ,warrantyExpDate
            ,warrantyMonths
            ,additionalInfo
            ,billNo
            ,warrantyStatus
            ,price
            ,ic_no
            ,email
            ,fName
            ,lName
            ,mName
            ,mobile
            ,phone
            ,officePh
            ,alterEmail
            ,addressLine1
            ,addressLine2
            ,city
            ,postal_code
            ,user_country
            ,countryName
            ,brandName
            ,serialNo
            ,productType
            ,productId
            ,modelName;

    public EWTabProductDetails
            (int PrewwarrId,String mw_warrantyId,String warrantyNo,String userId,String purchaseFrom,String purchaseDate,String prregDate
                    ,String ew_sDate,String warrantyExpDate,String warrantyMonths,String additionalInfo,String billNo
                    ,String warrantyStatus,String price,String ic_no,String email,String fName,String lName,String mName,String mobile
                    ,String phone,String officePh,String alterEmail,String addressLine1,String addressLine2
                    ,String city,String postal_code,String user_country,String countryName,String brandName,String serialNo
                    ,String productType,String productId,String modelName)
    {
        this.PrewwarrId = PrewwarrId;
        this.mw_warrantyId = mw_warrantyId;
        this.warrantyNo = warrantyNo;
        this.userId = userId;
        this.purchaseFrom = purchaseFrom;
        this.purchaseDate = purchaseDate;
        this.prregDate = prregDate;
        this.ew_sDate = ew_sDate;
        this.warrantyExpDate = warrantyExpDate;
        this.warrantyMonths = warrantyMonths;
        this.additionalInfo = additionalInfo;
        this.billNo = billNo;
        this.warrantyStatus = warrantyStatus;
        this.price = price;
        this.ic_no = ic_no;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.mName = mName;
        this.mobile = mobile;
        this.phone = phone;
        this.officePh = officePh;
        this.alterEmail = alterEmail;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.postal_code = postal_code;
        this.user_country = user_country;
        this.countryName = countryName;
        this.brandName = brandName;
        this.serialNo = serialNo;
        this.productType = productType;
        this.productId = productId;
        this.modelName = modelName;
    }

    public Integer getPrewwarrId(){return PrewwarrId; }
    public String getmw_warrantyId () { return mw_warrantyId; }
    public String getwarrantyNo () { return warrantyNo; }
    public String getuserId () { return userId; }
    public String getpurchaseFrom () { return purchaseFrom; }
    public String getpurchaseDate () { return purchaseDate; }
    public String getprregDate () { return prregDate; }
    public String getew_sDate () { return ew_sDate; }
    public String getwarrantyExpDate () { return warrantyExpDate; }
    public String getwarrantyMonths () { return warrantyMonths; }
    public String getadditionalInfo () { return additionalInfo; }
    public String getbillNo () { return billNo; }
    public String getwarrantyStatus () { return warrantyStatus; }
    public String getprice () { return price; }
    public String getic_no () { return ic_no; }
    public String getemail () { return email; }
    public String getfName () { return fName; }
    public String getlName () { return lName; }
    public String getmName () { return mName; }
    public String getmobile () { return mobile; }
    public String getphone () { return phone; }
    public String getofficePh () { return officePh; }
    public String getalterEmail () { return alterEmail; }
    public String getaddressLine1 () { return addressLine1; }
    public String getaddressLine2 () { return addressLine2; }
    public String getcity () { return city; }
    public String getpostal_code () { return postal_code; }
    public String getuser_country () { return user_country; }
    public String getcountryName () { return countryName; }
    public String getbrandName () { return brandName; }
    public String getserialNo () { return serialNo; }
    public String getproductType () { return productType; }
    public String getproductId () { return productId; }
    public String getmodelName () { return modelName; }

    public void setWarrantyNo(String warrantyNo){ this.warrantyNo = warrantyNo; }
    public void setPrewwarrId(Integer Prewward){this.PrewwarrId = Prewward; }
    public void setmw_warrantyId (String mw_warrantyId) { this.mw_warrantyId = mw_warrantyId; }
    public void setwarrantyNo (String warrantyNo) { this.warrantyNo = warrantyNo; }
    public void setuserId (String userId) { this.userId = userId; }
    public void setpurchaseFrom (String purchaseFrom) { this.purchaseFrom = purchaseFrom; }
    public void setpurchaseDate (String purchaseDate) { this.purchaseDate = purchaseDate; }
    public void setprregDate (String prregDate) { this.prregDate = prregDate; }
    public void setew_sDate (String ew_sDate) { this.ew_sDate = ew_sDate; }
    public void setwarrantyExpDate (String warrantyExpDate) { this.warrantyExpDate = warrantyExpDate; }
    public void setwarrantyMonths (String warrantyMonths) { this.warrantyMonths = warrantyMonths; }
    public void setadditionalInfo (String additionalInfo) { this.additionalInfo = additionalInfo; }
    public void setbillNo (String billNo) { this.billNo = billNo; }
    public void setwarrantyStatus (String warrantyStatus) { this.warrantyStatus = warrantyStatus; }
    public void setprice (String price) { this.price = price; }
    public void setic_no (String ic_no) { this.ic_no = ic_no; }
    public void setemail (String email) { this.email = email; }
    public void setfName (String fName) { this.fName = fName; }
    public void setlName (String lName) { this.lName = lName; }
    public void setmName (String mName) { this.mName = mName; }
    public void setmobile (String mobile) { this.mobile = mobile; }
    public void setphone (String phone) { this.phone = phone; }
    public void setofficePh (String officePh) { this.officePh = officePh; }
    public void setalterEmail (String alterEmail) { this.alterEmail = alterEmail; }
    public void setaddressLine1 (String addressLine1) { this.addressLine1 = addressLine1; }
    public void setaddressLine2 (String addressLine2) { this.addressLine2 = addressLine2; }
    public void setcity (String city) { this.city = city; }
    public void setpostal_code (String postal_code) { this.postal_code = postal_code; }
    public void setuser_country (String user_country) { this.user_country = user_country; }
    public void setcountryName (String countryName) { this.countryName = countryName; }
    public void setbrandName (String brandName) { this.brandName = brandName; }
    public void setserialNo (String serialNo) { this.serialNo = serialNo; }
    public void setproductType (String productType) { this.productType = productType; }
    public void setproductId (String productId) { this.productId = productId; }
    public void setmodelName (String modelName) { this.modelName = modelName; }
}
