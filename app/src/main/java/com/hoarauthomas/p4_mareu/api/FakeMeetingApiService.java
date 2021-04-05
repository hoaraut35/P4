package com.hoarauthomas.p4_mareu.api;

import com.hoarauthomas.p4_mareu.model.Collaborator;
import com.hoarauthomas.p4_mareu.model.Meeting;
import com.hoarauthomas.p4_mareu.model.MeetingRoom;

import java.util.ArrayList;
import java.util.List;

import static com.hoarauthomas.p4_mareu.api.FakeGenerator.generateCollaborators;
import static com.hoarauthomas.p4_mareu.api.FakeGenerator.generateMeetingRooms;

public class FakeMeetingApiService implements MeetingApiService {

    private final List<Collaborator> collaboratorsList = generateCollaborators();
    public List<Meeting> meetingsList = new ArrayList<>();
    private final List<MeetingRoom> meetingRoomsList = generateMeetingRooms();

    //return the collaborators list
    @Override
    public List<Collaborator> getCollaborators() {
        return collaboratorsList;
    }

    //return the meetinglist
    @Override
    public List<Meeting> getMeetings() {
        return meetingsList;
    }

    //return the rooms list
    @Override
    public List<MeetingRoom> getMeetingsRooms() {
        return meetingRoomsList;
    }

    //to add a new meeting to the list
    @Override
    public void addMeeting(Meeting meeting) {
        meetingsList.add(meeting);
    }

    //to remove a meetinf from the list
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingsList.remove(meeting);
    }
}
