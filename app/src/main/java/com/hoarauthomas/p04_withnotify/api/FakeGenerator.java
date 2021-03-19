package com.hoarauthomas.p04_withnotify.api;

import android.util.Log;

import com.hoarauthomas.p04_withnotify.model.Collaborator;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.model.MeetingRoom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeGenerator {
    public static List<Collaborator> FakeCollaborators = Arrays.asList(
            new Collaborator("USER1", "user1@gmail.com"),
            new Collaborator("USER2", "user2@gmail.com"),
            new Collaborator("USER3", "user3@gmail.com"),
            new Collaborator("USER4", "user4@gmail.com"),
            new Collaborator("USER5", "user5@gmail.com"),
            new Collaborator("USER6", "user6@gmail.com"),
            new Collaborator("USER7", "user7@gmail.com"),
            new Collaborator("USER8", "user8@gmail.com"),
            new Collaborator("USER9", "user9@gmail.com")
    );

    static List<Collaborator> generateCollaborators() {
        return new ArrayList<>(FakeCollaborators);
    }

    public static List<MeetingRoom> FakeMeetingRooms = Arrays.asList(
            new MeetingRoom(1, "Mario", "Rennes", 10),
            new MeetingRoom(2, "Luigi", "Toulouse", 10),
            new MeetingRoom(3, "Peach", "Paris", 10),
            new MeetingRoom(4, "Toad", "Nantes", 10),
            new MeetingRoom(5, "Yoshi", "Vannes", 10),
            new MeetingRoom(6, "Bowser", "Brest", 10),
            new MeetingRoom(7, "Wario", "Lannion", 10),
            new MeetingRoom(8, "Waluigi", "Quimper", 10),
            new MeetingRoom(9, "Bidosaure", "Laval", 10),
            new MeetingRoom(10, "Bero", "Le MAns", 10)
    );

    static List<MeetingRoom> generateMeetingRooms() {
        return new ArrayList<>(FakeMeetingRooms);
    }

    public static List<Meeting> FakeMeeting = Arrays.asList(


    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(FakeMeeting);
    }


}
