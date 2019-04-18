package com.example.pc.theapplication.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.pc.theapplication.R;
import com.example.pc.theapplication.normalSaderatActivity;

public class NetworkChangeReceiver extends BroadcastReceiver{


    @Override
    public void onReceive(final Context context, final Intent intent) {

        String status = NetworkUtil.getConnectivityStatusString(context);

        Toast.makeText(context, status, Toast.LENGTH_LONG).show();

        if (status == "Not connected to Internet"){
            Intent intentx = new Intent();
            intentx.setClassName("com.example", "com.example.pc.theapplication.NoConnectionActivity");
            intentx.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentx);

/*            Intent intent = new Intent(context, normalSaderatActivity.class);
            context.startActivity(intent);*/
        }


    }
}
