package com.example.lab01_assignment.exercises;

import android.content.Context;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

public class Bai9 implements Exercise {
    @Override
    public String getTitle() {
        return "Bài 9: Phân tích số n thành thừa số nguyên tố";
    }

    @Override
    public String run(Context context) {
        // Ô nhập số n
        EditText input = new EditText(context);
        input.setHint("Nhập số n");

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage("Nhập số nguyên dương n:")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    try {
                        int n = Integer.parseInt(input.getText().toString().trim());

                        if (n <= 1) {
                            showResult(context, "Vui lòng nhập n > 1");
                            return;
                        }

                        String result = factorize(n);
                        showResult(context, n + " = " + result);

                    } catch (Exception e) {
                        showResult(context, "Lỗi: Vui lòng nhập số hợp lệ!");
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
        return "";
    }

    // Hàm phân tích thừa số nguyên tố
    private String factorize(int n) {
        StringBuilder sb = new StringBuilder();
        int num = n;

        for (int i = 2; i * i <= num; i++) {
            while (n % i == 0) {
                sb.append(i).append(" x ");
                n /= i;
            }
        }
        if (n > 1) sb.append(n); // thêm thừa số nguyên tố cuối cùng

        // Bỏ " x " ở cuối nếu có
        String result = sb.toString();
        if (result.endsWith(" x ")) {
            result = result.substring(0, result.length() - 3);
        }
        return result;
    }

    private void showResult(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setTitle("Kết quả")
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }
}
