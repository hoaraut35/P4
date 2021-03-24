package com.hoarauthomas.p4_mareu.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.widget.Toolbar;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.hoarauthomas.p4_mareu.R;
import com.hoarauthomas.p4_mareu.api.MeetingApiService;
import com.hoarauthomas.p4_mareu.di.DI;
import com.hoarauthomas.p4_mareu.model.Collaborator;
import com.hoarauthomas.p4_mareu.model.Meeting;
import com.hoarauthomas.p4_mareu.model.MeetingRoom;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity {

    public static final String MEETING_KEY = "MEETING_KEY";

    private DatePickerDialog mDatePicker;
    private TimePickerDialog mTimePicker;
    private ChipGroup mChipGroup;
    private Button mBtnValidate, mBtnCancel;
    private EditText mEditDate;
    private TextInputEditText mEditTime, mEditSubject;
    private AutoCompleteTextView mRooms, mEmails;

    private Calendar calendar;
    public Date datePickerToDate;

    MeetingApiService service;

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
        mBtnCancel = findViewById(R.id.btn_cancel);
        mEditSubject = findViewById(R.id.subject_text);

        service = DI.getMeetingApiService();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(v -> finish());

        setupDatePicker();
        setupTimePicker();
        setupClickDate();
        setupClickTime();
        setupDataRooms();
        setupDataParticipants();
        setupClickValidate();

        mBtnCancel.setOnClickListener(v -> finish());
    }

    private void setupClickValidate() {
        mBtnValidate.setOnClickListener(v -> {
            setupBtnValidate();
        });
    }

    private void setupBtnValidate() {
        String participant = "";

        for (int i = 0; i < mChipGroup.getChildCount(); i++) {
            Chip chip = (Chip) mChipGroup.getChildAt(i);

            if (participant.length() != 0) {
                participant += ";" + chip.getText();
            } else {
                participant += chip.getText();
            }
        }

        if (mEditSubject.getEditableText().toString().isEmpty())
        {
            mEditSubject.setError("Sujet requis");
        }else
        {
            mEditSubject.setError(null);
        }

        if (mEditDate.getText().toString().isEmpty())
        {
            mEditDate.setError("Date requise");
        }else
        {
            mEditDate.setError(null);
        }

        if (mRooms.getText().toString().isEmpty())
        {
            mRooms.setError("Choisir une salle");
        }else
        {
            mRooms.setError(null);
        }

        if (mChipGroup.getChildCount() ==0)
        {
            mEmails.setError("Choisir les participants");
        }else
        {
            mEmails.setError(null);
        }

        if (mEditTime.getEditableText().toString().isEmpty())
        {
            mEditTime.setError("choisir l'heure de début");
        }else
        {
            mEditTime.setError(null);
        }

        if(mEditSubject.getEditableText().toString().isEmpty() || mRooms.getText().toString().isEmpty() || mEditDate.getText().toString().isEmpty() || mEditTime.getEditableText().toString().isEmpty() || mChipGroup.getChildCount() <= 0) {
            //Nothing
        }
        else
        {


            //TODO: modif pour api 21
            try {
                datePickerToDate = new SimpleDateFormat("yyyy-MM-dd").parse(mEditDate.getText().toString());
                Log.i("THOMAS","Date : " + datePickerToDate.toString());
            }catch (Exception e)
            {
                Log.i("THOMAS","erreur : " );
            }

//            Log.i("THOMAS","Date : " + datePickerToDate.toString());
            //Meeting meeting = new Meeting(mEditSubject.getEditableText().toString(), mRooms.getText().toString(), convertToDateViaInstant(LocalDate.parse(mEditDate.getText().toString())), mEditTime.getText().toString(), participant);
            Meeting meeting = new Meeting(mEditSubject.getEditableText().toString(), mRooms.getText().toString(), datePickerToDate, mEditTime.getText().toString(), participant);
            Intent intent = new Intent();
            intent.putExtra(MEETING_KEY, meeting);
            setResult(1, intent);
            finish();
        }
    }

   /* @RequiresApi(api = Build.VERSION_CODES.O)
    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    */

    private void addNewChipParticipant(String name) {
        LayoutInflater inflater = LayoutInflater.from(this);
        Chip newChip = (Chip) inflater.inflate(R.layout.chip_participant, this.mChipGroup, false);
        newChip.setText(name);
        this.mChipGroup.addView(newChip);
        newChip.setOnCloseIconClickListener(v -> handleChipCloseIconClicked((Chip) v));
    }

    private void handleChipCloseIconClicked(Chip chip) {
        mChipGroup.removeView(chip);
    }

    private void setupClickDate() {
        mEditDate.setOnClickListener(v -> mDatePicker.show());
    }

    private void setupClickTime() {
        mEditTime.setOnClickListener(v -> mTimePicker.show());
    }


    private void setupDatePicker() {

        calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mEditDate.setText(LocalDate.of(year, (month + 1), dayOfMonth).toString());


            }

        }, mYear, mMonth, mDay);


    }

    private void setupTimePicker() {
        int mHour = java.time.LocalTime.now().getHour();
        int mMinutes = java.time.LocalTime.now().getMinute();

        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                mEditTime.setText(hourOfDay + ":" + minute);

            }
        }, mHour, mMinutes, false);
    }

    private void setupDataRooms() {
        List<String> rooms = new ArrayList<>();

        for (MeetingRoom item : service.getMeetingsRooms()) {
            rooms.add(item.getmRoomName());
        }

        Collections.sort(rooms);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rooms);
        mRooms.setAdapter(adapter);
    }

    private void setupDataParticipants() {
        List<String> emails = new ArrayList<>();

        for (Collaborator item : service.getCollaborators()) {
            emails.add(item.getmEmail());
        }

        Collections.sort(emails);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emails);
        mEmails.setAdapter(adapter);


        mEmails.setOnItemClickListener((parent, view, position, id) -> {

            //TODO: check if already exist in the list

            if (mChipGroup.getChildCount() < 10)
            {
                addNewChipParticipant(mEmails.getText().toString());
            }
        });
    }

}