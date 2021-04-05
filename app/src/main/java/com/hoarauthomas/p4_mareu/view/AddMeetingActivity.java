package com.hoarauthomas.p4_mareu.view;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.hoarauthomas.p4_mareu.R;
import com.hoarauthomas.p4_mareu.api.MeetingApiService;
import com.hoarauthomas.p4_mareu.databinding.ActivityAddMeetingBinding;
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

    private Calendar calendar;
    public Date datePickerToDate;
    private DatePickerDialog mDatePicker;
    private TimePickerDialog mTimePicker;

    MeetingApiService service;

    //Added for use View Binding
    private ActivityAddMeetingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //enable view binding
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        service = new DI().getMeetingApiService();

        //setup ...
        setupDatePicker();
        setupTimePicker();
        setupClickDate();
        setupClickTime();

        //get data for rooms
        setupDataRooms();
        //get data for collaborators, emails
        setupDataParticipants();
        setupClickValidate();

        //close activity if button ANNULER is clicked
        binding.btnCancel.setOnClickListener(v -> finish());
    }

    private void setupClickValidate() {
        binding.btnValid.setOnClickListener(v -> {
            setupBtnValidate();
        });
    }

    private void setupBtnValidate() {
        String participant = "";

        //this routine add a ; between meeting object
        for (int i = 0; i < binding.groupParticipants.getChildCount(); i++) {
            Chip chip = (Chip) binding.groupParticipants.getChildAt(i);

            if (participant.length() != 0) {
                participant += ";" + chip.getText();
            } else {
                participant += chip.getText();
            }
        }
        //alert message for empty fileds ...
        if (binding.subjectText.getEditableText().toString().isEmpty()) {
            binding.subjectText.setError("Sujet requis");
        } else {
            binding.subjectText.setError(null);
        }

        if (binding.tietStartDate.getText().toString().isEmpty()) {
            binding.tietStartDate.setError("Date requise");
        } else {
            binding.tietStartDate.setError(null);
        }

        if (binding.listLocation.getText().toString().isEmpty()) {
            binding.listLocation.setError("Choisir une salle");
        } else {
            binding.listLocation.setError(null);
        }

        if (binding.groupParticipants.getChildCount() == 0) {
            binding.listParticipants.setError("Choisir les participants");
        } else {
            binding.listParticipants.setError(null);
        }

        if (binding.tietStartTime.getEditableText().toString().isEmpty()) {
            binding.tietStartTime.setError("choisir l'heure de début");
        } else {
            binding.tietStartTime.setError(null);
        }
        //check fields ...
        //if (mEditSubject.getEditableText().toString().isEmpty() || mRooms.getText().toString().isEmpty() || mEditDate.getText().toString().isEmpty() || mEditTime.getEditableText().toString().isEmpty() || mChipGroup.getChildCount() <= 0) {
        if (binding.subjectText.getEditableText().toString().isEmpty()
                || binding.listLocation.getText().toString().isEmpty()
                || binding.tietStartDate.getText().toString().isEmpty()
                || binding.tietStartTime.getEditableText().toString().isEmpty()
                || binding.groupParticipants.getChildCount() <= 0) {
            //Nothing to do


        } else {
            try {
                datePickerToDate = new SimpleDateFormat("yyyy-MM-dd").parse(binding.tietStartDate.getText().toString());
                Log.i("THOMAS", "Date : " + datePickerToDate.toString());
            } catch (Exception e) {
                Log.i("THOMAS", "erreur : ");
            }

            Meeting meeting = new Meeting(binding.subjectText.getEditableText().toString(), binding.listLocation.getText().toString(), datePickerToDate, binding.tietStartTime.getText().toString(), participant);
            Intent intent = new Intent();
            intent.putExtra(MEETING_KEY, meeting);
            setResult(1, intent);
            //close activity
            finish();
        }
    }

    //add a chip to the mChipGroup
    private void addNewChipParticipant(String name) {
        LayoutInflater inflater = LayoutInflater.from(this);
        Chip newChip = (Chip) inflater.inflate(R.layout.chip_participant, this.binding.groupParticipants, false);
        newChip.setText(name);
        this.binding.groupParticipants.addView(newChip);
        newChip.setOnCloseIconClickListener(v -> handleChipCloseIconClicked((Chip) v));
    }

    private void handleChipCloseIconClicked(Chip chip) {
        binding.groupParticipants.removeView(chip);
    }

    private void setupClickDate() {
        binding.tietStartDate.setOnClickListener(v -> mDatePicker.show());
    }

    private void setupClickTime() {
        binding.tietStartTime.setOnClickListener(v -> mTimePicker.show());
    }

    private void setupDatePicker() {
        calendar = Calendar.getInstance();
        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mDatePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> binding.tietStartDate.setText(LocalDate.of(year, (month + 1), dayOfMonth).toString()), mYear, mMonth, mDay);
    }

    private void setupTimePicker() {
        int mHour = java.time.LocalTime.now().getHour();
        int mMinutes = java.time.LocalTime.now().getMinute();
        mTimePicker = new TimePickerDialog(this, (view, hourOfDay, minute) -> binding.tietStartTime.setText(hourOfDay + ":" + minute), mHour, mMinutes, true);
    }

    private void setupDataRooms() {
        //get the rooms list from API
        List<String> rooms = new ArrayList<>();

        for (MeetingRoom item : service.getMeetingsRooms()) {
            rooms.add(item.getmRoomName());
        }
        //sort data in ascending order
        Collections.sort(rooms);
        //push data to adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rooms);
        binding.listLocation.setAdapter(adapter);
    }

    private void setupDataParticipants() {
        //get the emails list from API
        List<String> emails = new ArrayList<>();

        for (Collaborator item : service.getCollaborators()) {
            emails.add(item.getmEmail());
        }
        //sort data in aszcending order
        Collections.sort(emails);
        //push data to the adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emails);
        binding.listParticipants.setAdapter(adapter);
        //add a listener on items
        binding.listParticipants.setOnItemClickListener((parent, view, position, id) -> {
            if (binding.groupParticipants.getChildCount() < 10) {
                addNewChipParticipant(binding.listParticipants.getText().toString());
            } else {
                Toast toast = Toast.makeText(this, "Salle complète !", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}