package com.example.pc.theapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import static com.example.pc.theapplication.ManageAccountActivity.getColorWrapper;

public class nSaderatActivity extends AppCompatActivity {


    public void gotoNormal (View view){
        Intent intent = new Intent(getApplicationContext(),normalSaderatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);

    }
    public void gotoPostal (View view){
        Intent intent = new Intent(getApplicationContext(),postalSaderatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);

    }
    public void gotoPersonal (View view){
        Intent intent = new Intent(getApplicationContext(),personalSaderatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);
        setContentView(R.layout.activity_n_saderat);

        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getColorWrapper(this, R.color.colorWhite));
        }
    }

    public void onBackPressed()
    {
        Intent intent = new Intent(this,ManageAccountActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);

    }
}
