package com.hoarauthomas.p04_withnotify.repository;

import com.hoarauthomas.p04_withnotify.api.MeetingApiService;

public class MeetingRepository
{


    private final MeetingApiService meetingApiService;


    public MeetingRepository(MeetingApiService meetingApiService) {
        this.meetingApiService = meetingApiService;
    }
}
