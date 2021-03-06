package com.hoarauthomas.p4_mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Meeting implements Parcelable {
    private String mSubject;
    private String mRoom;
    private Date mDate;
    private String mStartTime;
    private String mEndTime;
    private String mParticipants;

    //constructor
    public Meeting(String mSubject, String mRoom, Date mDate, String mStartTime, String mParticipants) {
        this.mSubject = mSubject;
        this.mRoom = mRoom;
        this.mDate = mDate;
        this.mStartTime = mStartTime;
        this.mParticipants = mParticipants;
    }

    //getters and setters ...
    public String getmSubject() {
        return mSubject;
    }

    public void setmSubject(String mSubject) {
        this.mSubject = mSubject;
    }

    public String getmRoom() {
        return mRoom;
    }

    public void setmRoom(String mRoom) {
        this.mRoom = mRoom;
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

    //parecelable ...
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mSubject);
        dest.writeString(this.mRoom);
        dest.writeLong(this.mDate != null ? this.mDate.getTime() : -1);
        dest.writeString(this.mStartTime);
        dest.writeString(this.mEndTime);
        dest.writeString(this.mParticipants);
    }

    public void readFromParcel(Parcel source) {
        this.mSubject = source.readString();
        this.mRoom = source.readString();
        long tmpMDate = source.readLong();
        this.mDate = tmpMDate == -1 ? null : new Date(tmpMDate);
        this.mStartTime = source.readString();
        this.mEndTime = source.readString();
        this.mParticipants = source.readString();
    }

    protected Meeting(Parcel in) {
        this.mSubject = in.readString();
        this.mRoom = in.readString();
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
