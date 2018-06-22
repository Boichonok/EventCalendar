package com.example.alexboicko.mygooglemapstest.ViewModel.Adapters;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.alexboicko.mygooglemapstest.Model.Event;
import com.example.alexboicko.mygooglemapstest.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EventTypesListAdapter extends BaseAdapter{

    private LayoutInflater inflater;
    private  boolean isCreateNewEvent;

    private List<Integer> idIcons;
    private List<String> nameOfEvents;
    private List<Event> eventList;



    public static int currentPosition = -1;



    public EventTypesListAdapter(LayoutInflater inflater, List<Event> eventList){
        this.inflater = inflater;
        this.eventList = eventList;

    }

    public EventTypesListAdapter(LayoutInflater inflater, List<Integer> idIcons,List<String> nameOfEvents){
        this.inflater = inflater;
        this.idIcons = idIcons;
        this.nameOfEvents = nameOfEvents;
    }

    public void setCreateNewEvent(boolean isCreateNewEvent){
        this.isCreateNewEvent = isCreateNewEvent;
    }

    @Override
    public int getCount() {
        if (isCreateNewEvent) {
            return this.idIcons.size();
        } else {
            return this.eventList.size();
        }
    }



    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

           final View view1 = inflater.inflate(R.layout.event_type_list_items, viewGroup, false);
            holder = new ViewHolder(view1);




        if(isCreateNewEvent) {
            holder.eventTime.setVisibility(View.GONE);
            holder.descripe.setVisibility(View.GONE);
            holder.icon.setImageResource(idIcons.get(i));
            holder.nameOfEvent.setText(nameOfEvents.get(i));
        }else {
            holder.descripe.setVisibility(View.VISIBLE);
            holder.eventTime.setVisibility(View.VISIBLE);
            holder.icon.setImageResource(eventList.get(i).getEventIconId());
            holder.nameOfEvent.setText(eventList.get(i).getTypeOfEvent());
            holder.eventTime.setText(new SimpleDateFormat("kk:mm").format(new Date(eventList.get(i).getTimeBeginEvent())));
            holder.descripe.setText(eventList.get(i).getEvent());

        }
        view1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {
                view.setBackgroundColor(R.color.colorPrimary);
                currentPosition = i;
                Log.d("MY_TAG", "I: " + i + " CurrentPosition: " + currentPosition);
            }
        });
        view1.setTag(holder);

        return view1;
    }



    static class ViewHolder{

        @BindView(R.id.icoEventType)
        ImageView icon;
        @BindView(R.id.nameOfEvent)
        TextView nameOfEvent;
        @BindView(R.id.eventTime)
        TextView eventTime;
        @BindView(R.id.descripe)
        TextView descripe;


        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
