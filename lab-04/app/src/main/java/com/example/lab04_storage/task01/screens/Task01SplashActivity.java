package com.example.lab04_storage.task01.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;

/**
 * Màn hình Splash (giả lập loading 1-2 giây).
 * Nếu remember = true → đi thẳng vào Main.
 * Nếu không → vào Login.
 */
public class Task01SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_auth_splash);

        Task01PrefsManager prefs = new Task01PrefsManager(this);
        boolean remember = prefs.getSession().remember;

        new Handler().postDelayed(() -> {
            if (remember) {
                startActivity(new Intent(this, Task01MainActivity.class));
            } else {
                startActivity(new Intent(this, Task01LoginActivity.class));
            }
            finish();
        }, 1200);
    }
}
