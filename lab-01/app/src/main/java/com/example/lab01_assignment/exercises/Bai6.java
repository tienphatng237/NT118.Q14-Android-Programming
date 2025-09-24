package com.example.lab01_assignment.exercises;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;

public class Bai6 implements Exercise {
    @Override
    public String getTitle() {
        return "Bài 6: Tìm USCLN và BSCNN của hai số a, b";
    }

    @Override
    public String run(Context context) {
        // Tạo layout để chứa 2 ô nhập
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText inputA = new EditText(context);
        inputA.setHint("Nhập số a");
        layout.addView(inputA);

        EditText inputB = new EditText(context);
        inputB.setHint("Nhập số b");
        layout.addView(inputB);

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage("Nhập hai số nguyên dương a và b")
                .setView(layout)
                .setPositiveButton("OK", (dialog, which) -> {
                    try {
                        int a = Integer.parseInt(inputA.getText().toString().trim());
                        int b = Integer.parseInt(inputB.getText().toString().trim());

                        if (a <= 0 || b <= 0) {
                            showResult(context, "Vui lòng nhập a, b > 0");
                            return;
                        }

                        int uscln = gcd(a, b);
                        int bscnn = (a * b) / uscln;

                        String result = "USCLN(" + a + ", " + b + ") = " + uscln +
                                "\nBSCNN(" + a + ", " + b + ") = " + bscnn;

                        showResult(context, result);

                    } catch (Exception e) {
                        showResult(context, "Lỗi: Vui lòng nhập số hợp lệ!");
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
        return "";
    }

    // Thuật toán Euclid
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private void showResult(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setTitle("Kết quả")
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }
}
