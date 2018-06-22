package com.example.alexboicko.mygooglemapstest.View.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.alexboicko.mygooglemapstest.Model.DailySchedule;
import com.example.alexboicko.mygooglemapstest.R;
import com.example.alexboicko.mygooglemapstest.View.CustomListView.UtilsView;
import com.example.alexboicko.mygooglemapstest.ViewModel.Adapters.TimLineListAdapter;
import com.example.alexboicko.mygooglemapstest.ViewModel.ViewModelLogic;


import java.sql.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment_show_all_time_line extends Fragment implements CompoundButton.OnCheckedChangeListener {
    private Unbinder unbinder;

    @BindView(R.id.allForms)
    ListView allTimeLine;
    @BindView(R.id.switchCurrentDay)
    Switch switchCurrentDay;


    private ViewModelLogic viewModelLogic;

    private Context context;
    private TimLineListAdapter timLineListAdapter;

    private LayoutInflater inflater;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_all_time_line, container,false);
        unbinder = ButterKnife.bind(this,view);
        this.inflater = inflater;
        viewModelLogic = new ViewModelLogic(context);


        List<DailySchedule> allForms = viewModelLogic.showAllForms();
        for (int i = 0; i < allForms.size(); i++) {

            for (int j = 0; j < allForms.get(i).getEventList().size(); j++) {
                Log.d("MY_TAG", "Event :" );
                Log.d("MY_TAG","\n" + allForms.get(i).getEventList().get(j).getTypeOfEvent() +
                "\n" + allForms.get(i).getEventList().get(j).getEvent() +
                "\n" + allForms.get(i).getEventList().get(j).getEventIconId() +
                "\n" + new Date(allForms.get(i).getEventList().get(j).getTimeBeginEvent()) +
                "\n" + allForms.get(i).getEventList().get(j).getLocation().latitude + " | " + allForms.get(i).getEventList().get(j).getLocation().longitude);
            }
            Log.d("MY_TAG","DaysOfWeek: " + allForms.get(i).getDaysOfWeek());
        }

        switchCurrentDay.setOnCheckedChangeListener(this);
        timLineListAdapter = new TimLineListAdapter(inflater,allForms);
        allTimeLine.setAdapter(timLineListAdapter);
        timLineListAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch(compoundButton.getId()) {
            case R.id.switchCurrentDay: {
                if (compoundButton.isChecked()) {

                    TimLineListAdapter timLineListAdapter = new TimLineListAdapter(inflater,viewModelLogic.findFormForCurrentDay());
                    allTimeLine.setAdapter(timLineListAdapter);
                }else {
                    allTimeLine.setAdapter(timLineListAdapter);
                }
            }
            break;
        }
    }
}
