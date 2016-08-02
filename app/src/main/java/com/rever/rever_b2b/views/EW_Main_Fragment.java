package com.rever.rever_b2b.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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
import com.rever.rever_b2b.model.EwTabReports;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Matheswari on 6/8/2016.
 */

public class EW_Main_Fragment extends Fragment implements View.OnClickListener,AdapterView.OnItemSelectedListener, TextView.OnEditorActionListener {

    private View rootView;
    private TableLayout tblStockBal, tblFailure;

    private TableRow.LayoutParams stockParam, failureParam, failureLineParam, stockLineParam;
    private RelativeLayout.LayoutParams alertParam;
    private int width, height;
    private LinearLayout linearGraph;
    private String wiid;
    private EditText addNemail, addNfname, addNmname, addNlname, addNmobile, addNofficephone, addNhomePhone,
            addNaddressLine1, addNaddressLine2, addNpassport, addNcity, addNstate,
            addNpostalcode, addNalterEmail, addNserialNo, addNmodel,addN2brand,addN2prodType,addN2Serial,addN2model,editFilter;
    private  TextView closeNewewpopup,Nextstep2,Nextstep3,closeNewewpopup2,closeNewewpopup3,saveEW;

    private Spinner countrydropdown, branddropdown, productdropdown,searchSpin,addN2selectcountry,addN2curreny;
    private EditText edtSerialno,edtCountry,edtExtendedWCategory,edtExtendedWInvoice,edtExtendedWNo,
            edtExtendedWPdate,edtExtendedWPrice,edtExtendedWProvider,edtExtendedWStartDate,
            edtUPCCode,edtProductType,edtVoidRefund,edtWarrantyExtendMonths,  addN2prodCost,
            addN2warrMonths, addN2warrNumber, addN2Invoice, addN2additionalInfo;
    private TextView txtProductDetails,txtExWrDetails,txtCallLogs,txtClaimHis,addNewEw,DescriptionContent,addN2purchasedOn,addN2warrSdate,edtPurchasedOnStep3;

    private TextView txtCancelAddNew, txtNextAddNew,txtBrandStep2, txtProdTypeStep2, txtSerialNoStep2, txtModelStep2,
            txtPurchaseOnStep2, txtWarrStartDateStep2, txtBrandStep3, txtProdTypeStep3, txtSerialNoStep3, txtModelStep3,
            txtPurchaseOnStep3, txtWarrStartDateStep3,  txtBrandStep4, txtProdTypeStep4, txtSerialNoStep4, txtModelStep4;

    private EditText edtSearch, edtRemarks, edtProdStatus, edtFirstName, edtLastName, edtEmail, edtPhNo, edtAddressLine1, edtAddressLine2,edtmobile, edtCity,
            edtState, edtPostalCode, edtSerialNo, edtModel, edtProdCostStep2, edtPurchasedFromStep2 , edtBillNoStep2, edtPurchasedOnStep2,
            edtWarrMonthStep2,  edtWarrNoStep2, edtInvoiceNoStep2, edtAdditInfo2, edtProdCostStep3, edtPurchasedFromStep3 ,
            edtWarrMonthStep3,  edtWarrNoStep3, edtInvoiceNoStep3, edtAdditInfo3, edtRemarks4, edtFailDesc4;

    private Spinner spinSelect, spinCountry, spinJobStatus,spinSalutation, spinTechnician, spinReportedSymptom, spinReportedCondn, spinProdType,
            spinBrand, spinCountryStep2, spinCurrencyStep2, spinCountryStep3, spinCurrencyStep3;
    private ScrollView scrollStep1AddNew, scrollStep2AddNew, scrollStep3AddNew, scrollStep4AddNew;

    private Button btnChooseFile1Step2, btnChooseFile2Step2, btnChooseFile1Step3, btnChooseFile2Step3;



    private ImageView imgStepsAddNew;
    private LinearLayout tab_bar;
    private ListView lv,lv2;
    private static final String vbrand_name = "brand_name";
    private static final String vproduct_type = "product_type";
    private static final String vserial_no = "serial_no";
    private static final String vconsumer_name = "consumer";
    private static  ProgressDialog progressDialog;
    private static Dialog dialog,dialog2,dialog3;
    private String[] search = { "Search by..","Brand", "Model", "Serial No.", "Consumer Email", "Consumer Name", "Product Type", "Warranty Number" };
    private String spinneritem,newEwSelectedBrand,newEwSelectedprod,newEwSelectedserial,newEwSelectedModel;
    private ListAdapter adapter;
    private EW_Main_Fragment.CustomListforEwShowAll customAdapter;
    private long dateLong, warrantyLong, dateLong3, warrantyLong3;
    private AlertDialog.Builder alertDialog;
    private boolean userExists =false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_extended_warranty, container, false);
        initViews();
        //GetEWListTask();

        //readAllServiceRequest();
        return rootView;
    }
    private void initViews(){
        txtProductDetails = (TextView) rootView.findViewById(R.id.tabProductDetails);
        txtExWrDetails = (TextView) rootView.findViewById(R.id.tabExtendedWr);
        txtCallLogs = (TextView) rootView.findViewById(R.id.tabCallLogs);
        txtClaimHis = (TextView) rootView.findViewById(R.id.tabClaimHistory);
        editFilter = (EditText) rootView.findViewById(R.id.edtFilterInEW);
        edtSerialno = (EditText) rootView.findViewById(R.id.edtSerialNo);
        lv =(ListView) rootView.findViewById(R.id.list);
        searchSpin = (Spinner) rootView.findViewById(R.id.edtSearchInServReq);
        tab_bar=(LinearLayout) rootView.findViewById(R.id.tab_bar);
        addNewEw=(TextView) rootView.findViewById(R.id.addNewEw);
        txtProductDetails.setOnClickListener(this);
        txtExWrDetails.setOnClickListener(this);
        txtCallLogs.setOnClickListener(this);
        txtClaimHis.setOnClickListener(this);
        Getcountrydropdown();
        editFilter.setOnEditorActionListener(this);
        addNewEw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                addNewServiceRequest();
                //openAddNewEw1();

            }});


        ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, search);
        searchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpin.setAdapter(searchAdapter);
        searchSpin.setOnItemSelectedListener(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("myLog", "position:" + position);
                wiid = MasterCache.warrantyId.get(position);

                Log.i("myLog", "wid" + wiid);
                MasterCache.listPosition_id = wiid;
                GetEwproductTask(wiid);
                GetewDetailsTask(wiid);
                GetEwClaimHistoryTask(wiid);
                GetCallLogsTask(wiid);
            }
        });


        customAdapter = new EW_Main_Fragment.CustomListforEwShowAll(getActivity(),
                MasterCache.vbrandname, MasterCache.vprodtype, MasterCache.vserialno, MasterCache.vconsumername);
        lv.setAdapter(customAdapter);

        customAdapter.notifyDataSetChanged();

        lv.performItemClick(lv.getAdapter().getView(0, null, null),
                0, lv.getAdapter().getItemId(0));

        editFilter.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    searchService(String.valueOf(spinneritem));

                    return true;
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
                Log.i("myLog", "loadServReqDetails Error Response");
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

    public void AddNewEwUserTask(String data,String newuser) {

        Log.i("myLog", "AddnewEWuser data:" + data);
        Log.i("myLog", "AddnewEWuser newuser:" + newuser);
        String url = NetUtils.HOST+newuser;
        Log.i("myLog", "AddnewEWuser url:" + url);

        //declare other objects as per your need
        progressDialog= ProgressDialog.show(getContext(), "Progressing", "Validating Email Id", true);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        try {
            JSONObject jsonObject = new JSONObject(data);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // do something...
                    Log.i("myLog", "Success_Response_for_new_user_edit" + response);


                    progressDialog.dismiss();
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

    public void GetewDetailsTask(String str) {
        //  progressDialog= ProgressDialog.show(getContext(), "Progress Dialog Title Text", "Process Description Text", true);
        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_DETAILS_URL+str;

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        Log.i("myLog", "DetailsResponse" + response.toString());
                        MasterCache.saveEWDetailsTab(response);
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // do something...
                        Log.i("myLog", "loadServReqDetails Error Response");
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
        Volley.newRequestQueue(getActivity()).add(jsonRequest);
    }

    public void GetEwproductTask(String str) {
        progressDialog= ProgressDialog.show(getContext(), "Content ", "Loading", true);
        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_PRODUCT_DETAILS_URL + str;
        Log.i("myLog", "producturl : " + url);
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        Log.i("myLog", "ProdResponse" + response.toString());

                        MasterCache.saveEWProductDetailsTab(response);
                        loadProductDetails();
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // do something...
                        Log.i("myLog", "Load Product Details Error Response");
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
        Volley.newRequestQueue(getActivity()).add(jsonRequest);
    }

    public void GetEwClaimHistoryTask(String str) {
        // progressDialog= ProgressDialog.show(getContext(), "Progress Dialog Title Text", "Process Description Text", true);

        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_CLAIM_HISTORY_URL + str;
        Log.i("myLog", "warr_id : " + url);
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        Log.i("myLog", "Response" + response.toString());
                        MasterCache.saveEWClaimHistoryCache(response);
                        progressDialog.dismiss();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // do something...
                        Log.i("myLog", "loadServReqDetails Error Response");
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
        Volley.newRequestQueue(getActivity()).add(jsonRequest);
    }

    public void GetCallLogsTask(String str) {
        //  progressDialog= ProgressDialog.show(getContext(), "Progress Dialog Title Text", "Process Description Text", true);

        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_CALL_LOGS_URL+str;
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "Success Response of call logs" + response);
                MasterCache.saveEWCallLogsCache(response);
                // if(MasterCache.cl_case_status == )
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something...
                Log.i("myLog", "Error Response"+error);
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


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tabProductDetails:
                loadProductDetails();
                break;
            case R.id.tabExtendedWr:
                loadExtendedWR();
                break;
            case R.id.tabCallLogs:
                loadCallLogs();
                break;
            case R.id.tabClaimHistory:
                loadClaimHis();
                break;
            default:
        }
    }

    public void GetEWListTask() {
        // HTTP POST
        String url = NetUtils.HOST + NetUtils.EXTENDED_WARRANTY_URL;
        Log.i("myLog", "Listview url:" + url);

        //declare other objects as per your need
        progressDialog= ProgressDialog.show(getContext(), "Progress Dialog Title Text", "Process Description Text", true);

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
//                    adapter = new SimpleAdapter(getContext().getApplicationContext(),
//                            MasterCache.jo, R.layout.list_item, new String[]
//                            {vbrand_name, vproduct_type, vserial_no, vconsumer_name},
//                            new int[] {R.id.model_name, R.id.product_type, R.id.serial_no, R.id.consumer_name });
//                    lv.setAdapter(adapter);
//                    lv.invalidateViews();

                    customAdapter = new EW_Main_Fragment.CustomListforEwShowAll(getActivity(),
                            MasterCache.vbrandname, MasterCache.vprodtype, MasterCache.vserialno, MasterCache.vconsumername);
                    lv.setAdapter(customAdapter);
                    customAdapter.notifyDataSetChanged();

                    lv.performItemClick(lv.getAdapter().getView(0, null, null),
                            0, lv.getAdapter().getItemId(0));
                    progressDialog.dismiss();
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
                Log.i("myLog", "loadServReqDetails Error Response");
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.spinner_item, MasterCache.brand_name);
                spinBrand.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something...
                Log.i("myLog", "loadServReqDetails Error Response");
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
                ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, MasterCache.prodTypeNames);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinProdType.setAdapter(adp);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        searchSpin.setSelection(position);
        String item = parent.getItemAtPosition(position).toString();
        editFilter.setHint(item);
        spinneritem = item;
        Log.i("MyLog", "SpinnerItem" + spinneritem);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        return false;
    }

    public class CustomListforEwShowAll extends ArrayAdapter<String> {
        private List<String> vbrand_name, vproduct_type, vserial_no, vconsumer_name;
        private Activity context;



        public CustomListforEwShowAll(Activity context, List<String> vbrand_name, List<String> vproduct_type, List<String> vserial_no, List<String> vconsumer_name) {
            super(context, R.layout.list_item, vbrand_name);
            this.context = context;
            this.vbrand_name = vbrand_name;
            this.vproduct_type = vproduct_type;
            this.vserial_no = vserial_no;
            this.vconsumer_name = vconsumer_name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.list_item, null, true);
            TextView txtbrand_name = (TextView) listViewItem.findViewById(R.id.model_name);
            TextView txtproduct_type = (TextView) listViewItem.findViewById(R.id.product_type);
            TextView txtserial_no = (TextView) listViewItem.findViewById(R.id.serial_no);
            TextView txtconsumer_name = (TextView) listViewItem.findViewById(R.id.consumer_name);


            txtbrand_name.setText(vbrand_name.get(position));
            txtproduct_type.setText(vproduct_type.get(position));
            txtserial_no.setText(vserial_no.get(position));
            txtconsumer_name.setText(vconsumer_name.get(position));
            return listViewItem;
        }
    }

    public void searchService(String search){

        String url = NetUtils.HOST + NetUtils.EW_PRODUCT_SEARCH;

        Log.i("myLog", "Search_url:" + url);

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            switch (search) {

                //     { "Brand", "Model", "Serial No.", "Consumer Email", "Consumer Name", "Product Type", "Warranty Number" }

                case "Brand":
                    jsonObject.put("brand", editFilter.getText().toString());
                    Log.i("Brand:::", editFilter.getText().toString());
                    break;
                case "Model":
                    jsonObject.put("model_name", editFilter.getText().toString());
                    Log.i("Modelll:::", editFilter.getText().toString());
                    break;
                case "Serial No.":
                    jsonObject.put("serial_no", editFilter.getText().toString());
                    Log.i("Serial No.:::", editFilter.getText().toString());
                    break;
                case "Consumer Email":
                    jsonObject.put("email_id", editFilter.getText().toString());
                    Log.i("Consumer Email:::", editFilter.getText().toString());

                    break;
                case "Consumer Name":
                    jsonObject.put("consumer_name", editFilter.getText().toString());
                    Log.i("Consumer Name:::", editFilter.getText().toString());

                    break;
                case "Product Type":
                    jsonObject.put("product_type", editFilter.getText().toString());
                    Log.i("Product Type:::", editFilter.getText().toString());
                    break;
                case "Warranty Number":
                    jsonObject.put("warranty_no", editFilter.getText().toString());
                    Log.i("Warranty no:::", editFilter.getText().toString());
                    break;
                default:
                    Snackbar.make(getView(), "Please Select Field to search", Snackbar.LENGTH_SHORT).show();
                    break;

            }
            Log.i("myLog", "jsonObject string:" + jsonObject.toString());


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("myLog", "Success Response:" + response.toString());
                    MasterCache.saveEWDetailsCache(response);
                    customAdapter.notifyDataSetChanged();
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

    public void loadProductDetails(){

        tab_bar.setBackgroundResource(R.drawable.bordercolor_blue);

        txtProductDetails.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorwhite));
        txtProductDetails.setBackgroundResource(R.color.blue_txt);

        txtExWrDetails.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtExWrDetails.setBackgroundResource(0);

        txtCallLogs.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtCallLogs.setBackgroundResource(0);

        txtClaimHis.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtClaimHis.setBackgroundResource(0);


        EW_Product_Fragment newFragment = new EW_Product_Fragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.linearFragmentInEWMain, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void loadExtendedWR () {

        tab_bar.setBackgroundResource(R.drawable.bordercolor_blue);

        txtProductDetails.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtProductDetails.setBackgroundResource(0);

        txtExWrDetails.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorwhite));
        txtExWrDetails.setBackgroundResource(R.color.blue_txt);

        txtCallLogs.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtCallLogs.setBackgroundResource(0);

        txtClaimHis.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtClaimHis.setBackgroundResource(0);

        EW_Details_Fragment newFragment = new EW_Details_Fragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.linearFragmentInEWMain, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void loadCallLogs() {

        tab_bar.setBackgroundResource(R.drawable.bordercolor_blue);

        txtProductDetails.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtProductDetails.setBackgroundResource(0);

        txtExWrDetails.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtExWrDetails.setBackgroundResource(0);

        txtCallLogs.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorwhite));
        txtCallLogs.setBackgroundResource(R.color.blue_txt);

        txtClaimHis.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtClaimHis.setBackgroundResource(0);


        EW_CallLogs_Fragment newFragment = new EW_CallLogs_Fragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.linearFragmentInEWMain, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
    public void loadClaimHis() {

        tab_bar.setBackgroundResource(R.drawable.bordercolor_blue);

        txtProductDetails.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtProductDetails.setBackgroundResource(0);

        txtExWrDetails.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtExWrDetails.setBackgroundResource(0);

        txtCallLogs.setTextColor(ContextCompat.getColor(getActivity(), R.color.blue_txt));
        txtCallLogs.setBackgroundResource(0);

        txtClaimHis.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorwhite));
        txtClaimHis.setBackgroundResource(R.color.blue_txt);


        EW_ClaimHis_Fragment newFragment = new EW_ClaimHis_Fragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.linearFragmentInEWMain, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    public void addNewServiceRequest(){
        alertDialog = new  AlertDialog.Builder(getActivity());
        View convertView = getActivity().getLayoutInflater().inflate(R.layout.new_ew, null);
        alertDialog.setView(convertView);
        dialog = alertDialog.create();
        dialog.show();
        initNewSRViews(convertView);
        initServReqViews1(convertView);
        initServReq2Views(convertView);
        initServReq3Views(convertView);
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



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, MasterCache.country_name);
        spinCountry.setAdapter(adapter);

    }

    public void initNewSRViews(View v){
        scrollStep1AddNew = (ScrollView)v.findViewById(R.id.scrollStep1InNewService);
        scrollStep2AddNew = (ScrollView)v.findViewById(R.id.scrollStep2InNewService);
        scrollStep3AddNew = (ScrollView)v.findViewById(R.id.scrollStep3InNewService);

        txtCancelAddNew = (TextView)v.findViewById(R.id.txtCancelInNewServReq1);
        txtNextAddNew = (TextView)v.findViewById(R.id.txtNextStepInNewServReq1);
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


                }
            }
        });

    }
    public void initServReqViews1(View v){
        edtFirstName = (EditText)v.findViewById(R.id.edtFirstNameInNewServReq1);
        edtLastName = (EditText)v.findViewById(R.id.edtLastNameInNewServReq1);
        edtEmail = (EditText)v.findViewById(R.id.edtEmailInNewServReq1);
        edtPhNo = (EditText)v.findViewById(R.id.edtHomePhoneInNewServReq1);
        edtmobile = (EditText)v.findViewById(R.id.edtMobileInNewServReq1);
        edtAddressLine1 =(EditText)v.findViewById(R.id.edtAddress1InNewServReq1);
        edtAddressLine2 =(EditText)v.findViewById(R.id.edtAddrLine2InNewServReq1);
        edtCity =(EditText)v.findViewById(R.id.edtCityInNewServReq1);
        edtState =(EditText)v.findViewById(R.id.edtStateInNewServReq1);
        edtPostalCode =(EditText)v.findViewById(R.id.edtPostalCodeInNewServReq1);
        edtSerialNo =(EditText)v.findViewById(R.id.edtSerialNoInNewServReq1);
        edtModel = (EditText)v.findViewById(R.id.edtModelInNewServReq1);
        spinCountry =(Spinner)v.findViewById(R.id.spinSelCountryInNewServReq1);
        spinSalutation = (Spinner)v.findViewById(R.id.spinSalutationInNewServReq1);
        spinProdType = (Spinner)v.findViewById(R.id.spinnerProdTypeInNewServReq1);
        spinBrand = (Spinner)v.findViewById(R.id.spinBrandInNewServReq1);

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
        Getbranddropdown();
        Getproductdropdown();
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

    public void initServReq2Views(View v){
        txtBrandStep2 = (TextView)v.findViewById(R.id.txtBrandValInServReq2);
        txtProdTypeStep2 = (TextView)v.findViewById(R.id.txtProdTypeValInServReq2);
        txtSerialNoStep2 = (TextView)v.findViewById(R.id.txtSerialNoValInServReq2);
        txtModelStep2 = (TextView)v.findViewById(R.id.txtModelValInServReq2);
        txtPurchaseOnStep2 = (TextView)v.findViewById(R.id.txtPurchasedOnInNewServReq2);
        edtProdCostStep2 = (EditText)v.findViewById(R.id.edtProdCostInNewServReq2);
        edtPurchasedOnStep2 = (EditText)v.findViewById(R.id.edtPurchasedOnInNewServReq2);
        edtWarrMonthStep2 = (EditText)v.findViewById(R.id.edtWarrMonthInNewServReq2);
        edtPurchasedFromStep2 = (EditText)v.findViewById(R.id.edtPurchaseFromInNewServReq2);

        txtWarrStartDateStep2 = (TextView)v.findViewById(R.id.txtWarrStartDateInNewServReq2);
        edtWarrNoStep2 = (EditText)v.findViewById(R.id.edtWarrNoInNewServReq2);
        edtInvoiceNoStep2 = (EditText)v.findViewById(R.id.edtInvoiceNoInNewServReq2);
        edtAdditInfo2 = (EditText)v.findViewById(R.id.edtAdditionalInfoInNewServReq2);
        btnChooseFile1Step2 = (Button)v.findViewById(R.id.btnChooseFile1InNewServReq2);
        btnChooseFile2Step2 = (Button)v.findViewById(R.id.btnChooseFile2InNewServReq2);
        spinCountryStep2 = (Spinner)v.findViewById(R.id.spinCountryInNewServReq2);
        spinCurrencyStep2 = (Spinner)v.findViewById(R.id.spinCurrencyInNewServReq2);


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

    public void initServReq3Views(View v){
        txtBrandStep3 = (TextView)v.findViewById(R.id.txtBrandValInNewServReq3);
        txtProdTypeStep3 = (TextView)v.findViewById(R.id.txtProdTypeValInNewServReq3);
        txtSerialNoStep3 = (TextView)v.findViewById(R.id.txtSerialNoValInNewServReq3);
        txtModelStep3 = (TextView)v.findViewById(R.id.txtModelValInNewServReq3);
        txtPurchaseOnStep3 = (TextView)v.findViewById(R.id.txtPurchasedOnInNewServReq3);
        edtProdCostStep3 = (EditText)v.findViewById(R.id.edtProdCostInNewServReq3);
        edtPurchasedOnStep3 = (TextView)v.findViewById(R.id.txtPurchasedOnInNewServReq3);
        edtWarrMonthStep3 = (EditText)v.findViewById(R.id.edtWarrMonthInNewServReq3);
        edtPurchasedFromStep3 = (EditText)v.findViewById(R.id.edtPurchaseFromInNewServReq3);

        txtWarrStartDateStep3 = (TextView)v.findViewById(R.id.txtWarrStartDateInNewServReq3);
        edtWarrNoStep3 = (EditText)v.findViewById(R.id.edtWarrNoInNewServReq3);
        edtInvoiceNoStep3 = (EditText)v.findViewById(R.id.edtInvoiceNoInNewServReq3);
        edtAdditInfo3 = (EditText)v.findViewById(R.id.edtAdditionalInfoInNewServReq3);
        btnChooseFile1Step3 = (Button)v.findViewById(R.id.btnChooseFile1InNewServReq3);
        btnChooseFile2Step2 = (Button)v.findViewById(R.id.btnChooseFile2InNewServReq3);
        spinCountryStep3 = (Spinner)v.findViewById(R.id.spinCountryInNewServReq3);
        spinCurrencyStep3 = (Spinner)v.findViewById(R.id.spinCurrencyInNewServReq3);

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
                showDatePickerDialog("EW","Purchased", txtPurchaseOnStep3);
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

    public void getUserInfo(String email){
        String userInfoUrl = String.format(NetUtils.USER_INFO, email);
        String url = NetUtils.HOST + userInfoUrl;
        Log.i("myLog", " getUserInfo url : " + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("myLog", "getUserInfo Response:" + response);
                //{"errors":[{"message":"User does not exist!","code":107}]}
                MasterCache.AddNewEwEmailResponseCache(response);
//                if(MasterCache.userId1.size()>0)
//                    loadUserInfo();
                //getUserInfo Response:{"user":{"company_id":"127435","user_mobile_info_id":37,"user_type":"Service Centers","state":"Singapore (general)","country_code":"SG","city":"SINGAPORE","session_token":"4D4K374N","first_name":"VINSON","address_line1":"109 North Bridge Road","mobile_phone":"65-81818218","address_line2":" #06-00 Funan DigitaLife Mall","email":"challenger_service@yarraa.com","postal_code":"179097","last_name":"LEE","user_id":2107935}}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("myLog","getUserInfo Error Response");
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

    public void getProductType(){
        String url = NetUtils.HOST + NetUtils.PRODUCT_TYPE;
        Log.i("myLog", " getProductType url : " + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("myLog", "getProductType Response:" + response);
                //{"models":[{"product_id":0,"product_type":"DESKTOP","model_id":0},{"product_id":0,"product_type":"LAPTOP","model_id":0},{"product_id":0,"product_type":"MOBILE","model_id":0},{"product_id":0,"product_type":"TABLET","model_id":0}]}
                MasterCache.populateProductTypeCache(response.toString());
                ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, MasterCache.prodTypeNames);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinProdType.setAdapter(adp);
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

    public void getBrandByType(){
        String servReqUrl = String.format(NetUtils.BRANDS, "MOBILE");
        String url = NetUtils.HOST + servReqUrl;
        Log.i("myLog", " getBrand url : " + servReqUrl);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("myLog", "getBrand Response:" + response);
                //MasterCache.saveBrands(response.toString());
                ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, MasterCache.brand_name);
                adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinBrand.setAdapter(adp);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("myLog","getBrand Error Response");
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

    public void loadUserInfo(){
        userExists = true;
        edtPhNo.setText(MasterCache.userMobile1.get(0));
        edtState.setText(MasterCache.userState1.get(0));
        edtCity.setText(MasterCache.userCity1.get(0));
        edtAddressLine1.setText(MasterCache.userAddLine1.get(0));
        edtAddressLine2.setText(MasterCache.userAddLine2.get(0));
        edtPostalCode.setText(MasterCache.userPostal1.get(0));
        edtFirstName.setText(MasterCache.userFirstName1.get(0));
        edtLastName.setText(MasterCache.userLastName1.get(0));
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
                    //   MasterCache.saveServiceRequestListCache(response);
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

}
