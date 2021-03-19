package com.hoarauthomas.p04_withnotify.model;

import java.time.LocalDate;

public class Meeting {
    private String mSubject;
    private String mPosition;
    private LocalDate mDate;
    private String mStartTime;
    private String mEndTime;
    private String mParticipants;

    public Meeting(String mSubject, String mPosition, LocalDate mDate, String mStartTime, String mParticipants) {
        this.mSubject = mSubject;
        this.mPosition = mPosition;
        this.mStartTime = mStartTime;
        this.mDate = mDate;
        this.mParticipants = mParticipants;
    }

    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmPosition() {
        return mPosition;
    }

    public void setmPosition(String mPosition) {
        this.mPosition = mPosition;
    }

    public String getmStartTime() {
        return mStartTime;
    }

    public void setmStartTime(String mStartTime) {
        this.mStartTime = mStartTime;
    }

    public String getmParticipants() {
        return mParticipants;
    }

    public void setmParticipants(String mParticipants) {
        this.mParticipants = mParticipants;
    }

    public String getmEndTime() {
        return mEndTime;
    }

    public void setmEndTime(String mEndTime) {
        this.mEndTime = mEndTime;
    }

    public LocalDate getmDate() {
        return mDate;
    }

    public void setmDate(LocalDate mDate) {
        this.mDate = mDate;
    }

}
