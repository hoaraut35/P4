package com.hoarauthomas.p04_withnotify;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoarauthomas.p04_withnotify.adapter.MeetingAdapter;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.view.AddMeetingActivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public RecyclerView myRecyclerView;
    private MeetingAdapter myAdapter;
    public FloatingActionButton myFab;
    private String datefilter;
    private List<Meeting> meetingsList = new ArrayList<>();
    private List<Meeting> spareMeetingList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView();
        setupFabOpenAddActivity();
    }

    public void setupRecyclerView() {
        myAdapter = new MeetingAdapter(this, meetingsList);
        myRecyclerView = findViewById(R.id.recyclerview);
        myRecyclerView.setAdapter(myAdapter);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupFabOpenAddActivity() {
        myFab = findViewById(R.id.floatingbtn2);
        myFab.setOnClickListener(v -> {
            Intent OpenAddMeetingActivity = new Intent(getApplicationContext(), AddMeetingActivity.class);
            startActivityForResult(OpenAddMeetingActivity, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null) {
            myRecyclerView.getAdapter().notifyDataSetChanged();
            Meeting meeting = data.getParcelableExtra(AddMeetingActivity.MEETING_KEY);
            meetingsList.add(meeting);
            myAdapter.notifyDataSetChanged();
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

                List<Meeting> filteredList = new ArrayList<Meeting>();


                if (newText == null || newText.length() == 0 || newText.isEmpty()) {

                    filteredList.addAll(meetingsList);
                   // myAdapter = new MeetingAdapter(MainActivity.this, meetingsList);
                    //myRecyclerView.getAdapter().notifyDataSetChanged();

                } else {

                    String filterPattern = newText.toLowerCase().trim();

                    for (Meeting item : meetingsList) {

                        if (item.getmPosition().toLowerCase().contains(filterPattern)) {
                            Log.i("THOMAS", "retour" + item.getmPosition().toString());
                            filteredList.add(item);

                        }


                    }
                    //meetingsList.clear();

                }

                meetingsList.clear();
                meetingsList.addAll(filteredList);

                myAdapter.notifyDataSetChanged();
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

        DatePickerDialog mDatePicker = new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {

            datefilter = LocalDate.of(year, (month + 1), dayOfMonth).toString();

            List<Meeting> filteredList = new ArrayList<Meeting>();

            if (datefilter == null || datefilter.length() == 0 || datefilter.isEmpty()) {
                Log.i("THOMAS", "Taille adapter : " + datefilter);

                myAdapter = new MeetingAdapter(MainActivity.this, meetingsList);

            } else {
                String filterPattern = datefilter.toLowerCase().trim();

                for (Meeting item2 : meetingsList) {

                    Log.i("THOMAS", "Comparaison date => " + item2.getmDate() + " " + LocalDate.parse(filterPattern));
                    /*if (item2.getmDate().isEqual(LocalDate.parse(filterPattern))) {
                        Log.i("THOMAS", "retour" + item2.getmPosition().toString());
                        filteredList.add(item2);
                    }*/
                }

                myAdapter = new MeetingAdapter(MainActivity.this, filteredList);
            }

            myRecyclerView.setAdapter(myAdapter);

        }, mYear, mMonth, mDay);

        mDatePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel", (dialog, which) -> {
            Log.i("THOMAS", "test cancel");
            myAdapter = new MeetingAdapter(MainActivity.this, meetingsList);
            myRecyclerView.setAdapter(myAdapter);

        });

        mDatePicker.show();

    }


    private List<Meeting> filterByRoom(String query) {
        // Loop on meetingsList
        return null;
    }


}