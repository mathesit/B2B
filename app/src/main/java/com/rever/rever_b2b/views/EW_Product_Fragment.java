package com.rever.rever_b2b.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
public class EW_Product_Fragment extends Fragment {
    private View rootView;
    private EditText edtSerialno,edtBrand,edtCountry,edtExtendedWNo,edtExtendedWPdate,edtExtendedWPrice,
            edtEWPpurFrom,edtExtendedWStartDate,edtUPCCode,edtModel,edtEWPBillno,
            edtProductType,edtVoidRefund,edtWarrantyExtendMonths,edtEmail,edtAlterEmail,edtFname,edtMname,
            edtLname,edtPassport,edtAdressl1,edtAdressl2,edtPostal,edtCity,edtState,edtMobile,edtHomePhone,edtCountry2,edtOfficePhone,edtExpDate,edtRegDate;
    private TextView txtedttop,txtedtbottom;
    private int id;

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
        if(MasterCache.PrewwarrId.size()==0){
            Toast.makeText(getContext(), "empty list in product details", Toast.LENGTH_SHORT).show();
        }else {
            id = MasterCache.PrewwarrId.get(0);
            if(MasterCache.prSerialno.get(id)!=null)
        edtSerialno.setText(MasterCache.prSerialno.get(id));

            if(MasterCache.prBrandname.get(id)!=null)
        edtBrand.setText(MasterCache.prBrandname.get(id));

            if(MasterCache.prUCountry.get(id)!=null)
        edtCountry.setText(MasterCache.prUCountry.get(id));

            if(MasterCache.prwarrNo.get(id)!=null)
        edtExtendedWNo.setText(MasterCache.prwarrNo.get(id));

            if(MasterCache.prPurDate.get(id)!=null)
        edtExtendedWPdate.setText(MasterCache.prPurDate.get(id));

            if(MasterCache.prPrice.get(id)!=null)
        edtExtendedWPrice.setText(MasterCache.prPrice.get(id));

            if(MasterCache.prPurFrom.get(id)!=null)
        edtEWPpurFrom.setText(MasterCache.prPurFrom.get(id));

            if(MasterCache.prWsDate.get(id)!=null)
        edtExtendedWStartDate.setText(MasterCache.prWsDate.get(id));

            if(MasterCache.prModelName.get(id)!=null)
        edtModel.setText(MasterCache.prModelName.get(id));

            if(MasterCache.prBill.get(id)!=null)
        edtEWPBillno.setText(MasterCache.prBill.get(id));

            if(MasterCache.prProductType.get(id)!=null)
        edtProductType.setText(MasterCache.prProductType.get(id));

            if(MasterCache.prWarrMonths.get(id)!=null)
        edtWarrantyExtendMonths.setText(MasterCache.prWarrMonths.get(id));

            if(MasterCache.prEmail.get(id)!=null)
        edtEmail.setText(MasterCache.prEmail.get(id));

            if(MasterCache.prAlterEmail.get(id)!=null)
        edtAlterEmail.setText(MasterCache.prAlterEmail.get(id));

            if(MasterCache.prSerialno.get(id)!=null)
        edtFname.setText(MasterCache.prFname.get(id));

            if(MasterCache.prMname.get(id)!=null)
        edtMname.setText(MasterCache.prMname.get(id));

            if(MasterCache.prLname.get(id)!=null)
        edtLname.setText(MasterCache.prLname.get(id));

            if(MasterCache.prAddLine1.get(id)!=null)
        edtAdressl1.setText(MasterCache.prAddLine1.get(id));

            if(MasterCache.prAddLine2.get(id)!=null)
        edtAdressl2.setText(MasterCache.prAddLine2.get(id));

            if(MasterCache.prPostal.get(id)!=null)
        edtPostal.setText(MasterCache.prPostal.get(id));

            if(MasterCache.prCity.get(id)!=null)
        edtCity.setText(MasterCache.prCity.get(id));

            if(MasterCache.prState.get(id)!=null)
        edtState.setText(MasterCache.prState.get(id));

            if(MasterCache.prUpcode.get(id)!=null)
        edtUPCCode.setText(MasterCache.prUpcode.get(id));

            if(MasterCache.prPassport.get(id)!=null)
        edtPassport.setText(MasterCache.prPassport.get(id));

            if(MasterCache.prVoidrefund.get(id)!=null)
        edtVoidRefund.setText(MasterCache.prVoidrefund.get(id));

            if(MasterCache.prMobile.get(id)!=null)
        edtMobile.setText(MasterCache.prMobile.get(id));

            if(MasterCache.prPhone.get(id)!=null)
        edtHomePhone.setText(MasterCache.prPhone.get(id));

            if(MasterCache.prOffiPhone.get(id)!=null)
        edtOfficePhone.setText(MasterCache.prOffiPhone.get(id));

            if(MasterCache.prExpDate.get(id)!=null)
        edtExpDate.setText(MasterCache.prExpDate.get(id));

            if(MasterCache.prRegDate.get(id)!=null)
        edtRegDate.setText(MasterCache.prRegDate.get(id));
        }

        txtedttop.setTag(1);
        txtedttop.setText("EDIT");
        txtedttop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final int status = (Integer) v.getTag();
                if (status == 1) {
                    txtedttop.setText("DONE");
                    v.setTag(0); //pause
                    edtEmail.setFocusableInTouchMode(true);
                    edtEmail.setClickable(true);
                    edtEmail.setFocusable(true);
                    edtEmail.setBackgroundResource(R.drawable.edittext_bg);

                    edtAlterEmail.setFocusableInTouchMode(true);
                    edtAlterEmail.setClickable(true);
                    edtAlterEmail.setFocusable(true);
                    edtAlterEmail.setBackgroundResource(R.drawable.edittext_bg);

                    edtFname.setFocusableInTouchMode(true);
                    edtFname.setClickable(true);
                    edtFname.setFocusable(true);
                    edtFname.setBackgroundResource(R.drawable.edittext_bg);

                    edtMname.setFocusableInTouchMode(true);
                    edtMname.setClickable(true);
                    edtMname.setFocusable(true);
                    edtMname.setBackgroundResource(R.drawable.edittext_bg);

                    edtLname.setFocusableInTouchMode(true);
                    edtLname.setClickable(true);
                    edtLname.setFocusable(true);
                    edtLname.setBackgroundResource(R.drawable.edittext_bg);
                    edtPassport.setFocusableInTouchMode(true);
                    edtPassport.setClickable(true);
                    edtPassport.setFocusable(true);
                    edtPassport.setBackgroundResource(R.drawable.edittext_bg);
                    edtAdressl1.setFocusableInTouchMode(true);
                    edtAdressl1.setClickable(true);
                    edtAdressl1.setFocusable(true);
                    edtAdressl1.setBackgroundResource(R.drawable.edittext_bg);
                    edtAdressl2.setFocusableInTouchMode(true);
                    edtAdressl2.setClickable(true);
                    edtAdressl2.setFocusable(true);
                    edtAdressl2.setBackgroundResource(R.drawable.edittext_bg);
                    edtPostal.setFocusableInTouchMode(true);
                    edtPostal.setClickable(true);
                    edtPostal.setFocusable(true);
                    edtPostal.setBackgroundResource(R.drawable.edittext_bg);
                    edtCity.setFocusableInTouchMode(true);
                    edtCity.setClickable(true);
                    edtCity.setFocusable(true);
                    edtCity.setBackgroundResource(R.drawable.edittext_bg);
                    edtState.setFocusableInTouchMode(true);
                    edtState.setClickable(true);
                    edtState.setFocusable(true);
                    edtState.setBackgroundResource(R.drawable.edittext_bg);
                    edtCountry.setFocusableInTouchMode(true);
                    edtCountry.setClickable(true);
                    edtCountry.setFocusable(true);
                    edtCountry.setBackgroundResource(R.drawable.edittext_bg);
                    edtMobile.setFocusableInTouchMode(true);
                    edtMobile.setClickable(true);
                    edtMobile.setFocusable(true);
                    edtMobile.setBackgroundResource(R.drawable.edittext_bg);
                    edtHomePhone.setFocusableInTouchMode(true);
                    edtHomePhone.setClickable(true);
                    edtHomePhone.setFocusable(true);
                    edtHomePhone.setBackgroundResource(R.drawable.edittext_bg);
                    edtOfficePhone.setFocusableInTouchMode(true);
                    edtOfficePhone.setClickable(true);
                    edtOfficePhone.setFocusable(true);
                    edtOfficePhone.setBackgroundResource(R.drawable.edittext_bg);

                } else {

//Start of alert dialog
                    new AlertDialog.Builder(getContext())
                            .setTitle("Update")
                            .setMessage("Update changes")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    txtedttop.setText("EDIT");
                                    v.setTag(1); //pause
                                    edtEmail.setFocusableInTouchMode(false);
                                    edtEmail.setClickable(false);
                                    edtEmail.setFocusable(false);
                                    edtEmail.setBackgroundResource(0);

                                    edtAlterEmail.setFocusableInTouchMode(false);
                                    edtAlterEmail.setClickable(false);
                                    edtAlterEmail.setFocusable(false);
                                    edtAlterEmail.setBackgroundResource(0);
                                    edtFname.setFocusableInTouchMode(false);
                                    edtFname.setClickable(false);
                                    edtFname.setFocusable(false);
                                    edtFname.setBackgroundResource(0);
                                    edtMname.setFocusableInTouchMode(false);
                                    edtMname.setClickable(false);
                                    edtMname.setFocusable(false);
                                    edtMname.setBackgroundResource(0);
                                    edtLname.setFocusableInTouchMode(false);
                                    edtLname.setClickable(false);
                                    edtLname.setFocusable(false);
                                    edtLname.setBackgroundResource(0);
                                    edtPassport.setFocusableInTouchMode(false);
                                    edtPassport.setClickable(false);
                                    edtPassport.setFocusable(false);
                                    edtPassport.setBackgroundResource(0);
                                    edtAdressl1.setFocusableInTouchMode(false);
                                    edtAdressl1.setClickable(false);
                                    edtAdressl1.setFocusable(false);
                                    edtAdressl1.setBackgroundResource(0);
                                    edtAdressl2.setFocusableInTouchMode(false);
                                    edtAdressl2.setClickable(false);
                                    edtAdressl2.setFocusable(false);
                                    edtAdressl2.setBackgroundResource(0);
                                    edtPostal.setFocusableInTouchMode(false);
                                    edtPostal.setClickable(false);
                                    edtPostal.setFocusable(false);
                                    edtPostal.setBackgroundResource(0);
                                    edtCity.setFocusableInTouchMode(false);
                                    edtCity.setClickable(false);
                                    edtCity.setFocusable(false);
                                    edtCity.setBackgroundResource(0);
                                    edtState.setFocusableInTouchMode(false);
                                    edtState.setClickable(false);
                                    edtState.setFocusable(false);
                                    edtState.setBackgroundResource(0);
                                    edtCountry.setFocusableInTouchMode(false);
                                    edtCountry.setClickable(false);
                                    edtCountry.setFocusable(false);
                                    edtCountry.setBackgroundResource(0);
                                    edtMobile.setFocusableInTouchMode(false);
                                    edtMobile.setClickable(false);
                                    edtMobile.setFocusable(false);
                                    edtMobile.setBackgroundResource(0);
                                    edtHomePhone.setFocusableInTouchMode(false);
                                    edtHomePhone.setClickable(false);
                                    edtHomePhone.setFocusable(false);
                                    edtHomePhone.setBackgroundResource(0);
                                    edtOfficePhone.setFocusableInTouchMode(false);
                                    edtOfficePhone.setClickable(false);
                                    edtOfficePhone.setFocusable(false);
                                    edtOfficePhone.setBackgroundResource(0);

                                    //getText and post

                                    String email = edtEmail.getText().toString();
                                    String altemail = edtAlterEmail.getText().toString();
                                    String name = edtFname.getText().toString();
                                    String mname = edtMname.getText().toString();
                                    String lname = edtLname.getText().toString();
                                    String passport = edtPassport.getText().toString();
                                    String adressl1 = edtAdressl1.getText().toString();
                                    String adressl2 = edtAdressl2.getText().toString();
                                    String postal = edtPostal.getText().toString();
                                    String city = edtCity.getText().toString();
                                    String state = edtState.getText().toString();
                                    String country = edtCountry.getText().toString();
                                    String mobile = edtMobile.getText().toString();
                                    String homePhone = edtHomePhone.getText().toString();
                                    String officePhone = edtOfficePhone.getText().toString();

                                    String Serialno = edtSerialno.getText().toString();
                                    String Brand = edtBrand.getText().toString();
                                    String Country2 = edtCountry2.getText().toString();
                                    String ExtendedWNo = edtExtendedWNo.getText().toString();
                                    String ExtendedWPdate = edtExtendedWPdate.getText().toString();
                                    String ExtendedWPrice = edtExtendedWPrice.getText().toString();
                                    String EWPpurFrom = edtEWPpurFrom.getText().toString();
                                    String EWPBillno = edtEWPBillno.getText().toString();
                                    String ExtendedWStartDate = edtExtendedWStartDate.getText().toString();
                                    String Model = edtModel.getText().toString();
                                    String ProductType = edtProductType.getText().toString();
                                    String VoidRefund = edtVoidRefund.getText().toString();
                                    String WarrantyExtendMonths = edtWarrantyExtendMonths.getText().toString();
                                    String ExpDate = edtExpDate.getText().toString();
                                    String RegDate = edtRegDate.getText().toString();

                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("consumer_id",MasterCache.consumerId.get(MasterCache.warrId.get(0)));
                                    map.put("city", city);
                                    //map.put("title",String.valueOf(MasterCache.listPosition_id));
                                    map.put("address_line", adressl1);
                                    map.put("mobile", mobile);
                                    map.put("postal_code", postal);
                                    map.put("first_name", name);
                                    map.put("middle_name", mname);
                                    map.put("last_name", lname);
                                    map.put("address_line2", adressl2);
                                    map.put("state", state);
                                    //map.put("country_code",MasterCache.userCountryCode.get(MasterCache.userId.get(0)));
                                    map.put("ic_no", String.valueOf(MasterCache.prIcNo.get(0)));
                                    map.put("eq_stock_id", MasterCache.eq_stockId.get(MasterCache.warrId.get(0)));
                                    Log.i("My Log eqStock", "EqStock" + MasterCache.eq_stockId.get(MasterCache.warrId.get(0)));
                                    map.put("serial_no", Serialno);
                                    map.put("brand_name", Brand);
                                    map.put("model_name", Model);
                                    map.put("product_type", ProductType);
                                    map.put("warranty_id", MasterCache.listPosition_id);
                                    map.put("purchase_date", ExtendedWPdate);
                                    map.put("start_date", ExtendedWStartDate);
                                    map.put("purchase_from", EWPpurFrom);
                                    map.put("ew_warranty_no", String.valueOf(MasterCache.listPosition_id));
                                    map.put("price", ExtendedWPrice);
                                    map.put("currency_code", WarrantyExtendMonths);
                                    map.put("invoice_no", MasterCache.invoiceNo.get(0));
                                    map.put("company_id",String.valueOf(MasterCache.companyId.get(MasterCache.userId.get(0))) );
                                    map.put("additional_info", email);
                                    map.put("warranty_months", WarrantyExtendMonths);


                                    try {
                                        String data = NetUtils.getPostDataString(map);
//
                                        PostProductEdit(data);
                                        dialog.dismiss();
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
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
        });

        txtedtbottom.setTag(1);
        txtedtbottom.setText("EDIT");
        txtedtbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final int status = (Integer) v.getTag();
                if (status == 1) {
                    txtedtbottom.setText("DONE");
                    v.setTag(0); //pause

//                    edtSerialno.setFocusableInTouchMode(true);
//                    edtSerialno.setClickable(true);
//                    edtSerialno.setFocusable(true);
//                    edtSerialno.setBackgroundResource(R.drawable.edittext_bg);
//                    edtBrand.setFocusableInTouchMode(true);
//                    edtBrand.setClickable(true);
//                    edtBrand.setFocusable(true);
//                    edtBrand.setBackgroundResource(R.drawable.edittext_bg);

                    edtCountry2.setFocusableInTouchMode(true);
                    edtCountry2.setClickable(true);
                    edtCountry2.setFocusable(true);
                    edtCountry2.setBackgroundResource(R.drawable.edittext_bg);
                    edtExtendedWNo.setFocusableInTouchMode(true);
                    edtExtendedWNo.setClickable(true);
                    edtExtendedWNo.setFocusable(true);
                    edtExtendedWNo.setBackgroundResource(R.drawable.edittext_bg);
                    edtExtendedWPdate.setFocusableInTouchMode(true);
                    edtExtendedWPdate.setClickable(true);
                    edtExtendedWPdate.setFocusable(true);
                    edtExtendedWPdate.setBackgroundResource(R.drawable.edittext_bg);
                    edtExtendedWPrice.setFocusableInTouchMode(true);
                    edtExtendedWPrice.setClickable(true);
                    edtExtendedWPrice.setFocusable(true);
                    edtExtendedWPrice.setBackgroundResource(R.drawable.edittext_bg);
                    edtEWPpurFrom.setFocusableInTouchMode(true);
                    edtEWPpurFrom.setClickable(true);
                    edtEWPpurFrom.setFocusable(true);
                    edtEWPpurFrom.setBackgroundResource(R.drawable.edittext_bg);
                    edtEWPBillno.setFocusableInTouchMode(true);
                    edtEWPBillno.setClickable(true);
                    edtEWPBillno.setFocusable(true);
                    edtEWPBillno.setBackgroundResource(R.drawable.edittext_bg);

                    edtExtendedWStartDate.setFocusableInTouchMode(true);
                    edtExtendedWStartDate.setClickable(true);
                    edtExtendedWStartDate.setFocusable(true);
                    edtExtendedWStartDate.setBackgroundResource(R.drawable.edittext_bg);
//                    edtModel.setFocusableInTouchMode(true);
//                    edtModel.setClickable(true);
//                    edtModel.setFocusable(true);
//                    edtModel.setBackgroundResource(R.drawable.edittext_bg);
//                    edtProductType.setFocusableInTouchMode(true);
//                    edtProductType.setClickable(true);
//                    edtProductType.setFocusable(true);
//                    edtProductType.setBackgroundResource(R.drawable.edittext_bg);
                    edtVoidRefund.setFocusableInTouchMode(true);
                    edtVoidRefund.setClickable(true);
                    edtVoidRefund.setFocusable(true);
                    edtVoidRefund.setBackgroundResource(R.drawable.edittext_bg);
                    edtWarrantyExtendMonths.setFocusableInTouchMode(true);
                    edtWarrantyExtendMonths.setClickable(true);
                    edtWarrantyExtendMonths.setFocusable(true);
                    edtWarrantyExtendMonths.setBackgroundResource(R.drawable.edittext_bg);
                    edtExpDate.setFocusableInTouchMode(true);
                    edtExpDate.setClickable(true);
                    edtExpDate.setFocusable(true);
                    edtExpDate.setBackgroundResource(R.drawable.edittext_bg);
                    edtRegDate.setFocusableInTouchMode(true);
                    edtRegDate.setClickable(true);
                    edtRegDate.setFocusable(true);
                    edtRegDate.setBackgroundResource(R.drawable.edittext_bg);
                } else {

//Start of alert dialog
                    new AlertDialog.Builder(getContext())
                            .setTitle("Update")
                            .setMessage("Update changes")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    txtedtbottom.setText("EDIT");
                                    v.setTag(1); //pause
                                    edtSerialno.setFocusableInTouchMode(false);
                                    edtSerialno.setClickable(false);
                                    edtSerialno.setFocusable(false);
                                    edtSerialno.setBackgroundResource(0);
                                    edtBrand.setFocusableInTouchMode(false);
                                    edtBrand.setClickable(false);
                                    edtBrand.setFocusable(false);
                                    edtBrand.setBackgroundResource(0);
                                    edtCountry2.setFocusableInTouchMode(false);
                                    edtCountry2.setClickable(false);
                                    edtCountry2.setFocusable(false);
                                    edtCountry2.setBackgroundResource(0);
                                    edtExtendedWNo.setFocusableInTouchMode(false);
                                    edtExtendedWNo.setClickable(false);
                                    edtExtendedWNo.setFocusable(false);
                                    edtExtendedWNo.setBackgroundResource(0);
                                    edtExtendedWPdate.setFocusableInTouchMode(false);
                                    edtExtendedWPdate.setClickable(false);
                                    edtExtendedWPdate.setFocusable(false);
                                    edtExtendedWPdate.setBackgroundResource(0);
                                    edtExtendedWPrice.setFocusableInTouchMode(false);
                                    edtExtendedWPrice.setClickable(false);
                                    edtExtendedWPrice.setFocusable(false);
                                    edtExtendedWPrice.setBackgroundResource(0);
                                    edtEWPpurFrom.setFocusableInTouchMode(false);
                                    edtEWPpurFrom.setClickable(false);
                                    edtEWPpurFrom.setFocusable(false);
                                    edtEWPpurFrom.setBackgroundResource(0);
                                    edtEWPBillno.setFocusableInTouchMode(false);
                                    edtEWPBillno.setClickable(false);
                                    edtEWPBillno.setFocusable(false);
                                    edtEWPBillno.setBackgroundResource(0);

                                    edtExtendedWStartDate.setFocusableInTouchMode(false);
                                    edtExtendedWStartDate.setClickable(false);
                                    edtExtendedWStartDate.setFocusable(false);
                                    edtExtendedWStartDate.setBackgroundResource(0);
                                    edtModel.setFocusableInTouchMode(false);
                                    edtModel.setClickable(false);
                                    edtModel.setFocusable(false);
                                    edtModel.setBackgroundResource(0);
                                    edtProductType.setFocusableInTouchMode(false);
                                    edtProductType.setClickable(false);
                                    edtProductType.setFocusable(false);
                                    edtProductType.setBackgroundResource(0);
                                    edtVoidRefund.setFocusableInTouchMode(false);
                                    edtVoidRefund.setClickable(false);
                                    edtVoidRefund.setFocusable(false);
                                    edtVoidRefund.setBackgroundResource(0);
                                    edtWarrantyExtendMonths.setFocusableInTouchMode(false);
                                    edtWarrantyExtendMonths.setClickable(false);
                                    edtWarrantyExtendMonths.setFocusable(false);
                                    edtWarrantyExtendMonths.setBackgroundResource(0);
                                    edtExpDate.setFocusableInTouchMode(false);
                                    edtExpDate.setClickable(false);
                                    edtExpDate.setFocusable(false);
                                    edtExpDate.setBackgroundResource(0);
                                    edtRegDate.setFocusableInTouchMode(false);
                                    edtRegDate.setClickable(false);
                                    edtRegDate.setFocusable(false);
                                    edtRegDate.setBackgroundResource(0);

                                    //getText and post

                                    String email = edtEmail.getText().toString();
                                    String altemail = edtAlterEmail.getText().toString();
                                    String name = edtFname.getText().toString();
                                    String mname = edtMname.getText().toString();
                                    String lname = edtLname.getText().toString();
                                    String passport = edtPassport.getText().toString();
                                    String adressl1 = edtAdressl1.getText().toString();
                                    String adressl2 = edtAdressl2.getText().toString();
                                    String postal = edtPostal.getText().toString();
                                    String city = edtCity.getText().toString();
                                    String state = edtState.getText().toString();
                                    String country = edtCountry.getText().toString();
                                    String mobile = edtMobile.getText().toString();
                                    String homePhone = edtHomePhone.getText().toString();
                                    String officePhone = edtOfficePhone.getText().toString();

                                    String Serialno = edtSerialno.getText().toString();
                                    String Brand = edtBrand.getText().toString();
                                    String Country2 = edtCountry2.getText().toString();
                                    String ExtendedWNo = edtExtendedWNo.getText().toString();
                                    String ExtendedWPdate = edtExtendedWPdate.getText().toString();
                                    String ExtendedWPrice = edtExtendedWPrice.getText().toString();
                                    String EWPpurFrom = edtEWPpurFrom.getText().toString();
                                    String EWPBillno = edtEWPBillno.getText().toString();
                                    String ExtendedWStartDate = edtExtendedWStartDate.getText().toString();
                                    String Model = edtModel.getText().toString();
                                    String ProductType = edtProductType.getText().toString();
                                    String VoidRefund = edtVoidRefund.getText().toString();
                                    String WarrantyExtendMonths = edtWarrantyExtendMonths.getText().toString();
                                    String ExpDate = edtExpDate.getText().toString();
                                    String RegDate = edtRegDate.getText().toString();

                                    HashMap<String, String> map = new HashMap<>();
                                    map.put("consumer_id",MasterCache.consumerId.get(MasterCache.warrId.get(0)));
                                    map.put("city", city);
                                    //map.put("title",String.valueOf(MasterCache.listPosition_id));
                                    map.put("address_line", adressl1);
                                    map.put("mobile", mobile);
                                    map.put("postal_code", postal);
                                    map.put("first_name", name);
                                    map.put("last_name", lname);
                                    map.put("address_line2", adressl2);
                                    map.put("state", state);
                                    //map.put("country_code",MasterCache.userCountryCode.get(MasterCache.userId.get(0)));
                                    map.put("ic_no", String.valueOf(MasterCache.prIcNo.get(0)));
                                    map.put("eq_stock_id", MasterCache.eq_stockId.get(MasterCache.warrId.get(0)));
                                    Log.i("", "EqStock" + MasterCache.eq_stockId.get(MasterCache.warrId.get(0)));
                                    map.put("serial_no", Serialno);
                                    map.put("brand_name", Brand);
                                    map.put("model_name", Model);
                                    map.put("product_type", ProductType);
                                    map.put("warranty_id", MasterCache.listPosition_id);
                                    map.put("purchase_date", ExtendedWPdate);
                                    map.put("start_date", ExtendedWStartDate);
                                    map.put("purchase_from", EWPpurFrom);
                                    map.put("ew_warranty_no", String.valueOf(MasterCache.listPosition_id));
                                    map.put("price", ExtendedWPrice);
                                    map.put("currency_code", WarrantyExtendMonths);
                                    map.put("invoice_no", MasterCache.invoiceNo.get(0));
                                    map.put("company_id",String.valueOf(MasterCache.companyId.get(MasterCache.userId.get(0))) );
                                    map.put("additional_info", email);
                                    map.put("warranty_months", WarrantyExtendMonths);


                                    try {
                                        String data = NetUtils.getPostDataString(map);
//
                                        PostProductEdit(data);

                                        dialog.dismiss();
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
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
        });
    }

    public void PostProductEdit(String data) throws JSONException {
        // HTTP POST
        String url = NetUtils.HOST + NetUtils.EXTENDED_WARRANTY_PRODUCT_UPDATE_URL;
        Log.i("myLog", "Post_product_url:" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject(data);
        try {
            Log.i("myLog", "Data" + jsonObject);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    // do something...
                    Log.i("myLog", "Success_product_post" + response);
                    GetEwproductTask(MasterCache.listPosition_id);
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

    public void GetEwproductTask(String str) {
        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_PRODUCT_DETAILS_URL + str;
        Log.i("myLog", "producturl : " + url);
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        Log.i("myLog", "ProdResponse" + response.toString());

                        MasterCache.saveEWProductDetailsTab(response);
                        setTextFields();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // do something...
                        Log.i("myLog", "Load Product Details Error Response");
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
        Volley.newRequestQueue(getActivity()).add(jsonRequest);
    }
}
