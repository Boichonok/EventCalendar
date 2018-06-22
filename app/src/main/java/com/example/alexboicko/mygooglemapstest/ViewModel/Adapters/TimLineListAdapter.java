package com.example.alexboicko.mygooglemapstest.ViewModel.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alexboicko.mygooglemapstest.Model.DailySchedule;
import com.example.alexboicko.mygooglemapstest.R;
import com.example.alexboicko.mygooglemapstest.View.CustomListView.UtilsView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimLineListAdapter extends BaseAdapter {


    private LayoutInflater inflater;
    private List<DailySchedule> allForms;


    public TimLineListAdapter(LayoutInflater inflater, List<DailySchedule> allForms){
     this.inflater = inflater;
     this.allForms = allForms;


    }

    @Override
    public int getCount() {
        if (allForms.size() > 0) {
            return allForms.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return allForms.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHolderFullList holder;

        final View view1 = inflater.inflate(R.layout.time_line_items, viewGroup, false);

        holder = new ViewHolderFullList(view1);


        holder.eventList.setVisibility(View.GONE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMM");


        holder.daysOfWeek.setText("" + allForms.get(i).getDaysOfWeek().toString());
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.eventList.getVisibility() == View.GONE) {
                    holder.eventList.setVisibility(View.VISIBLE);

                    EventTypesListAdapter eventTypesListAdapter = new EventTypesListAdapter(inflater,allForms.get(i).getEventList());
                    eventTypesListAdapter.setCreateNewEvent(false);
                    holder.eventList.setAdapter(eventTypesListAdapter);
                    UtilsView.setListViewHeightBasedOnChildren(holder.eventList);

                    eventTypesListAdapter.notifyDataSetChanged();

                } else {
                    holder.eventList.setVisibility(View.GONE);
                }
            }
        });

        view1.setTag(holder);

        return view1;
    }

    static class ViewHolderFullList{



        @BindView(R.id.AllFormdaysOfWeek)
        TextView daysOfWeek;
        @BindView(R.id.AllFormimageButton)
        ImageButton imageButton;
        @BindView(R.id.AllFormeventList)
        ListView eventList;


        public ViewHolderFullList(View view){
            ButterKnife.bind(this,view);

        }
    }
}
