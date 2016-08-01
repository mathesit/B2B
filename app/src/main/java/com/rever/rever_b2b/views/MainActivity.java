package com.rever.rever_b2b.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rever.rever_b2b.R;
import com.rever.rever_b2b.gcm.QuickstartPreferences;
import com.rever.rever_b2b.gcm.RegistrationIntentService;


/**
 * Created by Matheswari on 3/24/2016.
 */
public class MainActivity extends AppCompatActivity {
    private LinearLayout linearQuotation, linearServiceReq, linearReports, linearDashboard, linearJobs, linearFragment;
    private ImageView imgDashboard, imgServReq, imgQuotation, imgJobs, imgReports;
    private TextView txtJobs,txtServiceReq,txtDashboard,txtQuotation,txtReports;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private boolean isReceiverRegistered;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    //static final int user_id = 0;
    private String userType,userId;
    //public int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        //toolbar.setNavigationIcon(R.drawable.logo);
        userType = ReverApplication.getUserType();
        Log.d("MYLOGGG:", userType);
        // uid = getIntent().getIntExtra("user_id",user_id);
        // Log.i("mylog", "GetIntent::" + uid);
        initViews();
        loadDashboard();
        initBroadcastReceiver();
        //  userId = getIntent().getStringExtra("User_Id");

    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver();
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        isReceiverRegistered = false;
        super.onPause();
    }

    private void registerReceiver(){
        if(!isReceiverRegistered) {
            LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                    new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
            isReceiverRegistered = true;
        }
    }

    private void initViews(){
      /* imgDashboard = (ImageView)findViewById(R.id.imgDashboardInMain);
        imgServReq = (ImageView)findViewById(R.id.imgServReqInMain);
        imgQuotation = (ImageView)findViewById(R.id.imgQuotationsInMain);
        imgJobs = (ImageView)findViewById(R.id.imgJobsInMain);
        imgReports = (ImageView)findViewById(R.id.imgReportsInMain);
        txtSR = (TextView)findViewById(R.id.txtSR);
        txtJobs = (TextView)findViewById(R.id.txtJobs);
        txtDashboard = (TextView)findViewById(R.id.textDashboard);
        txtQuotation = (TextView)findViewById(R.id.textQuotation);
        txtReports = (TextView)findViewById(R.id.textReport);
        if(userType.equalsIgnoreCase("Extended Warranty Providers")) {
            txtSR.setText("Extended Warranty");
            txtJobs.setText("Service Request");
        }

        //linearFragment = (LinearLayout)findViewById(R.id.linearLayoutInMain);
    }*/
        imgDashboard = (ImageView)findViewById(R.id.imgDashboardInMain);
        imgServReq = (ImageView)findViewById(R.id.imgServReqInMain);
        imgQuotation = (ImageView)findViewById(R.id.imgQuotationsInMain);
        imgJobs = (ImageView)findViewById(R.id.imgJobsInMain);
        imgReports = (ImageView)findViewById(R.id.imgReportsInMain);
        txtDashboard = (TextView)findViewById(R.id.txtDashboardInMain);
        txtReports = (TextView)findViewById(R.id.txtReportsInMain);
        txtQuotation = (TextView)findViewById(R.id.txtQuotationsInMain);
        txtServiceReq =(TextView) findViewById(R.id.txtServReqInMain);
        txtJobs =(TextView) findViewById(R.id.txtJobsInMain);
        if(userType.equalsIgnoreCase("Extended Warranty Providers")) {
            txtServiceReq.setText("Extended Warranty");
            txtJobs.setText("Service\nRequest");
        }

        // linearFragment = (LinearLayout)findViewById(R.id.linearLayoutInMain);
    }

    public void showDashboard(View v) {
        loadDashboard();
    }

    public void loadDashboard(){
        txtDashboard.setTextColor(ContextCompat.getColor(this, R.color.blue_txt));
        txtQuotation.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtReports.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtServiceReq.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtJobs.setTextColor(ContextCompat.getColor(this, R.color.gray_txt));

          /* if(MasterCache.userType.get(uid).equalsIgnoreCase("Extended Warranty Providers")) {
            Log.i("mylog", "uid" + uid);
            txtSR.setText("Extended Warranty");
            txtJobs.setText("Service Request");*/

        if (userType.equalsIgnoreCase("Extended Warranty Providers")) {
            imgDashboard.setImageResource(R.drawable.dashboardsel);
            imgQuotation.setImageResource(R.drawable.quotation);
            imgReports.setImageResource(R.drawable.reports);
            imgJobs.setImageResource(R.drawable.servicereq);
            imgServReq.setImageResource(R.drawable.extendedwarranty);

            DashboardExtended newFragment = new DashboardExtended();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linearFragmentInMain, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (userType.equalsIgnoreCase("Service Centers")) {
            imgDashboard.setImageResource(R.drawable.dashboardsel);
            imgQuotation.setImageResource(R.drawable.quotation);
            imgReports.setImageResource(R.drawable.reports);
            imgJobs.setImageResource(R.drawable.jobs);
            imgServReq.setImageResource(R.drawable.servicereq);

            DashboardFragment newFragment = new DashboardFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linearFragmentInMain, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void showServReq(View v){

        txtDashboard.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtQuotation.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtReports.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtJobs.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtServiceReq.setTextColor(ContextCompat.getColor(this, R.color.blue_txt));

        if(userType.equalsIgnoreCase("Extended Warranty Providers")) {
      /*      if(MasterCache.userType.get(uid).equalsIgnoreCase("Extended Warranty Providers")) {
            Log.i("mylog", "uid" + uid);
            txtSR.setText("Extended Warranty");
            txtJobs.setText("Service Request");*/
            imgDashboard.setImageResource(R.drawable.dashboard);
            imgQuotation.setImageResource(R.drawable.quotation);
            imgReports.setImageResource(R.drawable.reports);
            imgJobs.setImageResource(R.drawable.servicereq);
            imgServReq.setImageResource(R.drawable.extendedwarrantysel);
            EW_Main_Fragment newFragment = new EW_Main_Fragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linearFragmentInMain, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if(userType.equalsIgnoreCase("Service Centers")){

//        else if(MasterCache.userType.get(uid).equalsIgnoreCase("Service Centers")) {
            imgDashboard.setImageResource(R.drawable.dashboard);
            imgQuotation.setImageResource(R.drawable.quotation);
            imgReports.setImageResource(R.drawable.reports);
            imgJobs.setImageResource(R.drawable.jobs);
            imgServReq.setImageResource(R.drawable.servicereqsel);
            ServiceRequestFragment newFragment = new ServiceRequestFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linearFragmentInMain, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }
    public void showJobs(View v){
        txtDashboard.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtQuotation.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtReports.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtServiceReq.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtJobs.setTextColor(ContextCompat.getColor(this, R.color.blue_txt));

        if (userType.equalsIgnoreCase("Extended Warranty Providers")) {

       /* if(MasterCache.userType.get(uid).equalsIgnoreCase("Extended Warranty Providers")) {
            Log.i("mylog", "uid" + uid);
            txtSR.setText("Extended Warranty");
            txtJobs.setText("Service Request");*/
            imgDashboard.setImageResource(R.drawable.dashboard);
            imgQuotation.setImageResource(R.drawable.quotation);
            imgReports.setImageResource(R.drawable.reports);
            imgJobs.setImageResource(R.drawable.servicereqsel);
            imgServReq.setImageResource(R.drawable.extendedwarranty);
            ServiceRequestExtended newFragment = new ServiceRequestExtended();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linearFragmentInMain, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (userType.equalsIgnoreCase("Service Centers")) {

//            else if(MasterCache.userType.get(uid).equalsIgnoreCase("Service Centers")) {
            imgDashboard.setImageResource(R.drawable.dashboard);
            imgQuotation.setImageResource(R.drawable.quotation);
            imgReports.setImageResource(R.drawable.reports);
            imgJobs.setImageResource(R.drawable.jobssel);
            imgServReq.setImageResource(R.drawable.servicereq);

            JobsFragment newFragment = new JobsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linearFragmentInMain, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
    public void showQuotation(View v){
        txtDashboard.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtServiceReq.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtReports.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtJobs.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtQuotation.setTextColor(ContextCompat.getColor(this, R.color.blue_txt));

        if (userType.equalsIgnoreCase("Extended Warranty Providers")) {
         /*   if(MasterCache.userType.get(uid).equalsIgnoreCase("Extended Warranty Providers")) {
            Log.i("mylog", "uid" + uid);
            txtSR.setText("Extended Warranty");
            txtJobs.setText("Service Request");*/
            imgDashboard.setImageResource(R.drawable.dashboard);
            imgQuotation.setImageResource(R.drawable.quotationsel);
            imgReports.setImageResource(R.drawable.reports);
            imgJobs.setImageResource(R.drawable.servicereq);
            imgServReq.setImageResource(R.drawable.extendedwarranty);
            QuotationExtended newFragment = new QuotationExtended();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linearFragmentInMain, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else if (userType.equalsIgnoreCase("Service Centers")) {

            //      else if(MasterCache.userType.get(uid).equalsIgnoreCase("Service Centers")) {
            imgDashboard.setImageResource(R.drawable.dashboard);
            imgQuotation.setImageResource(R.drawable.quotationsel);
            imgReports.setImageResource(R.drawable.reports);
            imgJobs.setImageResource(R.drawable.jobs);
            imgServReq.setImageResource(R.drawable.servicereq);

            QuotationFragment newFragment = new QuotationFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linearFragmentInMain, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }

    }
    public void showReports(View v){
        txtDashboard.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtQuotation.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtServiceReq.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtJobs.setTextColor(ContextCompat.getColor(this ,R.color.gray_txt));
        txtReports.setTextColor(ContextCompat.getColor(this ,R.color.blue_txt));

        if (userType.equalsIgnoreCase("Extended Warranty Providers")) {
       /* if(MasterCache.userType.get(uid).equalsIgnoreCase("Extended Warranty Providers")) {
            Log.i("mylog", "uid" + uid);
            txtSR.setText("Extended Warranty");
            txtJobs.setText("Service Request");*/
            imgDashboard.setImageResource(R.drawable.dashboard);
            imgQuotation.setImageResource(R.drawable.quotation);
            imgReports.setImageResource(R.drawable.reportssel);
            imgJobs.setImageResource(R.drawable.servicereq);
            imgServReq.setImageResource(R.drawable.extendedwarranty);

            ReportFragment newFragment = new ReportFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linearFragmentInMain, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (userType.equalsIgnoreCase("Service Centers")) {

//        else if(MasterCache.userType.get(uid).equalsIgnoreCase("Service Centers")) {
            imgDashboard.setImageResource(R.drawable.dashboard);
            imgQuotation.setImageResource(R.drawable.quotation);
            imgReports.setImageResource(R.drawable.reportssel);
            imgJobs.setImageResource(R.drawable.jobs);
            imgServReq.setImageResource(R.drawable.servicereq);

            ReportFragment newFragment = new ReportFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.linearFragmentInMain, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    public void initBroadcastReceiver(){
        try {
            mRegistrationBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.i("myLog", "onReceive message");
                    try {
                        SharedPreferences sharedPreferences =
                                PreferenceManager.getDefaultSharedPreferences(context);
                        boolean sentToken = sharedPreferences
                                .getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                        if (sentToken) {
                            Log.i("myLog", "Send message");
                            //  mInformationTextView.setText(getString(R.string.gcm_send_message));
                        } else {
                            Log.i("myLog", "Error message");
                            //mInformationTextView.setText(getString(R.string.token_error_message));
                        }
                    } catch(Exception ex){

                    }
                }
            };
            registerReceiver();

            if (checkPlayServices()) {
                // Start IntentService to register this application with GCM.
                Intent intent = new Intent(this, RegistrationIntentService.class);
                startService(intent);
            }
        }catch(Exception ex){
            Log.i("myLog","initBraodcastReceiver Exception ex:"+ex.toString());
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i("myTag", "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

}
