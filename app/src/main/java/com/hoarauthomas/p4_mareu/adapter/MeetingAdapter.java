package com.hoarauthomas.p4_mareu.adapter;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hoarauthomas.p4_mareu.R;
import com.hoarauthomas.p4_mareu.model.Meeting;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.WordViewHolder> {

    private List<Meeting> mListMeeting;
    private final LayoutInflater mInflater;

    //constructor
    public MeetingAdapter(Context context, List<Meeting> mListMeeting2) {
        mInflater = LayoutInflater.from(context);
        this.mListMeeting = mListMeeting2;
    }


    //override onCreateViewsHolder to create a holder
    @NonNull
    @Override
    public MeetingAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.custom_meeting_layout, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    //override onBindViewHolder to bind data to the holder
    @Override
    public void onBindViewHolder(@NonNull MeetingAdapter.WordViewHolder holder, int position) {

        Meeting mCurrent = mListMeeting.get(position);

        //get the color from mCurrent meeting object
        switch (mCurrent.getmRoom()) {
            case "Luigi":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.Luigi));
                break;
            case "Peach":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.mAvatar.getContext(), R.color.Peach));
                break;
            case "Toad":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.Toad));
                break;
            case "Yoshi":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.Yoshi));
                break;
            case "Bowser":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.Bowser));
                break;
            case "Wario":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.Wario));
                break;
            case "Waluigi":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.Waluigi));
                break;
            case "Bidosaure":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.Bidosaure));
                break;
            case "Bero":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.Bero));
                break;
            case "Mario":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.Mario));
                break;
            default:
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        }

        //get others data from mCurrent meeting object ...
        holder.mNameView.setText(mCurrent.getmSubject() + " - ");
        holder.mStartTime.setText(mCurrent.getmStartTime() + " - ");
        holder.mPlace.setText(mCurrent.getmRoom());
        holder.mParticipants.setText(mCurrent.getmParticipants());
        //add a listener for delete a meeting if the user click a trash
        holder.mDeleteImage.setOnClickListener(v -> {
            mListMeeting.remove(position);
            notifyDataSetChanged();
            Toast toast = Toast.makeText(holder.mDeleteImage.getContext(), "Réunion supprimée", Toast.LENGTH_SHORT);
            toast.show();
        });
    }

    @Override
    public int getItemCount() {
        return mListMeeting.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //binding
        final MeetingAdapter mAdapter;
        public ImageView mAvatar;
        public TextView mNameView;
        public TextView mStartTime;
        public TextView mPlace;
        public TextView mParticipants;
        public ImageView mDeleteImage;

        public WordViewHolder(@NonNull View itemView, MeetingAdapter adapter) {
            super(itemView);

            mNameView = itemView.findViewById(R.id.view_name_meeting);
            mStartTime = itemView.findViewById(R.id.view_start_time_meeting);
            mAvatar = itemView.findViewById(R.id.view_image_meeting);
            mPlace = itemView.findViewById(R.id.view_position_meeting);
            mParticipants = itemView.findViewById(R.id.view_email_meeting);
            mDeleteImage = itemView.findViewById(R.id.view_delete_meeting);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int mPosition = getLayoutPosition();
        }
    }
}
