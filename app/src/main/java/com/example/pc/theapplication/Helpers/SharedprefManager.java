package com.example.pc.theapplication.Helpers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.pc.theapplication.SplashScreenActivity;

public class SharedprefManager {

    private static final String SHARED_PREF_NAME = "TheApplication";
    private static final String KEY_ID = "keyid";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_PASSWORD = "keypassword";
    private static final String KEY_PHONE = "keyphone";
    private static final String KEY_BRANCH = "keybranch";
    private static final String KEY_COUNTRY = "keycountry";
    private static final String KEY_ADMIN = "KEY_ADMIN";


    private static SharedprefManager mInstance;
    private static Context mCtx;

    private SharedprefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedprefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedprefManager(context);
        }
        return mInstance;
    }


    public void userLogin(UserModel user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, user.getID());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_PHONE, user.getPhone());
        editor.putString(KEY_BRANCH, user.getBranch());
        editor.putString(KEY_COUNTRY, user.getCountry());
        editor.putString(KEY_ADMIN, user.getAdmin());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    public UserModel getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new UserModel(
                sharedPreferences.getString(KEY_ID, null),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_PHONE, null),
                sharedPreferences.getString(KEY_BRANCH, null),
                sharedPreferences.getString(KEY_COUNTRY, null),
                sharedPreferences.getString(KEY_ADMIN, null)


                );
    }

    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, SplashScreenActivity.class));
    }

}
