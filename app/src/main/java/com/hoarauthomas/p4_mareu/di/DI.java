package com.hoarauthomas.p4_mareu.di;

import com.hoarauthomas.p4_mareu.api.FakeMeetingApiService;
import com.hoarauthomas.p4_mareu.api.MeetingApiService;

public class DI {

    private MeetingApiService service = new FakeMeetingApiService();

    public MeetingApiService getMeetingApiService() {
        return service;
    }

}
