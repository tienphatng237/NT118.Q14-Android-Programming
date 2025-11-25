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

/**
 * Task01MainActivity
 * ---------------------------
 * - Sử dụng layout feature_task_manager_main.xml của app gốc
 * - Hiển thị "Xin chào: <username>"
 * - Không sử dụng RecyclerView hay Task Manager
 * - Bottom Navigation:
 *      + Home → vẫn ở màn này
 *      + Tasks → vẫn ở màn này (không yêu cầu chức năng)
 *      + Profile → mở Task01ProfileActivity
 * - Nút "+" hiện tại KHÔNG dùng logout nữa (chuẩn UI)
 */
public class Task01MainActivity extends AppCompatActivity {

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

        // SỬA: trong layout là MaterialButton, không phải ImageButton
        MaterialButton btnAdd = findViewById(R.id.btn_add_small);

        // SỬA: dùng LinearLayout thay vì BottomNavigationView
        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navTasks = findViewById(R.id.nav_tasks);
        LinearLayout navProfile = findViewById(R.id.nav_profile);

        // ===============================
        // Hiển thị username
        // ===============================
        if (session != null && session.username != null) {
            tvSearchHint.setText("Xin chào: " + session.username);
        } else {
            tvSearchHint.setText("Xin chào: User");
        }

        // ===============================
        // Nút "+" — hiện KHÔNG dùng logout nữa
        // (giữ UI đúng app gốc, hoặc sẽ dùng cho Task02)
        // ===============================
        btnAdd.setOnClickListener(v -> {
            // Không dùng logout nữa để tránh sai yêu cầu UI
            // Có thể Toast tạm:
            // Toast.makeText(this, "Chưa có chức năng!", Toast.LENGTH_SHORT).show();
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

        // Không còn bottomNav.setSelectedItemId(...) vì không dùng BottomNavigationView
    }
}
