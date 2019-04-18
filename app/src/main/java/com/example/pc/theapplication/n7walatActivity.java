package com.example.pc.theapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import static com.example.pc.theapplication.ManageAccountActivity.getColorWrapper;

public class n7walatActivity extends AppCompatActivity {


    public void gotoNormal (View view){
        Intent intent = new Intent(getApplicationContext(),normal7walatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);

    }
    public void gotoPostal (View view){
        Intent intent = new Intent(getApplicationContext(),postal7walatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);

    }
    public void gotoLocal (View view){
        Intent intent = new Intent(getApplicationContext(),local7walatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);

    }
     public void gotoPersonal (View view){
        Intent intent = new Intent(getApplicationContext(),personal7walatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);

     }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);
*/
        setContentView(R.layout.activity_n7walat);

        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getColorWrapper(this, R.color.colorWhite));
        }

    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this,ManageAccountActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);
    }
}
