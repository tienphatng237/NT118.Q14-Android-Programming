package com.example.lab_02.menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_02.bai01_LinearLayout.LinearLayoutA;
import com.example.lab_02.bai01_LinearLayout.LinearLayoutB;
import com.example.lab_02.R;

public class LinearMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai01_activity_linear_menu);

        Button btnA = findViewById(R.id.btn_linear_a);
        Button btnB = findViewById(R.id.btn_linear_b);

        btnA.setOnClickListener(v ->
                startActivity(new Intent(LinearMenuActivity.this, LinearLayoutA.class)));

        btnB.setOnClickListener(v ->
                startActivity(new Intent(LinearMenuActivity.this, LinearLayoutB.class)));
    }
}
