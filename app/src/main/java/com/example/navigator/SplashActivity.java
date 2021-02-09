package com.example.navigator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;


public class SplashActivity extends AppCompatActivity {
    Handler handler;
    int limit = 2;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        onEverySecond.run();
    }

    Runnable onEverySecond = new Runnable() {
        @Override
        public void run() {
            count++;
            if (count == limit) {
                Intent intent = new Intent(SplashActivity.this, login.class);
                startActivity(intent);
            } else {
                handler.postDelayed(onEverySecond, 5000);
            }
        }
    };
}