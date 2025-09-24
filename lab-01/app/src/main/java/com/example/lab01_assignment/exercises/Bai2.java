package com.example.lab01_assignment.exercises;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Bai2 implements Exercise {

    @Override
    public String getTitle() {
        return "Bài 2: Tính giai thừa";
    }
    @Override
    public String run(Context context) {
        // Tạo EditText để nhập số n
        EditText input = new EditText(context);
        input.setHint("Nhập số n");

        LinearLayout layout = new LinearLayout(context);
        layout.setPadding(50, 40, 50, 10);
        layout.addView(input);

        // Dùng dialog để nhập
        new AlertDialog.Builder(context)
                .setTitle("Nhập số n")
                .setView(layout)
                .setPositiveButton("OK", (dialog, which) -> {
                    try {
                        int n = Integer.parseInt(input.getText().toString());
                        if (n < 0) {
                            showResult(context, "Giai thừa không xác định cho số âm");
                            return;
                        }
                        long gt = 1;
                        for (int i = 1; i <= n; i++) gt *= i;
                        showResult(context, "Giai thừa của " + n + " là " + gt);
                    } catch (NumberFormatException e) {
                        showResult(context, "Vui lòng nhập số hợp lệ!");
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();

        return ""; // Không trả về trực tiếp, dùng dialog hiển thị kết quả
    }

    private void showResult(Context context, String message) {
        new AlertDialog.Builder(context)
                .setTitle("Kết quả")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
