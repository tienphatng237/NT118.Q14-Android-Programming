package com.example.lab04_storage.task01.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;
import com.example.lab04_storage.task01.data.Task01UserSession;
import com.example.lab04_storage.task01.screens.Task01ProfileFragment;
import com.example.lab04_storage.task03.ui.selection.Task03StorageSelectionFragment;
import com.example.lab04_storage.task04.screens.Task04ClassManagerFragment;
import com.google.android.material.button.MaterialButton;

public class Task01MainActivity extends AppCompatActivity {

    private TextView tvSearchHint;
    private Task01PrefsManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task_manager_main);

        // ============================
        // LOAD SESSION
        // ============================
        prefs = new Task01PrefsManager(this);
        Task01UserSession session = prefs.getSession();

        // ============================
        // UI MAPPING
        // ============================
        tvSearchHint = findViewById(R.id.tv_search_hint);
        MaterialButton btnAdd = findViewById(R.id.btn_add_small);

        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navTasks = findViewById(R.id.nav_tasks);
        LinearLayout navProfile = findViewById(R.id.nav_profile);
        LinearLayout navNoteTask3 = findViewById(R.id.nav_note_task3);
        LinearLayout navDbTask4 = findViewById(R.id.nav_db_task4);

        // ============================
        // DISPLAY USERNAME
        // ============================
        if (session != null && session.username != null) {
            tvSearchHint.setText("Xin chào: " + session.username);
        } else {
            tvSearchHint.setText("Xin chào: User");
        }

        btnAdd.setOnClickListener(v -> {});

        navHome.setOnClickListener(v -> {});
        navTasks.setOnClickListener(v -> {});

        navProfile.setOnClickListener(v ->
                loadTask01ProfileFragment(new Task01ProfileFragment())
        );

        // ============================
        // TASK 3 — STORAGE MENU
        // ============================
        navNoteTask3.setOnClickListener(v ->
                loadTask03Fragment(new Task03StorageSelectionFragment())
        );

        // ============================
        // TASK 4 — SQLITE TABLE
        // ============================
        navDbTask4.setOnClickListener(v ->
                loadTask04Fragment(new Task04ClassManagerFragment())
        );
    }

    private void loadTask01ProfileFragment(Fragment fragment) {

        View rv = findViewById(R.id.rv_tasks);
        if (rv != null) rv.setVisibility(View.GONE);

        View container = findViewById(R.id.task03_fragment_container);
        if (container != null) container.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.task03_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void loadTask03Fragment(Fragment fragment) {

        // Ẩn RecyclerView chính
        View rv = findViewById(R.id.rv_tasks);
        if (rv != null) rv.setVisibility(View.GONE);

        // Hiện container fragment
        View container = findViewById(R.id.task03_fragment_container);
        if (container != null) container.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.task03_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void loadTask04Fragment(Fragment fragment) {

        // Ẩn recycler
        View rv = findViewById(R.id.rv_tasks);
        if (rv != null) rv.setVisibility(View.GONE);

        // Hiện container
        View container = findViewById(R.id.task03_fragment_container);
        if (container != null) container.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.task03_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

}
