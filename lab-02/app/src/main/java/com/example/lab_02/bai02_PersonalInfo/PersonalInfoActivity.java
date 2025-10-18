package com.example.lab_02.bai02_PersonalInfo;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab_02.R;

public class PersonalInfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai02_activity_personal_info);
        setTitle("Cập nhật thông tin cá nhân");

        // Cho phép tự xoay khi vào màn hình này
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }
}
