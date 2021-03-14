package com.hoarauthomas.p04_withnotify.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
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
import com.google.android.material.textfield.TextInputLayout;
import com.hoarauthomas.p04_withnotify.R;
import com.hoarauthomas.p04_withnotify.api.MeetingApiService;
import com.hoarauthomas.p04_withnotify.di.DI;
import com.hoarauthomas.p04_withnotify.model.Collaborator;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.model.MeetingRoom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
    //TextInputLayout mRooms;
    AutoCompleteTextView mRooms, mEmails;

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
     //   setupBtnValidate();

    }

    private void setupBtnValidate()
    {
//        service.addMeeting(new Meeting("Réuion Z","","","",""));
        service.getMeetings().add(new Meeting("test","test","","","test"));

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

    private void handleChipCheckChanged(Chip chip, boolean isChecked)
    {

    }

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

        mEmails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                service.addMeeting(new Meeting("","","","",""));
                finish();
            }
        });


    }

}