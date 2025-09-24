package com.example.lab01_assignment.exercises;

import android.content.Context;
import androidx.appcompat.app.AlertDialog;

public class Bai8 implements Exercise {
    @Override
    public String getTitle() {
        return "Bài 8: Liệt kê tất cả số nguyên tố có 5 chữ số";
    }

    @Override
    public String run(Context context) {
        StringBuilder sb = new StringBuilder();

        for (int i = 10000; i <= 99999; i++) {
            if (isPrime(i)) {
                sb.append(i).append(", ");
            }
        }

        String result;
        if (sb.length() > 0) {
            result = sb.substring(0, sb.length() - 2); // bỏ dấu ", " cuối
        } else {
            result = "Không tìm thấy số nguyên tố nào có 5 chữ số!";
        }

        // Chuỗi kết quả sẽ rất dài. Nên ta tách hiển thị bằng scroll.
        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage(result)
                .setPositiveButton("OK", null)
                .show();
        return result;
    }

    // Hàm kiểm tra số nguyên tố
    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
