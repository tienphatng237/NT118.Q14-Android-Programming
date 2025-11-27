package com.example.lab04_storage.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;
import com.example.lab04_storage.task01.data.Task01UserSession;
import com.example.lab04_storage.task01.screens.Task01ProfileActivity;

import com.example.lab04_storage.task02.ui.main.Task02MainActivity;

import com.example.lab04_storage.task03.ui.Task03ContainerActivity;

import com.example.lab04_storage.task04.screens.Task04ClassManagerActivity;
import com.google.android.material.button.MaterialButton;

/**
 * MainActivity – Màn hình điều hướng chung cho cả 4 bài
 * -----------------------------------------------------
 * Thay thế hoàn toàn Task01MainActivity cũ.
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvSearchHint;
    private Task01PrefsManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task_manager_main);

        // ==========================
        // Load Session từ Task01
        // ==========================
        prefs = new Task01PrefsManager(this);
        Task01UserSession session = prefs.getSession();

        tvSearchHint = findViewById(R.id.tv_search_hint);
        MaterialButton btnAdd = findViewById(R.id.btn_add_small);

        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navTasks = findViewById(R.id.nav_tasks);
        LinearLayout navNoteTask3 = findViewById(R.id.nav_note_task3);
        LinearLayout navDbTask4 = findViewById(R.id.nav_db_task4);
        LinearLayout navProfile = findViewById(R.id.nav_profile);

        // ==========================
        // Set Username
        // ==========================
        if (session != null && session.username != null) {
            tvSearchHint.setText("Xin chào: " + session.username);
        } else {
            tvSearchHint.setText("Xin chào: User");
        }

        // ==========================
        // Buttons
        // ==========================
        btnAdd.setOnClickListener(v -> {
            // Không xử lý logout tại đây nữa
        });

        // ==========================
        // Điều hướng
        // ==========================

        navHome.setOnClickListener(v -> {
            // đang ở Home
        });

        navTasks.setOnClickListener(v -> {
            startActivity(new Intent(this, Task02MainActivity.class));
        });

        navNoteTask3.setOnClickListener(v -> {
            startActivity(new Intent(this, Task03ContainerActivity.class));
        });

        navDbTask4.setOnClickListener(v -> {
            startActivity(new Intent(this, Task04ClassManagerActivity.class));
        });

        navProfile.setOnClickListener(v -> {
            startActivity(new Intent(this, Task01ProfileActivity.class));
        });
    }
}
