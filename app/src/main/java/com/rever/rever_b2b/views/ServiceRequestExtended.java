package com.rever.rever_b2b.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rever.rever_b2b.R;
import com.rever.rever_b2b.model.ServiceDetails;
import com.rever.rever_b2b.utils.JsonUtils;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oviya on 6/8/2016.
 */

    public class ServiceRequestExtended extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, TextView.OnEditorActionListener, TextWatcher {
    private View rootView;
    private LinearLayout linearSR, linearUpdate, linearStatus, linearRadio, linearEdit;
    private TextView service, product;
    private ListView listView, listSearch;
    private TextView txtServiceNo, txtCreatedBy, txtCreatedDate, txtFailureDesc, txtSerialNo, txtBrand, txtProdType,
            txtModel, txtWarrantyStatus, txtPastServiceReturns, txtEmail, txtCountry, txtFirstName, txtLastName, txtCity,
            txtState, txtAddress, txtPostalCode, txtIcPassportNo, txtMobile, txtServiceCentre, txtFailDesc, txtRemarks, txtStatus;

    private TextView emailText, altEmailText, firstNameText, middleNameText, lastNameText, icText, address1Text, address2Text, postalCodeText,
            cityText, stateText, countryText, mobileText, homePhoneText, officePhoneText, serialNoText, billNoText, brandText, warrantyNoText,
            prodTypeText, warrantyMonthsText, modelText, warranytyExpText, purchaseOnText, regDateText, purchaseFromText, prodCostText, countryProdText, warrantyStartText;

    private TextView edtStatus, edtCustomer, edtDetails, btnUpdate, btnUpclose, txtEditStatus;
    private EditText edtCity, edtContact, edtAddress, edtPostalCode, edtFailure, edtRemarks, editFilter;
    private RadioGroup group;
    private RadioButton btnNotYet, btnReceived, btnOnsite;
    private Spinner searchSpin;
    private String[] search = {"Search", "SR Number", "Brand", "Model", "Serial No.", "Status", "Consumer Email ID", "IC/Passport No."};
    private String sr_id = null;
    private SearchView searchView;
    ArrayList<ServiceDetails> serviceArrayList ;
   // CustomAdapter adapter;
//    String[] country_names , iso_codes ;
//String[] brand,model,srNo,status,icNo;



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
        searchSpin = (Spinner) rootView.findViewById(R.id.edtSearchInServReq);
        editFilter = (EditText) rootView.findViewById(R.id.editFilter);
        service = (TextView) rootView.findViewById(R.id.buttonService);
        product = (TextView) rootView.findViewById(R.id.buttonProduct);
        listView = (ListView) rootView.findViewById(R.id.listInServReq);
        service.setOnClickListener(this);
        product.setOnClickListener(this);
        editFilter.setOnEditorActionListener(this);
        editFilter.addTextChangedListener(this);


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


        ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, search);
        searchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpin.setAdapter(searchAdapter);
        searchSpin.setOnItemSelectedListener(this);

    }

    public void showAllService() {
        String url = NetUtils.HOST + NetUtils.EW_SERVICE_DETAILS_LIST;
        Log.i("myLog", "Post url:" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page_no", "1");
            jsonObject.put("page_count", "5");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("myLog", "Success Response" + response);
                    MasterCache.saveServiceListCache(response);
                    final CustomList listAdapter = new CustomList(getActivity(), MasterCache.SrNo, MasterCache.SrStatus, MasterCache.CreatedOn, MasterCache.Consumer, MasterCache.prdReqSerialNo, MasterCache.srReqIcNo,MasterCache.srReqBrandName, MasterCache.srReqModelName);
                    listView.setAdapter(listAdapter);
                    listAdapter.notifyDataSetChanged();
                 //   listView.setTextFilterEnabled(true);

/*                    editFilter.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            System.out.println("Text [" + s + "]");
                            listAdapter.getFilter().filter(s.toString());

                        }

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count,
                                                      int after) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });listAdapter.notifyDataSetChanged();*/

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("myLog", "Error Response");
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
<<<<<<< HEAD
                    //headers.put("Content-Type", "application/json");
=======
                    // headers.put("Content-Type", "application/json");
>>>>>>> origin
                    //headers.put("Accept", "application/json");
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
        switch (v.getId()) {
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

            case R.id.editFilter:
                searchService(String.valueOf(search));
               // searchService(search);
                break;

            case R.id.editCustomer:
                showCustomerDetails();
                break;

            case R.id.editDetails:
                showOtherDetails();
                break;

            case R.id.edtStatus:
                showStatusDetails();
                break;

            case R.id.updateService:
                showEditService();
                break;

            case R.id.updateClose:
                Toast.makeText(getActivity(), "Status closed", Toast.LENGTH_SHORT).show();
                break;

        }
    }


    /*public void searchServDetails(String option) {

        switch (option) {
            case "SR Number":
                searchService(search);
                break;
            case "Model":
                searchService(search);
                break;
            case "Brand":
                searchService(search);
                break;
            case "Serial No.":
                searchService(search);
                break;
            case "Status":
                searchService(search);
                break;
            case "Consumer Email ID":
                searchService(search);
                break;
            case "IC/Passport No.":
                searchService(search);
                break;
            default:
                break;
        }
    }*/


  //  public void searchService(String[] option){
       public void searchService(String search){


        String url = NetUtils.HOST + NetUtils.EW_SERVICE_SEARCH;

        Log.i("myLog", "Post url:" + url);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            switch (search) {
                case "SR Number":
                    jsonObject.put("page_no", "1");
                    jsonObject.put("page_count", "5");
                    jsonObject.put("sr_no", editFilter.getText().toString());
                    Log.i("Sr_no:::", editFilter.getText().toString());
                    break;
                case "Model":
                    jsonObject.put("page_no", "1");
                    jsonObject.put("page_count", "5");
                    jsonObject.put("model", editFilter.getText().toString());
                    Log.i("Modelll:::", editFilter.getText().toString());
                    break;
                case "Brand":
                    jsonObject.put("page_no", "1");
                    jsonObject.put("page_count", "5");
                    jsonObject.put("brand", editFilter.getText().toString());
                    Log.i("Brand:::", editFilter.getText().toString());
                    break;
                case "Serial No.":
                    jsonObject.put("page_no", "1");
                    jsonObject.put("page_count", "5");
                    jsonObject.put("serial_no", editFilter.getText().toString());
                    Log.i("Serial No:::", editFilter.getText().toString());

                    break;
                case "Status":
                    jsonObject.put("page_no", "1");
                    jsonObject.put("page_count", "5");
                    jsonObject.put("status", editFilter.getText().toString());
                    Log.i("Statuss:::", editFilter.getText().toString());

                    break;
                case "IC/Passport No.":
                    jsonObject.put("page_no", "1");
                    jsonObject.put("page_count", "5");
                    jsonObject.put("ic", editFilter.getText().toString());
                    Log.i("ICCC:::", editFilter.getText().toString());
                    break;
                default:
                    break;

                   }
            Log.i("myLog", "jsonObject string:" + jsonObject.toString());


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                       // MasterCache.saveServiceDetailCache(response);
                      /*  final CustomList spinAdapter = new CustomList(getActivity(),MasterCache.SrNo, MasterCache.SrStatus, MasterCache.CreatedOn, MasterCache.Consumer, MasterCache.prdReqSerialNo, MasterCache.srReqIcNo,MasterCache.srReqBrandName, MasterCache.srReqModelName);
                        listView.setAdapter(spinAdapter);
                        listView.setTextFilterEnabled(true);

                    editFilter.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                            spinAdapter.getFilter().filter(arg0);
                        }

                        @Override
                        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                        }

                        @Override
                        public void afterTextChanged(Editable arg0) {

                        }

                    });spinAdapter.notifyDataSetChanged();*/

                       Log.i("myLog", "Success Response:" + response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("myLog", "Error Response");
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null) {
                            Log.e("Volley", "Error. HTTP Status Code:" + networkResponse.statusCode);
                        }
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        //    headers.put("Content-Type", "application/json; charset=utf-8");
                        //  headers.put("Accept", "application/json");
                        headers.put("Authorization", ReverApplication.getSessionToken());
                        Log.i("AUTHORIZATION:::", ReverApplication.getSessionToken());
                        return headers;
                    }
                };
                requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showCustomerDetails(){
        edtCustomer.setText("DONE");
        linearUpdate.setVisibility(View.VISIBLE);

        txtCity.setVisibility(View.GONE);
        edtCity.setVisibility(View.VISIBLE);

        txtAddress.setVisibility(View.GONE);
        edtAddress.setVisibility(View.VISIBLE);

        txtPostalCode.setVisibility(View.GONE);
        edtPostalCode.setVisibility(View.VISIBLE);

        txtMobile.setVisibility(View.GONE);
        edtContact.setVisibility(View.VISIBLE);

        /*String city = edtCity.getText().toString();
        String contact = edtContact.getText().toString();
        String address = edtAddress.getText().toString();
        String pinCode = edtPostalCode.getText().toString();

        if(city.length() >= 3 && isValidCity(city)) {
            edtCity.setVisibility(View.GONE);
            txtCity.setVisibility(View.VISIBLE);
            txtCity.setText(edtCity.getText().toString());
        }else {
            Toast.makeText(getActivity(),"Please enter valid city",Toast.LENGTH_SHORT).show();
        }

        if(contact.length() >= 8 && isValidPhone(contact)){
            edtCity.setVisibility(View.GONE);
            txtMobile.setVisibility(View.VISIBLE);
            txtMobile.setText(edtContact.getText().toString());
        }else {
                Toast.makeText(getActivity(),"Please enter valid mobile number",Toast.LENGTH_SHORT).show();
        }

        if(address.length() >= 10 && isValidAddress(address)){
            edtAddress.setVisibility(View.GONE);
            txtAddress.setVisibility(View.VISIBLE);
            txtAddress.setText(edtAddress.getText().toString());
                }else {
                    Toast.makeText(getActivity(),"Please enter valid address",Toast.LENGTH_SHORT).show();
                }

        if(pinCode.length() >= 5 && isValidPostalCode(pinCode)){
            edtPostalCode.setVisibility(View.GONE);
            txtPostalCode.setVisibility(View.VISIBLE);
            txtPostalCode.setText(edtPostalCode.getText().toString());
        } else {
            Toast.makeText(getActivity(),"Please enter valid postal code",Toast.LENGTH_SHORT).show();
        }
        showEditService();*/
      }

    public void showOtherDetails(){
        edtDetails.setText("DONE");
        linearUpdate.setVisibility(View.VISIBLE);
        txtFailDesc.setVisibility(View.GONE);
        edtFailure.setVisibility(View.VISIBLE);
       // txtFailDesc.setText(edtFailure.getText().toString());

        txtRemarks.setVisibility(View.GONE);
        edtRemarks.setVisibility(View.VISIBLE);
        //txtRemarks.setText(edtRemarks.getText().toString());


      /*  String failure = edtFailure.getText().toString();
        String remarks = edtRemarks.getText().toString();

        if(failure.length() >= 4 && isValidFailure(failure)) {
            if(remarks.length() >= 4 && isValidRemarks(remarks)) {
                edtFailure.setVisibility(View.GONE);
                edtRemarks.setVisibility(View.GONE);
            }else {
                Toast.makeText(getActivity(), "Please enter valid remarks", Toast.LENGTH_SHORT).show();
            }}else {
            Toast.makeText(getActivity(), "Please enter valid failure description", Toast.LENGTH_SHORT).show();
        }
         /*   txtFailDesc.setVisibility(View.VISIBLE);
            txtFailDesc.setText(edtFailure.getText().toString());
        }else {
           }
           // txtRemarks.setVisibility(View.VISIBLE);
            //txtRemarks.setText(edtRemarks.getText().toString());
        }else {

*/

//        showEditService();

    }

    public void showStatusDetails() {
        edtStatus.setText("DONE");
        linearUpdate.setVisibility(View.VISIBLE);
        linearStatus.setVisibility(View.GONE);
        linearRadio.setVisibility(View.VISIBLE);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.notYetReceived) {
                    linearRadio.setVisibility(View.GONE);
                    // linearEdit.setVisibility(View.VISIBLE);
                    linearStatus.setVisibility(View.VISIBLE);
                    txtStatus.setText(btnNotYet.getText().toString());
                }
                if (checkedId == R.id.prodReceived) {
                    linearRadio.setVisibility(View.GONE);
                    // linearEdit.setVisibility(View.VISIBLE);
                    linearStatus.setVisibility(View.VISIBLE);
                    txtStatus.setText(btnReceived.getText().toString());
                }
                if (checkedId == R.id.onsiteRequest) {
                    linearRadio.setVisibility(View.GONE);
                    //linearEdit.setVisibility(View.VISIBLE);
                    linearStatus.setVisibility(View.VISIBLE);
                    txtStatus.setText(btnOnsite.getText().toString());
                }
            }
        });
       /* String status = txtStatus.getText().toString();

        if(status.length() >0){
            showEditService();
        }else {
            Toast.makeText(getActivity(),"Please select any one status",Toast.LENGTH_SHORT).show();
        }*/

    }

    public void showServiceReqDetails(){
        linearSR.removeAllViews();
        service.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_txt));
        product.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorwhite));
        service.setTextColor(ContextCompat.getColor(getContext(), R.color.colorwhite));
        product.setTextColor(ContextCompat.getColor(getContext(), R.color.blue_txt));
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

        btnUpdate = (TextView)rootView.findViewById(R.id.updateService);
        btnUpclose =(TextView)rootView.findViewById(R.id.updateClose);
        edtStatus =(TextView)rootView.findViewById(R.id.edtStatus);
        edtCustomer = (TextView)rootView.findViewById(R.id.editCustomer);
        edtDetails =(TextView)rootView.findViewById(R.id.editDetails);
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
        txtEditStatus =(TextView)v.findViewById(R.id.editProdStatus);
       // txtEditStatus =(TextView)v.findViewById(group.getCheckedRadioButtonId());

        linearEdit = (LinearLayout)v.findViewById(R.id.linearTextStatus);
        linearRadio = (LinearLayout)v.findViewById(R.id.linearRadioGroup);
        linearStatus = (LinearLayout)v.findViewById(R.id.statusInServiceReq);
        linearUpdate =(LinearLayout)v.findViewById(R.id.linearUpdateService);
        edtCity = (EditText)v.findViewById(R.id.editCity);
        edtAddress =(EditText)v.findViewById(R.id.editAddress);
        edtPostalCode = (EditText)v.findViewById(R.id.editPostalCode);
        edtContact =(EditText)v.findViewById(R.id.editMobile);
        edtFailure = (EditText)v.findViewById(R.id.editFailureDesc);
        edtRemarks =(EditText)v.findViewById(R.id.editRemarks);
        //edtProdStatus = (EditText)v.findViewById(R.id.editProdStatus);
        group =(RadioGroup)v.findViewById(R.id.myRadioGroup);
        btnNotYet = (RadioButton)v.findViewById(R.id.notYetReceived);
        btnReceived = (RadioButton)v.findViewById(R.id.prodReceived);
        btnOnsite =(RadioButton)v.findViewById(R.id.onsiteRequest);


        edtStatus.setOnClickListener(this);
        edtDetails.setOnClickListener(this);
        edtCustomer.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnUpclose.setOnClickListener(this);
    }

    private boolean isValidCity(String city){
        String CITY_PATTERN = "^[a-zA-Z]+(?:(?:\\s+|-)[a-zA-Z]+)*$";
        Pattern pattern = Pattern.compile(CITY_PATTERN);
        Matcher matcher = pattern.matcher(city);
        return matcher.matches();
    }

    private boolean isValidPhone(String phone){
        String PHONE_PATTERN = "^[+]?[0-9]{8,15}$";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    private boolean isValidAddress(String address){
        String ADDRESS_PATTERN = "[a-zA-Z\\d\\s\\-\\,\\#\\.\\+]+";
        Pattern pattern =Pattern.compile(ADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    private boolean isValidPostalCode(String pinCode){
        String CODE_PATTERN = "^\\d{6}(?:[-\\s]\\d{4})?$";
        Pattern pattern = Pattern.compile(CODE_PATTERN);
        Matcher matcher = pattern.matcher(pinCode);
        return matcher.matches();
    }
    private boolean isValidFailure(String failure){
        String FAILURE_PATTERN = "^[a-zA-Z]+(?:(?:\\s+|-)[a-zA-Z]+)*$";
        Pattern pattern = Pattern.compile(FAILURE_PATTERN);
        Matcher matcher = pattern.matcher(failure);
        return matcher.matches();
    }
    private boolean isValidRemarks(String remarks){
        String REMARKS_PATTERN = "^[a-zA-Z]+(?:(?:\\s+|-)[a-zA-Z]+)*$";
        Pattern pattern = Pattern.compile(REMARKS_PATTERN);
        Matcher matcher = pattern.matcher(remarks);
        return matcher.matches();
    }
    public void showEditService() {
        edtRemarks.setVisibility(View.GONE);
        txtRemarks.setVisibility(View.VISIBLE);
        txtRemarks.setText(edtRemarks.getText().toString());

        edtCity.setVisibility(View.GONE);
        txtCity.setVisibility(View.VISIBLE);
        txtCity.setText(edtCity.getText().toString());

        edtContact.setVisibility(View.GONE);
        txtMobile.setVisibility(View.VISIBLE);
        txtMobile.setText(edtContact.getText().toString());

        edtAddress.setVisibility(View.GONE);
        txtAddress.setVisibility(View.VISIBLE);
        txtAddress.setText(edtAddress.getText().toString());

        edtPostalCode.setVisibility(View.GONE);
        txtPostalCode.setVisibility(View.VISIBLE);
        txtPostalCode.setText(edtPostalCode.getText().toString());

        edtFailure.setVisibility(View.GONE);
        txtFailDesc.setVisibility(View.VISIBLE);
        txtFailDesc.setText(edtFailure.getText().toString());

        String url = NetUtils.HOST+ NetUtils.EW_SERVICE_EDIT;
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("consumer_id", MasterCache.srReqUserId.get(0));
                jsonObject.put("other_desc", MasterCache.srReqOtherDesc.get(0));
                jsonObject.put("created_on",MasterCache.srCreatedOn.get(0));
                jsonObject.put("state", MasterCache.srReqState.get(0));
                jsonObject.put("serial_no",MasterCache.srReqSerialNo.get(0));
                jsonObject.put("model_name",MasterCache.srReqModelName.get(0));
                jsonObject.put("brand_name",MasterCache.srReqBrandName.get(0));
                jsonObject.put("first_name",MasterCache.srReqFirstName.get(0));
                jsonObject.put("company_name",MasterCache.srCompanyName.get(0));
                jsonObject.put("sr_no",MasterCache.srReqSrNo.get(0));
                jsonObject.put("user_id",MasterCache.srReqUserId.get(0));
                jsonObject.put("warranty_status",MasterCache.srReqWarrantyStatus.get(0));
                jsonObject.put("created_by",MasterCache.srReqCreatedBy.get(0));
               // jsonObject.put("eq_stock_id",MasterCache.eqs180860,
                jsonObject.put("status",MasterCache.srReqStatus.get(0));
                jsonObject.put("product_type",MasterCache.srReqProdType.get(0));
                jsonObject.put("country_code",MasterCache.srReqCountryCode.get(0));
                jsonObject.put("address_line1",MasterCache.srReqAddress.get(0));
                //jsonObject.put("ew_count ",
                jsonObject.put("last_name",MasterCache.srReqLastName.get(0));
                jsonObject.put("return_count",MasterCache.srReqReturnCount.get(0));
                jsonObject.put("city", txtCity.getText().toString());
                jsonObject.put("address_line", txtAddress.getText().toString());
                jsonObject.put("mobile", txtMobile.getText().toString());
                jsonObject.put("postal_code", txtPostalCode.getText().toString());
                jsonObject.put("failure_desc", txtFailDesc.getText().toString());
                jsonObject.put("remarks", txtRemarks.getText().toString());
                jsonObject.put("sr_id", MasterCache.srReqSrId.get(0));
                jsonObject.put("product_status",txtStatus.getText().toString());
                Log.i("myLog", "jsonObject string:" + jsonObject.toString());

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("myLog", "Success Response:" + response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("myLog","Error Response");
                        NetworkResponse networkResponse = error.networkResponse;
                        if (networkResponse != null) {
                            Log.e("Volley", "Error. HTTP Status Code:"+networkResponse.statusCode);
                        }
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        //    headers.put("Content-Type", "application/json; charset=utf-8");
                        //  headers.put("Accept", "application/json");
                        headers.put("Authorization", ReverApplication.getSessionToken());
                        Log.i("AUTHORIZATION:::", ReverApplication.getSessionToken());
                        return headers;
                    }
                };
                requestQueue.add(jsonObjectRequest);
            } catch (JSONException e) {
                e.printStackTrace();
            }
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


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        searchSpin.setSelection(position);
        String item = parent.getItemAtPosition(position).toString();
        editFilter.setText(item);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    searchService(String.valueOf(search));
                   //searchService(search);
                    return true;
                }
                return false;
            }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public class CustomList extends ArrayAdapter<String> {
        private List<String> srNo, status, createdOn, customer, serialNo, brand, model, icNo;
        private Activity context;


      //  public CustomList(Activity context, List<String> srNo, List<String> status, List<String> createdOn, List<String> customer, List<String> serialNo, List<String> brand, List<String> model, List<String> icNo) {

        public CustomList(Activity context, List<String> srNo, List<String> srStatus, List<String> srCreated, List<String> customer, List<String> srReqBrandName, List<String> srReqModelName, List<String> srReqSerialNo, List<String> srReqIcNo) {
            super(context, R.layout.service_list, srNo);
            this.context = context;
            this.srNo = srNo;
            this.status = srStatus;
            this.createdOn = srCreated;
            this.customer = customer;
            this.serialNo = srReqSerialNo;
            this.brand = srReqBrandName ;
            this.model = srReqModelName ;
            this.icNo = srReqIcNo;
         }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.service_list, null, true);
            TextView txtStatus = (TextView) listViewItem.findViewById(R.id.status);
            TextView txtConsumer = (TextView) listViewItem.findViewById(R.id.customerName);
            TextView txtCreatedOn = (TextView)listViewItem.findViewById(R.id.service_date);
            TextView txtSrNo = (TextView)listViewItem.findViewById(R.id.customerId);
            TextView txtLstSerial = (TextView)listViewItem.findViewById(R.id.serialList);
            TextView txtLstBrand = (TextView)listViewItem.findViewById(R.id.brandList);
            TextView txtLstModel = (TextView)listViewItem.findViewById(R.id.modelList);
            TextView txtLstIc = (TextView)listViewItem.findViewById(R.id.icList);
           // txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.alert_small, 0, 0, 0);
            //txtCreatedOn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.calendar_small, 0, 0, 0);
            //txtConsumer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_f, 0, 0, 0);*/
            txtStatus.setText(status.get(position));
            txtConsumer.setText(customer.get(position));
            txtCreatedOn.setText(createdOn.get(position));
            txtSrNo.setText(srNo.get(position));
          /*  txtLstBrand.setText(MasterCache.srReqBrandName.get(0));
            Log.i("BRANDD:", txtLstBrand.getText().toString());

            txtLstModel.setText(MasterCache.srReqModelName.get(0));
            Log.i("listMODDD:", txtLstModel.getText().toString());

            txtLstSerial.setText(MasterCache.srReqSerialNo.get(0));
            Log.i("listSRRr:", txtLstSerial.getText().toString());

            txtLstIc.setText(MasterCache.srReqIcNo.get(0));
            Log.i("ListICC:", txtLstIc.getText().toString());*/
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

/*public class CustomAdapter extends BaseAdapter implements Filterable {

    Context context;
    ArrayList<ServiceDetails> serviceDetailsArrayList;
    ArrayList<ServiceDetails> mStringFilterList;
    ValueFilter valueFilter;

    CustomAdapter(Context context , ArrayList<ServiceDetails> serviceDetailsArrayList) {
        this.context = context;
        this.serviceDetailsArrayList = serviceDetailsArrayList;
        mStringFilterList = serviceDetailsArrayList;
    }

    @Override
    public int getCount() {
        return serviceDetailsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return serviceDetailsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return serviceDetailsArrayList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position , View convertView , ViewGroup parent ) {

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        convertView = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item, null);
            TextView text = (TextView) convertView.findViewById(R.id.customerId);
            TextView text1 = (TextView) convertView.findViewById(R.id.status);
            TextView text2 = (TextView) convertView.findViewById(R.id.customerName);
            TextView text3 = (TextView) convertView.findViewById(R.id.service_date);
            TextView text4 = (TextView)convertView.findViewById(R.id.serialList);
            TextView text5 = (TextView)convertView.findViewById(R.id.brandList);
            TextView text6 = (TextView)convertView.findViewById(R.id.modelList);
            TextView text7 = (TextView)convertView.findViewById(R.id.icList);



            ServiceDetails c = serviceDetailsArrayList.get(position);
             text.setText(c.getSrNo());
             text1.setText(c.getSrStatus());
           // text2.setText(c.getSrReqFirstName());
            //text3.setText(c.getSrCreatedOn());
            text4.setText(c.getSrReqSerialNo());
            text5.setText(c.getSrReqBrandName());
            text6.setText(c.getSrReqModelName());
            text7.setText(c.getSrReqIcNo());

        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<ServiceDetails> filterList = new ArrayList<ServiceDetails>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ( (mStringFilterList.get(i).getSrNo().toUpperCase() )
                            .contains(constraint.toString().toUpperCase())) {

                        ServiceDetails details = new ServiceDetails(mStringFilterList.get(i).getSrNo() ,
                                mStringFilterList.get(i).getSrReqBrandName() ,
                                mStringFilterList.get(i).getSrId(),
                                mStringFilterList.get(i).getSrCreatedOn(),
                                mStringFilterList.get(i).getSrCreatedBy(),
                                mStringFilterList.get(i).getSrReqModelName(),
                                mStringFilterList.get(i).getSrReqIcNo(),
                                mStringFilterList.get(i).getSrReqSerialNo(),
                                mStringFilterList.get(i).getSrStatus(),
                                mStringFilterList.get(i).getSrReqReturnCount(),
                                mStringFilterList.get(i).getSrReqWarrantyStatus(),
                                mStringFilterList.get(i).getSrReqUserId(),
                                mStringFilterList.get(i).getSrReqFirstName(),
                                mStringFilterList.get(i).getSrReqLastName(),
                                mStringFilterList.get(i).getSrReqCountryCode(),
                                mStringFilterList.get(i).getSrReqMobile(),
                                mStringFilterList.get(i).getSrReqAddress(),
                                mStringFilterList.get(i).getSrReqCity(),
                                mStringFilterList.get(i).getSrReqState(),
                                mStringFilterList.get(i).getSrReqPostalCode(),
                                mStringFilterList.get(i).getSrReqOtherDesc(),
                                mStringFilterList.get(i).getSrReqRemarks(),
                                mStringFilterList.get(i).getSrReqProductStatus(),
                                mStringFilterList.get(i).getSrCompanyName(),
                                mStringFilterList.get(i).getSrReqProdType(),
                                mStringFilterList.get(i).getSrFailureDesc());

                        filterList.add(details);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            serviceDetailsArrayList = (ArrayList<ServiceDetails>) results.values;
            notifyDataSetChanged();
        }

    }

}*/
/*listSearch =(ListView)rootView.findViewById(R.id.listInServReq);
        searchView = (SearchView)rootView. findViewById(R.id.search_view);

        serviceArrayList = new ArrayList<ServiceDetails>();
        for (int i = 0; i < brand.length; i++) {
        ServiceDetails details = new ServiceDetails(brand[i] ,model[i],srNo[i],icNo[i],);
        serviceArrayList.add(details);
        }

        adapter = new CustomAdapter(getActivity(), serviceArrayList );
        listSearch.setAdapter(adapter);

        searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) this);*/



