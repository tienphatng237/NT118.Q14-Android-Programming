package com.example.lab04_storage.task03.ui.internal;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab04_storage.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Task03InternalStorageFragment extends Fragment {

    private static final String FILE_NAME = "my_private_note.txt";

    private EditText etInputData;
    private ListView lvNotes;
    private Button btnAdd;
    private Button btnUpdate;
    private Button btnBack;

    private ArrayList<String> notesList;
    private ArrayAdapter<String> adapter;

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.feature_task03_internal_storage, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // Ánh xạ UI
        etInputData = view.findViewById(R.id.et_internal_data);
        lvNotes = view.findViewById(R.id.lv_internal_notes);
        btnAdd = view.findViewById(R.id.btn_internal_add);
        btnUpdate = view.findViewById(R.id.btn_internal_update);
        btnBack = view.findViewById(R.id.btn_internal_back);

        // Khởi tạo danh sách và Adapter
        notesList = new ArrayList<>();
        adapter = new ArrayAdapter<>(requireContext(), R.layout.item_note, R.id.tv_note_item, notesList);
        lvNotes.setAdapter(adapter);

        // Sự kiện click
        btnAdd.setOnClickListener(v -> saveNote(true));
        btnUpdate.setOnClickListener(v -> saveNote(false));

        // Nút quay lại Selection
        btnBack.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        // Tải ghi chú khi mở Fragment
        readNotesFromFile();

        lvNotes.setOnItemClickListener((parent, itemView, position, id) -> {
            etInputData.setText(notesList.get(position));
            Toast.makeText(getContext(),
                    "Đã chọn ghi chú thứ " + (position + 1) + ". Nhấn Cập nhật để ghi đè toàn bộ file.",
                    Toast.LENGTH_LONG).show();
        });
    }

    private void saveNote(boolean append) {
        String inputNote = etInputData.getText().toString().trim();

        if (inputNote.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập dữ liệu để lưu!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (append) {
            notesList.add(inputNote);
            writeNotesToFile(notesList, Context.MODE_PRIVATE);

            Toast.makeText(getContext(), "Thêm mới thành công!", Toast.LENGTH_SHORT).show();
        } else {
            notesList.clear();
            notesList.add(inputNote);
            writeNotesToFile(notesList, Context.MODE_PRIVATE);

            Toast.makeText(getContext(), "Cập nhật (Ghi đè file) thành công!", Toast.LENGTH_SHORT).show();
        }

        etInputData.setText("");
        adapter.notifyDataSetChanged();
    }

    private void writeNotesToFile(ArrayList<String> list, int mode) {
        FileOutputStream fos = null;
        try {
            fos = requireContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            for (String note : list) {
                fos.write((note + "\n").getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Lỗi khi lưu file: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            try {
                if (fos != null) fos.close();
            } catch (Exception ignored) {}
        }
    }

    private void readNotesFromFile() {
        notesList.clear();
        FileInputStream fis = null;
        try {
            fis = requireContext().openFileInput(FILE_NAME);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
            String line;

            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    notesList.add(line);
                }
            }

            adapter.notifyDataSetChanged();

        } catch (Exception e) {
            notesList.add("Không tìm thấy file. Hãy nhấn Thêm để tạo file!");
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (Exception ignored) {}
        }
    }
}
