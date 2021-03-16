package com.hoarauthomas.p04_withnotify;

import android.app.Activity;
import android.content.Context;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.hoarauthomas.p04_withnotify.model.Meeting;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private MainActivity mActivity;


    @Rule
    public ActivityTestRule<MainActivity> meetingActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setup()
    {
        mActivity = meetingActivityRule.getActivity();
        //assertThat(mActivity, notNullValue);
    }


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.hoarauthomas.p04_withnotify", appContext.getPackageName());
    }



    //This test check if we have a minimum of one item in the recyclerview
    @Test
    public void myMeetingList_shouldNotBeEmpty()
    {
        onView(ViewMatchers.withId(R.id.recyclerview)).check(matches(hasMinimumChildCount(1)));

    }







}