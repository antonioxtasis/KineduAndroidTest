package com.antoniocordova.kineduandroidtest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.antoniocordova.kineduandroidtest.R;
import com.antoniocordova.kineduandroidtest.ui.main.MainActivity;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 1400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                Animatoo.animateInAndOut(SplashActivity.this);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(timerTask, SPLASH_SCREEN_DELAY);
    }
}
