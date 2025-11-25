package com.example.lab04_storage.task04.screens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab04_storage.task01.screens.Task01MainActivity;
import com.example.lab04_storage.R;
import com.example.lab04_storage.task04.Task04DatabaseHelper;

import androidx.appcompat.app.AppCompatActivity;

public class Task04SqliteLoginActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Task04DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_auth_login);

        edtUsername = findViewById(R.id.et_username);
        edtPassword = findViewById(R.id.et_password);
        Button btnLogin = findViewById(R.id.btn_login);
        TextView tvRegister = findViewById(R.id.tv_register);

        dbHelper = new Task04DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> handleLogin());

        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(this, Task04SqliteRegisterActivity.class))
        );
    }

    private void handleLogin() {
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên đăng nhập và mật khẩu.", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isValid = dbHelper.checkUserLogin(username, password);

        if (isValid) {
            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Task01MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng.", Toast.LENGTH_LONG).show();
        }
    }
}
