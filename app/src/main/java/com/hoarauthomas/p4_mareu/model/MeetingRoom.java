package com.hoarauthomas.p4_mareu.model;

public class MeetingRoom {
    private int mId;
    private String mRoomName;
    private String mRoomPosition;
    private int mMaxCollaborator;

    //constructor
    public MeetingRoom(int mId, String mRoomName, String mRoomPosition, int mMaxCollaborator) {
        this.mId = mId;
        this.mRoomName = mRoomName;
        this.mRoomPosition = mRoomPosition;
        this.mMaxCollaborator = mMaxCollaborator;
    }

    public String getmRoomName() {
        return mRoomName;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setmRoomName(String mRoomName) {
        this.mRoomName = mRoomName;
    }

    public int getmMaxCollaborator() {
        return mMaxCollaborator;
    }

    public void setmMaxCollaborator(int mMaxCollaborator) {
        this.mMaxCollaborator = mMaxCollaborator;
    }

    public String getmRoomPosition() {
        return mRoomPosition;
    }

    public void setmRoomPosition(String mRoomPosition) {
        this.mRoomPosition = mRoomPosition;
    }
}
