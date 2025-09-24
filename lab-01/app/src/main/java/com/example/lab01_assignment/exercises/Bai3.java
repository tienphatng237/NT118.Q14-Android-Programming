package com.example.lab01_assignment.exercises;

import android.content.Context;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

import java.util.LinkedHashMap;
import java.util.Map;

public class Bai3 implements Exercise {
    @Override
    public String getTitle() {
        return "Bài 3: Tạo map (i, i*i) từ 1 đến n";
    }

    @Override
    public String run(Context context) {
        // Tạo ô nhập số n
        EditText input = new EditText(context);
        input.setHint("Nhập số n");

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage("Hãy nhập số n:")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    try {
                        int n = Integer.parseInt(input.getText().toString());
                        if (n < 1) {
                            showResult(context, "Vui lòng nhập n >= 1");
                            return;
                        }

                        // Tạo map (i, i*i)
                        Map<Integer, Integer> map = new LinkedHashMap<>();
                        for (int i = 1; i <= n; i++) {
                            map.put(i, i * i);
                        }

                        // In map ra
                        showResult(context, map.toString());
                    } catch (Exception e) {
                        showResult(context, "Lỗi: Vui lòng nhập số nguyên hợp lệ");
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();

        return "";
    }

    // Hàm phụ để show kết quả
    private void showResult(Context context, String result) {
        new AlertDialog.Builder(context)
                .setTitle("Kết quả")
                .setMessage(result)
                .setPositiveButton("OK", null)
                .show();
    }
}
