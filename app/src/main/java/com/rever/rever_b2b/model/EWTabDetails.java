package com.rever.rever_b2b.model;

import android.util.Log;

/**
 * Created by Bharath on 6/7/2016.
 */
public class EWTabDetails {
    private Integer warrantyId;
     private String eq_stockId
     ,warrantyNo
     ,countryCode
     ,ew_regDate
     ,warrantyType
     ,invoiceNo
     ,brandName
     ,serialNo
     ,productType
     ,modelName
     ,consumerId
     ,mycompanyId
     ,providerCompanyId
     ,warrantyMonths
     ,ew_sDate
     ,ew_sMonth
     ,ew_sYear
     ,ew_sQuarter
     ,ew_sMonthTxt
     ,ew_sYearTxt
     ,ew_sQuarterTxt
     ,purchaseDate
     ,ew_sellingPrice
     ,warrantyExpDate
     ,providerCompName
     ,monthlyRecognition
     ,purchasedMonth
     ,purchasedYear
     ,purchasedQuarter
     ,p_QuarterTxt
     ,p_MonthTxt
     ,p_YearTxt
             ,EW_CATEGORY;
//
//    "EW": {
//        "WARRANTY_ID": 230987
//        "EQ_STOCK_ID": 180881
//        "WARRANTY_NO": "23232"
//        "COUNTRY_CODE": "SG"
//        "EW_REGISTERED_DATE": "Jul 3, 2015 12:43:45 PM"
//        "WARRANTY_TYPE": "EW"
//        "INVOICE_NUMBER": "12345678"
//        "BRAND_NAME": "CHIN"
//        "SERIAL_NO": "t4343433"
//        "PRODUCT_TYPE": "TV"
//        "MODEL_NAME": "CHIN"
//        "CONSUMER_ID": 2384
//        "MY_COMPANY_ID": 127272
//        "PROVIDER_COMPANY_ID": 127272
//        "WARRANTY_MONTHS": 12
//        "EW_START_DATE": "Jul 3, 2013 12:00:00 AM"
//        "EW_START_MONTH": "Jul 1, 2013 12:00:00 AM"
//        "EW_START_YEAR": "Jan 1, 2013 12:00:00 AM"
//        "EW_START_QUARTER": "Jul 1, 2013 12:00:00 AM"
//        "EW_START_MONTH_TXT": "2013 07"
//        "EW_START_YEAR_TXT": "2013"
//        "EW_START_QUARTER_TXT": "2013 Q3"
//        "PURCHASED_DATE": "Jul 3, 2012 12:00:00 AM"
//        "EW_SELLING_PRICE": "12"
//        "WARRENTY_EXP_DT": "Jul 2, 2014 12:00:00 AM"
//        "PROVIDER_COMPANY_NAME": "Yarraa Extended Warranty Pte Ltd"
//        "MONTHLY_RECOGNITION": 0
//        "PURCHASED_MONTH": "Jul 1, 2012 12:00:00 AM"
//        "PURCHASED_YEAR": "Jan 1, 2012 12:00:00 AM"
//        "PURCHASED_QUARTER": "Jul 1, 2012 12:00:00 AM"
//        "PURCHASED_MONTH_TXT": "2012 07"
//        "PURCHASED_YEAR_TXT": "2012"
//        "PURCHASED_QUARTER_TXT": "2012 Q3"
//    }

        public EWTabDetails
                (Integer warrantyId,String eq_stockId,String warrantyNo,String countryCode,String ew_regDate,String warrantyType,String tnvoiceNo,
                         String brandName,String serialNo,String productType
                ,String modelName,String consumerId,String mycompanyId,String providerCompanyId,String warrantyMonths
                ,String ew_sDate,String ew_sMonth,String ew_sYear,String ew_sQuarter,String ew_sMonthTxt,String ew_sYearTxt
                ,String ew_sQuarterTxt,String purchaseDate,String ew_sellingPrice,String warrantyExpDate,String providerCompName
                ,String monthlyRecognition,String purchasedMonth,String purchasedYear,String purchasedQuarter,String p_QuarterTxt
                ,String p_MonthTxt,String p_YearTxt,String EW_CATEGORY)
        {
            this.warrantyId = warrantyId;
            this.eq_stockId = eq_stockId;
            this.warrantyNo = warrantyNo;
            this.countryCode = countryCode;
            this.ew_regDate = ew_regDate;
            this.warrantyType = warrantyType;
            this.invoiceNo = invoiceNo;
            this.brandName = brandName;
            this.serialNo = serialNo;
            this.productType = productType;
            this.modelName = modelName;
            this.consumerId = consumerId;
            this.mycompanyId = mycompanyId;
            this.providerCompanyId = providerCompanyId;
            this.warrantyMonths = warrantyMonths;
            this.ew_sDate = ew_sDate;
            this.ew_sMonth = ew_sMonth;
            this.ew_sYear = ew_sYear;
            this.ew_sQuarter = ew_sQuarter;
            this.ew_sMonthTxt = ew_sMonthTxt;
            this.ew_sYearTxt = ew_sYearTxt;
            this.ew_sQuarterTxt = ew_sQuarterTxt;
            this.purchaseDate = purchaseDate;
            this.ew_sellingPrice = ew_sellingPrice;
            this.warrantyExpDate = warrantyExpDate;
            this.providerCompName = providerCompName;
            this.monthlyRecognition = monthlyRecognition;
            this.purchasedMonth = purchasedMonth;
            this.purchasedYear = purchasedYear;
            this.purchasedQuarter = purchasedQuarter;
            this.p_QuarterTxt= p_QuarterTxt;
            this.p_MonthTxt= p_MonthTxt;
            this.p_YearTxt= p_YearTxt;
            this.EW_CATEGORY= EW_CATEGORY;
        }

        public Integer getWarrantyId(){ return warrantyId; }

        public String getWarrantyNo(){ return warrantyNo; }

        public String getEq_stockId(){ return eq_stockId; }

        public String getCountryCode(){return countryCode;}

        public String getEw_regDate (){return ew_regDate;}

        public String getWarrantyType(){return warrantyType;}

        public String getInvoiceNo(){return invoiceNo;}

        public String getBrandName(){ return brandName; }

        public String getSerialNo(){ return serialNo; }

        public String getProductType(){ return productType; }

        public String getModelName(){ return modelName; }

        public String getConsumerId(){ return consumerId; }

        public String getMycompanyId() { return  mycompanyId; }

        public String getProviderCompanyId() { return providerCompanyId; }

        public String getWarrantyMonths() { return warrantyMonths; }

        public String getEw_sDate() { return ew_sDate; }

        public String getEw_sMonth() { return ew_sMonth; }

        public String getEw_sYear() { return ew_sYear; }

        public String getEw_sQuarter() { return ew_sQuarter; }

        public String getEw_sMonthTxt() { return ew_sMonthTxt;}

        public String getEw_sYearTxt() { return ew_sYearTxt; }

        public String getEw_sQuarterTxt() { return ew_sQuarterTxt;}

        public String getPurchaseDate(){ return purchaseDate; }

        public String getEw_sellingPrice(){ return ew_sellingPrice; }

        public String getWarrantyExpDate(){ return warrantyExpDate; }

        public String getProviderCompName(){ return providerCompName; }

        public String getMonthlyRecognition(){ return monthlyRecognition; }

        public String getPurchasedMonth(){ return purchasedMonth; }

        public String getPurchasedYear(){ return purchasedYear; }

        public String getPurchasedQuarter(){ return purchasedQuarter; }

        public String getP_MonthTxt() { return p_MonthTxt;}

        public String getP_YearTxt(){ return p_YearTxt; }

        public String getP_QuarterTxt(){ return p_QuarterTxt; }

        public String getEW_CATEGORY(){ return EW_CATEGORY; }


        public void setEW_CATEGORY(String EW_CATEGORY){  this.EW_CATEGORY= EW_CATEGORY; }

        public void setWarrantyId(Integer warrantyId){ this.warrantyId = warrantyId; }

        public void setWarrantyNo(String warrantyNo){ this.warrantyNo = warrantyNo; }

        public void setEq_stockId(String eq_stockId){ this.eq_stockId = eq_stockId; }

        public void setCountryCode(String countryCode){this.countryCode=countryCode;}

        public void setEw_regDate (String ew_regDate){this.ew_regDate= ew_regDate;}

        public void setWarrantyType(String warrantyType){this.warrantyType= warrantyType;}

        public void setInvoiceNo(String invoiceNo){this.invoiceNo= invoiceNo;}

        public void setBrandName(String brandName){ this.brandName = brandName; }

        public void setSerialNo(String serialNo){ this.serialNo = serialNo; }

        public void setProductType(String productType){ this.productType=productType; }

        public void setModelName(String modelName){ this.modelName= modelName; }

        public void setConsumerId(String consumerId){ this.consumerId= consumerId; }

        public void setMycompanyId(String mycompanyId) { this.mycompanyId=  mycompanyId; }

        public void setProviderCompanyId(String providerCompanyId) { this.providerCompanyId= providerCompanyId; }

        public void setWarrantyMonths(String warrantyMonths) { this.warrantyMonths= warrantyMonths; }

        public void setEw_sDate(String ew_sDate) { this.ew_sDate=ew_sDate; }

        public void setEw_sMonth(String ew_sMonth) { this.ew_sMonth= ew_sMonth; }

        public void setEw_sYear(String ew_sYear) { this.ew_sYear= ew_sYear; }

        public void setEw_sQuarter(String ew_sQuarter) { this.ew_sQuarter= ew_sQuarter; }

        public void setEw_sMonthTxt(String ew_sMonthTxt) { this.ew_sMonthTxt= ew_sMonthTxt;}

        public void setEw_sYearTxt(String ew_sYearTxt) { this.ew_sYearTxt = ew_sYearTxt; }

        public void setEw_sQuarterTxt(String ew_sQuarterTxt) { this.ew_sQuarterTxt = ew_sQuarterTxt;}

        public void setPurchaseDate(String purchaseDate){ this.purchaseDate = purchaseDate; }

        public void setEw_sellingPrice(String ew_sellingPrice){ this.ew_sellingPrice = ew_sellingPrice; }

        public void setWarrantyExpDate(String warrantyExpDate){ this.warrantyExpDate = warrantyExpDate; }

        public void setProviderCompName(String providerCompName){ this.providerCompName = providerCompName; }

        public void setMonthlyRecognition(String monthlyRecognition){ this.monthlyRecognition = monthlyRecognition; }

        public void setPurchasedMonth(String purchasedMonth){ this.purchasedMonth = purchasedMonth; }

        public void setPurchasedYear(String purchasedYear){ this.purchasedYear = purchasedYear; }

        public void setPurchasedQuarter(String purchasedQuarter){ this.purchasedQuarter = purchasedQuarter; }

        public void setP_MonthTxt(String p_MonthTxt) { this.p_MonthTxt = p_MonthTxt;}

        public void setP_YearTxt(String p_YearTxt){ this.p_YearTxt = p_YearTxt; }

        public void setP_QuarterTxt(String p_QuarterTxt){ this.p_QuarterTxt = p_QuarterTxt; }



}
