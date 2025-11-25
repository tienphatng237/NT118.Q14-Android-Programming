package com.example.lab04_storage.task03.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;

public class Task03StorageSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task03_storage_selection);

        Button btnInternal = findViewById(R.id.btn_select_internal);
        Button btnExternal = findViewById(R.id.btn_select_external);

        // Điều hướng sang Internal Storage Activity
        btnInternal.setOnClickListener(v -> {
            startActivity(new Intent(this, Task03InternalStorageActivity.class));
        });

        // Điều hướng sang External Storage Activity
        btnExternal.setOnClickListener(v -> {
            startActivity(new Intent(this, Task03ExternalStorageActivity.class));
        });
    }
}