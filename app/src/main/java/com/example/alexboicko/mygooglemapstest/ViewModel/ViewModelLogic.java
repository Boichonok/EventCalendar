package com.example.alexboicko.mygooglemapstest.ViewModel;

import android.content.Context;
import android.graphics.drawable.Icon;
import android.location.Location;
import android.media.Image;
import android.util.Log;

import com.example.alexboicko.mygooglemapstest.Model.DailySchedule;
import com.example.alexboicko.mygooglemapstest.Model.DataBase.DataBaseManager;
import com.example.alexboicko.mygooglemapstest.Model.Event;
import com.example.alexboicko.mygooglemapstest.Model.ModelLogic;
import com.example.alexboicko.mygooglemapstest.Model.ModelToViewModel;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexboicko on 6/3/18.
 */

public class ViewModelLogic  implements ModelToViewModel{

    private static volatile ViewModelLogic instance;
    public static ViewModelLogic getInstance(Context context){
        if(instance == null) {
            synchronized (ViewModelLogic.class){
                if (instance == null) {
                    instance = new ViewModelLogic(context);
                }
            }
        }
        return instance;
    }



    private ModelLogic modelLogic;
    private List<Event> events = new ArrayList<Event>();



      public ViewModelLogic(Context context){
        this.modelLogic = new ModelLogic(context,this);
    }

    /**
     * Этот метод вызывается когда форма создалась и информация готова для сохранения.
     * @param dailySchedule
     */
    @Override
    public void formWasCreated(DailySchedule dailySchedule) {
        modelLogic.saveForm(dailySchedule);

    }






    @Override
    public void eventWasCreated(Event event) {
        events.add(event);
    }

    public List<Event> getEvents() {
        return events;
    }

    public void createEvent(String typeOfEvent, String descriptionOfEvent, Integer eventIconId, LatLng location, long timeBeginEvent) {

     modelLogic.createEvent(typeOfEvent,descriptionOfEvent,eventIconId,location,timeBeginEvent);
    }

    public void createSchedule(List<String> daysOfWeek){
        modelLogic.createForm(events,daysOfWeek);
    }

    /**
     * Этот метод используется в View, для того что бы подать запрос, на поиск формы рассписания, на текущий день.
     *
     */
    public List<DailySchedule> findFormForCurrentDay(){

        return modelLogic.findFormsForCurrentDay();
    }


    public List<DailySchedule> showAllForms(){
        return modelLogic.getAllForm();
    }
}
