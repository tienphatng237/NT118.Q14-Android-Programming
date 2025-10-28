package com.example.lab_03.bai01_SendInformation;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab_03.R;

/**
 * Màn hình hiển thị thông tin người dùng sau khi được gửi từ SendInfoActivity.
 * Nhận dữ liệu từ Intent và hiển thị lại lên các TextView.
 */
public class DisplayInfoActivity extends AppCompatActivity {
    // Khai báo các thành phần giao diện
    private TextView tvName, tvCMND, tvDegree, tvHobbies, tvExtra;
    private Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bai01_activity_display_info);

        // 1. Liên kết các view trong layout với biến Java
        tvName = findViewById(R.id.tvName);
        tvCMND = findViewById(R.id.tvCMND);
        tvDegree = findViewById(R.id.tvDegree);
        tvHobbies = findViewById(R.id.tvHobbies);
        tvExtra = findViewById(R.id.tvExtra);
        btnBack = findViewById(R.id.btnBack);

        // 2. Lấy dữ liệu được gửi từ Intent
        String name = getIntent().getStringExtra("name");
        String cmnd = getIntent().getStringExtra("cmnd");
        String degree = getIntent().getStringExtra("degree");
        String hobbies = getIntent().getStringExtra("hobbies");
        String extra = getIntent().getStringExtra("extra");

        // 3. Gán dữ liệu vào các TextView tương ứng
        tvName.setText(name != null ? name : "");
        tvCMND.setText(cmnd != null ? cmnd : "");
        tvDegree.setText(degree != null ? degree : "");
        tvHobbies.setText(hobbies != null ? hobbies : "");
        tvExtra.setText(extra != null ? extra : "");

        // 4. Xử lý sự kiện nút “Quay lại”
        btnBack.setOnClickListener(v -> finish());
    }
}

