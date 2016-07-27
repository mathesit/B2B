package com.rever.rever_b2b.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
import java.util.List;
import java.util.Map;

/**
 * Created by Oviya on 7/13/2016.
 */
public class DashboardExtended extends Fragment {
    private View rootView;
    private ListView lv;
    private DashboardExtended.CustomListforEwShowAll customAdapter;

    private static  ProgressDialog progressDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_dashboard, container, false);
        initViews();
        GetEWListTask();
        return rootView;
    }
    private void initViews(){

        lv=(ListView) rootView.findViewById(R.id.listview_EW_details);
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

                    customAdapter = new DashboardExtended.CustomListforEwShowAll(getActivity(),
                            MasterCache.vbrandname, MasterCache.vprodtype, MasterCache.vserialno, MasterCache.vconsumername);
                    lv.setAdapter(customAdapter);
                    customAdapter.notifyDataSetChanged();

                    lv.performItemClick(lv.getAdapter().getView(0, null, null),
                            0, lv.getAdapter().getItemId(0));
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

}
