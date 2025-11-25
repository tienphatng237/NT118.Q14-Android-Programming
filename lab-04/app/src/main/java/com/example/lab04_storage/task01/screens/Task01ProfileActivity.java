package com.example.lab04_storage.task01.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;
import com.example.lab04_storage.task01.data.Task01UserSession;

public class Task01ProfileActivity extends AppCompatActivity {

    private TextView tvUsername;
    private Switch switchTheme;
    private Button btnExport, btnLogout;

    private Task01PrefsManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task01_profile);

        prefs = new Task01PrefsManager(this);

        tvUsername = findViewById(R.id.tvUsername);
        switchTheme = findViewById(R.id.switchTheme);
        btnExport = findViewById(R.id.btnExport);
        btnLogout = findViewById(R.id.btnLogout);

        // Lấy user đang đăng nhập
        Task01UserSession session = prefs.getSession();
        tvUsername.setText("Xin chào, " + session.username + "!");

        // ========== DARK MODE ==========
        switchTheme.setChecked(prefs.isDarkMode());
        switchTheme.setOnCheckedChangeListener((btn, isChecked) -> {
            prefs.setDarkMode(isChecked);

            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES :
                            AppCompatDelegate.MODE_NIGHT_NO
            );
        });

        // ========== EXPORT JSON ==========
        btnExport.setOnClickListener(v -> {
            String json = prefs.exportToJson();
            Toast.makeText(this, "JSON:\n" + json, Toast.LENGTH_LONG).show();
        });

        // ========== LOGOUT ==========
        btnLogout.setOnClickListener(v -> {
            prefs.clear();
            startActivity(new Intent(this, Task01LoginActivity.class));
            finishAffinity();
        });
    }
}
