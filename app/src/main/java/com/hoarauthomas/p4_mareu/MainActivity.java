package com.hoarauthomas.p4_mareu;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoarauthomas.p4_mareu.adapter.MeetingAdapter;
import com.hoarauthomas.p4_mareu.model.Meeting;
import com.hoarauthomas.p4_mareu.view.AddMeetingActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    public RecyclerView myRecyclerView;
    private MeetingAdapter myAdapter;
    public FloatingActionButton myFab;
    private String datefilter;
    public List<Meeting> meetingsList = new ArrayList<>();
    private List<Meeting> spareMeetingList = new ArrayList<>();
    DatePickerDialog mDatePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setupRecyclerView();
        setupFabOpenAddActivity();
        checkEmptyData();

        LocalTime time = LocalTime.now();
        Log.i("THOMAS", "Time : " + time);

    }

    public void checkEmptyData() {
        if (meetingsList.isEmpty()) {
            myRecyclerView.setVisibility(View.GONE);
            TextView myTextEmpty = findViewById(R.id.txt_empty);
            myTextEmpty.setVisibility(View.VISIBLE);

        } else {
            myRecyclerView.setVisibility(View.VISIBLE);
            TextView myTextEmpty = findViewById(R.id.txt_empty);
            myTextEmpty.setVisibility(View.GONE);

        }
    }

    public void setupRecyclerView() {
        myAdapter = new MeetingAdapter(this, meetingsList);
        myRecyclerView = findViewById(R.id.recyclerview);
        myRecyclerView.setAdapter(myAdapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupFabOpenAddActivity() {
        myFab = findViewById(R.id.add_fab_btn);
        myFab.setOnClickListener(v -> {
            Intent OpenAddMeetingActivity = new Intent(getApplicationContext(), AddMeetingActivity.class);
            startActivityForResult(OpenAddMeetingActivity, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null) {
            Meeting meeting = data.getParcelableExtra(AddMeetingActivity.MEETING_KEY);
            meetingsList.add(meeting);
            myAdapter.notifyDataSetChanged();
            checkEmptyData();
        }
    }

    //Setup menu Searchview and filter by date
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menumain, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Meeting> filteredList = new ArrayList<>();

                if (newText == null || newText.length() == 0 || newText.isEmpty()) {
                    filteredList.addAll(meetingsList);
                    myAdapter = new MeetingAdapter(MainActivity.this, meetingsList);
                } else {

                    String filterPattern = newText.toLowerCase().trim();
                    for (Meeting item : meetingsList) {
                        if (item.getmRoom().toLowerCase().contains(filterPattern)) {
                            Log.i("THOMAS", "retour" + item.getmRoom().toString());
                            filteredList.add(item);
                        }
                    }
                    myAdapter = new MeetingAdapter(MainActivity.this, filteredList);
                }

                myRecyclerView.setAdapter(myAdapter);
                myRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }

        });

        MenuItem menuDate = menu.findItem(R.id.menu_date);

        menuDate.setOnMenuItemClickListener(item -> {
            setupDatePicker();
            return false;
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void setupDatePicker() {

        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        mDatePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {

            datefilter = LocalDate.of(year, (month + 1), dayOfMonth).toString();

            List<Meeting> filteredList = new ArrayList<Meeting>();

            if (datefilter == null || datefilter.length() == 0) {
                myAdapter = new MeetingAdapter(MainActivity.this, meetingsList);
            } else {

                for (Meeting meeting : meetingsList) {

                    year = mDatePicker.getDatePicker().getYear();
                    month = (mDatePicker.getDatePicker().getMonth());
                    dayOfMonth = mDatePicker.getDatePicker().getDayOfMonth();

                    Calendar mycalendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    mycalendar.set(year, month, dayOfMonth);

                    String dateStr = sdf.format(mycalendar.getTime());
                    //TODO: pb avec test instrumentalisé car

                    String date2Str = mycalendar.getTime().toString();

                    try {
                        date2Str = sdf.format(meeting.getmDate());
                    }
                    catch (Exception e)
                    {
                        date2Str = dateStr;
                    }


                    if (dateStr.equals(date2Str)) {
                        filteredList.add(meeting);
                    }
                }

                myAdapter = new MeetingAdapter(MainActivity.this, filteredList);
            }

            myRecyclerView.setAdapter(myAdapter);

        }, mYear, mMonth, mDay);

        mDatePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "Annuler", (dialog, which) -> {
            myAdapter = new MeetingAdapter(MainActivity.this, meetingsList);
            myRecyclerView.setAdapter(myAdapter);
        });
        mDatePicker.show();
    }

}