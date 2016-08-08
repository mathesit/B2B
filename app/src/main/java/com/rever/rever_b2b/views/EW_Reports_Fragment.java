package com.rever.rever_b2b.views;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
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
import android.widget.Toast;

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
    private ListView ReportsMainList,ReportsAvailList,ReportsSelectedList;
    private Spinner spinfactor,spinrange;
    private TextView FromDate,ToDate,outitem,initem,allIn,allOut;
    private ArrayAdapter<String> SelectedColadapter,AvailColadapter;
    private int AvailInposition,SelectPosition;
    private String Availstr,Selectstr;

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
        ReportsMainList = (ListView) rootView.findViewById(R.id.ReportsList);
        ReportsAvailList = (ListView) rootView.findViewById(R.id.ReportsLeftList);
        ReportsSelectedList = (ListView) rootView.findViewById(R.id.ReportsRightList);
        FromDate = (TextView) rootView.findViewById(R.id.fromDate);
        ToDate = (TextView) rootView.findViewById(R.id.toDate);
        initem = (TextView) rootView.findViewById(R.id.initem);
        outitem= (TextView) rootView.findViewById(R.id.outitem);
        allIn = (TextView) rootView.findViewById(R.id.allIn);
        allOut = (TextView) rootView.findViewById(R.id.allOut);

        TextView txtGen= (TextView)rootView.findViewById(R.id.txtGenReportInReports);
        txtGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetPdf().execute();
            }
        });

        ReportsMainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("myLog", "position:" + position);
                int rid = MasterCache.reports_id.get(position);
                GetAvailableColumn(rid);
                GetSelectedColumn(rid);
                GetReportsCriteria(rid);

            }
        });

        ReportsAvailList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                view.setSelected(true);

                AvailInposition = position;
                Availstr = MasterCache.display_nameAvail.get(position);


               // ReportsAvailList.setSelection(position);
//                for (int i = 0; i < ReportsAvailList.getChildCount(); i++) {
//                    if(position == i ){
//                        ReportsAvailList.getChildAt(i).setBackgroundColor(Color.LTGRAY);
//                    }else{
//                        ReportsAvailList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
//                    }
//                }

                Log.i("myLog", "position:" + AvailInposition);


            }
        });



        ReportsSelectedList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                view.setSelected(true);


//                for (int i = 0; i < ReportsSelectedList.getChildCount(); i++) {
//                    if(position == i ){
//                        ReportsSelectedList.getChildAt(i).setBackgroundColor(Color.LTGRAY);
//                    }else{
//                        ReportsSelectedList.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
//                    }
//                }


                view.setSelected(true);

                SelectPosition = position;
                Selectstr = MasterCache.display_nameSelect.get(position);

                Log.i("myLog", "position:" + SelectPosition);

            }
        });

        initem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Availstr !=null) {
                    MasterCache.display_nameAvail.remove(AvailInposition);
                    MasterCache.display_nameSelect.add(Availstr);

                    ReportsAvailList.clearChoices();
                    for (int i = 0; i < ReportsAvailList.getCount(); i++)
                        ReportsAvailList.setItemChecked(i, false);


                    Availstr = null;
                }else{
                    Toast.makeText(getContext(), "Select Item to Swap", Toast.LENGTH_SHORT).show();
                }
                Log.i("Mylog","Display");
                AvailColadapter.notifyDataSetChanged();
                SelectedColadapter.notifyDataSetChanged();

            }
        });

        outitem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(Selectstr !=null) {
                MasterCache.display_nameSelect.remove(SelectPosition);
                MasterCache.display_nameAvail.add(Selectstr);

                    ReportsSelectedList.clearChoices();
                    for (int i = 0; i < ReportsSelectedList.getCount(); i++)
                        ReportsSelectedList.setItemChecked(i, false);

                    Selectstr = null;
                }else{
                    Toast.makeText(getContext(), "Select Item to Swap", Toast.LENGTH_SHORT).show();
                }
                Log.i("Mylog","Display");
                AvailColadapter.notifyDataSetChanged();
                SelectedColadapter.notifyDataSetChanged();
            }
        });

        allIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MasterCache.display_nameAvail.size() != 0) {
                    MasterCache.display_nameSelect.addAll(MasterCache.display_nameAvail);
                    MasterCache.display_nameAvail.clear();
                }else{
                    Toast.makeText(getContext(), "No Items to Select", Toast.LENGTH_SHORT).show();
                }
                AvailColadapter.notifyDataSetChanged();
                SelectedColadapter.notifyDataSetChanged();

            }
        });

        allOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MasterCache.display_nameSelect.size() != 0) {
                    MasterCache.display_nameAvail.addAll(MasterCache.display_nameSelect);
                    MasterCache.display_nameSelect.clear();

                }else{
                    Toast.makeText(getContext(), "No Items to Select", Toast.LENGTH_SHORT).show();
                }
                AvailColadapter.notifyDataSetChanged();
                SelectedColadapter.notifyDataSetChanged();

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
                ReportsMainList.setAdapter(adapter);

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

                AvailColadapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.spinner_item, MasterCache.display_nameAvail);
                ReportsAvailList.setAdapter(AvailColadapter);

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
                SelectedColadapter = new ArrayAdapter<String>(getActivity(),
                        R.layout.spinner_item, MasterCache.display_nameSelect);
                ReportsSelectedList.setAdapter(SelectedColadapter);

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
