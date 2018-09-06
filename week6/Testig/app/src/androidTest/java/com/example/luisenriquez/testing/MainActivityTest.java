package com.example.luisenriquez.testing;

import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


public class MainActivityTest
{
    private int number1 = 0;
    private int number2 = 0;

    private int result;

    //public static final String PACKAGE_NAME = "com.example.luisenriquez.testing";

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp()
    {
        number1 = 9674;
        number2 = 9;
        result = 9688;

    }

    @Test
    public void testing_addition_views()
    {
        /* ViewMatcher. */
        onView(withId(R.id.etNumber1))
                .perform(typeText(String.valueOf(number1)),
                        ViewActions.closeSoftKeyboard());

        /* ViewMatcher. */
        onView(withId(R.id.etNumber2))
                .perform(typeText(String.valueOf(number2)),
                        ViewActions.closeSoftKeyboard());

        /* ViewMatcher. */
        onView(withId(R.id.btnAddition))
                .perform(click());

        /* ViewMatcher. */
        onView(withId(R.id.tvResult))
                .check(matches(withText(String.valueOf(result))));
    }
}
