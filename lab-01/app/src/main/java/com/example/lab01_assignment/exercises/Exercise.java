package com.example.lab01_assignment.exercises;

import android.content.Context;

public interface Exercise {
    String getTitle();       // Tên bài
    String run(Context context); // Chạy bài, cần context để mở dialog
}
