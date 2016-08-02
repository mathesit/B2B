package com.rever.rever_b2b.views;

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
import com.rever.rever_b2b.downloader.FileDownloader;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Bharath on 7/21/2016.
 */
public class EW_Reports_Fragment extends Fragment {
    private View rootView;
    private LinearLayout linearTitle, linearDetail;
    private ListView lv2;

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
        lv2 = (ListView) rootView.findViewById(R.id.ReportsList);
        TextView txtGen= (TextView)rootView.findViewById(R.id.txtGenReportInReports);
        txtGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetPdf().execute();
            }
        });


        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("myLog", "position:" + position);


            }
        });
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
}
