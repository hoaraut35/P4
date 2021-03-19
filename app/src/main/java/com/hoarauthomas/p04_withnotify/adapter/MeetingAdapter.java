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
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hoarauthomas.p04_withnotify.R;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.view.AddMeetingActivity;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.WordViewHolder>  {

    private List<Meeting> mListMeeting;
    private LayoutInflater mInflater;

    //----------------------------------------------------------------------------------------------
    private Listener callback;

    public interface Listener {
        void onClickDelete(int position, Meeting meetingToDel);

        void onUpdateList(List<Meeting> meets);

    }
    //----------------------------------------------------------------------------------------------

    public MeetingAdapter(Context context, List<Meeting> mListMeeting2) {
        mInflater = LayoutInflater.from(context);
        this.mListMeeting = mListMeeting2;
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

      //  Log.i("THOMAS", "Salle en cours : " + mCurrent.getmPosition());

        switch (mCurrent.getmPosition()) {
            case "Peach":
                Log.i("COLOR", "couleur col1");
                //holder.mAvatar.getBackground().setTint(DrawableCompat.setTint(R.drawable.circular, R.color.col1));
                //ContextCompat.getColor(holder.itemView.getContext(), R.color.col1));
                break;
            case "Toad":
                Log.i("COLOR", "couleur col2");
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.col2));
                break;
            case "Yoshi":
                Log.i("COLOR", "couleur col3");
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.col3));
                break;
            case "Bowser":
                Log.i("COLOR", "couleur col4");
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.col4));
                break;
            case "Wario":
                Log.i("COLOR", "couleur col5");
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.col5));
                break;
            case "Waluigi":
                Log.i("COLOR", "couleur col6");
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.col6));
                break;
            case "Bidosaure":
                Log.i("COLOR", "couleur col7");
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.col7));
                break;
            case "Bero":
                Log.i("COLOR", "couleur col8");
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.col8));
                break;
            case "Mario":
                Log.i("COLOR", "couleur col9");
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.col9));
                break;
            case "Xmen":
                Log.i("COLOR", "couleur col10");
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.col10));
                break;
            default:
                Log.i("COLOR", "couleur col11 default");
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.col11));

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
    public int getItemCount() {
        return mListMeeting.size();
    }

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
