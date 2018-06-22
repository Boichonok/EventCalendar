package com.example.alexboicko.mygooglemapstest.Model;

import android.content.Context;
import android.util.Log;

import com.example.alexboicko.mygooglemapstest.Model.DataBase.DataBaseManager;
import com.google.android.gms.maps.model.LatLng;

import java.security.Provider;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by alexboicko on 6/2/18.
 */

public class ModelLogic {


    private ModelToViewModel modelToViewModel;
    private DataBaseManager dataBaseManager;
    private List<Event> events;

    public ModelLogic(Context context, ModelToViewModel modelToViewModel) {
        dataBaseManager = new DataBaseManager(context);
        this.modelToViewModel = modelToViewModel;
    }




    public void createForm( List<Event> events, List<String> daysOfWeek){
         DailySchedule dailyScheduleForm = new DailySchedule();
         dailyScheduleForm.setEventList(events);
         dailyScheduleForm.setDaysOfWeek(daysOfWeek);
         modelToViewModel.formWasCreated(dailyScheduleForm);
    }

    public void createEvent(String typeOfEvent, String descriptionOfEvent, Integer eventIconId, LatLng location,long timeBeginEvent) {
        Event event = new Event();
        event.setTypeOfEvent(typeOfEvent);
        event.setEvent(descriptionOfEvent);
        event.setEventIconId(eventIconId);
        event.setLocation(location);
        event.setTimeBeginEvent(timeBeginEvent);
        modelToViewModel.eventWasCreated(event);
    }
    public void saveForm(DailySchedule dailySchedule){
        dataBaseManager.createDailySchedule(dailySchedule);

    }

    public List<DailySchedule> getAllForm(){
        return dataBaseManager.readAllForms();
    }

    public List<DailySchedule> findFormsForCurrentDay(){
        String currentDayOfWeek = new SimpleDateFormat("EEE",Locale.ENGLISH).format(Calendar.getInstance().getTime().getTime());//нужно добавить автоматическое определение часового пояса.
        List<DailySchedule> allForms = dataBaseManager.readAllForms();
        List<DailySchedule> formsForCurrentDay = new ArrayList<DailySchedule>();
        for (int i = 0; i < allForms.size(); i ++) {
            for (int j = 0; j < allForms.get(i).getDaysOfWeek().size(); j ++){
                if (allForms.get(i).getDaysOfWeek().get(j).substring(0,3).equals(currentDayOfWeek)){
                    Log.d("MY_TAG", "Days: " + allForms.get(i).getDaysOfWeek().get(j));
                    formsForCurrentDay.add(allForms.get(i));
                } else {
                    Log.d("MY_TAG","Current Day: " + currentDayOfWeek + " Days: " + allForms.get(i).getDaysOfWeek().get(j).substring(0,3));
                }
            }
        }
       return formsForCurrentDay;
    }
}
