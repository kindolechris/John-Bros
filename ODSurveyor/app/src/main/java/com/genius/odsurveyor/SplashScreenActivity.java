package com.genius.odsurveyor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent splashIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                                          SplashScreenActivity.this.startActivity(splashIntent);
                                          finish();
                                      }
                                  },
                SPLASH_SCREEN);
    }
}
