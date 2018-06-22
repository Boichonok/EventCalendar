package com.example.alexboicko.mygooglemapstest.Model;

import com.example.alexboicko.mygooglemapstest.Model.DataBase.DataBaseManager;

import java.util.List;

/**
 * Created by alexboicko on 6/3/18.
 */

public interface ModelToViewModel {
    public void formWasCreated(DailySchedule dailySchedule);
    public void eventWasCreated(Event event);
}
