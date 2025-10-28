package com.example.lab_03.bai02_Calculator;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.lab_03.R;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Flow Test cho CalculatorActivity
 * Kiểm thử toàn bộ thao tác chính trên UI với delay giúp quan sát dễ dàng.
 * ID của các nút hoàn toàn khớp với layout XML.
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // đảm bảo test chạy theo thứ tự
public class CalculatorFlowTest {

    @Rule
    public ActivityScenarioRule<CalculatorActivity> rule =
            new ActivityScenarioRule<>(CalculatorActivity.class);

    /** Hàm dừng để quan sát giữa các test */
    private void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* ============================================================
     * NHÓM 1 — BIỂU THỨC CƠ BẢN
     * ============================================================ */

    /** 1. Phép tính cơ bản: 2 + 3 × 4 = 14 */
    @Test
    public void test01_EvaluateSimpleUI() {
        pause(1000);
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnMul)).perform(click());
        onView(withId(R.id.btn4)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("14")));
        pause(3000);
    }

    /** 2. Định dạng kết quả hiển thị (7 ÷ 2 = 3.5) */
    @Test
    public void test02_TrimDoubleUI() {
        pause(1000);
        onView(withId(R.id.btn7)).perform(click());
        onView(withId(R.id.btnDiv)).perform(click());
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("3.5")));
        pause(3000);
    }

    /** 3. Thay toán tử cuối (2 ++ 3 → 2 + 3 = 5) */
    @Test
    public void test03_ReplaceLastOperatorUI() {
        pause(1000);
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("5")));
        pause(5000);
    }

    /** 4. Chia cho 0 → Lỗi */
    @Test
    public void test04_DivideByZeroUI() {
        pause(1000);
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btnDiv)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("Lỗi")));
        pause(5000);
    }

    /* ============================================================
     * NHÓM 2 — PHẦN TRĂM (%)
     * ============================================================ */

    /** 5. Phần trăm đơn giản: 50% = 0.5 */
    @Test
    public void test05_PercentSimpleUI() {
        pause(1000);
        onView(withId(R.id.btn5)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btnPercent)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("0.5")));
        pause(5000);
    }

    /** 6. Phần trăm trong biểu thức: 200 + 10% = 200.1 */
    @Test
    public void test06_PercentInExpressionUI() {
        pause(1000);
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btnPercent)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("200.1")));
        pause(5000);
    }

    /** 7. Phần trăm lồng nhau: 25%% = 0.0025 */
    @Test
    public void test07_NestedPercentUI() {
        pause(1000);
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btn5)).perform(click());
        onView(withId(R.id.btnPercent)).perform(click());
        onView(withId(R.id.btnPercent)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("0.0025")));
        pause(5000);
    }

    /* ============================================================
     * NHÓM 3 — ĐỔI DẤU (+/-)
     * ============================================================ */

    /** 8. Đổi dấu khi đang rỗng → (- */
    @Test
    public void test08_ToggleSignOnEmptyUI() {
        pause(1000);
        onView(withId(R.id.btnNegate)).perform(click());
        onView(withId(R.id.tvExpression)).check(matches(withText("(-")));
        pause(5000);
    }

    /** 9. Toggle dấu quanh số hiện tại → (-5) */
    @Test
    public void test09_ToggleSignAroundNumberUI() {
        pause(1000);
        onView(withId(R.id.btn5)).perform(click());
        onView(withId(R.id.btnNegate)).perform(click());
        onView(withId(R.id.tvExpression)).check(matches(withText("(-5)")));
        pause(5000);
    }

    /** 10. Toggle lại để bỏ dấu âm → 5 */
    @Test
    public void test10_ToggleOffUI() {
        pause(1000);
        onView(withId(R.id.btn5)).perform(click());
        onView(withId(R.id.btnNegate)).perform(click());
        onView(withId(R.id.btnNegate)).perform(click());
        onView(withId(R.id.tvExpression)).check(matches(withText("5")));
        pause(5000);
    }
}
