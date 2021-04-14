package com.hoarauthomas.p4_mareu;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.hoarauthomas.p4_mareu.api.MeetingApiService;
import com.hoarauthomas.p4_mareu.di.DI;
import com.hoarauthomas.p4_mareu.utils.DeleteItemAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.hoarauthomas.p4_mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.allOf;
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
    private final MeetingApiService myApiServiceForTest = new DI().getMeetingApiService();

    //to work with an activity in this case MainActivity
    @Rule
    public ActivityTestRule<MainActivity> meetingActivityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setup() {
        mActivity = meetingActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    //TODO: check if the recyclerview is empty
    @Test
    public void myMeetingList_shouldBeEmpty() {

        //check if the list is empty
        onView(ViewMatchers.withId(R.id.recyclerview)).check(matches(hasMinimumChildCount(0)));
    }

    //TODO: check if we can add a new meeting
    @Test
    public void add_Meeting_shouldAdd() {

        //retrieve the size of the list before adding a meeting
        int total_of_meeting = myApiServiceForTest.getMeetings().size();
        //adding 2 meeting(s) is this cae
        int nb_meetingToAdd = 2;
        //for more detail see addNewMeetingForTest ...
        for (int i = 1; i <= nb_meetingToAdd; i++) {
            addNewMeetingFortTest("add_meeting_" + i + " to " + nb_meetingToAdd, "", "");
        }
        //check if the recyclerview is incremented by nb_meetingToAdd (in this case 2)
        onView(ViewMatchers.withId(R.id.recyclerview)).check(withItemCount(total_of_meeting + nb_meetingToAdd));
        //for visual
        myPause();
        myPause();
        myPause();
    }

    //TODO: check if we can remove a meeting
    @Test
    public void remove_Meeting_shouldRemove() {
        //for visual
        myPause();
        myPause();
        //add one meetings to delete, see addNewMeetingForTest for more details...
        addNewMeetingFortTest("removeMeeting", "", "");
        //for visual
        myPause();
        myPause();
        //delete this meeting by click on trash
        onView(ViewMatchers.withId(R.id.recyclerview)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteItemAction()));
        //for visual
        myPause();
        myPause();
        //check if the recyclerview is equal to 0 after delete action
        onView(ViewMatchers.withId(R.id.recyclerview)).check(withItemCount(0));
        //add a pause for visual
        myPause();
        myPause();
    }

    //TODO: filter meetings by room
    @Test
    public void filterMeetingByRoom_shoulbBeFilterByRoom() {

        //addition of some meetings with two forced to the Mario room
        addNewMeetingFortTest("randomRoom1", "", "2021-03-01");
        addNewMeetingFortTest("fixedRoom", "Mario", "2021-03-02");
        addNewMeetingFortTest("randomRoom2", "", "2021-03-03");
        addNewMeetingFortTest("fixedRoom", "Mario", "");
        //for visual
        myPause();
        //click on SearchView for replace the text...
        onView(allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageView")), withContentDescription("Search"),
                childAtPosition(
                        allOf(withClassName(is("android.widget.LinearLayout")),
                                childAtPosition(
                                        withId(R.id.action_search),
                                        0)),
                        1),
                isDisplayed())).perform(click());
        //for visual
        myPause();
        //replace the text with Mario to filter this room
        onView(allOf(withClassName(is("android.widget.SearchView$SearchAutoComplete")),
                childAtPosition(
                        allOf(withClassName(is("android.widget.LinearLayout")),
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1)),
                        0),
                isDisplayed())).perform(replaceText("Mario"), closeSoftKeyboard());
        //for visual
        myPause();
        myPause();
        myPause();
        myPause();

        //we check if we have two rooms (filter MArio)
        onView(ViewMatchers.withId(R.id.recyclerview)).check(withItemCount(2));


        //for disable the filter after work
        ViewInteraction appCompatImageView2 = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageView")), withContentDescription("Clear query"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.LinearLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                1)),
                                1),
                        isDisplayed()));
        appCompatImageView2.perform(click());
        //for visual
        myPause();
        myPause();
        myPause();
    }

    //TODO: filter meetings by date ex:2021-11-02 and return the full list after work
    @Test
    public void filterMeetingByDate_shouldBeFilterByDate() {

        //add new meetings to the list with three dates
        addNewMeetingFortTest("2021_11_01", "", "2021-11-01");
        addNewMeetingFortTest("2021_11_02", "Luigi", "2021-11-02");//tested date here !!!!
        addNewMeetingFortTest("2021_11_03", "Toad", "2021-11-03");
        //for visual
        myPause();
        //process to choice a date
        onView(allOf(withContentDescription("More options"),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.action_bar),
                                1),
                        1),
                isDisplayed())).perform(click());
        //for visual
        myPause();
        //click on menu Filtrer par date
        onView(allOf(withId(R.id.title), withText("Filtrer par date"), isDisplayed())).perform(click());
        //for visual
        myPause();
        //set a date in datepickerdialog forced to 2021-11-2 in this case
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2021, 11, 2));
        //for visual
        myPause();
        //perform a click to validate
        onView(withText("OK")).perform(click());
        //for visual
        myPause();
        myPause();
        myPause();
        myPause();
        myPause();
        myPause();
        //for disable the filter after work ...
        onView(allOf(withContentDescription("More options"),
                childAtPosition(
                        childAtPosition(
                                withId(R.id.action_bar),
                                1),
                        1),
                isDisplayed())).perform(click());
        //for visual
        myPause();
        //perform a click on menu Filtrer par date
        onView(allOf(withId(R.id.title), withText("Filtrer par date"), isDisplayed())).perform(click());
        //for visual
        myPause();
        //perform a click on ANNULER to disable filter by date
        onView(withText("ANNULER")).perform(click());
        //for visual
        myPause();
        myPause();
        myPause();
        myPause();
    }


    //TODO: this tool add a new meeting with a subject,room, date forced or not
    public void addNewMeetingFortTest(String subject, String room, String date) {

        //generate random list for test
        List<String> mySubjects = Arrays.asList("Réunion A", "Réunion B", "Réunion C", "Réunion D");
        List<String> myRooms = Arrays.asList("Luigi", "Peach", "Toad", "Yoshi", "Boser", "Wario");
        //click on the fab button to open the activity for add a new meeting
        onView(withId(R.id.add_fab_btn)).perform(click());

        Random rand = new Random();

        //fill in the text fields
        if (subject.length() == 0) {
            myPause();
            onView(withId(R.id.subject_text)).perform(replaceText(mySubjects.get(rand.nextInt(mySubjects.size()))));
        } else {
            myPause();
            onView(withId(R.id.subject_text)).perform(replaceText(subject));
        }
        //for visual
        myPause();
        if (date.length() == 0) {
            onView(withId(R.id.tiet_start_date)).perform(replaceText("2021-11-26"));
        } else {
            onView(withId(R.id.tiet_start_date)).perform(replaceText(date));
        }

        myPause();
        onView(withId(R.id.tiet_start_time)).perform(replaceText("11H11"));

        myPause();
        if (room.length() == 0) {
            onView(withId(R.id.list_location)).perform(replaceText(myRooms.get(rand.nextInt(myRooms.size()))));
        } else {
            onView(withId(R.id.list_location)).perform(replaceText(room.toString()));
        }

        myPause();
        //click on the list of participants
        onView(withId(R.id.list_participants)).perform(click());

        myPause();

        //click on the fist element in the list of participants
        onView(withText("user1@gmail.com")).inRoot(isPlatformPopup()).perform(click());

        myPause();

        //click on valid button to add the new meeting to the list
        onView(withId(R.id.btn_valid)).perform(click());

        myPause();

    }

    //TODO: add a pause for visual comfort
    public void myPause() {
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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