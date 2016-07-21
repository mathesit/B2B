package com.rever.rever_b2b.views;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bharath on 6/3/2016.
 */
public class EW_CallLogs_Fragment extends Fragment {
    private static String wiid;
    private View rootView;
    private TextView txtcaseId,txtname,txtnumber,txtcreatedby,txtdate,txtenquiry,txtdetailsbtn,txtclosebtn,newcase,submitNewcase,txtewCLdetailsbtn;
    private ListView lv,lv2;
    private static final String vCase_id = "case_id";
    private static final String vCl_name = "consumer_name";
    private static final String vCl_no = "consumer_mobile";
    private static final String vCreated_by = "created_by";
    private static final String vDate = "created_on";
    private static final String vEnquiry = "call_category";
    private String warrId;
    private PopupWindow popupWindow;
    private EditText Name;
    private Spinner mySpinner;
    private ListAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_call_logs, container, false);
        warrId = MasterCache.listPosition_id;
        initViews();
        return rootView;
    }

    public void initViews() {
        txtcaseId = (TextView) rootView.findViewById(R.id.txtewCLCaseId);
        txtname = (TextView) rootView.findViewById(R.id.txtewCLName);
        txtnumber = (TextView) rootView.findViewById(R.id.txtewCLnum);
        txtcreatedby = (TextView) rootView.findViewById(R.id.txtewCLcreatedbyname);
        txtdate = (TextView) rootView.findViewById(R.id.txtewCLDate);
        txtenquiry = (TextView) rootView.findViewById(R.id.txtewCLenquiry);
        txtdetailsbtn = (TextView) rootView.findViewById(R.id.txtewCLdetailsbtn);
        txtclosebtn = (TextView) rootView.findViewById(R.id.txtewCLclosecasebtn);
        lv2 =(ListView) rootView.findViewById(R.id.CallLogsListview);
        newcase =(TextView) rootView.findViewById(R.id.txtNewCaseBtn);
        // txtewCLdetailsbtn =(TextView) rootView.findViewById(R.id.txtewCLdetailsbtn);
        setListViewAdapter();
        //GetCallLogsTask(warrId);
        newcase.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // custom dialog
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.cl_new_case_popup);
//                dialog.setTitle("New Case");
                submitNewcase = (TextView) dialog.findViewById(R.id.submitNewCase);
                TextView closebtn = (TextView) dialog.findViewById(R.id.closeNewCase);
                final EditText consumer_name = (EditText) dialog.findViewById(R.id.cl_consumername);
                final EditText contact_no = (EditText) dialog.findViewById(R.id.cl_contactnum);
                mySpinner = (Spinner) dialog.findViewById(R.id.cl_cat);

                GetCallCatTask();
                submitNewcase.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg) {

                        String name = consumer_name.getText().toString();
                        String num = contact_no.getText().toString();
                        if (name.length() == 0) {
                            Snackbar.make(arg, "Please enter the name", Snackbar.LENGTH_SHORT).show();
                        } else if (num.length() == 0) {
                            Snackbar.make(arg, "Please enter the contact no", Snackbar.LENGTH_SHORT).show();
                        } else {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("consumer_name", name);
                            map.put("contact_no", num);
                            map.put("warranty_id", MasterCache.listPosition_id);
                            String spId = String.valueOf(MasterCache.spinnerPosition_id);
                            map.put("category_id", spId);

                            try {
                                String data = NetUtils.getPostDataString(map);
                                CreateNewCallCase(data);
                                dialog.dismiss();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                      //  GetCallLogsTask(warrId);
                    }

                });

                // if button is clicked, close the custom dialog
                closebtn.setOnClickListener(new View.OnClickListener()

                                            {
                                                @Override
                                                public void onClick(View v) {

                                                    dialog.dismiss();
                                                }
                                            }

                );

                dialog.show();
                mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

                                {

                                    @Override
                                    public void onItemSelected(AdapterView<?> arg0,
                                                               View arg1, int position, long arg3) {
                                        int cat = MasterCache.cat_id.get(position);
                                        Log.i("Cat", "id" + cat);
                                        MasterCache.spinnerPosition_id = cat;

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> arg0) {

                                    }
                                }

                );
            }
        });

    }

    public void GetCallLogsTask(String str) {
        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_CALL_LOGS_URL+str;
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "Success Response of call logs" + response);
                MasterCache.saveEWCallLogsCache(response);

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

    public void GetCallCatTask() {
        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_CALL_CAT_URL;
        Log.i("myLog", "url cat" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "Success Response of call category " + response);
                MasterCache.saveCallCat(response);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.spinner_item, MasterCache.cat_desc);
                mySpinner.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something...
                Log.i("myLog", "Error Response");
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


    public void CreateNewCallCase(String data) throws JSONException {
        // HTTP POST
        String url = NetUtils.HOST + NetUtils.EXTENDED_WARRANTY_NEW_CALL_CASE_URL;
        Log.i("myLog", "Url:" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject(data);

        // "consumer_name" : "13978" ,"contact_no" : "Test" , "warranty_id": 1212, category_id: "category_id"

        Log.i("myLog", "Data" + jsonObject);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "Success Response" + response);
                MasterCache.saveEWCallLogsCache(response);
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
    }

    private void setListViewAdapter() {
        adapter = new CustomCallLogList(getActivity(), R.layout.listview_call_log_cases,MasterCache.EWCallLogsList);
        lv2.setAdapter(adapter);
    }

}

