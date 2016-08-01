package com.rever.rever_b2b.utils;


import android.util.Log;

import com.rever.rever_b2b.model.Brands;
import com.rever.rever_b2b.model.CaseLog;
import com.rever.rever_b2b.model.Countries;
import com.rever.rever_b2b.model.EwTabReports;
import com.rever.rever_b2b.model.Failures;
import com.rever.rever_b2b.model.ProductType;
import com.rever.rever_b2b.model.Quotation;
import com.rever.rever_b2b.model.QuotationDetails;
import com.rever.rever_b2b.model.QuotationList;
import com.rever.rever_b2b.model.ServiceDetails;
import com.rever.rever_b2b.model.ServiceList;
import com.rever.rever_b2b.model.CallCat;
import com.rever.rever_b2b.model.EWCallLogsCaseDetails;
import com.rever.rever_b2b.model.EWDetails;
import com.rever.rever_b2b.model.EWTabCallLogs;
import com.rever.rever_b2b.model.EWTabClaimHistory;
import com.rever.rever_b2b.model.EWTabDetails;
import com.rever.rever_b2b.model.EWTabProductDetails;
import com.rever.rever_b2b.model.ServiceRequest;
import com.rever.rever_b2b.model.StockBalance;
import com.rever.rever_b2b.model.UsedProduct;
import com.rever.rever_b2b.model.User;
import com.rever.rever_b2b.model.tempusersave;

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
    public static List<String> warrantyId = new ArrayList<>(),vbrandname =new ArrayList<>(),vprodtype =new ArrayList<>(),vserialno =new ArrayList<>(),vconsumername =new ArrayList<>();
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
    public static int Countryspinner = 0;
    public static int Brandspinner = 0;
    public static int Productspinner = 0;

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

    public static List<String> cld_logged_on1 = new ArrayList<>();
    public static List<String> cld_log_desc1 = new ArrayList<>();
    public static List<String> cld_logged_by1 = new ArrayList<>();

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
    public static Map<Integer, String> EW_CATEGORY = new HashMap<> ();

    public static Map<Integer, String> prState = new HashMap<>();
    public static Map<Integer, String> prUpcode = new HashMap<>();
    public static Map<Integer, String> prPassport = new HashMap<>();
    public static Map<Integer, String> prVoidrefund = new HashMap<>();


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
    public static Map<Integer, String> cat_desc_hash = new HashMap<>();

    public static List<Brands> brand_List = new ArrayList<>();
    public static List<Integer> brand_id = new ArrayList<>();
    public static List<String> brand_name = new ArrayList<>();
    public static Map<Integer, String> brand_name_hash = new HashMap<>();

    public static List<Countries> countries_list = new ArrayList<>();
    public static List<Integer> isd = new ArrayList<>();
    public static Map<Integer, String> country_code_hash = new HashMap<>();
    public static Map<Integer, String> country_name_hash = new HashMap<>();
    public static List<String> currency_code = new ArrayList<>();
    public static Map<Integer, String> currency_code_hash = new HashMap<>();
    public static List<String> country_code = new ArrayList<>();
    public static List<String> country_name = new ArrayList<>();


    public static List<ProductType> prodTypeList = new ArrayList<>();
    public static List<String> prodTypeNames = new ArrayList<>();
    public static Map<String, Integer> prodTypeIds = new HashMap<>();


    public static List<User> userList = new ArrayList<>();
    public static List<Integer> userId = new ArrayList<>();
    public static Map<Integer, Integer> companyId = new HashMap<>();
    public static Map<Integer, String> userSessionToken = new HashMap<>();
    public static Map<Integer, String> userFirstName = new HashMap<>();
    public static Map<Integer, String> userCountryCode = new HashMap<>();
    public static Map<Integer, String> userCity = new HashMap<>();
    public static Map<Integer, String> userEmail = new HashMap<>();
    public static Map<Integer, String> userType = new HashMap<>();


    //Add new EW popup Fetch Details with Email Id List
    public static List<tempusersave> userList1 = new ArrayList<>();
    public static List<Integer> userId1 = new ArrayList<>();
    public static List<String> companyId1 = new ArrayList<>();
    public static List<String> userSessionToken1 = new ArrayList<>();
    public static List<String> userFirstName1 = new ArrayList<>();
    public static List<String> userCountryCode1 = new ArrayList<>();
    public static List<String> userCity1 = new ArrayList<>();
    public static List<String> userEmail1 = new ArrayList<>();
    public static List<String> userType1 = new ArrayList<>();
    public static List<String> userLastName1 = new ArrayList<>();
    public static List<String> userPostal1 = new ArrayList<>();
    public static List<String> userAddLine2 = new ArrayList<>();
    public static List<String> userMobile1 = new ArrayList<>();
    public static List<String> userState1 = new ArrayList<>();
    public static List<String> userAddLine1 = new ArrayList<>();
    public static List<String> userMiddleName1 = new ArrayList<>();

    public static List<EwTabReports> EwReportsList = new ArrayList<>();
    public static List<Integer> reports_id = new ArrayList<>();
    public static List<String> Lreports_title = new ArrayList<>();
    public static Map<Integer, String> reports_title = new HashMap<>();
    public static Map<Integer, String> created_on = new HashMap<>();
    public static Map<Integer, String> show_details = new HashMap<>();
    public static Map<Integer, String> config_name = new HashMap<>();
    public static Map<Integer, String> config_description = new HashMap<>();
//            "report_id": 176703,
//            "report_title": "Warranty By Years",
//            "created_on": "2011-1-19.3.5. 57. 0",
//            "show_details": "-1",
//            "config_name": "EW_REPORTS",
//            "config_description": "Extended Warranty Reports"

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


    public static List<ServiceList> serviceList = new ArrayList<>();
    public static List<String> SrId = new ArrayList<>(), SrNo = new ArrayList<>(), SrStatus = new ArrayList<>(), Consumer = new ArrayList<>(), CreatedOn = new ArrayList<>(), serialListNo = new ArrayList<>(), brand = new ArrayList<>(), model = new ArrayList<>(),icNo =  new ArrayList<>();

    public static List<QuotationList> quotationList = new ArrayList<>();
    public static List<String> quotId = new ArrayList<>(), compId = new ArrayList<>(), srId = new ArrayList<>(), srNo = new ArrayList<>(),
            createdOn = new ArrayList<>(),quotStatus = new ArrayList<>();

    public static List<QuotationDetails> quotDetailsList = new ArrayList<>();
    public static List<String> quot_id = new ArrayList<>(),quotBrand = new ArrayList<>(),quotModel = new ArrayList<>(),quotSerialNo = new ArrayList<>(),
            quotProductType = new ArrayList<>(),quotEmail = new ArrayList<>(),quotConsumer = new ArrayList<>(),quotCreate = new ArrayList<>(),
            quotCreatedBy = new ArrayList<>(),quotationStatus = new ArrayList<>(), quotService = new ArrayList<>(),quotServAmt = new ArrayList<>(),
            quotServSrp = new ArrayList<>(),quotServMark = new ArrayList<>(),
            //quotCurrency = new ArrayList<>(),
            quotReceivedFrom = new ArrayList<>(), quotCreatedOn = new ArrayList<>(),quotSentTo = new ArrayList<>(),quotUpdatedOn = new ArrayList<>(),quotUpdatedBy = new ArrayList<>(),
            quotStatusHistory = new ArrayList<>();


    public static List<String> srReqSrId = new ArrayList<>(), srReqSrNo = new ArrayList<>(), srReqUserId = new ArrayList<>(), srReqStatus = new ArrayList<>(),
            srCreatedOn = new ArrayList<>(), srReqCreatedBy = new ArrayList<>(), srReqFailureDesc = new ArrayList<>(), srReqReturnCount = new ArrayList<>(),
            srReqSerialNo = new ArrayList<>(), srReqProdType = new ArrayList<>(), srReqBrandName = new ArrayList<>(), srCountryCode = new ArrayList<>(),
            srReqModelName = new ArrayList<>(), srReqWarrantyStatus = new ArrayList<>(), srReqFirstName = new ArrayList<>(), srReqLastName = new ArrayList<>(),
            srReqCity = new ArrayList<>(), srReqState = new ArrayList<>(), srReqAddress = new ArrayList<>(), srReqPostalCode = new ArrayList<>(),
            srReqCountryCode = new ArrayList<>(), srReqMobile = new ArrayList<>(), srCompanyName = new ArrayList<>(),
            srReqIcNo = new ArrayList<>(), srProductType = new ArrayList<>(),
            srReqOtherDesc = new ArrayList<>(), srReqRemarks = new ArrayList<>(),
            srReqProductStatus = new ArrayList<>();

    public static List<String> prdReqSrId = new ArrayList<>(), prdReqSrNo = new ArrayList<>(), prdReqUserId = new ArrayList<>(),
            prdReqFirstName = new ArrayList<>(), prdReqMiddleName = new ArrayList<>(), prdReqLastName = new ArrayList<>(),
            prdReqCity = new ArrayList<>(), prdReqCountryCode = new ArrayList<>(), prdReqMobile = new ArrayList<>(),
            prdReqOfficePhone = new ArrayList<>(), prdReqAltMail = new ArrayList<>(), prdReqPhoneNo = new ArrayList<>(),
            prdReqIcNo = new ArrayList<>(), prdReqAddLine1 = new ArrayList<>(), prdReqState = new ArrayList<>(),
            prdReqPostalCode = new ArrayList<>(), prdReqCompanyName = new ArrayList<>(), prdReqBrandName = new ArrayList<>(),
            prdReqModelName = new ArrayList<>(), prdReqSerialNo = new ArrayList<>(), prdReqProductType = new ArrayList<>(), prdReqBillNo = new ArrayList<>(),
            prdReqWarrantyNo = new ArrayList<>(), prdReqWarrantyMonths = new ArrayList<>(), prdReqWarrantyStartDate = new ArrayList<>(),
            prdReqWarrantyEndDate = new ArrayList<>(), prdReqPurchaseDate = new ArrayList<>(), prdReqPrice = new ArrayList<>(),
            prdReqRegDate = new ArrayList<>(), prdReqCountryName = new ArrayList<>(), prdReqAddInfo = new ArrayList<>(),
            prdReqPurchaseFrom = new ArrayList<>();

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

        vbrandname.clear();
        vconsumername.clear();
        vprodtype.clear();
        vserialno.clear();

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

            vbrandname.add(b.getbrandName());
            vconsumername.add(b.getconsumerName());
            vprodtype.add(b.getproductType());
            vserialno.add(b.getserialNo());


            HashMap<String, String> hm =new HashMap<>();
            hm.put("brand_name",b.getbrandName());
            hm.put("product_type",b.getproductType());
            hm.put("serial_no",b.getserialNo());
            hm.put("consumer",b.getconsumerName());
            hm.put("warranty_id",b.getwarrantyId());
            jo.add(hm);

        }
    }

    public static  List<Integer> srManuWarrId = new ArrayList<>();


    public static void saveSRManuWarranty(JSONObject jsonRes) {
        JsonUtils.parseSRManuWarr(jsonRes);
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

            cld_log_desc1.add(b.getlog_desc());
            cld_logged_by1.add(b.getlogged_by());
            cld_logged_on1.add(b.getlogged_on());

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
        for (User b : userList) {
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
    public static void SaveReportsListTask(JSONObject tempUjson) {

        EwReportsList = JsonUtils.parseReportsListJson(tempUjson);
        reports_id.clear();
        reports_title.clear();
        Lreports_title.clear();
        created_on.clear();
        show_details.clear();
        config_name.clear();
        config_description.clear();

        for (EwTabReports b : EwReportsList) {
            int rId = b.getreportsId();
            reports_id.add(rId);
            Lreports_title.add(b.getreport_title());
            reports_title.put(rId, b.getreport_title());
            created_on.put(rId, b.getcreated_on());
            show_details.put(rId, b.getshow_details());
            config_name.put(rId, b.getconfig_name());
            config_description.put(rId, b.getconfig_description());
        }
    }

    public static void AddNewEwEmailResponseCache(JSONObject tempUjson) {

        userList1 = JsonUtils.parseUser1Json(tempUjson);
        userId1.clear();
        companyId1.clear();
        userSessionToken1.clear();
        userFirstName1.clear();
        userCountryCode1.clear();
        userCity1.clear();
        userEmail1.clear();
        userType1.clear();
        userLastName1.clear();
        userPostal1.clear();
        userAddLine2.clear();
        userMobile1.clear();
        userAddLine1.clear();
        userMiddleName1.clear();
        userState1.clear();


        for(tempusersave b : userList1) {

            int cId = b.getuser_id();

            userId1.add(cId);

            companyId1.add(b.getcompanyId1());
            userSessionToken1.add(b.getuserSessionToken1());
            userFirstName1.add(b.getuserFirstName1());
            userCountryCode1.add(b.getuserCountryCode1());
            userCity1.add(b.getuserCity1());
            userEmail1.add(b.getuserEmail1());
            userType1.add(b.getuserType1());
            userLastName1.add(b.getuserLastName1());
            userPostal1.add(b.getuserPostal1());
            userAddLine2.add(b.getuserAddLine2());
            userMobile1.add(b.getuserMobile1());
            userAddLine1.add(b.getuserAddLine1());
            userState1.add(b.getuserState1());
            userMiddleName1.add(b.getuserMiddleName1());





        }
    }

    public static void saveCallCat(JSONObject callcatjson) {
        cl_catList = JsonUtils.parseCallCatJson(callcatjson);
        cat_id.clear();
        cat_desc.clear();
        cat_desc_hash.clear();

        for(CallCat b : cl_catList) {
            int cId = b.getCat_id();
            String Cdes = b.getdesc();
            cat_id.add(cId);
            cat_desc.add(Cdes);
            cat_desc_hash.put(cId, b.getdesc());
        }
    }

    public static void saveCountriesCache(JSONObject countryjson) {
        countries_list = JsonUtils.parseCountriesJson(countryjson);
        isd.clear();
        country_code.clear();
        country_name.clear();
        currency_code.clear();
        currency_code_hash.clear();
        country_name_hash.clear();
        country_code_hash.clear();

        for(Countries b : countries_list) {
            int Cid = b.getisd();
            String cname=b.getcountry_name();
            isd.add(Cid);

            currency_code_hash.put(Cid,b.getcurrency_code());
            country_code_hash.put(Cid, b.getcountry_code());
            country_name_hash.put(Cid, b.getcountry_name());
            country_code.add(b.getcountry_code());
            country_name.add(cname);
            currency_code.add(b.getcurrency_code());
        }
    }
    public static void saveBrands(JSONObject brandsjson) {
        brand_List = JsonUtils.parseBrandsJson(brandsjson);
        brand_id.clear();
        brand_name.clear();

        for(Brands b : brand_List) {
            int cId = b.getbrand_id();
            String Cdes = b.getbrand_name();
            brand_id.add(cId);
            brand_name.add(Cdes);
            brand_name_hash.put(cId, b.getbrand_name());
        }
    }


    public static void populateProductTypeCache(String prodTypeJson) {
        prodTypeList = JsonUtils.parseProductTypeJson(prodTypeJson);
        prodTypeNames.clear();
        prodTypeIds.clear();
        for(ProductType b : prodTypeList) {
            prodTypeNames.add(b.getName());
            prodTypeIds.put(b.getName(), b.getId());
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

        prState.clear();
        prPassport.clear();
        prUpcode.clear();
        prVoidrefund.clear();

        for(EWTabProductDetails b : EWPDetailsTab) {
            int prWid = b.getPrewwarrId();
            PrewwarrId.add(prWid);
            mw_waarId.put(prWid, b.getmw_warrantyId());
            prwarrNo.put(prWid, b.getwarrantyNo());
            prUserId.put(prWid, b.getuserId());
            prPurFrom.put(prWid, b.getpurchaseFrom());
            prPurDate.put(prWid, b.getpurchaseDate());
            prRegDate.put(prWid, b.getprregDate());
            prWsDate.put(prWid, b.getew_sDate());
            prExpDate.put(prWid, b.getwarrantyExpDate());
            prWarrMonths.put(prWid, b.getwarrantyMonths());
            prAddiInfo.put(prWid, b.getadditionalInfo());
            prBill.put(prWid, b.getbillNo());
            prWarrStatus.put(prWid, b.getwarrantyStatus());
            prPrice.put(prWid, b.getprice());
            prIcNo.put(prWid, b.getic_no());
            prEmail.put(prWid, b.getemail());
            prFname.put(prWid, b.getfName());
            prMname.put(prWid, b.getlName());
            prLname.put(prWid, b.getmName());
            prMobile.put(prWid, b.getmobile());
            prPhone.put(prWid, b.getphone());
            prOffiPhone.put(prWid, b.getofficePh());
            prAlterEmail.put(prWid, b.getalterEmail());
            prAddLine1.put(prWid, b.getaddressLine1());
            prAddLine2.put(prWid, b.getaddressLine2());
            prCity.put(prWid, b.getcity());
            prPostal.put(prWid, b.getpostal_code());
            prUCountry.put(prWid, b.getuser_country());
            prcountryName.put(prWid, b.getcountryName());
            prBrandname.put(prWid, b.getbrandName());
            prSerialno.put(prWid, b.getserialNo());
            prProductType.put(prWid, b.getproductType());
            prProductId.put(prWid, b.getproductId());
            prModelName.put(prWid, b.getmodelName());

            prState.put(prWid,b.getstate());
            prPassport.put(prWid, b.getpassport());
            prUpcode.put(prWid, b.getupc_code());
            prVoidrefund.put(prWid, b.getVoidrefund());
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
        EW_CATEGORY.clear();

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
            EW_CATEGORY.put(wId, b.getEW_CATEGORY());
        }
    }


    public static void saveStockBalCache(String userJson) {
        stockList = JsonUtils.parseStockBalJson(userJson);
        stockBrand.clear();
        stockModel.clear();
        stockProdType.clear();
        stockCount.clear();
        for (StockBalance b : stockList) {
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
        for (Failures b : failureList) {
            failureBrand.add(b.getFailureBrand());
            failureModel.add(b.getFailureModel());
            failureCount.add(b.getFailureCount());
            failureProdType.add(b.getFailureProdType());
        }
    }

    public static void saveCaseLogCache(String userJson) {
        caseLogList = JsonUtils.parseCaseLogJson(userJson);
        caseLogCount.clear();
        for (CaseLog b : caseLogList) {
            caseLogCount.add(b.getCaseLogCount());
        }
    }

    public static void savePendingQuotationCache(String userJson) {
        pendingQuoteList = JsonUtils.parseQuotationJson(userJson);
        pendingQuoteCount.clear();
        for (Quotation b : pendingQuoteList) {
            pendingQuoteCount.add(b.getQuotationCount());
        }
    }

    public static void saveServiceRequestCache(String userJson) {
        srList = JsonUtils.parseServiceRequestJson(userJson);
        srCount.clear();
        for (ServiceRequest b : srList) {
            srCount.add(b.getSrCount());
        }
    }

    public static void saveUsedProdCache(String userJson) {
        usedProductList = JsonUtils.parseUsedProductsJson(userJson);
        usedProdPartNo.clear();
        usedProdQty.clear();
        usedProdRank.clear();
        for (UsedProduct b : usedProductList) {
            usedProdQty.add(b.getQuantity());
            usedProdRank.add(b.getRank());
            usedProdPartNo.add(b.getPartNo());

        }
    }

    public static void saveServiceListCache(JSONObject serviceJson) {
        serviceList = JsonUtils.parseServiceJson(serviceJson);
        SrId.clear();
        SrNo.clear();
        CreatedOn.clear();
        SrStatus.clear();
        Consumer.clear();
        brand.clear();
        model.clear();
        icNo.clear();
        serialListNo.clear();

        for (ServiceList SL : serviceList) {
            SrId.add(SL.getSrId());
            SrNo.add(SL.getSrNo());
            CreatedOn.add(SL.getCreated_on());
            SrStatus.add(SL.getStatus());
            Consumer.add(SL.getConsumer_name());
        }

    }

    public static void  saveQuotationList(JSONObject quotationJson){
        quotationList = JsonUtils.parseQuotationList(quotationJson);
        quotId.clear();
        compId.clear();
        srId.clear();
        srNo.clear();
        createdOn.clear();
        quotStatus.clear();
        for(QuotationList ql : quotationList){
            quotId.add(ql.getQuot_id());
            compId.add(ql.getCompany_id());
            srId.add(ql.getSr_id());
            srNo.add(ql.getSr_no());
            createdOn.add(ql.getCreated_on());
            quotStatus.add(ql.getStatus());
        }
    }

   /* public static void saveQuotationDetails(String quotResponse){
        quotDetailsList = JsonUtils.parseQuotDetails(quotResponse);
        quot_id.clear();
        quotBrand.clear();
        quotModel.clear();
        quotSerialNo.clear();
        quotProductType.clear();
        quotEmail.clear();
        quotConsumer.clear();
        quotCreate.clear();
        quotCreatedBy.clear();
        quotationStatus.clear();
        quotService.clear();
        quotServAmt.clear();
        quotServSrp.clear();
        quotServMark.clear();
        quotCurrency.clear();
        quotReceivedFrom.clear();
        quotCreatedOn.clear();
        quotSentTo.clear();
        quotUpdatedOn.clear();
        quotUpdatedBy.clear();
        quotStatusHistory.clear();
        for(QuotationDetails qd: quotDetailsList){
            quot_id.add(qd.getQuot_id());
            quotBrand.add(qd.getQuotBrand());
            quotModel.add(qd.getQuotModel());
            quotSerialNo.add(qd.getQuotSerialNo());
            quotProductType.add(qd.getQuotProductType());
            quotEmail.add(qd.getQuotEmail());
            quotConsumer.add(qd.getQuotConsumer());
            quotCreate.add(qd.getCreatedOn());
            quotCreatedBy.add(qd.getQuotCreatedBy());
            quotationStatus.add(qd.getQuotStatus());
            quotService.add(qd.getQuotService());
            quotServAmt.add(qd.getQuotServAmt());
            quotServSrp.add(qd.getQuotServSrp());
            quotServMark.add(qd.getQuotServMark());
            quotCurrency.add(qd.getQuotCurrency());
            quotReceivedFrom.add(qd.getQuotReceivedFrom());
            quotCreatedOn.add(qd.getQuotCreatedOn());
            quotSentTo.add(qd.getQuotSentTo());
            quotUpdatedOn.add(qd.getQuotUpdatedOn());
            quotUpdatedOn.add(qd.getQuotUpdatedBy());
            quotStatusHistory.add(qd.getQuotStatusHistory());
        }

    }*/

    public static void saveQuotationDetails(String quotResponse) {
        QuotationDetails quotationDetails = JsonUtils.parseQuotDetails(quotResponse);
        quot_id.clear();
        quotBrand.clear();
        quotModel.clear();
        quotSerialNo.clear();
        quotProductType.clear();
        quotEmail.clear();
        quotConsumer.clear();
        quotCreate.clear();
        quotCreatedBy.clear();
        quotationStatus.clear();
        quotService.clear();
        quotServAmt.clear();
        quotServSrp.clear();
        quotServMark.clear();
        //quotCurrency.clear();
        quotReceivedFrom.clear();
        quotCreatedOn.clear();
        quotSentTo.clear();
        quotUpdatedOn.clear();
        quotUpdatedBy.clear();
        quotStatusHistory.clear();

        quot_id.add(quotationDetails.getQuot_id());
        quotBrand.add(quotationDetails.getQuotBrand());
        quotModel.add(quotationDetails.getQuotModel());
        quotSerialNo.add(quotationDetails.getQuotSerialNo());
        quotProductType.add(quotationDetails.getQuotProductType());
        quotEmail.add(quotationDetails.getQuotEmail());
        quotConsumer.add(quotationDetails.getQuotConsumer());
        quotCreate.add(quotationDetails.getCreatedOn());
        quotCreatedBy.add(quotationDetails.getQuotCreatedBy());
        quotationStatus.add(quotationDetails.getQuotStatus());
        quotService.add(quotationDetails.getQuotService());
        quotServAmt.add(quotationDetails.getQuotServAmt());
        quotServSrp.add(quotationDetails.getQuotServSrp());
        quotServMark.add(quotationDetails.getQuotServMark());
       // quotCurrency.add(quotationDetails.getQuotCurrency());
        quotReceivedFrom.add(quotationDetails.getQuotReceivedFrom());
        quotCreatedOn.add(quotationDetails.getQuotCreatedOn());
        quotSentTo.add(quotationDetails.getQuotSentTo());
        quotUpdatedOn.add(quotationDetails.getQuotUpdatedOn());
        quotUpdatedBy.add(quotationDetails.getQuotUpdatedBy());
        quotStatusHistory.add(quotationDetails.getQuotStatusHistory());


    }

    public static void saveServiceDetailCache(JSONObject reqJson) {
        ServiceDetails srDetails = JsonUtils.parseRequestJson(reqJson);

        srReqSrId.clear();
        srReqSrNo.clear();
        srReqStatus.clear();
        srCreatedOn.clear();
        srReqFailureDesc.clear();
        srReqCreatedBy.clear();
        srReqReturnCount.clear();
        srReqWarrantyStatus.clear();
        srReqUserId.clear();
        srReqFirstName.clear();
        srReqLastName.clear();
        srReqCity.clear();
        srReqCountryCode.clear();
        srReqMobile.clear();
        srReqIcNo.clear();
        srReqAddress.clear();
        srReqState.clear();
        srReqPostalCode.clear();
        srReqOtherDesc.clear();
        srReqRemarks.clear();
        srReqProductStatus.clear();
        srReqBrandName.clear();
        srReqModelName.clear();
        srCompanyName.clear();
        srReqSerialNo.clear();
        srReqProdType.clear();
       // srEqStockId.clear();

        //for(ServiceRequestList b : serviceReqList) {
        srReqSrId.add(srDetails.getSrId());
        srReqSrNo.add(srDetails.getSrNo());
        srReqStatus.add(srDetails.getSrStatus());
        srCreatedOn.add(srDetails.getSrCreatedOn());
        srReqCreatedBy.add(srDetails.getSrCreatedBy());
        srReqFailureDesc.add(srDetails.getSrFailureDesc());
        srReqReturnCount.add(srDetails.getSrReqReturnCount());
        srReqSerialNo.add(srDetails.getSrReqSerialNo());
        srReqProdType.add(srDetails.getSrReqProdType());
        srReqBrandName.add(srDetails.getSrReqBrandName());
        //  srCountryCode.add(srDetails.getSrReqCountryCode());
        srReqModelName.add(srDetails.getSrReqModelName());
        srReqWarrantyStatus.add(srDetails.getSrReqWarrantyStatus());
        srReqFirstName.add(srDetails.getSrReqFirstName());
        srReqLastName.add(srDetails.getSrReqLastName());
        srReqCity.add(srDetails.getSrReqCity());
        srReqState.add(srDetails.getSrReqState());
        srReqAddress.add(srDetails.getSrReqAddress());
        srReqPostalCode.add(srDetails.getSrReqPostalCode());
        srReqUserId.add(srDetails.getSrReqUserId());
        srReqCountryCode.add(srDetails.getSrReqCountryCode());
        srReqMobile.add(srDetails.getSrReqMobile());
        srCompanyName.add(srDetails.getSrCompanyName());
        srReqIcNo.add(srDetails.getSrReqIcNo());
        srProductType.add(srDetails.getSrReqProdType());
        srReqOtherDesc.add(srDetails.getSrReqOtherDesc());
        srReqRemarks.add(srDetails.getSrReqRemarks());
        srReqProductStatus.add(srDetails.getSrReqProductStatus());

    }

   /* public static void saveProductDetailCache(JSONObject prodJson) {
        ProductDetails prDetails = JsonUtils.parseProductJson(prodJson);

        prdReqSrId.clear();
        prdReqSrNo.clear();
        prdReqUserId.clear();
        prdReqFirstName.clear();
        prdReqMiddleName.clear();
        prdReqLastName.clear();
        prdReqCity.clear();
        prdReqCountryCode.clear();
        prdReqMobile.clear();
        prdReqOfficePhone.clear();
        prdReqAltMail.clear();
        prdReqPhoneNo.clear();
        prdReqIcNo.clear();
        prdReqAddLine1.clear();
        prdReqState.clear();
        prdReqPostalCode.clear();
        prdReqCompanyName.clear();
        prdReqBrandName.clear();
        prdReqModelName.clear();
        prdReqSerialNo.clear();
        prdReqProductType.clear();
        prdReqBillNo.clear();
        prdReqWarrantyNo.clear();
        prdReqWarrantyMonths.clear();
        prdReqWarrantyStartDate.clear();
        prdReqWarrantyEndDate.clear();
        prdReqPurchaseDate.clear();
        prdReqPrice.clear();
        prdReqRegDate.clear();
        prdReqCountryName.clear();
        prdReqAddInfo.clear();
        prdReqPurchaseFrom.clear();

        prdReqSrId.add(prDetails.getPrdSrId());
        prdReqSrNo.add(prDetails.getPrdSrNo());
        prdReqUserId.add(prDetails.getPrdUserId());
        prdReqFirstName.add(prDetails.getPrdFirstName());
        prdReqMiddleName.add(prDetails.getPrdMiddleName());
        prdReqLastName.add(prDetails.getPrdLastName());
        prdReqCity.add(prDetails.getPrdCity());
        prdReqCountryCode.add(prDetails.getPrdCountryCode());
        prdReqMobile.add(prDetails.getPrdMobile());
        prdReqOfficePhone.add(prDetails.getPrdOfficePhone());
        prdReqAltMail.add(prDetails.getPrdAltMail());
        prdReqPhoneNo.add(prDetails.getPrdPhoneNo());
        prdReqIcNo.add(prDetails.getPrdIcNo());
        prdReqAddLine1.add(prDetails.getPrdAddLine1());
        prdReqState.add(prDetails.getPrdState());
        prdReqPostalCode.add(prDetails.getPrdPostalCode());
        prdReqCompanyName.add(prDetails.getPrdCompanyName());
        prdReqBrandName.add(prDetails.getPrdBrandName());
        prdReqModelName.add(prDetails.getPrdModelName());
        prdReqSerialNo.add(prDetails.getPrdSerialNo());
        prdReqProductType.add(prDetails.getPrdProductType());
        prdReqBillNo.add(prDetails.getPrdBillNo());
        prdReqWarrantyNo.add(prDetails.getPrdWarrantyNo());
        prdReqWarrantyMonths.add(prDetails.getPrdWarrantyMonths());
        prdReqWarrantyStartDate.add(prDetails.getPrdWarrantyStartDate());
        prdReqWarrantyEndDate.add(prDetails.getPrdWarrantyEndDate());
        prdReqPurchaseDate.add(prDetails.getPrdPurchaseDate());
        prdReqPrice.add(prDetails.getPrdPrice());
        prdReqRegDate.add(prDetails.getPrdRegDate());
        prdReqCountryName.add(prDetails.getPrdCountryName());
        prdReqAddInfo.add(prDetails.getPrdAddInfo());
        prdReqPurchaseFrom.add(prDetails.getPrdPurchaseFrom());

    }*/
}

