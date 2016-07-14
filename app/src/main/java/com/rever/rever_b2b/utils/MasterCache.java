package com.rever.rever_b2b.utils;


import com.rever.rever_b2b.model.CaseLog;
import com.rever.rever_b2b.model.Failures;
import com.rever.rever_b2b.model.ProductDetails;
import com.rever.rever_b2b.model.Quotation;
import com.rever.rever_b2b.model.QuotationDetails;
import com.rever.rever_b2b.model.QuotationList;
import com.rever.rever_b2b.model.ServiceDetails;
import com.rever.rever_b2b.model.ServiceList;
import com.rever.rever_b2b.model.ServiceRequest;
import com.rever.rever_b2b.model.StockBalance;
import com.rever.rever_b2b.model.UsedProduct;
import com.rever.rever_b2b.model.User;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Matheswari on 3/25/2016.
 */
public class MasterCache {
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


    public static List<ServiceList> serviceList = new ArrayList<>();
    public static List<String> SrId = new ArrayList<>(), SrNo = new ArrayList<>(), SrStatus = new ArrayList<>(), Consumer = new ArrayList<>(), CreatedOn = new ArrayList<>();

    public static List<QuotationList> quotationList = new ArrayList<>();
    public static List<String> quotId = new ArrayList<>(), compId = new ArrayList<>(), srId = new ArrayList<>(), srNo = new ArrayList<>(),
                  createdOn = new ArrayList<>(),quotStatus = new ArrayList<>();

    public static List<QuotationDetails> quotDetailsList = new ArrayList<>();
    public static List<String> quot_id = new ArrayList<>(),quotBrand = new ArrayList<>(),quotModel = new ArrayList<>(),quotSerialNo = new ArrayList<>(),
            quotProductType = new ArrayList<>(),quotEmail = new ArrayList<>(),quotConsumer = new ArrayList<>(),quotCreate = new ArrayList<>(),
            quotCreatedBy = new ArrayList<>(),quotationStatus = new ArrayList<>(),quotService = new ArrayList<>(),quotServAmt = new ArrayList<>(),
            quotServSrp = new ArrayList<>(),quotServMark = new ArrayList<>(),quotCurrency = new ArrayList<>(),quotReceivedFrom = new ArrayList<>(),
            quotCreatedOn = new ArrayList<>(),quotSentTo = new ArrayList<>(),quotUpdatedOn = new ArrayList<>(),quotUpdatedBy = new ArrayList<>(),
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
            companyId.put(uId, b.getComopanyId());
            userSessionToken.put(uId, b.getSessionToken());
            userFirstName.put(uId, b.getFirstName());
            userCountryCode.put(uId, b.getCountryCode());
            userCity.put(uId, b.getCity());
            userEmail.put(uId, b.getEmail());
            userType.put(uId, b.getUserType());
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
        quotCurrency.clear();
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
        quotCurrency.add(quotationDetails.getQuotCurrency());
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

