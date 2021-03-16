package com.hoarauthomas.p04_withnotify.api;

import android.util.Log;

import com.hoarauthomas.p04_withnotify.model.Collaborator;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.model.MeetingRoom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FakeGenerator {
    public static List<Collaborator> FakeCollaborators = Arrays.asList(

            new Collaborator("USER1","user1@gmail.com"),
            new Collaborator("USER2","user2@gmail.com"),
            new Collaborator("USER3","user3@gmail.com"),
            new Collaborator("USER4","user4@gmail.com"),
            new Collaborator("USER5","user5@gmail.com"),
            new Collaborator("USER6","user6@gmail.com"),
            new Collaborator("USER7","user7@gmail.com"),
            new Collaborator("USER8","user8@gmail.com"),
            new Collaborator("USER9","user9@gmail.com")
    );

    static List<Collaborator> generateCollaborators()
    {
//        Log.i("THOMAS","[FakeGenerator] Récupération liste collaborateur(s) de base !" + FakeMeeting.size());
        return  new ArrayList<Collaborator>(FakeCollaborators);
    }




    public static List<MeetingRoom> FakeMeetingRooms = Arrays.asList(
            new MeetingRoom(1,"Mario","Rennes",10),
            new MeetingRoom(2,"Luigi","Toulouse",10),
            new MeetingRoom(3,"Peach","Paris",10),
            new MeetingRoom(4,"Toad","Nantes",10),
            new MeetingRoom(5,"Yoshi","Vannes",10),
            new MeetingRoom(6,"Bowser","Brest",10),
            new MeetingRoom(7,"Wario","Lannion",10),
            new MeetingRoom(8,"Waluigi","Quimper",10),
            new MeetingRoom(9,"Bidosaure","Laval",10),
            new MeetingRoom(10,"Bero","Le MAns",10)
    );


    static List<MeetingRoom> generateMeetingRooms()
    {
     //   Log.i("THOMAS","[FakeGenerator] Récupération liste salle(s) de base !" + FakeMeetingRooms.size());
        return  new ArrayList<MeetingRoom>(FakeMeetingRooms);
    }






    public static List<Meeting> FakeMeeting = Arrays.asList(
            new Meeting("Réunion A","Peach","2021-03-01", "14H00","test1@gmail.Com;testz@gmail.Com"),
        //    new Meeting("Réunion B","Mario","2021-03-02","11H00","test2@gmail.Com"),
          //  new Meeting("Réunion C","Luigi","2021-03-03","08H00","test3@gmail.Com"),
          //  new Meeting("Réunion D","Toad", "2021-03-04","16H00","test4@gmail.Com"),
          //  new Meeting("Réunion E","Yoshi","2021-03-01", "14H00","test1@gmail.Com;test2@gmail.Com;test3@gmail.Com"),
            //new Meeting("Réunion F","Peach","2021-03-02", "14H00","test1@gmail.Com"),
          //  new Meeting("Réunion G","Mario","2021-03-03","11H00","test2@gmail.Com"),
         //   new Meeting("Réunion H","Luigi","2021-03-053","08H00","test3@gmail.Com"),
         //   new Meeting("Réunion I","Toad", "2021-03-04","16H00","test4@gmail.Com"),
            new Meeting("Réunion J","Yoshi","2021-03-06", "14H00","test1@gmail.Com"),
            new Meeting("Réunion K","Wario", LocalDate.now().toString(),"16H00","test5@gmail.Com")

    );

    static List<Meeting> generateMeetings()
    {
  //      Log.i("THOMAS","[FakeGenerator] Récupération liste réunion(s) de base !" + FakeMeeting.size());
        return  new ArrayList<Meeting>(FakeMeeting);
    }




}
