package com.example.lab01_assignment.exercises;

import android.content.Context;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

public class Bai10 implements Exercise {
    @Override
    public String getTitle() {
        return "Bài 10: Đếm số từ trong chuỗi";
    }

    @Override
    public String run(Context context) {
        // Ô nhập chuỗi
        EditText input = new EditText(context);
        input.setHint("Nhập chuỗi ký tự");

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage("Nhập một chuỗi để đếm số từ:")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    String str = input.getText().toString().trim();

                    if (str.isEmpty()) {
                        showResult(context, "Số từ = 0 (chuỗi rỗng)");
                        return;
                    }

                    // Tách chuỗi bằng regex: khoảng trắng, tab, xuống dòng
                    String[] words = str.split("\\s+");
                    int count = words.length;

                    showResult(context, "Chuỗi có " + count + " từ.");
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
