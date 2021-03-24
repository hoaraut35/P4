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
public class ExampleUnitTest
{

    private MeetingApiService service;
    private int nbToDel;

    @Before
    public void setup()
    {
        service = DI.getMeetingApiService();
        service.getMeetings().addAll(FakeGenerator.FakeMeeting);
    }


    //check if the service retrieves the list of meetings
    @Test
    public void getMeetingWithSuccess()
    {
        List<Meeting> meetingActual = service.getMeetings();
        List<Meeting> expectedMeeting = FakeGenerator.FakeMeeting;
        assertEquals(expectedMeeting.size(),meetingActual.size());
    }

    @Test
    public void deleteMeetingWithSuccess()
    {
        Meeting myMeeting = service.getMeetings().get(0);
        service.deleteMeeting(myMeeting);
        //we check if the list don't contain myMeeting
        assertFalse(service.getMeetings().contains(myMeeting));
    }

    @Test
    public void addMeetingWithSuccess()
    {
        int compteur = service.getMeetings().size();
        Meeting myMeetingToAdd = service.getMeetings().get(0);
        service.addMeeting(myMeetingToAdd);
        assertEquals(compteur +1 , service.getMeetings().size());
    }

}