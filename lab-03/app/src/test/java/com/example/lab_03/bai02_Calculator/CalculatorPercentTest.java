package com.example.lab_03.bai02_Calculator;

import com.example.lab_03.bai02_Calculator.core.CalculatorCore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Kiểm thử logic xử lý dấu phần trăm (%) trong biểu thức
 */
public class CalculatorPercentTest {

    private final CalculatorCore calc = new CalculatorCore();

    /** Test chuyển đổi phần trăm đơn giản */
    @Test
    public void testPercentExpansion() {
        String expr = "50%";
        String expanded = calc.expandPercent(expr);
        assertEquals("(50/100)", expanded);
    }

    /** Test phần trăm trong biểu thức */
    @Test
    public void testPercentInExpression() {
        String expr = "200+10%";
        String expanded = calc.expandPercent(expr);
        assertEquals("200+(10/100)", expanded);
    }

    /** Test phần trăm chồng (25%%) */
    @Test
    public void testNestedPercent() {
        calc.setExpression("25%%");
        calc.applyPercentSmart();
        String expr = calc.getExpression();
        assertTrue(expr.contains("%"));
    }
}
