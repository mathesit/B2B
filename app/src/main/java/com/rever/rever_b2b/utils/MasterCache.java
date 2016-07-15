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
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Matheswari on 3/25/2016.
 */
public class MasterCache {
    public static List<EWDetails> EWDetailsList = new ArrayList<>();
    public static List<String> warrantyId = new ArrayList<>();
    public static Map<String, String> warrantyNo = new HashMap<>();
    public static Map<String, String> purchaseFrom = new HashMap<>();
    public static Map<String, String> purchaseDate = new HashMap<>();
    public static Map<String, String> warrantyStartDate = new HashMap<>();
    public static Map<String, String> warrantyEndDate = new HashMap<>();
    public static Map<String, String> email = new HashMap<>();
    public static Map<String, String> consumerName = new HashMap<>();
    public static Map<String, String> brandName = new HashMap<>();
    public static Map<String, String> serialNo = new HashMap<>();
    public static Map<String, String> productType = new HashMap<>();
    public static Map<String, String> modelName = new HashMap<>();

    public static String listPosition_id = null;
    public static int spinnerPosition_id = 0;
    public static String closeCaseRequest = null;

    public static ArrayList<HashMap<String,String>>  jo= new ArrayList();

    public static List<EWTabCallLogs> EWCallLogsList = new ArrayList<>();
    public static List<String> cl_case_id = new ArrayList<>();
    public static Map<String, String> cl_eq_stock_id = new HashMap<>();
    public static Map <String, String> cl_sr_id = new HashMap<>() ;
    public static Map <String, String> cl_consumer_id = new HashMap<>();
    public static Map <String, String> cl_consumer_name = new HashMap<>();
    public static Map <String, String> cl_consumer_mobile = new HashMap<>();
    public static Map <String, String> cl_consumer_email = new HashMap<>();
    public static Map <String, String> cl_case_status = new HashMap<>();
    public static Map <String, String> cl_created_on = new HashMap<>();
    public static Map <String, String> cl_call_category = new HashMap<>();
    public static Map <String, String> cl_sr_status = new HashMap<>();
    public static Map <String, String> cl_sr_no = new HashMap<>();
    public static Map <String, String> cl_created_by = new HashMap<>();
    public static ArrayList<HashMap<String,String>>  cljo= new ArrayList();

    public static List<EWCallLogsCaseDetails> EWcase_details = new ArrayList<>();
    public static List<String> cld_case_id = new ArrayList<>();
    public static Map<String, String> cld_log_id = new HashMap<>();
    public static Map <String, String> cld_log_desc = new HashMap<>() ;
    public static Map <String, String> cld_logged_by = new HashMap<>();
    public static Map <String, String> cld_logged_on = new HashMap<>();
    public static Map <String, String> cld_sr_id = new HashMap<>();
    public static Map <String, String> cld_sr_number = new HashMap<>();
    public static Map <String, String> cld_sr_status = new HashMap<>();

    public static ArrayList<HashMap<String,String>>  callLogEntry= new ArrayList();

    public static List<EWTabDetails> EWDetailsTab = new ArrayList<>();
    public static List<Integer> warrId = new ArrayList<>();
    public static Map<Integer, String> eq_stockId = new HashMap<>();
    public static Map<Integer, String> warrNo = new HashMap<>();
    public static Map<Integer, String> countryCode = new HashMap<>();
    public static Map<Integer, String> ew_regDate = new HashMap<>();
    public static Map<Integer, String> warrType = new HashMap<>();
    public static Map<Integer, String> invoiceNo = new HashMap<>();
    public static Map<Integer, String> pbrandname = new HashMap<>();
    public static Map<Integer, String> pserialNo = new HashMap<>();
    public static Map<Integer, String> pproductType = new HashMap<>();
    public static Map<Integer, String> pmodelName = new HashMap<>();
    public static Map<Integer, String> consumerId = new HashMap<>();
    public static Map<Integer, String> mycompanyId = new HashMap<>();
    public static Map<Integer, String> providerCompId = new HashMap<>();
    public static Map<Integer, String> warrMonths = new HashMap<>();
    public static Map<Integer, String> warrSdate = new HashMap<>();
    public static Map<Integer, String> warrSyear = new HashMap<>();
    public static Map<Integer, String> warrSmonth = new HashMap<>();
    public static Map<Integer, String> warrSquarter = new HashMap<>();
    public static Map<Integer, String> warrSMonthTxt = new HashMap<>();
    public static Map<Integer, String> warrSyearTxt = new HashMap<>();
    public static Map<Integer, String> warrSquarterTxt = new HashMap<>();
    public static Map<Integer, String> ppurchaseDate = new HashMap<>();
    public static Map<Integer, String> warrSellingPrice = new HashMap<>();
    public static Map<Integer, String> warrExpDate = new HashMap<>();
    public static Map<Integer, String> providerCompName = new HashMap<>();
    public static Map<Integer, String> monthlyRecog = new HashMap<>();
    public static Map<Integer, String> purchasedMonth = new HashMap<>();
    public static Map<Integer, String> purchasedYear = new HashMap<>();
    public static Map<Integer, String> purchasedQuarter = new HashMap<>();
    public static Map<Integer, String> p_QuarterTxt = new HashMap<>();
    public static Map<Integer, String> p_MonthTxt = new HashMap<>();
    public static Map<Integer, String> p_YearTxt = new HashMap<>();

    public static List<EWTabProductDetails> EWPDetailsTab = new ArrayList<>();
    public static List<Integer> PrewwarrId = new ArrayList<>();
    public static Map<Integer, String> mw_waarId = new HashMap<>();
    public static Map<Integer, String> prwarrNo = new HashMap<>();
    public static Map<Integer, String> prUserId = new HashMap<>();
    public static Map<Integer, String> prPurFrom = new HashMap<>();
    public static Map<Integer, String> prPurDate = new HashMap<>();
    public static Map<Integer, String> prRegDate = new HashMap<>();
    public static Map<Integer, String> prWsDate = new HashMap<>();
    public static Map<Integer, String> prExpDate = new HashMap<>();
    public static Map<Integer, String> prWarrMonths = new HashMap<>();
    public static Map<Integer, String> prAddiInfo = new HashMap<>();
    public static Map<Integer, String> prBill = new HashMap<>();
    public static Map<Integer, String> prWarrStatus = new HashMap<>();
    public static Map<Integer, String> prPrice = new HashMap<>();
    public static Map<Integer, String> prIcNo = new HashMap<>();
    public static Map<Integer, String> prEmail = new HashMap<>();
    public static Map<Integer, String> prFname = new HashMap<>();
    public static Map<Integer, String> prMname = new HashMap<>();
    public static Map<Integer, String> prLname = new HashMap<>();
    public static Map<Integer, String> prMobile = new HashMap<>();
    public static Map<Integer, String> prPhone = new HashMap<>();
    public static Map<Integer, String> prOffiPhone = new HashMap<>();
    public static Map<Integer, String> prAlterEmail = new HashMap<>();
    public static Map<Integer, String> prAddLine1 = new HashMap<>();
    public static Map<Integer, String> prAddLine2 = new HashMap<>();
    public static Map<Integer, String> prCity = new HashMap<>();
    public static Map<Integer, String> prPostal = new HashMap<>();
    public static Map<Integer, String> prUCountry = new HashMap<>();
    public static Map<Integer, String> prcountryName = new HashMap<>();
    public static Map<Integer, String> prBrandname = new HashMap<>();
    public static Map<Integer, String> prSerialno = new HashMap<>();
    public static Map<Integer, String> prProductType = new HashMap<>();
    public static Map<Integer, String> prProductId = new HashMap<>();
    public static Map<Integer, String> prModelName = new HashMap<>();


    public static List<EWTabClaimHistory> EWTabCHistory = new ArrayList<>();
    public static List<Integer> ch_warranty_id = new ArrayList<>();
    public static Map<Integer, String> ch_brand = new HashMap<>();
    public static Map<Integer, Integer> ch_user_id = new HashMap<>();
    public static Map<Integer, String> ch_model_name = new HashMap<>();
    public static Map<Integer, String> ch_eq_stock_id = new HashMap<>();
    public static Map<Integer, String> ch_warranty_provider_id = new HashMap<>();
    public static Map<Integer, String> ch_warranty_provider_name = new HashMap<>();
    public static Map<Integer, String> ch_purchased_date = new HashMap<>();
    public static Map<Integer, String> ch_max_claimable_amt = new HashMap<>();
    public static Map<Integer, String> ch_email_id = new HashMap<>();
    public static Map<Integer, String> ch_ew_expiry_date = new HashMap<>();
    public static Map<Integer, String> ch_ew_months = new HashMap<>();
    public static Map<Integer, String> ch_product_type = new HashMap<>();
    public static Map<Integer, String> ch_total_claimed_amt = new HashMap<>();
    public static Map<Integer, String> ch_claimable_balance = new HashMap<>();
    public static Map<Integer, String> ch_serial_no = new HashMap<>();
    public static Map<Integer, String> ch_ew_start_date = new HashMap<>();
    public static Map<Integer, String> ch_warranty_no = new HashMap<>();
    public static Map<Integer, String> ch_consumer_name = new HashMap<>();
    public static Map<Integer, String> ch_claim_history = new HashMap<>();

    public static List<CallCat> cl_catList = new ArrayList<>();
    public static List<Integer> cat_id = new ArrayList<>();
    public static List<String> cat_desc = new ArrayList<>();


    public static List<User> userList = new ArrayList<>();
    public static List<Integer> userId = new ArrayList<>();
    public static Map<Integer, Integer> companyId = new HashMap<>();
    public static Map<Integer, String> userSessionToken = new HashMap<>();
    public static Map<Integer, String> userFirstName = new HashMap<>();
    public static Map<Integer, String> userCountryCode = new HashMap<>();
    public static Map<Integer, String> userCity = new HashMap<>();
    public static Map<Integer, String> userEmail = new HashMap<>();
    public static Map<Integer, String> userType = new HashMap<>();


    public static List<StockBalance> stockList = new ArrayList<>();
    public static List<Integer> stockCount = new ArrayList<>();
    public static List<String> stockBrand = new ArrayList<>(), stockModel = new ArrayList<>(), stockProdType = new ArrayList<>();

    public static List<Failures> failureList = new ArrayList<>();
    public static List<Integer> failureCount = new ArrayList<>();
    public static List<String> failureBrand = new ArrayList<>(), failureModel = new ArrayList<>(), failureProdType = new ArrayList<>();

    public static List<CaseLog> caseLogList = new ArrayList<>();
    public static List<Integer> caseLogCount = new ArrayList<>();

    public static List<ServiceRequest> srList = new ArrayList<>();
    public static List<Integer> srCount = new ArrayList<>();

    public static List<Quotation> pendingQuoteList = new ArrayList<>();
    public static List<Integer> pendingQuoteCount = new ArrayList<>();

    public static List<UsedProduct> usedProductList = new ArrayList<>();
    public static List<Integer> usedProdQty = new ArrayList<>(), usedProdRank = new ArrayList<>();
    public static List<String> usedProdPartNo = new ArrayList<>();

    public static void saveEWDetailsCache(JSONObject EWDJson) {
        EWDetailsList = JsonUtils.parseEWDetailsJson(EWDJson);
        warrantyId.clear();
        warrantyNo.clear();
        purchaseFrom.clear();
        purchaseDate.clear();
        warrantyStartDate.clear();
        warrantyEndDate.clear();
        email.clear();
        consumerName.clear();
        brandName.clear();
        serialNo.clear();
        productType.clear();
        modelName.clear();
        for(EWDetails b : EWDetailsList) {
            String wId = b.getwarrantyId();
            warrantyId.add(wId);
            warrantyNo.put(wId, b.getwarrantyNo());
            purchaseFrom.put(wId, b.getpurchaseFrom());
            purchaseDate.put(wId, b.getpurchaseDate());
            warrantyStartDate.put(wId, b.getwarrantyStartDate());
            warrantyEndDate.put(wId, b.getwarrantyEndDate());
            email.put(wId, b.getEmail());
            consumerName.put(wId, b.getconsumerName());
            brandName.put(wId, b.getbrandName());
            serialNo.put(wId, b.getserialNo());
            productType.put(wId, b.getproductType());
            modelName.put(wId, b.getmodelName());

            HashMap<String, String> hm =new HashMap<>();
            hm.put("brand_name",b.getmodelName());
            hm.put("product_type",b.getproductType());
            hm.put("serial_no",b.getserialNo());
            hm.put("consumer",b.getconsumerName());
            hm.put("warranty_id",b.getwarrantyId());
            jo.add(hm);

        }
    }
    public static void saveEWCallLogsDetailsCache(JSONObject EWDJson) {
        EWcase_details = JsonUtils.parseEWCallLogDetailsJson(EWDJson);
        cld_case_id.clear();
        cld_log_desc.clear();
        cld_log_id.clear();
        cld_logged_by.clear();
        cld_logged_on.clear();
        cld_sr_id.clear();
        cld_sr_number.clear();
        cld_sr_status.clear();

        for(EWCallLogsCaseDetails b : EWcase_details) {
            String cId = b.getcase_id();
            cld_case_id.add(cId);

            cld_log_id.put(cId, b.getlog_id());
            cld_sr_id.put(cId, b.getsr_id());
            cld_log_desc.put(cId, b.getlog_desc());
            cld_logged_by.put(cId, b.getlogged_by());
            cld_logged_on.put(cId, b.getlogged_on());
            cld_sr_number.put(cId, b.getsr_number());
            cld_sr_status.put(cId, b.getsr_status());

            //"case_id":14169,"log_desc":"bharath tested","logged_by":"Vinson Lee","logged_on":"14\/07\/2016","log_id":18480
            HashMap<String, String> hm =new HashMap<>();
            hm.put("log_desc",b.getlog_desc());
            hm.put("logged_on", b.getlogged_on());
            hm.put("logged_by",b.getlogged_by());
            callLogEntry.add(hm);
        }}

        public static void saveEWCallLogsCache(JSONObject EWDJson) {
        EWCallLogsList = JsonUtils.parseEWCallLogJson(EWDJson);
        cl_case_id.clear();
        cl_eq_stock_id.clear();
        cl_sr_id.clear();
        cl_consumer_id.clear();
        cl_consumer_name.clear();
        cl_consumer_mobile.clear();
        cl_consumer_email.clear();
        cl_case_status.clear();
        cl_created_on.clear();
        cl_call_category.clear();
        cl_sr_status.clear();
        cl_sr_no.clear();
        cl_created_by.clear();

        for(EWTabCallLogs b : EWCallLogsList) {
            String cId = b.getcase_id();
            cl_case_id.add(cId);

            cl_eq_stock_id.put(cId, b.geteq_stock_id());
            cl_sr_id.put(cId, b.getsr_id());
            cl_consumer_id.put(cId, b.getconsumer_id());
            cl_consumer_name.put(cId, b.getconsumer_name());
            cl_consumer_mobile.put(cId, b.getconsumer_mobile());
            cl_consumer_email.put(cId, b.getconsumer_email());
            cl_case_status.put(cId, b.getcase_status());
            cl_created_on.put(cId, b.getcreated_on());
            cl_call_category.put(cId, b.getcall_category());
            cl_sr_status.put(cId, b.getsr_status());
            cl_sr_no.put(cId, b.getsr_no());
            cl_created_by.put(cId, b.getcreated_by());

            HashMap<String, String> hm =new HashMap<>();
            hm.put("case_id", b.getcase_id());
            hm.put("eq_stock_id", b.geteq_stock_id());
            hm.put("sr_id", b.getsr_id());
            hm.put("consumer_id", b.getconsumer_id());
            hm.put("consumer_name", b.getconsumer_name());
            hm.put("consumer_mobile", b.getconsumer_mobile());
            hm.put("consumer_email", b.getconsumer_email());
            hm.put("case_status", b.getcase_status());
            hm.put("created_on", b.getcreated_on());
            hm.put("call_category", b.getcall_category());
            hm.put("sr_status", b.getsr_status());
            hm.put("sr_no", b.getsr_no());
            hm.put("created_by", b.getcreated_by());
            cljo.add(hm);

        }
    }

    public static void saveUserCache(String userJson) {
        userList = JsonUtils.parseUserJson(userJson);
        userId.clear();
        companyId.clear();
        userSessionToken.clear();
        userFirstName.clear();
        userCity.clear();
        userCountryCode.clear();
        userEmail.clear();
        userType.clear();
        for(User b : userList) {
            int uId = b.getUserId();
            userId.add(uId);
            companyId.put(uId, b.getCompanyId());
            userSessionToken.put(uId, b.getSessionToken());
            userFirstName.put(uId, b.getFirstName());
            userCountryCode.put(uId, b.getCountryCode());
            userCity.put(uId, b.getCity());
            userEmail.put(uId, b.getEmail());
            userType.put(uId, b.getUserType());
        }
    }
    public static void saveCallCat(JSONObject callcatjson) {
        cl_catList = JsonUtils.parseCallCatJson(callcatjson);
        cat_id.clear();
        cat_desc.clear();

        for(CallCat b : cl_catList) {
            int cId = b.getCat_id();
            String Cdes = b.getdesc();
            cat_id.add(cId);
            cat_desc.add(Cdes);
        }
    }

    public static void saveEWClaimHistoryCache(JSONObject userJson) {
        EWTabCHistory = JsonUtils.parseEWTabCHistoryJson(userJson);
        ch_warranty_id.clear();
        ch_brand.clear();
        ch_user_id.clear();
        ch_model_name.clear();
        ch_eq_stock_id.clear();
        ch_warranty_provider_id.clear();
        ch_warranty_provider_name.clear();
        ch_purchased_date.clear();
        ch_max_claimable_amt.clear();
        ch_email_id.clear();
        ch_ew_expiry_date.clear();
        ch_ew_months.clear();
        ch_product_type.clear();
        ch_total_claimed_amt.clear();
        ch_claimable_balance.clear();
        ch_serial_no.clear();
        ch_ew_start_date.clear();
        ch_warranty_no.clear();
        ch_consumer_name.clear();
        ch_claim_history.clear();

        for(EWTabClaimHistory b : EWTabCHistory) {
            int chWid = b.getwarranty_id();
            ch_warranty_id.add(chWid);

            ch_brand.put(chWid, b.getbrand());
            ch_user_id.put(chWid, b.getuser_id());
            ch_model_name.put(chWid, b.getmodel_name());
            ch_eq_stock_id.put(chWid, b.geteq_stock_id());
            ch_warranty_provider_id.put(chWid, b.getwarranty_provider_id());
            ch_warranty_provider_name.put(chWid, b.getwarranty_provider_name());
            ch_purchased_date.put(chWid, b.getpurchased_date());
            ch_max_claimable_amt.put(chWid, b.getmax_claimable_amt());
            ch_email_id.put(chWid, b.getemail_id());
            ch_ew_expiry_date.put(chWid, b.getew_expiry_date());
            ch_ew_months.put(chWid, b.getew_months());
            ch_product_type.put(chWid, b.getproduct_type());
            ch_total_claimed_amt.put(chWid, b.gettotal_claimed_amt());
            ch_claimable_balance.put(chWid, b.getclaimable_balance());
            ch_serial_no.put(chWid, b.getserial_no());
            ch_ew_start_date.put(chWid, b.getew_start_date());
            ch_warranty_no.put(chWid, b.getwarranty_no());
            ch_consumer_name.put(chWid, b.getconsumer_name());
            ch_claim_history.put(chWid, b.getclaim_history());

        }
    }



    public static void saveEWProductDetailsTab(JSONObject userJson) {
        EWPDetailsTab = JsonUtils.parseEWPrJson(userJson);
        PrewwarrId.clear();
        mw_waarId.clear();
        prwarrNo.clear();
        prUserId.clear();
        prPurFrom.clear();
        prPurDate.clear();
        prRegDate.clear();
        prWsDate.clear();
        prExpDate.clear();
        prWarrMonths.clear();
        prAddiInfo.clear();
        prBill.clear();
        prWarrStatus.clear();
        prPrice.clear();
        prIcNo.clear();
        prEmail.clear();
        prFname.clear();
        prMname.clear();
        prLname.clear();
        prMobile.clear();
        prPhone.clear();
        prOffiPhone.clear();
        prAlterEmail.clear();
        prAddLine1.clear();
        prAddLine2.clear();
        prCity.clear();
        prPostal.clear();
        prUCountry.clear();
        prcountryName.clear();
        prBrandname.clear();
        prSerialno.clear();
        prProductType.clear();
        prProductId.clear();
        prModelName.clear();

        for(EWTabProductDetails b : EWPDetailsTab) {
            int prWid = b.getPrewwarrId();
            PrewwarrId.add(prWid);
            mw_waarId.put(prWid, b.getmw_warrantyId ());
            prwarrNo.put(prWid, b.getwarrantyNo ());
            prUserId.put(prWid, b.getuserId ());
            prPurFrom.put(prWid, b.getpurchaseFrom ());
            prPurDate.put(prWid, b.getpurchaseDate ());
            prRegDate.put(prWid, b.getprregDate ());
            prWsDate.put(prWid, b.getew_sDate ());
            prExpDate.put(prWid, b.getwarrantyExpDate ());
            prWarrMonths.put(prWid, b.getwarrantyMonths ());
            prAddiInfo.put(prWid, b.getadditionalInfo ());
            prBill.put(prWid, b.getbillNo ());
            prWarrStatus.put(prWid, b.getwarrantyStatus ());
            prPrice.put(prWid, b.getprice ());
            prIcNo.put(prWid, b.getic_no ());
            prEmail.put(prWid, b.getemail ());
            prFname.put(prWid, b.getfName ());
            prMname.put(prWid, b.getlName ());
            prLname.put(prWid, b.getmName ());
            prMobile.put(prWid, b.getmobile ());
            prPhone.put(prWid, b.getphone ());
            prOffiPhone.put(prWid, b.getofficePh ());
            prAlterEmail.put(prWid, b.getalterEmail ());
            prAddLine1.put(prWid, b.getaddressLine1 ());
            prAddLine2.put(prWid, b.getaddressLine2 ());
            prCity.put(prWid, b.getcity ());
            prPostal.put(prWid, b.getpostal_code ());
            prUCountry.put(prWid, b.getuser_country ());
            prcountryName.put(prWid, b.getcountryName ());
            prBrandname.put(prWid, b.getbrandName ());
            prSerialno.put(prWid, b.getserialNo ());
            prProductType.put(prWid, b.getproductType ());
            prProductId.put(prWid, b.getproductId ());
            prModelName.put(prWid, b.getmodelName ());
        }
    }


    public static void saveEWDetailsTab(JSONObject EWJson) {
        EWDetailsTab = JsonUtils.parseEWJson(EWJson);
        warrId.clear();
        eq_stockId.clear();
        warrNo.clear();
        countryCode.clear();
        ew_regDate.clear();
        warrType.clear();
        invoiceNo.clear();
        pbrandname.clear();
        pserialNo.clear();
        pproductType.clear();
        pmodelName.clear();
        consumerId.clear();
        mycompanyId .clear();
        providerCompId.clear();
        warrMonths.clear();
        warrSdate.clear();
        warrSyear.clear();
        warrSmonth.clear();
        warrSquarter.clear();
        warrSMonthTxt.clear();
        warrSyearTxt.clear();
        warrSquarterTxt.clear();
        ppurchaseDate.clear();
        warrSellingPrice.clear();
        warrExpDate.clear();
        providerCompName.clear();
        monthlyRecog.clear();
        purchasedMonth.clear();
        purchasedYear.clear();
        purchasedQuarter.clear();
        p_QuarterTxt.clear();
        p_MonthTxt.clear();
        p_YearTxt.clear();

        for(EWTabDetails b : EWDetailsTab) {
            int wId = b.getWarrantyId();
            warrId.add(wId);
            eq_stockId.put(wId, b.getEq_stockId());
            warrNo.put(wId, b.getWarrantyNo());
            countryCode.put(wId, b.getCountryCode());
            ew_regDate.put(wId, b.getEw_regDate());
            warrType.put(wId, b.getWarrantyType());
            invoiceNo.put(wId, b.getInvoiceNo());
            pbrandname.put(wId, b.getBrandName());
            pserialNo.put(wId, b.getSerialNo());
            pproductType.put(wId, b.getProductType());
            pmodelName.put(wId, b.getModelName());
            consumerId.put(wId, b.getConsumerId());
            mycompanyId .put(wId, b.getMycompanyId());
            providerCompId.put(wId, b.getProviderCompanyId());
            warrMonths.put(wId, b.getWarrantyMonths());
            warrSdate.put(wId, b.getEw_sDate());
            warrSyear.put(wId, b.getEw_sYear());
            warrSmonth.put(wId, b.getEw_sMonth());
            warrSquarter.put(wId, b.getEw_sQuarter());
            warrSMonthTxt.put(wId, b.getEw_sMonthTxt());
            warrSyearTxt.put(wId, b.getEw_sYearTxt());
            warrSquarterTxt.put(wId, b.getEw_sQuarterTxt());
            ppurchaseDate.put(wId, b.getPurchaseDate());
            warrSellingPrice.put(wId, b.getEw_sellingPrice());
            warrExpDate.put(wId, b.getWarrantyExpDate());
            providerCompName.put(wId, b.getProviderCompName());
            monthlyRecog.put(wId, b.getMonthlyRecognition());
            purchasedMonth.put(wId, b.getPurchasedMonth());
            purchasedYear.put(wId, b.getPurchasedYear());
            purchasedQuarter.put(wId, b.getPurchasedQuarter());
            p_QuarterTxt.put(wId, b.getP_QuarterTxt());
            p_MonthTxt.put(wId, b.getP_MonthTxt());
            p_YearTxt.put(wId, b.getP_YearTxt());

        }
    }


    public static void saveStockBalCache(String userJson) {
        stockList = JsonUtils.parseStockBalJson(userJson);
        stockBrand.clear();
        stockModel.clear();
        stockProdType.clear();
        stockCount.clear();
        for(StockBalance b : stockList) {
            stockBrand.add(b.getStockBrand());
            stockModel.add(b.getStockModel());
            stockCount.add(b.getStockCount());
            stockProdType.add(b.getStockProdType());
        }
    }

    public static void saveFailureCache(String userJson) {
        failureList = JsonUtils.parseFailuresJson(userJson);
        failureBrand.clear();
        failureModel.clear();
        failureProdType.clear();
        failureCount.clear();
        for(Failures b : failureList) {
            failureBrand.add(b.getFailureBrand());
            failureModel.add(b.getFailureModel());
            failureCount.add(b.getFailureCount());
            failureProdType.add(b.getFailureProdType());
        }
    }

    public static void saveCaseLogCache(String userJson) {
        caseLogList = JsonUtils.parseCaseLogJson(userJson);
        caseLogCount.clear();
        for(CaseLog b : caseLogList) {
            caseLogCount.add(b.getCaseLogCount());
        }
    }

    public static void savePendingQuotationCache(String userJson) {
        pendingQuoteList = JsonUtils.parseQuotationJson(userJson);
        pendingQuoteCount.clear();
        for(Quotation b : pendingQuoteList) {
            pendingQuoteCount.add(b.getQuotationCount());
        }
    }

    public static void saveServiceRequestCache(String userJson) {
        srList = JsonUtils.parseServiceRequestJson(userJson);
        srCount.clear();
        for(ServiceRequest b : srList) {
            srCount.add(b.getSrCount());
        }
    }

    public static void saveUsedProdCache(String userJson) {
        usedProductList = JsonUtils.parseUsedProductsJson(userJson);
        usedProdPartNo.clear();
        usedProdQty.clear();
        usedProdRank.clear();
        for(UsedProduct b : usedProductList) {
            usedProdQty.add(b.getQuantity());
            usedProdRank.add(b.getRank());
            usedProdPartNo.add(b.getPartNo());

        }
    }


}
