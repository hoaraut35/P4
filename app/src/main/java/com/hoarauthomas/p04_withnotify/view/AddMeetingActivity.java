package com.hoarauthomas.p04_withnotify.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.hoarauthomas.p04_withnotify.R;
import com.hoarauthomas.p04_withnotify.api.MeetingApiService;
import com.hoarauthomas.p04_withnotify.di.DI;
import com.hoarauthomas.p04_withnotify.model.Collaborator;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.model.MeetingRoom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {

    //For DatePickerDialog
    private DatePickerDialog mDatePicker;
    private TimePickerDialog mTimePicker;
    private int mYear, mMonth, mDay, mHour, mMinutes;
    private ChipGroup mChipGroup;

    MeetingApiService service;

    Button mBtnValidate;
    TextInputEditText mEditDate,mEditTime, mEditSubject;
    AutoCompleteTextView mRooms, mEmails;


    private Listener callback;
    public interface Listener
    {
        void onAddMeeting(int position);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        mEditDate = findViewById(R.id.tiet_start_date);
        mEditTime = findViewById(R.id.tiet_start_time);
        mRooms = findViewById(R.id.list_location);
        mEmails = findViewById(R.id.list_participants);
        mChipGroup = findViewById(R.id.group_participants);
        mBtnValidate = findViewById(R.id.btn_valid);
        mEditSubject = findViewById(R.id.subject_text);

        service = DI.getMeetingApiService();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupDatePicker();
        setupTimePicker();
        setupClickDate();
        setupClickTime();
        setupDataRooms();
        setupDataParticipants();

        mBtnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setupBtnValidate();
                finish();
            }
        });
    }

    private void setupBtnValidate()
    {
        String participant ="";

        for (int i=0;i < mChipGroup.getChildCount(); i++)
        {
            Chip chip = (Chip)mChipGroup.getChildAt(i);

            if (participant.length() != 0 )
            {
                participant += ";" + chip.getText();
            }
            else
            {
                participant += chip.getText();
            }

        }

        service.getMeetings().add(new Meeting(mEditSubject.getEditableText().toString(), mRooms.getText().toString(),mEditDate.getText().toString(),mEditTime.getText().toString(), participant));
        Log.i("THOMAS", "Taille liste api " + service.getMeetings().size());
        String message = "reponse de la 2";
        Intent intent = new Intent();
        intent.putExtra("MESSAGE", message);
        setResult(1,intent);
        finish();
    }

    //**********************************************************************************************
    private void addNewChipParticipant(String name)
    {
        LayoutInflater inflater = LayoutInflater.from(this);

        Chip newChip = (Chip)inflater.inflate(R.layout.chip_participant,this.mChipGroup,false);
        newChip.setText(name);

        this.mChipGroup.addView(newChip);

        newChip.setOnCloseIconClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                handleChipCloseIconClicked((Chip) v);
            }
        });
    }




    private void handleChipCloseIconClicked(Chip chip)
    {
        mChipGroup.removeView(chip);
    }

    private void handleChipCheckChanged(Chip chip, boolean isChecked)   {    }

    //**********************************************************************************************

    private void setupClickDate()
    {
         mEditDate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 mDatePicker.show();
             }
         });
    }

    private void setupClickTime()
    {
        mEditTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimePicker.show();
            }
        });
    }



    private void setupDatePicker()
    {


        //mEditDate.setEnabled(false);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
            {
                //viewBinding.viewEditDate2.setText(dayOfMonth+"/"+month +"/" + year);
                mEditDate.setText(LocalDate.of(year,(month+1),dayOfMonth).toString());
            }

        },mYear, mMonth, mDay     );
    }

    private void setupTimePicker()
    {
        //mEditDate.setEnabled(false);

        mHour = java.time.LocalTime.now().getHour();
        mMinutes = java.time.LocalTime.now().getMinute();

        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                mEditTime.setText(hourOfDay + ":" + minute);
                //viewBinding.viewEditTimeend.setText(hourOfDay+":"+minute);
            }
        }, mHour, mMinutes, false);
    }

    private void setupDataRooms()
    {
        List<String> rooms = new ArrayList<>();

        for (MeetingRoom item: service.getMeetingsRooms())
        {
            rooms.add(item.getmRoomName().toString());
        }

        Collections.sort(rooms);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rooms);
        mRooms.setAdapter(adapter);
    }

    private void setupDataParticipants()
    {
        List<String> emails = new ArrayList<>();

        for (Collaborator item: service.getCollaborators())
        {
            emails.add(item.getmEmail().toString());
        }

        Collections.sort(emails);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emails);
        mEmails.setAdapter(adapter);


        mEmails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //TODO: check if already exist in the list
                addNewChipParticipant(mEmails.getText().toString());
            }
        });

       /* mEmails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.i("THOMAS","onclick ");
                service.addMeeting(new Meeting("","","","",""));
               // finish();
            }
        });

        */




    }

}