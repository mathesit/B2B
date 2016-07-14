package com.rever.rever_b2b.utils;


import android.util.Log;
import android.widget.EditText;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

 /* public static List<QuotationDetails> parseQuotDetails(String json){
        List<QuotationDetails> quotDetails = new ArrayList<>();
      try {
            JSONObject response = new JSONObject(json);
            JSONObject object = response.getJSONObject("EW");

          String quotId="",brand = "" , model = "",sNo = " ", product = "",email = "",consumer = "", create ="",createdby = "",
                    quotstatus ="",description = "", currency = "", amount = "", srp = "", markUp = "",
                                     received = "", created = "", sent = "", update = "", updatedBy = "", status = "";

            JSONArray array = object.optJSONArray("quotation_cost");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj1 = array.getJSONObject(i);

            description = obj1.getString("item_description");
            currency = obj1.getString("currency");
            amount = obj1.getString("amount");
            srp = obj1.getString("srp_rate");
            markUp = obj1.getString("marked_up_value");
            }

          JSONArray fObj = object.optJSONArray("quotation_history");
            for (int index = 0; index < fObj.length(); index++) {
                JSONObject cObj = fObj.getJSONObject(index);

            received = cObj.getString("received_from");
            created = cObj.getString("created_on");
            sent = cObj.getString("sent_to");
            update = cObj.getString("updated_on");
            updatedBy = cObj.getString("updated_by");
            status = cObj.getString("status");
            }
            quotId = object.getString("quotation_id");
            brand = object.getString("brand_name");
            model = object.getString("model_name");
            sNo = object.getString("serial_no");
            product = object.getString("product_type");
            email = object.getString("email_id");
            consumer = object.getString("consumer_name");
            create = object.getString("created_on");
            createdby = object.getString("created_by");
            quotstatus = object.getString("status");



            quotDetails.add(new QuotationDetails(quotId, brand, model, sNo, product, email, consumer, create, createdby, quotstatus,
                    description, currency, amount, srp, markUp, received, created, sent, update, updatedBy, status));

            System.out.println("quotId: " + quotId + "brand: " + brand + "model: " + model + "sNo: " + sNo + "product: " + product +
                    "email: " + email + "consumer: " + consumer + "create: " + create + "createdby: " + createdby + "Quotstatus: " +
                    quotstatus + "desc:" + description + "currency:" + currency + "amount:" + amount + "srp:" + srp + "mark:" + markUp +
                    "received:" + received + "created:" + created + "sent:" + sent + "update:" + update + "updateBy:" + updatedBy +
                    "status:" + status);
        }
            catch (Exception e){
                    e.printStackTrace();
                }

        return quotDetails;
    }*/


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
            MasterCache.quotCreatedBy.clear();MasterCache.quotationStatus.clear();MasterCache.quotService.clear();MasterCache.quotServAmt.clear();
            MasterCache.quotServSrp.clear();MasterCache.quotServMark.clear();MasterCache.quotCurrency.clear();MasterCache.quotReceivedFrom.clear();
            MasterCache.quotCreatedOn.clear();MasterCache.quotSentTo.clear();MasterCache.quotUpdatedOn.clear();MasterCache.quotUpdatedBy.clear();
            MasterCache.quotStatusHistory.clear();


            String quotId="",brand = "" , model = "",sNo = " ", product = "",email = "",consumer = "", create ="",createdby = "",
                    quotstatus ="",description = "", currency = "", amount = "", srp = "", markUp = "",
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
            if (obj1.has("currency"))
                currency = obj1.getString("currency");
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
            quotDetails.setQuotCreatedBy(createdby);quotDetails.setQuotStatus(quotstatus);quotDetails.setQuotService(description);quotDetails.setQuotCurrency(currency);
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
            MasterCache.quotCurrency.add(quotDetails.getQuotCurrency());
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
}

  /*  if (object.has("quotation_id")) {
                   String quotId = object.getString("quotation_id");

                }*/
              /*  if(response.has("quotation_id")) quot_id = response.getString("quotation_id");
                if(response.has("brand_name")) brand = response.getString("brand_name");
                if(response.has("model_name")) model = response.getString("model_name");
                if(response.has("serial_no"))  sNo = response.getString("serial_no");
                if(response.has("product_type")) product = response.getString("product_type");
                if(response.has("email_id")) email = response.getString("email_id");
                if(response.has("consumer_name")) consumer = response.getString("consumer_name");
                if(response.has("created_on")) create = response.getString("created_on");
                if(response.has("created_by")) createdBy = response.getString("created_by");
                if(response.has("status")) quotStatus = response.getString("status");*/

/*System.out.println("quotId: " + quotId + object.getString("quotation_id") + "brand: " + object.getString("brand_name") + "model: " + object.getString("model_name") +
        "sNo: " + object.getString("serial_no") + "product: " + object.getString("product_type") +
        "email: " + object.getString("email_id") + "consumer: " + object.getString("consumer_name") +
        "create: " + object.getString("created_on") + "createdby: " + object.getString("created_by") +
        "quotstatus: " + object.getString("status"));*/

   /*   }
                JSONArray array = response.getJSONArray("quotation_cost");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject cObj = array.getJSONObject(i);*/
