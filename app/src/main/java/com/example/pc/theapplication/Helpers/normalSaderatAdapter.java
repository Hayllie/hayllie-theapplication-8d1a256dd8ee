package com.example.pc.theapplication.Helpers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pc.theapplication.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class normalSaderatAdapter extends RecyclerView.Adapter<normalSaderatAdapter.MyViewHolder>  {

    private List<NormalSaderModel> normalSaderatList;
    private Context mContext;
    String URLdone = "http://branding-kitchen.com/ba/donetrans.php";


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_receiver, tv_amountTransferred, tv_amountToBeReceived;
        public Button btn_done;
        public String trans_ID, userOut;


        public MyViewHolder(View view) {
            super(view);
            tv_receiver = (TextView) view.findViewById(R.id.tv_receiver);
            tv_amountTransferred = (TextView) view.findViewById(R.id.tv_amountTransferred);
            tv_amountToBeReceived = (TextView) view.findViewById(R.id.tv_amountToBeReceived);
            btn_done = (Button) view.findViewById(R.id.btn_done);

            btn_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RequestQueue MyRequestQueue = Volley.newRequestQueue(mContext);


                    StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URLdone, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            normalSaderatList.remove(getPosition());
                            notifyItemRemoved(getPosition());
                            notifyItemRangeChanged(getPosition(), normalSaderatList.size());

                            Toast toast = Toast.makeText(mContext,"تم التسجيل",Toast.LENGTH_SHORT);
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
                            MyData.put("auth", "doneOut");
                            MyData.put("trans_id", trans_ID);
                            MyData.put("userOut", userOut);

                            return MyData;
                        }
                    };

                    MyRequestQueue.add(MyStringRequest);
                }
            });
        }
    }
    public normalSaderatAdapter(Context mContext,List<NormalSaderModel> normalSaderatList) {
        this.normalSaderatList = normalSaderatList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_normalsaderat_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NormalSaderModel normalSaderModel = normalSaderatList.get(position);
        holder.tv_receiver.setText(normalSaderModel.getName());
        holder.tv_amountTransferred.setText(normalSaderModel.getInPrice());
        holder.tv_amountToBeReceived.setText(normalSaderModel.getOutPrice());
        holder.trans_ID = normalSaderModel.getID();
        holder.userOut = normalSaderModel.getUserOut();
    }

    @Override
    public int getItemCount() {
        return normalSaderatList.size();
    }

}
