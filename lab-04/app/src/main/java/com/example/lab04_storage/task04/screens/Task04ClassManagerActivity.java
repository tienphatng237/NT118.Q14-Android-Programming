package com.example.lab04_storage.task04.screens;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task04.Task04DatabaseHelper;

public class Task04ClassManagerActivity extends AppCompatActivity {

    private EditText edtCode, edtName, edtCount;
    private ListView listView;
    private Task04DatabaseHelper dbHelper;
    private SimpleCursorAdapter adapter;
    private String selectedClassCode = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task04_sqlite_class_manager);

        edtCode = findViewById(R.id.edtClassCode);
        edtName = findViewById(R.id.edtClassName);
        edtCount = findViewById(R.id.edtStudentCount);
        listView = findViewById(R.id.listView);
        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnBack = findViewById(R.id.btn_internal_back);

        dbHelper = new Task04DatabaseHelper(this);
        loadData();

        btnInsert.setOnClickListener(v -> insertClass());
        btnUpdate.setOnClickListener(v -> updateClass());
        btnDelete.setOnClickListener(v -> deleteClass());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Cursor cursor = (Cursor) adapter.getItem(position);

            selectedClassCode = cursor.getString(
                    cursor.getColumnIndexOrThrow(Task04DatabaseHelper.COLUMN_CLASS_CODE)
            );

            edtCode.setText(selectedClassCode);
            edtName.setText(cursor.getString(cursor.getColumnIndexOrThrow(Task04DatabaseHelper.COLUMN_CLASS_NAME)));
            edtCount.setText(cursor.getString(cursor.getColumnIndexOrThrow(Task04DatabaseHelper.COLUMN_STUDENT_COUNT)));
        });

        btnBack.setOnClickListener(v -> finish());
    }

    private void insertClass() {
        String code = edtCode.getText().toString();
        String name = edtName.getText().toString();
        String countStr = edtCount.getText().toString();

        if (code.isEmpty() || name.isEmpty() || countStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ Mã lớp, Tên lớp và Sĩ số.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int count = Integer.parseInt(countStr);
            dbHelper.insertClass(code, name, count);
            Toast.makeText(this, "Thêm lớp học thành công!", Toast.LENGTH_SHORT).show();
            clearInput();
            loadData();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Sĩ số phải là số nguyên.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateClass() {
        String code = edtCode.getText().toString();
        String name = edtName.getText().toString();
        String countStr = edtCount.getText().toString();

        if (code.isEmpty() || name.isEmpty() || countStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin để cập nhật.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int count = Integer.parseInt(countStr);
            dbHelper.updateClass(code, name, count);
            Toast.makeText(this, "Cập nhật lớp học thành công!", Toast.LENGTH_SHORT).show();
            clearInput();
            loadData();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Sĩ số phải là số nguyên.", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteClass() {
        String code = edtCode.getText().toString();

        if (code.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập Mã lớp để xoá.", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.deleteClass(code);
        Toast.makeText(this, "Xoá lớp học thành công!", Toast.LENGTH_SHORT).show();
        clearInput();
        loadData();
    }

    private void clearInput() {
        edtCode.setText("");
        edtName.setText("");
        edtCount.setText("");
        selectedClassCode = null;
    }

    private void loadData() {
        Cursor cursor = dbHelper.getAllClasses();

        String[] fromColumns = {
                Task04DatabaseHelper.COLUMN_CLASS_CODE,
                Task04DatabaseHelper.COLUMN_CLASS_NAME,
                Task04DatabaseHelper.COLUMN_STUDENT_COUNT
        };

        int[] toViews = {
                R.id.classCode,
                R.id.className,
                R.id.studentCount
        };

        adapter = new SimpleCursorAdapter(
                this,
                R.layout.feature_task04_sqlite_class_list_item,
                cursor,
                fromColumns,
                toViews,
                0
        );

        listView.setAdapter(adapter);
    }
}
