package com.example.lab04_storage.task01.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;

public class Task01LoginActivity extends AppCompatActivity {

    private EditText etUser, etPass;
    private Button btnLogin;
    private TextView tvRegister;

    private Task01PrefsManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_auth_login);

        prefs = new Task01PrefsManager(this);

        etUser = findViewById(R.id.et_username);
        etPass = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);

        btnLogin.setOnClickListener(v -> doLogin());
        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(this, Task01RegisterActivity.class))
        );
    }

    private void doLogin() {
        String u = etUser.getText().toString().trim();
        String p = etPass.getText().toString().trim();

        if (u.isEmpty()) {
            etUser.setError("Không được để trống");
            return;
        }
        if (p.isEmpty()) {
            etPass.setError("Không được để trống");
            return;
        }

        if (!prefs.validateLogin(u, p)) {
            Toast.makeText(this, "Sai tên đăng nhập hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            return;
        }

        prefs.saveSession(u, p, true);

        // --------------------------
        // USER → vào màn Task Manager
        // --------------------------
        startActivity(new Intent(this, Task01MainActivity.class));
        finish();
    }
}
