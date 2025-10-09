package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class Bai12Test {

    @Test
    public void testContainsNormal() {
        String result = Bai12.containsSubstring("Hello world", "world");
        assertTrue(result.contains("Có"));
        assertTrue(result.contains("Vị trí xuất hiện đầu tiên: 6"));
    }

    @Test
    public void testNotContains() {
        String result = Bai12.containsSubstring("Hello world", "abc");
        assertTrue(result.contains("Không"));
    }

    @Test
    public void testCaseInsensitive() {
        String result = Bai12.containsSubstring("Hello World", "world");
        assertTrue(result.contains("Không")); // phân biệt hoa/thường
        assertTrue(result.contains("Có"));    // không phân biệt hoa/thường
    }

    @Test
    public void testEmptyS2() {
        String result = Bai12.containsSubstring("abc", "");
        assertEquals("Vui lòng nhập s2 (không để trống).", result);
    }
}
