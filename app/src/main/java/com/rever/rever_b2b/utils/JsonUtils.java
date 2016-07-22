package com.rever.rever_b2b.utils;


import android.util.Log;

import com.rever.rever_b2b.model.Brands;
import com.rever.rever_b2b.model.CallCat;
import com.rever.rever_b2b.model.CaseLog;
import com.rever.rever_b2b.model.Countries;
import com.rever.rever_b2b.model.EWCallLogsCaseDetails;
import com.rever.rever_b2b.model.EWDetails;
import com.rever.rever_b2b.model.EWTabCallLogs;
import com.rever.rever_b2b.model.EWTabClaimHistory;
import com.rever.rever_b2b.model.EWTabDetails;
import com.rever.rever_b2b.model.EWTabProductDetails;
import com.rever.rever_b2b.model.Failures;
import com.rever.rever_b2b.model.Quotation;
import com.rever.rever_b2b.model.QuotationDetails;
import com.rever.rever_b2b.model.QuotationList;
import com.rever.rever_b2b.model.ServiceDetails;
import com.rever.rever_b2b.model.ServiceList;
import com.rever.rever_b2b.model.ServiceRequest;
import com.rever.rever_b2b.model.StockBalance;
import com.rever.rever_b2b.model.UsedProduct;
import com.rever.rever_b2b.model.User;
import com.rever.rever_b2b.model.tempusersave;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matheswari on 3/25/2016.
 */
 /*String quotId,//="",brand = "" , model = "",sNo = " ", product = "",email = "",consumer = "", create ="",createdby = "",
        quotstatus ="",description = "", //currency = "",
        amount = "", srp = "", markUp = "",
        received = "", created = "", sent = "", update = "", updatedBy = "", status = "";*/

public class JsonUtils {
    private static String quotId;
    private static String brand;
    private static String model;
    private static String sNo;
    private static String product;
    private static String email;
    private static String consumer;
    private static String create;
    private static String createdby;
    private static String quotstatus;
    private static String description;
    private static String amount;
    private static String srp;
    private static String markUp;
    private static String received;
    private static String created;
    private static String sent;
    private static String update;
    private static String updatedBy;
    private static String status;


    public static List<User> parseUserJson(String json) {
        List<User> users = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(json);
            JSONObject cObj = response.getJSONObject("user");
            users.add(new User(cObj.getInt("user_id"), cObj.getInt("company_id"), cObj.getString("session_token"), cObj.getString("first_name"),
                    cObj.getString("country_code"), cObj.getString("city"), cObj.getString("email"), cObj.getString("user_type")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    public static List<tempusersave> parseUser1Json(JSONObject json) {
        Log.i("JsonUtils","UserJson"+json);

            List<tempusersave> users1 = new ArrayList<>();
            Integer userId1 = 0;
            String companyId1 = null, userSessionToken1 = null, userFirstName1 = null, userCountryCode1 = null, userCity1 = null,
                    userEmail1 = null, userType1 = null,userLastName1 = null, userPostal1 = null, userAddLine2 = null, userMobile1 = null,
                userAddLine1 = null, userMiddleName1 = null;
            try {
                //JSONObject response = new JSONObject(EWJson);
                JSONObject cObj = json.getJSONObject("user");
                if (cObj.has("user_id"))
                    userId1 = cObj.getInt("user_id");
                if (cObj.has("company_id"))
                    companyId1 = cObj.getString("company_id");
                if (cObj.has("session_token"))
                    userSessionToken1 = cObj.getString("session_token");
                if (cObj.has("first_name"))
                    userFirstName1 = cObj.getString("first_name");
                if (cObj.has("country_code"))
                    userCountryCode1 = cObj.getString("country_code");
                if (cObj.has("city"))
                    userCity1 = cObj.getString("city");
                if (cObj.has("email"))
                    userEmail1 = cObj.getString("email");
                if (cObj.has("user_type"))
                    userType1 = cObj.getString("user_type");

                if (cObj.has("last_name"))
                    userLastName1 = cObj.getString("last_name");
                if (cObj.has("postal_code"))
                    userPostal1 = cObj.getString("postal_code");
                if (cObj.has("address_line2"))
                    userAddLine2 = cObj.getString("address_line2");
                if (cObj.has("mobile_phone"))
                    userMobile1 = cObj.getString("mobile_phone");
                if (cObj.has("address_line1"))
                    userAddLine1 = cObj.getString("address_line1");
                if (cObj.has("middle_name"))
                    userMiddleName1 = cObj.getString("middle_name");

                users1.add(new tempusersave(userId1, companyId1, userSessionToken1, userFirstName1, userCountryCode1,
                        userCity1, userEmail1, userType1, userLastName1, userPostal1, userAddLine2, userMobile1,
                        userAddLine1, userMiddleName1));
                Log.i("JsonUtils", "Userssize" + users1.size());


            } catch (Exception e) {
            e.printStackTrace();
        }
        return users1;
    }
    public static List<EWDetails> parseEWDetailsJson(JSONObject json) {
        List<EWDetails> EWDetails = new ArrayList<>();
        Integer user_id=0;
        String warranty_id = null,warranty_no = null,purchase_from = null,
                purchase_dt = null,warranty_start_date = null,warranty_end_date = null,email_id = null,consumer_name = null,
                brand_name = null,serial_no = null,product_type = null,model_name = null;
        try {
            JSONObject cObjj = json.getJSONObject("Warranties");
            JSONArray jarr = cObjj.getJSONArray("List");
            int size = jarr.length();
            for (int i = 0; i < size; i++) {
                JSONObject cObj = jarr.getJSONObject(i);
                if (cObj.has("user_id"))
                    user_id = cObj.getInt("user_id");
                if (cObj.has("warranty_id"))
                    warranty_id = cObj.getString("warranty_id");
                if (cObj.has("warranty_no"))
                    warranty_no = cObj.getString("warranty_no");
                if (cObj.has("purchase_from"))
                    purchase_from = cObj.getString("purchase_from");
                if (cObj.has("purchase_dt"))
                    purchase_dt = cObj.getString("purchase_dt");
                if (cObj.has("warranty_start_date"))
                    warranty_start_date = cObj.getString("warranty_start_date");
                if (cObj.has("warranty_end_date"))
                    warranty_end_date = cObj.getString("warranty_end_date");
                if (cObj.has("email_id"))
                    email_id = cObj.getString("email_id");
                if (cObj.has("consumer_name"))
                    consumer_name = cObj.getString("consumer_name");
                if (cObj.has("brand_name"))
                    brand_name = cObj.getString("brand_name");
                if (cObj.has("serial_no"))
                    serial_no = cObj.getString("serial_no");
                if (cObj.has("product_type"))
                    product_type = cObj.getString("product_type");
                if (cObj.has("model_name"))
                    model_name = cObj.getString("model_name");

                EWDetails.add(new EWDetails(user_id,warranty_id, warranty_no, purchase_from, purchase_dt, warranty_start_date, warranty_end_date, email_id, consumer_name, brand_name, serial_no, product_type, model_name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EWDetails;
    }

    public static List<StockBalance> parseStockBalJson(String json) {
        List<StockBalance> stocks = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(json);
            JSONArray array = response.getJSONArray("stockbalance");
            for (int i = 0; i < array.length(); i++) {
                JSONObject cObj = array.getJSONObject(i);
                stocks.add(new StockBalance(cObj.getString("brand_name"), cObj.getString("model_name"),
                        cObj.getString("product_type"), cObj.getInt("stock")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stocks;
    }

    public static List<CaseLog> parseCaseLogJson(String json) {
        List<CaseLog> caseLogs = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(json);
            caseLogs.add(new CaseLog(response.getInt("caselog-count")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return caseLogs;
    }

    public static List<Quotation> parseQuotationJson(String json) {
        List<Quotation> quotCount = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(json);
            quotCount.add(new Quotation(response.getInt("pending-quotation-count")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return quotCount;
    }

    public static List<ServiceRequest> parseServiceRequestJson(String json) {
        List<ServiceRequest> srCount = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(json);
            srCount.add(new ServiceRequest(response.getInt("sr-count")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return srCount;
    }

    public static List<Failures> parseFailuresJson(String json) {
        List<Failures> failures = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(json);
            JSONArray array = response.getJSONArray("SR");
            for (int i = 0; i < array.length(); i++) {
                JSONObject cObj = array.getJSONObject(i);
                String brand = "", model = "", prodtype = "";
                int count = 0;
                if (cObj.has("brand_name")) brand = cObj.getString("brand_name");
                if (cObj.has("model_name")) model = cObj.getString("model_name");
                if (cObj.has("product_type")) prodtype = cObj.getString("product_type");
                if (cObj.has("failure_count")) count = cObj.getInt("failure_count");
                failures.add(new Failures(brand, model, prodtype, count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failures;
    }

    public static List<UsedProduct> parseUsedProductsJson(String json) {
        List<UsedProduct> usedProds = new ArrayList<>();
        try {
            JSONObject response = new JSONObject(json);
            JSONArray array = response.getJSONArray("top5-products");
            for (int i = 0; i < array.length(); i++) {
                JSONObject cObj = array.getJSONObject(i);
                usedProds.add(new UsedProduct(cObj.getInt("quantity"), cObj.getInt("rank"),
                        cObj.getString("part_no")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usedProds;
    }

<<<<<<< HEAD

    public static List<EWTabDetails> parseEWJson(JSONObject EWJson) {
        List<EWTabDetails> ewTabDetails = new ArrayList<>();
        Integer WarrantyId = 0;
        String EW_CATEGORY = null,Eq_stockId = null, WarrantyNo = null, CountryCode = null, Ew_regDate = null, WarrantyType = null, InvoiceNo = null, BrandName = null, SerialNo = null, ProductType = null, ModelName = null, ConsumerId = null, MycompanyId = null, ProviderCompanyId = null, WarrantyMonths = null, Ew_sDate = null, Ew_sMonth = null, Ew_sYear = null, Ew_sQuarter = null, Ew_sMonthTxt = null, Ew_sYearTxt = null, Ew_sQuarterTxt = null, PurchaseDate = null, Ew_sellingPrice = null, WarrantyExpDate = null, ProviderCompName = null, MonthlyRecognition = null, PurchasedMonth = null, PurchasedYear = null, PurchasedQuarter = null, P_QuarterTxt = null, P_MonthTxt = null, P_YearTxt = null;
        try {
            //JSONObject response = new JSONObject(EWJson);
            JSONObject cObj = EWJson.getJSONObject("EW");
            if (cObj.has("WARRANTY_ID"))
                WarrantyId = cObj.getInt("WARRANTY_ID");
            if (cObj.has("EQ_STOCK_ID"))
                Eq_stockId = cObj.getString("EQ_STOCK_ID");
            if (cObj.has("WARRANTY_NO"))
                WarrantyNo = cObj.getString("WARRANTY_NO");
            if (cObj.has("COUNTRY_CODE"))
                CountryCode = cObj.getString("COUNTRY_CODE");
            if (cObj.has("EW_REGISTERED_DATE"))
                Ew_regDate = cObj.getString("EW_REGISTERED_DATE");
            if (cObj.has("WARRANTY_TYPE"))
                WarrantyType = cObj.getString("WARRANTY_TYPE");
            if (cObj.has("INVOICE_NUMBER"))
                InvoiceNo = cObj.getString("INVOICE_NUMBER");
            if (cObj.has("BRAND_NAME"))
                BrandName = cObj.getString("BRAND_NAME");
            if (cObj.has("SERIAL_NO"))
                SerialNo = cObj.getString("SERIAL_NO");
            if (cObj.has("PRODUCT_TYPE"))
                ProductType = cObj.getString("PRODUCT_TYPE");
            if (cObj.has("MODEL_NAME"))
                ModelName = cObj.getString("MODEL_NAME");
            if (cObj.has("CONSUMER_ID"))
                ConsumerId = cObj.getString("CONSUMER_ID");
            if (cObj.has("MY_COMPANY_ID"))
                MycompanyId = cObj.getString("MY_COMPANY_ID");
            if (cObj.has("PROVIDER_COMPANY_ID"))
                ProviderCompanyId = cObj.getString("PROVIDER_COMPANY_ID");
            if (cObj.has("WARRANTY_MONTHS"))
                WarrantyMonths = cObj.getString("WARRANTY_MONTHS");
            if (cObj.has("EW_START_DATE"))
                Ew_sDate = cObj.getString("EW_START_DATE");
            if (cObj.has("EW_START_MONTH"))
                Ew_sMonth = cObj.getString("EW_START_MONTH");
            if (cObj.has("EW_START_YEAR"))
                Ew_sYear = cObj.getString("EW_START_YEAR");
            if (cObj.has("EW_START_QUARTER"))
                Ew_sQuarter = cObj.getString("EW_START_QUARTER");
            if (cObj.has("EW_START_MONTH_TXT"))
                Ew_sMonthTxt = cObj.getString("EW_START_MONTH_TXT");
            if (cObj.has("EW_START_YEAR_TXT"))
                Ew_sYearTxt = cObj.getString("EW_START_YEAR_TXT");
            if (cObj.has("EW_START_QUARTER_TXT"))
                Ew_sQuarterTxt = cObj.getString("EW_START_QUARTER_TXT");
            if (cObj.has("PURCHASED_DATE"))
                PurchaseDate = cObj.getString("PURCHASED_DATE");
            if (cObj.has("EW_SELLING_PRICE"))
                Ew_sellingPrice = cObj.getString("EW_SELLING_PRICE");
            if (cObj.has("WARRANTY_EXP_DT"))
                WarrantyExpDate = cObj.getString("WARRANTY_EXP_DT");
            if (cObj.has("PROVIDER_COMPANY_NAME"))
                ProviderCompName = cObj.getString("PROVIDER_COMPANY_NAME");
            if (cObj.has("MONTHLY_RECOGNITION"))
                MonthlyRecognition = cObj.getString("MONTHLY_RECOGNITION");
            if (cObj.has("PURCHASED_MONTH"))
                PurchasedMonth = cObj.getString("PURCHASED_MONTH");
            if (cObj.has("PURCHASED_YEAR"))
                PurchasedYear = cObj.getString("PURCHASED_YEAR");
            if (cObj.has("PURCHASED_QUARTER"))
                PurchasedQuarter = cObj.getString("PURCHASED_QUARTER");
            if (cObj.has("PURCHASED_MONTH_TXT"))
                P_MonthTxt = cObj.getString("PURCHASED_MONTH_TXT");
            if (cObj.has("PURCHASED_YEAR_TXT"))
                P_YearTxt = cObj.getString("PURCHASED_YEAR_TXT");
            if (cObj.has("PURCHASED_QUARTER_TXT"))
                P_QuarterTxt = cObj.getString("PURCHASED_QUARTER_TXT");
            if (cObj.has("EW_CATEGORY"))
                EW_CATEGORY = cObj.getString("EW_CATEGORY");


            ewTabDetails.add(new EWTabDetails(
                    WarrantyId, Eq_stockId, WarrantyNo, CountryCode, Ew_regDate, WarrantyType, InvoiceNo, BrandName, SerialNo, ProductType
                    , ModelName, ConsumerId, MycompanyId, ProviderCompanyId, WarrantyMonths
                    , Ew_sDate, Ew_sMonth, Ew_sYear, Ew_sQuarter, Ew_sMonthTxt, Ew_sYearTxt
                    , Ew_sQuarterTxt, PurchaseDate, Ew_sellingPrice, WarrantyExpDate, ProviderCompName
                    , MonthlyRecognition, PurchasedMonth, PurchasedYear, PurchasedQuarter, P_QuarterTxt
                    , P_MonthTxt, P_YearTxt,EW_CATEGORY));
            System.out.println("value :  " + Ew_sMonthTxt);
            System.out.println("value :  " + Ew_sMonth);

        } catch (Exception e) {
=======
      /*  {"EW": {"quotation_id": 8720, "brand_name": "Apple", "model_name": "test", "serial_no": "UNKNOWN_127343_8", "product_type": "Others",
    "email_id": "test222@gmail.com", "consumer_name": "test222 test222", "created_on": "16/10/2014","created_by": "Fixers II",
    "status": "Pending", "quotation_cost": [{ "item_description": "Onsite Charges","currency": "SGD","amount": 100,"srp_rate": 1,"marked_up_value": 100},
        { "item_description": "Service Charges","currency": "SGD","amount": 100,"srp_rate": 1,"marked_up_value": 100}],
        "quotation_history": [{"received_from": "Fixers II", "created_on": "16/10/2014","sent_to": "Daves Too","updated_on": null, "updated_by": " ",
         "status": "Pending" }]

    }}*/


    public static QuotationDetails parseQuotDetails(String json){
        Log.i("Mylog:", "QuotationDetails response:" + json);
        QuotationDetails quotDetails = new QuotationDetails();
        try{
            JSONObject response = new JSONObject(json);
            JSONObject object = response.getJSONObject("EW");
            MasterCache.quot_id.clear();MasterCache.quotBrand.clear();MasterCache.quotModel.clear();MasterCache.quotSerialNo.clear();
            MasterCache.quotProductType.clear();MasterCache.quotEmail.clear();MasterCache.quotConsumer.clear();MasterCache.quotCreate.clear();
            MasterCache.quotCreatedBy.clear();MasterCache.quotationStatus.clear();
            MasterCache.quotService.clear();MasterCache.quotServAmt.clear();
            MasterCache.quotServSrp.clear();MasterCache.quotServMark.clear();
            //MasterCache.quotCurrency.clear();
            MasterCache.quotReceivedFrom.clear();
            MasterCache.quotCreatedOn.clear();MasterCache.quotSentTo.clear();MasterCache.quotUpdatedOn.clear();MasterCache.quotUpdatedBy.clear();
            MasterCache.quotStatusHistory.clear();



            if(object.has("quotation_id"))
            quotId = object.getString("quotation_id");
            if(object.has("brand_name"))
            brand = object.getString("brand_name");
            if(object.has("model_name"))
            model = object.getString("model_name");
            if(object.has("serial_no"))
            sNo = object.getString("serial_no");
            if(object.has("product_type"))
            product = object.getString("product_type");
            if(object.has("email_id"))
            email = object.getString("email_id");
            if(object.has("consumer_name"))
            consumer = object.getString("consumer_name");
            if(object.has("created_on"))
            create = object.getString("created_on");
            if(object.has("created_by"))
            createdby = object.getString("created_by");
            if(object.has("status"))
            quotstatus = object.getString("status");
            quotDetails.setQuot_id(quotId);quotDetails.setQuotBrand(brand);quotDetails.setQuotModel(model);quotDetails.setQuotSerialNo(sNo);
            quotDetails.setQuotProductType(product);quotDetails.setQuotEmail(email);quotDetails.setQuotConsumer(consumer);quotDetails.setCreatedOn(create);
            quotDetails.setQuotCreatedBy(createdby);quotDetails.setQuotStatus(quotstatus);
            try {
    JSONArray array = object.optJSONArray("quotation_cost");
    if(object.has("quotation_cost")) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj1 = array.getJSONObject(i);

            if (obj1.has("item_description"))
                description = obj1.getString("item_description");
           /* if (obj1.has("currency"))
                currency = obj1.getString("currency");*/
            if (obj1.has("amount"))
                amount = obj1.getString("amount");
            if (obj1.has("srp_rate"))
                srp = obj1.getString("srp_rate");
            if (obj1.has("marked_up_value"))
                markUp = obj1.getString("marked_up_value");
        }
        quotDetails.setQuotService(description);
        quotDetails.setQuotServAmt(amount);quotDetails.setQuotServSrp(srp);quotDetails.setQuotServMark(markUp);

    } else {
        Log.i("QuotationCost:","no value");
    }
}catch (JSONException e)
{
    Log.i("QuotationCost:", "no value");
}
            try {
                    JSONArray fObj = object.optJSONArray("quotation_history");
                if(object.has("quotation_history")){
                    for (int index = 0; index < fObj.length(); index++) {
                        JSONObject cObj = fObj.getJSONObject(index);
                        if (cObj.has("received_from"))
                            received = cObj.getString("received_from");
                        if (cObj.has("created_on"))
                            created = cObj.getString("created_on");
                        if (cObj.has("sent_to"))
                            sent = cObj.getString("sent_to");
                        if (cObj.has("updated_on"))
                            update = cObj.getString("updated_on");
                        if (cObj.has("updated_by"))
                            updatedBy = cObj.getString("updated_by");
                        if (cObj.has("status"))
                            status = cObj.getString("status");
                    }  quotDetails.setQuotReceivedFrom(received);
                    quotDetails.setQuotCreatedOn(created);quotDetails.setQuotSentTo(sent);quotDetails.setQuotUpdatedOn(update);quotDetails.setQuotUpdatedBy(updatedBy);
                    quotDetails.setQuotStatusHistory(status);
                } else{
                    Log.i("QuotationHistory:","no value");
                }
            }catch (JSONException e){
                Log.i("QuotationHistory:", "no value");
            }

            //quotDetails.setQuotCurrency(currency);

            MasterCache.quot_id.add(quotDetails.getQuot_id());
            MasterCache.quotBrand.add(quotDetails.getQuotBrand());
            MasterCache.quotModel.add(quotDetails.getQuotModel());
            MasterCache.quotSerialNo.add(quotDetails.getQuotSerialNo());
            MasterCache.quotProductType.add(quotDetails.getQuotProductType());
            MasterCache.quotEmail.add(quotDetails.getQuotEmail());
            MasterCache.quotConsumer.add(quotDetails.getQuotConsumer());
            MasterCache.quotCreate.add(quotDetails.getCreatedOn());
            MasterCache.quotCreatedBy.add(quotDetails.getQuotCreatedBy());
            MasterCache.quotationStatus.add(quotDetails.getQuotStatus());
            MasterCache.quotService.add(quotDetails.getQuotService());
            MasterCache.quotServAmt.add(quotDetails.getQuotServAmt());
            MasterCache.quotServSrp.add(quotDetails.getQuotServSrp());
            MasterCache.quotServMark.add(quotDetails.getQuotServMark());
           // MasterCache.quotCurrency.add(quotDetails.getQuotCurrency());
            MasterCache.quotReceivedFrom.add(quotDetails.getQuotReceivedFrom());
            MasterCache.quotCreatedOn.add(quotDetails.getQuotCreatedOn());
            MasterCache.quotSentTo.add(quotDetails.getQuotSentTo());
            MasterCache.quotUpdatedOn.add(quotDetails.getQuotUpdatedOn());
            MasterCache.quotUpdatedBy.add(quotDetails.getQuotUpdatedBy());
            MasterCache.quotStatusHistory.add(quotDetails.getQuotStatusHistory());
        }
        catch (JSONException e) {
>>>>>>> origin
            e.printStackTrace();
        }
        return ewTabDetails;

    }

    public static List<EWTabProductDetails> parseEWPrJson(JSONObject EWPrJson) {
        List<EWTabProductDetails> ewTabProductDetails = new ArrayList<>();
        Integer ew_warranty_id = 0;
        String mw_warranty_id = null, warranty_no = null, user_id = null, purchase_from = null, purchase_dt = null,
                registered_dt = null, warranty_start_date = null, warranty_expiry_date = null, warranty_months = null,
                additional_info = null, bill_no = null, warranty_status = null, price = null, ic_no = null, email_id = null,
                first_name = null, last_name = null, middle_name = null, mobile = null, phone_no = null, office_ph = null,
                alternative_email = null, address_line1 = null, address_line2 = null, city = null, postal_code = null,
                user_country_name = null, country_name = null, brand_name = null, serial_no = null, product_type = null,
                product_id = null, model_name = null,state = null,passport = null,Upc_code = null,Voidrefund = null;
        try {
            //JSONObject response = new JSONObject(EWJson);
            JSONObject cObj = EWPrJson.getJSONObject("EW");
            if (cObj.has("ew_warranty_id"))
                ew_warranty_id = cObj.getInt("ew_warranty_id");
            if (cObj.has("mw_warranty_id"))
                mw_warranty_id = cObj.getString("mw_warranty_id");
            if (cObj.has("warranty_no"))
                warranty_no = cObj.getString("warranty_no");
            if (cObj.has("user_id"))
                user_id = cObj.getString("user_id");
            if (cObj.has("purchase_from"))
                purchase_from = cObj.getString("purchase_from");
            if (cObj.has("purchase_dt"))
                purchase_dt = cObj.getString("purchase_dt");
            if (cObj.has("registered_dt"))
                registered_dt = cObj.getString("registered_dt");
            if (cObj.has("warranty_start_date"))
                warranty_start_date = cObj.getString("warranty_start_date");
            if (cObj.has("warranty_expiry_date"))
                warranty_expiry_date = cObj.getString("warranty_expiry_date");
            if (cObj.has("warranty_months"))
                warranty_months = cObj.getString("warranty_months");
            if (cObj.has("additional_info"))
                additional_info = cObj.getString("additional_info");
            if (cObj.has("bill_no"))
                bill_no = cObj.getString("bill_no");
            if (cObj.has("warranty_no"))
                warranty_no = cObj.getString("warranty_no");
            if (cObj.has("price"))
                price = cObj.getString("price");
            if (cObj.has("ic_no"))
                ic_no = cObj.getString("ic_no");
            if (cObj.has("email_id"))
                email_id = cObj.getString("email_id");
            if (cObj.has("first_name"))
                first_name = cObj.getString("first_name");
            if (cObj.has("last_name"))
                last_name = cObj.getString("last_name");
            if (cObj.has("middle_name"))
                middle_name = cObj.getString("middle_name");
            if (cObj.has("mobile"))
                mobile = cObj.getString("mobile");
            if (cObj.has("phone_no"))
                phone_no = cObj.getString("phone_no");
            if (cObj.has("office_ph"))
                office_ph = cObj.getString("office_ph");
            if (cObj.has("alternative_email"))
                alternative_email = cObj.getString("alternative_email");
            if (cObj.has("address_line1"))
                address_line1 = cObj.getString("address_line1");
            if (cObj.has("address_line2"))
                address_line2 = cObj.getString("address_line2");
            if (cObj.has("city"))
                city = cObj.getString("city");
            if (cObj.has("postal_code"))
                postal_code = cObj.getString("postal_code");
            if (cObj.has("user_country"))
                user_country_name = cObj.getString("user_country");
            if (cObj.has("country_name"))
                country_name = cObj.getString("country_name");
            if (cObj.has("brand_name"))
                brand_name = cObj.getString("brand_name");
            if (cObj.has("serial_no"))
                serial_no = cObj.getString("serial_no");
            if (cObj.has("product_type"))
                product_type = cObj.getString("product_type");
            if (cObj.has("product_id"))
                product_id = cObj.getString("product_id");
            if (cObj.has("model_name"))
                model_name = cObj.getString("model_name");
            if (cObj.has("state"))
                state = cObj.getString("state");
            if (cObj.has("passport"))
                passport = cObj.getString("passport");
            if (cObj.has("upc_code"))
                Upc_code = cObj.getString("upc_code");
            if (cObj.has("Voidrefund"))
                Voidrefund = cObj.getString("Voidrefund");



            ewTabProductDetails.add(new EWTabProductDetails(
                    ew_warranty_id, mw_warranty_id, warranty_no, user_id, purchase_from, purchase_dt, registered_dt,
                    warranty_start_date, warranty_expiry_date, warranty_months, additional_info, bill_no, warranty_status,
                    price, ic_no, email_id, first_name, last_name, middle_name, mobile, phone_no, office_ph, alternative_email,
                    address_line1, address_line2, city, postal_code, user_country_name, country_name, brand_name, serial_no,
                    product_type, product_id, model_name,state,passport,Upc_code,Voidrefund));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ewTabProductDetails;

    }

    public static List<EWTabClaimHistory> parseEWTabCHistoryJson(JSONObject EWChJson) {
        List<EWTabClaimHistory> ewTabClaimHistory = new ArrayList<>();
        Integer user_id = 0, warranty_id = 0;
        String brand = null, model_name = null, eq_stock_id = null, warranty_provider_id = null, warranty_provider_name = null,
                purchased_date = null, max_claimable_amt = null,
                email_id = null, ew_expiry_date = null, ew_months = null, product_type = null, total_claimed_amt = null,
                claimable_balance = null,
                serial_no = null, ew_start_date = null, warranty_no = null, consumer_name = null, claim_history = null;
        try {
            //JSONObject response = new JSONObject(EWJson);
            JSONObject cobj = EWChJson.getJSONObject("EW");
            if (cobj.has("brand"))
                brand = cobj.getString("brand");
            if (cobj.has("user_id"))
                user_id = cobj.getInt("user_id");
            if (cobj.has("model_name"))
                model_name = cobj.getString("model_name");
            if (cobj.has("warranty_id"))
                warranty_id = cobj.getInt("warranty_id");
            if (cobj.has("eq_stock_id"))
                eq_stock_id = cobj.getString("eq_stock_id");
            if (cobj.has("warranty_provider_id"))
                warranty_provider_id = cobj.getString("warranty_provider_id");
            if (cobj.has("warranty_provider_name"))
                warranty_provider_name = cobj.getString("warranty_provider_name");
            if (cobj.has("purchased_date"))
                purchased_date = cobj.getString("purchased_date");
            if (cobj.has("max_claimable_amt"))
                max_claimable_amt = cobj.getString("max_claimable_amt");
            if (cobj.has("email_id"))
                email_id = cobj.getString("email_id");
            if (cobj.has("ew_expiry_date"))
                ew_expiry_date = cobj.getString("ew_expiry_date");
            if (cobj.has("ew_months"))
                ew_months = cobj.getString("ew_months");
            if (cobj.has("product_type"))
                product_type = cobj.getString("product_type");
            if (cobj.has("total_claimed_amt"))
                total_claimed_amt = cobj.getString("total_claimed_amt");
            if (cobj.has("claimable_balance"))
                claimable_balance = cobj.getString("claimable_balance");
            if (cobj.has("serial_no"))
                serial_no = cobj.getString("serial_no");
            if (cobj.has("ew_start_date"))
                ew_start_date = cobj.getString("ew_start_date");
            if (cobj.has("warranty_no"))
                warranty_no = cobj.getString("warranty_no");
            if (cobj.has("consumer_name"))
                consumer_name = cobj.getString("consumer_name");
            if (cobj.has("claim_history"))
                claim_history = cobj.getString("claim_history");

            ewTabClaimHistory.add(new EWTabClaimHistory(user_id, warranty_id,
                    brand, model_name, eq_stock_id, warranty_provider_id, warranty_provider_name, purchased_date, max_claimable_amt,
                    email_id, ew_expiry_date, ew_months, product_type, total_claimed_amt, claimable_balance,
                    serial_no, ew_start_date, warranty_no, consumer_name, claim_history));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ewTabClaimHistory;

    }

    public static List<EWTabCallLogs> parseEWCallLogJson(JSONObject Json) {
        List<EWTabCallLogs> EWTabCallLogs = new ArrayList<>();
        String case_id = null,
                eq_stock_id = null,
                sr_id = null,
                consumer_id = null,
                consumer_name = null,
                consumer_mobile = null,
                consumer_email = null,
                case_status = null,
                created_on = null,
                call_category = null,
                sr_status = null,
                sr_no = null,
                created_by = null;

        try {
            JSONArray array = Json.getJSONArray("EW");
            // JSONObject cobj = Json.getJSONObject("EW");
            for (int i = 0; i < array.length(); i++) {
                JSONObject cobj = array.getJSONObject(i);

                if (cobj.has("case_id"))
                    case_id = cobj.getString("case_id");
                if (cobj.has("eq_stock_id"))
                    eq_stock_id = cobj.getString("eq_stock_id");
                if (cobj.has("sr_id"))
                    sr_id = cobj.getString("sr_id");
                if (cobj.has("consumer_id"))
                    consumer_id = cobj.getString("consumer_id");
                if (cobj.has("consumer_name"))
                    consumer_name = cobj.getString("consumer_name");
                if (cobj.has("consumer_mobile"))
                    consumer_mobile = cobj.getString("consumer_mobile");
                if (cobj.has("consumer_email"))
                    consumer_email = cobj.getString("consumer_email");
                if (cobj.has("case_status"))
                    case_status = cobj.getString("case_status");
                if (cobj.has("created_on"))
                    created_on = cobj.getString("created_on");
                if (cobj.has("call_category"))
                    call_category = cobj.getString("call_category");
                if (cobj.has("sr_status"))
                    sr_status = cobj.getString("sr_status");
                if (cobj.has("sr_no"))
                    sr_no = cobj.getString("sr_no");
                if (cobj.has("created_by"))
                    created_by = cobj.getString("created_by");


                EWTabCallLogs.add(new EWTabCallLogs(case_id, eq_stock_id, sr_id, consumer_id, consumer_name, consumer_mobile,
                        consumer_email, case_status, created_on, call_category, sr_status, sr_no, created_by));

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return EWTabCallLogs;
    }

    public static List<CallCat> parseCallCatJson(JSONObject Json) {
        List<CallCat> CallCat = new ArrayList<>();
        Integer cat_id = 0;
        String  desc = null;

        try {
            JSONArray array = Json.getJSONArray("EW");
            // JSONObject cobj = Json.getJSONObject("EW");
            for (int i = 0; i < array.length(); i++) {
                JSONObject cobj = array.getJSONObject(i);

                if (cobj.has("code_id"))
                    cat_id = cobj.getInt("code_id");
                if (cobj.has("description"))
                    desc = cobj.getString("description");
                CallCat.add(new CallCat(cat_id, desc));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return CallCat;
    }
    public static List<Brands> parseBrandsJson(JSONObject Json) {
        List<Brands> Brands = new ArrayList<>();
        Integer brand_id = 0;
        String  brand_name = null;

        try {
            JSONArray array = Json.getJSONArray("brands");
            // JSONObject cobj = Json.getJSONObject("EW");
            for (int i = 0; i < array.length(); i++) {
                JSONObject cobj = array.getJSONObject(i);

                if (cobj.has("brand_id"))
                    brand_id = cobj.getInt("brand_id");
                if (cobj.has("brand_name"))
                    brand_name = cobj.getString("brand_name");
                Brands.add(new Brands(brand_id, brand_name));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return Brands;
    }

    public static List<Countries> parseCountriesJson(JSONObject Json) {
        List<Countries> Countries = new ArrayList<>();
        Integer country_id = 0,isd=0;
        String  country_code = null,name=null,currency_code=null;
        //  {"countries":[{"isd_code":"93","currency_code":"AFA","country_code":"AF","country_name":"AFGHANISTAN"}
        try {
            JSONArray array = Json.getJSONArray("countries");
            // JSONObject cobj = Json.getJSONObject("EW");
            for (int i = 0; i < array.length(); i++) {
                JSONObject cobj = array.getJSONObject(i);

                if (cobj.has("isd_code"))
                    isd = cobj.getInt("isd_code");
                if (cobj.has("currency_code"))
                    currency_code = cobj.getString("currency_code");
                if (cobj.has("country_code"))
                    country_code = cobj.getString("country_code");
                if (cobj.has("country_name"))
                    name = cobj.getString("country_name");
                Countries.add(new Countries(isd,currency_code,name,country_code));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return Countries;
    }

    public static List<ServiceList> parseServiceJson(JSONObject json) {
        List<ServiceList> serviceList = new ArrayList<>();
        try {
            JSONArray array = json.getJSONArray("EW");
            int size= array.length();
            for(int index = 0 ; index < size; index++) {
                JSONObject jObj = array.getJSONObject(index);
                String sr_id = jObj.getString("sr_id"),
                        sr_no = jObj.getString("sr_no"),
                        status = jObj.getString("status"),
                        createdOn = jObj.getString("created_on"),
                        consumerName = jObj.getString("consumer_name");
                serviceList.add(new ServiceList(sr_id, sr_no, status, createdOn, consumerName));
                System.out.println("sr_id: " + sr_id + " sr_no: " + sr_no+" status:"+status+"  createdOn:"+createdOn+"  consumer:"+consumerName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return serviceList;
    }


    public static List<QuotationList> parseQuotationList(JSONObject json){
        List<QuotationList> quotList = new ArrayList<>();
        try{
            JSONArray array = json.getJSONArray("EW");
            int size = array.length();
            for(int index =0; index<size; index++){
                JSONObject obj = array.getJSONObject(index);
                String quot_id = obj.getString("quotation_id"),
                        company_id = obj.getString("company_id"),
                        sr_id = obj.getString("sr_id"),
                        sr_no = obj.getString("sr_no"),
                        created_on = obj.getString("created_on"),
                        status = obj.getString("status");
                quotList.add(new QuotationList(quot_id,company_id,sr_id,sr_no,created_on,status));
                System.out.println("quot_id: " + quot_id + "comp_id:" + company_id + "sr_id" + sr_id + "sr_no" + sr_no + "created_on" + created_on + "status" + status);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return quotList;

    }

// /* public static List<QuotationDetails> parseQuotDetails(String json){
//        List<QuotationDetails> quotDetails = new ArrayList<>();
//      try {
//            JSONObject response = new JSONObject(json);
//            JSONObject object = response.getJSONObject("EW");
//
//          String quotId="",brand = "" , model = "",sNo = " ", product = "",email = "",consumer = "", create ="",createdby = "",
//                    quotstatus ="",description = "", currency = "", amount = "", srp = "", markUp = "",
//                                     received = "", created = "", sent = "", update = "", updatedBy = "", status = "";
//
//            JSONArray array = object.optJSONArray("quotation_cost");
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject obj1 = array.getJSONObject(i);
//
//            description = obj1.getString("item_description");
//            currency = obj1.getString("currency");
//            amount = obj1.getString("amount");
//            srp = obj1.getString("srp_rate");
//            markUp = obj1.getString("marked_up_value");
//            }
//
//          JSONArray fObj = object.optJSONArray("quotation_history");
//            for (int index = 0; index < fObj.length(); index++) {
//                JSONObject cObj = fObj.getJSONObject(index);
//
//            received = cObj.getString("received_from");
//            created = cObj.getString("created_on");
//            sent = cObj.getString("sent_to");
//            update = cObj.getString("updated_on");
//            updatedBy = cObj.getString("updated_by");
//            status = cObj.getString("status");
//            }
//            quotId = object.getString("quotation_id");
//            brand = object.getString("brand_name");
//            model = object.getString("model_name");
//            sNo = object.getString("serial_no");
//            product = object.getString("product_type");
//            email = object.getString("email_id");
//            consumer = object.getString("consumer_name");
//            create = object.getString("created_on");
//            createdby = object.getString("created_by");
//            quotstatus = object.getString("status");
//
//
//
//            quotDetails.add(new QuotationDetails(quotId, brand, model, sNo, product, email, consumer, create, createdby, quotstatus,
//                    description, currency, amount, srp, markUp, received, created, sent, update, updatedBy, status));
//
//            System.out.println("quotId: " + quotId + "brand: " + brand + "model: " + model + "sNo: " + sNo + "product: " + product +
//                    "email: " + email + "consumer: " + consumer + "create: " + create + "createdby: " + createdby + "Quotstatus: " +
//                    quotstatus + "desc:" + description + "currency:" + currency + "amount:" + amount + "srp:" + srp + "mark:" + markUp +
//                    "received:" + received + "created:" + created + "sent:" + sent + "update:" + update + "updateBy:" + updatedBy +
//                    "status:" + status);
//        }
//            catch (Exception e){
//                    e.printStackTrace();
//                }
//
//        return quotDetails;
//    }*/



//
//      /*  {"EW": {"quotation_id": 8720, "brand_name": "Apple", "model_name": "test", "serial_no": "UNKNOWN_127343_8", "product_type": "Others",
//    "email_id": "test222@gmail.com", "consumer_name": "test222 test222", "created_on": "16/10/2014","created_by": "Fixers II",
//    "status": "Pending", "quotation_cost": [{ "item_description": "Onsite Charges","currency": "SGD","amount": 100,"srp_rate": 1,"marked_up_value": 100},
//        { "item_description": "Service Charges","currency": "SGD","amount": 100,"srp_rate": 1,"marked_up_value": 100}],
//        "quotation_history": [{"received_from": "Fixers II", "created_on": "16/10/2014","sent_to": "Daves Too","updated_on": null, "updated_by": " ",
//         "status": "Pending" }]
//
//    }}*/


    public static QuotationDetails parseQuotDetails(String json){
        Log.i("Mylog:", "QuotationDetails response:" + json);
        QuotationDetails quotDetails = new QuotationDetails();
        try{
            JSONObject response = new JSONObject(json);
            JSONObject object = response.getJSONObject("EW");
            MasterCache.quot_id.clear();MasterCache.quotBrand.clear();MasterCache.quotModel.clear();MasterCache.quotSerialNo.clear();
            MasterCache.quotProductType.clear();MasterCache.quotEmail.clear();MasterCache.quotConsumer.clear();MasterCache.quotCreate.clear();
            MasterCache.quotCreatedBy.clear();MasterCache.quotationStatus.clear();
            MasterCache.quotService.clear();MasterCache.quotServAmt.clear();
            MasterCache.quotServSrp.clear();MasterCache.quotServMark.clear();
            //MasterCache.quotCurrency.clear();
            MasterCache.quotReceivedFrom.clear();
            MasterCache.quotCreatedOn.clear();MasterCache.quotSentTo.clear();MasterCache.quotUpdatedOn.clear();MasterCache.quotUpdatedBy.clear();
            MasterCache.quotStatusHistory.clear();


            String quotId="",brand = "" , model = "",sNo = " ", product = "",email = "",consumer = "", create ="",createdby = "",
                    quotstatus ="",description = "", //currency = "",
                    amount = "", srp = "", markUp = "",
                    received = "", created = "", sent = "", update = "", updatedBy = "", status = "";

            if(object.has("quotation_id"))
                quotId = object.getString("quotation_id");
            if(object.has("brand_name"))
                brand = object.getString("brand_name");
            if(object.has("model_name"))
                model = object.getString("model_name");
            if(object.has("serial_no"))
                sNo = object.getString("serial_no");
            if(object.has("product_type"))
                product = object.getString("product_type");
            if(object.has("email_id"))
                email = object.getString("email_id");
            if(object.has("consumer_name"))
                consumer = object.getString("consumer_name");
            if(object.has("created_on"))
                create = object.getString("created_on");
            if(object.has("created_by"))
                createdby = object.getString("created_by");
            if(object.has("status"))
                quotstatus = object.getString("status");

            try {
                JSONArray array = object.optJSONArray("quotation_cost");
                if(object.has("quotation_cost")) {
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj1 = array.getJSONObject(i);

                        if (obj1.has("item_description"))
                            description = obj1.getString("item_description");
           /* if (obj1.has("currency"))
                currency = obj1.getString("currency");*/
                        if (obj1.has("amount"))
                            amount = obj1.getString("amount");
                        if (obj1.has("srp_rate"))
                            srp = obj1.getString("srp_rate");
                        if (obj1.has("marked_up_value"))
                            markUp = obj1.getString("marked_up_value");
                    }
                } else {
                    Log.i("QuotationCost:","no value");
                }
            }catch (JSONException e)
            {
                Log.i("QuotationCost:","no value");
            }
            try {
                JSONArray fObj = object.optJSONArray("quotation_history");
                if(object.has("quotation_history")){
                    for (int index = 0; index < fObj.length(); index++) {
                        JSONObject cObj = fObj.getJSONObject(index);
                        if (cObj.has("received_from"))
                            received = cObj.getString("received_from");
                        if (cObj.has("created_on"))
                            created = cObj.getString("created_on");
                        if (cObj.has("sent_to"))
                            sent = cObj.getString("sent_to");
                        if (cObj.has("updated_on"))
                            update = cObj.getString("updated_on");
                        if (cObj.has("updated_by"))
                            updatedBy = cObj.getString("updated_by");
                        if (cObj.has("status"))
                            status = cObj.getString("status");
                    }
                } else{
                    Log.i("QuotationHistory:","no value");
                }
            }catch (JSONException e){
                Log.i("QuotationHistory:","no value");
            }

            quotDetails.setQuot_id(quotId);quotDetails.setQuotBrand(brand);quotDetails.setQuotModel(model);quotDetails.setQuotSerialNo(sNo);
            quotDetails.setQuotProductType(product);quotDetails.setQuotEmail(email);quotDetails.setQuotConsumer(consumer);quotDetails.setCreatedOn(create);
            quotDetails.setQuotCreatedBy(createdby);quotDetails.setQuotStatus(quotstatus);quotDetails.setQuotService(description);
            //quotDetails.setQuotCurrency(currency);
            quotDetails.setQuotServAmt(amount);quotDetails.setQuotServSrp(srp);quotDetails.setQuotServMark(markUp);quotDetails.setQuotReceivedFrom(received);
            quotDetails.setQuotCreatedOn(created);quotDetails.setQuotSentTo(sent);quotDetails.setQuotUpdatedOn(update);quotDetails.setQuotUpdatedBy(updatedBy);
            quotDetails.setQuotStatusHistory(status);

            MasterCache.quot_id.add(quotDetails.getQuot_id());
            MasterCache.quotBrand.add(quotDetails.getQuotBrand());
            MasterCache.quotModel.add(quotDetails.getQuotModel());
            MasterCache.quotSerialNo.add(quotDetails.getQuotSerialNo());
            MasterCache.quotProductType.add(quotDetails.getQuotProductType());
            MasterCache.quotEmail.add(quotDetails.getQuotEmail());
            MasterCache.quotConsumer.add(quotDetails.getQuotConsumer());
            MasterCache.quotCreate.add(quotDetails.getCreatedOn());
            MasterCache.quotCreatedBy.add(quotDetails.getQuotCreatedBy());
            MasterCache.quotationStatus.add(quotDetails.getQuotStatus());
            MasterCache.quotService.add(quotDetails.getQuotService());
            MasterCache.quotServAmt.add(quotDetails.getQuotServAmt());
            MasterCache.quotServSrp.add(quotDetails.getQuotServSrp());
            MasterCache.quotServMark.add(quotDetails.getQuotServMark());
            // MasterCache.quotCurrency.add(quotDetails.getQuotCurrency());
            MasterCache.quotReceivedFrom.add(quotDetails.getQuotReceivedFrom());
            MasterCache.quotCreatedOn.add(quotDetails.getQuotCreatedOn());
            MasterCache.quotSentTo.add(quotDetails.getQuotSentTo());
            MasterCache.quotUpdatedOn.add(quotDetails.getQuotUpdatedOn());
            MasterCache.quotUpdatedBy.add(quotDetails.getQuotUpdatedBy());
            MasterCache.quotStatusHistory.add(quotDetails.getQuotStatusHistory());
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return quotDetails;

    }

    public static ServiceDetails parseRequestJson(JSONObject response) {
        Log.i("myLog", "ServiceRequestDetails response:" + response.toString());
        ServiceDetails serviceDetails = new ServiceDetails();
        try {
            JSONObject jObj = response.getJSONObject("EW");
            MasterCache.srReqSrId.clear(); MasterCache.srReqSrNo.clear(); MasterCache.srReqStatus.clear();
            MasterCache.srCreatedOn.clear(); MasterCache.srReqFailureDesc.clear();
            MasterCache.srReqCreatedBy.clear(); MasterCache.srReqWarrantyStatus.clear(); MasterCache.srReqReturnCount.clear();
            MasterCache.srReqUserId.clear(); MasterCache.srReqFirstName.clear();
            MasterCache.srReqLastName.clear(); MasterCache.srReqCity.clear(); MasterCache.srReqCountryCode.clear();
            //MasterCache.srCountryCode.clear();
            MasterCache.srReqMobile.clear(); MasterCache.srReqIcNo.clear();
            MasterCache.srReqAddress.clear(); MasterCache.srReqState.clear(); MasterCache.srReqPostalCode.clear();
            MasterCache.srReqOtherDesc.clear(); MasterCache.srReqRemarks.clear();
            MasterCache.srReqProductStatus.clear(); MasterCache.srCompanyName.clear(); MasterCache.srReqBrandName.clear();
            MasterCache.srReqModelName.clear(); MasterCache.srReqSerialNo.clear(); MasterCache.srReqProdType.clear();

            String sr_id= "", sr_no = "", status = "", created_on = "", failure_desc = "", created_by = "", return_count = "",
                    user_id = "", first_name = "", last_name = "", city = "", warranty_status = "",
                    country_code = "", mobile = "",ic_no = "", address_line1 = "", state = "", postal_code = "", other_desc = "",
                    company_name = "", brand_name = "", model_name = "", serial_no = "",
                    remarks = "", product_type = "", product_status = "";
            if(jObj.has("sr_id"))
                sr_id = jObj.getString("sr_id");
            if(jObj.has("sr_no"))
                sr_no = jObj.getString("sr_no");
            if(jObj.has("status"))
                status = jObj.getString("status");

            if(jObj.has("created_on"))
                created_on = jObj.getString("created_on");

            if(jObj.has("failure_desc"))
                failure_desc = jObj.getString("failure_desc");
            if(jObj.has("warranty_status"))
                warranty_status = jObj.getString("warranty_status");
            if(jObj.has("created_by"))
                created_by = jObj.getString("created_by");
            if(jObj.has("return_count"))
                return_count = jObj.getString("return_count");
            if(jObj.has("user_id"))
                user_id = jObj.getString("user_id");
            if(jObj.has("first_name"))
                first_name = jObj.getString("first_name");
            if(jObj.has("last_name"))
                last_name = jObj.getString("last_name");
            if(jObj.has("city"))
                city = jObj.getString("city");
            if(jObj.has("country_code"))
                country_code = jObj.getString("country_code");
            if(jObj.has("mobile"))
                mobile = jObj.getString("mobile");
            if(jObj.has("ic_no"))
                ic_no = jObj.getString("ic_no");
            if(jObj.has("address_line1"))
                address_line1 = jObj.getString("address_line1");
            if(jObj.has("state"))
                state = jObj.getString("state");
            if(jObj.has("postal_code"))
                postal_code = jObj.getString("postal_code");
            if(jObj.has("other_desc"))
                other_desc = jObj.getString("other_desc");
            if(jObj.has("remarks"))
                remarks = jObj.getString("remarks");
            if(jObj.has("product_status"))
                product_status = jObj.getString("product_status");
            if(jObj.has("company_name"))company_name = jObj.getString("company_name");
            if(jObj.has("brand_name"))  brand_name = jObj.getString("brand_name");
            if(jObj.has("model_name")) model_name = jObj.getString("model_name");
            if(jObj.has("serial_no")) serial_no = jObj.getString("serial_no");
            if(jObj.has("product_type")) product_type = jObj.getString("product_type");

            serviceDetails.setSrId(sr_id); serviceDetails.setSrNo(sr_no); serviceDetails.setSrStatus(status);
            serviceDetails.setSrCreatedOn(created_on); serviceDetails.setSrFailureDesc(failure_desc);
            serviceDetails.setSrCreatedBy(created_by);   serviceDetails.setSrReqWarrantyStatus(warranty_status);
            serviceDetails.setSrReqReturnCount(return_count); serviceDetails.setSrReqUserId(user_id);
            serviceDetails.setSrReqFirstName(first_name);serviceDetails.setSrReqLastName(last_name); serviceDetails.setSrReqCity(city);
            serviceDetails.setSrReqCountryCode(country_code); serviceDetails.setSrReqMobile(mobile); serviceDetails.setSrReqIcNo(ic_no);
            serviceDetails.setSrReqAddress(address_line1); serviceDetails.setSrReqState(state);
            serviceDetails.setSrReqPostalCode(postal_code); serviceDetails.setSrReqOtherDesc(other_desc);
            serviceDetails.setSrReqRemarks(remarks); serviceDetails.setSrReqProductStatus(product_status);
            serviceDetails.setSrCompanyName(company_name); serviceDetails.setSrReqBrandName(brand_name);
            serviceDetails.setSrReqModelName(model_name);serviceDetails.setSrReqSerialNo(serial_no);
            serviceDetails.setSrReqProdType(product_type);

            MasterCache.srReqSrId.add(serviceDetails.getSrReqUserId()); MasterCache.srReqSrNo.add(serviceDetails.getSrNo());
            MasterCache.srReqStatus.add(serviceDetails.getSrStatus()); MasterCache.srCreatedOn.add(serviceDetails.getSrCreatedOn());
            MasterCache.srReqFailureDesc.add(serviceDetails.getSrFailureDesc());
            MasterCache.srReqCreatedBy.add(serviceDetails.getSrCreatedBy());
            MasterCache.srReqWarrantyStatus.add(serviceDetails.getSrReqWarrantyStatus());
            MasterCache.srReqReturnCount.add(serviceDetails.getSrReqReturnCount());
            MasterCache.srReqUserId.add(serviceDetails.getSrReqUserId());
            MasterCache.srReqFirstName.add(serviceDetails.getSrReqFirstName());
            MasterCache.srReqLastName.add(serviceDetails.getSrReqLastName()); MasterCache.srReqCity.add(serviceDetails.getSrReqCity());
            // MasterCache.srCountryCode.add(serviceDetails.getSrReqCountryCode());
            MasterCache.srReqMobile.add(serviceDetails.getSrReqMobile());
            MasterCache.srReqIcNo.add(serviceDetails.getSrReqIcNo()); MasterCache.srReqAddress.add(serviceDetails.getSrReqAddress());
            MasterCache.srReqState.add(serviceDetails.getSrReqState()); MasterCache.srReqPostalCode.add(serviceDetails.getSrReqPostalCode());
            MasterCache.srReqOtherDesc.add(serviceDetails.getSrReqOtherDesc()); MasterCache.srReqRemarks.add(serviceDetails.getSrReqRemarks());
            MasterCache.srReqProductStatus.add(serviceDetails.getSrReqProductStatus());
            MasterCache.srCompanyName.add(serviceDetails.getSrCompanyName());MasterCache.srReqCountryCode.add(serviceDetails.getSrReqCountryCode());
            MasterCache.srReqBrandName.add(serviceDetails.getSrReqBrandName()); MasterCache.srReqModelName.add(serviceDetails.getSrReqModelName());
            MasterCache.srReqSerialNo.add(serviceDetails.getSrReqSerialNo()); MasterCache.srReqProdType.add(serviceDetails.getSrReqProdType());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return serviceDetails;
    }

    public static void parseProductJson(JSONObject response) {
        Log.i("myLog", "ProductDetails response:" + response.toString());

   /*     ProductDetails productDetails = new ProductDetails();*/
        try {
            JSONObject jObj = response.getJSONObject("EW");
            MasterCache.prdReqSrId.clear();
            MasterCache.prdReqSrNo.clear();
            MasterCache.prdReqUserId.clear();
            MasterCache.prdReqFirstName.clear();
            MasterCache.prdReqMiddleName.clear();
            MasterCache.prdReqState.clear();
            MasterCache.prdReqLastName.clear();
            MasterCache.prdReqCity.clear();
            MasterCache.prdReqCountryCode.clear();
            MasterCache.prdReqMobile.clear();
            MasterCache.prdReqOfficePhone.clear();
            MasterCache.prdReqAltMail.clear();
            MasterCache.prdReqPhoneNo.clear();
            MasterCache.prdReqIcNo.clear();
            MasterCache.prdReqAddLine1.clear();
            MasterCache.prdReqPostalCode.clear();
            MasterCache.prdReqCompanyName.clear();
            MasterCache.prdReqBrandName.clear();
            MasterCache.prdReqModelName.clear();
            MasterCache.prdReqSerialNo.clear();
            MasterCache.prdReqProductType.clear();
            MasterCache.prdReqBillNo.clear();
            MasterCache.prdReqWarrantyNo.clear();
            MasterCache.prdReqWarrantyMonths.clear();
            MasterCache.prdReqWarrantyStartDate.clear();
            MasterCache.prdReqWarrantyEndDate.clear();
            MasterCache.prdReqPurchaseDate.clear();
            MasterCache.prdReqPrice.clear();
            MasterCache.prdReqRegDate.clear();
            MasterCache.prdReqCountryName.clear();
            MasterCache.prdReqAddInfo.clear();
            MasterCache.prdReqPurchaseFrom.clear();

            String sr_id = "", sr_no = "", user_id = " ", first_name = "", middle_name = " ", last_name = "", city = "", country_code = "",
                    mobile = "", offce_phone = " ", alternative_email = " ", phone_no = " ", ic_no = "", address_line1 = "", state = "",
                    postal_code = "", company_name = "", brand_name = "", model_name = "", serial_no = "", product_type = "",
                    bill_no = " ", warranty_no = " ", warranty_months = "", warranty_start_date = "", warranty_end_date = "",
                    purchase_dt = "", price = "", registered_dt = "", country_name = "", additional_info = "", purchase_from = "";
            if (jObj.has("sr_id"))
                sr_id = jObj.getString("sr_id");
            if (jObj.has("sr_no"))
                sr_no = jObj.getString("sr_no");
            if (jObj.has("user_id"))
                user_id = jObj.getString("user_id");
            if (jObj.has("first_name"))
                first_name = jObj.getString("first_name");
            if (jObj.has("middle_name"))
                middle_name = jObj.getString("middle_name");
            if (jObj.has("last_name"))
                last_name = jObj.getString("last_name");
            if(jObj.has("state"))
                state = jObj.getString("state");
            if (jObj.has("city"))
                city = jObj.getString("city");
            if (jObj.has("country_code"))
                country_code = jObj.getString("country_code");
            if (jObj.has("mobile"))
                mobile = jObj.getString("mobile");
            if (jObj.has("offce_phone"))
                offce_phone = jObj.getString("offce_phone");
            if (jObj.has("alternative_email"))
                alternative_email = jObj.getString("alternative_email");
            if (jObj.has("phone_no"))
                phone_no = jObj.getString("phone_no");
            if (jObj.has("ic_no"))
                ic_no = jObj.getString("ic_no");
            if (jObj.has("address_line1"))
                address_line1 = jObj.getString("address_line1");
            if (jObj.has("state"))
                state = jObj.getString("state");
            if (jObj.has("postal_code"))
                postal_code = jObj.getString("postal_code");
            if (jObj.has("company_name")) company_name = jObj.getString("company_name");
            if (jObj.has("brand_name")) brand_name = jObj.getString("brand_name");
            if (jObj.has("model_name")) model_name = jObj.getString("model_name");
            if (jObj.has("serial_no")) serial_no = jObj.getString("serial_no");
            if (jObj.has("product_type")) product_type = jObj.getString("product_type");
            if (jObj.has("bill_no")) bill_no = jObj.getString("bill_no");
            if (jObj.has("warranty_no")) warranty_months = jObj.getString("warranty_no");
            if (jObj.has("warranty_months")) warranty_months = jObj.getString("warranty_months");
            if (jObj.has("warranty_start_date"))
                warranty_start_date = jObj.getString("warranty_start_date");
            if (jObj.has("warranty_end_date"))
                warranty_end_date = jObj.getString("warranty_end_date");
            if (jObj.has("purchase_dt")) purchase_dt = jObj.getString("purchase_dt");
            if (jObj.has("price")) price = jObj.getString("price");
            if (jObj.has("additional_info")) additional_info = jObj.getString("additional_info");
            if (jObj.has("purchase_from")) purchase_from = jObj.getString("purchase_from");
            if (jObj.has("country_name")) country_name = jObj.getString("country_name");
            if(jObj.has("registered_dt")) registered_dt = jObj.getString("registered_dt");

            MasterCache.prdReqSrId.add(sr_id);
            MasterCache.prdReqSrNo.add(sr_no);
            MasterCache.prdReqUserId.add(user_id);
            MasterCache.prdReqCountryName.add(country_name);
            MasterCache.prdReqFirstName.add(first_name);
            MasterCache.prdReqMiddleName.add(middle_name);
            MasterCache.prdReqLastName.add(last_name);
            MasterCache.prdReqCity.add(city);
            MasterCache.prdReqCountryCode.add(country_code);
            MasterCache.prdReqMobile.add(mobile);
            MasterCache.prdReqOfficePhone.add(offce_phone);
            MasterCache.prdReqAltMail.add(alternative_email);
            MasterCache.prdReqPhoneNo.add(phone_no);
            MasterCache.prdReqIcNo.add(ic_no);
            MasterCache.prdReqState.add(state);
            MasterCache.prdReqAddLine1.add(address_line1);
            MasterCache.prdReqPostalCode.add(postal_code);
            MasterCache.prdReqCompanyName.add(company_name);
            MasterCache.prdReqBrandName.add(brand_name);
            MasterCache.prdReqModelName.add(model_name);
            MasterCache.prdReqSerialNo.add(serial_no);
            MasterCache.prdReqProductType.add(product_type);
            MasterCache.prdReqBillNo.add(bill_no);
            MasterCache.prdReqWarrantyNo.add(warranty_no);
            MasterCache.prdReqWarrantyMonths.add(warranty_months);
            MasterCache.prdReqWarrantyStartDate.add(warranty_start_date);
            MasterCache.prdReqWarrantyEndDate.add(warranty_end_date);
            MasterCache.prdReqPurchaseDate.add(purchase_dt);
            MasterCache.prdReqPrice.add(price);
            MasterCache.prdReqRegDate.add(registered_dt);
            MasterCache.prdReqAddInfo.add(additional_info);
            MasterCache.prdReqPurchaseFrom.add(purchase_from);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//  /*  if (object.has("quotation_id")) {
//                   String quotId = object.getString("quotation_id");
//
//                }*/
//              /*  if(response.has("quotation_id")) quot_id = response.getString("quotation_id");
//                if(response.has("brand_name")) brand = response.getString("brand_name");
//                if(response.has("model_name")) model = response.getString("model_name");
//                if(response.has("serial_no"))  sNo = response.getString("serial_no");
//                if(response.has("product_type")) product = response.getString("product_type");
//                if(response.has("email_id")) email = response.getString("email_id");
//                if(response.has("consumer_name")) consumer = response.getString("consumer_name");
//                if(response.has("created_on")) create = response.getString("created_on");
//                if(response.has("created_by")) createdBy = response.getString("created_by");
//                if(response.has("status")) quotStatus = response.getString("status");*/
//
///*System.out.println("quotId: " + quotId + object.getString("quotation_id") + "brand: " + object.getString("brand_name") + "model: " + object.getString("model_name") +
//        "sNo: " + object.getString("serial_no") + "product: " + object.getString("product_type") +
//        "email: " + object.getString("email_id") + "consumer: " + object.getString("consumer_name") +
//        "create: " + object.getString("created_on") + "createdby: " + object.getString("created_by") +
//        "quotstatus: " + object.getString("status"));*/
//
//   /*   }
//                JSONArray array = response.getJSONArray("quotation_cost");
//                for (int i = 0; i < array.length(); i++) {
//                    JSONObject cObj = array.getJSONObject(i);*/

    public static List<EWCallLogsCaseDetails> parseEWCallLogDetailsJson(JSONObject json) {
        List<EWCallLogsCaseDetails> EWCallLogsCaseDetails = new ArrayList<>();

        String case_id=null,log_id = null,sr_id = null,log_desc = null,
                logged_by = null,logged_on = null,sr_number = null,sr_status = null;
        try {
            JSONArray array = json.getJSONArray("EW");
            // JSONObject cobj = Json.getJSONObject("EW");
            for (int i = 0; i < array.length(); i++) {
                JSONObject cObj = array.getJSONObject(i);
                if (cObj.has("case_id"))
                    case_id = cObj.getString("case_id");
                if (cObj.has("log_id"))
                    log_id = cObj.getString("log_id");
                if (cObj.has("sr_id"))
                    sr_id = cObj.getString("sr_id");
                if (cObj.has("log_desc"))
                    log_desc = cObj.getString("log_desc");
                if (cObj.has("logged_by"))
                    logged_by = cObj.getString("logged_by");
                if (cObj.has("logged_on"))
                    logged_on = cObj.getString("logged_on");
                if (cObj.has("sr_number"))
                    sr_number = cObj.getString("sr_number");
                if (cObj.has("sr_status"))
                    sr_status = cObj.getString("sr_status");


                EWCallLogsCaseDetails.add(new EWCallLogsCaseDetails(case_id,
                        log_id,sr_id,log_desc,logged_by,logged_on,
                        sr_number,sr_status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return EWCallLogsCaseDetails;
    }

}

