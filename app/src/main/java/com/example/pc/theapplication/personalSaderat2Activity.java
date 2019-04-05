package com.example.pc.theapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class personalSaderat2Activity extends AppCompatActivity {


    TextView tv_branch;
    EditText et_amountValue;
    String urlSubmit = "http://branding-kitchen.com/ba/pertrans.php" ;
    String amount, BranchIn, UserIn, Way;
    Boolean checked;
    String retreiveAccount;

    public Boolean checkValues(){
        checked = false;
        if (TextUtils.isEmpty(et_amountValue.toString())) {
            et_amountValue.setError("الرجاء إدخال المطلوب");
            et_amountValue.requestFocus();
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
                    Toast toast = Toast.makeText(getApplicationContext(), "تم تسجيل الصادر", Toast.LENGTH_SHORT);
                    toast.setMargin(50, 50);
                    toast.show();
                    Intent intent = new Intent(personalSaderat2Activity.this, personalSaderatActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slideinright, R.anim.slideoutleft);
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            }) {
                protected Map<String, String> getParams() {
                    Map<String, String> MyData = new HashMap<String, String>();
                    MyData.put("auth", "sadrper");
                    MyData.put("name", retreiveAccount);
                    MyData.put("price", amount);
                    MyData.put("branchIn", BranchIn);
                    MyData.put("way", Way);
                    MyData.put("userOut", UserIn);

                    return MyData;
                }
            };


            MyRequestQueue.add(MyStringRequest);
/*  */
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_saderat2);

        Intent retreiveIntent = getIntent();
        retreiveAccount = retreiveIntent.getStringExtra("account");

        tv_branch = findViewById(R.id.tv_branch);
        et_amountValue = findViewById(R.id.et_amountValue);

        UserModel userModel = SharedprefManager.getInstance(getApplicationContext()).getUser();

        UserIn = userModel.getUsername();
        BranchIn = userModel.getBranch();

        tv_branch.setText(BranchIn);

        Way = retreiveIntent.getStringExtra("way");


        tv_branch.setText(BranchIn);

        amount = et_amountValue.toString();


    }

    public void onBackPressed()
    {
        Intent intent = new Intent(this,nSaderatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);

    }
}
