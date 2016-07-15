package com.rever.rever_b2b.utils;


import com.rever.rever_b2b.model.CallCat;
import com.rever.rever_b2b.model.CaseLog;
import com.rever.rever_b2b.model.EWCallLogsCaseDetails;
import com.rever.rever_b2b.model.EWDetails;
import com.rever.rever_b2b.model.EWTabCallLogs;
import com.rever.rever_b2b.model.EWTabClaimHistory;
import com.rever.rever_b2b.model.EWTabDetails;
import com.rever.rever_b2b.model.EWTabProductDetails;
import com.rever.rever_b2b.model.Failures;
import com.rever.rever_b2b.model.Quotation;
import com.rever.rever_b2b.model.ServiceRequest;
import com.rever.rever_b2b.model.StockBalance;
import com.rever.rever_b2b.model.UsedProduct;
import com.rever.rever_b2b.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matheswari on 3/25/2016.
 */
public class JsonUtils {

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


    public static List<EWTabDetails> parseEWJson(JSONObject EWJson) {
        List<EWTabDetails> ewTabDetails = new ArrayList<>();
        Integer WarrantyId = 0;
        String Eq_stockId = null, WarrantyNo = null, CountryCode = null, Ew_regDate = null, WarrantyType = null, InvoiceNo = null, BrandName = null, SerialNo = null, ProductType = null, ModelName = null, ConsumerId = null, MycompanyId = null, ProviderCompanyId = null, WarrantyMonths = null, Ew_sDate = null, Ew_sMonth = null, Ew_sYear = null, Ew_sQuarter = null, Ew_sMonthTxt = null, Ew_sYearTxt = null, Ew_sQuarterTxt = null, PurchaseDate = null, Ew_sellingPrice = null, WarrantyExpDate = null, ProviderCompName = null, MonthlyRecognition = null, PurchasedMonth = null, PurchasedYear = null, PurchasedQuarter = null, P_QuarterTxt = null, P_MonthTxt = null, P_YearTxt = null;
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

            ewTabDetails.add(new EWTabDetails(
                    WarrantyId, Eq_stockId, WarrantyNo, CountryCode, Ew_regDate, WarrantyType, InvoiceNo, BrandName, SerialNo, ProductType
                    , ModelName, ConsumerId, MycompanyId, ProviderCompanyId, WarrantyMonths
                    , Ew_sDate, Ew_sMonth, Ew_sYear, Ew_sQuarter, Ew_sMonthTxt, Ew_sYearTxt
                    , Ew_sQuarterTxt, PurchaseDate, Ew_sellingPrice, WarrantyExpDate, ProviderCompName
                    , MonthlyRecognition, PurchasedMonth, PurchasedYear, PurchasedQuarter, P_QuarterTxt
                    , P_MonthTxt, P_YearTxt));
            System.out.println("value :  " + Ew_sMonthTxt);
            System.out.println("value :  " + Ew_sMonth);

        } catch (Exception e) {
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
                product_id = null, model_name = null;
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


            ewTabProductDetails.add(new EWTabProductDetails(
                    ew_warranty_id, mw_warranty_id, warranty_no, user_id, purchase_from, purchase_dt, registered_dt,
                    warranty_start_date, warranty_expiry_date, warranty_months, additional_info, bill_no, warranty_status,
                    price, ic_no, email_id, first_name, last_name, middle_name, mobile, phone_no, office_ph, alternative_email,
                    address_line1, address_line2, city, postal_code, user_country_name, country_name, brand_name, serial_no,
                    product_type, product_id, model_name));

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


    public static List<EWCallLogsCaseDetails> parseEWCallLogDetailsJson(JSONObject Json) {
        List<EWCallLogsCaseDetails> EWTabCallLogsDetails = new ArrayList<>();
         String case_id = null,
                log_id = null,
                sr_id = null,
                log_desc = null,
                logged_by = null,
                logged_on = null,
                sr_number = null,
                sr_status = null;

        try {
            JSONArray array = Json.getJSONArray("EW");
            // JSONObject cobj = Json.getJSONObject("EW");
            for (int i = 0; i < array.length(); i++) {
                JSONObject cobj = array.getJSONObject(i);

                if (cobj.has("case_id"))
                    case_id = cobj.getString("case_id");
                if (cobj.has("log_id"))
                    log_id = cobj.getString("log_id");
                if (cobj.has("sr_id"))
                    sr_id = cobj.getString("sr_id");
                if (cobj.has("log_desc"))
                    log_desc = cobj.getString("log_desc");
                if (cobj.has("logged_by"))
                    logged_by = cobj.getString("logged_by");
                if (cobj.has("logged_on"))
                    logged_on = cobj.getString("logged_on");
                if (cobj.has("sr_number"))
                    sr_number = cobj.getString("sr_number");
                if (cobj.has("sr_status"))
                    sr_status = cobj.getString("sr_status");

                EWTabCallLogsDetails.add(new EWCallLogsCaseDetails(case_id, log_id, sr_id, log_desc, logged_by, logged_on,
                        sr_number, sr_status));

            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return EWTabCallLogsDetails;
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

    }

