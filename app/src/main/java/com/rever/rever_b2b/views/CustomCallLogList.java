package com.rever.rever_b2b.views;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rever.rever_b2b.R;
import com.rever.rever_b2b.model.EWTabCallLogs;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomCallLogList extends ArrayAdapter<EWTabCallLogs> {

    private Activity activity;
    private ListView lv;
    private CustomCallLogList.CustomListforEwShowAll customAdapter;

    private ProgressDialog progressDialog;
    private Dialog dialog, dialog2;


    public CustomCallLogList(Activity activity, int resource, List<EWTabCallLogs> Userra) {
        super(activity, resource, Userra);
        this.activity = activity;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_call_log_cases, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final EWTabCallLogs user = getItem(position);

        holder.name.setText(user.getconsumer_name());
        holder.num.setText(user.getconsumer_mobile());
        holder.createdby.setText(user.getcreated_by());
        holder.caseId.setText(user.getcase_id());
        holder.date.setText(user.getcreated_on());
        holder.enquiry.setText(user.getcall_category());


        holder.Detailsbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click


                dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.cl_case_details_popup);
                ListView lv1 = (ListView) dialog.findViewById(R.id.newcalllogList);

                TextView CloseNewCase = (TextView) dialog.findViewById(R.id.closeNewCase);
                TextView newLogBtn = (TextView) dialog.findViewById(R.id.newlogbtn);
                EditText case_id = (EditText) dialog.findViewById(R.id.DTcaseId);
                EditText case_status = (EditText) dialog.findViewById(R.id.DTcaseStatus);
                EditText createdOn = (EditText) dialog.findViewById(R.id.DTcreatedOn);
                EditText createdBy = (EditText) dialog.findViewById(R.id.DTcreatedBy);
                EditText cusName = (EditText) dialog.findViewById(R.id.DTcusName);
                EditText callCat = (EditText) dialog.findViewById(R.id.DTcallCat);
                EditText contactNo = (EditText) dialog.findViewById(R.id.DTcontactNo);
                EditText cusEmail = (EditText) dialog.findViewById(R.id.DTemail);
                String str = user.getcase_id();
                GetDetailCallLogTask(str);


//                ListAdapter adapter = new SimpleAdapter(getContext().getApplicationContext(),
//                        MasterCache.callLogEntry, R.layout.new_call_log_listview,
//                        new String[] {vlogged_by, vlogged_on, vlog_desc},
//                        new int[] {R.id.loggedby, R.id.loggedon, R.id.logdesc });
//                Log.i("myLog", "answer1" + adapter);
//                lv1.setAdapter(adapter);
//                lv1.invalidateViews();

                Log.i("myLog", "Success Response of call cat" + MasterCache.cld_log_desc);

                case_id.setText(user.getcase_id());
                case_status.setText(MasterCache.cld_sr_status.get(str));
                createdOn.setText(MasterCache.cld_logged_on.get(str));
                createdBy.setText(MasterCache.cld_logged_by.get(str));
                cusName.setText(MasterCache.cl_consumer_name.get(str));
                callCat.setText(MasterCache.cl_call_category.get(str));
                contactNo.setText(MasterCache.cl_consumer_mobile.get(str));
                cusEmail.setText(MasterCache.cl_consumer_email.get(str));

                newLogBtn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {

                        dialog2 = new Dialog(getContext());
                        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog2.setContentView(R.layout.cl_add_calllog_popup);
                        final EditText Logdes = (EditText) dialog2.findViewById(R.id.Logdes);
                        TextView cancelAddCallLogs = (TextView) dialog2.findViewById(R.id.closeNewCase);
                        TextView SubmitnewCallLog = (TextView) dialog2.findViewById(R.id.submitNewCase);

                        cancelAddCallLogs.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog2.dismiss();

                            }

                        });
                        SubmitnewCallLog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                { "case_id" : "13978" ,"log_desc" : "Test"}
                                HashMap<String, String> map = new HashMap<>();
                                map.put("case_id", user.getcase_id());
                                map.put("log_desc", Logdes.getText().toString());
                                try {
                                    String data = NetUtils.getPostDataString(map);
                                    CreateNewCallLogTask(data);
                                    customAdapter.notifyDataSetChanged();
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                dialog2.dismiss();

                            }

                        });
                        dialog2.show();
                    }
                });

                CloseNewCase.setOnClickListener(new View.OnClickListener()

                {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }

                });

                dialog.show();
            }
        });


        holder.Closebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MasterCache.closeCaseRequest = user.getcase_id();

                //EW_CallLogs_Fragment.closeRequestlog();

                HashMap<String, String> map = new HashMap<>();
                map.put("warranty_id", MasterCache.listPosition_id);
                map.put("case_id", user.getcase_id());
                try {
                    String data = NetUtils.getPostDataString(map);
                    // MasterCache.closeCaseRequest=data;
                    CloseCallLogTask(data);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                remove(user);

            }
        });
        return convertView;
    }

    private static class ViewHolder {
        private TextView name,num,createdby,caseId,date,enquiry,Detailsbutton,Closebutton;

        public ViewHolder(View v) {
            name = (TextView) v.findViewById(R.id.txtewCLName);
            num = (TextView) v.findViewById(R.id.txtewCLnum);
            createdby = (TextView) v.findViewById(R.id.txtewCLcreatedbyname);
            caseId= (TextView) v.findViewById(R.id.txtewCLCaseId);;
            date = (TextView) v.findViewById(R.id.txtewCLDate);
            enquiry = (TextView) v.findViewById(R.id.txtewCLenquiry);
            Detailsbutton =(TextView) v.findViewById(R.id.txtewCLdetailsbtn);
            Closebutton =(TextView) v.findViewById(R.id.txtewCLclosecasebtn);
        }
    }

    public void CloseCallLogTask(String data) throws JSONException {
        // HTTP POST
        String url = NetUtils.HOST + NetUtils.EXTENDED_WARRANTY_CLOSE_CALL_CASE_URL;
        Log.i("myLog", "Url:" + url);
        Log.i("myLog", "Data" + data);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JSONObject jsonObject = new JSONObject(data);
        // "consumer_name" : "13978" ,"contact_no" : "Test" , "warranty_id": 1212, category_id: "category_id"

        Log.i("myLog", "JSON CONVERTED Data" + jsonObject);
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

    public void CreateNewCallLogTask(String data) throws JSONException {
        // HTTP POST
        String url = NetUtils.HOST + NetUtils.EXTENDED_WARRANTY_NEW_CALL_LOG_URL;
        Log.i("myLog", "Url:" + url);
        Log.i("myLog", "Data" + data);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JSONObject jsonObject = new JSONObject(data);
        // "consumer_name" : "13978" ,"contact_no" : "Test" , "warranty_id": 1212, category_id: "category_id"

        Log.i("myLog", "JSON CONVERTED Data" + jsonObject);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "New Call Log Response" + response);

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



    public class CustomListforEwShowAll extends ArrayAdapter<String> {
        private List<String> vbrand_name, vproduct_type, vconsumer_name;
        private Activity context;



        public CustomListforEwShowAll(Activity context, List<String> vbrand_name, List<String> vproduct_type, List<String> vconsumer_name) {
            super(context, R.layout.new_call_log_listview, vbrand_name);
            this.context = context;
            this.vbrand_name = vbrand_name;
            this.vproduct_type = vproduct_type;
            this.vconsumer_name = vconsumer_name;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.new_call_log_listview, null, true);
            TextView txtbrand_name = (TextView) listViewItem.findViewById(R.id.loggedby);
            TextView txtproduct_type = (TextView) listViewItem.findViewById(R.id.loggedon);
            TextView txtconsumer_name = (TextView) listViewItem.findViewById(R.id.logdesc);


            txtbrand_name.setText(vbrand_name.get(position));
            txtproduct_type.setText(vproduct_type.get(position));
            txtconsumer_name.setText(vconsumer_name.get(position));
            return listViewItem;
        }
    }


    public void GetDetailCallLogTask (String str){
        progressDialog = ProgressDialog.show(getContext(), "Progress Dialog Title Text", "Process Description Text", true);
        String url = NetUtils.HOST + NetUtils.EXTENDED_WARRANTY_CALL_CASE_DETAILS_URL + str;
        Log.i("myLog", "url cat" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "Success Response of call cat" + response);
                MasterCache.saveEWCallLogsDetailsCache(response);

                customAdapter = new CustomCallLogList.CustomListforEwShowAll
                        (activity, MasterCache.cld_logged_by1, MasterCache.cld_logged_on1, MasterCache.cld_log_desc1);
                ((ListView) dialog.findViewById(R.id.newcalllogList)).setAdapter(customAdapter);
                customAdapter.notifyDataSetChanged();

                Log.i("test", "test");

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

}
