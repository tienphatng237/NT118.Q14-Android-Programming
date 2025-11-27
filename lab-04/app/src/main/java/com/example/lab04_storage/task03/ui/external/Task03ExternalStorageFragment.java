package com.example.lab04_storage.task03.ui.external;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab04_storage.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Task03ExternalStorageFragment extends Fragment {

    private static final String FILE_NAME = "external_note.txt"; // Tên file
    private EditText etInputData;
    private TextView tvOutput;
    private Button btnSave;
    private Button btnLoad;
    private Button btnBack;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.feature_task03_external_storage, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Ánh xạ UI
        etInputData = view.findViewById(R.id.et_external_data);
        tvOutput = view.findViewById(R.id.tv_external_output);
        btnSave = view.findViewById(R.id.btn_external_save);
        btnLoad = view.findViewById(R.id.btn_external_read);
        btnBack = view.findViewById(R.id.btn_internal_back);

        // Kiểm tra trạng thái bộ nhớ ngoài khi khởi động
        if (!isExternalStorageWritable()) {
            Toast.makeText(getContext(), "Bộ nhớ ngoài không sẵn sàng để ghi!", Toast.LENGTH_LONG).show();
            btnSave.setEnabled(false);
        }

        // Thiết lập sự kiện click
        btnSave.setOnClickListener(v -> saveExternalFile());
        btnLoad.setOnClickListener(v -> readExternalFile());

        // Nút back quay về Fragment Selection
        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        // Tự động đọc dữ liệu khi Fragment khởi tạo
        readExternalFile();
    }

    // =================================================================
    // CÁC PHƯƠNG THỨC HỖ TRỢ
    // =================================================================

    private boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    private File getExternalFile() {
        File directory = requireContext().getExternalFilesDir(null);
        return new File(directory, FILE_NAME);
    }

    private void saveExternalFile() {
        String data = etInputData.getText().toString().trim();

        if (data.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập dữ liệu để lưu!", Toast.LENGTH_SHORT).show();
            return;
        }

        File file = getExternalFile();
        try (FileOutputStream fos = new FileOutputStream(file, false);
             OutputStreamWriter writer = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {

            writer.write(data);
            Toast.makeText(getContext(), "Dữ liệu đã lưu vào External Storage thành công!", Toast.LENGTH_SHORT).show();
            etInputData.setText("");
            readExternalFile();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Lỗi khi lưu file: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

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
