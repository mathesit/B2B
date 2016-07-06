package com.rever.rever_b2b.views;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.rever.rever_b2b.utils.SharedPreferenceManager;

/**
 * Created by Oviya on 6/3/2016.
 */
public class ReverApplication extends Application {

    public static int  userId;

    public static  String userType;

    private static Context context;

    public static int width, height;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        SharedPreferenceManager.initializePreferenceManager(PreferenceManager.getDefaultSharedPreferences(this));
    }

    public static boolean isUserLoggedIn() {
        return SharedPreferenceManager.getString(SharedPreferenceManager.SESSION_TOKEN, null) != null;
    }

    public static String getSessionToken() {
        return SharedPreferenceManager.getString(SharedPreferenceManager.SESSION_TOKEN, null);
    }

    public static String getUserType() {
        return SharedPreferenceManager.getString(SharedPreferenceManager.USER_TYPE, null);
    }

}

