package com.example.lab04_storage.task01.screens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;
// Import Activity Menu Auth MỚI
import com.example.lab04_storage.task04.screens.Task04AuthSelectionActivity;

/**
 * Màn hình Splash (giả lập loading 1-2 giây).
 * Đã sửa: Luôn dẫn đến Menu chọn Auth (Prefs hoặc SQLite).
 */
public class Task01SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_auth_splash);

        // Bỏ kiểm tra 'remember' cũ. Luôn dẫn đến Menu Auth.
        // Task01PrefsManager prefs = new Task01PrefsManager(this);
        // boolean remember = prefs.getSession().remember;

        new Handler().postDelayed(() -> {
            // Điều hướng sang Menu chọn Auth (Task 4)
            startActivity(new Intent(this, Task04AuthSelectionActivity.class));
            finish();
        }, 1200);
    }
}