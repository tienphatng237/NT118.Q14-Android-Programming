package com.example.lab_03.bai02_Calculator;

import com.example.lab_03.bai02_Calculator.core.CalculatorCore;
import static org.junit.Assert.*;

/**
 * Helper test dùng cho các case tổng hợp
 * (giúp kiểm tra chuỗi biểu thức và kết quả chính xác)
 */
public class CalculatorTestHelper {

    private final CalculatorCore calc = new CalculatorCore();

    /** So sánh kết quả thực tế và mong đợi */
    public void assertResult(String expr, double expected) {
        String expanded = calc.expandPercent(calc.autoCloseParentheses(expr));
        double val = calc.evaluate(expanded);
        assertEquals(expected, val, 0.0001);
    }

    /** Kiểm tra định dạng số hợp lệ */
    public void assertTrimmed(String input, String expected) {
        assertEquals(expected, calc.trimDouble(Double.parseDouble(input)));
    }

    /** Kiểm tra xem biểu thức có đúng dạng âm không */
    public void assertNegativeForm(String expr) {
        calc.setExpression(expr);
        assertTrue(calc.getExpression().contains("(-"));
    }
}
