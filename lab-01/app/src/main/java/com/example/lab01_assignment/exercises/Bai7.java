package com.example.lab01_assignment.exercises;

import android.content.Context;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;

public class Bai7 implements Exercise {
    @Override
    public String getTitle() {
        return "Bài 7: Liệt kê các số nguyên tố nhỏ hơn n";
    }

    @Override
    public String run(Context context) {
        // Tạo ô nhập n
        EditText input = new EditText(context);
        input.setHint("Nhập số n");

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage("Nhập số nguyên dương n:")
                .setView(input)
                .setPositiveButton("OK", (dialog, which) -> {
                    try {
                        int n = Integer.parseInt(input.getText().toString().trim());

                        if (n <= 2) {
                            showResult(context, "Không có số nguyên tố nào nhỏ hơn " + n);
                            return;
                        }

                        StringBuilder sb = new StringBuilder();
                        for (int i = 2; i < n; i++) {
                            if (isPrime(i)) {
                                sb.append(i).append(", ");
                            }
                        }

                        String result = sb.length() > 0
                                ? sb.substring(0, sb.length() - 2)
                                : "Không có số nguyên tố";

                        showResult(context, "Các số nguyên tố nhỏ hơn " + n + ":\n" + result);

                    } catch (Exception e) {
                        showResult(context, "Lỗi: Vui lòng nhập số hợp lệ!");
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
        return "";
    }

    // Hàm kiểm tra số nguyên tố
    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private void showResult(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setTitle("Kết quả")
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }
}
