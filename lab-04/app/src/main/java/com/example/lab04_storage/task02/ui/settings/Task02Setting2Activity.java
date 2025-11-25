package com.example.lab04_storage.task02.ui.settings;

import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task02.data.Task02Prefs;

public class Task02Setting2Activity extends AppCompatActivity {

    private Switch switchMode;
    private Task02Prefs prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task02_setting2);

        prefs = new Task02Prefs(this);
        switchMode = findViewById(R.id.switchMode);

        switchMode.setChecked(prefs.isSpecialMode());

        switchMode.setOnCheckedChangeListener((b, checked) ->
                prefs.setSpecialMode(checked)
        );
    }
}
