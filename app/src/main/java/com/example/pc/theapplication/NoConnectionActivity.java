package com.example.pc.theapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NoConnectionActivity extends AppCompatActivity {

    public void retry(View view){
/*        Intent intent = new Intent();
        intent.setAction("com.example.pc.theapplication");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slideup,R.anim.nochange);
        setContentView(R.layout.activity_no_connection);

    }
}
