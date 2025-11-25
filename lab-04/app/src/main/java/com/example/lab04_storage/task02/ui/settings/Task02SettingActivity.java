package com.example.lab04_storage.task02.ui.settings;

import android.os.Bundle;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task02.data.Task02Prefs;

public class Task02SettingActivity extends AppCompatActivity {

    private CheckBox cbColor;
    private Task02Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task02_setting);

        prefs = new Task02Prefs(this);
        cbColor = findViewById(R.id.cbColor);

        cbColor.setChecked(prefs.isRedBackground());

        cbColor.setOnCheckedChangeListener((b, checked) ->
                prefs.setRedBackground(checked)
        );
    }
}
