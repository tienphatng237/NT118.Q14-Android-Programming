package com.example.lab01_assignment.exercises;

import android.content.Context;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

import java.util.HashMap;
import java.util.Map;

public class Bai11 implements Exercise {
    @Override
    public String getTitle() {
        return "Bài 11: Đếm số lần xuất hiện của các từ trong chuỗi";
    }

    @Override
    public String run(Context context) {
        // Ô nhập chuỗi
        EditText input = new EditText(context);
        input.setHint("Nhập chuỗi ký tự");

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage("Nhập một chuỗi để liệt kê số lần xuất hiện của các từ:")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    String str = input.getText().toString().trim();

                    if (str.isEmpty()) {
                        showResult(context, "Chuỗi rỗng, không có từ nào.");
                        return;
                    }

                    // Tách chuỗi thành các từ (dựa trên khoảng trắng, tab, xuống dòng)
                    String[] words = str.split("\\s+");

                    // Dùng HashMap để đếm số lần xuất hiện
                    Map<String, Integer> wordCount = new HashMap<>();
                    for (String word : words) {
                        wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                    }

                    // Ghép kết quả thành chuỗi
                    StringBuilder sb = new StringBuilder();
                    for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                        sb.append(entry.getKey())
                                .append(": ")
                                .append(entry.getValue())
                                .append(" lần\n");
                    }

                    showResult(context, sb.toString());
                })
                .setNegativeButton("Hủy", null)
                .show();
        return "";
    }

    private void showResult(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setTitle("Kết quả")
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }
}
