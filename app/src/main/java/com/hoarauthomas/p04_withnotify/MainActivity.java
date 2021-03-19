package com.hoarauthomas.p04_withnotify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoarauthomas.p04_withnotify.adapter.MeetingAdapter;
import com.hoarauthomas.p04_withnotify.api.FakeGenerator;
import com.hoarauthomas.p04_withnotify.api.MeetingApiService;
import com.hoarauthomas.p04_withnotify.di.DI;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.view.AddMeetingActivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mRecyclerView;
    public MeetingAdapter mAdapter;
    public FloatingActionButton mFloatingBtn, mFloatingBtn2;
    private int mYear, mMonth, mDay;

    private DatePickerDialog mDatePicker;
    private List<Meeting> spareMeetingList = new ArrayList<>();
    private String datefilter;
    private List<Meeting> meetingsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView();
        setupFab2();
        //TODO: Fab for demo mode : add a random meeting
        setupFabForDemo(true);

    }

    private List<Meeting> filterByRoom(String query) {
        // Loop on meetingsList
        return null;
    }

    public void setupRecyclerView() {
        mAdapter = new MeetingAdapter(this, meetingsList);
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupFabForDemo(Boolean setup) {
        mFloatingBtn = findViewById(R.id.floatingbtn);
        mFloatingBtn.setVisibility(View.GONE);

        if (setup) {
            mFloatingBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //service.getMeetings().add(FakeGenerator.FakeMeeting.get(new Random().nextInt(FakeGenerator.FakeMeeting.size())));
                    mRecyclerView.getAdapter().notifyDataSetChanged();
                }
            });
        } else {
            mFloatingBtn.hide();
        }

    }

    //**********************************************************************************************

    public void setupFab2() {

        mFloatingBtn2 = findViewById(R.id.floatingbtn2);

        mFloatingBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent OpenAddMeetingActivity = new Intent(getApplicationContext(), AddMeetingActivity.class);
                startActivityForResult(OpenAddMeetingActivity, 1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null) {
            mRecyclerView.getAdapter().notifyDataSetChanged();
            Log.i("THOMAS","onActivityResult");
            Meeting meeting = data.getParcelableExtra(AddMeetingActivity.MEETING_KEY);
            meetingsList.add(meeting);
            mAdapter.notifyDataSetChanged();
        }
    }

    //**********************************************************************************************

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

                List<Meeting> filteredList = new ArrayList<Meeting>();

                if (newText == null || newText.length() == 0 || newText.isEmpty()) {
                    //mAdapter = new MeetingAdapter(MainActivity.this, service.getMeetings());
                 //   mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                } else {
                    String filterPattern = newText.toString().toLowerCase().trim();

                    /*for (Meeting item : service.getMeetings()) {
                        if (item.getmPosition().toLowerCase().contains(filterPattern)) {
                            Log.i("THOMAS", "retour" + item.getmPosition().toString());
                            filteredList.add(item);

                        }

                        //mAdapter = new MeetingAdapter(getApplicationContext(), filteredList);
                        //mAdapter.notifyDataSetChanged();
                    }*/
                    mAdapter = new MeetingAdapter(MainActivity.this, filteredList);
                }

                mRecyclerView.setAdapter(mAdapter);

                return false;
            }

        });


        MenuItem menuDate = menu.findItem(R.id.menu_date);

        menuDate.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                setupDatePicker();
                return false;
            }
        });

        return true;
    }

    private void setupDatePicker() {

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                datefilter = LocalDate.of(year, (month + 1), dayOfMonth).toString();

                List<Meeting> filteredList = new ArrayList<Meeting>();

                if (datefilter == null || datefilter.length() == 0 || datefilter.isEmpty()) {
                    Log.i("THOMAS", "Taille adapter : " + datefilter);

                    mAdapter = new MeetingAdapter(MainActivity.this, meetingsList);

                } else {
                    String filterPattern = datefilter.toLowerCase().trim();

                    for (Meeting item2 : meetingsList) {

                        Log.i("THOMAS","Comparaison date => "+ item2.getmDate() + " " +  LocalDate.parse(filterPattern));
                        /*if (item2.getmDate().isEqual(LocalDate.parse(filterPattern))) {
                            Log.i("THOMAS", "retour" + item2.getmPosition().toString());
                            filteredList.add(item2);
                        }*/
                    }

                    mAdapter = new MeetingAdapter(MainActivity.this, filteredList);
                }

                mRecyclerView.setAdapter(mAdapter);

            }

        }, mYear, mMonth, mDay);


        mDatePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("THOMAS", "test cancel");
                mAdapter = new MeetingAdapter(MainActivity.this, meetingsList);
                mRecyclerView.setAdapter(mAdapter);

            }
        });

        mDatePicker.show();


    }


}