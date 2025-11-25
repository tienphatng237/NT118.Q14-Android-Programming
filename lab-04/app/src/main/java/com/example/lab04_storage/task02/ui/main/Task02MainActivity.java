package com.example.lab04_storage.task02.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task02.data.Task02Prefs;
import com.example.lab04_storage.task02.ui.settings.Task02SettingActivity;

public class Task02MainActivity extends AppCompatActivity {

    private LinearLayout layoutMain;
    private Button btnStartSetting;

    private Task02Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task02_main);

        prefs = new Task02Prefs(this);

        layoutMain = findViewById(R.id.layoutMain);
        btnStartSetting = findViewById(R.id.btnStartSetting);

        btnStartSetting.setOnClickListener(v ->
                startActivity(new Intent(this, Task02SettingActivity.class))
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        applyColor();
    }

    private void applyColor() {
        if (prefs.isColorRed()) {
            layoutMain.setBackgroundColor(0xFFFF0000);
        } else {
            layoutMain.setBackgroundColor(0xFF03A9F4);
        }
    }
}
