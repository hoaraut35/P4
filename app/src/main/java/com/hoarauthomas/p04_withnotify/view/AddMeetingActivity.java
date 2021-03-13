package com.hoarauthomas.p04_withnotify.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.hoarauthomas.p04_withnotify.R;

import java.time.LocalDate;
import java.util.Calendar;

public class AddMeetingActivity extends AppCompatActivity {

    //For DatePickerDialog
    private DatePickerDialog mDatePicker;
    private TimePickerDialog mTimePicker;
    private int mYear, mMonth, mDay, mHour, mMinutes;

    TextInputEditText mEditDate,mEditTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        mEditDate = findViewById(R.id.tiet_start_date);
        mEditTime = findViewById(R.id.tiet_start_time);

        setupDatePicker();
        setupTimePicker();
        setupClickDate();

    }

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

    }



    private void setupDatePicker()
    {


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
}