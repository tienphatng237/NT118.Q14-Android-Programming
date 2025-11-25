package com.example.lab04_storage.ui.main;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.task01.screens.Task01SplashActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // App → Splash → Login → Main
        startActivity(new Intent(this, Task01SplashActivity.class));
        finish();
    }
}
