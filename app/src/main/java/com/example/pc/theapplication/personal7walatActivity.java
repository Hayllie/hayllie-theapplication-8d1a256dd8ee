package com.example.pc.theapplication;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class personal7walatActivity extends AppCompatActivity {

    Spinner spinner;
    String URL = "http://branding-kitchen.com/ba/getData.php";
    ArrayList<String> AccountName;
    String account;
    TextView tv_receiverbranchValue, tv_lib, tv_egy;
    EditText et_amountTransferedValue, et_notes;
    Boolean Clicked = false;

    String UserIn, BranchIn, WayDef;
    String amount, notes;

    String urlSubmit = "http://branding-kitchen.com/ba/pertrans.php";


    public void submit(View view) {

        if (Clicked==true) {


            RequestQueue MyRequestQueue = Volley.newRequestQueue(this);


            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, urlSubmit, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getBoolean("status") == true) {
                            Toast toast = Toast.makeText(getApplicationContext(), "تم تسجيل الإيراد", Toast.LENGTH_SHORT);
                            toast.setMargin(50, 50);
                            toast.show();

                        } else {
                            Toast toast = Toast.makeText(getApplicationContext(), "الرجاء مراجعة اتصالك بالانترنت والتأكد من ملئ جميع الخانات", Toast.LENGTH_LONG);
                            toast.show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("auth", "persontrans");
                    MyData.put("price", amount);
                    MyData.put("name", account);
                    MyData.put("way", WayDef);
                    MyData.put("branchIn", BranchIn);
                    MyData.put("notes", notes);
                    MyData.put("userIn", UserIn);

                    return MyData;
                }
            };


            MyRequestQueue.add(MyStringRequest);
        } else{
        Toast toast = Toast.makeText(getApplicationContext(), "الرجاء اختيار إحدى العملتين", Toast.LENGTH_SHORT);
        toast.show();
    }

}




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal7walat);

        tv_receiverbranchValue = findViewById(R.id.tv_receiverbranchValue);
        et_amountTransferedValue = findViewById(R.id.et_amountTransferedValue);
        tv_egy = findViewById(R.id.tv_egy);
        tv_lib = findViewById(R.id.tv_lib);
        et_notes = findViewById(R.id.et_notes);
        UserModel userModel = SharedprefManager.getInstance(getApplicationContext()).getUser();



        UserIn = userModel.getUsername();
        BranchIn = userModel.getBranch();


        tv_receiverbranchValue.setText(BranchIn);
        amount = et_amountTransferedValue.toString();
        notes = et_notes.toString();

        AccountName=new ArrayList<>();
        spinner=(Spinner)findViewById(R.id.sp_personalaccountValue);
        loadSpinnerData(URL);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                account =   spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
/*
                Toast.makeText(getApplicationContext(),account,Toast.LENGTH_LONG).show();
*/
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });


        tv_lib.setClickable(true);
        tv_egy.setClickable(true);

        tv_lib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicked = true;
                tv_lib.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                tv_egy.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                WayDef = "inEgypt";
            }
        });
        tv_egy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clicked = true;
                tv_egy.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                tv_lib.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                WayDef = "outEgypt";

            }
        });
    }

    private void loadSpinnerData(String url) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
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
                    spinner.setAdapter(new ArrayAdapter<String>(personal7walatActivity.this, android.R.layout.simple_spinner_dropdown_item, AccountName));
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
        tv_lib.setBackground(ContextCompat.getDrawable(personal7walatActivity.this, R.drawable.greyroundedcorners));
        tv_egy.setBackground(ContextCompat.getDrawable(personal7walatActivity.this, R.drawable.greyroundedcorners));

        Intent intent = new Intent(this,n7walatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);

    }
}
