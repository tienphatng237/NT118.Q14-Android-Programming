package com.example.lab_02.menu;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab_02.R;

public class SalesMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_manager);
        setTitle("Quản lý bán hàng");
    }
}
