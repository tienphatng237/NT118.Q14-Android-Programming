package com.example.lab04_storage.task01.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;
import com.example.lab04_storage.task01.data.Task01UserSession;
import com.google.android.material.button.MaterialButton;

// DÒNG IMPORT CHO TASK 3
import com.example.lab04_storage.task03.ui.selection.Task03StorageSelectionFragment;
// DÒNG IMPORT CHO TASK 4 (SQLite)
import com.example.lab04_storage.task04.screens.Task04ClassManagerActivity;

/**
 * Task01MainActivity
 * ---------------------------
 * [Giữ nguyên chú thích]
 */
public class Task01MainActivity extends AppCompatActivity {
    // ... (Phần còn lại của class giữ nguyên) ...

    private TextView tvSearchHint;
    private Task01PrefsManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task_manager_main);

        // ===============================
        // SharedPreferences Session
        // ===============================
        prefs = new Task01PrefsManager(this);
        Task01UserSession session = prefs.getSession();

        // ===============================
        // Mapping UI
        // ===============================
        tvSearchHint = findViewById(R.id.tv_search_hint);
        MaterialButton btnAdd = findViewById(R.id.btn_add_small);

        // SỬA: dùng LinearLayout thay vì BottomNavigationView
        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navTasks = findViewById(R.id.nav_tasks);
        LinearLayout navProfile = findViewById(R.id.nav_profile);

        // MAPPING: Mục Note mới cho Task 3
        LinearLayout navNoteTask3 = findViewById(R.id.nav_note_task3);

        // MAPPING MỚI: Mục Database/Bảng cho Task 4
        LinearLayout navDbTask4 = findViewById(R.id.nav_db_task4);

        // ===============================
        // Hiển thị username
        // ===============================
        if (session != null && session.username != null) {
            tvSearchHint.setText("Xin chào: " + session.username);
        } else {
            tvSearchHint.setText("Xin chào: User");
        }

        // [Phần xử lý nút + giữ nguyên]
        btnAdd.setOnClickListener(v -> {
            // Không dùng logout nữa để tránh sai yêu cầu UI
        });

        // ===============================
        // Bottom Navigation (dạng LinearLayout)
        // ===============================
        navHome.setOnClickListener(v -> {
            // Home → vẫn ở màn này
        });

        navTasks.setOnClickListener(v -> {
            // Tasks → vẫn ở màn này
        });

        navProfile.setOnClickListener(v -> {
            // Profile → mở màn hình Profile của Task01
            startActivity(new Intent(this, Task01ProfileActivity.class));
        });

        // LOGIC MỚI: Note/Storage (Điều hướng đến Menu lựa chọn Storage)
        navNoteTask3.setOnClickListener(v -> {
            startActivity(new Intent(this, Task03StorageSelectionFragment.class));
        });

        // LOGIC MỚI: Database/Bảng (Điều hướng đến Task 4)
        navDbTask4.setOnClickListener(v -> {
            startActivity(new Intent(this, Task04ClassManagerActivity.class));
        });
    }
}