package com.example.lab04_storage.task02.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;
import com.example.lab04_storage.task01.data.Task01UserSession;
import com.example.lab04_storage.task02.data.Task02Prefs;
import com.example.lab04_storage.task02.ui.settings.Task02SettingActivity;
import com.example.lab04_storage.task02.ui.settings.Task02Setting2Activity;

public class Task02MainActivity extends AppCompatActivity {

    private LinearLayout mainContainer;
    private Button btnSetting1, btnSetting2;
    private TextView tvWelcome;

    private Task02Prefs prefs;
    private Task01PrefsManager sessionPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task02_main);

        prefs = new Task02Prefs(this);
        sessionPrefs = new Task01PrefsManager(this);

        mainContainer = findViewById(R.id.mainContainer);
        btnSetting1 = findViewById(R.id.btnSetting1);
        btnSetting2 = findViewById(R.id.btnSetting2);
        tvWelcome = findViewById(R.id.tvWelcome);

        Task01UserSession u = sessionPrefs.getSession();
        tvWelcome.setText("Xin chào: " + u.username);

        applyBackgroundColor();

        btnSetting1.setOnClickListener(v ->
                startActivity(new Intent(this, Task02SettingActivity.class))
        );

        btnSetting2.setOnClickListener(v ->
                startActivity(new Intent(this, Task02Setting2Activity.class))
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        applyBackgroundColor();
    }

    private void applyBackgroundColor() {
        if (prefs.isRedBackground()) {
            mainContainer.setBackgroundColor(0xFFF44336);
        } else {
            mainContainer.setBackgroundColor(0xFF03A9F4);
        }
    }
}
