package com.example.lab_03.bai01_SendInformation;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ✅ Unit Test cho lớp SendInfoValidator (test logic kiểm tra dữ liệu)
 * Bao gồm các trường hợp:
 * - Tên trống hoặc < 3 ký tự
 * - CMND không hợp lệ
 * - Chưa chọn sở thích
 */
public class SendInfoUnitTest {

    private SendInfoValidator validator;

    @Before
    public void setUp() {
        validator = new SendInfoValidator();
    }

    /** 🔹 Tên < 3 ký tự -> sai */
    @Test
    public void testInvalidNameTooShort() {
        assertFalse(validator.isNameValid("An"));
    }

    /** 🔹 Tên hợp lệ ≥ 3 ký tự */
    @Test
    public void testValidName() {
        assertTrue(validator.isNameValid("Nguyen Van A"));
    }

    /** 🔹 CMND sai định dạng (ít hơn 9 chữ số) */
    @Test
    public void testInvalidCMNDTooShort() {
        assertFalse(validator.isCMNDValid("12345"));
    }

    /** 🔹 CMND hợp lệ (đủ 9 chữ số) */
    @Test
    public void testValidCMND() {
        assertTrue(validator.isCMNDValid("250592829"));
    }

    /** 🔹 Chưa chọn sở thích nào */
    @Test
    public void testNoHobbySelected() {
        boolean[] hobbies = {false, false, false};
        assertFalse(validator.hasAtLeastOneHobby(hobbies));
    }

    /** 🔹 Chọn ít nhất 1 sở thích */
    @Test
    public void testAtLeastOneHobbySelected() {
        boolean[] hobbies = {true, false, false};
        assertTrue(validator.hasAtLeastOneHobby(hobbies));
    }
}
