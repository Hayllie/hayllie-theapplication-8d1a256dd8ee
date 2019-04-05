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
import com.example.pc.theapplication.Helpers.SharedprefManager;
import com.example.pc.theapplication.Helpers.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class postal7walatActivity extends AppCompatActivity {

    Spinner spinner;
    String URL="http://branding-kitchen.com/ba/getData.php";
    ArrayList<String> BranchName;
    String branch;
    TextView tv_senderbranchValue;

    String urlSubmit = " http://branding-kitchen.com/ba/barid.php";

    EditText et_receiverValue, et_nationalIDValue, et_mobilenumberValue, et_amounttransferredValue, et_transferringvaluevalue, et_amountobereceivedValue, et_notes;

    String UserIn, BranchIn, Way, WayDef;
    String name, amount, transferringValue, secamount, notes,national_Id,phone;

    Boolean checked;

    public Boolean checkValues(){
        checked = false;
        if (TextUtils.isEmpty(et_receiverValue.toString())) {
            et_receiverValue.setError("الرجاء إدخال المطلوب");
            et_receiverValue.requestFocus();
            checked = true;
        } else {
            checked = false;

        }
        if (TextUtils.isEmpty(et_nationalIDValue.toString())) {
            et_nationalIDValue.setError("الرجاء إدخال المطلوب");
            et_nationalIDValue.requestFocus();
            checked = true;
        } else {
            checked = false;

        }
        if (TextUtils.isEmpty(et_mobilenumberValue.toString())) {
            et_mobilenumberValue.setError("الرجاء إدخال المطلوب");
            et_mobilenumberValue.requestFocus();
            checked = true;
        } else {
            checked = false;

        }
        if (TextUtils.isEmpty(et_amounttransferredValue.toString())) {
            et_amounttransferredValue.setError("الرجاء إدخال المطلوب");
            et_amounttransferredValue.requestFocus();
            checked = true;
        } else {
            checked = false;

        }
        if (TextUtils.isEmpty(et_transferringvaluevalue.toString())) {
            et_transferringvaluevalue.setError("الرجاء إدخال المطلوب");
            et_transferringvaluevalue.requestFocus();
            checked = true;
        } else {
            checked = false;

        }
        if (TextUtils.isEmpty(et_amountobereceivedValue.toString())) {
            et_amountobereceivedValue.setError("الرجاء إدخال المطلوب");
            et_amountobereceivedValue.requestFocus();
            checked = true;
        } else {
            checked = false;

        }

        return checked;

    }

    public void submit (View view){

        checkValues();
        if (checked) {
            RequestQueue MyRequestQueue = Volley.newRequestQueue(this);


            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, urlSubmit, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    Toast toast = Toast.makeText(getApplicationContext(),"تم تسجيل الإيراد",Toast.LENGTH_SHORT);
                    toast.setMargin(50,50);
                    toast.show();
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("auth", "barid");
                    MyData.put("name", name);
                    MyData.put("nationalID", national_Id);
                    MyData.put("phone", phone);
                    MyData.put("inPrice", amount);
                    MyData.put("equal", transferringValue);
                    MyData.put("outPrice", secamount);
                    MyData.put("branchIn", BranchIn);
                    MyData.put("notes", notes);
                    MyData.put("userIn", UserIn);

                    return MyData;
                }
            };


            MyRequestQueue.add(MyStringRequest);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postal7walat);

        et_receiverValue = findViewById(R.id.et_receiverValue);
        et_nationalIDValue = findViewById(R.id.et_nationalIDValue);
        et_mobilenumberValue = findViewById(R.id.et_mobilenumberValue);
        et_amounttransferredValue = findViewById(R.id.et_amounttransferredValue);
        et_transferringvaluevalue = findViewById(R.id.et_transferringvaluevalue);
        et_amountobereceivedValue = findViewById(R.id.et_amountobereceivedValue);
        et_notes = findViewById(R.id.et_notes);
        tv_senderbranchValue = findViewById(R.id.tv_senderbranchValue);

        UserModel userModel = SharedprefManager.getInstance(getApplicationContext()).getUser();


        UserIn = userModel.getUsername();
        BranchIn = userModel.getBranch();

        Way = userModel.getCountry();

        if(Way=="مصر"){
            WayDef = "outEgypt";
        } else {
            WayDef = "inEgypt";
        }


        tv_senderbranchValue.setText(BranchIn);


        name = et_receiverValue.toString();
        national_Id = et_nationalIDValue.toString();
        phone = et_mobilenumberValue.toString();
        amount = et_amounttransferredValue.toString();
        transferringValue = et_transferringvaluevalue.toString();
        secamount = et_amountobereceivedValue.toString();
        notes = et_notes.toString();

        BranchName = new ArrayList<>();
        spinner=(Spinner)findViewById(R.id.sp_receiverbranchValue);
        loadSpinnerData(URL);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                branch =   spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
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
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getBoolean("status")==true){
                        JSONArray jsonArray=jsonObject.getJSONArray("branch");
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String id=jsonObject1.getString("ID");
                            String branch=jsonObject1.getString("name");
/*
                            String country=jsonObject1.getString("country");
*/
                            BranchName.add(branch);
                        }
                    }
                    spinner.setAdapter(new ArrayAdapter<String>(postal7walatActivity.this, android.R.layout.simple_spinner_dropdown_item, BranchName));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("branch", "");
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
        Intent intent = new Intent(this,n7walatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);

    }
}
