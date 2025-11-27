package com.example.lab04_storage.task04.screens;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task04.Task04DatabaseHelper;

public class Task04ClassManagerFragment extends Fragment {

    private EditText edtCode, edtName, edtCount;
    private ListView listView;
    private Task04DatabaseHelper dbHelper;
    private SimpleCursorAdapter adapter;
    private String selectedClassCode = null;

    public Task04ClassManagerFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.feature_task04_sqlite_class_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtCode = view.findViewById(R.id.edtClassCode);
        edtName = view.findViewById(R.id.edtClassName);
        edtCount = view.findViewById(R.id.edtStudentCount);
        listView = view.findViewById(R.id.listView);

        Button btnInsert = view.findViewById(R.id.btnInsert);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);
        Button btnDelete = view.findViewById(R.id.btnDelete);
        Button btnBack   = view.findViewById(R.id.btn_internal_back);

        dbHelper = new Task04DatabaseHelper(requireContext());
        loadData();

        btnInsert.setOnClickListener(v -> insertClass());
        btnUpdate.setOnClickListener(v -> updateClass());
        btnDelete.setOnClickListener(v -> deleteClass());

        listView.setOnItemClickListener((parent, itemView, position, id) -> {
            Cursor cursor = (Cursor) adapter.getItem(position);

            selectedClassCode = cursor.getString(
                    cursor.getColumnIndexOrThrow(Task04DatabaseHelper.COLUMN_CLASS_CODE)
            );

            edtCode.setText(selectedClassCode);
            edtName.setText(cursor.getString(cursor.getColumnIndexOrThrow(Task04DatabaseHelper.COLUMN_CLASS_NAME)));
            edtCount.setText(cursor.getString(cursor.getColumnIndexOrThrow(Task04DatabaseHelper.COLUMN_STUDENT_COUNT)));
        });

        btnBack.setOnClickListener(v -> requireActivity().onBackPressed());
    }

    private void insertClass() {
        String code = edtCode.getText().toString();
        String name = edtName.getText().toString();
        String countStr = edtCount.getText().toString();

        if (code.isEmpty() || name.isEmpty() || countStr.isEmpty()) {
            Toast.makeText(requireContext(), "Vui lòng nhập đầy đủ!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int count = Integer.parseInt(countStr);
            dbHelper.insertClass(code, name, count);
            Toast.makeText(requireContext(), "Thêm lớp thành công!", Toast.LENGTH_SHORT).show();
            clearInput();
            loadData();
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Sĩ số phải là số.", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateClass() {
        String code = edtCode.getText().toString();
        String name = edtName.getText().toString();
        String countStr = edtCount.getText().toString();

        if (code.isEmpty() || name.isEmpty() || countStr.isEmpty()) {
            Toast.makeText(requireContext(), "Vui lòng nhập đủ để cập nhật!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int count = Integer.parseInt(countStr);
            dbHelper.updateClass(code, name, count);
            Toast.makeText(requireContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
            clearInput();
            loadData();
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Sĩ số phải là số.", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteClass() {
        String code = edtCode.getText().toString();

        if (code.isEmpty()) {
            Toast.makeText(requireContext(), "Nhập mã lớp để xoá!", Toast.LENGTH_SHORT).show();
            return;
        }

        dbHelper.deleteClass(code);
        Toast.makeText(requireContext(), "Xoá thành công!", Toast.LENGTH_SHORT).show();
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
                requireContext(),
                R.layout.feature_task04_sqlite_class_list_item,
                cursor,
                fromColumns,
                toViews,
                0
        );

        listView.setAdapter(adapter);
    }
}
