package com.example.piggybank;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EndToEndTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testUserFlow() {
        // Klik na gumb za dodavanje troška
        Espresso.onView(ViewMatchers.withId(R.id.button_add_expense)).perform(ViewActions.click());

        // Unos iznosa troška
        Espresso.onView(withId(R.id.edit_text_amount)).perform(ViewActions.replaceText("50.00"));

        // Unos opisa troška
        Espresso.onView(withId(R.id.edit_text_description)).perform(ViewActions.replaceText("Groceries"));

        // Klik na gumb za spremanje troška
        Espresso.onView(ViewMatchers.withId(R.id.button_save_expense)).perform(ViewActions.click());

        // Provera da li je novi trošak dodan u listu
        Espresso.onView(ViewMatchers.withText("Groceries")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withText("50.00")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Navigacija nazad na početni ekran
        Espresso.pressBack();

        // Provera da li smo se vratili na početni ekran
        Espresso.onView(withId(R.id.main_activity_layout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
