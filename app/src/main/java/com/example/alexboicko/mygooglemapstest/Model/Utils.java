package com.example.alexboicko.mygooglemapstest.Model;

import android.location.Location;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

    public final static class DAYS_OF_WEEK {
        public final static String SUNDAY = "Sunday";
        public final static String MONDAY = "Monday";
        public final static String TUESDAY = "Tuesday";
        public final static String WEDNESDAY = "Wednesday";
        public final static String THURSDAY = "Thursday";
        public final static String FRIDAY = "Friday";
        public final static String SATURDAY = "Saturday";
    }

    public static Long convertTimeBeginEventFromStringToLong(String time){
         String[] hourMM = time.split(":",2);
        String currentDate = new SimpleDateFormat("kk:mm").format(Calendar.getInstance().getTime());
        String[] currentHM = currentDate.split(":",2);
        long currentTimeInLong = Integer.valueOf(currentHM[0]) * 3600000 + Integer.valueOf(currentHM[1]) * 60000;
        long currentDateInLong = Calendar.getInstance().getTimeInMillis() - currentTimeInLong;
        Log.d("MY_TAG", "\ncurrentDate: " + currentDateInLong + " Current Time: " + currentTimeInLong);
        long timeInLong = currentDateInLong + Integer.valueOf(hourMM[0]) * 3600000 + Integer.valueOf(hourMM[1]) * 60000;
        Log.d("MY_TAG","Hour: " + Integer.valueOf(hourMM[0]) * 3600000 + " Minutes: " + Integer.valueOf(hourMM[1]) * 60000);
        Log.d("MY_TAG", "Sum: " + timeInLong);
        return timeInLong;
    }






    public static String toGson(DailySchedule dailySchedule) {
        Gson gson = new Gson();
        return gson.toJson(dailySchedule);
    }
    public static DailySchedule fromGSON(String dailySchedule){
        Gson gson = new Gson();
        Type token = new TypeToken<DailySchedule>(){}.getType();
        //Log.d("MY_TAG", dailySchedule);
        return gson.fromJson(dailySchedule,token);
    }
}
