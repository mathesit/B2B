package com.rever.rever_b2b.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
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
 public class EW_Details_Fragment extends Fragment {
    private View rootView;
    private EditText edtSerialno,edtBrand,edtCountry,edtExtendedWCategory,
            edtExtendedWInvoice,edtExtendedWNo,edtExtendedWPdate,edtExtendedWPrice,
            edtExtendedWProvider,edtExtendedWStartDate,edtUPCCode,edtModel,
            edtProductType,edtVoidRefund,edtWarrantyExtendMonths,edtExpDate,edtRegDate,edtEWDdescription;
    private  TextView txteditbtn;
    private String wiid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_details, container, false);
        initView();
        Settextfields();
        return rootView;
    }

    public void initView(){
        wiid=MasterCache.listPosition_id;
        Log.i("myLog", "warr_id in: " + wiid);
        //GetewDetailsTask(wiid);
        txteditbtn = (TextView) rootView.findViewById(R.id.txteditbtn);
        edtSerialno = (EditText) rootView.findViewById(R.id.edtSerialNo);
        edtBrand = (EditText) rootView.findViewById(R.id.edtbrand);
        edtCountry = (EditText) rootView.findViewById(R.id.edtCountry);
        edtExtendedWCategory = (EditText) rootView.findViewById(R.id.edtExtendedWCategory);
        edtExtendedWInvoice = (EditText) rootView.findViewById(R.id.edtExtendedWInvoice);
        edtExtendedWNo = (EditText) rootView.findViewById(R.id.edtExtendedWNo);
        edtExtendedWPdate = (EditText) rootView.findViewById(R.id.edtExtendedWPdate);
        edtExtendedWPrice = (EditText) rootView.findViewById(R.id.edtExtendedWPrice);
        edtExtendedWProvider = (EditText) rootView.findViewById(R.id.edtExtendedWProvider);
        edtExtendedWStartDate = (EditText) rootView.findViewById(R.id.edtExtendedWStartDate);
        edtUPCCode = (EditText) rootView.findViewById(R.id.edtUPCCode);
        edtModel = (EditText) rootView.findViewById(R.id.edtModel);
        edtProductType = (EditText) rootView.findViewById(R.id.edtProductType);
        edtVoidRefund = (EditText) rootView.findViewById(R.id.edtVoidRefund);
        edtWarrantyExtendMonths = (EditText) rootView.findViewById(R.id.edtWarrantyExtendMonths);
        edtExpDate=(EditText) rootView.findViewById(R.id.EWexpiryDate);
        edtRegDate=(EditText) rootView.findViewById(R.id.EWregisterDate);
        edtEWDdescription=(EditText) rootView.findViewById(R.id.edtEWDdescription);



    }


    public void Settextfields(){
        if(MasterCache.PrewwarrId.size()==0){
            Toast.makeText(getContext(), "empty list in product details", Toast.LENGTH_SHORT).show();
        }else {
            int id = MasterCache.warrId.get(0);
            if(MasterCache.pserialNo.get(id)!=null)
            edtSerialno.setText(MasterCache.pserialNo.get(id));
            if(MasterCache.pbrandname.get(id)!=null)
            edtBrand.setText(MasterCache.pbrandname.get(id));
            if(MasterCache.countryCode.get(id)!=null)
            edtCountry.setText(MasterCache.countryCode.get(id));
            if(MasterCache.EW_CATEGORY.get(id)!=null)
            edtExtendedWCategory.setText(MasterCache.EW_CATEGORY.get(id));
            if(MasterCache.invoiceNo.get(id)!=null)
            edtExtendedWInvoice.setText(MasterCache.invoiceNo.get(id));
            if(MasterCache.warrNo.get(id)!=null)
            edtExtendedWNo.setText(MasterCache.warrNo.get(id));
            if(MasterCache.ppurchaseDate.get(id)!=null)
            edtExtendedWPdate.setText(MasterCache.ppurchaseDate.get(id));
            if(MasterCache.warrSellingPrice.get(id)!=null)
            edtExtendedWPrice.setText(MasterCache.warrSellingPrice.get(id));
            if(MasterCache.providerCompName.get(id)!=null)
            edtExtendedWProvider.setText(MasterCache.providerCompName.get(id));
            if(MasterCache.warrSdate.get(id)!=null)
            edtExtendedWStartDate.setText(MasterCache.warrSdate.get(id));
            if(MasterCache.prUpcode.get(id)!=null)
            edtUPCCode.setText(MasterCache.prUpcode.get(id));
            if(MasterCache.pmodelName.get(id)!=null)
            edtModel.setText(MasterCache.pmodelName.get(id));
            if(MasterCache.pproductType.get(id)!=null)
            edtProductType.setText(MasterCache.pproductType.get(id));
            if(MasterCache.prVoidrefund.get(id)!=null)
            edtVoidRefund.setText(MasterCache.prVoidrefund.get(id));
            if(MasterCache.warrMonths.get(id)!=null)
            edtWarrantyExtendMonths.setText(MasterCache.warrMonths.get(id));
            if(MasterCache.warrExpDate.get(id)!=null)
            edtExpDate.setText(MasterCache.warrExpDate.get(id));
            if(MasterCache.ew_regDate.get(id)!=null)
            edtRegDate.setText(MasterCache.ew_regDate.get(id));
            if(MasterCache.prAddiInfo.get(id)!=null)
            edtEWDdescription.setText(MasterCache.prAddiInfo.get(id));

            txteditbtn.setTag(1);
            txteditbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    final int status = (Integer) v.getTag();
                    if (status == 1) {
                        txteditbtn.setText("DONE");
                        v.setTag(0); //pause

                        edtSerialno.setFocusableInTouchMode(true);
                        edtBrand.setFocusableInTouchMode(true);
                        edtCountry.setFocusableInTouchMode(true);
                        edtExtendedWCategory.setFocusableInTouchMode(true);
                        edtExtendedWInvoice.setFocusableInTouchMode(true);
                        edtExtendedWNo.setFocusableInTouchMode(true);
                        edtExtendedWPdate.setFocusableInTouchMode(true);
                        edtExtendedWPrice.setFocusableInTouchMode(true);
                        edtExtendedWProvider.setFocusableInTouchMode(true);
                        edtExtendedWStartDate.setFocusableInTouchMode(true);
                        edtUPCCode.setFocusableInTouchMode(true);
                        edtModel.setFocusableInTouchMode(true);
                        edtProductType.setFocusableInTouchMode(true);
                        edtVoidRefund.setFocusableInTouchMode(true);
                        edtWarrantyExtendMonths.setFocusableInTouchMode(true);
                        edtExpDate.setFocusableInTouchMode(true);
                        edtRegDate.setFocusableInTouchMode(true);

                        edtEWDdescription.setFocusableInTouchMode(true);
                        edtEWDdescription.setClickable(true);
                        edtEWDdescription.setFocusable(true);
                        edtEWDdescription.setBackgroundResource(R.drawable.edittext_bg);

                        edtSerialno.setClickable(true);
                        edtBrand.setClickable(true);
                        edtCountry.setClickable(true);
                        edtExtendedWCategory.setClickable(true);
                        edtExtendedWInvoice.setClickable(true);
                        edtExtendedWNo.setClickable(true);
                        edtExtendedWPdate.setClickable(true);
                        edtExtendedWPrice.setClickable(true);
                        edtExtendedWProvider.setClickable(true);
                        edtExtendedWStartDate.setClickable(true);
                        edtUPCCode.setClickable(true);
                        edtModel.setClickable(true);
                        edtProductType.setClickable(true);
                        edtVoidRefund.setClickable(true);
                        edtWarrantyExtendMonths.setClickable(true);
                        edtExpDate.setClickable(true);
                        edtRegDate.setClickable(true);


                        edtSerialno.setFocusable(true);
                        edtBrand.setFocusable(true);
                        edtCountry.setFocusable(true);
                        edtExtendedWCategory.setFocusable(true);
                        edtExtendedWInvoice.setFocusable(true);
                        edtExtendedWNo.setFocusable(true);
                        edtExtendedWPdate.setFocusable(true);
                        edtExtendedWPrice.setFocusable(true);
                        edtExtendedWProvider.setFocusable(true);
                        edtExtendedWStartDate.setFocusable(true);
                        edtUPCCode.setFocusable(true);
                        edtModel.setFocusable(true);
                        edtProductType.setFocusable(true);
                        edtVoidRefund.setFocusable(true);
                        edtWarrantyExtendMonths.setFocusable(true);
                        edtExpDate.setFocusable(true);
                        edtRegDate.setFocusable(true);


                        edtSerialno.setBackgroundResource(R.drawable.edittext_bg);
                        edtBrand.setBackgroundResource(R.drawable.edittext_bg);
                        edtCountry.setBackgroundResource(R.drawable.edittext_bg);
                        edtExtendedWCategory.setBackgroundResource(R.drawable.edittext_bg);
                        edtExtendedWInvoice.setBackgroundResource(R.drawable.edittext_bg);
                        edtExtendedWNo.setBackgroundResource(R.drawable.edittext_bg);
                        edtExtendedWPdate.setBackgroundResource(R.drawable.edittext_bg);
                        edtExtendedWPrice.setBackgroundResource(R.drawable.edittext_bg);
                        edtExtendedWProvider.setBackgroundResource(R.drawable.edittext_bg);
                        edtExtendedWStartDate.setBackgroundResource(R.drawable.edittext_bg);
                        edtUPCCode.setBackgroundResource(R.drawable.edittext_bg);
                        edtModel.setBackgroundResource(R.drawable.edittext_bg);
                        edtProductType.setBackgroundResource(R.drawable.edittext_bg);
                        edtVoidRefund.setBackgroundResource(R.drawable.edittext_bg);
                        edtWarrantyExtendMonths.setBackgroundResource(R.drawable.edittext_bg);
                        edtExpDate.setBackgroundResource(R.drawable.edittext_bg);
                        edtRegDate.setBackgroundResource(R.drawable.edittext_bg);
                    } else {
//Start of alert dialog
                        new AlertDialog.Builder(getContext())
                                .setTitle("Update")
                                .setMessage("Update changes")
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // continue with delete
                                        txteditbtn.setText("EDIT");
                                        v.setTag(1); //pause

                                        edtSerialno.setFocusableInTouchMode(false);
                                        edtBrand.setFocusableInTouchMode(false);
                                        edtCountry.setFocusableInTouchMode(false);
                                        edtExtendedWCategory.setFocusableInTouchMode(false);
                                        edtExtendedWInvoice.setFocusableInTouchMode(false);
                                        edtExtendedWNo.setFocusableInTouchMode(false);
                                        edtExtendedWPdate.setFocusableInTouchMode(false);
                                        edtExtendedWPrice.setFocusableInTouchMode(false);
                                        edtExtendedWProvider.setFocusableInTouchMode(false);
                                        edtExtendedWStartDate.setFocusableInTouchMode(false);
                                        edtUPCCode.setFocusableInTouchMode(false);
                                        edtModel.setFocusableInTouchMode(false);
                                        edtProductType.setFocusableInTouchMode(false);
                                        edtVoidRefund.setFocusableInTouchMode(false);
                                        edtWarrantyExtendMonths.setFocusableInTouchMode(false);
                                        edtExpDate.setFocusableInTouchMode(false);
                                        edtRegDate.setFocusableInTouchMode(false);

                                        edtEWDdescription.setFocusableInTouchMode(false);
                                        edtEWDdescription.setClickable(false);
                                        edtEWDdescription.setFocusable(false);
                                        edtEWDdescription.setBackgroundResource(0);


                                        edtSerialno.setClickable(false);
                                        edtBrand.setClickable(false);
                                        edtCountry.setClickable(false);
                                        edtExtendedWCategory.setClickable(false);
                                        edtExtendedWInvoice.setClickable(false);
                                        edtExtendedWNo.setClickable(false);
                                        edtExtendedWPdate.setClickable(false);
                                        edtExtendedWPrice.setClickable(false);
                                        edtExtendedWProvider.setClickable(false);
                                        edtExtendedWStartDate.setClickable(false);
                                        edtUPCCode.setClickable(false);
                                        edtModel.setClickable(false);
                                        edtProductType.setClickable(false);
                                        edtVoidRefund.setClickable(false);
                                        edtWarrantyExtendMonths.setClickable(false);
                                        edtExpDate.setClickable(false);
                                        edtRegDate.setClickable(false);


                                        edtSerialno.setFocusable(false);
                                        edtBrand.setFocusable(false);
                                        edtCountry.setFocusable(false);
                                        edtExtendedWCategory.setFocusable(false);
                                        edtExtendedWInvoice.setFocusable(false);
                                        edtExtendedWNo.setFocusable(false);
                                        edtExtendedWPdate.setFocusable(false);
                                        edtExtendedWPrice.setFocusable(false);
                                        edtExtendedWProvider.setFocusable(false);
                                        edtExtendedWStartDate.setFocusable(false);
                                        edtUPCCode.setFocusable(false);
                                        edtModel.setFocusable(false);
                                        edtProductType.setFocusable(false);
                                        edtVoidRefund.setFocusable(false);
                                        edtWarrantyExtendMonths.setFocusable(false);
                                        edtExpDate.setFocusable(false);
                                        edtRegDate.setFocusable(false);

                                        edtSerialno.setBackgroundResource(0);
                                        edtBrand.setBackgroundResource(0);
                                        edtCountry.setBackgroundResource(0);
                                        edtExtendedWCategory.setBackgroundResource(0);
                                        edtExtendedWInvoice.setBackgroundResource(0);
                                        edtExtendedWNo.setBackgroundResource(0);
                                        edtExtendedWPdate.setBackgroundResource(0);
                                        edtExtendedWPrice.setBackgroundResource(0);
                                        edtExtendedWProvider.setBackgroundResource(0);
                                        edtExtendedWStartDate.setBackgroundResource(0);
                                        edtUPCCode.setBackgroundResource(0);
                                        edtModel.setBackgroundResource(0);
                                        edtProductType.setBackgroundResource(0);
                                        edtVoidRefund.setBackgroundResource(0);
                                        edtWarrantyExtendMonths.setBackgroundResource(0);
                                        edtExpDate.setBackgroundResource(0);
                                        edtRegDate.setBackgroundResource(0);

                                        //getText and post

                                        String strSerialno = edtSerialno.getText().toString();
                                        String strBrand = edtBrand.getText().toString();
                                        String strCountry = edtCountry.getText().toString();
                                        String strExtendedWCategory = edtExtendedWCategory.getText().toString();
                                        String strExtendedWInvoice = edtExtendedWInvoice.getText().toString();
                                        String strExtendedWNo = edtExtendedWNo.getText().toString();
                                        String strExtendedWPdate = edtExtendedWPdate.getText().toString();
                                        String strExtendedWPrice = edtExtendedWPrice.getText().toString();
                                        String strExtendedWProvider = edtExtendedWProvider.getText().toString();
                                        String strExtendedWStartDate = edtExtendedWStartDate.getText().toString();
                                        String strUPCCode = edtUPCCode.getText().toString();
                                        String strModel = edtModel.getText().toString();
                                        String strProductType = edtProductType.getText().toString();
                                        String strVoidRefund = edtVoidRefund.getText().toString();
                                        String strWarrantyExtendMonths = edtWarrantyExtendMonths.getText().toString();
                                        String strExpDate = edtExpDate.getText().toString();
                                        String strRegDate = edtRegDate.getText().toString();
                                        String strAddInfo = edtEWDdescription.getText().toString();

                                        HashMap<String, String> map = new HashMap<>();
                                        map.put("consumer_id",MasterCache.consumerId.get(MasterCache.warrId.get(0)));
                                        map.put("city", MasterCache.prCity.get(0));
                                        //map.put("title",String.valueOf(MasterCache.listPosition_id));
                                        map.put("address_line", MasterCache.prAddLine1.get(0));
                                        map.put("mobile", MasterCache.prMobile.get(0));
                                        map.put("postal_code", MasterCache.prPostal.get(0));
                                        map.put("first_name", MasterCache.prFname.get(0));
                                        map.put("last_name", MasterCache.prLname.get(0));
                                        map.put("address_line2", MasterCache.prAddLine2.get(0));
                                        map.put("state", MasterCache.prState.get(0));
                                        //map.put("country_code",MasterCache.userCountryCode.get(MasterCache.userId.get(0)));
                                        map.put("ic_no", String.valueOf(MasterCache.prIcNo.get(0)));
                                        map.put("eq_stock_id", MasterCache.eq_stockId.get(MasterCache.warrId.get(0)));
                                        Log.i("", "EqStock" + MasterCache.eq_stockId.get(MasterCache.warrId.get(0)));
                                        map.put("serial_no", strSerialno);
                                        map.put("brand_name", strBrand);
                                        map.put("model_name", strModel);
                                        map.put("product_type", MasterCache.prProductType.get(MasterCache.userId.get(0)));
                                        map.put("warranty_id", MasterCache.listPosition_id);
                                       // map.put("purchase_date", strExtendedWPdate);
                                       // map.put("start_date", strExtendedWStartDate);
                                        map.put("purchase_from", strExtendedWProvider);
                                        map.put("ew_warranty_no", String.valueOf(MasterCache.listPosition_id));
                                        map.put("price", strExtendedWPrice);
                                        //map.put("currency_code", WarrantyExtendMonths);
                                        //map.put("invoice_no", MasterCache.invoiceNo.get(0));
                                        map.put("invoice_no" ,strExtendedWInvoice);
                                        map.put("company_id",String.valueOf(MasterCache.companyId.get(MasterCache.userId.get(0))) );
                                        map.put("additional_info", strAddInfo);
                                        map.put("warranty_months", strWarrantyExtendMonths);
//                                        map.put("country" ,strCountry);
//                                        map.put("warranty_type" ,strExtendedWCategory);
//                                        //map.put("warranty_no" ,strExtendedWNo);
//                                        map.put("upc_code" ,strUPCCode);
//                                        map.put("void_refund" ,strVoidRefund);
//                                        map.put("expiry_date" ,strExpDate);
//                                        map.put("registered_date" ,strRegDate);

                                        try {
                                            String data = NetUtils.getPostDataString(map);
                                            Log.i("Mylog","EWDData"+data);
                                            PostProductEdit(data);
                                            dialog.dismiss();
                                        } catch (UnsupportedEncodingException e) {
                                            e.printStackTrace();
                                        }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                    }
                                })
                                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        // do nothing
                                        dialog.dismiss();
                                    }
                                })
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
//end of alert dialog


                    }
                }
            });//On clickListener of txtedtbtn
        }//else end
    }//setTextfield end

    public void PostProductEdit(String data) throws JSONException {
        // HTTP POST
        String url = NetUtils.HOST + NetUtils.EXTENDED_WARRANTY_DETAILS_UPDATE_URL;
        Log.i("myLog", "Post_details_url:" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject(data);
        try {
            Log.i("myLog", "Data" + jsonObject);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // do something...
                    Log.i("myLog", "Success_details_post" + response);

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
        }catch (Exception e){}
    }

    }
