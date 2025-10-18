package com.example.lab_02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_02.menu.LinearMenuActivity;
import com.example.lab_02.bai02_PersonalInfo.PersonalInfoActivity;
import com.example.lab_02.bai03_SalesManager.SalesManagerActivity;
import com.example.lab_02.bai04_Calculator.CalculatorActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLinear = findViewById(R.id.btn_linear_demo);
        Button btnLandscape = findViewById(R.id.btn_landscape_demo);
        Button btnSales = findViewById(R.id.btn_sales_demo);
        Button btnCalc = findViewById(R.id.btn_calculator);

        btnLinear.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, LinearMenuActivity.class)));

        btnLandscape.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, PersonalInfoActivity.class)));

        btnSales.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, SalesManagerActivity.class)));
        btnCalc.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CalculatorActivity.class)));

    }
}
