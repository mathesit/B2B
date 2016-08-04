package com.rever.rever_b2b.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
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
import com.rever.rever_b2b.model.EWDetails;
import com.rever.rever_b2b.model.EWTabCallLogs;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oviya on 7/13/2016.
 */
public class DashboardExtended extends Fragment implements View.OnClickListener {
    private View rootView;
    private TableLayout tblEWDetails, tblFailure;
    private TextView txtLogCount, txtRequestCount, txtPendingCount, txtSearch;
    private TableRow.LayoutParams EwParams, failureParams,failureLineParams,ewLineParams,EWlineParams;
    private int width, height;

    private Spinner countrydropdown, branddropdown, productdropdown,searchSpin,addN2selectcountry,addN2curreny;

    private TextView txtProductDetails,txtExWrDetails,txtCallLogs,txtClaimHis,newEwInDashboard,DescriptionContent,addN2purchasedOn,addN2warrSdate,edtPurchasedOnStep3;

    private TextView txtCancelAddNew, txtNextAddNew,txtBrandStep2, txtProdTypeStep2, txtSerialNoStep2, txtModelStep2,
            txtPurchaseOnStep2, txtWarrStartDateStep2, txtBrandStep3, txtProdTypeStep3, txtSerialNoStep3, txtModelStep3,
            txtPurchaseOnStep3, txtWarrStartDateStep3,  txtBrandStep4, txtProdTypeStep4, txtSerialNoStep4, txtModelStep4;

    private EditText edtSearch, edtRemarks, edtProdStatus, edtFirstName, edtLastName, edtEmail, edtPhNo, edtAddressLine1, edtAddressLine2,edtmobile, edtCity,
            edtState, edtPostalCode, edtSerialNo, edtModel, edtProdCostStep2, edtPurchasedFromStep2 , edtBillNoStep2, edtPurchasedOnStep2,
            edtWarrMonthStep2,  edtWarrNoStep2, edtInvoiceNoStep2, edtAdditInfo2, edtProdCostStep3, edtPurchasedFromStep3 ,
            edtWarrMonthStep3,  edtWarrNoStep3, edtInvoiceNoStep3, edtAdditInfo3, editFilter,edtSerialno;

    private Spinner spinCountry,spinSalutation, spinProdType,
            spinBrand, spinCountryStep2, spinCurrencyStep2, spinCountryStep3, spinCurrencyStep3;
    private ScrollView scrollStep1AddNew, scrollStep2AddNew, scrollStep3AddNew, scrollStep4AddNew;

    private Button btnChooseFile1Step2, btnChooseFile2Step2, btnChooseFile1Step3, btnChooseFile2Step3;
    private long dateLong, warrantyLong, dateLong3, warrantyLong3;
    private AlertDialog.Builder alertDialog;
    private boolean userExists =false;
    private static Dialog dialog,dialog2,dialog3;
    private ImageView imgStepsAddNew;
    private LinearLayout tab_bar;
    private static  ProgressDialog progressDialog;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_dashboard, container, false);
        initViews();
        initParams();
        loadTopFailures();
        GetEWListTask();
      //  loadTopBrands();
        return rootView;

    }
    public class CustomListforEwShowAll extends ArrayAdapter<String> {
        private List<String> vbrand_name, vproduct_type, vserial_no, vconsumer_name;
        private Activity context;



        public CustomListforEwShowAll(Activity context, List<String> vbrand_name, List<String> vproduct_type, List<String> vserial_no, List<String> vconsumer_name) {
            super(context, R.layout.top_list_in_dashboard, vbrand_name);
            this.context = context;
            this.vbrand_name = vbrand_name;
            this.vproduct_type = vproduct_type;
            this.vserial_no = vserial_no;
            this.vconsumer_name = vconsumer_name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.top_list_in_dashboard, null, true);
            TextView txtbrand_name = (TextView) listViewItem.findViewById(R.id.brand);
            TextView txtproduct_type = (TextView) listViewItem.findViewById(R.id.product_type);
            TextView txtserial_no = (TextView) listViewItem.findViewById(R.id.serial_no);
            TextView txtconsumer_name = (TextView) listViewItem.findViewById(R.id.cus_name);


            txtbrand_name.setText(vbrand_name.get(position));
            txtproduct_type.setText(vproduct_type.get(position));
            txtserial_no.setText(vserial_no.get(position));
            txtconsumer_name.setText(vconsumer_name.get(position));
            return listViewItem;
        }
    }

    private void initViews(){
        //lv=(ListView) rootView.findViewById(R.id.listview_EW_details);

        txtLogCount = (TextView)rootView.findViewById(R.id.txtCaseLogsCountInEwDash);
        txtPendingCount = (TextView)rootView.findViewById(R.id.txtPendQuotCountInEwDash);
        txtRequestCount = (TextView)rootView.findViewById(R.id.txtServReqCountInEwDash);
        newEwInDashboard = (TextView)rootView.findViewById(R.id.newEwInDashboard);
        tblFailure = (TableLayout)rootView.findViewById(R.id.tblFailuresInEwDash);
        tblEWDetails = (TableLayout)rootView.findViewById(R.id.tblEwListInEwDash);
        txtSearch = (TextView)rootView.findViewById(R.id.searchInDash);
        txtSearch.setOnClickListener(this);
        loadCaseLog();
        loadPendingQuote();
        loadServiceLog();
        Getcountrydropdown();
        Getbranddropdown();
        Getproductdropdown();
//        tblEWDetails.setVisibility(View.VISIBLE);
//        customAdapter = new DashboardExtended.CustomListforEwShowAll(getActivity(),
//                MasterCache.vbrandname, MasterCache.vprodtype, MasterCache.vserialno, MasterCache.vconsumername);
//        lv.setAdapter(customAdapter);
        newEwInDashboard.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                addNewEW();
                //openAddNewEw1();

            }});

    }

    private void initParams(){
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels;
        width = metrics.widthPixels;
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            Toast.makeText(getActivity(), "Xlarge screen", Toast.LENGTH_LONG).show();
            int swidth = (width *80)/100;
            EwParams = new TableRow.LayoutParams(swidth/5,dpToPx(50));
            failureParams = new TableRow.LayoutParams((swidth/2)/5,dpToPx(50));
            failureLineParams = new TableRow.LayoutParams(swidth/2,dpToPx(1));
            failureLineParams.span = 4;
            ewLineParams = new TableRow.LayoutParams(swidth,dpToPx(1));
            ewLineParams.span = 5;

        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(getActivity(), "Large screen", Toast.LENGTH_LONG).show();
            int swidth = width - dpToPx(150);
            Log.i("myLog","Swidth:"+swidth);
            EwParams = new TableRow.LayoutParams(swidth/8,dpToPx(45));
            failureParams = new TableRow.LayoutParams((swidth/2)/6,dpToPx(50));
            failureLineParams = new TableRow.LayoutParams(swidth/2,dpToPx(1));
            failureLineParams.span = 4;
            ewLineParams = new TableRow.LayoutParams(swidth,dpToPx(1));
            ewLineParams.span = 5;

            // failureParams = new TableRow.LayoutParams((swidth/2)/5,dpToPx(45));

        } else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(getActivity(), "Normal sized screen" , Toast.LENGTH_LONG).show();
            int swidth = (width *75)/100;
            EwParams = new TableRow.LayoutParams(swidth,dpToPx(50));
            failureParams = new TableRow.LayoutParams((swidth/2)/5,dpToPx(40));
            failureLineParams = new TableRow.LayoutParams(swidth/2,dpToPx(1));
            failureLineParams.span = 4;
            ewLineParams = new TableRow.LayoutParams(swidth,dpToPx(1));
            ewLineParams.span = 5;


        }
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    public void loadCaseLog() {
        String caseLoqUrl = String.format(NetUtils.EW_DASH_LOG);
        String url = NetUtils.HOST + caseLoqUrl;
        Log.i("myLog", " loadCaseLogDetails url : " + url);


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("myLog", "loadCaseLog Success Response");
                        MasterCache.saveEWLogDetails(String.valueOf(response));
                        txtLogCount.setText(MasterCache.ewLogCount.get(0));
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
                //headers.put("Content-Type", "application/json");
               // headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);

    }

    public void loadPendingQuote(){
        String pendingLogUrl = String.format(NetUtils.EW_DASH_PENDING);
        String url = NetUtils.HOST + pendingLogUrl;
        Log.i("myLog", " loadPendingQuote url : " + url);


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("myLog", "loadPending Success Response");
                        MasterCache.saveEWPendingList(String.valueOf(response));
                        txtPendingCount.setText(MasterCache.ewCount.get(0));
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
                //headers.put("Content-Type", "application/json");
                // headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);


    }

    public void loadServiceLog() {
        String serviceLogUrl = String.format(NetUtils.EW_DASH_SERVICE);
        String url = NetUtils.HOST + serviceLogUrl;
        Log.i("myLog", " loadServiceLog url : " + url);


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("myLog", "loadServiceLog Success Response");
                        MasterCache.saveEWServiceCount(String.valueOf(response));
                        txtRequestCount.setText(MasterCache.ewSrCount.get(0));

//                        loadCount();
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
                //headers.put("Content-Type", "application/json");
                // headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);

    }

    public void loadTopFailures(){
        String topFailUrl = String.format(NetUtils.EW_DASH_FAILURES);
        String url = NetUtils.HOST + topFailUrl;
        Log.i("myLog", " loadTopFailures url : " + url);


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("myLog", "loadTopFail Success Response");
                        MasterCache.saveTopFailures(String.valueOf(response));
                        showTopFailures();
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
                //headers.put("Content-Type", "application/json");
                // headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);
    }

//
//
///*    public void showTopBrands() {
//        Log.i("myLog", "displayTopBrands");
//        int size = MasterCache.brandName.size();
//        if (size >= 5)
//            size = 5;
//        Log.i("myLog", "Size:" + MasterCache.brandName.size());
//        for (int start = 0; start < size; start++) {
//            Log.i("myLog", "displayTopBrand index:" + start);
//            TableRow tr = new TableRow(getActivity());
//            RelativeLayout rel = new RelativeLayout(getActivity());
//
//            TextView txtBrand = new TextView(getActivity());
//            txtBrand.setText(MasterCache.brandName.get(start));
//            Log.i("Brand:", MasterCache.brandName.get(start));
//            txtBrand.setGravity(Gravity.CENTER);
//            tr.addView(txtBrand, EwParams);
//
//            TextView txtProduct = new TextView(getActivity());
//            txtProduct.setText(MasterCache.productType.get(start));
//            Log.i("Product:", MasterCache.productType.get(start));
//            txtProduct.setGravity(Gravity.CENTER);
//            tr.addView(txtProduct, EwParams);
//
//            TextView txtSerial = new TextView(getActivity());
//            txtSerial.setText(MasterCache.serialNo.get(start));
//            Log.i("Serial:", MasterCache.serialNo.get(start));
//            txtSerial.setGravity(Gravity.CENTER);
//            tr.addView(txtSerial, EwParams);
//
//            TextView txtCustomer = new TextView(getActivity());
//            txtCustomer.setText(String.valueOf(MasterCache.consumerName.get(start)));
//            txtCustomer.setGravity(Gravity.CENTER);
//            tr.addView(txtCustomer, EwParams);
//            Log.i("Customer:", MasterCache.consumerName.get(start));
//
//            ImageView img = new ImageView(getActivity());
//            img.setImageResource(R.drawable.dot);
//            //  rel.addView(img, alertParam);
//            tr.addView(rel, EwParams);
//
//
//            View v = new View(getActivity());
//            v.setBackgroundColor(Color.LTGRAY);
//            TableRow trLine = new TableRow(getActivity());
//            trLine.addView(v, ewLineParams);
//            tblEWDetails.addView(tr);
//            tblEWDetails.addView(trLine);
//        }
//    }*/

    public void showEwList(){
        int size = MasterCache.vbrandname.size();
        if(size>=5){
            size=5;
        }
        for(int start=0; start<size; start++) {
            TableRow tr= new TableRow(getActivity());
            TextView txtBrand = new TextView(getActivity());
            txtBrand.setText(MasterCache.vbrandname.get(start));
            txtBrand.setGravity(Gravity.CENTER);
            tr.addView(txtBrand, EwParams);

            TextView txtModel = new TextView(getActivity());
            txtModel.setText(MasterCache.vprodtype.get(start));
            txtModel.setGravity(Gravity.CENTER);
            tr.addView(txtModel, EwParams);

            TextView txtProdType = new TextView(getActivity());
            txtProdType.setText(MasterCache.vserialno.get(start));
            txtProdType.setGravity(Gravity.CENTER);
            tr.addView(txtProdType, EwParams);

            TextView txtCount = new TextView(getActivity());
            txtCount.setText(String.valueOf(MasterCache.vconsumername.get(start)));
            txtCount.setGravity(Gravity.CENTER);
            tr.addView(txtCount, EwParams);

            LinearLayout imgalert = new LinearLayout(getActivity());
            ImageView image = new ImageView(getActivity());
            image.setImageResource(R.drawable.alert1);
            image.setMaxHeight(5);
            image.setMaxWidth(10);
            imgalert.addView(image);

            tr.addView(imgalert, EwParams);

            View v = new View(getActivity());
            v.setBackgroundColor(Color.LTGRAY);
            TableRow trLine = new TableRow(getActivity());
            trLine.addView(v, ewLineParams);

           /* View v = new View(getActivity());
            v.setBackgroundColor(Color.LTGRAY);
            TableRow trLine = new TableRow(getActivity());
            trLine.addView(v, failureParams);*/
            tblEWDetails.addView(tr);
            tblEWDetails.addView(trLine);

            // tblFailure.addView(trLine);

        }
    }


    public void showTopFailures(){
        int size = MasterCache.failBrand.size();
        if(size>=5){
            size=5;
        }
        for(int start=0; start<size; start++) {
            TableRow tr= new TableRow(getActivity());
            TextView txtBrand = new TextView(getActivity());
            txtBrand.setText(MasterCache.failBrand.get(start));
            txtBrand.setGravity(Gravity.CENTER);
            tr.addView(txtBrand, failureParams);
            TextView txtModel = new TextView(getActivity());
            txtModel.setText(MasterCache.failModel.get(start));
            txtModel.setGravity(Gravity.CENTER);
            tr.addView(txtModel, failureParams);
            TextView txtProdType = new TextView(getActivity());
            txtProdType.setText(MasterCache.failProduct.get(start));
            txtProdType.setGravity(Gravity.CENTER);
            tr.addView(txtProdType, failureParams);
            TextView txtCount = new TextView(getActivity());
            txtCount.setText(String.valueOf(MasterCache.failCount.get(start)));
            txtCount.setGravity(Gravity.CENTER);
            tr.addView(txtCount, failureParams);
            View v = new View(getActivity());
            v.setBackgroundColor(Color.LTGRAY);
            TableRow trLine = new TableRow(getActivity());
            trLine.addView(v, failureLineParams);

           /* View v = new View(getActivity());
            v.setBackgroundColor(Color.LTGRAY);
            TableRow trLine = new TableRow(getActivity());
            trLine.addView(v, failureParams);*/
            tblFailure.addView(tr);
            tblFailure.addView(trLine);

            // tblFailure.addView(trLine);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchInDash:
                showSearchSR();
                break;
            default:
                break;

        }
    }

    public void showSearchSR(){
        ServiceRequestExtended fragment = new ServiceRequestExtended();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linearFragmentInMain, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void GetEWListTask() {
        // HTTP POST
        String url = NetUtils.HOST + NetUtils.EXTENDED_WARRANTY_URL;
        Log.i("myLog", "Listview url:" + url);

        //declare other objects as per your need
        // progressDialog= ProgressDialog.show(getContext(), "Progress Dialog Title Text", "Process Description Text", true);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page_no", "1");
            jsonObject.put("page_count", "5");
            Log.i("myLog", "Data" + jsonObject);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // do something...
                    Log.i("myLog", "Success_Response_for_EW_LIST" + response);
                    MasterCache.saveEWDetailsCache(response);

                    showEwList();

                    //     progressDialog.dismiss();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // do something...
                    Log.i("myLog", "Error Response: " +error);
                }

            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    //headers.put("Content-Type", "application/json");
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



    public void addNewEW(){
        alertDialog = new  AlertDialog.Builder(getActivity());
        View convertView = getActivity().getLayoutInflater().inflate(R.layout.new_ew, null);
        alertDialog.setView(convertView);
        dialog = alertDialog.create();
        dialog.show();
        initNewSRViews(convertView);
        initEWViews1(convertView);
        initEW2Views(convertView);
        initEW3Views(convertView);
        edtEmail.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (!event.isShiftPressed()) {
                                String str = edtEmail.getText().toString();

                                GetAddNewEwUserEmail(str);

                                return true;
                            }
                        }
                        return false;
                    }
                });



    }


    public void GetAddNewEwUserEmail(String email) {
        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_ADD_NEW_EW_URL+email;
        Log.i("myLog", "url Add New EW" + url);
        progressDialog= ProgressDialog.show(getContext(), "Progressing", "Validating Email Id", true);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                {"errors":[{"message":"User does not exist!","code":107}]}
                Log.i("MyLog","Response : "+response);
                if (response.has("errors")){
                    Snackbar.make(getView(), "User does not exist", Snackbar.LENGTH_SHORT).show();

                }
                else {
                    Log.i("myLog", "saveAddNewEwEmailResponseCache : " + response);
                    MasterCache.AddNewEwEmailResponseCache(response);
                    edtFirstName.setText(MasterCache.userFirstName1.get(0));
                    edtLastName.setText(MasterCache.userLastName1.get(0));
                    edtPostalCode.setText(MasterCache.userPostal1.get(0));
                    edtAddressLine2.setText(MasterCache.userAddLine2.get(0));
                    edtPhNo.setText(MasterCache.userMobile1.get(0));
                    edtAddressLine1.setText(MasterCache.userAddLine1.get(0));
                    edtCity.setText(MasterCache.userCity1.get(0));
                    edtState.setText(MasterCache.userState1.get(0));
                    edtmobile.setText(MasterCache.userMobile1.get(0));
                }
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something...
                Log.i("myLog", "loadEWDetails Error Response");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                //headers.put("Content-Type", "application/json");
                //headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }


    public void initNewSRViews(View v){
        scrollStep1AddNew = (ScrollView)v.findViewById(R.id.scrollStep1InNewService);
        scrollStep2AddNew = (ScrollView)v.findViewById(R.id.scrollStep2InNewService);
        scrollStep3AddNew = (ScrollView)v.findViewById(R.id.scrollStep3InNewService);

        txtCancelAddNew = (TextView)v.findViewById(R.id.txtCancelInNewEW1);
        txtNextAddNew = (TextView)v.findViewById(R.id.txtNextStepInNewEW1);
        imgStepsAddNew = (ImageView)v.findViewById(R.id.imgStepsInNewService);

        txtCancelAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scrollStep1AddNew.getVisibility() == View.VISIBLE) {
                    dialog.dismiss();

                } else if (scrollStep2AddNew.getVisibility() == View.VISIBLE) {
                    scrollStep1AddNew.setVisibility(View.VISIBLE);
                    scrollStep2AddNew.setVisibility(View.INVISIBLE);
                    txtCancelAddNew.setText("Cancel");
                    txtNextAddNew.setText("Next Step");
                    imgStepsAddNew.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.step1));

                } else if (scrollStep3AddNew.getVisibility() == View.VISIBLE) {
                    scrollStep2AddNew.setVisibility(View.VISIBLE);
                    scrollStep3AddNew.setVisibility(View.INVISIBLE);
                    txtCancelAddNew.setText("Previous");
                    txtNextAddNew.setText("Next Step");
                    imgStepsAddNew.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.step2));

                }
            }
        });

        txtNextAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scrollStep1AddNew.getVisibility() == View.VISIBLE) {
                    scrollStep2AddNew.setVisibility(View.VISIBLE);
                    scrollStep1AddNew.setVisibility(View.INVISIBLE);
                    txtCancelAddNew.setText("Previous");
                    txtNextAddNew.setText("Next Step");
                    imgStepsAddNew.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.step2));
                    if (userExists) {

                    } else {
                        registerUser();
                    }
                    txtBrandStep2.setText(spinBrand.getSelectedItem().toString());
                    txtProdTypeStep2.setText(spinProdType.getSelectedItem().toString());
                    txtSerialNoStep2.setText(edtSerialNo.getText().toString());
                    txtModelStep2.setText(edtModel.getText().toString());

                } else if (scrollStep2AddNew.getVisibility() == View.VISIBLE) {
                    scrollStep3AddNew.setVisibility(View.VISIBLE);
                    scrollStep2AddNew.setVisibility(View.INVISIBLE);
                    txtCancelAddNew.setText("Previous");
                    txtNextAddNew.setText("save");
                    imgStepsAddNew.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.step3));
                    txtBrandStep3.setText(spinBrand.getSelectedItem().toString());
                    txtProdTypeStep3.setText(spinProdType.getSelectedItem().toString());
                    txtSerialNoStep3.setText(edtSerialNo.getText().toString());
                    txtModelStep3.setText(edtModel.getText().toString());

                    addManuWarranty();


                } else if (scrollStep3AddNew.getVisibility() == View.VISIBLE) {
                    scrollStep3AddNew.setVisibility(View.INVISIBLE);

                    addExtWarranty();
                    dialog.dismiss();

                }
            }
        });

    }
    public void initEWViews1(View v){
        edtFirstName = (EditText)v.findViewById(R.id.edtFirstNameInNewEW1);
        edtLastName = (EditText)v.findViewById(R.id.edtLastNameInNewEW1);
        edtEmail = (EditText)v.findViewById(R.id.edtEmailInNewEW1);
        edtPhNo = (EditText)v.findViewById(R.id.edtHomePhoneInNewEW1);
        edtmobile = (EditText)v.findViewById(R.id.edtMobileInNewEW1);
        edtAddressLine1 =(EditText)v.findViewById(R.id.edtAddress1InNewEW1);
        edtAddressLine2 =(EditText)v.findViewById(R.id.edtAddrLine2InNewEW1);
        edtCity =(EditText)v.findViewById(R.id.edtCityInNewEW1);
        edtState =(EditText)v.findViewById(R.id.edtStateInNewEW1);
        edtPostalCode =(EditText)v.findViewById(R.id.edtPostalCodeInNewEW1);
        edtSerialNo =(EditText)v.findViewById(R.id.edtSerialNoInNewEW1);
        edtModel = (EditText)v.findViewById(R.id.edtModelInNewEW1);
        spinCountry =(Spinner)v.findViewById(R.id.spinSelCountryInNewEW1);
        spinSalutation = (Spinner)v.findViewById(R.id.spinSalutationInNewEW1);
        spinProdType = (Spinner)v.findViewById(R.id.spinnerProdTypeInNewEW1);
        spinBrand = (Spinner)v.findViewById(R.id.spinBrandInNewEW1);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, MasterCache.country_name);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCountry.setAdapter(dataAdapter);

        ArrayList<String> al = new ArrayList<>();
        al.add("Mr"); al.add("Ms"); al.add("Mrs");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, al);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSalutation.setAdapter(dataAdapter1);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, MasterCache.brand_name);
        spinBrand.setAdapter(adapter);

        ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, MasterCache.prodTypeNames);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinProdType.setAdapter(adp);

        //getProductType();
        //getBrandByType();

        edtEmail.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //do here your stuff f
                    String email = edtEmail.getText().toString();
                    userExists = false;
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    if (email != null && !email.equalsIgnoreCase(""))
                        //getUserInfo(email);
                        GetAddNewEwUserEmail(email);
                    return true;
                }
                return false;
            }
        });
    }

    public void initEW2Views(View v){
        txtBrandStep2 = (TextView)v.findViewById(R.id.txtBrandValInEW2);
        txtProdTypeStep2 = (TextView)v.findViewById(R.id.txtProdTypeValInEW2);
        txtSerialNoStep2 = (TextView)v.findViewById(R.id.txtSerialNoValInEW2);
        txtModelStep2 = (TextView)v.findViewById(R.id.txtModelValInEW2);
        txtPurchaseOnStep2 = (TextView)v.findViewById(R.id.txtPurchasedOnInNewEW2);
        edtProdCostStep2 = (EditText)v.findViewById(R.id.edtProdCostInNewEW2);
        edtWarrMonthStep2 = (EditText)v.findViewById(R.id.edtWarrMonthInNewEW2);
        edtPurchasedFromStep2 = (EditText)v.findViewById(R.id.edtPurchaseFromInNewEW2);

        txtWarrStartDateStep2 = (TextView)v.findViewById(R.id.txtWarrStartDateInNewEW2);
        edtWarrNoStep2 = (EditText)v.findViewById(R.id.edtWarrNoInNewEW2);
        edtInvoiceNoStep2 = (EditText)v.findViewById(R.id.edtInvoiceNoInNewEW2);
        edtAdditInfo2 = (EditText)v.findViewById(R.id.edtAdditionalInfoInNewEW2);
        btnChooseFile1Step2 = (Button)v.findViewById(R.id.btnChooseFile1InNewEW2);
        btnChooseFile2Step2 = (Button)v.findViewById(R.id.btnChooseFile2InNewEW2);
        spinCountryStep2 = (Spinner)v.findViewById(R.id.spinCountryInNewEW2);
        spinCurrencyStep2 = (Spinner)v.findViewById(R.id.spinCurrencyInNewEW2);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, MasterCache.country_name);
        spinCountryStep2.setAdapter(adapter);

        ArrayAdapter<String> daaAdapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, MasterCache.currency_code);
        daaAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCurrencyStep2.setAdapter(daaAdapter1);

        txtPurchaseOnStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog("Manu", "Purchased", txtPurchaseOnStep2);
            }
        });

        txtWarrStartDateStep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog("Manu", "Warranty", txtWarrStartDateStep2);
            }
        });

        btnChooseFile1Step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnChooseFile2Step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void initEW3Views(View v){
        txtBrandStep3 = (TextView)v.findViewById(R.id.txtBrandValInNewEW3);
        txtProdTypeStep3 = (TextView)v.findViewById(R.id.txtProdTypeValInNewEW3);
        txtSerialNoStep3 = (TextView)v.findViewById(R.id.txtSerialNoValInNewEW3);
        txtModelStep3 = (TextView)v.findViewById(R.id.txtModelValInNewEW3);
        txtPurchaseOnStep3 = (TextView)v.findViewById(R.id.txtPurchasedOnInNewEW3);
        edtProdCostStep3 = (EditText)v.findViewById(R.id.edtProdCostInNewEW3);
        edtPurchasedOnStep3 = (TextView)v.findViewById(R.id.txtPurchasedOnInNewEW3);
        edtWarrMonthStep3 = (EditText)v.findViewById(R.id.edtWarrMonthInNewEW3);
        edtPurchasedFromStep3 = (EditText)v.findViewById(R.id.edtPurchaseFromInNewEW3);

        txtWarrStartDateStep3 = (TextView)v.findViewById(R.id.txtWarrStartDateInNewEW3);
        edtWarrNoStep3 = (EditText)v.findViewById(R.id.edtWarrNoInNewEW3);
        edtInvoiceNoStep3 = (EditText)v.findViewById(R.id.edtInvoiceNoInNewEW3);
        edtAdditInfo3 = (EditText)v.findViewById(R.id.edtAdditionalInfoInNewEW3);
        btnChooseFile1Step3 = (Button)v.findViewById(R.id.btnChooseFile1InNewEW3);
        btnChooseFile2Step2 = (Button)v.findViewById(R.id.btnChooseFile2InNewEW3);
        spinCountryStep3 = (Spinner)v.findViewById(R.id.spinCountryInNewEW3);
        spinCurrencyStep3 = (Spinner)v.findViewById(R.id.spinCurrencyInNewEW3);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, MasterCache.country_name);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCountryStep3.setAdapter(dataAdapter);

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, MasterCache.currency_code);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCurrencyStep3.setAdapter(dataAdapter1);

        txtPurchaseOnStep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog("EW", "Purchased", txtPurchaseOnStep3);
            }
        });

        txtWarrStartDateStep3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog("EW", "Warranty", txtWarrStartDateStep3);
            }
        });

        btnChooseFile1Step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnChooseFile2Step2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void registerUser(){
        String url = NetUtils.HOST+ NetUtils.REGISTER;
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            int index= spinCountry.getSelectedItemPosition();
//
//            "first_name": "Kishore",
//                    "email": "ukis.kok@gmail.com",
//                    "telephone": "12345",
//                    "country_code": "SG",
//                    "state": "Singapore",
//                    "city": "Serangoon",
//                    "fb_id": "fbid123",
//                    "fb_token": "fbtoken1",
//                    "google_access_token": "g123",
//                    "google_id_token": "id_tkn",
//                    "google_token_type": "offline",
//                    "device_token": "kajshdkajsd",
//                    "device_type": "I",
//                    "pwd": "test1234"


            String countryCode = MasterCache.countryCode.get(index);
            jsonObject.put("first_name", edtFirstName.getText().toString());
            jsonObject.put("email", edtEmail.getText().toString());
            jsonObject.put("telephone", edtPhNo.getText().toString());
            jsonObject.put("country_code", countryCode);
            jsonObject.put("state", edtState.getText().toString());
            jsonObject.put("city", edtCity.getText().toString());
            jsonObject.put("device_token", "asdf");
            jsonObject.put("device_type", "A");
            jsonObject.put("pwd", "test3242");
            Log.i("myLog","Registeruser req:"+jsonObject.toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("myLog", "Success Response");
                    Log.i("myLog", "Success Response:"+response.toString());
                    //   MasterCache.saveEWListCache(response);
                    // CustomList listAdapter = new CustomList(getActivity(),MasterCache.srNo, MasterCache.srStatus, MasterCache.srCreatedOn, MasterCache.srConsumerName);
                    //listView.setAdapter(listAdapter);
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
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addManuWarranty(){
        //addManuWarranty data:{"warranty_number":"12345","warranty_period":"12","purchased_from":"vuguj","product_type":"DESKTOP","bill_no":"","warranty_start_date":1469019430306,"cost":"500","currency_code":"AFA","serial_no":"","model_name":"","brand_name":"DESKTOP","country":"AFGHANISTAN"}

        //addManuWarranty data:{"warranty_period":"12","purchased_from":"fhjkl","warranty_no":"34567","product_type":"DESKTOP","bill_no":"","warranty_start_date":1469019614453,"cost":"","currency_code":"AFA","serial_no":"","model_name":"","brand_name":"DESKTOP","country":"AFGHANISTAN"}
        String url = NetUtils.HOST+ NetUtils.SR_ADD_MANU;
        Log.i("myLog","url:"+url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            String warrStartDate = txtWarrStartDateStep2.getText().toString();
            jsonObject.put("product_type", txtProdTypeStep2.getText().toString());
            jsonObject.put("warranty_no", edtWarrNoStep2.getText().toString());
            jsonObject.put("brand_name", txtBrandStep2.getText().toString());
            jsonObject.put("currency_code", spinCurrencyStep2.getSelectedItem().toString());
            jsonObject.put("cost", edtProdCostStep2.getText().toString());
            jsonObject.put("warranty_start_date", String.valueOf(warrantyLong));
            jsonObject.put("purchased_on", String.valueOf(dateLong));
            jsonObject.put("purchased_from", edtPurchasedFromStep2.getText().toString());
            jsonObject.put("warranty_period", edtWarrMonthStep2.getText().toString());
            jsonObject.put("bill_no", "");
            jsonObject.put("country", spinCountryStep2.getSelectedItem().toString());
            jsonObject.put("model_name", txtModelStep2.getText().toString());
            jsonObject.put("serial_no", txtSerialNoStep2.getText().toString());
            Log.i("myLog","addManuWarranty data:"+jsonObject.toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("myLog", "addManuWarranty job Success Response");
                    //addManuWarranty Response:{"warranty":{"warranty_end_date":1500557818,"warranty_period":11,"warranty_no":"12345","product_type":"MOBILE","cost":"500","model_name":"m123456","brand_name":"MOBILE","serial_no":"123456","country":"AFGHANISTAN","title":"MOBILE m123456","purchased_on":1469021818,"warranty_in_months":12,"model_id":485743,"purchased_from":"ertyu","bill_no":"","currency_code":"AFA","warranty_start_date":1469021818,"warranty_id":243543}}
                    Log.i("myLog", "addManuWarranty Response:" + response.toString());
                    MasterCache.saveSRManuWarranty(response);
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
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void addExtWarranty(){
        String url = NetUtils.HOST+ NetUtils.SR_ADD_EW;
        Log.i("myLog","addExtWarranty url:"+url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
//            "mwarranty_id": "544", 11
//                    "purchase_date": "01/05/2015", 11
//                    "start_date": "01/05/2016", 11
//                    "purchase_from": "Ext Warranty provider", 11
//                    "price": "700011", 11
//                    "currency_code": "SGD",
//                    "additional_info": "ADDITIONAL REMARKS", 11
//                    "warranty_months": "24", 11
//                    "category": "60885",
//                    "invoice_no": "11111",11
//                    "warranty_country": "SG",11
//                    "ew_warranty_no": "1234567"11

            jsonObject.put("warranty_id", MasterCache.srManuWarrId.get(0).toString());//11
            //jsonObject.put("warranty_id", "87687589");//11
            jsonObject.put("product_type", txtProdTypeStep3.getText().toString());
            jsonObject.put("warranty_no", edtWarrNoStep3.getText().toString());
            jsonObject.put("brand_name", txtBrandStep3.getText().toString());
            jsonObject.put("currency_code", spinCurrencyStep3.getSelectedItem().toString());
            jsonObject.put("price", edtProdCostStep3.getText().toString());
            jsonObject.put("start_date", "01/05/2016");
            //jsonObject.put("start_date", String.valueOf(warrantyLong3));
            //jsonObject.put("purchase_date", String.valueOf(dateLong3));
            jsonObject.put("purchase_date", "01/05/2016");

            jsonObject.put("purchase_from", edtPurchasedFromStep3.getText().toString());
            jsonObject.put("warranty_months", edtWarrMonthStep3.getText().toString());
            jsonObject.put("invoice_no", edtInvoiceNoStep3);
            jsonObject.put("country", spinCountryStep3.getSelectedItem().toString());
            jsonObject.put("model_name", txtModelStep3.getText().toString());
            jsonObject.put("serial_no", txtSerialNoStep3.getText().toString());
            jsonObject.put("additional_info", edtAdditInfo3.getText().toString());
            jsonObject.put("category", edtAdditInfo3.getText().toString());
            jsonObject.put("warranty_country", spinCountryStep3.getSelectedItem().toString());
            jsonObject.put("ew_warranty_no", edtWarrNoStep3.getText().toString());



            Log.i("myLog","addExtWarranty data:"+jsonObject.toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("myLog", "addExtWarranty job Success Response");
                    //addManuWarranty Response:{"warranty":{"warranty_end_date":1500557818,"warranty_period":11,"warranty_no":"12345","product_type":"MOBILE","cost":"500","model_name":"m123456","brand_name":"MOBILE","serial_no":"123456","country":"AFGHANISTAN","title":"MOBILE m123456","purchased_on":1469021818,"warranty_in_months":12,"model_id":485743,"purchased_from":"ertyu","bill_no":"","currency_code":"AFA","warranty_start_date":1469021818,"warranty_id":243543}}
                    Log.i("myLog", "addExtWarranty Response:" + response.toString());
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
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Getbranddropdown() {
        String url = NetUtils.HOST+NetUtils.BRANDS;
        Log.i("myLog", "url Brands" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "Success Response of Brands" + response);
                MasterCache.saveBrands(response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something...
                Log.i("myLog", "loadEWDetails Error Response");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                //headers.put("Content-Type", "application/json");
                //headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }

    public void Getproductdropdown() {
        String url = NetUtils.HOST + NetUtils.PRODUCT_TYPE;
        Log.i("myLog", " getProductType url : " + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("myLog", "getProductType Response:" + response);
                //{"models":[{"product_id":0,"product_type":"DESKTOP","model_id":0},{"product_id":0,"product_type":"LAPTOP","model_id":0},{"product_id":0,"product_type":"MOBILE","model_id":0},{"product_id":0,"product_type":"TABLET","model_id":0}]}
                MasterCache.populateProductTypeCache(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("myLog","getProductType Error Response");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                // headers.put("Content-Type", "application/json");
                // headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);

    }


    public void Getcountrydropdown() {
        String url = NetUtils.HOST+NetUtils.COUNTRIES;
        Log.i("myLog", "url cat" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "ySuccess Response of countr" + response);
                MasterCache.saveCountriesCache(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something...
                Log.i("myLog", "loadEWDetails Error Response");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                final Map<String, String> headers = new HashMap<>();
                //headers.put("Content-Type", "application/json");
                //headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }


    public void showDatePickerDialog(final String warranty, final String option, final TextView txtView) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtView.setText((dayOfMonth + 1) + "/" + monthOfYear + "/" + year);
                        if(option.equalsIgnoreCase("Purchased")) {
                            if(warranty.equalsIgnoreCase("EW"))
                                dateLong3 = c.getTimeInMillis();
                            else
                                dateLong = c.getTimeInMillis();
                        } else {
                            if(warranty.equalsIgnoreCase("EW"))
                                warrantyLong3 = c.getTimeInMillis();
                            else
                                warrantyLong = c.getTimeInMillis();
                        }
                        Log.i("myLog","DateLong:"+dateLong+ "  warrantyLong:"+warrantyLong);
                    }
                }, year, month, day);
        dpd.show();

        Date newDate = c.getTime();
        if(option.equalsIgnoreCase("Purchased"))
            dpd.getDatePicker().setMaxDate(newDate.getTime());

    }


}
