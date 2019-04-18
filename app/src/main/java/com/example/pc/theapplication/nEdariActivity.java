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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class nEdariActivity extends AppCompatActivity {

    EditText et_amountValue, et_descriptionValue, et_notes;
    TextView tv_receiverbranchValue;

    String UserIn, BranchIn;
    String amount, description;

    String urlSubmit = "http://branding-kitchen.com/ba/mangtrans.php";

    public void submit (View view){

            RequestQueue MyRequestQueue = Volley.newRequestQueue(this);


                StringRequest MyStringRequest = new StringRequest(Request.Method.POST, urlSubmit, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getBoolean("status") == true) {
                            Toast toast = Toast.makeText(getApplicationContext(), "تم التسجيل", Toast.LENGTH_SHORT);
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
                    MyData.put("auth", "mangTrans");
                    MyData.put("price", amount);
                    MyData.put("reason", description);
                    MyData.put("branch", BranchIn);
                    MyData.put("user", UserIn);

                    return MyData;
                }

            };


            MyRequestQueue.add(MyStringRequest);

        }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_edari);

        et_amountValue = findViewById(R.id.et_amountValue);
        et_descriptionValue = findViewById(R.id.et_descriptionValue);
        et_notes = findViewById(R.id.et_notes);


        tv_receiverbranchValue = findViewById(R.id.tv_receiverbranchValue);
        UserModel userModel = SharedprefManager.getInstance(getApplicationContext()).getUser();


        UserIn = userModel.getUsername();
        BranchIn = userModel.getBranch();

        tv_receiverbranchValue.setText(BranchIn);

        amount = et_amountValue.toString();
        description = et_descriptionValue.toString();
    }

    public void onBackPressed()
    {
        Intent intent = new Intent(this,ManageAccountActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
    }
}
