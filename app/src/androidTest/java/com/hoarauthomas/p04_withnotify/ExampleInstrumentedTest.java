package com.hoarauthomas.p04_withnotify;

import android.app.Activity;
import android.content.Context;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.utils.DeleteItemAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.hoarauthomas.p04_withnotify.utils.RecyclerViewItemCountAssertion.withItemCount;
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
        onView(withId(R.id.recyclerview)).check(matches(hasMinimumChildCount(1)));

    }

    //This test check if
    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem()
    {
        onView(withId(R.id.recyclerview)).check(withItemCount(3));
        // When perform a click on a delete icon
        onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0,  new DeleteItemAction()));
        // Then : the number of element is 3-1
        onView(withId(R.id.recyclerview)).check(withItemCount(2));
    }


    //TODO: voir le nombre d'élément avant l'ajout pour tester
    //Check if we can add a meeting to the list
    @Test
    public void myMeetingList_addMeetingAction_shouldAddItem()
    {


        onView(withId(R.id.floatingbtn2)).check(matches(isClickable()));
        onView(withId(R.id.floatingbtn2)).perform(click());

        onView(withId(R.id.subject_text)).perform(replaceText("Réunion TEST"));
        onView(withId(R.id.list_participants)).perform(replaceText("test@gmail.com"));
        onView(withId(R.id.list_location)).perform(replaceText("Luigi"));
        onView(withId(R.id.tiet_start_date)).perform(replaceText("2021/03/01"));
        onView(withId(R.id.tiet_start_time)).perform(replaceText("11H11"));

        onView(withId(R.id.btn_valid)).perform(click());

        onView(withId(R.id.recyclerview)).check(withItemCount(4));
    }


    @Test
    public void myMeetingList_filteredByDate_shouldFilteredByDate()
    {
       // onView(withId(R.id.menu_date)).perform(click());
    }

    @Test
    public void myMeetingList_filteredByLocation_shouldFilteredByLocation()
    {
        onView(withId(R.id.action_search)).perform(click());
      //  onView(withId(R.id.action_search)).perform(replaceText("Peach"));

    }




}