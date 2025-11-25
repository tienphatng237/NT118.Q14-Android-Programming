package com.example.lab04_storage.task01.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;
import com.example.lab04_storage.task01.data.Task01UserSession;

import java.util.ArrayList;

public class Task01AdminActivity extends AppCompatActivity {

    private ListView listUsers;
    private Button btnLogout;

    private Task01PrefsManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task01_admin);

        prefs = new Task01PrefsManager(this);

        listUsers = findViewById(R.id.listUsers);
        btnLogout = findViewById(R.id.btnLogoutAdmin);

        // ============================
        // LOAD LIST USERS
        // ============================
        ArrayList<Task01UserSession> list = prefs.getAllUsers();
        ArrayList<String> names = new ArrayList<>();

        for (Task01UserSession u : list) {
            names.add(u.username + " (" + u.password + ")");
        }

        listUsers.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                names
        ));

        // ============================
        // LOGOUT — chuẩn không crash
        // ============================
        btnLogout.setOnClickListener(v -> {

            // Xóa phiên đăng nhập hiện tại
            prefs.saveSession("", "", false);

            // Chuyển về Login
            Intent i = new Intent(Task01AdminActivity.this, Task01LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(i);

            // Chỉ finish() là đủ (KHÔNG dùng finishAffinity)
            finish();
        });
    }
}
