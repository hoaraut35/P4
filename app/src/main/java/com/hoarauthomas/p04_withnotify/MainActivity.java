package com.hoarauthomas.p04_withnotify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hoarauthomas.p04_withnotify.adapter.WordListAdapter;
import com.hoarauthomas.p04_withnotify.api.MeetingApiService;
import com.hoarauthomas.p04_withnotify.di.DI;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
{
    private final LinkedList<String> mWordList = new LinkedList<>();

    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private FloatingActionButton mFloatingBtn, mFloatingBtn2;

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

    public void setupData()
    {
        service = DI.getMeetingApiService();

        for (int i = 0 ; i<20; i++)
        {
            mWordList.addLast("Word " + i);
        }
    }

    public void setupRecyclerView()
    {
        mRecyclerView = findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, service.getMeetings());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void setupFab1()
    {
        mFloatingBtn = findViewById(R.id.floatingbtn);
        mFloatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int wordListSize = mWordList.size();
                // Add a new word to the wordList.
                mWordList.addLast("+ Word " + wordListSize);
                // Notify the adapter, that the data has changed.
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });
    }

    public void setupFab2()
    {
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
        });

    }

}