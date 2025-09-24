package com.example.lab01_assignment.exercises;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Arrays;

public class Bai14 implements Exercise {
    private int[][] matrix;
    private int n, m;

    @Override
    public String getTitle() {
        return "Bài 14: Xử lý ma trận (max, prime, sort, cột nhiều prime)";
    }

    @Override
    public String run(Context context) {
        // Nhập n, m và ma trận
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        int pad = dp(context, 12);
        layout.setPadding(pad, pad, pad, pad);

        final EditText edtN = new EditText(context);
        edtN.setHint("Nhập số dòng n");
        layout.addView(edtN);

        final EditText edtM = new EditText(context);
        edtM.setHint("Nhập số cột m");
        layout.addView(edtM);

        final EditText edtValues = new EditText(context);
        edtValues.setHint("Nhập n*m phần tử (theo hàng, cách nhau dấu cách)");
        layout.addView(edtValues);

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setView(layout)
                .setPositiveButton("OK", (d, w) -> {
                    try {
                        n = Integer.parseInt(edtN.getText().toString().trim());
                        m = Integer.parseInt(edtM.getText().toString().trim());
                        String[] parts = edtValues.getText().toString().trim().split("\\s+");
                        if (parts.length != n * m) {
                            showResult(context, "Số phần tử không khớp n*m!");
                            return;
                        }

                        matrix = new int[n][m];
                        int idx = 0;
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < m; j++) {
                                matrix[i][j] = Integer.parseInt(parts[idx++]);
                            }
                        }

                        // Hiện dialog chọn chức năng + hiển thị ma trận dạng bảng
                        chooseFunction(context);

                    } catch (Exception e) {
                        showResult(context, "Lỗi nhập dữ liệu: " + e.getMessage());
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
        return "";
    }

    /** Dialog chọn chức năng + hiển thị ma trận dưới dạng bảng */
    private void chooseFunction(Context context) {
        // Root layout cho dialog
        LinearLayout root = new LinearLayout(context);
        root.setOrientation(LinearLayout.VERTICAL);
        int pad = dp(context, 12);
        root.setPadding(pad, pad, pad, pad);

        // Tiêu đề nhỏ
        TextView title = new TextView(context);
        title.setText("Ma trận A");
        title.setTextSize(16);
        title.setPadding(0, 0, 0, dp(context, 6));
        root.addView(title);

        // Bảng ma trận có scroll (ngang + dọc)
        View matrixView = buildMatrixView(context, matrix);
        root.addView(matrixView);

        // Label chọn chức năng
        TextView label = new TextView(context);
        label.setText("\nChọn chức năng");
        label.setTextSize(16);
        root.addView(label);

        // List các chức năng (single choice)
        String[] funcs = {
                "a) Tìm phần tử lớn nhất",
                "b) Thay số không nguyên tố = 0",
                "c) Sắp xếp từng cột tăng dần",
                "d) Tìm cột có nhiều số nguyên tố nhất"
        };
        ListView lv = new ListView(context);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setAdapter(new ArrayAdapter<>(context,
                android.R.layout.simple_list_item_single_choice, funcs));
        lv.setItemChecked(0, true);
        root.addView(lv);

        AlertDialog dlg = new AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Thực hiện", (di, w) -> {
                    int pos = lv.getCheckedItemPosition();
                    switch (pos) {
                        case 0: funcA(context); break;
                        case 1: funcB(context); break;
                        case 2: funcC(context); break;
                        case 3: funcD(context); break;
                        default: funcA(context);
                    }
                })
                .setNegativeButton("Đóng", null)
                .create();
        dlg.show();
    }

    /** Tạo view bảng TableLayout nằm trong ScrollView + HorizontalScrollView */
    private View buildMatrixView(Context context, int[][] mat) {
        // Table
        TableLayout table = new TableLayout(context);
        table.setStretchAllColumns(true);

        for (int i = 0; i < n; i++) {
            TableRow row = new TableRow(context);
            for (int j = 0; j < m; j++) {
                TextView cell = new TextView(context);
                cell.setText(String.valueOf(mat[i][j]));
                cell.setGravity(Gravity.CENTER);
                int pad = dp(context, 8);
                cell.setPadding(pad, pad, pad, pad);
                row.addView(cell);
            }
            table.addView(row);
        }

        // Scroll dọc
        ScrollView vScroll = new ScrollView(context);
        // Scroll ngang
        HorizontalScrollView hScroll = new HorizontalScrollView(context);
        hScroll.addView(table);
        vScroll.addView(hScroll);

        return vScroll;
    }

    // a) Tìm phần tử lớn nhất (giữ chỉ số đầu tiên)
    private void funcA(Context context) {
        int max = -1, row = -1, col = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    row = i; col = j;
                }
            }
        }
        showResult(context, "Max = " + max + " tại (" + row + ", " + col + ")");
    }

    // b) Thay số không nguyên tố bằng 0 và in ra
    private void funcB(Context context) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(isPrime(matrix[i][j]) ? matrix[i][j] : 0).append("\t");
            }
            sb.append("\n");
        }
        showResult(context, "Ma trận nguyên tố (không nguyên tố = 0):\n" + sb);
    }

    // c) Sắp xếp tất cả các cột tăng dần và in ra
    private void funcC(Context context) {
        for (int j = 0; j < m; j++) {
            Integer[] col = new Integer[n];
            for (int i = 0; i < n; i++) col[i] = matrix[i][j];
            Arrays.sort(col);
            for (int i = 0; i < n; i++) matrix[i][j] = col[i];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) sb.append(matrix[i][j]).append("\t");
            sb.append("\n");
        }
        showResult(context, "Ma trận sau khi sắp xếp cột tăng dần:\n" + sb);
    }

    // d) Tìm cột có nhiều số nguyên tố nhất
    private void funcD(Context context) {
        int bestCol = -1, maxCount = 0;
        for (int j = 0; j < m; j++) {
            int cnt = 0;
            for (int i = 0; i < n; i++) if (isPrime(matrix[i][j])) cnt++;
            if (cnt > maxCount) { maxCount = cnt; bestCol = j; }
        }
        showResult(context, "Cột nhiều số nguyên tố nhất: " + bestCol + " ("
                + maxCount + " số nguyên tố)");
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;
        for (int i = 2; i * i <= x; i++) if (x % i == 0) return false;
        return true;
    }

    private void showResult(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton("OK", (d, w) -> chooseFunction(context)) // quay lại menu + bảng
                .show();
    }

    private int dp(Context ctx, int dp) {
        float scale = ctx.getResources().getDisplayMetrics().density;
        return Math.round(dp * scale);
    }
}
