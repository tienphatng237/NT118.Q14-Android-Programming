package com.example.lab01_assignment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab01_assignment.exercises.*;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Exercise> exercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Đăng ký các bài tập
        exercises.add(new Bai1());
        exercises.add(new Bai2());
        exercises.add(new Bai3());
        exercises.add(new Bai4());

        // Lấy danh sách tiêu đề bài
        List<String> titles = new ArrayList<>();
        for (Exercise e : exercises) {
            titles.add(e.getTitle());
        }

        // Gắn danh sách vào ListView
        ListView lv = findViewById(R.id.lvExercises);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        lv.setAdapter(adapter);

        // Bắt sự kiện khi chọn bài
        lv.setOnItemClickListener((parent, view, pos, id) -> {
            Exercise ex = exercises.get(pos);
            ex.run(this);   // Truyền context để mỗi bài tự xử lý
        });
    }
}
