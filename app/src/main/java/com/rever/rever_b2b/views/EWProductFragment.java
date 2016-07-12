package com.rever.rever_b2b.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rever.rever_b2b.R;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bharath on 6/3/2016.
 */
public class EWProductFragment extends Fragment {
    private View rootView;
    private EditText edtSerialno,edtBrand,edtCountry,edtExtendedWNo,edtExtendedWPdate,edtExtendedWPrice,
            edtEWPpurFrom,edtExtendedWStartDate,edtUPCCode,edtModel,edtEWPBillno,
            edtProductType,edtVoidRefund,edtWarrantyExtendMonths,edtEmail,edtAlterEmail,edtFname,edtMname,
            edtLname,edtPassport,edtAdressl1,edtAdressl2,edtPostal,edtCity,edtState,edtMobile,edtHomePhone,edtCountry2,edtOfficePhone,edtExpDate,edtRegDate;
    private TextView txtedttop,txtedtbottom;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_product_details, container, false);
        initView();
        return rootView;

    }

    public void initView(){
        txtedttop = (TextView) rootView.findViewById(R.id.txtedttop);
        txtedtbottom = (TextView) rootView.findViewById(R.id.txtedtbottom);
        edtEmail = (EditText) rootView.findViewById(R.id.edtEWPemail);
        edtAlterEmail = (EditText) rootView.findViewById(R.id.edtEWPalternateEmail);
        edtFname = (EditText) rootView.findViewById(R.id.edtEWPfname);
        edtMname = (EditText) rootView.findViewById(R.id.edtEWPmname);
        edtLname = (EditText) rootView.findViewById(R.id.edtEWPlname);
        edtPassport = (EditText) rootView.findViewById(R.id.edtEWPpassportno);
        edtAdressl1 = (EditText) rootView.findViewById(R.id.edtEWPaddressL1);
        edtAdressl2 = (EditText) rootView.findViewById(R.id.edtEWPaddressL2);
        edtPostal = (EditText) rootView.findViewById(R.id.edtEWPpostal);
        edtCity = (EditText) rootView.findViewById(R.id.edtEWPcity);
        edtState = (EditText) rootView.findViewById(R.id.edtEWPstate);
        edtCountry = (EditText) rootView.findViewById(R.id.edtEWPcountry);
        edtMobile = (EditText) rootView.findViewById(R.id.edtEWPmobile);
        edtHomePhone = (EditText) rootView.findViewById(R.id.edtEWPhomephone);
        edtOfficePhone = (EditText) rootView.findViewById(R.id.edtEWPofficephone);

        edtSerialno = (EditText) rootView.findViewById(R.id.edtEWPSerialNo);
        edtBrand = (EditText) rootView.findViewById(R.id.edtEWPbrand);
        edtCountry2 = (EditText) rootView.findViewById(R.id.edtEWPprodcountry2);
        edtExtendedWNo = (EditText) rootView.findViewById(R.id.edtEWPwarrantyno);
        edtExtendedWPdate = (EditText) rootView.findViewById(R.id.edtEWPpurDate);
        edtExtendedWPrice = (EditText) rootView.findViewById(R.id.edtEWPprice);
        edtEWPpurFrom  = (EditText) rootView.findViewById(R.id.edtEWPpurFrom);
        edtEWPBillno = (EditText) rootView.findViewById(R.id.edtEWPbill);
        edtExtendedWStartDate = (EditText) rootView.findViewById(R.id.edtEWPsDate);
        edtModel = (EditText) rootView.findViewById(R.id.edtEWPmodel);
        edtProductType = (EditText) rootView.findViewById(R.id.edtEWPprodtype);
        edtVoidRefund = (EditText) rootView.findViewById(R.id.edtEWPvoidrefund);
        edtWarrantyExtendMonths = (EditText) rootView.findViewById(R.id.edtEWPwarrantyMonths);
        edtExpDate=(EditText) rootView.findViewById(R.id.edtEWPexpiryDate);
        edtRegDate=(EditText) rootView.findViewById(R.id.edtEWPregDate);
        setTextFields();
    }
    private void setTextFields() {

        int id= MasterCache.PrewwarrId.get(0);

        edtSerialno.setText(MasterCache.prSerialno.get(id));
        edtBrand.setText(MasterCache.prBrandname.get(id));
        edtCountry.setText(MasterCache.prUCountry.get(id));
        edtExtendedWNo.setText(MasterCache.prwarrNo.get(id));
        edtExtendedWPdate.setText(MasterCache.prPurDate.get(id));
        edtExtendedWPrice.setText(MasterCache.prPrice.get(id));
        edtEWPpurFrom.setText(MasterCache.prPurFrom.get(id));
        edtExtendedWStartDate.setText(MasterCache.prWsDate.get(id));
        //edtUPCCode.setText(MasterCache.pr.get(id));
        edtModel.setText(MasterCache.prModelName.get(id));
        edtEWPBillno.setText(MasterCache.prBill.get(id));
        edtProductType.setText(MasterCache.prProductType.get(id));
        //edtVoidRefund.setText(MasterCache.pr.get(id));
        edtWarrantyExtendMonths.setText(MasterCache.prWarrMonths.get(id));
        edtEmail.setText(MasterCache.prEmail.get(id));
        edtAlterEmail.setText(MasterCache.prAlterEmail.get(id));
        edtFname.setText(MasterCache.prFname.get(id));
        edtMname.setText(MasterCache.prMname.get(id));
        edtLname.setText(MasterCache.prLname.get(id));
        //edtPassport.setText(MasterCache.pr.get(id));
        edtAdressl1.setText(MasterCache.prAddLine1.get(id));
        edtAdressl2.setText(MasterCache.prAddLine2.get(id));
        edtPostal.setText(MasterCache.prPostal.get(id));
        edtCity.setText(MasterCache.prCity.get(id));
        //edtState.setText(MasterCache.prState.get(id));
        edtMobile.setText(MasterCache.prMobile.get(id));
        edtHomePhone.setText(MasterCache.prPhone.get(id));
        edtOfficePhone.setText(MasterCache.prOffiPhone.get(id));
        edtExpDate.setText(MasterCache.prExpDate.get(id));
        edtRegDate.setText(MasterCache.prRegDate.get(id));


        txtedttop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtedttop.setText("Done");

                edtEmail.setFocusableInTouchMode(true);
                edtEmail.setBackgroundResource(R.drawable.edittext_bg);
                edtAlterEmail.setFocusableInTouchMode(true);
                edtAlterEmail.setBackgroundResource(R.drawable.edittext_bg);
                edtFname.setFocusableInTouchMode(true);
                edtFname.setBackgroundResource(R.drawable.edittext_bg);
                edtMname.setFocusableInTouchMode(true);
                edtMname.setBackgroundResource(R.drawable.edittext_bg);
                edtLname.setFocusableInTouchMode(true);
                edtLname.setBackgroundResource(R.drawable.edittext_bg);
                edtPassport.setFocusableInTouchMode(true);
                edtPassport.setBackgroundResource(R.drawable.edittext_bg);
                edtAdressl1.setFocusableInTouchMode(true);
                edtAdressl1.setBackgroundResource(R.drawable.edittext_bg);
                edtAdressl2.setFocusableInTouchMode(true);
                edtAdressl2.setBackgroundResource(R.drawable.edittext_bg);
                edtPostal.setFocusableInTouchMode(true);
                edtPostal.setBackgroundResource(R.drawable.edittext_bg);
                edtCity.setFocusableInTouchMode(true);
                edtCity.setBackgroundResource(R.drawable.edittext_bg);
                edtState.setFocusableInTouchMode(true);
                edtState.setBackgroundResource(R.drawable.edittext_bg);
                edtCountry.setFocusableInTouchMode(true);
                edtCountry.setBackgroundResource(R.drawable.edittext_bg);
                edtMobile.setFocusableInTouchMode(true);
                edtMobile.setBackgroundResource(R.drawable.edittext_bg);
                edtHomePhone.setFocusableInTouchMode(true);
                edtHomePhone.setBackgroundResource(R.drawable.edittext_bg);
                edtOfficePhone.setFocusableInTouchMode(true);
                edtOfficePhone.setBackgroundResource(R.drawable.edittext_bg);


            }
        });

        txtedtbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txtedtbottom.setText("Done");

                edtSerialno.setFocusableInTouchMode(true);
                edtSerialno.setBackgroundResource(R.drawable.edittext_bg);
                edtBrand.setFocusableInTouchMode(true);
                edtBrand.setBackgroundResource(R.drawable.edittext_bg);
                edtCountry2.setFocusableInTouchMode(true);
                edtCountry2.setBackgroundResource(R.drawable.edittext_bg);
                edtExtendedWNo.setFocusableInTouchMode(true);
                edtExtendedWNo.setBackgroundResource(R.drawable.edittext_bg);
                edtExtendedWPdate.setFocusableInTouchMode(true);
                edtExtendedWPdate.setBackgroundResource(R.drawable.edittext_bg);
                edtExtendedWPrice.setFocusableInTouchMode(true);
                edtExtendedWPrice.setBackgroundResource(R.drawable.edittext_bg);
                edtEWPpurFrom.setFocusableInTouchMode(true);
                edtEWPpurFrom.setBackgroundResource(R.drawable.edittext_bg);
                edtEWPBillno.setFocusableInTouchMode(true);
                edtEWPBillno.setBackgroundResource(R.drawable.edittext_bg);

                edtExtendedWStartDate.setFocusableInTouchMode(true);
                edtExtendedWStartDate.setBackgroundResource(R.drawable.edittext_bg);
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
