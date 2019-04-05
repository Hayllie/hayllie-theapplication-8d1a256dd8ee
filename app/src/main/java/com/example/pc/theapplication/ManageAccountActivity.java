package com.example.pc.theapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.theapplication.Helpers.SharedprefManager;
import com.example.pc.theapplication.Helpers.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ManageAccountActivity extends AppCompatActivity {


    TextView tv_sayhi, textToAppear, tv_branchCreditValue;
    LinearLayout pView;
    String URL = "http://branding-kitchen.com/ba/branchm.php";
    String admin, branchIn;

    public static int getColorWrapper(Context context, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return context.getColor(id);
        } else {
            //noinspection deprecation
            return context.getResources().getColor(id);
        }
    }

    public void goto7walat (View view){
        Intent intent = new Intent(getApplicationContext(),n7walatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);
    }
    public void gotoSaderat (View view){
        Intent intent = new Intent(getApplicationContext(),nSaderatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);
    }
    public void gotoEdari (View view){
        Intent intent = new Intent(getApplicationContext(),nEdariActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);
    }
    public void Logout (View view){
        Intent intent = new Intent(getApplicationContext(),SplashScreenActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.nochange,R.anim.slidedown);
        finish();

        SharedprefManager.getInstance(getApplicationContext()).logout();

        Toast toast=Toast.makeText(getApplicationContext(),"تم تسجيل الخروج",Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);


        tv_sayhi = findViewById(R.id.tv_sayhi);
        pView = findViewById(R.id.pView);
        textToAppear = findViewById(R.id.textToAppear);
        tv_branchCreditValue = findViewById(R.id.tv_branchCreditValue);

        UserModel userModel = SharedprefManager.getInstance(getApplicationContext()).getUser();
        tv_sayhi.setText("أهلا " + userModel.getUsername());

        admin = userModel.getAdmin();
        if ( admin == "1"){
            pView.setClickable(true);
            tv_branchCreditValue.setVisibility(View.INVISIBLE);
            textToAppear.setText("أرصدة الفروع");
        } else {
            pView.setClickable(false);
            textToAppear.setText("رصيد الفرع");
            branchIn = userModel.getBranch();
            tv_branchCreditValue.setVisibility(View.VISIBLE);
            getThisBranch(URL);
        }

        pView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);
            }
        });

        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getColorWrapper(this,R.color.colorWhite));

        }
    }

    private void getThisBranch(String url){
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean("status")==true){
                        tv_branchCreditValue.setText(jsonObject.getString("money"));
                    }
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("auth", "branchm");
                params.put("name", branchIn);
                return params;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }

}
