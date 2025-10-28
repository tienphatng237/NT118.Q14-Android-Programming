package com.example.lab_03.bai02_Calculator;

import com.example.lab_03.bai02_Calculator.core.CalculatorCore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Kiểm thử tổng quan cho các chức năng xử lý biểu thức cơ bản của CalculatorCore
 */
public class CalculatorCoreTest {

    private final CalculatorCore calc = new CalculatorCore();

    /** Test tự động đóng ngoặc */
    @Test
    public void testAutoCloseParentheses() {
        String expr = "(1+2*(3+4";
        String fixed = calc.autoCloseParentheses(expr);
        assertEquals("(1+2*(3+4))", fixed);
    }

    /** Test mở rộng phần trăm */
    @Test
    public void testExpandPercent() {
        String expr = "100+25%";
        String expanded = calc.expandPercent(expr);
        assertEquals("100+(25/100)", expanded);
    }

    /** Test tính giá trị biểu thức */
    @Test
    public void testEvaluateSimple() {
        double val = calc.evaluate("2+3*4");
        assertEquals(14.0, val, 0.0001);
    }

    /** Test biểu thức có ngoặc và số âm */
    @Test
    public void testEvaluateWithParentheses() {
        double val = calc.evaluate("2*(-3+5)");
        assertEquals(4.0, val, 0.0001);
    }

    /** Test định dạng kết quả hiển thị */
    @Test
    public void testTrimDouble() {
        assertEquals("3.5", calc.trimDouble(3.50000));
        assertEquals("10", calc.trimDouble(10.0));
        assertEquals("Lỗi", calc.trimDouble(Double.NaN));
    }
}
