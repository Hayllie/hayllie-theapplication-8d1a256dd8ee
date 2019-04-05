package com.example.pc.theapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class personalSaderatActivity extends AppCompatActivity {


    Spinner spinner;
    String URL="http://branding-kitchen.com/ba/getData.php";
    String URLGetAccVals = "http://branding-kitchen.com/ba/pertrans.php";
    ArrayList<String> AccountName;
    String account, way;
    TextView tv_currency_egValue, tv_currency_liValue;
    Boolean Clicked = false;
    LinearLayout liBox, egBox;

    public void registerSader (View view){
        if (Clicked) {
            Intent intent = new Intent(this, personalSaderat2Activity.class);
            intent.putExtra("account", account);
            intent.putExtra("way", way);


            startActivity(intent);
            overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_saderat);

        AccountName=new ArrayList<>();
        spinner=(Spinner)findViewById(R.id.sp_accountValue);
        tv_currency_egValue = findViewById(R.id.tv_currency_egValue);
        tv_currency_liValue = findViewById(R.id.tv_currency_liValue);

        liBox = findViewById(R.id.liBox);
        egBox = findViewById(R.id.egBox);

        liBox.setClickable(false);
        egBox.setClickable(false);

        loadSpinnerData(URL);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                account =   spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

                GetAccValues(URLGetAccVals);
                liBox.setClickable(true);
                egBox.setClickable(true);

                liBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Clicked = true;
                        liBox.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        way = "outEgypt";
                    }
                });
                egBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Clicked = true;
                        egBox.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        way = "inEgypt";

                    }
                });

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tv_currency_egValue.setText("-");
                tv_currency_liValue.setText("-");
            }
        });
    }

    private  void GetAccValues(String url){

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getBoolean("status")==true){
                        tv_currency_egValue.setText(jsonObject.getString("egy"));
                        tv_currency_liValue.setText(jsonObject.getString("notegy"));
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
                params.put("auth", "getpermon");
                params.put("name", account);

                //
                return params;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }
    private void loadSpinnerData(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getBoolean("status")==true){
                        JSONArray jsonArray=jsonObject.getJSONArray("person");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String id=jsonObject1.getString("ID");
                            String account=jsonObject1.getString("name");
/*
                            String country=jsonObject1.getString("country");
*/
                            AccountName.add(account);
                        }
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(personalSaderatActivity.this, android.R.layout.simple_spinner_dropdown_item, AccountName));
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
                params.put("acc", "");
                //
                return params;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    public void onBackPressed()
    {
        Intent intent = new Intent(this,nSaderatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);

    }
}
