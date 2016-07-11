package com.rever.rever_b2b.views;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
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
import com.rever.rever_b2b.model.EWTabCallLogs;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bharath on 6/3/2016.
 */
public class EWCallLogsFragment extends Fragment {
    private static String wiid;
    private View rootView;
    private TextView txtcaseId,txtname,txtnumber,txtcreatedby,txtdate,txtenquiry,txtdetailsbtn,txtclosebtn,newcase;
    private ListView lv,lv2;
    private static final String vCase_id = "case_id";
    private static final String vCl_name = "consumer_name";
    private static final String vCl_no = "consumer_mobile";
    private static final String vCreated_by = "created_by";
    private static final String vDate = "created_on";
    private static final String vEnquiry = "call_category";
    private String Item;
    private PopupWindow popupWindow;
    private EditText Name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        //Item = arguments.getString("wid");
        Item = MasterCache.position_id;
        rootView = inflater.inflate(R.layout.fragment_ew_call_logs, container, false);
        Log.i("BUNDLE", "Item" + Item);
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
        GetCallLogsTask(Item);
        newcase.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // custom dialog
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.cl_new_case_popup);
//                dialog.setTitle("New Case");
                TextView dialogButton = (TextView) dialog.findViewById(R.id.closeNewCase);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
               // callPopup();
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
                        Log.i("myLog", "answer" + MasterCache.cl_case_id);
                        Log.i("myLog", "answer" + MasterCache.cl_call_category);
                        Log.i("myLog", "answer" + MasterCache.jo);
                        Log.i("myLog", "answer" + MasterCache.cljo);
                        ListAdapter adapter1 = new SimpleAdapter(getContext().getApplicationContext(), MasterCache.cljo,
                                R.layout.listview_call_log_cases, new String[]
                                {vCase_id, vCl_name, vCl_no, vCreated_by, vDate, vEnquiry},
                                new int[]{R.id.txtewCLCaseId, R.id.txtewCLName, R.id.txtewCLnum, R.id.txtewCLcreatedbyname, R.id.txtewCLDate, R.id.txtewCLenquiry});

                        lv2.setAdapter(adapter1);

                        //lv.getAdapter().getView(0, null, null).performClick();
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

    public void callPopup() {

        LayoutInflater layoutInflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View popupView = layoutInflater.inflate(R.layout.cl_new_case_popup, null);

        popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                true);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);


        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        Name = (EditText) popupView.findViewById(R.id.cl_consumername);

        ((TextView) popupView.findViewById(R.id.submitNewCase))
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
                        Toast.makeText(getActivity(), "get", Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();

                    }

                });

        ((TextView) popupView.findViewById(R.id.closeNewCase))
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {

                        popupWindow.dismiss();
                    }
                });
    }
}

