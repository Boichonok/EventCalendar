package com.example.alexboicko.mygooglemapstest.View.Fragments;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.alexboicko.mygooglemapstest.Model.Utils;
import com.example.alexboicko.mygooglemapstest.R;
import com.example.alexboicko.mygooglemapstest.ViewModel.Adapters.EventTypesListAdapter;
import com.example.alexboicko.mygooglemapstest.ViewModel.ViewModelLogic;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;

import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.model.LatLng;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Fragment_creat_new_time_line extends Fragment implements PlaceSelectionListener{

    private Unbinder unbinder;


    @BindView(R.id.eventsInThisDay)
    ListView typeOfEvents;
    @BindView(R.id.createNewEvent)
    Button createNewEvent;
    @BindView(R.id.createNewTimeLineButton)
    Button createNewTimeLine;
    @BindView(R.id.monday)
    CheckBox monday;
    @BindView(R.id.tuesday)
    CheckBox tuesday;
    @BindView(R.id.wednesday)
    CheckBox wednesday;
    @BindView(R.id.thursday)
    CheckBox thursday;
    @BindView(R.id.friday)
    CheckBox friday;
    @BindView(R.id.saturday)
    CheckBox saturday;
    @BindView(R.id.sunday)
    CheckBox sunday;

    private ViewModelLogic viewModelLogic;


    private Context context;


    private SupportPlaceAutocompleteFragment placeAutocompleteFragment;
    private LatLng placeLocation;
    private LayoutInflater inflater;
    private Dialog dialog;
    private EventTypesListAdapter eventTypesListAdapter;

    private List<String> daysOfWeek = new ArrayList<String>();


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_creat_new_time_line, container,false);
        this.inflater = inflater;
        unbinder = ButterKnife.bind(this,view);

        viewModelLogic = ViewModelLogic.getInstance(context);


        createAddNewEventDialog();

        eventTypesListAdapter = new EventTypesListAdapter(inflater,viewModelLogic.getEvents());
        eventTypesListAdapter.setCreateNewEvent(false);
        typeOfEvents.setAdapter(eventTypesListAdapter);


        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @OnCheckedChanged(R.id.sunday)
    public void setSunday(){
        if(!daysOfWeek.contains(Utils.DAYS_OF_WEEK.SUNDAY)) {
            daysOfWeek.add(Utils.DAYS_OF_WEEK.SUNDAY);
        }
    }
    @OnCheckedChanged(R.id.monday)
    public void setMonday(){
        if(!daysOfWeek.contains(Utils.DAYS_OF_WEEK.MONDAY)) {
            daysOfWeek.add(Utils.DAYS_OF_WEEK.MONDAY);
        }
    }
    @OnCheckedChanged(R.id.tuesday)
    public void setTuesday(){
        if(!daysOfWeek.contains(Utils.DAYS_OF_WEEK.TUESDAY)) {
            daysOfWeek.add(Utils.DAYS_OF_WEEK.TUESDAY);
        }
    }
    @OnCheckedChanged(R.id.wednesday)
    public void setWednesday(){
        if(!daysOfWeek.contains(Utils.DAYS_OF_WEEK.WEDNESDAY)) {
            daysOfWeek.add(Utils.DAYS_OF_WEEK.WEDNESDAY);
        }
    }
    @OnCheckedChanged(R.id.thursday)
    public void setThursday(){
        if(!daysOfWeek.contains(Utils.DAYS_OF_WEEK.THURSDAY)) {
            daysOfWeek.add(Utils.DAYS_OF_WEEK.THURSDAY);
        }
    }
    @OnCheckedChanged(R.id.friday)
    public void setFriday(){
        if(!daysOfWeek.contains(Utils.DAYS_OF_WEEK.FRIDAY)) {
            daysOfWeek.add(Utils.DAYS_OF_WEEK.FRIDAY);
        }
    }
    @OnCheckedChanged(R.id.saturday)
    public void setSaturday(){
        if(!daysOfWeek.contains(Utils.DAYS_OF_WEEK.SATURDAY)) {
            daysOfWeek.add(Utils.DAYS_OF_WEEK.SATURDAY);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }

    @Override
    public void onPlaceSelected(Place place) {
        placeLocation = place.getLatLng();
        Log.d("MY_TAG","Location: " + placeLocation.latitude + " | " + placeLocation.longitude);

    }

    @Override
    public void onError(Status status) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @SuppressLint("ResourceType")
    @OnClick(R.id.createNewTimeLineButton)
    public void createNewTimeLine(){
        if(daysOfWeek.size() == 0) {
            errorDialog("You don't choose the days of week for time line!").show();
        } else if(eventTypesListAdapter.getCount() <= 0){
            errorDialog("You don't have any events").show();
        }else{

            android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(Fragment_creat_new_time_line.this);
            fragmentTransaction.replace(R.id.fragmentLayout,(android.support.v4.app.Fragment)new Fragment_show_all_time_line());
            fragmentTransaction.commit();
            viewModelLogic.createSchedule(daysOfWeek);
           // setDataScheduleDialog().show();
        }
    }

    @OnClick(R.id.createNewEvent)
    public void createNewEvent(){
        dialog.show();
    }

    private void createAddNewEventDialog() {
        final List<Integer> iconsEventTypesID = new ArrayList<Integer>();
        final List<String> namesEventTypes = new ArrayList<String>();
        iconsEventTypesID.add(R.drawable.ic_event_dating);
        iconsEventTypesID.add(R.drawable.ic_event_gim);
        iconsEventTypesID.add(R.drawable.ic_event_home);
        iconsEventTypesID.add(R.drawable.ic_event_meeting);
        iconsEventTypesID.add(R.drawable.ic_event_sleep);
        iconsEventTypesID.add(R.drawable.ic_event_work);

        namesEventTypes.add("Dating");
        namesEventTypes.add("Gim");
        namesEventTypes.add("Home");
        namesEventTypes.add("Meeting");
        namesEventTypes.add("Sleep");
        namesEventTypes.add("Work");


        dialog = new Dialog(context);
        dialog.addContentView(inflater.inflate(R.layout.create_event_dialog,null,false),new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView title = dialog.findViewById(R.id.title);
        title.setText("New Event");

        ListView chooseEventType = dialog.findViewById(R.id.eventTypes);
        EventTypesListAdapter createEventTypesListAdapter = new EventTypesListAdapter(inflater,iconsEventTypesID,namesEventTypes);
        createEventTypesListAdapter.setCreateNewEvent(true);
        chooseEventType.setAdapter(createEventTypesListAdapter);


        final EditText eventDescribe = dialog.findViewById(R.id.eventDescription);

        placeAutocompleteFragment = (SupportPlaceAutocompleteFragment) getChildFragmentManager().findFragmentById(dialog.findViewById(R.id.autoCompletePlaces).getId());
        if(placeAutocompleteFragment == null) {
            android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            placeAutocompleteFragment = (SupportPlaceAutocompleteFragment) SupportPlaceAutocompleteFragment.instantiate(dialog.getLayoutInflater().getContext(),dialog.getLayoutInflater().getClass().getName());
            fragmentTransaction.replace(dialog.findViewById(R.id.autoCompletePlaces).getId(),placeAutocompleteFragment).commit();
        }
        placeAutocompleteFragment.setOnPlaceSelectedListener(this);



        final EditText timeBeginningHr = dialog.findViewById(R.id.timeBeginningHr);
        final EditText timeBeginningMM = dialog.findViewById(R.id.timeBeginningMM);
        final TextView doubleDot = dialog.findViewById(R.id._DoubleDot);

        Button createButton = dialog.findViewById(R.id.creatEvent);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String editTime = timeBeginningHr.getText().toString() + doubleDot.getText().toString() + timeBeginningMM.getText().toString();


                eventTypesListAdapter.notifyDataSetChanged();
                dialog.cancel();
                dialog.dismiss();
                viewModelLogic.createEvent(
                        namesEventTypes.get(EventTypesListAdapter.currentPosition),
                        eventDescribe.getText().toString(),
                        iconsEventTypesID.get(EventTypesListAdapter.currentPosition),
                        placeLocation,
                        Utils.convertTimeBeginEventFromStringToLong(editTime));
            }
        });

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {

                if(getActivity() != null) {

                }
            }
        });
        dialog.create();
    }


    private Dialog errorDialog(String errorMessage){
        final Dialog dialog = new Dialog(context);
        dialog.addContentView(inflater.inflate(R.layout.error_dialog,null,false),new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView title = (TextView) dialog.findViewById(R.id.titleError);
        title.setText("ERROR");
        TextView errorMessageTextView = (TextView)dialog.findViewById(R.id.error_message);
        errorMessageTextView.setText(errorMessage);
        Button cancelButton = (Button) dialog.findViewById(R.id.cancel);
        cancelButton.setText("Cancel");
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                dialog.dismiss();
            }
        });
        return dialog;
    }

    /*private Dialog setDataScheduleDialog(){
        final Dialog dialog = new Dialog(context);
        dialog.addContentView(inflater.inflate(R.layout.chose_date_for_time_line_dialog,null,false),new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        final EditText year = (EditText) dialog.findViewById(R.id.year);
        final TextView slash1 = (TextView) dialog.findViewById(R.id.slash1);
        final EditText month = (EditText) dialog.findViewById(R.id.month);
        final TextView slash2 = (TextView) dialog.findViewById(R.id.slash2);
        final EditText day = (EditText) dialog.findViewById(R.id.day);


        Button createButton = (Button) dialog.findViewById(R.id.creatScheduleButtonDialog);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    final String dateString = year.getText().toString() + slash1.getText().toString() + month.getText().toString() + slash2.getText().toString() + day.getText().toString();
                final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = null;



                try {
                    date  = dateFormat.parse(dateString);


                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.d("MY_TAG","" + e.toString());
                }
                viewModelLogic.createSchedule(date.getTime(), daysOfWeek);
                dialog.cancel();
                dialog.dismiss();
            }
        });

        return dialog;
    }*/

}
