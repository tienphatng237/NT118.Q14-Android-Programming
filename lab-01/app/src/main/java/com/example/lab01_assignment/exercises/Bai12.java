package com.example.lab01_assignment.exercises;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AlertDialog;

public class Bai12 implements Exercise {
    @Override
    public String getTitle() {
        return "Bài 12: Kiểm tra s1 có chứa s2?";
    }

    @Override
    public String run(Context context) {
        // Layout nhập 2 chuỗi s1, s2
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        EditText etS1 = new EditText(context);
        etS1.setHint("Nhập chuỗi s1");
        layout.addView(etS1);

        EditText etS2 = new EditText(context);
        etS2.setHint("Nhập chuỗi s2");
        layout.addView(etS2);

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setMessage("Nhập 2 chuỗi s1 và s2 để kiểm tra s1.contains(s2)")
                .setView(layout)
                .setPositiveButton("OK", (d, w) -> {
                    String s1 = etS1.getText().toString();
                    String s2 = etS2.getText().toString();

                    if (s2.isEmpty()) {
                        showResult(context, "Vui lòng nhập s2 (không để trống).");
                        return;
                    }

                    boolean contains = s1.contains(s2);
                    String msg = contains
                            ? "Có: s1 có chứa s2.\nVị trí xuất hiện đầu tiên: " + s1.indexOf(s2)
                            : "Không: s1 không chứa s2.";

                    // Gợi ý kiểm tra không phân biệt hoa/thường
                    msg += "\n\n(Gợi ý) Không phân biệt hoa/thường: "
                            + (s1.toLowerCase().contains(s2.toLowerCase()) ? "Có" : "Không");

                    showResult(context, msg);
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
