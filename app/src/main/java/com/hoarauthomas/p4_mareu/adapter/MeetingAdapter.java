package com.hoarauthomas.p4_mareu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.hoarauthomas.p4_mareu.R;
import com.hoarauthomas.p4_mareu.model.Meeting;

import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.WordViewHolder> {

    private List<Meeting> mListMeeting;
    private final LayoutInflater mInflater;

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

    /*      <color name="Mario">#F05545</color>
    <color name="Luigi">#BC477B</color>
    <color name="Peach">#7c43BD</color>
    <color name="Toad">#428e92</color>
    <color name="Yoshi">#4c8c4a</color>
    <color name="Bowser">#FFb04c</color>
    <color name="Wario">#ff833a</color>
    <color name="Waluigi">#c3fdff</color>
    <color name="Bidosaure">#4f5b62</color>
    <color name="Bero">#4f8f4f</color>

     */
        switch (mCurrent.getmRoom())
        {
            case "Luigi":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.Luigi));
                break;

            case "Peach":
                holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), R.color.Peach));
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


        }

       /* //TODO:switch
        try {
            Field field = R.color.class.getDeclaredField(mCurrent.getmPosition());
            field.setAccessible(true);
            int idField = field.getInt(null);
            holder.mAvatar.getBackground().setTint(ContextCompat.getColor(holder.itemView.getContext(), idField));
        } catch (Exception e) {
            throw new Resources.NotFoundException(e.getMessage());
        }

        */

        holder.mNameView.setText(mCurrent.getmSubject() + " - ");
        holder.mStartTime.setText(mCurrent.getmStartTime() + " - ");
        holder.mPlace.setText(mCurrent.getmRoom());
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
