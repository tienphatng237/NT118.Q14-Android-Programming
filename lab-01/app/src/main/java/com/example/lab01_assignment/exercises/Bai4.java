package com.example.lab01_assignment.exercises;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;

public class Bai4 implements Exercise {
    @Override
    public String getTitle() {
        return "Bài 4: Chuyển đổi số thập phân sang hệ cơ số B (2-16)";
    }

    @Override
    public String run(Context context) {
        // Layout nhập số và cơ số
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText inputNumber = new EditText(context);
        inputNumber.setHint("Nhập số thập phân (>=0)");

        EditText inputBase = new EditText(context);
        inputBase.setHint("Nhập cơ số B (2-16)");

        layout.addView(inputNumber);
        layout.addView(inputBase);

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage("Nhập số cần chuyển và cơ số B:")
                .setView(layout)
                .setPositiveButton("OK", (dialog, which) -> {
                    try {
                        int number = Integer.parseInt(inputNumber.getText().toString());
                        int base = Integer.parseInt(inputBase.getText().toString());

                        if (base < 2 || base > 16) {
                            showResult(context, "Cơ số B phải từ 2 đến 16");
                            return;
                        }

                        String result = convertToBase(number, base);
                        showResult(context, number + " (hệ 10) = " + result + " (hệ " + base + ")");
                    } catch (Exception e) {
                        showResult(context, "Lỗi: Vui lòng nhập số hợp lệ");
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();

        return "";
    }

    // Chuyển số thập phân sang hệ cơ số B
    private String convertToBase(int num, int base) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int m = num % base;
            char c;
            if (m >= 10) {
                // Dùng chr(55 + m): 10 -> 'A', 11 -> 'B', ..., 15 -> 'F'
                c = (char) (55 + m);
            } else {
                c = (char) (48 + m); // 48 là '0'
            }
            sb.insert(0, c);
            num /= base;
        }
        return sb.toString();
    }

    // Hiển thị kết quả
    private void showResult(Context context, String result) {
        new AlertDialog.Builder(context)
                .setTitle("Kết quả")
                .setMessage(result)
                .setPositiveButton("OK", null)
                .show();
    }
}
