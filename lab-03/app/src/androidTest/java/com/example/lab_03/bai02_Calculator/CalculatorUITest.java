package com.example.lab_03.bai02_Calculator;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.example.lab_03.R;

@RunWith(AndroidJUnit4.class)
public class CalculatorUITest {

    @Rule
    public ActivityScenarioRule<CalculatorActivity> activityRule =
            new ActivityScenarioRule<>(CalculatorActivity.class);

    /** Test nhập phép tính đơn giản trên UI */
    @Test
    public void testSimpleAdditionUI() {
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btnAdd)).perform(click());
        onView(withId(R.id.btn2)).perform(click());
        onView(withId(R.id.btnEqual)).perform(click());

        onView(withId(R.id.tvResult)).check(matches(withText("3")));
    }
}
