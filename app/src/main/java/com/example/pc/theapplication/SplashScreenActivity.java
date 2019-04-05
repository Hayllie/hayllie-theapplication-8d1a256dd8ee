package com.example.pc.theapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
 TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        tv_title = findViewById(R.id.tv_title);

        Animation titleAnimation = AnimationUtils.loadAnimation(this,R.anim.titletransition);
        tv_title.startAnimation(titleAnimation);

        Thread splashScreenThread = new Thread(){
            @Override
            public void  run () {
                try {
                    sleep(1000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();


                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        splashScreenThread.start();
    }
}
