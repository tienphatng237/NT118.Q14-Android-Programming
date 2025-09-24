package com.example.lab01_assignment.exercises;

import android.content.Context;
import androidx.appcompat.app.AlertDialog;

public class Bai1 implements Exercise {
    public Bai1() {
        // khởi tạo (nếu cần)
    }

    @Override
    public String getTitle() {
        return "Bài 1: In ra các số là bội của 7 nhưng không là bội của 5 (từ 10 đến 200)";
    }

    @Override
    public String run(Context context) {
        StringBuilder sb = new StringBuilder();
        for (int i = 10; i <= 200; i++) {
            if (i % 7 == 0 && i % 5 != 0) {
                sb.append(i).append(", ");
            }
        }

        // bỏ dấu phẩy cuối
        String result = sb.toString();
        if (result.endsWith(", ")) {
            result = result.substring(0, result.length() - 2);
        }

        // hiển thị kết quả bằng AlertDialog
        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage(result)
                .setPositiveButton("OK", null)
                .show();

        return result;
    }
}
