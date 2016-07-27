package com.rever.rever_b2b.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import com.rever.rever_b2b.model.EWDetails;
import com.rever.rever_b2b.model.EWTabCallLogs;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oviya on 7/13/2016.
 */
public class DashboardExtended extends Fragment {
    private View rootView;
    private TableLayout tblEWDetails, tblFailure;
    private TextView txtLogCount, txtRequestCount, txtPendingCount;
    private TableRow.LayoutParams EwParams, failureParams;
    private int width, height;
    private ListAdapter adapter;
    private ListView listView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_dashboard, container, false);
        initViews();
        initParams();
        loadTopFailures();
        //showTopBrands();
        //loadTopBrands();

        return rootView;

    }


    private void initViews(){
        tblEWDetails = (TableLayout) rootView.findViewById(R.id.tblStockBalInEwDash);
        txtLogCount = (TextView)rootView.findViewById(R.id.txtCaseLogsCountInEwDash);
        txtPendingCount = (TextView)rootView.findViewById(R.id.txtPendQuotCountInEwDash);
        txtRequestCount = (TextView)rootView.findViewById(R.id.txtServReqCountInEwDash);
        tblFailure = (TableLayout)rootView.findViewById(R.id.tblFailuresInEwDash);
        loadCaseLog();
        loadPendingQuote();
        loadServiceLog();
//        tblEWDetails.setVisibility(View.VISIBLE);
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

        } else if((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(getActivity(), "Large screen", Toast.LENGTH_LONG).show();
            int swidth = width - dpToPx(150);
            Log.i("myLog","Swidth:"+swidth);
            EwParams = new TableRow.LayoutParams(swidth/6,dpToPx(45));
            failureParams = new TableRow.LayoutParams((swidth/2)/6,dpToPx(45));
           // failureParams = new TableRow.LayoutParams((swidth/2)/5,dpToPx(45));

        } else if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(getActivity(), "Normal sized screen" , Toast.LENGTH_LONG).show();
            int swidth = (width *75)/100;
            EwParams = new TableRow.LayoutParams(swidth,dpToPx(50));
            failureParams = new TableRow.LayoutParams((swidth/2)/5,dpToPx(40));

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

/*    public void loadTopBrands() {

       String url = NetUtils.HOST + NetUtils.EXTENDED_WARRANTY_URL;
        Log.i("myLog", "Listview url:" + url);

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
                    MasterCache.saveBrandCache(String.valueOf(response));
                    showTopBrands();

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // do something...
                    Log.i("myLog", "Error Response: " + error);
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
    }*/

   /* public class CustomBrandList extends ArrayAdapter<String> {
        private List<String> brand, product, sNo, customer;
        private Activity context;

        public CustomBrandList(Activity context, List<String> brand, List<String> product, List<String> sNo, List<String> customer) {
            super(context, R.layout.list_dashboard);
            this.context = context;
            this.brand = brand;
            this.product = product;
            this.sNo = sNo;
            this.customer = customer;
        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.list_dashboard, null, true);
            TextView txtBrand = (TextView) listViewItem.findViewById(R.id.txtDashBrand);
            TextView txtConsumer = (TextView) listViewItem.findViewById(R.id.txtDashCustomer);
            TextView txtProduct = (TextView) listViewItem.findViewById(R.id.txtDashProd);
            TextView txtSrNo = (TextView) listViewItem.findViewById(R.id.txtDashSerial);

            txtBrand.setText(brand.get(position));
            txtProduct.setText(product.get(position));
            txtSrNo.setText(sNo.get(position));
            txtConsumer.setText(customer.get(position));
            return listViewItem;
            /*txtBrand.setText(MasterCache.brandName.toString());
            txtConsumer.setText(MasterCache.consumerName.toString());
            txtProduct.setText(MasterCache.productType.toString());
            txtSrNo.setText(MasterCache.serialNo.toString());
        }
    }*/

   /* public void showTopBrands(){
         CustomBrandList listAdapter = new CustomBrandList(getActivity(), MasterCache.brandName, MasterCache.productType, MasterCache.serialNo, MasterCache.consumerName);
        listView.setAdapter(listAdapter);

       adapter = new CustomBrandList(getActivity(), R.layout.list_dashboard,MasterCache.EWDetailsList);
        listView.setAdapter(adapter);

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


        int size = MasterCache.brandName.size();
        if(size>=5){
            size=5;
        }
        for(int start=0; start<size; start++) {
            TableRow tr= new TableRow(getActivity());
            TextView txtBrand = new TextView(getActivity());
            txtBrand.setText(MasterCache.brandName.get(start));
            Log.i("Brand:", MasterCache.brandName.get(start));
            txtBrand.setGravity(Gravity.CENTER);
            tr.addView(txtBrand, EwParams);

            TextView txtProduct = new TextView(getActivity());
            txtProduct.setText(MasterCache.productType.get(start));
            Log.i("Product:", MasterCache.productType.get(start));
            txtProduct.setGravity(Gravity.CENTER);
            tr.addView(txtProduct, EwParams);

            TextView txtSerial = new TextView(getActivity());
            txtSerial.setText(MasterCache.serialNo.get(start));
            Log.i("Serial:", MasterCache.serialNo.get(start));
            txtSerial.setGravity(Gravity.CENTER);
            tr.addView(txtSerial, EwParams);

            TextView txtCustomer = new TextView(getActivity());
            txtCustomer.setText(String.valueOf(MasterCache.consumerName.get(start)));
            txtCustomer.setGravity(Gravity.CENTER);
            tr.addView(txtCustomer, EwParams);
            Log.i("Customer:", MasterCache.consumerName.get(start));

            ImageView img = new ImageView(getActivity());
            img.setImageResource(R.drawable.dot);
            //  rel.addView(img, alertParam);
            tr.addView(img, EwParams);

            View v = new View(getActivity());
            v.setBackgroundColor(Color.LTGRAY);
            TableRow trLine = new TableRow(getActivity());
            trLine.addView(v, failureParams);
            tblEWDetails.addView(tr);
             tblFailure.addView(trLine);*/




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
           /* View v = new View(getActivity());
            v.setBackgroundColor(Color.LTGRAY);
            TableRow trLine = new TableRow(getActivity());
            trLine.addView(v, failureParams);*/
            tblFailure.addView(tr);
           // tblFailure.addView(trLine);

        }
    }
}
