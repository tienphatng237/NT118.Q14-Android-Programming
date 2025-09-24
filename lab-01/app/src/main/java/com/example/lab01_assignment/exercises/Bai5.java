package com.example.lab01_assignment.exercises;

import android.content.Context;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

public class Bai5 implements Exercise {
    @Override
    public String getTitle() {
        return "Bài 5: In dãy Fibonacci với n số đầu tiên";
    }

    @Override
    public String run(Context context) {
        // Tạo ô nhập liệu
        EditText input = new EditText(context);
        input.setHint("Nhập số n");

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage("Nhập số n để in ra n số Fibonacci đầu tiên")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    try {
                        int n = Integer.parseInt(input.getText().toString().trim());

                        if (n <= 0) {
                            showResult(context, "Vui lòng nhập n > 0");
                            return;
                        }

                        // Tính Fibonacci
                        StringBuilder sb = new StringBuilder();
                        int f0 = 0, f1 = 1;
                        if (n >= 1) sb.append(f0);
                        if (n >= 2) sb.append(", ").append(f1);

                        for (int i = 2; i < n; i++) {
                            int fn = f0 + f1;
                            sb.append(", ").append(fn);
                            f0 = f1;
                            f1 = fn;
                        }

                        showResult(context, sb.toString());

                    } catch (Exception e) {
                        showResult(context, "Lỗi: Vui lòng nhập số hợp lệ!");
                    }
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
