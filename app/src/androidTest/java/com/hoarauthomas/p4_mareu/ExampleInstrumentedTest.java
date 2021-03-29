package com.hoarauthomas.p4_mareu;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Root;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.hoarauthomas.p4_mareu.api.MeetingApiService;
import com.hoarauthomas.p4_mareu.di.DI;
import com.hoarauthomas.p4_mareu.model.Meeting;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.service.autofill.Validators.not;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private MainActivity mActivity;
    private int ITEM_COUBNT = 3;
    private MeetingApiService myApiServiceForTest = DI.getMeetingApiService();
    private List<Meeting> myMeetingList = new ArrayList<>();


    @Rule
    public ActivityTestRule<MainActivity> meetingActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setup() {
        mActivity = meetingActivityRule.getActivity();
        //myApiServiceForTest.addMeeting(new Meeting("Sujet A","Toad", new Date(), "10H00","test@gmail.Com"));
        myMeetingList = myApiServiceForTest.getMeetings();
        //myApiServiceForTest.addMeeting(new Meeting("Sujet A","Toad", new Date(), "10H00","test@gmail.Com"));

        assertThat(mActivity, notNullValue());
    }

    //TODO: We ensure that our recycler is displaying at least on tiem
    @Test
    public void myMeetingList_shouldBeEmpty() {

        //First time we check if the list is empty
        onView(ViewMatchers.withId(R.id.recyclerview)).check(matches(hasMinimumChildCount(0)));
    }


    @Test
    public void addNewMEetingTes()
    {
        onView(withId(R.id.add_fab_btn)).perform(click());

        onView(withId(R.id.view_participants)).perform(click());
     //  onView(withId(R.id.list_participants)).perform(scrollTo(),click());


    }

    @Test
    public void addNewMeeting() {

        //We click on fab for add a new meeting
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.add_fab_btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        //We fill in the text fields
        onView(withId(R.id.subject_text)).perform(replaceText("Réunion TEST"));
        onView(withId(R.id.tiet_start_date)).perform(replaceText("2021/03/01"));
        onView(withId(R.id.tiet_start_time)).perform(replaceText("11H11"));
        onView(withId(R.id.list_location)).perform(replaceText("Luigi"));


        ViewInteraction materialAutoCompleteTextView = onView(
                allOf(withId(R.id.list_participants), childAtPosition(childAtPosition(withId(R.id.view_participants),0),0), isDisplayed()));


        onView(withId(R.id.list_participants)).perform(click());

        //onView(withId(R.id.list_participants)).inRoot();

        onView(withText("user1@gmail.com"))
                .inRoot(isPlatformPopup())
                .perform(click());



    }


    //This test check if
    //   @Test
  /*  public void myMeetingList_deleteAction_shouldRemoveItem() {
        if (myApiServiceForTest.getMeetings().size() == 0) {
            //We click on fab for add a new meeting
            onView(withId(R.id.floatingbtn2)).perform(click());

            //We fill in the text fields
            onView(withId(R.id.subject_text)).perform(replaceText("Réunion TEST"));
            onView(withId(R.id.tiet_start_date)).perform(replaceText("2021/03/01"));
            onView(withId(R.id.tiet_start_time)).perform(replaceText("11H11"));
            onView(withId(R.id.list_location)).perform(replaceText("Luigi"));


            ViewInteraction materialAutoCompleteTextView2 = onView(
                    allOf(withId(R.id.list_participants),
                            childAtPosition(
                                    childAtPosition(
                                            withId(R.id.view_participants),
                                            0),
                                    0),
                            isDisplayed()));
            materialAutoCompleteTextView2.perform(click());

          //  DataInteraction materialTextView2 = onData(anything())
          //          .inAdapterView(withClassName(is("android.widget.ListPopupWindow$DropDownListView")))
          //          .atPosition(0);
          //  materialTextView2.perform(click());



        //    ViewInteraction materialButton3 = onView( allOf(withId(R.id.btn_valid), withText("Valider"),   childAtPosition(childAtPosition( withId(android.R.id.content),0),7), isDisplayed()));
        //    materialButton3.perform(click());



          //  ViewInteraction appCompatImageView = onView(allOf(withId(R.id.view_delete_meeting), childAtPosition(childAtPosition(withId(R.id.recyclerview),0),5),isDisplayed()));
          //  appCompatImageView.perform(click());

        } else {
            ViewInteraction appCompatImageView = onView(
                    allOf(withId(R.id.view_delete_meeting),
                            childAtPosition(
                                    childAtPosition(
                                            withId(R.id.recyclerview),
                                            0),
                                    0),
                            isDisplayed()));
            appCompatImageView.perform(click());

        }
      //  onView(withId(R.id.recyclerview)).check(withItemCount(3));
        // When perform a click on a delete icon
      //  onView(withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteItemAction()));
        // Then : the number of element is 3-1
      //  onView(withId(R.id.recyclerview)).check(withItemCount(2));
    }


   */

    //TODO: voir le nombre d'élément avant l'ajout pour tester
    //Check if we can add a meeting to the list
    //  @Test
    public void myMeetingList_addMeetingAction_shouldAddItem() {

        //We click on fab for add a new meeting
        onView(withId(R.id.add_fab_btn)).perform(click());

        //We fill in the text fields
        onView(withId(R.id.subject_text)).perform(replaceText("Réunion TEST"));
        onView(withId(R.id.tiet_start_date)).perform(replaceText("2021/03/01"));
        onView(withId(R.id.tiet_start_time)).perform(replaceText("11H11"));
        onView(withId(R.id.list_location)).perform(replaceText("Luigi"));
        onView(withId(R.id.list_participants)).perform(replaceText("test@gmail.Com"));
        //  onView(withId(R.id.list_participants)).perform(click());
        // onView(allOf(withId(R.id.list_participants), childAtPosition(childAtPosition(withId(R.id.view_participants), 0), 0), isDisplayed()));
        // onView(withId(R.id.list_participants)).perform(click());

    }



    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

}