package com.rever.rever_b2b.views;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bharath on 6/3/2016.
 */
 public class EWDetailsFragment extends Fragment {
    private View rootView;
    private EditText edtSerialno,edtBrand,edtCountry,edtExtendedWCategory,
            edtExtendedWInvoice,edtExtendedWNo,edtExtendedWPdate,edtExtendedWPrice,
            edtExtendedWProvider,edtExtendedWStartDate,edtUPCCode,edtModel,
            edtProductType,edtVoidRefund,edtWarrantyExtendMonths,edtExpDate,edtRegDate;
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

    }


    public void Settextfields(){
        int id= MasterCache.warrId.get(0);
        edtSerialno.setText(MasterCache.pserialNo.get(id));
        edtBrand.setText(MasterCache.pbrandname.get(id));
        edtCountry.setText(MasterCache.countryCode.get(id));
        edtExtendedWCategory.setText(MasterCache.warrType.get(id));
        edtExtendedWInvoice.setText(MasterCache.invoiceNo.get(id));
        edtExtendedWNo.setText(MasterCache.warrNo.get(id));
        edtExtendedWPdate.setText(MasterCache.ppurchaseDate.get(id));
        edtExtendedWPrice.setText(MasterCache.warrSellingPrice.get(id));
        edtExtendedWProvider.setText(MasterCache.providerCompName.get(id));
        edtExtendedWStartDate.setText(MasterCache.warrSdate.get(id));
        //edtUPCCode.setText(MasterCache..get(id));
        edtModel.setText(MasterCache.modelName.get(id));
        edtProductType.setText(MasterCache.productType.get(id));
        //edtVoidRefund.setText(MasterCache..get(id));
        edtWarrantyExtendMonths.setText(MasterCache.warrMonths.get(id));
        edtExpDate.setText(MasterCache.warrExpDate.get(id));
        edtRegDate.setText(MasterCache.ew_regDate.get(id));




        txteditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txteditbtn.setText("Done");

                edtSerialno.setFocusableInTouchMode(true);
                edtSerialno.setBackgroundResource(R.drawable.edittext_bg);

                edtBrand.setFocusableInTouchMode(true);
                edtBrand.setBackgroundResource(R.drawable.edittext_bg);

                edtCountry.setFocusableInTouchMode(true);
                edtCountry.setBackgroundResource(R.drawable.edittext_bg);

                edtExtendedWCategory.setFocusableInTouchMode(true);
                edtExtendedWCategory.setBackgroundResource(R.drawable.edittext_bg);

                edtExtendedWInvoice.setFocusableInTouchMode(true);
                edtExtendedWInvoice.setBackgroundResource(R.drawable.edittext_bg);

                edtExtendedWNo.setFocusableInTouchMode(true);
                edtExtendedWNo.setBackgroundResource(R.drawable.edittext_bg);

                edtExtendedWPdate.setFocusableInTouchMode(true);
                edtExtendedWPdate.setBackgroundResource(R.drawable.edittext_bg);

                edtExtendedWPrice.setFocusableInTouchMode(true);
                edtExtendedWPrice.setBackgroundResource(R.drawable.edittext_bg);

                edtExtendedWProvider.setFocusableInTouchMode(true);
                edtExtendedWProvider.setBackgroundResource(R.drawable.edittext_bg);

                edtExtendedWStartDate.setFocusableInTouchMode(true);
                edtExtendedWStartDate.setBackgroundResource(R.drawable.bordercolor_blue);

                edtUPCCode.setFocusableInTouchMode(true);
                edtUPCCode.setBackgroundResource(R.drawable.edittext_bg);

                edtModel.setFocusableInTouchMode(true);
                edtModel.setBackgroundResource(R.drawable.edittext_bg);

                edtProductType.setFocusableInTouchMode(true);
                edtProductType.setBackgroundResource(R.drawable.edittext_bg);

                edtVoidRefund.setFocusableInTouchMode(true);
                edtVoidRefund.setBackgroundResource(R.drawable.edittext_bg);

                edtWarrantyExtendMonths.setFocusableInTouchMode(true);
                edtWarrantyExtendMonths.setBackgroundResource(R.drawable.edittext_bg);

                edtExpDate.setFocusableInTouchMode(true);
                edtExpDate.setBackgroundResource(R.drawable.edittext_bg);

                edtRegDate.setFocusableInTouchMode(true);
                edtRegDate.setBackgroundResource(R.drawable.edittext_bg);
            }

        });

    }
 }