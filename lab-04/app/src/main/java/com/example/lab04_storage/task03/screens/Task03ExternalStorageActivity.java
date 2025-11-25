package com.example.lab04_storage.task03.screens;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Task03ExternalStorageActivity extends AppCompatActivity {

    private static final String FILE_NAME = "external_note.txt"; // Tên file
    private EditText etInputData;
    private TextView tvOutput;
    private Button btnSave;
    private Button btnLoad;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task03_external_storage);

        // Ánh xạ UI
        etInputData = findViewById(R.id.et_external_data);
        tvOutput = findViewById(R.id.tv_external_output);
        btnSave = findViewById(R.id.btn_external_save);
        btnLoad = findViewById(R.id.btn_external_read);
        btnBack = findViewById(R.id.btn_internal_back);

        // Kiểm tra trạng thái bộ nhớ ngoài khi khởi động
        if (!isExternalStorageWritable()) {
            Toast.makeText(this, "Bộ nhớ ngoài không sẵn sàng để ghi!", Toast.LENGTH_LONG).show();
            btnSave.setEnabled(false);
        }

        // Thiết lập sự kiện click
        btnSave.setOnClickListener(v -> saveExternalFile());
        btnLoad.setOnClickListener(v -> readExternalFile());
        btnBack.setOnClickListener(v -> finish()); // Logic nút Quay lại

        // Tự động đọc dữ liệu khi Activity khởi tạo
        readExternalFile();
    }

    // =================================================================
    // CÁC PHƯƠNG THỨC HỖ TRỢ
    // =================================================================

    /**
     * Kiểm tra xem External Storage có sẵn để ghi không.
     */
    private boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * Lấy đường dẫn file External Storage. (App-specific Files)
     */
    private File getExternalFile() {
        // Trả về thư mục của ứng dụng trong bộ nhớ ngoài
        File directory = getExternalFilesDir(null);
        return new File(directory, FILE_NAME);
    }

    /**
     * Lưu dữ liệu vào file external.txt trong External Storage. (Ghi đè)
     */
    private void saveExternalFile() {
        String data = etInputData.getText().toString().trim();

        if (data.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập dữ liệu để lưu!", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = getExternalFile();
        try (FileOutputStream fos = new FileOutputStream(file, false); // false = Ghi đè
             OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {

            writer.write(data);
            Toast.makeText(this, "Dữ liệu đã lưu vào External Storage thành công!", Toast.LENGTH_SHORT).show();
            etInputData.setText("");
            readExternalFile(); // Cập nhật hiển thị

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi lưu file: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Đọc dữ liệu từ file external.txt và hiển thị trên giao diện.
     */
    private void readExternalFile() {
        File file = getExternalFile();
        StringBuilder sb = new StringBuilder();

        if (!file.exists()) {
            tvOutput.setText("Không có dữ liệu trong file external.txt");
            return;
        }

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            if (sb.length() > 0) {
                tvOutput.setText(sb.toString().trim());
            } else {
                tvOutput.setText("File external.txt trống.");
            }

        } catch (IOException e) {
            e.printStackTrace();
            tvOutput.setText("Lỗi khi đọc file: " + e.getMessage());
        }
    }
}