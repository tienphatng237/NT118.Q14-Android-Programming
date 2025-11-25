package com.example.lab04_storage.task03.screens;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Task03InternalStorageActivity extends AppCompatActivity {

    private static final String FILE_NAME = "my_private_note.txt";

    private EditText etInputData;
    private ListView lvNotes; // Đã thay thế tvOutput/lvNotes
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnBack;

    private ArrayList<String> notesList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task03_internal_storage);

        // Ánh xạ các thành phần UI
        etInputData = findViewById(R.id.et_internal_data);
        lvNotes = findViewById(R.id.lv_internal_notes); // Ánh xạ ListView
        btnAdd = findViewById(R.id.btn_internal_add);
        btnUpdate = findViewById(R.id.btn_internal_update);
        btnBack = findViewById(R.id.btn_internal_back);

        // Khởi tạo danh sách và Adapter
        notesList = new ArrayList<>();
        // SỬA: Lỗi đã được khắc phục sau khi tạo item_note.xml
        adapter = new ArrayAdapter<>(this, R.layout.item_note, R.id.tv_note_item, notesList);
        lvNotes.setAdapter(adapter);

        // Thiết lập sự kiện click
        // THÊM: Nối thêm (true/APPEND)
        btnAdd.setOnClickListener(v -> saveNote(true));

        // CẬP NHẬT: Ghi đè toàn bộ (false/PRIVATE)
        btnUpdate.setOnClickListener(v -> saveNote(false));

        // Logic cho nút Quay lại
        btnBack.setOnClickListener(v -> finish());

        // Tải ghi chú khi Activity được tạo
        readNotesFromFile();

        // Thiết lập sự kiện click vào item để chọn ghi chú cũ (cho tính năng Cập nhật/Sửa)
        lvNotes.setOnItemClickListener((parent, view, position, id) -> {
            etInputData.setText(notesList.get(position));
            Toast.makeText(this, "Đã chọn ghi chú thứ " + (position + 1) + ". Nhấn Cập nhật để ghi đè toàn bộ file.", Toast.LENGTH_LONG).show();
            // Lưu ý: Cập nhật ở đây sẽ GHI ĐÈ toàn bộ file chỉ bằng nội dung trong EditText.
        });
    }

    /**
     * Phương thức lưu/ghi dữ liệu vào Internal Storage.
     * @param append Nếu true (MODE_APPEND), THÊM GHI CHÚ MỚI (nối thêm vào list và ghi đè file).
     * Nếu false (MODE_PRIVATE), CẬP NHẬT/GHI ĐÈ toàn bộ file (bằng nội dung duy nhất).
     */
    private void saveNote(boolean append) {
        String inputNote = etInputData.getText().toString().trim();

        if (inputNote.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập dữ liệu để lưu!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (append) {
            // THÊM MỚI (Nối thêm vào danh sách và ghi đè toàn bộ file với danh sách mới)
            notesList.add(inputNote);
            writeNotesToFile(notesList, Context.MODE_PRIVATE);

            Toast.makeText(this, "Thêm mới thành công!", Toast.LENGTH_SHORT).show();
        } else {
            // CẬP NHẬT (Ghi đè toàn bộ file bằng nội dung duy nhất)

            notesList.clear(); // Xóa sạch list
            notesList.add(inputNote); // Thêm nội dung mới vào list
            writeNotesToFile(notesList, Context.MODE_PRIVATE);

            Toast.makeText(this, "Cập nhật (Ghi đè file) thành công!", Toast.LENGTH_SHORT).show();
        }

        etInputData.setText(""); // Xóa nội dung input sau khi lưu
        adapter.notifyDataSetChanged(); // Cập nhật ListView
    }


    /**
     * Phương thức ghi danh sách ghi chú vào file (luôn ở chế độ GHI ĐÈ toàn bộ).
     */
    private void writeNotesToFile(ArrayList<String> list, int mode) {
        FileOutputStream fos = null;
        try {
            // Luôn dùng MODE_PRIVATE để ghi đè, vì chúng ta đang quản lý list trong bộ nhớ
            fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            for (String note : list) {
                // Ghi từng ghi chú, thêm ký tự xuống dòng để đọc lại dễ dàng
                fos.write((note + "\n").getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi lưu file: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Phương thức đọc dữ liệu từ Internal Storage và nạp vào ListView.
     */
    private void readNotesFromFile() {
        notesList.clear();
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    notesList.add(line);
                }
            }

            adapter.notifyDataSetChanged();

        } catch (Exception e) {
            // Nếu file chưa tồn tại, thêm thông báo vào list
            notesList.add("Không tìm thấy file. Hãy nhấn Thêm để tạo file!");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}