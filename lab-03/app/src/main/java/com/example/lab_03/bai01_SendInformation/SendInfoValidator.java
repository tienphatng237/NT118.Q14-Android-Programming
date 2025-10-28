package com.example.lab_03.bai01_SendInformation;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Lớp SendInfoValidator kiểm tra hợp lệ dữ liệu đầu vào
 * Hỗ trợ cả kiểm thử logic (Unit Test) và kiểm tra thực tế trong Activity.
 * ---------------------------------------------------------
 * Phân chia thành 2 nhóm phương thức:
 * 1. Kiểm tra logic cơ bản — phục vụ Unit Test tự động.
 * 2, Kiểm tra trực tiếp từ giao diện — hiển thị lỗi trực quan.
 */
public class SendInfoValidator {

    /* ============================================================
     * 1. KIỂM TRA LOGIC CƠ BẢN (DÙNG CHO UNIT TEST)
     * ============================================================ */

    /** Kiểm tra tên hợp lệ: không rỗng và có ít nhất 3 ký tự */
    public static boolean isNameValid(String name) {
        if (name == null) return false;
        String trimmed = name.trim();
        return !trimmed.isEmpty() && trimmed.length() >= 3;
    }

    /** Kiểm tra CMND hợp lệ: phải gồm đúng 9 chữ số */
    public static boolean isCMNDValid(String cmnd) {
        if (cmnd == null) return false;
        return cmnd.matches("\\d{9}");
    }

    /** Kiểm tra ít nhất 1 sở thích được chọn */
    public static boolean hasAtLeastOneHobby(boolean[] hobbies) {
        if (hobbies == null || hobbies.length == 0) return false;
        for (boolean h : hobbies) {
            if (h) return true;
        }
        return false;
    }

    /** Tổng hợp kiểm tra tất cả điều kiện logic */
    public static boolean validateAll(String name, String cmnd, boolean[] hobbies) {
        return isNameValid(name) && isCMNDValid(cmnd) && hasAtLeastOneHobby(hobbies);
    }


    /* ============================================================
     * 2. KIỂM TRA THỰC TẾ TỪ GIAO DIỆN (DÙNG CHO ACTIVITY)
     * ============================================================ */

    /**
     * Kiểm tra trực tiếp từ các thành phần giao diện:
     *  - Họ tên phải có ≥ 3 ký tự
     *  - CMND đúng 9 chữ số
     *  - Chọn ít nhất 1 sở thích
     * Nếu sai → hiển thị lỗi tại EditText hoặc Toast cảnh báo.
     */
    public static boolean validate(EditText etName,
                                   EditText etCMND,
                                   CheckBox cbSport,
                                   CheckBox cbMusic,
                                   CheckBox cbReading,
                                   Context context) {

        String name = etName.getText().toString().trim();
        String cmnd = etCMND.getText().toString().trim();

        // Kiểm tra tên
        if (!isNameValid(name)) {
            etName.setError("Tên phải có ít nhất 3 ký tự");
            return false;
        }
        // Kiểm tra CMND
        if (!isCMNDValid(cmnd)) {
            etCMND.setError("CMND phải gồm đúng 9 chữ số");
            return false;
        }
        // Kiểm tra sở thích
        boolean[] hobbies = {cbSport.isChecked(), cbMusic.isChecked(), cbReading.isChecked()};
        if (!hasAtLeastOneHobby(hobbies)) {
            Toast.makeText(context, "Chọn ít nhất 1 sở thích!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true; //  Dữ liệu hợp lệ
    }
}
