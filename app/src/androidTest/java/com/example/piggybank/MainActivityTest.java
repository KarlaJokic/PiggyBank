package com.example.piggybank;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.click;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testAddReminderButton() {
        // Klikni na dugme "Add Reminder"
        onView(withId(R.id.button_add_reminder)).perform(click());

        // Provjeri da li se otvara ekran za dodavanje podsjetnika
        onView(withId(R.id.edit_text_reminder_title)).check(matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testSetBudgetButton() {
        // Klikni na dugme "Set Budget"
        onView(withId(R.id.button_set_budget)).perform(click());

        // Provjeri da li se otvara ekran za postavljanje budžeta
        onView(withId(R.id.edit_text_budget_category)).check(matches(ViewMatchers.isDisplayed()));
    }

}
