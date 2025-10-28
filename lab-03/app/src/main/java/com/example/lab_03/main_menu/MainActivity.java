package com.example.lab_03.main_menu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_03.R;
import com.example.lab_03.bai01_SendInformation.SendInfoActivity;
import com.example.lab_03.bai02_Calculator.CalculatorActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLinear = findViewById(R.id.btn_linear_demo);
        Button btnCalc = findViewById(R.id.btn_calculator);

        btnLinear.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, SendInfoActivity.class)));

        btnCalc.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CalculatorActivity.class)));
    }
}
