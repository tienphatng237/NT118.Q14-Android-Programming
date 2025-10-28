package com.example.lab_03.bai02_Calculator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * CalculatorTestSuite
 * Gom toàn bộ unit test của bài 2 (Máy tính cơ bản) vào 1 suite.
 * Giúp chạy toàn bộ test chỉ bằng 1 lần nhấn Run.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorCoreTest.class,
        CalculatorPercentTest.class,
        CalculatorToggleSignTest.class
})
public class CalculatorTestSuite {
    // Không viết gì thêm, chỉ dùng để gom nhóm test
}
