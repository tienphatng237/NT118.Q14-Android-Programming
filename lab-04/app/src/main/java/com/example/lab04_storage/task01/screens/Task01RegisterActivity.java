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

public class Task01RegisterActivity extends AppCompatActivity {

    private EditText etUser, etEmail, etPass, etConfirm;
    private Button btnRegister;
    private TextView tvToLogin;

    private Task01PrefsManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_auth_register);

        prefs = new Task01PrefsManager(this);

        etUser = findViewById(R.id.et_register_username);
        etEmail = findViewById(R.id.et_register_email);
        etPass = findViewById(R.id.et_register_password);
        etConfirm = findViewById(R.id.et_register_confirm);
        btnRegister = findViewById(R.id.btn_register);
        tvToLogin = findViewById(R.id.tv_to_login);

        btnRegister.setOnClickListener(v -> doRegister());
        tvToLogin.setOnClickListener(v -> {
            startActivity(new Intent(this, Task01LoginActivity.class));
            finish();
        });
    }

    private void doRegister() {

        String u = etUser.getText().toString().trim();
        String e = etEmail.getText().toString().trim();
        String p = etPass.getText().toString().trim();
        String c = etConfirm.getText().toString().trim();

        if (u.isEmpty()) {
            etUser.setError("Không được để trống");
            return;
        }
        if (prefs.userExists(u)) {
            etUser.setError("Tên người dùng đã tồn tại");
            return;
        }
        if (p.isEmpty()) {
            etPass.setError("Không được để trống");
            return;
        }
        if (!p.equals(c)) {
            etConfirm.setError("Mật khẩu không khớp");
            return;
        }

        prefs.addUser(u, p, e);

        Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, Task01LoginActivity.class));
        finish();
    }
}
