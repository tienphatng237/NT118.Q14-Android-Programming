package com.example.lab04_storage.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.task01.screens.Task01SplashActivity;

/**
 * MainActivity
 * -----------------------------------------
 * Đây là entry point chính được khai báo trong AndroidManifest.
 * Mục đích:
 *  - Khởi động màn hình Splash của Task 01.
 *  - Kết thúc chính Activity này để tránh người dùng quay lại bằng nút Back.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Điều hướng sang Splash Screen của Task 01
        startActivity(new Intent(this, Task01SplashActivity.class));

        // Đảm bảo người dùng không quay lại MainActivity
        finish();
    }
}
