package com.rever.rever_b2b.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.rever.rever_b2b.utils.JsonUtils;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oviya on 6/8/2016.
 */
public class QuotationExtended extends Fragment implements View.OnClickListener  {
    private View rootView;
    private LinearLayout linearDetails;

    private TableLayout tblMarkUp,tblCost, tblHistory, tblViewHistory,tblMarkDetails;
   // private TableLayout.LayoutParams tabParams;
    TableRow.LayoutParams markupParams, costParams, viewParams, markupTitleParams, costTitleParams, costLineParams,
           markupLineParams, historyParams, historyTitleParams, historyLineParams, viewHistoryParams, viewHistoryTitle,
           viewHistoryline,tblMarkInStep,tblMarkTitle,tblMarkLine;

    private ImageView viewSearch;
    private EditText editSearch;
    private Spinner spinner;
    int width, height;
    private AlertDialog.Builder alertDialog;
    private Dialog dialog;
    RelativeLayout relParent;
    CustomList listAdapter;
    private int textSize;
    private TextView txtQuotId, txtQuotStatus, txtCreatedOn, txtCreatedBy, txtBrand, txtSerialNo, txtModel, txtProdType, txtEmail,
            txtCustomer,txtClaim, txtProcess,txtApprove,txtQuote,txtTotalClaim, txtTotalCharge,txtGstTotal, txtGstCharge,
            txtGrandTotal,txtGrandCharge,txtReject, consumer, email, sNo, model, brand, product, warrantyNo, warrantyMonths, purchase, start,
            expire, provider, amountClaim, maxClaim, availClaim;

    private String quot_id = null;
    private ListView listSearch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_quotation, container, false);
        initViews(rootView);
        initParams();
        showAllQuotation("");
        showQuotationViews();
        return rootView;

    }

    public void initViews(View v) {
        viewSearch = (ImageView) v.findViewById(R.id.imgSearchInQuotation);
        spinner = (Spinner) v.findViewById(R.id.spinInQuotation);
        txtClaim = (TextView)rootView.findViewById(R.id.txtViewClaim);
        editSearch = (EditText)v.findViewById(R.id.searchInQuotation);
        listSearch = (ListView) v.findViewById(R.id.listInQuotation);
        ArrayList<String> al = new ArrayList<>();
        al.add("Quotation No."); al.add("SR Number"); al.add("Brand"); al.add("Model"); al.add("Serial No.");
        al.add("Consumer email ID"); al.add("IC/Passport No.");
        ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, al );
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);
        editSearch.setHint("Search");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editSearch.setHint(parent.getSelectedItem().toString());
                editSearch.setText("");
                //  imgSearch.setImageResource(R.drawable.search_service_center);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editSearch.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String text = editSearch.getText().toString();
                    String hint = editSearch.getHint().toString();
                    Log.i("myLog", "Hint:" + hint + "  text:" + text);

                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    String key =null;
                    if(hint.equalsIgnoreCase("Quotation No."))
                        key = "quo_no";
                    else if(hint.equalsIgnoreCase("SR Number"))
                        key = "sr_no";
                    else if(hint.equalsIgnoreCase("Brand"))
                        key = "brand";
                    else if(hint.equalsIgnoreCase("Model"))
                        key = "model";
                    else if(hint.equalsIgnoreCase("Serial No."))
                        key = "serial_no";
                    else if(hint.equalsIgnoreCase("Status"))
                        key = "status";
                    else if(hint.equalsIgnoreCase("Consumer email id"))
                        key = "email_id";
                    else if(hint.equalsIgnoreCase("IC/Passport No"))
                        key = "ic";
                    if(key == null)
                        key = hint;
                    searchQuotation(key, text);
                    return true;
                }
                return false;
            }
        });
        viewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editSearch.setText("");
                editSearch.setHint("Search");
                showAllQuotation("Close");
            }
        });
        listSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                quot_id = MasterCache.quotId.get(position);
                Log.i("myLog", "sr_id" + quot_id);
                showQuotationViews();
                loadQuotationDetails(quot_id);

            }
        });

        txtClaim.setOnClickListener(this);
    }


    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

    private void initParams(){
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels;
        width = metrics.widthPixels;
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            Toast.makeText(getActivity(), "Xlarge screen", Toast.LENGTH_LONG).show();
            int swidth = ((width - dpToPx(195))*3)/4;
            markupParams = new TableRow.LayoutParams(swidth/4,dpToPx(45));
            costParams = new TableRow.LayoutParams(swidth/5,dpToPx(45));
            costLineParams = new TableRow.LayoutParams(swidth,dpToPx(1));
            costLineParams.span=5;
            markupLineParams = new TableRow.LayoutParams(swidth,dpToPx(1));
            markupLineParams.span=4;
            markupTitleParams = new TableRow.LayoutParams(swidth,dpToPx(2));
            markupTitleParams.span=4;
            costTitleParams = new TableRow.LayoutParams(swidth,dpToPx(2));
            costTitleParams.span=5;
            viewParams = new TableRow.LayoutParams(dpToPx(1),dpToPx(45));

            historyParams = new TableRow.LayoutParams(swidth/6,dpToPx(45));
            historyLineParams = new TableRow.LayoutParams(swidth,dpToPx(1));
            historyLineParams.span= 6;
            historyTitleParams = new TableRow.LayoutParams(swidth,dpToPx(2));
            historyTitleParams.span= 6;


        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(getActivity(), "Large screen", Toast.LENGTH_LONG).show();
            int swidth = ((width - dpToPx(180))*3)/4;
            Log.i("myLog","Swidth:"+swidth);
            markupParams = new TableRow.LayoutParams(swidth/4,dpToPx(45));
            costParams = new TableRow.LayoutParams(swidth/5,dpToPx(45));
            costLineParams = new TableRow.LayoutParams(swidth,dpToPx(1));
            costLineParams.span=9;
            markupLineParams = new TableRow.LayoutParams(swidth,dpToPx(1));
            markupLineParams.span=7;
            markupTitleParams = new TableRow.LayoutParams(swidth,dpToPx(2));
            markupTitleParams.span=7;
            costTitleParams = new TableRow.LayoutParams(swidth,dpToPx(2));
            costTitleParams.span=9;
            viewParams = new TableRow.LayoutParams(dpToPx(1),dpToPx(50));

            historyParams = new TableRow.LayoutParams(swidth/6,dpToPx(50));
            historyLineParams = new TableRow.LayoutParams(swidth ,dpToPx(1));
            historyLineParams.span= 15;
            textSize=14;

            historyTitleParams = new TableRow.LayoutParams(swidth, dpToPx(2));
            historyTitleParams.span= 15;

           /* viewHistoryParams = new TableRow.LayoutParams(swidth/5,dpToPx(45));
            viewHistoryline = new TableRow.LayoutParams(swidth,dpToPx(1));
            viewHistoryline.span= 11;
            viewHistoryTitle = new TableRow.LayoutParams(swidth, dpToPx(2));
            viewHistoryTitle.span = 11;*/

        } else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(getActivity(), "Normal sized screen" , Toast.LENGTH_LONG).show();
            int swidth = ((width - dpToPx(120))*3)/4;
            markupParams = new TableRow.LayoutParams(swidth/4,dpToPx(45));
            costParams = new TableRow.LayoutParams(swidth/5,dpToPx(45));
            costLineParams = new TableRow.LayoutParams(swidth,dpToPx(1));
            costLineParams.span=9;
            markupLineParams = new TableRow.LayoutParams(swidth,dpToPx(1));
            markupLineParams.span=7;
            markupTitleParams = new TableRow.LayoutParams(swidth,dpToPx(2));
            markupTitleParams.span=7;
            costTitleParams = new TableRow.LayoutParams(swidth,dpToPx(2));
            costTitleParams.span=9;
            viewParams = new TableRow.LayoutParams(dpToPx(1),dpToPx(45));

            historyParams = new TableRow.LayoutParams(swidth/6,dpToPx(45));
            historyLineParams = new TableRow.LayoutParams(swidth,dpToPx(1));
            historyLineParams.span= 11;
            historyTitleParams = new TableRow.LayoutParams(swidth,dpToPx(2));
            historyTitleParams.span= 11;
        }
    }
    public void searchQuotation(String key, String value) {
        Log.i("myLog", "searchInQuotes");
        String url = NetUtils.HOST + NetUtils.EW_SEARCH_QUOTE;
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page_no", 1);
            jsonObject.put("page_count", 5);
            jsonObject.put(key,value);
            Log.i("myLog", "Search json request data:" + jsonObject.toString());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("myLog", "Search Success Response");
                    Log.i("myLog", "Success Response:" + response.toString());
                    MasterCache.saveQuotationList(response);
                    // listAdapter = new CustomList(getActivity(), MasterCache.quoteSrNo, MasterCache.quoteStatus, MasterCache.quoteCreatedOn, MasterCache.quoteCompanyId);
                    listAdapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("myLog", "Search Error Response");
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
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showAllQuotation(final String option) {
        String url = NetUtils.HOST + NetUtils.EW_QUOTATION_lIST;
        Log.i("myLog", "Post url:" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page_no", "1");
            jsonObject.put("count", "5");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // do something...
                    Log.i("myLog", "Success Response" + response);
                    MasterCache.saveQuotationList(response);
        if(option.equalsIgnoreCase("Close")){
        listAdapter.notifyDataSetChanged();
        }else {

            listAdapter = new CustomList(getActivity(), MasterCache.quotId,  MasterCache.srNo, MasterCache.createdOn, MasterCache.quotStatus);
            listSearch.setAdapter(listAdapter);
            listSearch.performItemClick(listSearch.getAdapter().getView(0, null, null),
                    0, listSearch.getAdapter().getItemId(0));
        }
        }

/*        QuotationExtended.CustomList customAdapter = new QuotationExtended.CustomList(getActivity(), MasterCache.quotId, MasterCache.srNo, MasterCache.createdOn, MasterCache.quotStatus);
                    listView.setAdapter(customAdapter);
                    listView.performItemClick(listView.getAdapter().getView(0, null, null),
                            0, listView.getAdapter().getItemId(0));


                }*/
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error){
        Log.i("myLog","Error Response");
        NetworkResponse networkResponse=error.networkResponse;
        if(networkResponse!=null){
        Log.e("Volley","Error. HTTP Status Code:"+networkResponse.statusCode);
        }
        }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                //    headers.put("Content-Type", "application/json");
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



    public void showQuotationViews() {
        txtQuotId = (TextView) rootView.findViewById(R.id.quotId);
        txtQuotStatus = (TextView) rootView.findViewById(R.id.quotStatus);
        txtCreatedOn = (TextView) rootView.findViewById(R.id.quotCreatedOn);
        txtCreatedBy = (TextView) rootView.findViewById(R.id.quotCreatedBy);
        txtBrand = (TextView) rootView.findViewById(R.id.quotBrand);
        txtSerialNo = (TextView) rootView.findViewById(R.id.quotSerialNo);
        txtModel = (TextView) rootView.findViewById(R.id.quotModel);
        txtProdType = (TextView) rootView.findViewById(R.id.quotProdType);
        txtEmail = (TextView) rootView.findViewById(R.id.quotEmail);
        txtCustomer = (TextView) rootView.findViewById(R.id.quotCustomer);

       /* txtServiceDescription = (TextView) v.findViewById(R.id.quotService);
        txtServiceAmt = (TextView) v.findViewById(R.id.quotAmount);
        txtServiceSrp = (TextView) v.findViewById(R.id.quotSrpRate);
        txtServiceMark = (TextView) v.findViewById(R.id.quotMarkedUp);
        txtOnsiteDescription = (TextView) v.findViewById(R.id.quotOnsite);
        txtOnsiteAmt = (TextView) v.findViewById(R.id.quotOnsiteAmt);
        txtOnsiteSrp = (TextView) v.findViewById(R.id.quotOnsiteSrp);
        txtOnsiteMark = (TextView) v.findViewById(R.id.quotOnsiteMarkup);
        txtServiceItem = (TextView) v.findViewById(R.id.quotItemNo);
        txtServiceUpc = (TextView) v.findViewById(R.id.quotUpc);
        txtServiceDesc = (TextView) v.findViewById(R.id.quotDescription);
        txtServiceClaim = (TextView) v.findViewById(R.id.quotClaimable);
        txtServiceCharge = (TextView) v.findViewById(R.id.quotChargeable);
        txtOnsiteItem = (TextView) v.findViewById(R.id.quotOnsiteItem);
        txtOnsiteUpc = (TextView) v.findViewById(R.id.quotOnsiteUpc);
        txtOnsiteDesc = (TextView) v.findViewById(R.id.quotOnsiteDesc);
        txtOnsiteClaim = (TextView) v.findViewById(R.id.quotOnsiteClaimable);
        txtOnsiteCharge = (TextView) v.findViewById(R.id.quotOnsiteChargeable);
        txtGstClaim = (TextView) v.findViewById(R.id.quotGstClaim);
        txtGstCharge = (TextView) v.findViewById(R.id.quotGstCharge);
        txtGrandClaim = (TextView) v.findViewById(R.id.quotGrandClaim);
        txtGrandCharge = (TextView) v.findViewById(R.id.quotGrandCharge);
        txtRemarks = (TextView) v.findViewById(R.id.quotRemarks);
        txtReceivedFrom = (TextView) v.findViewById(R.id.quotReceivedFrom);
        txtReceivedOn = (TextView) v.findViewById(R.id.quotReceivedOn);
        txtSentTo = (TextView) v.findViewById(R.id.quotSentTo);
        txtUpdatedBy = (TextView) v.findViewById(R.id.quotUpdatedBy);
        txtUpdatedOn = (TextView) v.findViewById(R.id.quotUpdatedOn);
        txtStatusHistory = (TextView) v.findViewById(R.id.quotStatusHistory);*/
        txtTotalClaim = (TextView) rootView.findViewById(R.id.quotClaimTotal);
        txtTotalCharge = (TextView) rootView.findViewById(R.id.quotChargeTotal);
        txtGstTotal = (TextView)rootView.findViewById(R.id.quotClaimTotalGst);
        txtGstCharge =(TextView)rootView.findViewById(R.id.quotChargeTotalGst);
        txtGrandTotal = (TextView) rootView.findViewById(R.id.quotGrandTotal);
        txtGrandCharge =(TextView)rootView.findViewById(R.id.quotGrandCharge);
        tblCost = (TableLayout)rootView.findViewById(R.id.tblCostDetails);
        tblMarkUp = (TableLayout)rootView.findViewById(R.id.tblMarkUpDetails);
        tblHistory = (TableLayout)rootView.findViewById(R.id.tblHistoryDetails);
        txtProcess = (TextView)rootView.findViewById(R.id.txtProcessQuotation);
        txtReject = (TextView)rootView.findViewById(R.id.txtRejectQuotation);
        txtApprove = (TextView)rootView.findViewById(R.id.txtApproveQuotation);
        txtQuote = (TextView)rootView.findViewById(R.id.txtReQuote);

        txtProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProcessDialog();
            }
        });

        txtApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approveQuotation();
            }
        });

        txtReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rejectQuotation();
            }
        });

    }

    public void rejectQuotation(){
        String url = NetUtils.HOST + NetUtils.EW_REJECT_QUOTE;
        Log.i("myLog", "Post url:" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("quotation_id", quot_id);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // do something...
                    Log.i("myLog", "Success Response" + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.i("myLog","Error Response");
                    NetworkResponse networkResponse=error.networkResponse;
                    if(networkResponse!=null){
                        Log.e("Volley","Error. HTTP Status Code:"+networkResponse.statusCode);
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    //    headers.put("Content-Type", "application/json");
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


    private void showProcessDialog(){
        alertDialog = new  AlertDialog.Builder(getActivity());
        View convertView = getActivity().getLayoutInflater().inflate(R.layout.show_process_dialog, null);
        alertDialog.setView(convertView);
        relParent = (RelativeLayout)convertView.findViewById(R.id.relProcessQuotation);
        dialog = alertDialog.create();
        dialog.show();
        addFirstStep();

    }

    public void addFirstStep(){
        relParent.removeAllViews();
        LayoutInflater inflater = dialog.getLayoutInflater();
        View layout = inflater.inflate(R.layout.quotation_step_one, relParent);
        Button btnCancel = (Button)layout.findViewById(R.id.btnCancel);
        Button btnNext = (Button)layout.findViewById(R.id.btnNext);
        tblMarkDetails = (TableLayout)layout.findViewById(R.id.tblMarkInStepOne);
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(getActivity(), "Large screen", Toast.LENGTH_LONG).show();
            int swidth = ((width - dpToPx(180)) * 3) / 4;
            Log.i("myLog", "Swidth:" + swidth);

            tblMarkInStep = new TableRow.LayoutParams(swidth/5, dpToPx(45));
            tblMarkLine = new TableRow.LayoutParams(swidth, dpToPx(1));
            tblMarkLine.span = 12;
            tblMarkTitle = new TableRow.LayoutParams(swidth, dpToPx(2));
            tblMarkTitle.span = 12;
        }

        showMarkTable();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSecondStep();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    public void addSecondStep(){
        relParent.removeAllViews();
        LayoutInflater inflater = dialog.getLayoutInflater();
        View layout = inflater.inflate(R.layout.quotation_step_two, relParent);
        Button btnCancel = (Button)layout.findViewById(R.id.btnCancelTwo);
        Button btnNext = (Button)layout.findViewById(R.id.btnNextTwo);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addThirdStep();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFirstStep();
            }
        });
    }

    public void addThirdStep(){
        relParent.removeAllViews();
        LayoutInflater inflater = dialog.getLayoutInflater();
        View layout = inflater.inflate(R.layout.quotation_step_three, relParent);
        Button btnCancel = (Button)layout.findViewById(R.id.btnCancelThree);
        Button btnNext = (Button)layout.findViewById(R.id.btnNextThree);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStepFour();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSecondStep();
            }
        });
    }

    public void addStepFour(){
        relParent.removeAllViews();
        LayoutInflater inflater = dialog.getLayoutInflater();
        View layout = inflater.inflate(R.layout.quotation_step_four, relParent);
        Button btnCancel = (Button)layout.findViewById(R.id.btnCancelFour);
        Button btnNext = (Button)layout.findViewById(R.id.btnNextFour);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                processQuotation();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addThirdStep();
            }
        });
    }

    public void processQuotation(){
        String url = NetUtils.HOST + NetUtils.EW_PROCESS_QUOTE;
        Log.i("myLog", "Post url:" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("quotation_id", quot_id);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // do something...
                    Log.i("myLog", "Success Response" + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error){
                    Log.i("myLog","Error Response");
                    NetworkResponse networkResponse=error.networkResponse;
                    if(networkResponse!=null){
                        Log.e("Volley","Error. HTTP Status Code:"+networkResponse.statusCode);
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    //    headers.put("Content-Type", "application/json");
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

    @Override
    public void onClick(View v) {
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.view_customer_claims);
//                dialog.setTitle("New Case");
        TextView txtClose = (TextView)dialog.findViewById(R.id.txtViewClose);
        consumer = (TextView) dialog.findViewById(R.id.txtCustName);
        email = (TextView) dialog.findViewById(R.id.txtEmailId);
        sNo = (TextView) dialog.findViewById(R.id.txtViewSNo);
        model = (TextView) dialog.findViewById(R.id.txtViewModel);
        brand = (TextView) dialog.findViewById(R.id.txtViewBrand);
        product = (TextView) dialog.findViewById(R.id.txtViewProduct);
        warrantyNo = (TextView) dialog.findViewById(R.id.txtWrtyNo);
        warrantyMonths = (TextView) dialog.findViewById(R.id.txtWrtyMonths);
        purchase = (TextView) dialog.findViewById(R.id.txtWrtyPurchase);
        start = (TextView) dialog.findViewById(R.id.txtWrtyStart);
        expire = (TextView) dialog.findViewById(R.id.txtWrtyExpire);
        provider = (TextView) dialog.findViewById(R.id.txtProvider);
        amountClaim = (TextView ) dialog.findViewById(R.id.txtAmtClaimed);
        maxClaim = (TextView) dialog.findViewById(R.id.txtMaxClaim);
        availClaim = (TextView)dialog.findViewById(R.id.txtAvailClaim);
        tblViewHistory = (TableLayout) dialog.findViewById(R.id.tblViewHistory);
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(getActivity(), "Large screen", Toast.LENGTH_LONG).show();
            int swidth = ((width - dpToPx(180)) * 3) / 4;
            Log.i("myLog", "Swidth:" + swidth);

            viewHistoryParams = new TableRow.LayoutParams(swidth/4, dpToPx(50));
            viewHistoryline = new TableRow.LayoutParams(swidth, dpToPx(1));
            viewHistoryline.span = 13;
            viewHistoryTitle = new TableRow.LayoutParams(swidth, dpToPx(2));
            viewHistoryTitle.span = 13;
        }

        viewClaimBalance(quot_id);

        txtClose.setOnClickListener(new View.OnClickListener()

                                    {
                                        @Override
                                        public void onClick(View v) {

                                            dialog.dismiss();
                                        }
                                    }

        );
        dialog.show();

    }

    public void approveQuotation() {
            String url = NetUtils.HOST + NetUtils.EW_APPROVE_QUOTE;
            Log.i("myLog", "Post url:" + url);
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("quotation_id", quot_id);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do something...
                        Log.i("myLog", "Success Response" + response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.i("myLog","Error Response");
                        NetworkResponse networkResponse=error.networkResponse;
                        if(networkResponse!=null){
                            Log.e("Volley","Error. HTTP Status Code:"+networkResponse.statusCode);
                        }
                    }
                }) {
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        final Map<String, String> headers = new HashMap<>();
                        //    headers.put("Content-Type", "application/json");
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


    public void viewClaimBalance(final String quot_id){
            String viewClaimUrl = String.format(NetUtils.EW_VIEW_CLAIM, quot_id);
            String url = NetUtils.HOST + viewClaimUrl;
            Log.i("myLog", " viewClaimDetails url : " + url);
        Log.i("QUOT:", quot_id);


            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // the response is already constructed as a JSONObject!
                            Log.i("myLog", "loadViewClaimDetails Success Response");
                            MasterCache.saveViewClaim(response);
                            Log.i("RESPONSEEE:", response.toString());
                            loadClaimInfo();
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
                   // headers.put("Content-Type", "application/json");
                    //headers.put("Accept", "application/json");
                    headers.put("Authorization", ReverApplication.getSessionToken());
                    return headers;
                }
            };
            requestQueue.add(jsonRequest);

        }

    public class CustomList extends ArrayAdapter<String> {
        private List<String> quotId, srNo, createdOn, status;
        private Activity context;

        public CustomList(Activity context, List<String> quotId, List<String> srNo, List<String> status, List<String> createdOn) {
            super(context, R.layout.quotation_list, quotId);
            this.context = context;
            this.quotId = quotId;
            this.srNo = srNo;
            this.status = status;
            this.createdOn = createdOn;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.quotation_list, null, true);
            TextView txtQuotId = (TextView) listViewItem.findViewById(R.id.quotationId);
            TextView txtStatus = (TextView) listViewItem.findViewById(R.id.quotationStatus);
            TextView txtCreatedOn = (TextView) listViewItem.findViewById(R.id.quotCreated);
            TextView txtSrNo = (TextView) listViewItem.findViewById(R.id.quotSrNo);
            // txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.alert_small, 0, 0, 0);
            //txtCreatedOn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.calendar_small, 0, 0, 0);
            //txtConsumer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_f, 0, 0, 0);
            txtQuotId.setText(quotId.get(position));
            txtSrNo.setText(srNo.get(position));
            txtStatus.setText(status.get(position));
            txtCreatedOn.setText(createdOn.get(position));
            return listViewItem;
        }
    }


    public void loadQuotationDetails(String quot_id) {
        String quotationReqUrl = String.format(NetUtils.EW_QUOTATION_DETAILS, quot_id);
        String url = NetUtils.HOST + quotationReqUrl;
        Log.i("myLog", " quotationReqDetails url : " + url);


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        Log.i("myLog", "loadQuotationReqDetails Success Response");
                        MasterCache.saveQuotationDetails(response);
                        Log.i("RESPONSEEE:", response.toString());
                        loadQuotRequest();
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
             //   headers.put("Content-Type", "application/json");
               // headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);

    }

    public void loadGstDetails() {
        String quotationReqUrl = String.format(NetUtils.EW_GST);
        String url = NetUtils.HOST + quotationReqUrl;
        Log.i("myLog", " loadGstDetails url : " + url);


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        Log.i("myLog", "loadGst Success Response");
                        MasterCache.saveGstDetails(response);
                        //  txtGstTotal.setText(MasterCache.gstPercent.get(0));

                        Log.i("RESPONSEEE:", response.toString());
                        //loadQuotRequest();
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
                //   headers.put("Content-Type", "application/json");
                // headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);

    }

    private void loadQuotRequest() {
        txtQuotId.setText(MasterCache.quot_id.get(0));
        txtQuotStatus.setText(MasterCache.quotationStatus.get(0));
        txtBrand.setText(MasterCache.quotBrand.get(0));
        txtModel.setText(MasterCache.quotModel.get(0));
        txtSerialNo.setText(MasterCache.quotSerialNo.get(0));
        txtProdType.setText(MasterCache.quotProductType.get(0));
        txtEmail.setText(MasterCache.quotEmail.get(0));
        txtCustomer.setText(MasterCache.quotConsumer.get(0));
        txtCreatedOn.setText(MasterCache.quotCreate.get(0));
        txtCreatedBy.setText(MasterCache.quotCreatedBy.get(0));
        txtTotalClaim.setText(MasterCache.quotTotal.get(0));
        txtTotalCharge.setText(MasterCache.quotChargeTotal.get(0));
        Log.i("CLAIM:", txtTotalCharge.getText().toString());

        loadGstDetails();

        double amount = Integer.parseInt(txtTotalClaim.getText().toString());
        double percentage = (7 * amount/ 100);
        txtGstTotal.setText(String.valueOf(percentage));
        txtGstCharge.setText(txtTotalCharge.getText().toString());

        int a= Integer.parseInt(txtTotalClaim.getText().toString());
        double b = Double.parseDouble(txtGstTotal.getText().toString());
        double c = a + b ;
        txtGrandTotal.setText(String.valueOf(c));

        int d = Integer.parseInt(txtTotalCharge.getText().toString());
        int e =Integer.parseInt(txtGstCharge.getText().toString());
        int f = d + e ;
        txtGrandCharge.setText(String.valueOf(f));
        displayMarkup();
        displayCost();
        displayHistory();
    }


    private void displayMarkup() {
        Log.i("myLog", "displayMrkUPDetails");
        int size = MasterCache.quotService.size();

        Log.i("myLog", "Sizeeeee:" + size);
            if(size>0){
                tblMarkUp.removeAllViews();
                TableRow trTitle = new TableRow(getActivity());
                TextView txtItem = new TextView(getActivity());
                txtItem.setText("Item Description");
                txtItem.setTextColor(Color.BLACK);
                txtItem.setGravity(Gravity.CENTER);
                trTitle.addView(txtItem, markupParams);
                View v1 = new View(getActivity());
                v1.setBackgroundColor(Color.GRAY);
                trTitle.addView(v1, viewParams);

                TextView txtAmount = new TextView(getActivity());
                txtAmount.setText("Amount");
                txtAmount.setTextColor(Color.BLACK);
                txtAmount.setGravity(Gravity.CENTER);
                trTitle.addView(txtAmount, markupParams);
                View v2 = new View(getActivity());
                v2.setBackgroundColor(Color.GRAY);
                trTitle.addView(v2, viewParams);

                TextView txtSrp = new TextView(getActivity());
                txtSrp.setText("SRP Rate");
                txtSrp.setTextColor(Color.BLACK);
                txtSrp.setGravity(Gravity.CENTER);
                trTitle.addView(txtSrp, markupParams);
                View v3 = new View(getActivity());
                v3.setBackgroundColor(Color.GRAY);
                trTitle.addView(v3, viewParams);

                TextView txtMark = new TextView(getActivity());
                txtMark.setText("Marked up value");
                txtMark.setTextColor(Color.BLACK);
                txtMark.setGravity(Gravity.CENTER);
                trTitle.addView(txtMark, markupParams);

                TableRow trLine = new TableRow(getActivity());
                View v =new View(getActivity());
                v.setBackgroundColor(Color.BLACK);
                trLine.addView(v, markupTitleParams);
                tblMarkUp.addView(trTitle);
                tblMarkUp.addView(trLine);
            }

            for(int start = 0; start < size ; start++){
                TableRow tr = new TableRow(getActivity());
                TextView txtItemDesc = new TextView(getActivity());
                txtItemDesc.setText(MasterCache.quotService.get(start));
                txtItemDesc.setGravity(Gravity.CENTER);
                tr.addView(txtItemDesc, markupParams);
                View v1 = new View(getActivity());
                v1.setBackgroundColor(Color.GRAY);
                tr.addView(v1, viewParams);

                TextView txtAmt = new TextView(getActivity());
                txtAmt.setText(MasterCache.quotServAmt.get(start));
                txtAmt.setGravity(Gravity.CENTER);
                tr.addView(txtAmt, markupParams);
                View v2 = new View(getActivity());
                v2.setBackgroundColor(Color.GRAY);
                tr.addView(v2, viewParams);

                TextView txtSrpRate = new TextView(getActivity());
                txtSrpRate.setText(MasterCache.quotServSrp.get(start));
                txtSrpRate.setGravity(Gravity.CENTER);
                tr.addView(txtSrpRate, markupParams);
                View v3 = new View(getActivity());
                v3.setBackgroundColor(Color.GRAY);
                tr.addView(v3, viewParams);

                TextView txtMarkUp = new TextView(getActivity());
                txtMarkUp.setText(MasterCache.quotServMark.get(start));
                txtMarkUp.setGravity(Gravity.CENTER);
                tr.addView(txtMarkUp, markupParams);

                TableRow trLine = new TableRow(getActivity());
                View v =new View(getActivity());
                v.setBackgroundColor(Color.GRAY);
                trLine.addView(v, markupLineParams);
                tblMarkUp.addView(tr);
                tblMarkUp.addView(trLine);
            }

        }

    private void showMarkTable() {
        Log.i("myLog", "displayMrkUPDetails");
        int size = MasterCache.quotService.size();

        Log.i("myLog", "Sizeeeee:" + size);
        if(size>0){
            tblMarkDetails.removeAllViews();
            TableRow trTitle = new TableRow(getActivity());
            TextView txtItem = new TextView(getActivity());
            txtItem.setText("Item Description");
            txtItem.setTextColor(Color.BLACK);
            txtItem.setGravity(Gravity.CENTER);
            trTitle.addView(txtItem, tblMarkInStep);
            View v1 = new View(getActivity());
            v1.setBackgroundColor(Color.GRAY);
            trTitle.addView(v1, viewParams);

            TextView txtAmount = new TextView(getActivity());
            txtAmount.setText("Amount");
            txtAmount.setTextColor(Color.BLACK);
            txtAmount.setGravity(Gravity.CENTER);
            trTitle.addView(txtAmount, tblMarkInStep);
            View v2 = new View(getActivity());
            v2.setBackgroundColor(Color.GRAY);
            trTitle.addView(v2, viewParams);

            TextView txtSrp = new TextView(getActivity());
            txtSrp.setText("SRP Rate");
            txtSrp.setBackgroundResource(R.drawable.edittext_bg);
            txtSrp.setTextColor(Color.BLACK);
            txtSrp.setGravity(Gravity.CENTER);
            trTitle.addView(txtSrp, tblMarkInStep);
            View v3 = new View(getActivity());
            v3.setBackgroundColor(Color.GRAY);
            trTitle.addView(v3, viewParams);

            TextView txtMark = new TextView(getActivity());
            txtMark.setText("Marked up value");
            txtMark.setTextColor(Color.BLACK);
            txtMark.setGravity(Gravity.CENTER);
            trTitle.addView(txtMark, tblMarkInStep);

            TableRow trLine = new TableRow(getActivity());
            View v =new View(getActivity());
            v.setBackgroundColor(Color.BLACK);
            trLine.addView(v, tblMarkTitle);
            tblMarkDetails.addView(trTitle);
            tblMarkDetails.addView(trLine);
        }

        for(int start = 0; start < size ; start++){
            TableRow tr = new TableRow(getActivity());
            TextView txtItemDesc = new TextView(getActivity());
            txtItemDesc.setText(MasterCache.quotService.get(start));
            txtItemDesc.setGravity(Gravity.CENTER);
            tr.addView(txtItemDesc, tblMarkInStep);
            View v1 = new View(getActivity());
            v1.setBackgroundColor(Color.GRAY);
            tr.addView(v1, viewParams);

            TextView txtAmt = new TextView(getActivity());
            txtAmt.setText(MasterCache.quotServAmt.get(start));
            txtAmt.setGravity(Gravity.CENTER);
            tr.addView(txtAmt, tblMarkInStep);
            View v2 = new View(getActivity());
            v2.setBackgroundColor(Color.GRAY);
            tr.addView(v2, viewParams);

            EditText txtSrpRate = new EditText(getActivity());
            txtSrpRate.setHint(MasterCache.quotServSrp.get(start));
            txtSrpRate.setGravity(Gravity.CENTER);

            tr.addView(txtSrpRate, tblMarkInStep);
            View v3 = new View(getActivity());
            v3.setBackgroundColor(Color.GRAY);
            tr.addView(v3, viewParams);

            EditText txtMarkUp = new EditText(getActivity());
            txtMarkUp.setText(MasterCache.quotServMark.get(start));
            txtMarkUp.setGravity(Gravity.CENTER);
            tr.addView(txtMarkUp, tblMarkInStep);

            TableRow trLine = new TableRow(getActivity());
            View v =new View(getActivity());
            v.setBackgroundColor(Color.GRAY);
            trLine.addView(v, tblMarkLine);
            tblMarkDetails.addView(tr);
            tblMarkDetails.addView(trLine);
        }

    }

    public void loadClaimInfo(){
        brand.setText(MasterCache.viewBrand.get(0));
        model.setText(MasterCache.viewModel.get(0));
        product.setText(MasterCache.viewProduct.get(0));
        consumer.setText(MasterCache.viewConsumer.get(0));
        email.setText((MasterCache.viewEmail.get(0)));
        sNo.setText(MasterCache.viewSerial.get(0));
        warrantyNo.setText(MasterCache.viewWarranty.get(0));
        warrantyMonths.setText(MasterCache.viewMonths.get(0));
        purchase.setText(MasterCache.viewPurchase.get(0));
        start.setText(MasterCache.viewStart.get(0));
        expire.setText(MasterCache.viewEnd.get(0));
        provider.setText(MasterCache.viewProvider.get(0));
        amountClaim.setText(MasterCache.viewTotalClaim.get(0));
        maxClaim.setText(MasterCache.viewMaxClaim.get(0));
        availClaim.setText(MasterCache.viewclaimBalance.get(0));
        displayViewHistory();

    }
    private void displayViewHistory() {

            Log.i("myLog", "displayViewHistory");
        int size = MasterCache.viewQuotationId.size();

        Log.i("myLog", "Sizeeeee:" + size);
        if(size>0){
            tblViewHistory.removeAllViews();
            TableRow trTitle = new TableRow(getActivity());

            TextView txtId = new TextView(getActivity());
            txtId.setText("Quotation ID");
            txtId.setTextColor(Color.BLACK);
            txtId.setGravity(Gravity.CENTER);
            trTitle.addView(txtId, viewHistoryParams);
            View v1 = new View(getActivity());
            v1.setBackgroundColor(Color.GRAY);
            trTitle.addView(v1, viewParams);

            TextView txtSr = new TextView(getActivity());
            txtSr.setText("SR Number");
            txtSr.setTextColor(Color.BLACK);
            txtSr.setGravity(Gravity.CENTER);
            trTitle.addView(txtSr, viewHistoryParams);
            View v2 = new View(getActivity());
            v2.setBackgroundColor(Color.GRAY);
            trTitle.addView(v2, viewParams);

            TextView txtAmt = new TextView(getActivity());
            txtAmt.setText("Amount Approved");
            txtAmt.setTextColor(Color.BLACK);
            txtAmt.setGravity(Gravity.CENTER);
            trTitle.addView(txtAmt, viewHistoryParams);
            View v3 = new View(getActivity());
            v3.setBackgroundColor(Color.GRAY);
            trTitle.addView(v3, viewParams);

            TextView txtApp = new TextView(getActivity());
            txtApp.setText("Approved On");
            txtApp.setTextColor(Color.BLACK);
            txtApp.setGravity(Gravity.CENTER);
            trTitle.addView(txtApp, viewHistoryParams);
            View v4 = new View(getActivity());
            v4.setBackgroundColor(Color.GRAY);
            trTitle.addView(v4, viewParams);

            TextView txtAppBy = new TextView(getActivity());
            txtAppBy.setText("Approved By");
            txtAppBy.setTextColor(Color.BLACK);
            txtAppBy.setGravity(Gravity.CENTER);
            trTitle.addView(txtAppBy, viewHistoryParams);

            TableRow trLine = new TableRow(getActivity());
            View v =new View(getActivity());
            v.setBackgroundColor(Color.BLACK);
            trLine.addView(v, viewHistoryTitle);
            tblViewHistory.addView(trTitle);
            tblViewHistory.addView(trLine);
        }

        for(int start = 0; start < size ; start++){
            TableRow tr = new TableRow(getActivity());
            TextView txtQuotId = new TextView(getActivity());
            txtQuotId.setText(MasterCache.viewQuotationId.get(start));
            txtQuotId.setGravity(Gravity.CENTER);
            tr.addView(txtQuotId, viewHistoryParams);
            View v1 = new View(getActivity());
            v1.setBackgroundColor(Color.GRAY);
            tr.addView(v1, viewParams);

            TextView txtSR = new TextView(getActivity());
            txtSR.setText(MasterCache.viewSrNo.get(start));
            txtSR.setGravity(Gravity.CENTER);
            tr.addView(txtSR, viewHistoryParams);
            View v2 = new View(getActivity());
            v2.setBackgroundColor(Color.GRAY);
            tr.addView(v2, viewParams);

            TextView txtAmount = new TextView(getActivity());
            txtAmount.setText(MasterCache.viewApprovedAmt.get(start));
            txtAmount.setGravity(Gravity.CENTER);
            tr.addView(txtAmount, viewHistoryParams);
            View v3 = new View(getActivity());
            v3.setBackgroundColor(Color.GRAY);
            tr.addView(v3, viewParams);

            TextView txtAppOn = new TextView(getActivity());
            txtAppOn.setText(MasterCache.viewApprovedOn.get(start));
            txtAppOn.setGravity(Gravity.CENTER);
            tr.addView(txtAppOn, viewHistoryParams);
            View v4 = new View(getActivity());
            v4.setBackgroundColor(Color.GRAY);
            tr.addView(v4, viewParams);

            TextView txtProvider = new TextView(getActivity());
            txtProvider.setText(MasterCache.viewApprovedName.get(start));
            txtProvider.setGravity(Gravity.CENTER);
            tr.addView(txtProvider, viewHistoryParams);

            TableRow trLine = new TableRow(getActivity());
            View v =new View(getActivity());
            v.setBackgroundColor(Color.GRAY);
            trLine.addView(v, viewHistoryline);
            tblViewHistory.addView(tr);
            tblViewHistory.addView(trLine);
        }

    }

/*
            TextView txtItemDesc = new TextView(getActivity());
            txtItemDesc.setText(MasterCache.quotService.get(start));
            txtItemDesc.setGravity(Gravity.CENTER);
            txtItemDesc.setTextColor(Color.BLACK);
            txtItemDesc.setTextSize(textSize);
            tr.addView(txtItemDesc, markUpParam);
            tr.setBackgroundResource(R.drawable.row_border);

             Log.i("myLog", "Test Description" + MasterCache.quotService.get(start));

            TextView txtAmount = new TextView(getActivity());
            txtAmount.setText(MasterCache.quotServAmt.get(start));
            txtAmount.setGravity(Gravity.CENTER);
            txtAmount.setTextColor(Color.BLACK);
            txtAmount.setTextSize(textSize);
            tr.addView(txtAmount, markUpParam);
            tr.setBackgroundResource(R.drawable.row_border);
            Log.i("myLog", "amount:" + MasterCache.quotServAmt.get(start));

            TextView txtSrpRate = new TextView(getActivity());
            txtSrpRate.setText(MasterCache.quotServSrp.get(start));
            txtSrpRate.setGravity(Gravity.CENTER);
            txtSrpRate.setTextColor(Color.BLACK);
            txtSrpRate.setTextSize(textSize);
            tr.addView(txtSrpRate, markUpParam);
            tr.setBackgroundResource(R.drawable.row_border);
            Log.i("myLog", "Srp:" + MasterCache.quotServSrp.get(start));

            TextView txtMarkUp = new TextView(getActivity());
            txtMarkUp.setText(MasterCache.quotServMark.get(start));
            txtMarkUp.setGravity(Gravity.CENTER);
            txtMarkUp.setTextColor(Color.BLACK);
            txtMarkUp.setTextSize(textSize);
            tr.addView(txtMarkUp, markUpParam);
            tr.setBackgroundResource(R.drawable.row_border);
            Log.i("myLog", "markUp:" + MasterCache.quotServMark.get(start));

            Log.i("mylog", "tr ;" + tr.getChildCount());
            tblMarkUp.addView(tr);
            Log.i("myLog", "after add to tbl Size::::" + tblMarkUp.getChildCount());

        }*/

            // TextView txtStockBal= new TextView(getActivity());
            //Log.i("myLog","count:"+MasterCache.stockCount.get(start));
            //   txtStockBal.setText(MasterCache.stockCount.get(start));
            //txtStockBal.setGravity(Gravity.CENTER);
            // tr.addView(txtStockBal);

    private void displayCost() {
        int size = MasterCache.quotService.size();
        Log.i("myLog", "Sizeeeee:" + size);
        if (size > 0) {
            tblCost.removeAllViews();
            TableRow trTitle = new TableRow(getActivity());
            TextView txtCreatedBy = new TextView(getActivity());
            txtCreatedBy.setText("Item No");
            txtCreatedBy.setTextColor(Color.BLACK);
            txtCreatedBy.setGravity(Gravity.CENTER);
            trTitle.addView(txtCreatedBy, costParams);
            View v1 = new View(getActivity());
            v1.setBackgroundColor(Color.GRAY);
            trTitle.addView(v1, viewParams);
            TextView txtCreatedDate = new TextView(getActivity());
            txtCreatedDate.setText("UPC #");
            txtCreatedDate.setTextColor(Color.BLACK);
            txtCreatedDate.setGravity(Gravity.CENTER);
            trTitle.addView(txtCreatedDate, costParams);
            View v2 = new View(getActivity());
            v2.setBackgroundColor(Color.GRAY);
            trTitle.addView(v2, viewParams);

            TextView txtRepairNotes = new TextView(getActivity());
            txtRepairNotes.setText("Item Description");
            txtRepairNotes.setTextColor(Color.BLACK);
            txtRepairNotes.setGravity(Gravity.CENTER);
            trTitle.addView(txtRepairNotes, costParams);
            View v3 = new View(getActivity());
            v3.setBackgroundColor(Color.GRAY);
            trTitle.addView(v3, viewParams);
            TextView txtMarked = new TextView(getActivity());
            txtMarked.setText("Claimable");
            txtMarked.setTextColor(Color.BLACK);
            txtMarked.setGravity(Gravity.CENTER);
            trTitle.addView(txtMarked, costParams);

            View v4 = new View(getActivity());
            v4.setBackgroundColor(Color.GRAY);
            trTitle.addView(v4, viewParams);
            TextView txtChargable = new TextView(getActivity());
            txtChargable.setText("Chargeable");
            txtChargable.setTextColor(Color.BLACK);
            txtChargable.setGravity(Gravity.CENTER);
            trTitle.addView(txtChargable, costParams);


            TableRow trLine = new TableRow(getActivity());
            View v = new View(getActivity());
            v.setBackgroundColor(Color.BLACK);
            trLine.addView(v, costTitleParams);
            tblCost.addView(trTitle);
            tblCost.addView(trLine);
        }

        for (int start = 0; start < size; start++) {
            TableRow tr = new TableRow(getActivity());
            TextView txtCreatedBy = new TextView(getActivity());
            int no = start + 1;
            txtCreatedBy.setText(String.valueOf(no));
            txtCreatedBy.setGravity(Gravity.CENTER);
            tr.addView(txtCreatedBy, costParams);

            View v1 = new View(getActivity());
            v1.setBackgroundColor(Color.GRAY);
            tr.addView(v1, viewParams);
            TextView txtCreatedDate = new TextView(getActivity());
            txtCreatedDate.setText("");
            txtCreatedDate.setGravity(Gravity.CENTER);
            tr.addView(txtCreatedDate, costParams);

            View v2 = new View(getActivity());
            v2.setBackgroundColor(Color.GRAY);
            tr.addView(v2, viewParams);
            TextView txtRepairNotes = new TextView(getActivity());
            txtRepairNotes.setText(MasterCache.quotService.get(start));
            txtRepairNotes.setGravity(Gravity.CENTER);
            tr.addView(txtRepairNotes, costParams);

            View v3 = new View(getActivity());
            v3.setBackgroundColor(Color.GRAY);
            tr.addView(v3, viewParams);
            TextView txtMarked = new TextView(getActivity());
            txtMarked.setText(MasterCache.quotApproved.get(start));
            txtMarked.setGravity(Gravity.CENTER);
            tr.addView(txtMarked, costParams);


            View v4 = new View(getActivity());
            v4.setBackgroundColor(Color.GRAY);
            tr.addView(v4, viewParams);
            TextView txtChargeable = new TextView(getActivity());
            txtChargeable.setText(MasterCache.quotChargeable.get(start));
            txtChargeable.setGravity(Gravity.CENTER);
            tr.addView(txtChargeable, costParams);

            TableRow trLine = new TableRow(getActivity());
            View v = new View(getActivity());
            v.setBackgroundColor(Color.GRAY);
            trLine.addView(v, costLineParams);
            tblCost.addView(tr);
            tblCost.addView(trLine);


           // String charge = txtChargeable.getText().toString();
            //int chargeable = Integer.parseInt(charge);


        }
        }

        private void displayHistory(){
            int size = MasterCache.quotStatusHistory.size();
            Log.i("myLog","Sizeeeee:"+size);
            if(size>0){
                tblHistory.removeAllViews();
                TableRow trTitle = new TableRow(getActivity());
                TextView txtRecvFrom = new TextView(getActivity());
                txtRecvFrom.setText("Received From");
                txtRecvFrom.setTextColor(Color.BLACK);
                txtRecvFrom.setGravity(Gravity.CENTER);
                trTitle.addView(txtRecvFrom, historyParams);
                View v1 = new View(getActivity());
                v1.setBackgroundColor(Color.GRAY);
                trTitle.addView(v1, viewParams);

                TextView txtRecvOn = new TextView(getActivity());
                txtRecvOn.setText("Received On");
                txtRecvOn.setTextColor(Color.BLACK);
                txtRecvOn.setGravity(Gravity.CENTER);
                trTitle.addView(txtRecvOn, historyParams);
                View v2 = new View(getActivity());
                v2.setBackgroundColor(Color.GRAY);
                trTitle.addView(v2, viewParams);

                TextView txtSentTo = new TextView(getActivity());
                txtSentTo.setText("Sent To");
                txtSentTo.setTextColor(Color.BLACK);
                txtSentTo.setGravity(Gravity.CENTER);
                trTitle.addView(txtSentTo, historyParams);
                View v3 = new View(getActivity());
                v3.setBackgroundColor(Color.GRAY);
                trTitle.addView(v3, viewParams);

                TextView txtUpdatedOn = new TextView(getActivity());
                txtUpdatedOn.setText("Updated On");
                txtUpdatedOn.setTextColor(Color.BLACK);
                txtUpdatedOn.setGravity(Gravity.CENTER);
                trTitle.addView(txtUpdatedOn, historyParams);
                View v4 = new View(getActivity());
                v4.setBackgroundColor(Color.GRAY);
                trTitle.addView(v4, viewParams);
                TextView txtUpdatedBy = new TextView(getActivity());

                txtUpdatedBy.setText("Updated By");
                txtUpdatedBy.setTextColor(Color.BLACK);
                txtUpdatedBy.setGravity(Gravity.CENTER);
                trTitle.addView(txtUpdatedBy, historyParams);
                View v5 = new View(getActivity());
                v5.setBackgroundColor(Color.GRAY);
                trTitle.addView(v5, viewParams);
                TextView txtStatus = new TextView(getActivity());

                txtStatus.setText("Status");
                txtStatus.setTextColor(Color.BLACK);
                txtStatus.setGravity(Gravity.CENTER);
                trTitle.addView(txtStatus, historyParams);

                TableRow trLine = new TableRow(getActivity());
                View v =new View(getActivity());
                v.setBackgroundColor(Color.BLACK);
                trLine.addView(v, historyTitleParams);
                tblHistory.addView(trTitle);
                tblHistory.addView(trLine);
            }

            for(int start = 0; start < size ; start++){
                TableRow tr = new TableRow(getActivity());

                TextView txtReceivedFrom = new TextView(getActivity());
                txtReceivedFrom.setText(MasterCache.quotReceivedFrom.get(start));
                txtReceivedFrom.setGravity(Gravity.CENTER);
                txtReceivedFrom.setTextSize(textSize);
                tr.addView(txtReceivedFrom, historyParams);
                View v1 = new View(getActivity());
                v1.setBackgroundColor(Color.GRAY);
                tr.addView(v1, viewParams);

                TextView txtRecvOn = new TextView(getActivity());
                txtRecvOn.setText(MasterCache.quotCreatedOn.get(start));
                txtRecvOn.setGravity(Gravity.CENTER);
                txtRecvOn.setTextSize(textSize);
                tr.addView(txtRecvOn, historyParams);
                View v2 = new View(getActivity());
                v2.setBackgroundColor(Color.GRAY);
                tr.addView(v2, viewParams);

                TextView txtSentTo = new TextView(getActivity());
                txtSentTo.setText(MasterCache.quotSentTo.get(start));
                txtSentTo.setGravity(Gravity.CENTER);
                txtSentTo.setTextSize(textSize);
                tr.addView(txtSentTo, historyParams);
                View v3 = new View(getActivity());
                v3.setBackgroundColor(Color.GRAY);
                tr.addView(v3, viewParams);

                TextView txtUpdatedOn = new TextView(getActivity());
                txtUpdatedOn.setText(MasterCache.quotUpdatedOn.get(start));
                txtUpdatedOn.setGravity(Gravity.CENTER);
                txtUpdatedOn.setTextSize(textSize);
                tr.addView(txtUpdatedOn, historyParams);
                View v4 = new View(getActivity());
                v4.setBackgroundColor(Color.GRAY);
                tr.addView(v4, viewParams);

                TextView txtUpdatedBy = new TextView(getActivity());
                txtUpdatedBy.setText(MasterCache.quotUpdatedBy.get(start));
                txtUpdatedBy.setGravity(Gravity.CENTER);
                txtUpdatedBy.setTextSize(textSize);
                tr.addView(txtUpdatedBy, historyParams);
                View v5 = new View(getActivity());
                v5.setBackgroundColor(Color.GRAY);
                tr.addView(v5, viewParams);

                TextView txtStatus = new TextView(getActivity());
                txtStatus.setText(MasterCache.quotStatusHistory.get(start));
                txtStatus.setGravity(Gravity.CENTER);
                txtStatus.setTextSize(textSize);
                tr.addView(txtStatus, historyParams);

                TableRow trLine = new TableRow(getActivity());
                View v =new View(getActivity());
                v.setBackgroundColor(Color.GRAY);
                trLine.addView(v, historyLineParams);
                tblHistory.addView(tr);
                tblHistory.addView(trLine);
            }

        }
    }

/*    private void displayHistory() {
            Log.i("myLog", "displayHistoryDetails");
            int size1 = MasterCache.quotReceivedFrom.size();
            Log.i("myLog", "Size1:" + MasterCache.quotReceivedFrom.size());
            for (int index = 0; index < size1; index++) {
                Log.i("myLog", "displayHistory index:" + index);
                TableRow row = new TableRow(getActivity());
                row.removeAllViews();

                TextView txtReceivedFrom = new TextView(getActivity());
                txtReceivedFrom.setText(MasterCache.quotReceivedFrom.get(index));
                txtReceivedFrom.setGravity(Gravity.CENTER);
                txtReceivedFrom.setTextColor(Color.BLACK);
                txtReceivedFrom.setTextSize(textSize);
                row.addView(txtReceivedFrom, historyParam);
                row.setBackgroundResource(R.drawable.row_border);
                Log.i("myLog", "received" + MasterCache.quotReceivedFrom.get(index));

                TextView txtReceivedOn = new TextView(getActivity());
                txtReceivedOn.setText(MasterCache.quotCreatedOn.get(index));
                txtReceivedOn.setGravity(Gravity.CENTER);
                txtReceivedOn.setTextColor(Color.BLACK);
                txtReceivedOn.setTextSize(textSize);
                row.addView(txtReceivedOn, historyParam);
                row.setBackgroundResource(R.drawable.row_border);
                Log.i("myLog", "receivedOn:" + MasterCache.quotCreatedOn.get(index));

                TextView txtSentTo = new TextView(getActivity());
                txtSentTo.setText(MasterCache.quotSentTo.get(index));
                txtSentTo.setGravity(Gravity.CENTER);
                txtSentTo.setTextColor(Color.BLACK);
                txtSentTo.setTextSize(textSize);
                row.addView(txtSentTo, historyParam);
                row.setBackgroundResource(R.drawable.row_border);
                Log.i("myLog", "sentTo:" + MasterCache.quotSentTo.get(index));

                TextView txtUpdateOn = new TextView(getActivity());
                txtUpdateOn.setText(MasterCache.quotUpdatedOn.get(index));
                txtUpdateOn.setGravity(Gravity.CENTER);
                txtUpdateOn.setTextColor(Color.BLACK);
                txtUpdateOn.setTextSize(textSize);
                row.setBackgroundResource(R.drawable.row_border);
                row.addView(txtUpdateOn, historyParam);
                Log.i("myLog", "updateOn:" + MasterCache.quotUpdatedOn.get(index));

                TextView txtUpdateBy = new TextView(getActivity());
                txtUpdateBy.setText(MasterCache.quotUpdatedBy.get(index));
                txtUpdateBy.setGravity(Gravity.CENTER);
                txtUpdateBy.setTextColor(Color.BLACK);
                txtUpdateBy.setTextSize(textSize);
                row.addView(txtUpdateBy, historyParam);
                row.setBackgroundResource(R.drawable.row_border);
                Log.i("myLog", "updateBy:" + MasterCache.quotUpdatedBy.get(index));

                TextView txtStatus = new TextView(getActivity());
                txtStatus.setText(MasterCache.quotStatusHistory.get(index));
                txtStatus.setGravity(Gravity.CENTER);
                txtStatus.setTextColor(Color.BLACK);
                txtStatus.setTextSize(textSize);
                row.addView(txtStatus, historyParam);
                row.setBackgroundResource(R.drawable.row_border);
                Log.i("myLog", "updateOn:" + MasterCache.quotStatusHistory.get(index));

                tblHistory.addView(row);
                Log.i("myLog", "after add to tbl");
            }
      }
    }*/
      /*  txtServiceDescription.setText(MasterCache.quotService.get(0));
        txtServiceAmt.setText(MasterCache.quotServAmt.get(0));
        txtServiceSrp.setText(MasterCache.quotServSrp.get(0));
        txtServiceMark.setText(MasterCache.quotServMark.get(0));
        txtReceivedFrom.setText(MasterCache.quotReceivedFrom.get(0));
        txtReceivedOn.setText(MasterCache.quotCreatedOn.get(0));
        txtSentTo.setText(MasterCache.quotSentTo.get(0));
        txtUpdatedBy.setText(MasterCache.quotUpdatedBy.get(0));
        txtUpdatedOn.setText(MasterCache.quotUpdatedOn.get(0));
        txtStatusHistory.setText(MasterCache.quotStatusHistory.get(0));*/
      /*  txtOnsiteDescription.setText("");
        txtOnsiteAmt.setText("");
        txtOnsiteSrp.setText("");
        txtOnsiteMark.setText("");
       txtOnsiteDescription.setText(MasterCache.quotService.get(0));
        txtOnsiteAmt.setText(MasterCache.quotServAmt.get(0));
        txtOnsiteSrp .setText(MasterCache.quotServSrp.get(0));
        txtOnsiteMark.setText(MasterCache.quotServMark.get(0));

        txtServiceItem.setText(" ");
        txtServiceUpc.setText(" ");
        txtServiceDesc.setText(" ");
        txtServiceClaim .setText(" ");
        txtServiceCharge.setText(" ");
        txtOnsiteItem.setText(" ");
        txtOnsiteUpc.setText(" ");
        txtOnsiteDesc.setText(" ");
        txtOnsiteClaim.setText(" ");
        txtOnsiteCharge.setText(" ");
        txtTotalClaim.setText(" ");
        txtTotalCharge.setText(" ");
        txtGstClaim.setText(" ");
        txtGstCharge.setText(" ");
        txtGrandClaim.setText(" ");
        txtGrandCharge.setText(" ");
        txtRemarks.setText(" ");*/






/*  private void initParams(){
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        height = metrics.heightPixels;
        width = metrics.widthPixels;
        if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            Toast.makeText(getActivity(), "Xlarge screen", Toast.LENGTH_LONG).show();
            int swidth = (width *80)/100;
            markUpParam = new TableRow.LayoutParams(swidth/5,dpToPx(50));
            historyParam = new TableRow.LayoutParams((swidth/2)/5,dpToPx(50));
            textSize=20;
            leftMargin=10;
            topMargin=2;
            rightMargin=10;
            bottomMargin=2;
            tabParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);


        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(getActivity(), "Large screen", Toast.LENGTH_LONG).show();
            int swidth = ((width - dpToPx(170))*3)/4;
            Log.i("myLog"," Large Swidth:"+swidth);
            markUpParam = new TableRow.LayoutParams(swidth/4,dpToPx(40));
           historyParam = new TableRow.LayoutParams(swidth/6,dpToPx(90));
        //    historyParam = new TableRow.LayoutParams((swidth/2)/5,dpToPx(60));
            textSize=14;
            leftMargin=10;
            topMargin=2;
            rightMargin=10;
            bottomMargin=2;
            tabParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);

        } else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(getActivity(), "Normal sized screen" , Toast.LENGTH_LONG).show();
            int swidth = (width *75)/100;
            Log.i("myLog"," Normal Swidth:"+swidth);

            markUpParam = new TableRow.LayoutParams(swidth,dpToPx(40));
            historyParam = new TableRow.LayoutParams((swidth/2)/5,dpToPx(40));
            textSize =12;
            leftMargin=10;
            topMargin=2;
            rightMargin=10;
            bottomMargin=2;
            tabParams.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);


        }
    }

    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }*/


