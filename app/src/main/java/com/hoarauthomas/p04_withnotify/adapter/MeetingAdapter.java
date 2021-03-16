package com.hoarauthomas.p04_withnotify.adapter;

import android.content.Context;
import android.net.sip.SipSession;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.hoarauthomas.p04_withnotify.R;
import com.hoarauthomas.p04_withnotify.api.MeetingApiService;
import com.hoarauthomas.p04_withnotify.di.DI;
import com.hoarauthomas.p04_withnotify.model.Meeting;
import com.hoarauthomas.p04_withnotify.view.AddMeetingActivity;

import java.util.ArrayList;
import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.WordViewHolder> implements AddMeetingActivity.Listener, Filterable
{
    private List<Meeting> mListMeeting = null;
    private LayoutInflater mInflater;

    private List<Meeting> mCopyListMeeting;

    public MeetingApiService service;

    private Listener callback;
    public interface Listener
    {
        void onClickDelete(int position, Meeting meetingToDel);
        void onUpdateList(List<Meeting> meets);
        //void onUpdateMeet(Meeting newMeeting);
    }




    public MeetingAdapter(Context context, List<Meeting> mListMeeting2)
    {
        Log.i("THOMAS","Constructeur adapteur : " + mListMeeting2.size());
        mInflater = LayoutInflater.from(context);
        this.mListMeeting = mListMeeting2;
      //  this.mListMeeting = service.getMeetings();
        this.mCopyListMeeting = new ArrayList<Meeting>(mListMeeting2);
    }

    @NonNull
    @Override
    public MeetingAdapter.WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View mItemView = mInflater.inflate(R.layout.custom_meeting_layout, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingAdapter.WordViewHolder holder, int position)
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

        holder.mNameView.setText(mCurrent.getmSubject() + " - ");
        holder.mStartTime.setText(mCurrent.getmStartTime() + " - ");
        holder.mPlace.setText(mCurrent.getmPosition());
        holder.mParticipants.setText(mCurrent.getmParticipants());

       /* Glide.with(holder.mDeleteImage.getContext())
                .load(R.drawable.ic_baseline_restore_from_trash_24)
                .placeholder(R.drawable.ic_baseline_restore_from_trash_24)
              //  .apply(RequestOptions.centerCropTransform())
                .into(holder.mDeleteImage)  ;
*/


        holder.mDeleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListMeeting.remove(position);
               // service.getMeetings().remove(position);
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mListMeeting.size();
    }

    @Override
    public void onAddMeeting(int position)
    {
        Log.i("THOMAS","callback add");
    }

    @Override
    public Filter getFilter()
    {
        return mMeetingsFilter;
    }

    private Filter mMeetingsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            String charString = constraint.toString();

            List<Meeting> filteredlist = new ArrayList<Meeting>();

          // mCopyListMeeting = service.getMeetings();

            Log.i("THOMAS","Taille liste origine mListMeetingtemp :" + mListMeeting.size()+ " copie : " +  mCopyListMeeting.size());

            if (charString == null || charString.length() == 0 || charString.isEmpty() )
            {
                Log.i("THOMAS","Contrainte inactive donc liste complète"+ mListMeeting.size()+ " " +mCopyListMeeting);
                filteredlist.addAll(mCopyListMeeting);

            }else
            {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Meeting item :mCopyListMeeting)
                {
                    if (item.getmPosition().toLowerCase().contains(filterPattern))
                    {
                        Log.i("THOMAS","retour"+  item.getmPosition().toString());
                       filteredlist.add(item);

                    }
                }

            }

            FilterResults results = new FilterResults();
            results.values = filteredlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            mListMeeting.clear();
            mListMeeting.addAll((List)results.values);



            notifyDataSetChanged();
       }
    };


    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final TextView wordItemView;
        final MeetingAdapter mAdapter;
        public ImageView mAvatar;
        public TextView mNameView;
        public TextView mStartTime;
        public TextView mPlace;
        public TextView mParticipants;
        public ImageView mDeleteImage;

        public WordViewHolder(@NonNull View itemView, MeetingAdapter adapter)
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

           // Meeting element = mListMeetingtemp.get(mPosition);


            //mListMeeting.addAll(mPosition,)
           // mListMeetings.set(mPosition, "Clicked! " + element);
            mAdapter.notifyDataSetChanged();
        }
    }
}
