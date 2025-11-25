package com.example.lab04_storage.task04.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.screens.Task01LoginActivity;
import com.example.lab04_storage.task04.screens.Task04SqliteLoginActivity;

public class Task04AuthSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task04_auth_selection);

        Button btnPrefs = findViewById(R.id.btn_auth_task1_prefs);
        Button btnSqlite = findViewById(R.id.btn_auth_task4_sqlite);

        // Login bằng SharedPreferences
        btnPrefs.setOnClickListener(v -> {
            startActivity(new Intent(this, Task01LoginActivity.class));
            finish();
        });

        // Login bằng SQLite
        btnSqlite.setOnClickListener(v -> {
            startActivity(new Intent(this, Task04SqliteLoginActivity.class));
            finish();
        });
    }
}
