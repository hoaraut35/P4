package com.hoarauthomas.p04_withnotify.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hoarauthomas.p04_withnotify.R;
import com.hoarauthomas.p04_withnotify.model.Meeting;

import java.util.LinkedList;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder>
{
    private final List<Meeting> mListMeeting;
    private LayoutInflater mInflater;

    public WordListAdapter(Context context, List<Meeting> mListMeeting) {
        mInflater = LayoutInflater.from(context);
        this.mListMeeting = mListMeeting;
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
        Meeting mCurrent = mListMeeting.get(position);
        holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col5));

        switch (mCurrent.getmPosition().toString())
        {
            case "Peach":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col1));
                break;
            case "Toad":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col2));
                break;
            case "Yoshi":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col3));
                break;
            case "Bowser":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col4));
                break;
            case "Wario":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col5));
                break;
            case "Waluigi":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col6));
                break;
            case "Bidosaure":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col7));
                break;
            case "Bero":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col8));
                break;
            case "Mario":
                Log.i("COLOR","couleur col9");
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col9));
                break;
            case "Xmen":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col10));
                break;
            default:
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(),R.color.col1));

        }


    }

    @Override
    public int getItemCount() {
        return mListMeeting.size();
    }




    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView wordItemView;
        final WordListAdapter mAdapter;
        public ImageView mAvatar;
        public TextView mNameView;
        public TextView mStartTime;
        public TextView mPlace;
        public TextView mParticipants;
        public ImageView mDeleteImage;

        public WordViewHolder(@NonNull View itemView, WordListAdapter adapter)
        {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.view_name_meeting);
            mNameView = itemView.findViewById(R.id.view_name_meeting);
            mStartTime = itemView.findViewById(R.id.view_start_time_meeting);
            mAvatar =itemView.findViewById(R.id.view_image_meeting);
            mPlace =itemView.findViewById(R.id.view_position_meeting);
            mParticipants = itemView.findViewById(R.id.view_email_meeting);
            mDeleteImage =itemView.findViewById(R.id.view_delete_meeting);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int mPosition = getLayoutPosition();

            Meeting element = mListMeeting.get(mPosition);

            //mListMeeting.addAll(mPosition,)
           // mListMeetings.set(mPosition, "Clicked! " + element);
            mAdapter.notifyDataSetChanged();
        }
    }
}
