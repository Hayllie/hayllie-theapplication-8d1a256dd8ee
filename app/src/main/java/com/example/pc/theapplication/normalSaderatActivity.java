package com.example.pc.theapplication;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.theapplication.Helpers.NormalSaderModel;
import com.example.pc.theapplication.Helpers.SharedprefManager;
import com.example.pc.theapplication.Helpers.UserModel;
import com.example.pc.theapplication.Helpers.normalSaderatAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class normalSaderatActivity extends AppCompatActivity {

    private List<NormalSaderModel> normalSaderatList = new ArrayList<>();
    private RecyclerView recyclerView;
    private normalSaderatAdapter mAdapter;
    String UserIn, UserOut, BranchIn, BranchOut, ID, name, inPrice, equal, outPrice, date, notes, state;
    String URL= "http://branding-kitchen.com/ba/getTrans.php";
    String Current_Branch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_saderat);

/*
        myPreference= new MyPreference(this);
*/
        UserModel userModel = SharedprefManager.getInstance(getApplicationContext()).getUser();
        Current_Branch = userModel.getBranch();


        recyclerView = (RecyclerView) findViewById(R.id.rv_normal_saderat);
        mAdapter = new normalSaderatAdapter(this,normalSaderatList);
        RecyclerView.LayoutManager mLayoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        fetchdata();

    }

    private void fetchdata(){
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest
                (Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject =new JSONObject(response);
                            if(jsonObject.getBoolean("status")==true) {
                                JSONArray jsonArray = jsonObject.getJSONArray("trans");
                                for (int l = 0; l <= jsonArray.length(); l++) {
                                    JSONObject object = jsonArray.getJSONObject(l);

                                    ID = object.getString("ID");
                                    name = object.getString("name");
                                    inPrice = object.getString("inPrice");
                                    equal = object.getString("equal");
                                    outPrice = object.getString("outPrice");
                                    BranchIn = object.getString("branchIn");
                                    BranchOut = object.getString("branchOut");
                                    date = object.getString("date");
                                    notes = object.getString("notes");
                                    state = object.getString("state");
                                    UserIn = object.getString("userIn");
                                    UserOut = object.getString("userOut");

                                    NormalSaderModel postalSader = new NormalSaderModel("" + ID, "" + name, "" + inPrice, "" + equal, "" + outPrice, "" + BranchIn, "" + BranchOut, "" + date, "" + notes, "" + state, "" + UserIn, "" + UserOut);
                                    normalSaderatList.add(postalSader);
                                    mAdapter.notifyDataSetChanged();


                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("Error","Message",e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error != null){
                            Log.e("Error","Message",error);
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("auth", "gettrans");
                params.put("type", "outtrans");
                params.put("branch", Current_Branch);
                return params;
            }
        };
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);

    }


}
