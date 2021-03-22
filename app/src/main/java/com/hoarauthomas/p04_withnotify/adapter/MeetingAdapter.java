package com.hoarauthomas.p04_withnotify.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.hoarauthomas.p04_withnotify.R;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.WordViewHolder>  {

    private List<Meeting> mListMeeting;
    private List<Meeting> mListMeetingAll;
    private final LayoutInflater mInflater;

    public MeetingAdapter(Context context, List<Meeting> mListMeeting2) {
        mInflater = LayoutInflater.from(context);
        this.mListMeeting = mListMeeting2;
        this.mListMeetingAll = mListMeeting2;
    }

    @NonNull
    @Override
    public MeetingAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.custom_meeting_layout, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingAdapter.WordViewHolder holder, int position) {

        Meeting mCurrent = mListMeeting.get(position);

        try {
            Field field = R.color.class.getDeclaredField(mCurrent.getmPosition());
            field.setAccessible(true);
            int idField = field.getInt(null);
            holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), idField));
        } catch (Exception e) {
            throw new Resources.NotFoundException(e.getMessage());
        }

        holder.mNameView.setText(mCurrent.getmSubject() + " - ");
        holder.mStartTime.setText(mCurrent.getmStartTime() + " - ");
        holder.mPlace.setText(mCurrent.getmPosition());
        holder.mParticipants.setText(mCurrent.getmParticipants());
        holder.mDeleteImage.setOnClickListener(v -> {
            mListMeeting.remove(position);
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {return mListMeeting.size();}










    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView wordItemView;
        final MeetingAdapter mAdapter;
        public ImageView mAvatar;
        public TextView mNameView;
        public TextView mStartTime;
        public TextView mPlace;
        public TextView mParticipants;
        public ImageView mDeleteImage;

        public WordViewHolder(@NonNull View itemView, MeetingAdapter adapter) {
            super(itemView);

            wordItemView = itemView.findViewById(R.id.view_name_meeting);
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
            Log.i("THOMAS", "Position item cliqué : " + Integer.valueOf(mPosition));
        }
    }
}
