package com.hoarauthomas.p04_withnotify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>
{
    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    public WordListAdapter(Context context, LinkedList<String> mWordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = mWordList;
    }

    @NonNull
    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View mItemView = mInflater.inflate(R.layout.custom_meeting_layout, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull WordListAdapter.WordViewHolder holder, int position)
    {
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }




    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView wordItemView;
        final WordListAdapter mAdapter;

        public WordViewHolder(@NonNull View itemView, WordListAdapter adapter)
        {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.view_name_meeting);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int mPosition = getLayoutPosition();

            String element = mWordList.get(mPosition);

            mWordList.set(mPosition, "Clicked! " + element);
            mAdapter.notifyDataSetChanged();
        }
    }
}
