package com.hoarauthomas.p04_withnotify.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class Meeting implements Parcelable {
    private String mSubject;
    private String mPosition;
    private Date mDate;
    private LocalTime localTime;
    private String mStartTime;
    private String mEndTime;
    private String mParticipants;

    public Meeting(String mSubject, String mPosition, Date mDate, String mStartTime, String mParticipants) {
        this.mSubject = mSubject;
        this.mPosition = mPosition;
        this.mDate = mDate;
        this.mStartTime = mStartTime;

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

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mSubject);
        dest.writeString(this.mPosition);
        dest.writeLong(this.mDate != null ? this.mDate.getTime() : -1);
        dest.writeString(this.mStartTime);
        dest.writeString(this.mEndTime);
        dest.writeString(this.mParticipants);
    }

    public void readFromParcel(Parcel source) {
        this.mSubject = source.readString();
        this.mPosition = source.readString();
        long tmpMDate = source.readLong();
        this.mDate = tmpMDate == -1 ? null : new Date(tmpMDate);
        this.mStartTime = source.readString();
        this.mEndTime = source.readString();
        this.mParticipants = source.readString();
    }

    protected Meeting(Parcel in) {
        this.mSubject = in.readString();
        this.mPosition = in.readString();
        long tmpMDate = in.readLong();
        this.mDate = tmpMDate == -1 ? null : new Date(tmpMDate);
        this.mStartTime = in.readString();
        this.mEndTime = in.readString();
        this.mParticipants = in.readString();
    }

    public static final Parcelable.Creator<Meeting> CREATOR = new Parcelable.Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel source) {
            return new Meeting(source);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };
}
