package com.hoarauthomas.p04_withnotify.api;

import com.hoarauthomas.p04_withnotify.model.Collaborator;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.model.MeetingRoom;

import java.util.ArrayList;
import java.util.List;

import static com.hoarauthomas.p04_withnotify.api.FakeGenerator.generateCollaborators;
import static com.hoarauthomas.p04_withnotify.api.FakeGenerator.generateMeetingRooms;

public class FakeMeetingApiService implements MeetingApiService {

    private final List<Collaborator> collaboratorsList = generateCollaborators();
    public List<Meeting> meetingsList = new ArrayList<>();
    private final List<MeetingRoom> meetingRoomsList = generateMeetingRooms();

    @Override
    public List<Collaborator> getCollaborators() {
        return collaboratorsList;
    }

    @Override
    public List<Meeting> getMeetings() {
        return meetingsList;
    }

    @Override
    public List<MeetingRoom> getMeetingsRooms() {
        return meetingRoomsList;
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetingsList.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetingsList.remove(meeting);
    }
}
