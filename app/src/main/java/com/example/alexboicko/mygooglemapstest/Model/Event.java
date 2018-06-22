package com.example.alexboicko.mygooglemapstest.Model;

import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alexboicko on 6/2/18.
 */

public class Event {

    private String typeOfEvent;
    private String event;
    private Integer EventIconId;
    private LatLng location;
    private long timeBeginEvent;

    public Event(){

    }

    public String getTypeOfEvent() {
        return typeOfEvent;
    }

    public void setTypeOfEvent(String typeOfEvent) {
        this.typeOfEvent = typeOfEvent;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setEventIconId(Integer eventIconId) {
        EventIconId = eventIconId;
    }

    public Integer getEventIconId() {
        return EventIconId;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public long getTimeBeginEvent() {
        return this.timeBeginEvent;

    }

    public void setTimeBeginEvent(long timeBeginEvent) {
        this.timeBeginEvent = timeBeginEvent;
    }


}
