package com.rever.rever_b2b.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oviya on 6/8/2016.
 */
public class QuotationExtended extends Fragment {
    private View rootView;
    private LinearLayout linearDetails;
    private ListView listView;
    private TextView txtQuotId, txtQuotStatus, txtCreatedOn, txtCreatedBy, txtBrand, txtSerialNo, txtModel, txtProdType, txtEmail,
            txtCustomer, txtServiceDescription, txtOnsiteDescription, txtServiceAmt, txtOnsiteAmt, txtServiceSrp, txtOnsiteSrp, txtServiceMark, txtOnsiteMark,
            txtServiceItem, txtServiceUpc, txtServiceDesc, txtServiceClaim, txtServiceCharge, txtOnsiteItem, txtOnsiteUpc, txtOnsiteDesc,

    txtOnsiteClaim, txtOnsiteCharge, txtTotalClaim, txtTotalCharge, txtGstClaim, txtGstCharge, txtGrandClaim, txtGrandCharge, txtRemarks,
            txtReceivedFrom, txtReceivedOn, txtSentTo, txtUpdatedBy, txtUpdatedOn, txtStatusHistory;

    String quot_id = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_quotation, container, false);
        initViews();
        showAllQuotation();
        showQuotationViews(rootView);
        return rootView;
    }

    public void initViews() {
        //  linearDetails = (LinearLayout)rootView.findViewById(R.id.LinearQuotationDetails);
        listView = (ListView) rootView.findViewById(R.id.listInQuotation);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("myLog", "position:" + position);
                quot_id = MasterCache.quotId.get(position);
                Log.i("myLog", "sr_id" + quot_id);
                loadQuotationDetails(quot_id);
            }
        });

    }

    public void showAllQuotation() {
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
                    QuotationExtended.CustomList customAdapter = new QuotationExtended.CustomList(getActivity(), MasterCache.quotId, MasterCache.srNo, MasterCache.createdOn, MasterCache.quotStatus);
                    listView.setAdapter(customAdapter);

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
                    headers.put("Content-Type", "application/json");
                    headers.put("Accept", "application/json");
                    headers.put("Authorization", ReverApplication.getSessionToken());
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showQuotationViews(View v) {
        txtQuotId = (TextView) v.findViewById(R.id.quotId);
        txtQuotStatus = (TextView) v.findViewById(R.id.quotStatus);
        txtCreatedOn = (TextView) v.findViewById(R.id.quotCreatedOn);
        txtCreatedBy = (TextView) v.findViewById(R.id.quotCreatedBy);
        txtBrand = (TextView) v.findViewById(R.id.quotBrand);
        txtSerialNo = (TextView) v.findViewById(R.id.quotSerialNo);
        txtModel = (TextView) v.findViewById(R.id.quotModel);
        txtProdType = (TextView) v.findViewById(R.id.quotProdType);
        txtEmail = (TextView) v.findViewById(R.id.quotEmail);
        txtCustomer = (TextView) v.findViewById(R.id.quotCustomer);
        txtServiceDescription = (TextView) v.findViewById(R.id.quotService);
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
        txtTotalClaim = (TextView) v.findViewById(R.id.quotClaimTotal);
        txtTotalCharge = (TextView) v.findViewById(R.id.quotChargeTotal);
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
        txtStatusHistory = (TextView) v.findViewById(R.id.quotStatusHistory);
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
                        MasterCache.saveQuotationDetails(String.valueOf(response));
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
                headers.put("Content-Type", "application/json");
                headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);

    }

    private void loadQuotRequest() {
        txtQuotId.setText(MasterCache.quot_id.get(0));
        txtBrand.setText(MasterCache.quotBrand.get(0));
        txtModel.setText(MasterCache.quotModel.get(0));
        txtSerialNo.setText(MasterCache.quotSerialNo.get(0));
        txtProdType.setText(MasterCache.quotProductType.get(0));
        txtEmail.setText(MasterCache.quotEmail.get(0));
        txtCustomer.setText(MasterCache.quotConsumer.get(0));
        txtCreatedOn.setText(MasterCache.quotCreate.get(0));
        txtCreatedBy.setText(MasterCache.quotCreatedBy.get(0));
        txtQuotStatus.setText(MasterCache.quotationStatus.get(0));
        txtServiceDescription.setText(MasterCache.quotService.get(0));
        txtServiceAmt.setText(MasterCache.quotServAmt.get(0));
        txtServiceSrp.setText(MasterCache.quotServSrp.get(0));
        txtServiceMark.setText(MasterCache.quotServMark.get(0));
        txtReceivedFrom.setText(MasterCache.quotReceivedFrom.get(0));
        txtReceivedOn.setText(MasterCache.quotCreatedOn.get(0));
        txtSentTo.setText(MasterCache.quotSentTo.get(0));
        txtUpdatedBy.setText(MasterCache.quotUpdatedBy.get(0));
        txtUpdatedOn.setText(MasterCache.quotUpdatedOn.get(0));
        txtStatusHistory.setText(MasterCache.quotStatusHistory.get(0));
      /*  txtOnsiteDescription.setText("");
        txtOnsiteAmt.setText("");
        txtOnsiteSrp.setText("");
        txtOnsiteMark.setText("");
       txtOnsiteDescription.setText(MasterCache.quotService.get(0));
        txtOnsiteAmt.setText(MasterCache.quotServAmt.get(0));
        txtOnsiteSrp .setText(MasterCache.quotServSrp.get(0));
        txtOnsiteMark.setText(MasterCache.quotServMark.get(0));*/

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
        txtRemarks.setText(" ");


    }

}




