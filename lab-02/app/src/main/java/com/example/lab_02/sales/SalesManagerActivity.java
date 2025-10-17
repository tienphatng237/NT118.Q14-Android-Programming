package com.example.lab_02.sales;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab_02.R;

public class SalesManagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_manager);
        setTitle("Quản lý bán hàng");

        Button btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> finish());
    }
}
