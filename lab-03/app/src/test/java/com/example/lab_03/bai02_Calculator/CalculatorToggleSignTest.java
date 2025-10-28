package com.example.lab_03.bai02_Calculator;

import com.example.lab_03.bai02_Calculator.core.CalculatorCore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  Kiểm thử logic đổi dấu (+/-) trong CalculatorCore
 */
public class CalculatorToggleSignTest {

    private final CalculatorCore calc = new CalculatorCore();

    /**  Test đổi dấu khi đang rỗng */
    @Test
    public void testToggleSignOnEmpty() {
        calc.toggleSignSmart();
        assertTrue(calc.getExpression().contains("(-"));
    }

    /**  Test toggle dấu quanh số hiện tại */
    @Test
    public void testToggleSignAroundNumber() {
        calc.append("5");
        calc.toggleSignSmart();
        assertTrue(calc.getExpression().contains("(-5)"));
    }

    /**  Test toggle lại -> bỏ ngoặc âm */
    @Test
    public void testToggleOff() {
        calc.setExpression("(-5)");
        calc.toggleSignSmart(); // -> bỏ ngoặc
        assertFalse(calc.getExpression().contains("(-"));
    }
}
