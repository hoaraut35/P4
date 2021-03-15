package com.hoarauthomas.p04_withnotify.events;

import android.util.Log;

import com.hoarauthomas.p04_withnotify.model.Meeting;

public class AddMeetingEvent
{
    /**
     * Meeting to add
     */
    public Meeting meeting;

    public AddMeetingEvent(Meeting meeting)
    {
        Log.i("THOMAS","[EventBus] Ajout d'une réunion" + meeting.getmSubject());
        this.meeting = meeting;
    }
}
