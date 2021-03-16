package com.hoarauthomas.p04_withnotify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Filter;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoarauthomas.p04_withnotify.adapter.MeetingAdapter;
import com.hoarauthomas.p04_withnotify.api.MeetingApiService;
import com.hoarauthomas.p04_withnotify.di.DI;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.view.AddMeetingActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private final LinkedList<String> mWordList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private MeetingAdapter mAdapter;
    public FloatingActionButton mFloatingBtn, mFloatingBtn2;

    public MeetingApiService service;







    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        setupData();
        setupRecyclerView();
        setupFab1();
        setupFab2();




    }

    //TODO: donné de base impossible
    @Override
    protected void onResume() {
        super.onPostResume();
        //setupData();
        //mRecyclerView.getAdapter()..notifyDataSetChanged();
    }

    public void setupData()
    {
        service = DI.getMeetingApiService();
    }

    public void setupRecyclerView()
    {
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new MeetingAdapter(this, service.getMeetings());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }





    public void setupFab1()
    {

        mFloatingBtn = findViewById(R.id.floatingbtn);

        mFloatingBtn.hide();

     //   myFilter();
/*
        mFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int wordListSize = mWordList.size();
                // Add a new word to the wordList.

                service.getMeetings().add(new Meeting("test","test","","","test"));


                //mWordList.addLast("+ Word " + wordListSize);
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });
        */
        /*
        mFloatingBtn2 = findViewById(R.id.floatingbtn2);
        mFloatingBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wordListSize = mWordList.size();
                // Add a new word to the wordList.
                mWordList.remove(0);
                // Notify the adapter, that the data has changed.
                mRecyclerView.getAdapter().notifyItemRemoved(0);
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });*/

    }

    public void setupFab2()
    {
        mFloatingBtn2 = findViewById(R.id.floatingbtn2);

        mFloatingBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent OpenAddMeetingActivity = new Intent(getApplicationContext(), AddMeetingActivity.class);
                startActivityForResult(OpenAddMeetingActivity, 1);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        //back from add meeting activity
        if (requestCode==1)
        {
            String message = data.getStringExtra("MESSAGE");
            Log.i("THOMAS","retour activit éadd meetingf" + message + " liste " + service.getMeetings().size());
          // mAdapter.notifyItemInserted(service.getMeetings().size() - 1);
           // mRecyclerView.getAdapter().notifyItemInserted(service.getMeetings().size() - 1);
            mRecyclerView.getAdapter().notifyDataSetChanged();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menumain,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)searchItem.getActionView();
         searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query) { return false; }

            @Override
            public boolean onQueryTextChange(String newText)
            {

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






                // myFilter(newText, service.getMeetings());


                //mAdapter.getFilter().filter(newText);

                return false;
            }

        });

        return true;
    }



}