package com.rever.rever_b2b.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rever.rever_b2b.R;
import com.rever.rever_b2b.utils.JsonUtils;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oviya on 6/8/2016.
 */

    public class ServiceRequestExtended extends Fragment implements View.OnClickListener {
    private View rootView;
    private LinearLayout linearSR, linearService, linearServiceRequest, linearProduct, linearProductRequest;
    private TextView service, product;
    private ListView listView;
    private TextView txtServiceNo,txtCreatedBy,txtCreatedDate,txtFailureDesc,txtSerialNo,txtBrand,txtProdType,
                txtModel,txtWarrantyStatus,txtPastServiceReturns,txtEmail ,txtCountry,txtFirstName,txtLastName ,txtCity ,
                txtState ,txtAddress,txtPostalCode,txtIcPassportNo,txtMobile,txtServiceCentre,txtFailDesc ,txtRemarks ,txtStatus ;

    private TextView emailText,altEmailText,firstNameText,middleNameText,lastNameText,icText,address1Text,address2Text,postalCodeText,
            cityText,stateText,countryText,mobileText,homePhoneText,officePhoneText,serialNoText,billNoText,brandText,warrantyNoText,
            prodTypeText,warrantyMonthsText,modelText,warranytyExpText,purchaseOnText,regDateText,purchaseFromText,prodCostText,countryProdText,warrantyStartText;

    String sr_id = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_service, container, false);
        initViews();
        showAllService();
        showServiceReqDetails();
        return rootView;
    }

    public void initViews() {
        linearSR = (LinearLayout) rootView.findViewById(R.id.LinearServiceDetails);
        // linearService = (LinearLayout) v.findViewById(R.id.LinearServiceRequest);
        service = (TextView) rootView.findViewById(R.id.buttonService);
        product = (TextView) rootView.findViewById(R.id.buttonProduct);
        listView = (ListView) rootView.findViewById(R.id.listInServReq);
        service.setOnClickListener(this);
        product.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("myLog", "position:" + position);
                sr_id = MasterCache.SrId.get(position);
                Log.i("myLog", "sr_id" + sr_id);
                    showServiceReqDetails();
                    //loadProductDetails(sr_id);
                    loadServiceRequest(sr_id);
                if (product.isPressed()) {
                    showProductDetails();
                    loadProductDetails(sr_id);
                }
            }
        });

    }
        /*service.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("myLog", "service clivk:");
            }
        });
        //service.setOnClickListener(this);
        product.setOnClickListener(this);

    }*/

    public void showAllService(){
        String url = NetUtils.HOST+ NetUtils.EW_SERVICE_DETAILS_LIST;
        Log.i("myLog", "Post url:" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page_no", "1");
            jsonObject.put("page_count", "5");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // do something...
                    Log.i("myLog", "Success Response" +response);
                    MasterCache.saveServiceListCache(response);
                    CustomList listAdapter = new CustomList(getActivity(),MasterCache.SrNo, MasterCache.SrStatus, MasterCache.CreatedOn, MasterCache.Consumer);
                    listView.setAdapter(listAdapter);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // do something...
                    Log.i("myLog","Error Response");
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Accept", "application/json");
                    headers.put("Authorization", ReverApplication.getSessionToken());
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.buttonService:
                showServiceReqDetails();
                if (sr_id != null)
                    loadServiceRequest(sr_id);
                   else
                    Toast.makeText(getActivity(), "Select Service Request to get details.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonProduct:
                showProductDetails();
                if (sr_id != null)
                    loadProductDetails(sr_id);
                else
                    Toast.makeText(getActivity(), "Select Service Request to get details.", Toast.LENGTH_SHORT).show();
                break;
        }
    }          /* Log.i("SUCCESS", "BUTTON CLICKED");
                Log.i("ENTER", "OPENED");
                break;

                Log.i("SUCCESS", "BUTTON2 CLICKED");
                showProductDetails();
                Log.i("ENTER", "OPENED2");


                break;
        }
    }*/

    public void showServiceReqDetails(){
        linearSR.removeAllViews();
        service.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_txt));
        product.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorwhite));
        service.setTextColor(ContextCompat.getColor(getContext(), R.color.colorwhite));
        product.setTextColor(ContextCompat.getColor(getContext(),R.color.blue_txt));
        LayoutInflater li = LayoutInflater.from(getActivity());
        View layout = li.inflate(R.layout.service_request_details, null, false);
        linearSR.addView(layout);
        initServiceRequest(layout);
    }

    public void showProductDetails(){
        linearSR.removeAllViews();
        service.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorwhite));
        product.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_txt));
        service.setTextColor(ContextCompat.getColor(getContext(), R.color.blue_txt));
        product.setTextColor(ContextCompat.getColor(getContext(), R.color.colorwhite));
        LayoutInflater li = LayoutInflater.from(getActivity());
        View layout = li.inflate(R.layout.service_product_details, null, false);
        linearSR.addView(layout);
        initProductDetails(layout);
    }


    public void initServiceRequest(View v) {
        txtServiceNo = (TextView) v.findViewById(R.id.txtServiceNo);
        txtCreatedBy = (TextView) v.findViewById(R.id.txtCreatedBy);
        txtCreatedDate = (TextView) v.findViewById(R.id.txtCreatedDate);
        txtFailureDesc = (TextView) v.findViewById(R.id.txtFailureDesc);
        txtSerialNo = (TextView) v.findViewById(R.id.txtSerialNo);
        txtBrand = (TextView) v.findViewById(R.id.txtBrand);
        txtProdType = (TextView) v.findViewById(R.id.txtProductType);
        txtModel = (TextView) v.findViewById(R.id.txtModel);
        txtWarrantyStatus = (TextView) v.findViewById(R.id.txtWarrantyStatus);
        txtPastServiceReturns = (TextView) v.findViewById(R.id.txtPastServiceReturns);
        txtEmail = (TextView) v.findViewById(R.id.txtEmail);
        txtCountry = (TextView) v.findViewById(R.id.txtCountry);
        txtFirstName = (TextView) v.findViewById(R.id.txtFirstName);
        txtLastName = (TextView) v.findViewById(R.id.txtLastName);
        txtCity = (TextView) v.findViewById(R.id.txtCity);
        txtState = (TextView) v.findViewById(R.id.txtState);
        txtAddress = (TextView)v.findViewById(R.id.txtAddress);
        txtPostalCode = (TextView) v.findViewById(R.id.txtPostalCode);
        txtIcPassportNo = (TextView) v.findViewById(R.id.txtIcPassport);
        txtMobile = (TextView)v.findViewById(R.id.txtMobile);
        txtServiceCentre = (TextView) v.findViewById(R.id.txtServiceCenters);
        txtFailDesc = (TextView) v.findViewById(R.id.txtFailDesc);
        txtRemarks = (TextView) v.findViewById(R.id.txtRemarks);
        txtStatus = (TextView) v.findViewById(R.id.txtStatus);


       /* int size = MasterCache.srReqSrId.size();
        for(int start=0; start<size; start++) {

            txtServiceNo.setText(MasterCache.prdReqSrNo.get(start));
            Log.i("SERIAL NO", String.valueOf(txtServiceNo));
        }*/

    }

    public void initProductDetails(View v){
        emailText = (TextView)v.findViewById(R.id.emailText);
        altEmailText = (TextView)v.findViewById(R.id.altEmailText);
        firstNameText = (TextView)v.findViewById(R.id.firstNameText);
        middleNameText = (TextView)v.findViewById(R.id.middleNameText);
        lastNameText = (TextView)v.findViewById(R.id.lastNameText);
        icText = (TextView)v.findViewById(R.id.icPassportText);
        address1Text = (TextView)v.findViewById(R.id.address1Text);
        address2Text = (TextView)v.findViewById(R.id.address2Text);
        postalCodeText = (TextView)v.findViewById(R.id.postalCodeText);
        cityText = (TextView)v.findViewById(R.id.cityText);
        stateText = (TextView)v.findViewById(R.id.stateText);
        countryText = (TextView)v.findViewById(R.id.countryProdText);
        mobileText = (TextView)v.findViewById(R.id.mobileText);
        homePhoneText = (TextView)v.findViewById(R.id.homePhoneText);
        officePhoneText = (TextView)v.findViewById(R.id.officePhoneText);
        serialNoText = (TextView)v.findViewById(R.id.serialNoText);
        billNoText = (TextView)v.findViewById(R.id.billNoText);
        brandText = (TextView)v.findViewById(R.id.brandText);
        warrantyNoText = (TextView)v.findViewById(R.id.warrantyNoText);
        prodTypeText = (TextView)v.findViewById(R.id.prodTypeText);
        warrantyMonthsText = (TextView)v.findViewById(R.id.warrantyMonthsText);
        modelText = (TextView)v.findViewById(R.id.modelText);
        warranytyExpText = (TextView)v.findViewById(R.id.warrExpText);
        purchaseOnText = (TextView)v.findViewById(R.id.purchaseText);
        regDateText = (TextView)v.findViewById(R.id.warrRegText);
        purchaseFromText = (TextView)v.findViewById(R.id.purchaseFromText);
        prodCostText = (TextView)v.findViewById(R.id.productCostText);
        countryProdText = (TextView)v.findViewById(R.id.countryText);
        warrantyStartText = (TextView)v.findViewById(R.id.warrStartText);

       /* int start = 0;
        serialNoText.setText(MasterCache.prdReqSrNo.get(start));
        Log.d("SERIALNO", serialNoText.toString());*/
    }


    public class CustomList extends ArrayAdapter<String> {
        private List<String> srNo, status, createdOn, customer;
        private Activity context;

        public CustomList(Activity context, List<String> srNo, List<String> status, List<String> createdOn, List<String> customer) {
            super(context, R.layout.service_list, srNo);
            this.context = context;
            this.srNo = srNo;
            this.status = status;
            this.createdOn = createdOn;
            this.customer = customer;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.service_list, null, true);
            TextView txtStatus = (TextView) listViewItem.findViewById(R.id.status);
            TextView txtConsumer = (TextView) listViewItem.findViewById(R.id.customerName);
            TextView txtCreatedOn = (TextView)listViewItem.findViewById(R.id.service_date);
            TextView txtSrNo = (TextView)listViewItem.findViewById(R.id.customerId);
           // txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.alert_small, 0, 0, 0);
            //txtCreatedOn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.calendar_small, 0, 0, 0);
            //txtConsumer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_f, 0, 0, 0);
            txtStatus.setText(status.get(position));
            txtConsumer.setText(customer.get(position));
            txtCreatedOn.setText(createdOn.get(position));
            txtSrNo.setText(srNo.get(position));
            return  listViewItem;
        }
    }

        public void loadServiceRequest(String sr_id) {
            String serviceReqUrl = String.format(NetUtils.EW_SERVICE_DETAILS, sr_id);
            String url = NetUtils.HOST + serviceReqUrl;
            Log.i("myLog", " loadServiceReqDetails url : " + url);


            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // the response is already constructed as a JSONObject!
                            Log.i("myLog", "loadServiceReqDetails Success Response");
                            MasterCache.saveServiceDetailCache(response);
                            loadServReqDet();
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    headers.put("Accept", "application/json");
                    headers.put("Authorization", ReverApplication.getSessionToken());
                    return headers;
                }
            };
            requestQueue.add(jsonRequest);

        }


    public void loadProductDetails(String sr_id){
        String serviceProdUrl = String.format(NetUtils.EW_PRODUCT_DETAILS, sr_id);
        String url = NetUtils.HOST + serviceProdUrl;
        Log.i("myLog", " loadServiceProdDetails url : " + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        Log.i("myLog", "loadServiceProdDetails Success Response");
                        JsonUtils.parseProductJson(response);
                        //MasterCache.saveProductDetailCache(response);
                        loadServProdDet();
                    }
                }, new Response.ErrorListener() {
                @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);

    }

    private void loadServReqDet(){
        txtServiceNo.setText(MasterCache.srReqSrNo.get(0));
        txtCreatedBy.setText(MasterCache.srReqCreatedBy.get(0));
        txtCreatedDate.setText(MasterCache.srCreatedOn.get(0));
        txtFailureDesc.setText(MasterCache.srReqFailureDesc.get(0));
        txtSerialNo.setText(MasterCache.srReqSerialNo.get(0));
        txtBrand.setText(MasterCache.srReqBrandName.get(0));
        txtProdType.setText(MasterCache.srReqProdType.get(0));
        txtModel.setText(MasterCache.srReqModelName.get(0));
        txtWarrantyStatus.setText(MasterCache.srReqWarrantyStatus.get(0));
        txtPastServiceReturns.setText(MasterCache.srReqReturnCount.get(0));
        txtEmail.setText("");
        txtCountry.setText(MasterCache.srReqCountryCode.get(0));
        txtFirstName.setText(MasterCache.srReqFirstName.get(0));
        txtLastName.setText(MasterCache.srReqLastName.get(0));
        txtCity.setText(MasterCache.srReqCity.get(0));
        txtState.setText(MasterCache.srReqState.get(0));
        txtAddress.setText(MasterCache.srReqAddress.get(0));
        txtPostalCode.setText(MasterCache.srReqPostalCode.get(0));
        txtIcPassportNo.setText(MasterCache.srReqIcNo.get(0));
        txtMobile.setText(MasterCache.srReqMobile.get(0));
        txtServiceCentre.setText(MasterCache.srCompanyName.get(0));
        txtFailDesc.setText(MasterCache.srReqFailureDesc.get(0));
        txtRemarks.setText(MasterCache.srReqRemarks.get(0));
        txtStatus.setText(MasterCache.srReqProductStatus.get(0));
    }

    private void loadServProdDet(){
        emailText.setText("");
        altEmailText.setText("");
        firstNameText.setText(MasterCache.prdReqFirstName.get(0));
        middleNameText.setText(MasterCache.prdReqMiddleName.get(0));
        lastNameText.setText(MasterCache.prdReqLastName.get(0));
        icText.setText(MasterCache.prdReqIcNo.get(0));
        address1Text.setText(MasterCache.prdReqAddLine1.get(0));
        address2Text.setText("");
        postalCodeText.setText(MasterCache.prdReqPostalCode.get(0));
        cityText.setText(MasterCache.prdReqCity.get(0));
        stateText.setText(MasterCache.prdReqState.get(0));
        countryText.setText(MasterCache.prdReqCountryCode.get(0));
        mobileText.setText(MasterCache.prdReqMobile.get(0));
        homePhoneText.setText(MasterCache.prdReqPhoneNo.get(0));
        officePhoneText.setText(MasterCache.prdReqOfficePhone.get(0));
        serialNoText.setText(MasterCache.prdReqSerialNo.get(0));
        billNoText.setText(MasterCache.prdReqBillNo.get(0));
        brandText.setText(MasterCache.prdReqBrandName.get(0));
        warrantyNoText.setText(MasterCache.prdReqWarrantyNo.get(0));
        prodTypeText.setText(MasterCache.prdReqProductType.get(0));
        warrantyMonthsText.setText(MasterCache.prdReqWarrantyMonths.get(0));
        modelText.setText(MasterCache.prdReqModelName.get(0));
        warranytyExpText.setText(MasterCache.prdReqWarrantyEndDate.get(0));
        purchaseOnText.setText(MasterCache.prdReqPurchaseDate.get(0));
        regDateText.setText(MasterCache.prdReqRegDate.get(0));
        purchaseFromText.setText(MasterCache.prdReqPurchaseFrom.get(0));
        prodCostText.setText(MasterCache.prdReqPrice.get(0));
        countryProdText.setText(MasterCache.prdReqCountryName.get(0));
        warrantyStartText.setText(MasterCache.prdReqWarrantyStartDate.get(0));;
    }



}

