package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class Bai15Test {

    @Test
    public void testValidCodes() {
        assertTrue(Bai15.validateCode("002L1234"));
        assertTrue(Bai15.validateCode("003L9999"));
        assertTrue(Bai15.validateCode("005L0000"));
    }

    @Test
    public void testInvalidCodes_WrongPrefix() {
        assertFalse(Bai15.validateCode("001L1234")); // sai 00[2-5]
        assertFalse(Bai15.validateCode("006L1234")); // ngoài 2-5
    }

    @Test
    public void testInvalidCodes_WrongFormat() {
        assertFalse(Bai15.validateCode("0021234"));   // thiếu 'L'
        assertFalse(Bai15.validateCode("002LX234"));  // ký tự không hợp lệ
        assertFalse(Bai15.validateCode("002L12"));    // không đủ 4 số
        assertFalse(Bai15.validateCode("002L12345")); // dư số
    }
}
