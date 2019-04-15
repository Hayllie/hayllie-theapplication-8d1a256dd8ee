package com.example.pc.theapplication;
//comment sdsds

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.pc.theapplication.Helpers.SharedprefManager;
import com.example.pc.theapplication.Helpers.UserModel;
import com.example.pc.theapplication.Helpers.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {

    EditText et_mobile, et_password;
    public static String LOGIN_URL = "http://branding-kitchen.com/ba/users.php?login";


    public void Login (View view){
        final String mobile = et_mobile.getText().toString();
        final String password = et_password.getText().toString();

        if (TextUtils.isEmpty(mobile)) {
            et_mobile.setError("الرجاء إدخال رقم الموبايل");
            et_mobile.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            et_password.setError("الرجاء إدخال كلمة السر");
            et_password.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);

                            if (obj.getBoolean("status")) {
                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                JSONObject userJson = obj.getJSONObject("user");

                                UserModel user = new UserModel(
                                        userJson.getString("ID"),
                                        userJson.getString("username"),
                                        userJson.getString("password"),
                                        userJson.getString("phone"),
                                        userJson.getString("branch"),
                                        userJson.getString("country"),
                                        userJson.getInt("admin")

                                        );

                                SharedprefManager.getInstance(getApplicationContext()).userLogin(user);

                                finish();
                                startActivity(new Intent(getApplicationContext(), ManageAccountActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("sorry"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("password", password);
                    params.put("phone", mobile);
                    return params;
                }
            };

            VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slideup,R.anim.nochange);
        //overridePendingTransition(R.anim.fadein, R.anim.fadeout); //fade in activity
        //overridePendingTransition(R.anim.no_change,R.anim.slide_down_info); //slidedown activity
        setContentView(R.layout.activity_main);

        if (SharedprefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, ManageAccountActivity.class));
        }


        et_mobile = findViewById(R.id.et_mobile);
        et_password = findViewById(R.id.et_password);

    }




}
