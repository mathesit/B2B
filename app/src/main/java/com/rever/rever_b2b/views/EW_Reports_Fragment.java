package com.rever.rever_b2b.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.rever.rever_b2b.R;
import com.rever.rever_b2b.downloader.FileDownloader;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bharath on 7/21/2016.
 */
public class EW_Reports_Fragment extends Fragment {
    private View rootView;
    private LinearLayout linearTitle, linearDetail;
    private ListView lv1,lv2,lv3;
    private Spinner spinfactor,spinrange;
    private TextView FromDate,ToDate;

    private String[] search = { "Greater than","Greater than or Equal", "Less than", "Less than or equal",
            "Equal to", "Not equal to", "Contains", "Between" };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_ew_reports, container, false);
        initViews();
        GetReportsList();
        //readAllServiceRequest();
        return rootView;
    }
    private void initViews(){
        linearTitle = (LinearLayout)rootView.findViewById(R.id.linearTitlesInReports);
        linearDetail = (LinearLayout)rootView.findViewById(R.id.linearDetailInReports);
        spinfactor = (Spinner) rootView.findViewById(R.id.spinFactor);
        spinrange = (Spinner) rootView.findViewById(R.id.spinRange);
        lv1 = (ListView) rootView.findViewById(R.id.ReportsList);
        lv2 = (ListView) rootView.findViewById(R.id.ReportsLeftList);
        lv3 = (ListView) rootView.findViewById(R.id.ReportsRightList);
        FromDate = (TextView) rootView.findViewById(R.id.fromDate);
        ToDate = (TextView) rootView.findViewById(R.id.toDate);

        TextView txtGen= (TextView)rootView.findViewById(R.id.txtGenReportInReports);
        txtGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetPdf().execute();
            }
        });


        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("myLog", "position:" + position);
                int rid = MasterCache.reports_id.get(position);
                GetAvailableColumn(rid);
                GetSelectedColumn(rid);
                GetReportsCriteria(rid);

            }
        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, search);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinrange.setAdapter(dataAdapter);

        FromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(FromDate);
            }
        });

        ToDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(ToDate);
            }
        });


    }

    public void showDatePickerDialog(final TextView txtView) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        txtView.setText((dayOfMonth + 1) + "/" + monthOfYear + "/" + year);

                    }
                }, year, month, day);
        dpd.show();


    }


    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(
                WebView view, String url) {
            return(false);
        }
    }

    public class GetPdf extends AsyncTask<Void,Void,String> {

        @Override
        protected String doInBackground(Void... params) {
            generateReport();
            return "test";
        }

        @Override
        protected void onPostExecute(String option)
        {
            showPdf();
        }
    }

    public void generateReport(){
        Log.i("myLog", "generateReport");
        String extStorageDirectory = Environment.getExternalStorageDirectory()
                .toString();
        File folder = new File(extStorageDirectory, "pdf");
        folder.mkdir();
        File file = new File(folder, "Read.pdf");
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        FileDownloader.DownloadFile("http://54.179.167.160:8080/Yarraa/reports/exportCaseLogReportPDF", file);
        Log.i("myLog", "generateReport aft3r download");


    }

    public void showPdf()
    {
        Log.i("myLog","showpdf");
        File file = new File(Environment.getExternalStorageDirectory()+"/pdf/Read.pdf");
        PackageManager packageManager = getActivity().getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        //  List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(file);
        intent.setDataAndType(uri, "application/pdf");
        startActivity(intent);
    }



    public void GetReportsList() {
        Integer companyId = MasterCache.companyId.get(MasterCache.userId.get(0));
        String url = NetUtils.HOST+NetUtils.EXTENDED_WARRANTY_REPORTS_URL+companyId;
        Log.i("myLog", "url Reports" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "Success_Response_For_reports" + response);
                MasterCache.SaveReportsListTask(response);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.spinner_item, MasterCache.Lreports_title);
                lv1.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something...
                Log.i("myLog", "Reports Error Response");
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

    public void GetAvailableColumn(int position) {

        String url = NetUtils.HOST+NetUtils.EW_REPORTS_AVAILABLE_COLUMNS+position;
        Log.i("myLog", "url Reports" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "Success_Response_For_reportsAvailable" + response);
                MasterCache.SaveReportsAvailableCol(response);

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.spinner_item, MasterCache.display_nameAvail);
                lv2.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something...
                Log.i("myLog", "Reports Error Response");
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

    public void GetSelectedColumn(int position) {

        String url = NetUtils.HOST+NetUtils.EW_REPORTS_SELECTED_COLUMNS+position;
        Log.i("myLog", "url Reports" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "Success_Response_For_reportselected" + response);
                MasterCache.SaveReportsSelectedCol(response);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.spinner_item, MasterCache.display_nameSelect);
                lv3.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something...
                Log.i("myLog", "Reports Error Response");
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

    public void GetReportsCriteria(int position) {

        String url = NetUtils.HOST+NetUtils.EW_REPORTS_CRITERIA+position;
        Log.i("myLog", "url Reports" + url);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // do something...
                Log.i("myLog", "Success_Response_For_reportsCriteria" + response);
                MasterCache.SaveReportsCriteria(response);
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_spinner_item, MasterCache.display_nameCriter);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinfactor.setAdapter(dataAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something...
                Log.i("myLog", "Reports Error Response");
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

}
