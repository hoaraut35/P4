package com.hoarauthomas.p04_withnotify.api;

import com.hoarauthomas.p04_withnotify.model.Collaborator;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.model.MeetingRoom;

import java.util.List;

public interface MeetingApiService {
    List<Collaborator> getCollaborators();

    List<Meeting> getMeetings();

    List<MeetingRoom> getMeetingsRooms();

    void addMeeting(Meeting meeting);

    void deleteMeeting(Meeting meeting);

}
