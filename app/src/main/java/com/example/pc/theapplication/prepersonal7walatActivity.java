package com.example.pc.theapplication;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import static com.example.pc.theapplication.ManageAccountActivity.getColorWrapper;

public class prepersonal7walatActivity extends AppCompatActivity {

    TextView tv_lib;
    TextView tv_egp;

    public void passEgp (View view){

        tv_egp.setBackground(ContextCompat.getDrawable(prepersonal7walatActivity.this, R.drawable.blueroundedcorners));
        Intent intent = new Intent(getApplicationContext(),personal7walatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);

    }

    public void passLib (View view){

        tv_lib.setBackground(ContextCompat.getDrawable(prepersonal7walatActivity.this, R.drawable.blueroundedcorners));
        Intent intent = new Intent(getApplicationContext(),personal7walatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinleft, R.anim.slideoutright);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepersonal7walat);

        //changing statusbar color
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getColorWrapper(this, R.color.colorWhite));

            tv_lib = findViewById(R.id.txt_lib);
            tv_egp = findViewById(R.id.txt_egp);

            tv_lib.setBackground(ContextCompat.getDrawable(prepersonal7walatActivity.this, R.drawable.greyroundedcorners));
            tv_egp.setBackground(ContextCompat.getDrawable(prepersonal7walatActivity.this, R.drawable.greyroundedcorners));

        }


}
    @Override
    public void onBackPressed()
    {
        tv_lib.setBackground(ContextCompat.getDrawable(prepersonal7walatActivity.this, R.drawable.greyroundedcorners));
        tv_egp.setBackground(ContextCompat.getDrawable(prepersonal7walatActivity.this, R.drawable.greyroundedcorners));

        Intent intent = new Intent(this,n7walatActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideinright,R.anim.slideoutleft);

    }
}
