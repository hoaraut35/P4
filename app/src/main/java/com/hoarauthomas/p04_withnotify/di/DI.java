package com.hoarauthomas.p04_withnotify.di;

import android.util.Log;

import com.hoarauthomas.p04_withnotify.api.FakeMeetingApiService;
import com.hoarauthomas.p04_withnotify.api.MeetingApiService;

public class DI {
    private static MeetingApiService service = new FakeMeetingApiService();

    public static MeetingApiService getMeetingApiService()
    {
        Log.i("THOMAS","[DI] Injection des données ..." + service.getMeetings().size());
        return service;
    }


}
