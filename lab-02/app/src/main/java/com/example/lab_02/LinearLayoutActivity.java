package com.example.lab_02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LinearLayoutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);

        Button btnCancel = findViewById(R.id.btnCancel);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnCancel.setOnClickListener(v -> {
            // Quay lại MainActivity
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });

        btnSubmit.setOnClickListener(v ->
                // Ví dụ tạm — sau này có thể show Toast
                android.widget.Toast.makeText(this, "Form submitted!", android.widget.Toast.LENGTH_SHORT).show()
        );
    }
}
