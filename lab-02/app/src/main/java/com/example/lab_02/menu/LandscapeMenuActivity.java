package com.example.lab_02.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab_02.R;
import com.example.lab_02.personal.PersonalInfoActivity;

public class LandscapeMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landscape_menu);
        setTitle("Landscape Layout Menu");

        Button btnBai2 = findViewById(R.id.btnPersonalInfo);
        btnBai2.setOnClickListener(v -> {
            Intent intent = new Intent(LandscapeMenuActivity.this, PersonalInfoActivity.class);
            startActivity(intent);
        });
    }
}
