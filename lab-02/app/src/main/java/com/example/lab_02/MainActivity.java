package com.example.lab_02;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLinear = findViewById(R.id.btnLinear);
        Button btnLandscape = findViewById(R.id.btnLandscape);
        Button btnSales = findViewById(R.id.btnSales);
        Button btnCalc = findViewById(R.id.btnCalc);

        // Chuyển qua LinearLayoutActivity
        btnLinear.setOnClickListener(v ->
                startActivity(new Intent(this, LinearLayoutActivity.class))
        );

//        // Chuyển qua LandscapeActivity
//        btnLandscape.setOnClickListener(v ->
//                startActivity(new Intent(this, LandscapeActivity.class))
//        );
//
//        // Chuyển qua SalesManagerActivity
//        btnSales.setOnClickListener(v ->
//                startActivity(new Intent(this, SalesManagerActivity.class))
//        );
//
//        // Chuyển qua CalculatorActivity
//        btnCalc.setOnClickListener(v ->
//                startActivity(new Intent(this, CalculatorActivity.class))
//        );
    }
}
