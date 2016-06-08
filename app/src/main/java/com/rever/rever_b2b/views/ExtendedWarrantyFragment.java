package com.rever.rever_b2b.views;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.rever.rever_b2b.R;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Oviya on 6/2/2016.
 */
public class ExtendedWarrantyFragment extends Fragment {
        private View rootView;
        private EditText edtSerialno,edtCountry,edtExtendedWCategory,edtExtendedWInvoice,edtExtendedWNo,edtExtendedWPdate,edtExtendedWPrice,edtExtendedWProvider,edtExtendedWStartDate,edtUPCCode,edtModel,edtProductType,edtVoidRefund,edtWarrantyExtendMonths;
        private TextView txtProductDetails,txtExWrDetails,txtCallLogs,txtClaimHis;
        private LinearLayout frameProduct,frameExWr,tab_bar;
        private static String url = "http://rever.com.sg/Yarraa/ext-warranty/show-all";
        private static final String vmodel_name = "model_name";
        private static final String vproduct_type = "product_type";
        private static final String vserial_no = "serial_no";
        private static final String vconsumer_name = "consumer_name";
        private static final String vwarranty_id = "warranty_id";

        ArrayList<HashMap<String, String>> jsonlist = new ArrayList<HashMap<String, String>>();

        ListView lv ;


        @Override

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_extendedwarranty, container, false);
            initViews();
            new GetWarrantyListTask().execute();
            return rootView;
            //Response
            //http://54.179.167.160:8080/Yarraa/ext-warranty/show-all
            //http://54.179.167.160:8080/Yarraa/ext-warranty/warranties/{id}

//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//                Log.i("mylog","position"+position);
//                String data=null;
//                String uurl="http://rever.com.sg/Yarraa/ext-warranty/warranties/243382";
//                new ProgressTasks().execute(uurl, data);
//            }
//        });

        }


        private void initViews(){
            edtSerialno = (EditText) rootView.findViewById(R.id.edtSerialNo);
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

            txtProductDetails = (TextView) rootView.findViewById(R.id.tabProductDetails);
            txtExWrDetails = (TextView) rootView.findViewById(R.id.tabExtendedWr);
            txtCallLogs = (TextView) rootView.findViewById(R.id.tabCallLogs);
            txtClaimHis = (TextView) rootView.findViewById(R.id.tabClaimHistory);

            frameProduct=(LinearLayout)rootView.findViewById(R.id.frameProductDetails);
            frameExWr=(LinearLayout) rootView.findViewById(R.id.frameExtendedWarrantyDetails);
            tab_bar=(LinearLayout) rootView.findViewById(R.id.tab_bar);

        }


        public class GetWarrantyListTask extends AsyncTask<String,Void,String> {
            private ProgressDialog dialog;
            private ListActivity activity;

            public GetWarrantyListTask() {

                context = activity;
                dialog = new ProgressDialog(context); }
            private Context context;


            protected void onPreExecute() {
                this.dialog.setMessage("Progress start");
                this.dialog.show();
            }

            @Override
            protected String doInBackground(String... params) {
                String option =params[0];
                String url ="";
                url=NetUtils.EXTENDED_WARRANTY_URL;
                String resp = NetUtils.sendRequest(getActivity(), url, null);
                Log.i("myLog", option + " Response:" + resp);
                MasterCache.saveFailureCache(resp);


                return option;
            }

            @Override
            protected void onPostExecute(String option)
            {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }

                ListAdapter adapter = new SimpleAdapter(context, jsonlist, R.layout.list_item, new String[]
                        {vmodel_name, vproduct_type, vserial_no, vconsumer_name, vwarranty_id},
                        new int[] {R.id.model_name, R.id.product_type, R.id.serial_no, R.id.consumer_name });

//            setListAdapter(adapter);
//            // select single ListView item
//            lv = getListView();

            }

        }

        //            try {
//                JSONObject job= new JSONObject(jstr);
//
//                JSONArray jarr= job.getJSONArray("EW");
//                int size=jarr.length();
//                for (int i = 0; i < size; i++) {
//
//                    JSONObject jo = jarr.getJSONObject(i);
//
//                    String Model_Name = jo.getString("model_name");
//                    String Product_Type = jo.getString("product_type");
//                    String Serial_No = jo.getString("serial_no");
//                    String Consumer_Name = jo.getString("consumer_name");
//                    String Warranty_Id = jo.getString("warranty_id");
//
//                    HashMap<String, String> map = new HashMap<String, String>();
//                    // Add child node to HashMap key & value
//                    map.put(vmodel_name, Model_Name);
//                    map.put(vproduct_type, Product_Type);
//                    map.put(vserial_no, Serial_No);
//                    map.put(vconsumer_name, Consumer_Name);
//                    map.put(vwarranty_id, Warranty_Id);
//                    jsonlist.add(map);
//                }
//
//
        public void productdetail(View v) {
            tab_bar.setBackgroundResource(R.drawable.bordercolor_blue);


            txtProductDetails.setTextColor(getResources().getColor(R.color.colorwhite));
            txtProductDetails.setBackgroundColor(getResources().getColor(R.color.colorblue));

            txtExWrDetails.setTextColor(getResources().getColor(R.color.colorblue));
            txtExWrDetails.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            txtCallLogs.setTextColor(getResources().getColor(R.color.colorblue));
            txtCallLogs.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            txtClaimHis.setTextColor(getResources().getColor(R.color.colorblue));
            txtClaimHis.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            frameExWr.setVisibility(View.INVISIBLE);
            frameExWr.setVisibility(View.GONE);
            frameProduct.setVisibility(View.VISIBLE);
        }

        public void ExtendedWR (View v) {
            tab_bar.setBackgroundResource(R.drawable.bordercolor_blue);


            txtExWrDetails.setTextColor(getResources().getColor(R.color.colorwhite));
            txtExWrDetails.setBackgroundColor(getResources().getColor(R.color.colorblue));

            txtCallLogs.setTextColor(getResources().getColor(R.color.colorblue));
            txtCallLogs.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            txtClaimHis.setTextColor(getResources().getColor(R.color.colorblue));
            txtClaimHis.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            txtProductDetails.setTextColor(getResources().getColor(R.color.colorblue));
            txtProductDetails.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            frameExWr.setVisibility(View.VISIBLE);
            frameProduct.setVisibility(View.INVISIBLE);
            frameProduct.setVisibility(View.GONE);
        }

        public void CallLogs(View v) {
            tab_bar.setBackgroundResource(R.drawable.bordercolor_blue);

            txtCallLogs.setTextColor(getResources().getColor(R.color.colorwhite));
            txtCallLogs.setBackgroundColor(getResources().getColor(R.color.colorblue));

            txtExWrDetails.setTextColor(getResources().getColor(R.color.colorblue));
            txtExWrDetails.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            txtClaimHis.setTextColor(getResources().getColor(R.color.colorblue));
            txtClaimHis.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            txtProductDetails.setTextColor(getResources().getColor(R.color.colorblue));
            txtProductDetails.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            frameExWr.setVisibility(View.INVISIBLE);
            frameExWr.setVisibility(View.GONE);
            frameProduct.setVisibility(View.VISIBLE);
        }

        public void ClaimHis(View v) {
            tab_bar.setBackgroundResource(R.drawable.bordercolor_blue);

            txtClaimHis.setTextColor(getResources().getColor(R.color.colorwhite));
            txtClaimHis.setBackgroundColor(getResources().getColor(R.color.colorblue));

            txtCallLogs.setTextColor(getResources().getColor(R.color.colorblue));
            txtCallLogs.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            txtExWrDetails.setTextColor(getResources().getColor(R.color.colorblue));
            txtExWrDetails.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            txtProductDetails.setTextColor(getResources().getColor(R.color.colorblue));
            txtProductDetails.setBackgroundColor(getResources().getColor(R.color.colorwhite));

            frameExWr.setVisibility(View.INVISIBLE);
            frameExWr.setVisibility(View.GONE);
            frameProduct.setVisibility(View.VISIBLE);
        }

    }

