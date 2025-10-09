package com.example.lab01_assignment.exercises;

import org.junit.Test;
import static org.junit.Assert.*;

public class Bai1Test {

    @Test
    public void testFirstValue() {
        String result = new Bai1().run(null);
        assertTrue("Kết quả phải bắt đầu bằng 14", result.startsWith("14"));
    }

    @Test
    public void testLastValue() {
        String result = new Bai1().run(null);
        assertTrue("Kết quả phải chứa 196", result.contains("196"));
    }

    @Test
    public void testExcludeMultiplesOf5() {
        String result = new Bai1().run(null);
        assertFalse("Không được chứa 35", result.contains("35"));
        assertFalse("Không được chứa 70", result.contains("70"));
        assertFalse("Không được chứa 105", result.contains("105"));
        assertFalse("Không được chứa 140", result.contains("140"));
        assertFalse("Không được chứa 175", result.contains("175"));
    }

    @Test
    public void testOutputFormat() {
        String result = new Bai1().run(null);
        assertFalse("Kết quả không được kết thúc bằng dấu phẩy", result.endsWith(","));
        assertFalse("Kết quả không được kết thúc bằng dấu phẩy + khoảng trắng", result.endsWith(", "));
    }
}
