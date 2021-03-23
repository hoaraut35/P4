package com.hoarauthomas.p04_withnotify.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.hoarauthomas.p04_withnotify.R;
import com.hoarauthomas.p04_withnotify.callbacks.Mycallbacks;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.WordViewHolder>   {

    private List<Meeting> mListMeeting;
    private List<Meeting> mListMeetingAll;
    private final LayoutInflater mInflater;

    private static final int VIEW_TYPE_EMPTY_LIST_PLACEHOLDER = 0;
    private static final int VIEW_TYPE_OBJECT_VIEW = 1;


    public interface Listener
    {
        void onDelete();
        void onChanged();
    }
    private Listener callback;




    public MeetingAdapter(Context context, List<Meeting> mListMeeting2) {
        mInflater = LayoutInflater.from(context);
        this.mListMeeting = mListMeeting2;
        this.mListMeetingAll = mListMeeting2;
        //this.callback = callback;

    }

    @NonNull
    @Override
    public MeetingAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)  {

        View mItemView;

        switch (viewType)
        {


            case VIEW_TYPE_EMPTY_LIST_PLACEHOLDER:
                //mItemView = mInflater.inflate(R.layout.emptylist, parent, false);
                mItemView = mInflater.inflate(R.layout.custom_meeting_layout, parent, false);

                break;

                case VIEW_TYPE_OBJECT_VIEW:
                mItemView = mInflater.inflate(R.layout.custom_meeting_layout, parent, false);
                break;

            default:
                mItemView = mInflater.inflate(R.layout.custom_meeting_layout, parent, false);
                break;

        }

        Log.i("THOMAS","Type de vue : " + viewType);

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
            //TODO: callback delete
            //callback.onDelete();
            mListMeeting.remove(position);
            notifyDataSetChanged();
        });
    }



    @Override
    public int getItemCount() {
        Log.i("THOMAS","getitem count : " + mListMeeting.size());
        return mListMeeting.size();}

    @Override
    public int getItemViewType(int position) {
        Log.i("THOMAS","Taille liste vue :  " + mListMeeting.isEmpty());
        if (mListMeeting.isEmpty())
        {
            return VIEW_TYPE_EMPTY_LIST_PLACEHOLDER;
        }else
        {
            return VIEW_TYPE_OBJECT_VIEW;
        }

        //return super.getItemViewType(position);
    }

    final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver(){
        @Override
        public void onChanged() {
            super.onChanged();




        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);

        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
        }
    };




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
