package com.hoarauthomas.p04_withnotify.model;

import java.util.ArrayList;

public class Collaborator {

    private String mName;
    private String mEmail;

    public Collaborator(String mName, String mEmail) {
        this.mName = mName;
        this.mEmail = mEmail;
    }

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

    public static ArrayList<Collaborator> createContactsList(int numCollaborators) {
        ArrayList<Collaborator> collaborators = new ArrayList<Collaborator>();


        collaborators.add(new Collaborator("Thomas ", "hoarau.thomas@gmail.com"));
        collaborators.add(new Collaborator("Samuel ", "hoarau.samuel@gmail.com"));
        collaborators.add(new Collaborator("Jessica ", "hoarau.jessica@gmail.com"));
        collaborators.add(new Collaborator("Melissa ", "hoarau.melissa@gmail.com"));
        collaborators.add(new Collaborator("Camille ", "hoarau.camille@gmail.com"));
        collaborators.add(new Collaborator("Thomas ", "hoarau.thomas@gmail.com"));
        collaborators.add(new Collaborator("Samuel ", "hoarau.samuel@gmail.com"));
        collaborators.add(new Collaborator("Jessica ", "hoarau.jessica@gmail.com"));
        collaborators.add(new Collaborator("Melissa ", "hoarau.melissa@gmail.com"));
        collaborators.add(new Collaborator("Camille ", "hoarau.camille@gmail.com"));
        collaborators.add(new Collaborator("Thomas ", "hoarau.thomas@gmail.com"));
        collaborators.add(new Collaborator("Samuel ", "hoarau.samuel@gmail.com"));
        collaborators.add(new Collaborator("Jessica ", "hoarau.jessica@gmail.com"));
        collaborators.add(new Collaborator("Melissa ", "hoarau.melissa@gmail.com"));
        collaborators.add(new Collaborator("Camille ", "hoarau.camille@gmail.com"));
        collaborators.add(new Collaborator("Thomas ", "hoarau.thomas@gmail.com"));
        collaborators.add(new Collaborator("Samuel ", "hoarau.samuel@gmail.com"));
        collaborators.add(new Collaborator("Jessica ", "hoarau.jessica@gmail.com"));
        collaborators.add(new Collaborator("Melissa ", "hoarau.melissa@gmail.com"));
        collaborators.add(new Collaborator("Camille ", "hoarau.camille@gmail.com"));
        collaborators.add(new Collaborator("Thomas ", "hoarau.thomas@gmail.com"));
        collaborators.add(new Collaborator("Samuel ", "hoarau.samuel@gmail.com"));
        collaborators.add(new Collaborator("Jessica ", "hoarau.jessica@gmail.com"));
        collaborators.add(new Collaborator("Melissa ", "hoarau.melissa@gmail.com"));
        collaborators.add(new Collaborator("Camille ", "hoarau.camille@gmail.com"));
        collaborators.add(new Collaborator("Thomas ", "hoarau.thomas@gmail.com"));
        collaborators.add(new Collaborator("Samuel ", "hoarau.samuel@gmail.com"));
        collaborators.add(new Collaborator("Jessica ", "hoarau.jessica@gmail.com"));
        collaborators.add(new Collaborator("Melissa ", "hoarau.melissa@gmail.com"));
        collaborators.add(new Collaborator("Camille ", "hoarau.camille@gmail.com"));

        return collaborators;
    }


}
