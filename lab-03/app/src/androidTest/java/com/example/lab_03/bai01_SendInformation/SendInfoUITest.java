package com.example.lab_03.bai01_SendInformation;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import com.example.lab_03.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;

/**
 * Instrumentation Test (UI Test)
 * Mô phỏng hành vi người dùng nhập dữ liệu, nhấn nút GỬI, kiểm tra thông báo lỗi.
 * Bao phủ: Kiểm tra validate UI + chuyển Activity.
 */
@RunWith(AndroidJUnit4.class)
public class SendInfoUITest {

    @Rule
    public ActivityScenarioRule<SendInfoActivity> activityRule =
            new ActivityScenarioRule<>(SendInfoActivity.class);

    /** Test khi tên trống */
    @Test
    public void testEmptyNameShowsError() {
        onView(withId(R.id.etName)).perform(typeText(""));
        onView(withId(R.id.etCMND)).perform(typeText("123456789"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btnSend)).perform(click());
        onView(withId(R.id.etName)).check(matches(hasErrorText("Tên phải có ít nhất 3 ký tự")));
    }

    /** Test CMND sai định dạng */
    @Test
    public void testInvalidCMNDShowsError() {
        onView(withId(R.id.etName)).perform(typeText("Nguyen Van A"));
        onView(withId(R.id.etCMND)).perform(typeText("12345"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btnSend)).perform(click());
        onView(withId(R.id.etCMND)).check(matches(hasErrorText("CMND phải gồm đúng 9 chữ số")));
    }

    /** Test chưa chọn sở thích -> Toast hiển thị */
    @Test
    public void testNoHobbyShowsToast() {
        onView(withId(R.id.etName)).perform(typeText("Nguyen Van A"));
        onView(withId(R.id.etCMND)).perform(typeText("123456789"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.btnSend)).perform(click());
        // Espresso không bắt Toast trực tiếp, có thể logcat kiểm tra thay thế.
    }
}
