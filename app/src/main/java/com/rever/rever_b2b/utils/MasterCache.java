package com.rever.rever_b2b.utils;


import com.rever.rever_b2b.model.CaseLog;
import com.rever.rever_b2b.model.EWDetails;
import com.rever.rever_b2b.model.Failures;
import com.rever.rever_b2b.model.Quotation;
import com.rever.rever_b2b.model.ServiceRequest;
import com.rever.rever_b2b.model.StockBalance;
import com.rever.rever_b2b.model.UsedProduct;
import com.rever.rever_b2b.model.User;

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
    public static Map<Integer, Integer> Id = new HashMap<>();
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

/*
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
        this.modelName = modelName;*/

    public static void saveEWDetailsCache(String EWJson) {
        EWDetailsList = JsonUtils.parseEWDetailsJson(EWJson);
        Id.clear();
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
