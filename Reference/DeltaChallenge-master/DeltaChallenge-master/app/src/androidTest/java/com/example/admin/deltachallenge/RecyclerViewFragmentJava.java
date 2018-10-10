package com.example.admin.deltachallenge;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.admin.deltachallenge.adapters.RandomNumbersAdapter;
import com.example.admin.deltachallenge.ui.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecyclerViewFragmentJava {

    private String itemData;
    private int position;


    @Before
    public void setup() throws InterruptedException {

        activityTestRule.getActivity()
                .getFragmentManager().beginTransaction().commit();

        Thread.sleep(3000);
        position = 3;


        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.rvNumbers);
        RandomNumbersAdapter adapter = (RandomNumbersAdapter) recyclerView.getAdapter();
        itemData = String.valueOf(adapter.getItem(position));


    }

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void testRecyclerViewCountList() {

        onView(withId(R.id.rvNumbers))
                .check(new RecyclerViewItemCountAssertion(40));

    }

    @Test
    public void testItemRecyclerView() {


        onView(withId(R.id.rvNumbers))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()))
                .perform(click());

        onView(withId(R.id.tvDisplayedNumber)).check(matches(withText(itemData)));


    }

    @Test
    public void averageIsNotEmpty() {

        onView(withId(R.id.tvDisplayedNumber)).check(matches(not(withText(""))));

    }


    @After
    public void tearUp() {


    }
}



