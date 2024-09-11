package com.example.piggybank;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EndToEndTest {

    private UiDevice device;
    private static final String TAG = "EndToEndTest";

    @Before
    public void setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        launchApp();
        waitForIdle(5000);
    }

    private void launchApp() {
        Context context = ApplicationProvider.getApplicationContext();
        Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage("com.example.piggybank");
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);
        }
        SystemClock.sleep(5000); // Čekanje za pokretanje aplikacije
    }

    private void waitForIdle(long timeout) {
        long endTime = SystemClock.uptimeMillis() + timeout;
        while (SystemClock.uptimeMillis() < endTime) {
            if (device.getCurrentPackageName().equals("com.example.piggybank")) {
                return;
            }
            SystemClock.sleep(1000);
        }
    }

    private UiObject findUiObjectById(String resourceId, long timeout) throws UiObjectNotFoundException {
        UiObject uiObject = device.findObject(new UiSelector().resourceId(resourceId));
        if (!uiObject.waitForExists(timeout)) {
            throw new UiObjectNotFoundException("UI element with resourceId " + resourceId + " not found");
        }
        return uiObject;
    }

    @Test
    public void testUserFlow() throws UiObjectNotFoundException {
        Log.d(TAG, "Starting testUserFlow");

        // Klikni na dugme "Add Expense" koristeći Espresso
        onView(withId(R.id.button_add_expense)).perform(click());

        SystemClock.sleep(2000);

        // Unesi iznos koristeći Espresso
        onView(withId(R.id.edit_text_expense_amount)).perform(replaceText("50"));
        closeSoftKeyboard();

        // Unesi opis koristeći Espresso
        onView(withId(R.id.edit_text_expense_name)).perform(replaceText("Lunch"));
        closeSoftKeyboard();

        SystemClock.sleep(2000);

        // Klikni na dugme "Save" koristeći Espresso
        onView(withId(R.id.button_save_expense)).perform(click());

        SystemClock.sleep(4000);

        // Klikni na dugme "History" koristeći Espresso
        onView(withId(R.id.button_view_history)).perform(click());

        SystemClock.sleep(4000);

        // Provjera da li je novi trošak dodan u HistoryActivity koristeći UI Automator
        UiObject expenseItem = device.findObject(new UiSelector().textContains("Lunch"));
        if (!expenseItem.waitForExists(5000)) {
            Log.e(TAG, "Expense item 'Lunch' not found in HistoryActivity");
            throw new UiObjectNotFoundException("Expense item 'Lunch' not found in HistoryActivity");
        }
        Log.d(TAG, "Expense item 'Lunch' found in HistoryActivity");
        assertTrue(expenseItem.exists());
    }
}
