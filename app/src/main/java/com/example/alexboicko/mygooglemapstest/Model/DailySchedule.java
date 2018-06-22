package com.example.alexboicko.mygooglemapstest.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by alexboicko on 6/2/18.
 */

public class DailySchedule {

    //private long currentDay;
    private List<Event> eventList;
    private List<String> daysOfWeek;

    public DailySchedule(){

    }

   // public long getCurrentDay() {
   //     return currentDay;
  //  }

    /*public void setCurrentDay(long currentDay) {
        this.currentDay = currentDay;
    }*/

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    public List<String> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<String> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }




}
