package com.example.lab01_assignment.exercises;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.*;

public class Bai13 implements Exercise {
    @Override
    public String getTitle() {
        return "Bài 13: Xử lý mảng (max, sort, chèn)";
    }

    @Override
    public String run(Context context) {
        // Nhập n và mảng A
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText edtN = new EditText(context);
        edtN.setHint("Nhập số phần tử n");
        layout.addView(edtN);

        final EditText edtArray = new EditText(context);
        edtArray.setHint("Nhập mảng A (cách nhau bởi dấu cách)");
        layout.addView(edtArray);

        new AlertDialog.Builder(context)
                .setTitle(getTitle())
                .setView(layout)
                .setPositiveButton("OK", (dialog, which) -> {
                    try {
                        int n = Integer.parseInt(edtN.getText().toString().trim());
                        String[] parts = edtArray.getText().toString().trim().split("\\s+");
                        if (parts.length != n) {
                            showResult(context, "Số phần tử không khớp với n!");
                            return;
                        }

                        int[] A = new int[n];
                        for (int i = 0; i < n; i++) {
                            A[i] = Integer.parseInt(parts[i]);
                        }

                        // a) Tìm max1 và max2
                        int max1 = -1, max2 = -1, idx1 = -1, idx2 = -1;
                        for (int i = 0; i < n; i++) {
                            if (A[i] > max1) {
                                max2 = max1; idx2 = idx1;
                                max1 = A[i]; idx1 = i;
                            } else if (A[i] > max2 && A[i] < max1) {
                                max2 = A[i]; idx2 = i;
                            }
                        }

                        // b) Sắp xếp tăng dần
                        Arrays.sort(A);

                        // In kết quả (a + b)
                        StringBuilder sb = new StringBuilder();
                        sb.append("Max 1: ").append(max1).append(" tại chỉ số ").append(idx1).append("\n");
                        if (idx2 != -1) {
                            sb.append("Max 2: ").append(max2).append(" tại chỉ số ").append(idx2).append("\n");
                        } else {
                            sb.append("Không có phần tử lớn thứ 2.\n");
                        }
                        sb.append("Mảng sau khi sắp xếp: ").append(Arrays.toString(A));

                        // Hiện dialog kết quả
                        new AlertDialog.Builder(context)
                                .setTitle("Kết quả A + B")
                                .setMessage(sb.toString())
                                .setPositiveButton("Chèn số X", (d, w) -> {
                                    // Sang bước C: nhập x để chèn
                                    askForX(context, A);
                                })
                                .setNegativeButton("Kết thúc", null)
                                .show();

                    } catch (Exception e) {
                        showResult(context, "Lỗi: " + e.getMessage());
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
        return "";
    }

    // Bước C: Nhập x để chèn
    private void askForX(Context context, int[] sortedArr) {
        final EditText edtX = new EditText(context);
        edtX.setHint("Nhập số nguyên x để chèn");

        new AlertDialog.Builder(context)
                .setTitle("Chèn x vào mảng")
                .setView(edtX)
                .setPositiveButton("OK", (dialog, which) -> {
                    try {
                        int x = Integer.parseInt(edtX.getText().toString().trim());
                        int[] newArr = insertSorted(sortedArr, x);

                        showResult(context, "Mảng sau khi chèn x: " + Arrays.toString(newArr));
                    } catch (Exception e) {
                        showResult(context, "Lỗi: " + e.getMessage());
                    }
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private int[] insertSorted(int[] arr, int x) {
        int[] newArr = new int[arr.length + 1];
        int i = 0, j = 0;
        boolean inserted = false;

        while (i < arr.length) {
            if (!inserted && x < arr[i]) {
                newArr[j++] = x;
                inserted = true;
            } else {
                newArr[j++] = arr[i++];
            }
        }
        if (!inserted) newArr[j] = x;
        return newArr;
    }

    private void showResult(Context context, String msg) {
        new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .show();
    }
}
