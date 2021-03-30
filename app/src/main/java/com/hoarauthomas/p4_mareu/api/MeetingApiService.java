package com.hoarauthomas.p4_mareu.api;

import com.hoarauthomas.p4_mareu.model.Collaborator;
import com.hoarauthomas.p4_mareu.model.Meeting;
import com.hoarauthomas.p4_mareu.model.MeetingRoom;

import java.util.List;

public interface MeetingApiService {
    List<Collaborator> getCollaborators();
    List<Meeting> getMeetings();
    List<MeetingRoom> getMeetingsRooms();
    void addMeeting(Meeting meeting);
    void deleteMeeting(Meeting meeting);
}
