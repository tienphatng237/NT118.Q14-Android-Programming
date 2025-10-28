package com.example.lab_03.bai01_SendInformation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab_03.R;

public class DisplayInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai01_activity_display_info);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvCMND = findViewById(R.id.tvCMND);
        TextView tvDegree = findViewById(R.id.tvDegree);
        TextView tvHobbies = findViewById(R.id.tvHobbies);
        TextView tvExtra = findViewById(R.id.tvExtra);
        Button btnBack = findViewById(R.id.btnBack);

        tvName.setText(getIntent().getStringExtra("name"));
        tvCMND.setText(getIntent().getStringExtra("cmnd"));
        tvDegree.setText(getIntent().getStringExtra("degree"));
        tvHobbies.setText(getIntent().getStringExtra("hobbies"));
        tvExtra.setText(getIntent().getStringExtra("extra"));

        btnBack.setOnClickListener(v -> finish());
    }
}
