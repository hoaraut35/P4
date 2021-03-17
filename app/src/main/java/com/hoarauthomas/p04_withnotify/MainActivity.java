package com.hoarauthomas.p04_withnotify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.Filter;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoarauthomas.p04_withnotify.adapter.MeetingAdapter;
import com.hoarauthomas.p04_withnotify.api.MeetingApiService;
import com.hoarauthomas.p04_withnotify.di.DI;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.view.AddMeetingActivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mWordList = new LinkedList<>();

    public RecyclerView mRecyclerView;
    public MeetingAdapter mAdapter;
    public FloatingActionButton mFloatingBtn, mFloatingBtn2;
    private int mYear, mMonth, mDay;
    public MeetingApiService service;
    private DatePickerDialog mDatePicker;

    private List<Meeting> spareMeetingList = new ArrayList<>();

    private String datefilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFab1();
        setupFab2();
        setupData();
        setupRecyclerView();


    }

    //TODO: donné de base impossible
    @Override
    protected void onResume() {
        super.onPostResume();
        //setupData();
        //mRecyclerView.getAdapter()..notifyDataSetChanged();
    }

    public void setupData() {
        service = DI.getMeetingApiService();
    }

    public void setupRecyclerView() {
        mAdapter = new MeetingAdapter(this, service.getMeetings());
        mRecyclerView = findViewById(R.id.recyclerview);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public void setupFab1() {

        mFloatingBtn = findViewById(R.id.floatingbtn);

        //mFloatingBtn.hide();

        mFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wordListSize = mWordList.size();

                service.getMeetings().add(new Meeting("test", "test", "", "", "test"));
                //  mAdapter.notifyDataSetChanged();
                mRecyclerView.getAdapter().notifyDataSetChanged();
                //mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                //mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });

    }

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

        //back from add meeting activity
        if (requestCode == 1) {
            String message = data.getStringExtra("MESSAGE");
            Log.i("THOMAS", "retour activit éadd meetingf" + message + " liste " + service.getMeetings().size());
            // mAdapter.notifyItemInserted(service.getMeetings().size() - 1);
            // mRecyclerView.getAdapter().notifyItemInserted(service.getMeetings().size() - 1);
            mRecyclerView.getAdapter().notifyDataSetChanged();

        }
    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){


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


                    Log.i("THOMAS","Taille adapter : " + mAdapter.getItemCount());

                    List<Meeting> filteredList = new ArrayList<Meeting>();

                    if (newText == null || newText.length() == 0 || newText.isEmpty() )
                    {
                        mAdapter = new MeetingAdapter(getApplicationContext(), service.getMeetings());


                    }
                    else
                    {
                        String filterPattern = newText.toString().toLowerCase().trim();

                        for (Meeting item : service.getMeetings())
                        {
                            if (item.getmPosition().toLowerCase().contains(filterPattern))
                            {
                                Log.i("THOMAS","retour"+  item.getmPosition().toString());
                                filteredList.add(item);

                            }
                            //          mAdapter = new MeetingAdapter(getApplicationContext(), filteredList);
                            //mAdapter.notifyDataSetChanged();

                        }


                        mAdapter = new MeetingAdapter(getApplicationContext(), filteredList);

                    }

                    mRecyclerView.setAdapter(mAdapter);

                    return false;
                }

            });


            MenuItem menuDate = menu.findItem(R.id.menu_date);

            menuDate.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    Log.i("THOMAS", "menu date cliqué");

                    setupDatePicker();


                    return false;
                }
            });

            return true;
        }



    private List<Meeting> filter(List<Meeting> models, String query) {
        query = query.toLowerCase();

        final List<Meeting> filteredModelList = new ArrayList<>();

        for (Meeting model : models) {
            final String text = model.getmPosition().toLowerCase();
            final String textp = model.getmPosition().toLowerCase();

            if (text.contains(query)) {
                filteredModelList.add(model);
            }

        }
        return filteredModelList;
    }


    private void setupDatePicker() {


        //mEditDate.setEnabled(false);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //viewBinding.viewEditDate2.setText(dayOfMonth+"/"+month +"/" + year);
                //mEditDate.setText(LocalDate.of(year,(month+1),dayOfMonth).toString());
                datefilter = LocalDate.of(year, (month + 1), dayOfMonth).toString();

                List<Meeting> filteredList = new ArrayList<Meeting>();

                if (datefilter == null || datefilter.length() == 0 || datefilter.isEmpty()) {
                    Log.i("THOMAS", "Taille adapter : " + datefilter);

                    mAdapter = new MeetingAdapter(getApplicationContext(), service.getMeetings());

                } else {
                    String filterPattern = datefilter.toString().toLowerCase().trim();

                    for (Meeting item2 : service.getMeetings()) {
                        if (item2.getmDate().toLowerCase().contains(filterPattern)) {
                            Log.i("THOMAS", "retour" + item2.getmPosition().toString());
                            filteredList.add(item2);
                        }
                    }


                    mAdapter = new MeetingAdapter(getApplicationContext(), filteredList);
                }


                mRecyclerView.setAdapter(mAdapter);


            }

        }, mYear, mMonth, mDay);


        mDatePicker.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("THOMAS", "test cancel");
                mAdapter = new MeetingAdapter(getApplicationContext(), service.getMeetings());
                mRecyclerView.setAdapter(mAdapter);

            }
        });

        mDatePicker.show();


    }


}