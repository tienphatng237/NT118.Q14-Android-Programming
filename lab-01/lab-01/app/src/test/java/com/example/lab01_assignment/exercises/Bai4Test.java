package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class Bai4Test {

    @Test
    public void testConvertToBase_Binary() {
        assertEquals("1010", Bai4.convertToBase(10, 2));   // 10 (dec) -> 1010 (bin)
    }

    @Test
    public void testConvertToBase_Octal() {
        assertEquals("12", Bai4.convertToBase(10, 8));     // 10 (dec) -> 12 (oct)
    }

    @Test
    public void testConvertToBase_Hexadecimal() {
        assertEquals("A", Bai4.convertToBase(10, 16));     // 10 (dec) -> A (hex)
        assertEquals("1F", Bai4.convertToBase(31, 16));    // 31 (dec) -> 1F (hex)
    }

    @Test
    public void testConvertToBase_Decimal() {
        assertEquals("123", Bai4.convertToBase(123, 10));  // 123 (dec) -> 123 (dec)
    }

    @Test
    public void testConvertToBase_Zero() {
        assertEquals("0", Bai4.convertToBase(0, 2));       // 0 -> 0
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToBase_InvalidBaseTooLow() {
        Bai4.convertToBase(10, 1); // base < 2
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertToBase_InvalidBaseTooHigh() {
        Bai4.convertToBase(10, 20); // base > 16
    }
}
