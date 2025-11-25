package com.example.lab04_storage.task04.screens;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task04.Task04DatabaseHelper;

import androidx.appcompat.app.AppCompatActivity;

public class Task04SqliteRegisterActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword, edtConfirmPassword;
    private Task04DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_auth_register);

        edtUsername = findViewById(R.id.et_register_username);
        edtPassword = findViewById(R.id.et_register_password);
        edtConfirmPassword = findViewById(R.id.et_register_confirm);

        Button btnRegister = findViewById(R.id.btn_register);
        TextView tvLogin = findViewById(R.id.tv_to_login);

        dbHelper = new Task04DatabaseHelper(this);

        btnRegister.setOnClickListener(v -> handleRegister());
        tvLogin.setOnClickListener(v -> finish());
    }

    private void handleRegister() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString();
        String confirm = edtConfirmPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirm)) {
            Toast.makeText(this, "Mật khẩu xác nhận không khớp.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean success = dbHelper.registerUser(username, password);

        if (success) {
            Toast.makeText(this, "Đăng ký thành công! Vui lòng đăng nhập.", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Đăng ký thất bại. Tên đăng nhập đã tồn tại.", Toast.LENGTH_LONG).show();
        }
    }
}
