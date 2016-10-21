package com.mantambakberas.iamantaras.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mantambakberas.iamantaras.R;
import com.mantambakberas.iamantaras.helper.SessionManager;

public class SplashActivity extends AppCompatActivity {

    SessionManager session;
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        session = new SessionManager(getApplicationContext());

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);


        if(session.isLoggedIn()) {
            Intent ii = new Intent(this, LoginActivity.class);
            startActivity(ii);
            finish();
        }


    }
}
