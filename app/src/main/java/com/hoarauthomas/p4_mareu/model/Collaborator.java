package com.hoarauthomas.p4_mareu.model;

public class Collaborator {

    private String mName;
    private String mEmail;

    //constructor
    public Collaborator(String mName, String mEmail) {
        this.mName = mName;
        this.mEmail = mEmail;
    }

    //getters and setters ...
    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

}
