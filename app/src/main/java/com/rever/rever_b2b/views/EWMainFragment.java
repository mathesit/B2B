package com.rever.rever_b2b.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
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
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matheswari on 6/8/2016.
 */

public class EWMainFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private TableLayout tblStockBal, tblFailure;
    private TextView txtCaseLogCount, txtServReqCount, txtPendQuoteCount;
    private TableRow.LayoutParams stockParam, failureParam, failureLineParam, stockLineParam;
    private RelativeLayout.LayoutParams alertParam;
    private int width, height;
    private LinearLayout linearGraph;
    private String wiid;
    private EditText addNemail, addNfname, addNmname, addNlname, addNmobile, addNofficephone, addNhomePhone,
            addNaddressLine1, addNaddressLine2, addNpassport, addNcity, addNstate,
            addNpostalcode, addNalterEmail, addNserialNo, addNmodel;
    private  TextView closeNewewpopup,Nextstep2,Nextstep3,closeNewewpopup2,closeNewewpopup3;

    private Spinner countrydropdown, branddropdown, productdropdown;
    private EditText edtSerialno,edtCountry,edtExtendedWCategory,edtExtendedWInvoice,edtExtendedWNo,edtExtendedWPdate,edtExtendedWPrice,edtExtendedWProvider,edtExtendedWStartDate,edtUPCCode,edtModel,edtProductType,edtVoidRefund,edtWarrantyExtendMonths;
    private TextView txtProductDetails,txtExWrDetails,txtCallLogs,txtClaimHis;
    private TextView addNewEw;
    private LinearLayout tab_bar;
    private ListView lv;
    private static final String vbrand_name = "brand_name";
    private static final String vproduct_type = "product_type";
    private static final String vserial_no = "serial_no";
    private static final String vconsumer_name = "consumer";
    private static  ProgressDialog progressDialog;
    private static Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_extended_warranty, container, false);
        initViews();
        GetEWListTask();
        //readAllServiceRequest();
        return rootView;
    }
    private void initViews(){
        txtProductDetails = (TextView) rootView.findViewById(R.id.tabProductDetails);
        txtExWrDetails = (TextView) rootView.findViewById(R.id.tabExtendedWr);
        txtCallLogs = (TextView) rootView.findViewById(R.id.tabCallLogs);
        txtClaimHis = (TextView) rootView.findViewById(R.id.tabClaimHistory);
        edtSerialno = (EditText) rootView.findViewById(R.id.edtSerialNo);
        lv =(ListView) rootView.findViewById(R.id.list);
        tab_bar=(LinearLayout) rootView.findViewById(R.id.tab_bar);
        addNewEw=(TextView) rootView.findViewById(R.id.addNewEw);
        txtProductDetails.setOnClickListener(this);
        txtExWrDetails.setOnClickListener(this);
        txtCallLogs.setOnClickListener(this);
        txtClaimHis.setOnClickListener(this);
        addNewEw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.ew_add_new);

                closeNewewpopup = (TextView) dialog.findViewById(R.id.closeNewewpopup);
                Nextstep2 = (TextView) dialog.findViewById(R.id.Nextstep2);

                addNemail =(EditText) dialog.findViewById(R.id.addNemail1);
                addNfname =(EditText) dialog.findViewById(R.id.addNfname);
                addNmname =(EditText) dialog.findViewById(R.id.addNmname);
                addNlname =(EditText) dialog.findViewById(R.id.addNlname);
                addNmobile =(EditText) dialog.findViewById(R.id.addNmobile);
                addNofficephone =(EditText) dialog.findViewById(R.id.addNofficephone);
                addNhomePhone =(EditText) dialog.findViewById(R.id.addNhomePhone);
                addNaddressLine1 =(EditText) dialog.findViewById(R.id.addNaddressLine1);
                addNaddressLine2 =(EditText) dialog.findViewById(R.id.addNaddressLine2);
                addNpassport =(EditText) dialog.findViewById(R.id.addNpassport);
                addNcity =(EditText) dialog.findViewById(R.id.addNcity);
                addNstate =(EditText) dialog.findViewById(R.id.addNstate);
                addNpostalcode =(EditText) dialog.findViewById(R.id.addNpostalcode);
                addNalterEmail =(EditText) dialog.findViewById(R.id.addNalterEmail);
                addNserialNo =(EditText) dialog.findViewById(R.id.addNserialNo);
                addNmodel =(EditText) dialog.findViewById(R.id.addNmodel);

                countrydropdown =(Spinner) dialog.findViewById(R.id.countrydropdown);
                branddropdown =(Spinner) dialog.findViewById(R.id.branddropdown);
                productdropdown =(Spinner) dialog.findViewById(R.id.productdropdown);

                Getcountrydropdown();
                Getbranddropdown();

                ((EditText)dialog.findViewById(R.id.addNemail1)).setOnEditorActionListener(
                        new EditText.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                        actionId == EditorInfo.IME_ACTION_DONE ||
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                                    if (!event.isShiftPressed()) {

                                        String data = addNemail.getText().toString();
                                        Log.i("", "Emailtyped" + data);
                                        GetAddNewEwUserEmail(data);


//                                    addNlname.setText(MasterCache.userFirstName1.get(userId));
//                                    addNmobile.setText(MasterCache.userFirstName1.get(userId));
//                                    addNofficephone.setText(MasterCache.userFirstName1.get(userId));
//                                    addNhomePhone.setText(MasterCache.userFirstName1.get(userId));
//                                    addNaddressLine1.setText(MasterCache.userFirstName1.get(userId));
//                                    addNaddressLine2.setText(MasterCache.userFirstName1.get(userId));
//                                    addNpassport.setText(MasterCache.userFirstName1.get(userId));
//                                    addNstate.setText(MasterCache.userFirstName1.get(userId));
//                                    addNpostalcode.setText(MasterCache.userFirstName1.get(userId));
//                                    addNalterEmail.setText(MasterCache.userFirstName1.get(userId));
//                                    addNserialNo.setText(MasterCache.userFirstName1.get(userId));
//                                    addNmodel.setText(MasterCache.userFirstName1.get(userId));

                                        return true;
                                    }
                                }
                                return false;
                            }
                        });

                countrydropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                      {
                          @Override
                          public void onItemSelected(AdapterView<?> arg0,
                                                     View arg1, int position, long arg3) {

                          }
                          @Override
                          public void onNothingSelected(AdapterView<?> arg0) {

                          }
                      }
                );
                branddropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> arg0,
                                                       View arg1, int position, long arg3) {

                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {

                            }
                        }
                );
                productdropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                      {
                          @Override
                          public void onItemSelected(AdapterView<?> arg0,
                                                     View arg1, int position, long arg3) {

                          }
                          @Override
                          public void onNothingSelected(AdapterView<?> arg0) {

                          }
                      }
                );


                closeNewewpopup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                    }

                });
                Nextstep2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog2 = new Dialog(getContext());
                        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog2.setContentView(R.layout.ew_add_new_step2);
                        closeNewewpopup2 = (TextView) dialog2.findViewById(R.id.closeAddNewPopup2);
                        Nextstep3 = (TextView) dialog2.findViewById(R.id.Nextstep3);

                        closeNewewpopup2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog2.dismiss();

                            }

                        });

                        Nextstep3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final Dialog dialog3 = new Dialog(getContext());
                                dialog3.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                dialog3.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                dialog3.setContentView(R.layout.ew_add_new_step3);
                                closeNewewpopup3 = (TextView) dialog3.findViewById(R.id.closeAddNewPopup2);

                                closeNewewpopup3.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        dialog3.dismiss();

                                    }

                                });
                                dialog3.show();
                            }

                        });
                        dialog2.show();
                    }

                });
                dialog.show();
            }});


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
    }


    public void GetAddNewEwUserEmail(String email) {
        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_ADD_NEW_EW_URL+email;
        Log.i("myLog", "url Add New EW" + url);
        progressDialog= ProgressDialog.show(getContext(), "Progress Dialog Title Text", "Process Description Text", true);

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
                    addNfname.setText(MasterCache.userFirstName1.get(0));
                    addNmname.setText(MasterCache.userMiddleName1.get(0));
                    addNlname.setText(MasterCache.userLastName1.get(0));
                    addNpostalcode.setText(MasterCache.userPostal1.get(0));
                    addNaddressLine2.setText(MasterCache.userAddLine2.get(0));
                    addNmobile.setText(MasterCache.userMobile1.get(0));
                    addNaddressLine1.setText(MasterCache.userAddLine1.get(0));
                    addNcity.setText(MasterCache.userCity1.get(0));
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
       // progressDialog= ProgressDialog.show(getContext(), "Progress Dialog Title Text", "Process Description Text", true);
        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_PRODUCT_DETAILS_URL+str;
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

        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_CLAIM_HISTORY_URL+str;
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


        EWProductFragment newFragment = new EWProductFragment();
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

        EWDetailsFragment newFragment = new EWDetailsFragment();
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


        EWCallLogsFragment newFragment = new EWCallLogsFragment();
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


        EWClaimHisFragment newFragment = new EWClaimHisFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.linearFragmentInEWMain, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

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
                    ListAdapter adapter = new SimpleAdapter(getContext().getApplicationContext(),
                            MasterCache.jo, R.layout.list_item, new String[]
                            {vbrand_name, vproduct_type, vserial_no, vconsumer_name},
                            new int[] {R.id.model_name, R.id.product_type, R.id.serial_no, R.id.consumer_name });
                    lv.setAdapter(adapter);
                    lv.invalidateViews();

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


                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.spinner_item, MasterCache.country_name);
                countrydropdown.setAdapter(adapter);

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
                branddropdown.setAdapter(adapter);

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
        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_CALL_CAT_URL;
        Log.i("myLog", "url cat" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "Success Response of call cat" + response);
                MasterCache.saveCallCat(response);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.spinner_item, MasterCache.cat_desc);
                productdropdown.setAdapter(adapter);

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



}
