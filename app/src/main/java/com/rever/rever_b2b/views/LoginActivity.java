package com.rever.rever_b2b.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rever.rever_b2b.R;
import com.rever.rever_b2b.utils.MasterCache;
import com.rever.rever_b2b.utils.NetUtils;
import com.rever.rever_b2b.utils.SharedPreferenceManager;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText edtUser, edtPwd;
    private Button btnLogin;
    private View contentView;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.logo);
        initViews();
    }

<<<<<<< HEAD
    private void initViews(){
        edtUser = (EditText)findViewById(R.id.edtUserInLogin);
        edtPwd = (EditText)findViewById(R.id.edtPwdInLogin);
        btnLogin = (Button)findViewById(R.id.btnLoginInLogin);

          //    edtUser.setText("hemab2b@gmail.com");
        //edtPwd.setText("password");
=======
    private void initViews() {
        edtUser = (EditText) findViewById(R.id.edtUserInLogin);
        edtPwd = (EditText) findViewById(R.id.edtPwdInLogin);
        btnLogin = (Button) findViewById(R.id.btnLoginInLogin);
//        edtPwd.setText(edtPwd.getText().toString());
  //      edtUser.setText(edtUser.getText().toString());
     //   edtUser.setText("andymay2@fixers.com");
      edtUser.setText("ssewadmin@starshield.sg");
     // edtUser.setText("warrext@daves2.com");
       //       edtPwd.setText("davidbede");
        //      edtPwd.setText("123@Service");

       // edtUser.setText("challenger_service@yarraa.com");
        //    edtUser.setText("ssewadmin@starshield.sg");
        //edtPwd.setText("123@Service");
       edtPwd.setText("Password@123");
        //  edtPwd.setText("davidbede");
    }
>>>>>>> origin

        edtUser.setText("ssewadmin@starshield.sg");
        edtPwd.setText("Password@123");
    }

    public void loginUser(View v){
        // Data:{"email":"andymay2@fixers.com","pwd":"davidbede"}
        String user= edtUser.getText().toString();
        String pwd = edtPwd.getText().toString();
        if(user.length()==0){
            Snackbar.make(v, "Please enter the Username", Snackbar.LENGTH_SHORT).show();
        } else if(pwd.length() == 0) {
            Snackbar.make(v, "Please enter the Password", Snackbar.LENGTH_SHORT).show();
        } else {
            HashMap<String,String> map = new HashMap<>();
            map.put("email",user);
            map.put("pwd", pwd);
            try {
                String data = NetUtils.getPostDataString(map);
                new CheckUserTask().execute("users/login", data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public class CheckUserTask extends AsyncTask<String,Void,String>{
        ProgressDialog progressDialog;
        //declare other objects as per your need
        @Override
        protected void onPreExecute()
        {
            progressDialog= ProgressDialog.show(LoginActivity.this, "Logging In", "Loading...", true);

            //do initialization of required objects objects here
        };
        @Override
        protected String doInBackground(String... params) {
            String url = params[0];
            String data = params[1];
            String resp = NetUtils.sendCommand(LoginActivity.this,url,data,"POST");
            Log.i("myLog", "Response:" + resp);

            MasterCache.saveUserCache(resp);

            return resp;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("myLog", "Response:" + result);
            progressDialog.setMessage("Validating User...");
            if(MasterCache.userId.size()>0) {
                int userId = MasterCache.userId.get(0);
                ReverApplication.userId = userId;
                String userType = MasterCache.userType.get(userId);
                String sessionToken = MasterCache.userSessionToken.get(userId);
                SharedPreferenceManager.setString(SharedPreferenceManager.SESSION_TOKEN, sessionToken);
                SharedPreferenceManager.setString(SharedPreferenceManager.USER_TYPE, userType);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                progressDialog.setMessage("Welcome " + MasterCache.userFirstName.get(userId));

            }else{
                Toast.makeText(LoginActivity.this,result, Toast.LENGTH_SHORT).show();
            }
        }
    }

}

