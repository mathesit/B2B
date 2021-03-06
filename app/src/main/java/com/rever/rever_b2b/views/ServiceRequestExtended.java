package com.rever.rever_b2b.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rever.rever_b2b.R;
import com.rever.rever_b2b.model.ServiceDetails;
import com.rever.rever_b2b.utils.JsonUtils;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Oviya on 6/8/2016.
 */

    public class ServiceRequestExtended extends Fragment implements View.OnClickListener {
    private View rootView;
    private LinearLayout linearSR, linearUpdate, linearStatus, linearRadio, linearEdit;
    private TextView service, product;
    private ListView listView, listSearch;
    private TextView txtServiceNo, txtCreatedBy, txtCreatedDate, txtFailureDesc, txtSerialNo, txtBrand, txtProdType,
            txtModel, txtWarrantyStatus, txtPastServiceReturns, txtEmail, txtCountry, txtFirstName, txtLastName, txtCity,
            txtState, txtAddress, txtPostalCode, txtIcPassportNo, txtMobile, txtServiceCentre, txtFailDesc, txtRemarks, txtStatus;

    private TextView emailText, altEmailText, firstNameText, middleNameText, lastNameText, icText, address1Text, address2Text, postalCodeText,
            cityText, stateText, countryText, mobileText, homePhoneText, officePhoneText, serialNoText, billNoText, brandText, warrantyNoText,
            prodTypeText, warrantyMonthsText, modelText, warranytyExpText, purchaseOnText, regDateText, purchaseFromText, prodCostText, countryProdText, warrantyStartText;

    private TextView edtStatus, edtCustomer, edtDetails, btnUpdate, btnUpclose, txtViewQuote;
    private EditText edtCity, edtContact, edtAddress, edtPostalCode, edtFailure, edtRemarks, editFilter;
    private RadioGroup group;
    private RadioButton btnNotYet, btnReceived, btnOnsite;
   // private Spinner searchSpin;
    private String sr_id = null;
    private AlertDialog.Builder alertDialog;
    private Dialog dialog;
    private Spinner spinSelect;
    private ImageView imgClose;
    CustomList listAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_service, container, false);
        initViews();
        showAllService("");
        showServiceReqDetails();
        return rootView;

    }

    public void initViews() {
        linearSR = (LinearLayout) rootView.findViewById(R.id.LinearServiceDetails);
        service = (TextView) rootView.findViewById(R.id.buttonService);
        product = (TextView) rootView.findViewById(R.id.buttonProduct);
        listView = (ListView) rootView.findViewById(R.id.listInServReq);
        service.setOnClickListener(this);
        product.setOnClickListener(this);

        imgClose = (ImageView) rootView.findViewById(R.id.imgCloseInServReq);
        spinSelect = (Spinner) rootView.findViewById(R.id.edtSearchInServReq);
        editFilter = (EditText)rootView.findViewById(R.id.editFilter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("myLog", "position:" + position);
                sr_id = MasterCache.SrId.get(position);
                Log.i("myLog", "sr_id:" + sr_id);
                showServiceReqDetails();
                loadServiceRequest(sr_id);
                    if (product.isPressed()) {
                        showProductDetails();
                        loadProductDetails(sr_id);
                    }
            }
        });
        ArrayList<String> al = new ArrayList<>();
        al.add("SR Number"); al.add("Brand"); al.add("Model"); al.add("Serial No."); al.add("Status");
        al.add("Consumer Email ID"); al.add("IC/Passport No.");
        ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, al );
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinSelect.setAdapter(adp);
        spinSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editFilter.setHint(parent.getSelectedItem().toString());
                editFilter.setText("");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        editFilter.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String text = editFilter.getText().toString();
                    String hint = editFilter.getHint().toString();
                    Log.i("myLog", "Hint:" + hint + "  text:" + text);

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    String key = null;
                    if (hint.equalsIgnoreCase("SR Number"))
                        key = "sr_no";
                    else if (hint.equalsIgnoreCase("Brand"))
                        key = "brand";
                    else if (hint.equalsIgnoreCase("Model"))
                        key = "model";
                    else if (hint.equalsIgnoreCase("Serial No."))
                        key = "serial_no";
                    else if (hint.equalsIgnoreCase("Status"))
                        key = "status";
                    else if (hint.equalsIgnoreCase("Consumer Email ID"))
                        key = "email_id";
                    else if (hint.equalsIgnoreCase("IC/Passport No."))
                        key = "ic";
                    if (key == null)
                        key = hint;
                    searchService(key, text);
                    return true;
                }
                return false;
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editFilter.setText("");
                editFilter.setHint("Search");
                showAllService("Close");
            }
        });
    }

    public void showAllService(final String option) {
        String url = NetUtils.HOST + NetUtils.EW_SERVICE_DETAILS_LIST;
        Log.i("myLog", "Post url:" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page_no", "1");
            jsonObject.put("count", "5");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("myLog", "Success Response");
                    Log.i("myLog", "Success Response:" + response.toString());
                    MasterCache.saveServiceListCache(response);
                    if(option.equalsIgnoreCase("Close")){
                        listAdapter.notifyDataSetChanged();
                    }else {
                        listAdapter = new CustomList(getActivity(), MasterCache.SrNo, MasterCache.SrStatus, MasterCache.CreatedOn, MasterCache.Consumer);
                        listView.setAdapter(listAdapter);
                      listView.performItemClick(listView.getAdapter().getView(1, null, null),
                                1, listView.getAdapter().getItemId(1));


                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("myLog","Error Response");
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null) {
                        Log.e("Volley", "Error. HTTP Status Code:"+networkResponse.statusCode);
                    }

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    //    headers.put("Content-Type", "application/json; charset=utf-8");
                    //  headers.put("Accept", "application/json");
                    headers.put("Authorization", ReverApplication.getSessionToken());
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonService:
                showServiceReqDetails();
                if (sr_id != null)
                    loadServiceRequest(sr_id);
                else
                    Toast.makeText(getActivity(), "Select Service Request to get details.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.buttonProduct:
                showProductDetails();
                if (sr_id != null)
                    loadProductDetails(sr_id);
                else
                    Toast.makeText(getActivity(), "Select Service Request to get details.", Toast.LENGTH_SHORT).show();
                break;

           /* case R.id.editCustomer:
                showCustomerDetails();
                break;

            case R.id.editDetails:
                showOtherDetails();
                break;*/

            case R.id.viewQuote:
                showQuotation();
                break;

           /* case R.id.edtStatus:
                showStatusDetails();
                break;*/

            case R.id.updateService:
                getEditValues();
                break;

            case R.id.updateClose:
                closeService();
                default:
                break;


        }
    }


    public void showQuotation(){

        QuotationExtended fragment = new QuotationExtended();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.linearFragmentInMain, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void searchService(String key, String value) {
        Log.i("myLog", "searchInSR");

        String url = NetUtils.HOST + NetUtils.EW_SERVICE_SEARCH;
        Log.i("myLog", "Post url:" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("page_no", 1);
            jsonObject.put("page_count", 5);
            jsonObject.put(key,value);
            Log.i("myLog","Search json request data:"+jsonObject.toString());
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("myLog", "Search Success Response");
                    Log.i("myLog", "Success Response:" + response.toString());
                    MasterCache.saveServiceListCache(response);
                    listAdapter.notifyDataSetChanged();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("myLog", "Search Error Response");
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null) {
                        Log.e("Volley", "Error. HTTP Status Code:" + networkResponse.statusCode);
                    }

                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    //    headers.put("Content-Type", "application/json; charset=utf-8");
                    //  headers.put("Accept", "application/json");
                    headers.put("Authorization", ReverApplication.getSessionToken());
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getEditValues(){

        edtCustomer.setTag(1);
        edtCustomer.setText("EDIT");
        edtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final int status = (Integer) v.getTag();
                if (status == 1) {
                    edtCustomer.setText("DONE");
                    v.setTag(0); //pause
                    edtCity.setFocusableInTouchMode(true);
                    edtCity.setClickable(true);
                    edtCity.setFocusable(true);
                    edtCity.setBackgroundResource(R.drawable.edittext_bg);

                    edtAddress.setFocusableInTouchMode(true);
                    edtAddress.setClickable(true);
                    edtAddress.setFocusable(true);
                    edtAddress.setBackgroundResource(R.drawable.edittext_bg);

                    edtContact.setFocusableInTouchMode(true);
                    edtContact.setClickable(true);
                    edtContact.setFocusable(true);
                    edtContact.setBackgroundResource(R.drawable.edittext_bg);

                    edtPostalCode.setFocusableInTouchMode(true);
                    edtPostalCode.setClickable(true);
                    edtPostalCode.setFocusable(true);
                    edtPostalCode.setBackgroundResource(R.drawable.edittext_bg);

                } else {

                    new AlertDialog.Builder(getContext())
                            .setTitle("Update")
                            .setMessage("Update changes")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    edtCustomer.setText("EDIT");
                                    v.setTag(1); //pause
                                    edtCity.setFocusableInTouchMode(false);
                                    edtCity.setClickable(false);
                                    edtCity.setFocusable(false);
                                    edtCity.setBackgroundResource(0);

                                    edtContact.setFocusableInTouchMode(false);
                                    edtContact.setClickable(false);
                                    edtContact.setFocusable(false);
                                    edtContact.setBackgroundResource(0);
                                    edtAddress.setFocusableInTouchMode(false);
                                    edtAddress.setClickable(false);
                                    edtAddress.setFocusable(false);
                                    edtAddress.setBackgroundResource(0);
                                    edtPostalCode.setFocusableInTouchMode(false);
                                    edtPostalCode.setClickable(false);
                                    edtPostalCode.setFocusable(false);
                                    edtPostalCode.setBackgroundResource(0);

                                    String city = edtCity.getText().toString();
                                    String contact = edtContact.getText().toString();
                                    String code = edtCustomer.getText().toString();
                                    String address = edtAddress.getText().toString();


                                    HashMap<String, String> map = new HashMap<>();


                                    map.put("consumer_id", MasterCache.srReqUserId.get(0));
                                    Log.i("USER", MasterCache.srReqUserId.toString());
                                    map.put("other_desc", MasterCache.srReqOtherDesc.get(0));
                                    map.put("created_on", MasterCache.srCreatedOn.get(0));
                                    map.put("state", MasterCache.srReqState.get(0));
                                    map.put("serial_no", MasterCache.srReqSerialNo.get(0));
                                    map.put("model_name", MasterCache.srReqModelName.get(0));
                                    map.put("brand_name", MasterCache.srReqBrandName.get(0));
                                    map.put("first_name", MasterCache.srReqFirstName.get(0));
                                    map.put("company_name", MasterCache.srCompanyName.get(0));
                                    map.put("sr_no", MasterCache.srReqSrNo.get(0));
                                    map.put("user_id", MasterCache.srReqUserId.get(0));
                                    map.put("warranty_status", MasterCache.srReqWarrantyStatus.get(0));
                                    map.put("created_by", MasterCache.srReqCreatedBy.get(0));
                                    // jsonObject.put("eq_stock_id",MasterCache.eqs180860,
                                    map.put("status", MasterCache.srReqStatus.get(0));
                                    map.put("product_type", MasterCache.srReqProdType.get(0));
                                    map.put("country_code", MasterCache.srReqCountryCode.get(0));
                                    map.put("address_line1", MasterCache.srReqAddress.get(0));
                                    //jsonObject.put("ew_count ",
                                    map.put("last_name", MasterCache.srReqLastName.get(0));
                                    map.put("return_count", MasterCache.srReqReturnCount.get(0));
                                    map.put("city", city);
                                    Log.i("CITY", city);
                                    map.put("address_line", address);
                                    Log.i("ADDRESS", address);
                                    map.put("mobile", contact);
                                    Log.i("CONTACT:", contact);
                                    map.put("postal_code", code);
                                    map.put("failure_desc", MasterCache.srReqFailureDesc.get(0));
                                    map.put("remarks", MasterCache.srReqRemarks.get(0));
                                    map.put("sr_id", MasterCache.srReqSrId.get(0));
                                    Log.i("SRID:", MasterCache.srReqSrId.toString());
                                    map.put("product_status", MasterCache.srReqProductStatus.get(0));
                                    Log.i("PROS", MasterCache.srReqProductStatus.toString());

                                    try {
                                        String data = NetUtils.getPostDataString(map);
                                        Log.i("Mylog", "EWDData" + data);
                                        showEditService(data);
                                        dialog.dismiss();
                                    } catch (UnsupportedEncodingException e) {
                                        e.printStackTrace();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                   /* try {
                                        showEditService();
                                        dialog.dismiss();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }*/


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
                }
            }
        });

                edtDetails.setTag(1);
                edtDetails.setText("EDIT");
                edtDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        final int status = (Integer) v.getTag();
                        if (status == 1) {
                            edtDetails.setText("DONE");
                            v.setTag(0); //pause
                            edtFailure.setFocusableInTouchMode(true);
                            edtFailure.setClickable(true);
                            edtFailure.setFocusable(true);
                            edtFailure.setBackgroundResource(R.drawable.edittext_bg);

                            edtRemarks.setFocusableInTouchMode(true);
                            edtRemarks.setClickable(true);
                            edtRemarks.setFocusable(true);
                            edtRemarks.setBackgroundResource(R.drawable.edittext_bg);

                        } else {

                            new AlertDialog.Builder(getContext())
                                    .setTitle("Update")
                                    .setMessage("Update changes")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            // continue with delete
                                            edtDetails.setText("EDIT");
                                            v.setTag(1); //pause
                                            edtFailure.setFocusableInTouchMode(false);
                                            edtFailure.setClickable(false);
                                            edtFailure.setFocusable(false);
                                            edtFailure.setBackgroundResource(0);

                                            edtRemarks.setFocusableInTouchMode(false);
                                            edtRemarks.setClickable(false);
                                            edtRemarks.setFocusable(false);
                                            edtRemarks.setBackgroundResource(0);

                                            String failure = edtFailure.getText().toString();
                                            String remarks = edtRemarks.getText().toString();

                                            HashMap<String, String> map = new HashMap<>();


                                            map.put("consumer_id", MasterCache.srReqUserId.get(0));
                                            Log.i("USER", MasterCache.srReqUserId.toString());
                                            map.put("other_desc", MasterCache.srReqOtherDesc.get(0));
                                            map.put("created_on", MasterCache.srCreatedOn.get(0));
                                            map.put("state", MasterCache.srReqState.get(0));
                                            map.put("serial_no", MasterCache.srReqSerialNo.get(0));
                                            map.put("model_name", MasterCache.srReqModelName.get(0));
                                            map.put("brand_name", MasterCache.srReqBrandName.get(0));
                                            map.put("first_name", MasterCache.srReqFirstName.get(0));
                                            map.put("company_name", MasterCache.srCompanyName.get(0));
                                            map.put("sr_no", MasterCache.srReqSrNo.get(0));
                                            map.put("user_id", MasterCache.srReqUserId.get(0));
                                            map.put("warranty_status", MasterCache.srReqWarrantyStatus.get(0));
                                            map.put("created_by", MasterCache.srReqCreatedBy.get(0));
                                            map.put("status", MasterCache.srReqStatus.get(0));
                                            map.put("product_type", MasterCache.srReqProdType.get(0));
                                            map.put("country_code", MasterCache.srReqCountryCode.get(0));
                                            map.put("address_line1", MasterCache.srReqAddress.get(0));
                                            map.put("last_name", MasterCache.srReqLastName.get(0));
                                            map.put("return_count", MasterCache.srReqReturnCount.get(0));
                                            map.put("city", MasterCache.srReqCity.get(0));
                                            map.put("address_line", MasterCache.srReqAddress.get(0));
                                            map.put("mobile", MasterCache.srReqMobile.get(0));
                                            map.put("postal_code", MasterCache.srReqPostalCode.get(0));
                                            map.put("failure_desc", failure);
                                            Log.i("FAIL:", failure);
                                            map.put("remarks", remarks);
                                            Log.i("REMARKS:", remarks);
                                            map.put("sr_id", MasterCache.srReqSrId.get(0));
                                            Log.i("SRID:", MasterCache.srReqSrId.toString());
                                            map.put("product_status", MasterCache.srReqProductStatus.get(0));
                                            Log.i("PROS", MasterCache.srReqProductStatus.toString());

                                            try {
                                                String data = NetUtils.getPostDataString(map);
                                                Log.i("Mylog", "EDIT" + data);
                                                showEditService(data);
                                                dialog.dismiss();
                                            } catch (UnsupportedEncodingException e) {
                                                e.printStackTrace();
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }


                                          /*  try {
                                                showEditService();
                                                dialog.dismiss();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }*/


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
                        }
                    }
                });

        edtStatus.setText("DONE");
        linearUpdate.setVisibility(View.VISIBLE);
        linearStatus.setVisibility(View.GONE);
        linearRadio.setVisibility(View.VISIBLE);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.notYetReceived) {
                    linearRadio.setVisibility(View.GONE);
                    // linearEdit.setVisibility(View.VISIBLE);
                    linearStatus.setVisibility(View.VISIBLE);
                    txtStatus.setText(btnNotYet.getText().toString());
                    edtStatus.setText("EDIT");
                }
                if (checkedId == R.id.prodReceived) {
                    linearRadio.setVisibility(View.GONE);
                    // linearEdit.setVisibility(View.VISIBLE);
                    linearStatus.setVisibility(View.VISIBLE);
                    txtStatus.setText(btnReceived.getText().toString());
                    edtStatus.setText("EDIT");
                }
                if (checkedId == R.id.onsiteRequest) {
                    linearRadio.setVisibility(View.GONE);
                    //linearEdit.setVisibility(View.VISIBLE);
                    linearStatus.setVisibility(View.VISIBLE);
                    txtStatus.setText(btnOnsite.getText().toString());
                    edtStatus.setText("EDIT");
                }
            }
        });

        String status = txtStatus.getText().toString();

        HashMap<String, String> map = new HashMap<>();


        map.put("consumer_id", MasterCache.srReqUserId.get(0));
        Log.i("USER", MasterCache.srReqUserId.toString());
        map.put("other_desc",MasterCache.srReqOtherDesc.get(0));
        map.put("created_on", MasterCache.srCreatedOn.get(0));
        map.put("state", MasterCache.srReqState.get(0));
        map.put("serial_no", MasterCache.srReqSerialNo.get(0));
        map.put("model_name", MasterCache.srReqModelName.get(0));
        map.put("brand_name", MasterCache.srReqBrandName.get(0));
        map.put("first_name", MasterCache.srReqFirstName.get(0));
        map.put("company_name", MasterCache.srCompanyName.get(0));
        map.put("sr_no", MasterCache.srReqSrNo.get(0));
        map.put("user_id", MasterCache.srReqUserId.get(0));
        map.put("warranty_status", MasterCache.srReqWarrantyStatus.get(0));
        map.put("created_by", MasterCache.srReqCreatedBy.get(0));
        // jsonObject.put("eq_stock_id",MasterCache.eqs180860,
        map.put("status", MasterCache.srReqStatus.get(0));
        map.put("product_type", MasterCache.srReqProdType.get(0));
        map.put("country_code", MasterCache.srReqCountryCode.get(0));
        map.put("address_line1", MasterCache.srReqAddress.get(0));
        //jsonObject.put("ew_count ",
        map.put("last_name", MasterCache.srReqLastName.get(0));
        map.put("return_count", MasterCache.srReqReturnCount.get(0));
        map.put("city", MasterCache.srReqCity.get(0));
        map.put("address_line", MasterCache.srReqAddress.get(0));
        map.put("mobile", MasterCache.srReqMobile.get(0));
        map.put("postal_code", MasterCache.srReqPostalCode.get(0));
        map.put("failure_desc", MasterCache.srReqFailureDesc.get(0));
        map.put("remarks", MasterCache.srReqRemarks.get(0));
        map.put("sr_id", MasterCache.srReqSrId.get(0));
        Log.i("SRID:", MasterCache.srReqSrId.toString());
        map.put("product_status", status);
        Log.i("PROS", status);

        try {
            String data = NetUtils.getPostDataString(map);
            Log.i("Mylog","EDIT"+data);
            showEditService(data);
            dialog.dismiss();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void showServiceReqDetails() {
        linearSR.removeAllViews();
        service.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_txt));
        product.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorwhite));
        service.setTextColor(ContextCompat.getColor(getContext(), R.color.colorwhite));
        product.setTextColor(ContextCompat.getColor(getContext(), R.color.blue_txt));
        LayoutInflater li = LayoutInflater.from(getActivity());
        View layout = li.inflate(R.layout.service_request_details, null, false);
        linearSR.addView(layout);
        initServiceRequest(layout);
    }
    public void showProductDetails() {
        linearSR.removeAllViews();
        service.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorwhite));
        product.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_txt));
        service.setTextColor(ContextCompat.getColor(getContext(), R.color.blue_txt));
        product.setTextColor(ContextCompat.getColor(getContext(), R.color.colorwhite));
        LayoutInflater li = LayoutInflater.from(getActivity());
        View layout = li.inflate(R.layout.service_product_details, null, false);
        linearSR.addView(layout);
        initProductDetails(layout);
    }

    public void initServiceRequest(View v) {
        btnUpdate = (TextView) rootView.findViewById(R.id.updateService);
        btnUpclose = (TextView) rootView.findViewById(R.id.updateClose);
        txtViewQuote = (TextView)rootView.findViewById(R.id.viewQuote);
        edtStatus = (TextView) rootView.findViewById(R.id.edtStatus);
        edtCustomer = (TextView) rootView.findViewById(R.id.editCustomer);
        edtDetails = (TextView) rootView.findViewById(R.id.editDetails);
        txtServiceNo = (TextView) v.findViewById(R.id.txtServiceNo);
        txtCreatedBy = (TextView) v.findViewById(R.id.txtCreatedBy);
        txtCreatedDate = (TextView) v.findViewById(R.id.txtCreatedDate);
        txtFailureDesc = (TextView) v.findViewById(R.id.txtFailureDesc);
        txtSerialNo = (TextView) v.findViewById(R.id.txtSerialNo);
        txtBrand = (TextView) v.findViewById(R.id.txtBrand);
        txtProdType = (TextView) v.findViewById(R.id.txtProductType);
        txtModel = (TextView) v.findViewById(R.id.txtModel);
        txtWarrantyStatus = (TextView) v.findViewById(R.id.txtWarrantyStatus);
        txtPastServiceReturns = (TextView) v.findViewById(R.id.txtPastServiceReturns);
        txtEmail = (TextView) v.findViewById(R.id.txtEmail);
        txtCountry = (TextView) v.findViewById(R.id.txtCountry);
        txtFirstName = (TextView) v.findViewById(R.id.txtFirstName);
        txtLastName = (TextView) v.findViewById(R.id.txtLastName);
        //  txtCity = (TextView) v.findViewById(R.id.txtCity);
        txtState = (TextView) v.findViewById(R.id.txtState);
        //txtAddress = (TextView)v.findViewById(R.id.txtAddress);
        //txtPostalCode = (TextView) v.findViewById(R.id.txtPostalCode);
        txtIcPassportNo = (TextView) v.findViewById(R.id.txtIcPassport);
        //txtMobile = (TextView)v.findViewById(R.id.txtMobile);
        txtServiceCentre = (TextView) v.findViewById(R.id.txtServiceCenters);
        //txtFailDesc = (TextView) v.findViewById(R.id.txtFailDesc);
        //txtRemarks = (TextView) v.findViewById(R.id.txtRemarks);
        txtStatus = (TextView) v.findViewById(R.id.txtStatus);
        //txtEditStatus = (TextView) v.findViewById(R.id.editProdStatus);
        // txtEditStatus =(TextView)v.findViewById(group.getCheckedRadioButtonId());
        // linearEdit = (LinearLayout) v.findViewById(R.id.linearTextStatus);
        linearRadio = (LinearLayout) v.findViewById(R.id.linearRadioGroup);
        linearStatus = (LinearLayout) v.findViewById(R.id.statusInServiceReq);
        linearUpdate = (LinearLayout) v.findViewById(R.id.linearUpdateService);
        edtCity = (EditText) v.findViewById(R.id.editCity);
        edtAddress = (EditText) v.findViewById(R.id.editAddress);
        edtPostalCode = (EditText) v.findViewById(R.id.editPostalCode);
        edtContact = (EditText) v.findViewById(R.id.editMobile);
        edtFailure = (EditText) v.findViewById(R.id.editFailureDesc);
        edtRemarks = (EditText) v.findViewById(R.id.editRemarks);
        //edtProdStatus = (EditText)v.findViewById(R.id.editProdStatus);
        group = (RadioGroup) v.findViewById(R.id.myRadioGroup);
        btnNotYet = (RadioButton) v.findViewById(R.id.notYetReceived);
        btnReceived = (RadioButton) v.findViewById(R.id.prodReceived);
        btnOnsite = (RadioButton) v.findViewById(R.id.onsiteRequest);
        edtStatus.setOnClickListener(this);
        edtDetails.setOnClickListener(this);
        edtCustomer.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnUpclose.setOnClickListener(this);
        txtViewQuote.setOnClickListener(this);
    }

    public void closeService(){

        String closeReqUrl = String.format(NetUtils.EW_SERVICE_CLOSE);
        String url = NetUtils.HOST + closeReqUrl;
        Log.i("myLog", " loadCloseUrl url : " + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("myLog", "loadClose Success Response");
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
                //headers.put("Accept", "application/json");
                headers.put("Authorization", ReverApplication.getSessionToken());
                return headers;
            }
        };
        requestQueue.add(jsonRequest);
    }


    public void showEditService(String data) throws JSONException {

        String url = NetUtils.HOST + NetUtils.EW_SERVICE_EDIT;
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

/*public void showEditService(data) {
      /* edtRemarks.setVisibility(View.GONE);
        txtRemarks.setVisibility(View.VISIBLE);
        txtRemarks.setText(edtRemarks.getText().toString());
        edtCity.setVisibility(View.GONE);
        txtCity.setVisibility(View.VISIBLE);
        txtCity.setText(edtCity.getText().toString());
        edtContact.setVisibility(View.GONE);
        txtMobile.setVisibility(View.VISIBLE);
        txtMobile.setText(edtContact.getText().toString());
        edtAddress.setVisibility(View.GONE);
        txtAddress.setVisibility(View.VISIBLE);
        txtAddress.setText(edtAddress.getText().toString());
        edtPostalCode.setVisibility(View.GONE);
        txtPostalCode.setVisibility(View.VISIBLE);
        txtPostalCode.setText(edtPostalCode.getText().toString());
        edtFailure.setVisibility(View.GONE);
        txtFailDesc.setVisibility(View.VISIBLE);
        txtFailDesc.setText(edtFailure.getText().toString());
        String city = edtCity.getText().toString();
        String contact = edtContact.getText().toString();
        String address = edtAddress.getText().toString();
        String code = edtPostalCode.getText().toString();
        String failure = edtFailure.getText().toString();
        String remarks = edtRemarks.getText().toString();
        String status = txtStatus.getText().toString();

        String url = NetUtils.HOST + NetUtils.EW_SERVICE_EDIT;

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("consumer_id", MasterCache.srReqUserId.get(0));
            Log.i("USER", MasterCache.srReqUserId.toString());

            jsonObject.put("other_desc", MasterCache.srReqOtherDesc.get(0));
            jsonObject.put("created_on", MasterCache.srCreatedOn.get(0));
            jsonObject.put("state", MasterCache.srReqState.get(0));
            jsonObject.put("serial_no", MasterCache.srReqSerialNo.get(0));
            jsonObject.put("model_name", MasterCache.srReqModelName.get(0));
            jsonObject.put("brand_name", MasterCache.srReqBrandName.get(0));
            jsonObject.put("first_name", MasterCache.srReqFirstName.get(0));
            jsonObject.put("company_name", MasterCache.srCompanyName.get(0));
            jsonObject.put("sr_no", MasterCache.srReqSrNo.get(0));
            jsonObject.put("user_id", MasterCache.srReqUserId.get(0));
            jsonObject.put("warranty_status", MasterCache.srReqWarrantyStatus.get(0));
            jsonObject.put("created_by", MasterCache.srReqCreatedBy.get(0));
            // jsonObject.put("eq_stock_id",MasterCache.eqs180860,
            jsonObject.put("status", MasterCache.srReqStatus.get(0));
            jsonObject.put("product_type", MasterCache.srReqProdType.get(0));
            jsonObject.put("country_code", MasterCache.srReqCountryCode.get(0));
            jsonObject.put("address_line1", MasterCache.srReqAddress.get(0));
            //jsonObject.put("ew_count ",
            jsonObject.put("last_name", MasterCache.srReqLastName.get(0));
            jsonObject.put("return_count", MasterCache.srReqReturnCount.get(0));
            jsonObject.put("city", city);
            Log.i("CITY", city);
            jsonObject.put("address_line", address);
            Log.i("ADDRESS", address);
            jsonObject.put("mobile", contact);
            Log.i("CONTACT:", contact);
            jsonObject.put("postal_code", code);
            Log.i("CODE", code);
            jsonObject.put("failure_desc", failure);
            Log.i("FAIL:", failure);
            jsonObject.put("remarks", remarks);
            Log.i("REMARKS", remarks);
            jsonObject.put("sr_id", MasterCache.srReqSrId.get(0));
            Log.i("SRID:", MasterCache.srReqSrId.toString());
            jsonObject.put("product_status", status);
            Log.i("PROS", status);
            Log.i("myLog", "jsonObject string:" + jsonObject.toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i("myLog", "Success Response:" + response.toString());
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("myLog", "Error Response");
                    NetworkResponse networkResponse = error.networkResponse;
                    if (networkResponse != null) {
                        Log.e("Volley", "Error. HTTP Status Code:" + networkResponse.statusCode);
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> headers = new HashMap<>();
                    //    headers.put("Content-Type", "application/json; charset=utf-8");
                    //  headers.put("Accept", "application/json");
                    headers.put("Authorization", ReverApplication.getSessionToken());
                    Log.i("AUTHORIZATION:::", ReverApplication.getSessionToken());
                    return headers;
                }
            };
            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
    public void initProductDetails(View v) {
        emailText = (TextView) v.findViewById(R.id.emailText);
        altEmailText = (TextView) v.findViewById(R.id.altEmailText);
        firstNameText = (TextView) v.findViewById(R.id.firstNameText);
        middleNameText = (TextView) v.findViewById(R.id.middleNameText);
        lastNameText = (TextView) v.findViewById(R.id.lastNameText);
        icText = (TextView) v.findViewById(R.id.icPassportText);
        address1Text = (TextView) v.findViewById(R.id.address1Text);
        address2Text = (TextView) v.findViewById(R.id.address2Text);
        postalCodeText = (TextView) v.findViewById(R.id.postalCodeText);
        cityText = (TextView) v.findViewById(R.id.cityText);
        stateText = (TextView) v.findViewById(R.id.stateText);
        countryText = (TextView) v.findViewById(R.id.countryProdText);
        mobileText = (TextView) v.findViewById(R.id.mobileText);
        homePhoneText = (TextView) v.findViewById(R.id.homePhoneText);
        officePhoneText = (TextView) v.findViewById(R.id.officePhoneText);
        serialNoText = (TextView) v.findViewById(R.id.serialNoText);
        billNoText = (TextView) v.findViewById(R.id.billNoText);
        brandText = (TextView) v.findViewById(R.id.brandText);
        warrantyNoText = (TextView) v.findViewById(R.id.warrantyNoText);
        prodTypeText = (TextView) v.findViewById(R.id.prodTypeText);
        warrantyMonthsText = (TextView) v.findViewById(R.id.warrantyMonthsText);
        modelText = (TextView) v.findViewById(R.id.modelText);
        warranytyExpText = (TextView) v.findViewById(R.id.warrExpText);
        purchaseOnText = (TextView) v.findViewById(R.id.purchaseText);
        regDateText = (TextView) v.findViewById(R.id.warrRegText);
        purchaseFromText = (TextView) v.findViewById(R.id.purchaseFromText);
        prodCostText = (TextView) v.findViewById(R.id.productCostText);
        countryProdText = (TextView) v.findViewById(R.id.countryText);
        warrantyStartText = (TextView) v.findViewById(R.id.warrStartText);
     }

    public class CustomList extends ArrayAdapter<String> {
        private List<String> srNo, status, createdOn, customer;
        private Activity context;

        public CustomList(Activity context, List<String> srNo, List<String> status, List<String> createdOn, List<String> customer) {
            super(context, R.layout.service_list, srNo);
            this.context = context;
            this.srNo = srNo;
            this.status = status;
            this.createdOn = createdOn;
            this.customer = customer;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.service_list, null, true);
            TextView txtStatus = (TextView) listViewItem.findViewById(R.id.status);
            TextView txtConsumer = (TextView) listViewItem.findViewById(R.id.customerName);
            TextView txtCreatedOn = (TextView) listViewItem.findViewById(R.id.service_date);
            TextView txtSrNo = (TextView) listViewItem.findViewById(R.id.customerId);
            // txtStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.alert_small, 0, 0, 0);
            //txtCreatedOn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.calendar_small, 0, 0, 0);
            //txtConsumer.setCompoundDrawablesWithIntrinsicBounds(R.drawable.user_f, 0, 0, 0);*/
            txtStatus.setText(status.get(position));
            txtConsumer.setText(customer.get(position));
            txtCreatedOn.setText(createdOn.get(position));
            txtSrNo.setText(srNo.get(position));
            return listViewItem;
        }
    }

     public void loadServiceRequest(String sr_id) {
         String serviceReqUrl = String.format(NetUtils.EW_SERVICE_DETAILS, sr_id);
         String url = NetUtils.HOST + serviceReqUrl;
         Log.i("myLog", " loadServiceReqDetails url : " + url);
         RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
         JsonObjectRequest jsonRequest = new JsonObjectRequest
                 (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                     @Override
                     public void onResponse(JSONObject response) {
                         Log.i("myLog", "loadServiceReqDetails Success Response");
                         MasterCache.saveServiceDetailCache(response);
                         loadServReqDet();
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

    public void loadProductDetails(String sr_id) {
        String serviceProdUrl = String.format(NetUtils.EW_PRODUCT_DETAILS, sr_id);
        String url = NetUtils.HOST + serviceProdUrl;
        Log.i("myLog", " loadServiceProdDetails url : " + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        Log.i("myLog", "loadServiceProdDetails Success Response");
                        JsonUtils.parseProductJson(response);
                        //MasterCache.saveProductDetailCache(response);
                        loadServProdDet();
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
    private void loadServReqDet() {
        txtServiceNo.setText(MasterCache.srReqSrId.get(0));
        txtCreatedBy.setText(MasterCache.srReqCreatedBy.get(0));
        txtCreatedDate.setText(MasterCache.srCreatedOn.get(0));
        txtFailureDesc.setText(MasterCache.srReqFailureDesc.get(0));
        txtSerialNo.setText(MasterCache.srReqSerialNo.get(0));
        txtBrand.setText(MasterCache.srReqBrandName.get(0));
        txtProdType.setText(MasterCache.srReqProdType.get(0));
        txtModel.setText(MasterCache.srReqModelName.get(0));
        txtWarrantyStatus.setText(MasterCache.srReqWarrantyStatus.get(0));
        txtPastServiceReturns.setText(MasterCache.srReqReturnCount.get(0));
        txtEmail.setText("");
        txtCountry.setText(MasterCache.srReqCountryCode.get(0));
        txtFirstName.setText(MasterCache.srReqFirstName.get(0));
        txtLastName.setText(MasterCache.srReqLastName.get(0));
        edtCity.setText(MasterCache.srReqCity.get(0));
        // txtCity.setText(MasterCache.srReqCity.get(0));
        txtState.setText(MasterCache.srReqState.get(0));
        edtAddress.setText(MasterCache.srReqAddress.get(0));
        // txtAddress.setText(MasterCache.srReqAddress.get(0));
        //  txtPostalCode.setText(MasterCache.srReqPostalCode.get(0));
        edtPostalCode.setText(MasterCache.srReqPostalCode.get(0));
        txtIcPassportNo.setText(MasterCache.srReqIcNo.get(0));
        //txtMobile.setText(MasterCache.srReqMobile.get(0));
        edtContact.setText(MasterCache.srReqMobile.get(0));
        txtServiceCentre.setText(MasterCache.srCompanyName.get(0));
        edtFailure.setText(MasterCache.srReqFailureDesc.get(0));
        // txtFailDesc.setText(MasterCache.srReqFailureDesc.get(0));
        //txtRemarks.setText(MasterCache.srReqRemarks.get(0));
        edtRemarks.setText(MasterCache.srReqRemarks.get(0));
        txtStatus.setText(MasterCache.srReqProductStatus.get(0));
    }
    private void loadServProdDet() {
        emailText.setText("");
        altEmailText.setText("");
        firstNameText.setText(MasterCache.prdReqFirstName.get(0));
        middleNameText.setText(MasterCache.prdReqMiddleName.get(0));
        lastNameText.setText(MasterCache.prdReqLastName.get(0));
        icText.setText(MasterCache.prdReqIcNo.get(0));
        address1Text.setText(MasterCache.prdReqAddLine1.get(0));
        address2Text.setText("");
        postalCodeText.setText(MasterCache.prdReqPostalCode.get(0));
        cityText.setText(MasterCache.prdReqCity.get(0));
        stateText.setText(MasterCache.prdReqState.get(0));
        countryText.setText(MasterCache.prdReqCountryCode.get(0));
        mobileText.setText(MasterCache.prdReqMobile.get(0));
        homePhoneText.setText(MasterCache.prdReqPhoneNo.get(0));
        officePhoneText.setText(MasterCache.prdReqOfficePhone.get(0));
        serialNoText.setText(MasterCache.prdReqSerialNo.get(0));
        billNoText.setText(MasterCache.prdReqBillNo.get(0));
        brandText.setText(MasterCache.prdReqBrandName.get(0));
        warrantyNoText.setText(MasterCache.prdReqWarrantyNo.get(0));
        prodTypeText.setText(MasterCache.prdReqProductType.get(0));
        warrantyMonthsText.setText(MasterCache.prdReqWarrantyMonths.get(0));
        modelText.setText(MasterCache.prdReqModelName.get(0));
        warranytyExpText.setText(MasterCache.prdReqWarrantyEndDate.get(0));
        purchaseOnText.setText(MasterCache.prdReqPurchaseDate.get(0));
        regDateText.setText(MasterCache.prdReqRegDate.get(0));
        purchaseFromText.setText(MasterCache.prdReqPurchaseFrom.get(0));
        prodCostText.setText(MasterCache.prdReqPrice.get(0));
        countryProdText.setText(MasterCache.prdReqCountryName.get(0));
        warrantyStartText.setText(MasterCache.prdReqWarrantyStartDate.get(0));
        ;
    }

    private boolean isValidCity(String city) {
        String CITY_PATTERN = "^[a-zA-Z]+(?:(?:\\s+|-)[a-zA-Z]+)*$";
        Pattern pattern = Pattern.compile(CITY_PATTERN);
        Matcher matcher = pattern.matcher(city);
        return matcher.matches();
    }
    private boolean isValidPhone(String phone) {
        String PHONE_PATTERN = "^[+]?[0-9]{8,15}$";
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
    private boolean isValidAddress(String address) {
        String ADDRESS_PATTERN = "[a-zA-Z\\d\\s\\-\\,\\#\\.\\+]+";
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
    private boolean isValidPostalCode(String pinCode) {
        String CODE_PATTERN = "^\\d{6}(?:[-\\s]\\d{4})?$";
        Pattern pattern = Pattern.compile(CODE_PATTERN);
        Matcher matcher = pattern.matcher(pinCode);
        return matcher.matches();
    }
    private boolean isValidFailure(String failure) {
        String FAILURE_PATTERN = "^[a-zA-Z]+(?:(?:\\s+|-)[a-zA-Z]+)*$";
        Pattern pattern = Pattern.compile(FAILURE_PATTERN);
        Matcher matcher = pattern.matcher(failure);
        return matcher.matches();
    }
    private boolean isValidRemarks(String remarks) {
        String REMARKS_PATTERN = "^[a-zA-Z]+(?:(?:\\s+|-)[a-zA-Z]+)*$";
        Pattern pattern = Pattern.compile(REMARKS_PATTERN);
        Matcher matcher = pattern.matcher(remarks);
        return matcher.matches();
    }

}

         /*   txtFailDesc.setVisibility(View.VISIBLE);
            txtFailDesc.setText(edtFailure.getText().toString());
        }else {
           }
           // txtRemarks.setVisibility(View.VISIBLE);
            //txtRemarks.setText(edtRemarks.getText().toString());
        }*/
     /*                       edtDetails.setText("DONE");
                                    linearUpdate.setVisibility(View.VISIBLE);
                                    txtFailDesc.setVisibility(View.GONE);
                                    edtFailure.setVisibility(View.VISIBLE);
                                    edtFailure.setText(txtFailDesc.getText().toString());
                                    // txtFailDesc.setText(edtFailure.getText().toString());

                                    txtRemarks.setVisibility(View.GONE);
                                    edtRemarks.setVisibility(View.VISIBLE);
                                    edtRemarks.setText(txtRemarks.getText().toString());
                                    //txtRemarks.setText(edtRemarks.getText().toString());*/


      /*  String failure = edtFailure.getText().toString();
        String remarks = edtRemarks.getText().toString();

        if(failure.length() >= 4 && isValidFailure(failure)) {
            if(remarks.length() >= 4 && isValidRemarks(remarks)) {
                edtFailure.setVisibility(View.GONE);
                edtRemarks.setVisibility(View.GONE);
            }else {
                Toast.makeText(getActivity(), "Please enter valid remarks", Toast.LENGTH_SHORT).show();
            }}else {
            Toast.makeText(getActivity(), "Please enter valid failure description", Toast.LENGTH_SHORT).show();
        }
         /*   txtFailDesc.setVisibility(View.VISIBLE);
            txtFailDesc.setText(edtFailure.getText().toString());
        }else {
           }
           // txtRemarks.setVisibility(View.VISIBLE);
            //txtRemarks.setText(edtRemarks.getText().toString());
        }else {

*/

//        showEditServ//ice();
  /* String city = edtCity.getText().toString();
                    String contact = edtContact.getText().toString();
                    String address = edtAddress.getText().toString();
                    String pinCode = edtPostalCode.getText().toString();

                    if(city.length() >= 3 && isValidCity(city)) {
                        if (contact.length() >= 8 && isValidPhone(contact)) {
                            if (address.length() >= 10 && isValidAddress(address)) {
                                if (pinCode.length() >= 5 && isValidPostalCode(pinCode)) {
                                    edtCity.setText(city);
                                    edtContact.setText(contact);
                                    edtAddress.setText(address);
                                    edtPostalCode.setText(pinCode);
                                } else {
                                    Toast.makeText(getActivity(), "Please enter valid postal code", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Please enter valid address", Toast.LENGTH_SHORT).show();
                            }
                            // edtCity.setVisibility(View.GONE);
                            //txtCity.setVisibility(View.VISIBLE);
                            //edtCity.setVisibility(View.GONE);
                            //txtMobile.setVisibility(View.VISIBLE);
                        } else {
                            Toast.makeText(getActivity(), "Please enter valid mobile number", Toast.LENGTH_SHORT).show();
                        }                       // edtAddress.setVisibility(View.GONE);
                        //txtAddress.setVisibility(View.VISIBLE);
                        //edtPostalCode.setVisibility(View.GONE);
                        //txtPostalCode.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(getActivity(),"Please enter valid city",Toast.LENGTH_SHORT).show();
                    }*/



    /* edtCustomer.setText("DONE");
                                linearUpdate.setVisibility(View.VISIBLE);
                           txtCity.setVisibility(View.GONE);
                                    edtCity.setVisibility(View.VISIBLE);
                                    edtCity.setText(txtCity.getText().toString());

                                    txtAddress.setVisibility(View.GONE);
                                    edtAddress.setVisibility(View.VISIBLE);
                                    edtAddress.setText(txtAddress.getText().toString());

                                    txtPostalCode.setVisibility(View.GONE);
                                    edtPostalCode.setVisibility(View.VISIBLE);
                                    edtPostalCode.setText(txtPostalCode.getText().toString());

                                    txtMobile.setVisibility(View.GONE);
                                    edtContact.setVisibility(View.VISIBLE);
                                    edtContact.setText(txtMobile.getText().toString());*/

        /*String city = edtCity.getText().toString();
        String contact = edtContact.getText().toString();
        String address = edtAddress.getText().toString();
        String pinCode = edtPostalCode.getText().toString();

        if(city.length() >= 3 && isValidCity(city)) {
            edtCity.setVisibility(View.GONE);
            txtCity.setVisibility(View.VISIBLE);
            txtCity.setText(edtCity.getText().toString());
        }else {
            Toast.makeText(getActivity(),"Please enter valid city",Toast.LENGTH_SHORT).show();
        }

        if(contact.length() >= 8 && isValidPhone(contact)){
            edtCity.setVisibility(View.GONE);
            txtMobile.setVisibility(View.VISIBLE);
            txtMobile.setText(edtContact.getText().toString());
        }else {
                Toast.makeText(getActivity(),"Please enter valid mobile number",Toast.LENGTH_SHORT).show();
        }

        if(address.length() >= 10 && isValidAddress(address)){
            edtAddress.setVisibility(View.GONE);
            txtAddress.setVisibility(View.VISIBLE);
            txtAddress.setText(edtAddress.getText().toString());
                }else {
                    Toast.makeText(getActivity(),"Please enter valid address",Toast.LENGTH_SHORT).show();
                }

        if(pinCode.length() >= 5 && isValidPostalCode(pinCode)){
            edtPostalCode.setVisibility(View.GONE);
            txtPostalCode.setVisibility(View.VISIBLE);
            txtPostalCode.setText(edtPostalCode.getText().toString());
        } else {
            Toast.makeText(getActivity(),"Please enter valid postal code",Toast.LENGTH_SHORT).show();
        }
        showEditService();*/
   /*     String failure = edtFailure.getText().toString();
                            String remarks = edtRemarks.getText().toString();

                            if (failure.length() >= 4 && isValidFailure(failure)) {
                                if (remarks.length() >= 4 && isValidRemarks(remarks)) {
                                    edtFailure.setText(failure);
                                    edtRemarks.setText(remarks);
                                } else {
                                    Toast.makeText(getActivity(), "Please enter valid remarks", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Please enter valid failure description", Toast.LENGTH_SHORT).show();

                            }*/
     //   public void showStatusDetails() {
            // edtStatus.setTag(1);
            //edtStatus.setText("EDIT");

        /*edtStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final int status = (Integer) v.getTag();
                if (status == 1) {*/

              /*  } else {

                    new AlertDialog.Builder(getContext())
                            .setTitle("Update")
                            .setMessage("Update changes")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // continue with delete
                                    edtStatus.setText("EDIT");
                                    v.setTag(1);

                                   // showEditService();

                                    try {
                                        showEditService();
                                        dialog.dismiss();
                                    } catch (Exception e) {
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
                }
            }
        });
           String status = txtStatus.getText().toString();

        if(status.length() >0){
            showEditService();
        }else {
            Toast.makeText(getActivity(),"Please select any one status",Toast.LENGTH_SHORT).show();
        }*/
