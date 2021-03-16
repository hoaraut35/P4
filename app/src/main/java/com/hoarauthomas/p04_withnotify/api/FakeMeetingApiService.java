package com.hoarauthomas.p04_withnotify.api;

import android.util.Log;

import com.hoarauthomas.p04_withnotify.model.Collaborator;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.model.MeetingRoom;

import java.util.List;

import static com.hoarauthomas.p04_withnotify.api.FakeGenerator.generateCollaborators;
import static com.hoarauthomas.p04_withnotify.api.FakeGenerator.generateMeetingRooms;
import static com.hoarauthomas.p04_withnotify.api.FakeGenerator.generateMeetings;

public class FakeMeetingApiService implements MeetingApiService {

    //list declaration
    private List<Collaborator> collaboratorsList = generateCollaborators();
    private List<Meeting> meetingsList = generateMeetings();
    private List<Meeting> meetingsListWork;
    private List<MeetingRoom> meetingRoomsList = generateMeetingRooms();

    @Override
    public List<Collaborator> getCollaborators()
    {
        return collaboratorsList;
    }

    @Override
    public List<Meeting> getMeetings()
    {
        Log.i("THOMAS","[FakeMeetingApiService] récupération réunion(s) " + meetingsList.size() );
        meetingsListWork = meetingsList;
        return meetingsListWork;}

    @Override
    public List<MeetingRoom> getMeetingsRooms()
    {
        return meetingRoomsList;
    }




    @Override
    public void addMeeting(Meeting meeting)
    {

        meetingsListWork.add(meeting);
        Log.i("THOMAS","API ajout d'une réunion, tailel list : " + meetingsListWork.size());
    }

    @Override
    public void deleteMeeting(Meeting meeting)

    {

        meetingsListWork.remove(meeting);
        Log.i("THOMAS","API supprimer une réunion : " + meetingsListWork.size());
    }
}
