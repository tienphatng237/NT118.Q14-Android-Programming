package com.example.lab_03.bai01_SendInformation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab_03.R;

public class SendInfoActivity extends AppCompatActivity {

    private EditText etName, etCMND, etExtra;
    private Spinner spDegree;
    private CheckBox cbSport, cbMusic, cbReading;
    private Button btnSend, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai01_activity_send_info);

        etName = findViewById(R.id.etName);
        etCMND = findViewById(R.id.etCMND);
        spDegree = findViewById(R.id.spDegree);
        cbSport = findViewById(R.id.cbSport);
        cbMusic = findViewById(R.id.cbMusic);
        cbReading = findViewById(R.id.cbReading);
        etExtra = findViewById(R.id.etExtra);
        btnSend = findViewById(R.id.btnSend);
        btnBack = findViewById(R.id.btnBack);

        // Xử lý nút GỬI
        btnSend.setOnClickListener(v -> {
            if (!SendInfoValidator.validate(etName, etCMND, cbSport, cbMusic, cbReading, this)) return;

            String name = etName.getText().toString().trim();
            String cmnd = etCMND.getText().toString().trim();
            String degree = spDegree.getSelectedItem().toString();

            StringBuilder hobbies = new StringBuilder();
            if (cbSport.isChecked()) hobbies.append("Thể thao ");
            if (cbMusic.isChecked()) hobbies.append("Âm nhạc ");
            if (cbReading.isChecked()) hobbies.append("Đọc sách ");

            String extra = etExtra.getText().toString();

            Intent i = new Intent(SendInfoActivity.this, DisplayInfoActivity.class);
            i.putExtra("name", name);
            i.putExtra("cmnd", cmnd);
            i.putExtra("degree", degree);
            i.putExtra("hobbies", hobbies.toString());
            i.putExtra("extra", extra);
            startActivity(i);
        });

        btnBack.setOnClickListener(v -> finish());
    }
}
