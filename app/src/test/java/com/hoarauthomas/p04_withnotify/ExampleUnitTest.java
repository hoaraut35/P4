package com.hoarauthomas.p04_withnotify;

import com.hoarauthomas.p04_withnotify.api.FakeGenerator;
import com.hoarauthomas.p04_withnotify.api.MeetingApiService;
import com.hoarauthomas.p04_withnotify.di.DI;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.repository.MeetingRepository;

import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Assert;
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

    @Before
    public void setup()
    {
        service = DI.getMeetingApiService();
    }


    @Test
    public void getMeetingWithSuccess()
    {
        List<Meeting> meetingActual = service.getMeetings();
        List<Meeting> expectedMeeting = FakeGenerator.FakeMeeting;
        assertEquals(service.getMeetings().size(),expectedMeeting.size());
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
        service.getMeetings().add(myMeetingToAdd);
        assertEquals(compteur +1 , service.getMeetings().size());
    }

}