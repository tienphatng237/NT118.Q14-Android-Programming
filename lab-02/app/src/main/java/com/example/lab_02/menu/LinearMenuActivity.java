package com.example.lab_02.menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_02.linear.LinearAVerticalActivity;
import com.example.lab_02.linear.LinearBHorizontalActivity;
import com.example.lab_02.R;

public class LinearMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_menu);

        Button btnA = findViewById(R.id.btn_linear_a);
        Button btnB = findViewById(R.id.btn_linear_b);

        btnA.setOnClickListener(v ->
                startActivity(new Intent(LinearMenuActivity.this, LinearAVerticalActivity.class)));

        btnB.setOnClickListener(v ->
                startActivity(new Intent(LinearMenuActivity.this, LinearBHorizontalActivity.class)));
    }
}
