package com.example.lab01_assignment.exercises;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.regex.Pattern;

public class Bai15 implements Exercise {

    @Override
    public String getTitle() {
        return "Bài 15: Kiểm tra 5 mã số theo định dạng";
    }

    @Override
    public String run(Context context) {
        // Tạo layout nhập liệu
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        int pad = dp(context, 12);
        layout.setPadding(pad, pad, pad, pad);

        final EditText[] edits = new EditText[5];
        for (int i = 0; i < 5; i++) {
            edits[i] = new EditText(context);
            edits[i].setHint("Nhập mã số thứ " + (i + 1));
            layout.addView(edits[i]);
        }

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setView(layout)
                .setPositiveButton("Kiểm tra", (d, w) -> {
                    boolean ok = true;
                    Pattern regex = Pattern.compile("^00[2-5]L\\d{4}$");
                    for (int i = 0; i < 5; i++) {
                        String code = edits[i].getText().toString().trim();
                        if (!regex.matcher(code).matches()) {
                            ok = false;
                            break;
                        }
                    }

                    if (ok) {
                        showResult(context, "Đúng rồi");
                    } else {
                        showResult(context, "Sai rồi");
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
        return "";
    }

    private void showResult(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }

    private int dp(Context ctx, int dp) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }
}
