package com.example.pc.theapplication;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.theapplication.Helpers.SharedprefManager;
import com.example.pc.theapplication.Helpers.UserModel;

import java.util.HashMap;
import java.util.Map;

public class local7walat2Activity extends AppCompatActivity {

    Spinner spinner;
    String URL = "http://branding-kitchen.com/ba/getData.php";
    ArrayList<String> AccountName;

    String account;
    String UserIn, BranchIn, Way, WayDef;
    String amount, notes;

    String urlSubmit = "http://branding-kitchen.com/ba/intrans.php";

    EditText et_amountValue, et_notes;
    TextView tv_receivingbranchValue;

    Boolean checked;


    public Boolean checkValues() {
        checked = false;
        if (TextUtils.isEmpty(et_amountValue.toString())) {
            et_amountValue.setError("الرجاء إدخال المطلوب");
            et_amountValue.requestFocus();
            checked = true;
        }
        return checked;

    }


    public void submit (View view){

        checkValues();
        if (checked) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(this);


            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, urlSubmit, new Response.Listener<String>() {

                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("auth", "intrans");
                    MyData.put("type", "person");
                    MyData.put("name", account);
                    MyData.put("price", amount);
                    MyData.put("branchIn", BranchIn);
                    MyData.put("way", WayDef);
                    MyData.put("notes", notes);
                    MyData.put("userIn", UserIn);

                    return MyData;
                }
                @Override
                public void onResponse(String response) {

                    Toast toast = Toast.makeText(getApplicationContext(), "تم تسجيل الإيراد", Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.show();
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "الرجاء الاتصال بالانترنت ", Toast.LENGTH_LONG).show();
                }
            });


            MyRequestQueue.add(MyStringRequest);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local7walat2);

        et_amountValue =findViewById(R.id.et_amountValue);
        et_notes =findViewById(R.id.et_notes);
        tv_receivingbranchValue = findViewById(R.id.tv_receivingbranchValue);

        spinner=(Spinner)findViewById(R.id.sp_accountValue);
        loadSpinnerData(URL);

        UserModel userModel = SharedprefManager.getInstance(getApplicationContext()).getUser();

        UserIn = userModel.getUsername();
        BranchIn = userModel.getBranch();
        Way = userModel.getCountry();

        if(Way=="مصر"){
            WayDef = "outEgypt";
        } else {
            WayDef = "inEgypt";
        }

        tv_receivingbranchValue.setText(BranchIn);

        amount = et_amountValue.toString();
        notes = et_notes.toString();

        AccountName=new ArrayList<>();

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
                    spinner.setAdapter(new ArrayAdapter<String>(local7walat2Activity.this, android.R.layout.simple_spinner_dropdown_item, AccountName));
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
        Intent intent = new Intent(this,local7walatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
    }


}
