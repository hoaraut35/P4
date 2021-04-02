package com.hoarauthomas.p4_mareu;

import com.hoarauthomas.p4_mareu.api.FakeGenerator;
import com.hoarauthomas.p4_mareu.api.MeetingApiService;
import com.hoarauthomas.p4_mareu.di.DI;
import com.hoarauthomas.p4_mareu.model.Meeting;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private MeetingApiService service;

    @Before
    public void setup() {
        service = new DI().getMeetingApiService();
        service.getMeetings().clear();
        //adding fake meeting to the service API
        service.getMeetings().addAll(FakeGenerator.FakeMeeting);
    }

    //TODO : Test 1 : get full list of meetings added by API
    @Test
    public void getMeetingWithSuccess() {
        //We get the full list of API list
        List<Meeting> meetingActual = service.getMeetings();
        //We get the full list of FakeMeeting to compare ...
        List<Meeting> expectedMeeting = FakeGenerator.FakeMeeting;
        //We compare the two lists
        assertEquals(expectedMeeting.size(), meetingActual.size());
    }

    //TODO : Test2 : delete a meeting in the list
    @Test
    public void deleteMeetingWithSuccess() {
        //We get the first element of meeting list
        Meeting myMeeting = service.getMeetings().get(0);
        //We delete this
        service.deleteMeeting(myMeeting);
        //we check if the list still contains the element
        assertFalse(service.getMeetings().contains(myMeeting));
    }

    //TODO: Test 3 : add a new meeting in the list
    @Test
    public void addMeetingWithSuccess() {
        //We get the size of actual list
        int compteur = service.getMeetings().size();
        //We copy the first element in the list
        Meeting myMeetingToAdd = service.getMeetings().get(0);
        //We add this copy to the list
        service.addMeeting(myMeetingToAdd);
        //We check if the new size of original list is incremented by one
        assertEquals(compteur + 1, service.getMeetings().size());
    }

}