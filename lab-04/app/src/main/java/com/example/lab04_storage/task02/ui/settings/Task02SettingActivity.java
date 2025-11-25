package com.example.lab04_storage.task02.ui.settings;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task02.data.Task02Prefs;

public class Task02SettingActivity extends AppCompatActivity {

    private CheckBox cbColor, cbInfo;
    private Button btnStatus;
    private Task02Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task02_setting);

        prefs = new Task02Prefs(this);

        cbColor = findViewById(R.id.cbColor);
        cbInfo = findViewById(R.id.cbInfo);
        btnStatus = findViewById(R.id.btnStatus);

        // Load trạng thái
        cbColor.setChecked(prefs.isColorRed());
        cbInfo.setChecked(prefs.isInfoChecked());

        // Hiển thị nút theo trạng thái hiện tại
        updateStatusText(cbInfo.isChecked());

        // Checkbox 1 – đổi màu nền
        cbColor.setOnCheckedChangeListener((v, checked) -> {
            prefs.setColorRed(checked);
        });

        // Checkbox 2 – hiển thị trạng thái
        cbInfo.setOnCheckedChangeListener((v, checked) -> {
            prefs.setInfoChecked(checked);
            updateStatusText(checked);
        });
    }

    private void updateStatusText(boolean checked) {
        btnStatus.setText("checked = " + checked);
    }
}
