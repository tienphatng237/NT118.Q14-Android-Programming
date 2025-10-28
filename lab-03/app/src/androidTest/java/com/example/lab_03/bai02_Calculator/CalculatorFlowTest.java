package com.example.lab_03.bai02_Calculator;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.lab_03.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * 🔹 Flow Test: Kiểm thử toàn bộ thao tác chính trên UI của CalculatorActivity.
 * Chạy chậm có delay để người dùng quan sát rõ từng bước trên giao diện.
 */
@RunWith(AndroidJUnit4.class)
public class CalculatorFlowTest {

    @Rule
    public ActivityScenarioRule<CalculatorActivity> rule =
            new ActivityScenarioRule<>(CalculatorActivity.class);

    /** Hàm dừng tạm để quan sát UI giữa các test */
    private void pause(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /** 1. Phép tính cơ bản: 1 + 2 = 3 */
    @Test
    public void testEvaluateSimpleUI() {
        pause(1000);
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("3")));
        pause(5000);
    }

    /** 2. Làm tròn kết quả 1 ÷ 2 = 0.5 */
    @Test
    public void testTrimDoubleUI() {
        pause(1000);
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btnDiv)).perform(click());
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("0.5")));
        pause(5000);
    }

    /** 3. Thay toán tử cuối (2 ++ 3 => 2 + 3) */
    @Test
    public void testReplaceLastOperatorUI() {
        pause(1000);
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("5")));
        pause(5000);
    }

    /** 4️⃣ Áp dụng phần trăm (100 + 25%) = 125 */
    @Test
    public void testPercentUI() {
        pause(1000);
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btn5)).perform(click());
        onView(withId(R.id.btnPercent)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("125")));
        pause(5000);
    }

    /** 5️⃣ Phần trăm lồng nhau (25%%) */
    @Test
    public void testNestedPercentUI() {
        pause(1000);
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btn5)).perform(click());
        onView(withId(R.id.btnPercent)).perform(click());
        onView(withId(R.id.btnPercent)).perform(click());
        onView(withId(R.id.tvExpression)).check(matches(withText("25%%")));
        pause(5000);
    }

    /** 6. Đổi dấu (+/-) */
    @Test
    public void testToggleSignUI() {
        pause(1000);
        onView(withId(R.id.btn5)).perform(click());
        onView(withId(R.id.btnNegate)).perform(click());
        onView(withId(R.id.tvExpression)).check(matches(withText("(-5)")));
        pause(5000);
    }

    /** 7️⃣ Xoá 1 ký tự (⌫) */
    @Test
    public void testDeleteOneCharUI() {
        pause(1000);
        onView(withId(R.id.btn9)).perform(click());
        onView(withId(R.id.btnDelete)).perform(click());
        onView(withId(R.id.tvExpression)).check(matches(withText("")));
        pause(5000);
    }

    /** 8. Xoá toàn bộ (AC) */
    @Test
    public void testClearUI() {
        pause(1000);
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btnClear)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("0"))); // hoặc "" tuỳ UI
        pause(5000);
    }

    /** 9️⃣ Chia cho 0 */
    @Test
    public void testDivideByZeroUI() {
        pause(1000);
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btnDiv)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText("Lỗi")));
        pause(5000);
    }
}
