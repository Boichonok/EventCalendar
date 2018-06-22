package com.example.alexboicko.mygooglemapstest.Model.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.alexboicko.mygooglemapstest.Model.DailySchedule;
import com.example.alexboicko.mygooglemapstest.Model.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alexboicko on 6/2/18.
 */

public class DataBaseManager extends DataBase {

    public DataBaseManager(Context context){
        super(context);
    }

    public boolean createDailySchedule(DailySchedule dailySchedule){
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBase.DAILY_SCHEDULE,Utils.toGson(dailySchedule));

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        boolean createSuccessful = sqLiteDatabase.insert(DataBase.NAME_TABLE,null,contentValues) > 0;
        sqLiteDatabase.close();
        return createSuccessful;
    }

    public int count() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = "SELECT * FROM " + DataBase.NAME_TABLE;
        int count = sqLiteDatabase.rawQuery(sql,null).getCount();
        sqLiteDatabase.close();
        return count;
    }

    public List<DailySchedule> readAllForms(){
        List<DailySchedule> allForms = new ArrayList<DailySchedule>();
        String sql = "SELECT * FROM " + DataBase.NAME_TABLE + " ORDER BY " + DataBase.INDEX + " DESC";

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()) {
            do {
                String dailyScheduleString = cursor.getString(cursor.getColumnIndex(DataBase.DAILY_SCHEDULE));


                //Log.d("MY_TAG","In DAB MAnager:" + new Date(Utils.fromGSON(dailyScheduleString).getCurrentDay()));
                    allForms.add(Utils.fromGSON(dailyScheduleString));





            } while (cursor.moveToNext());
        }


        return allForms;
    }

    /*
    Not Req.
     */
    public boolean update(DailySchedule dailySchedule, int index) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBase.DAILY_SCHEDULE,Utils.toGson(dailySchedule));

        String where = DataBase.INDEX + " = ?";
        String[] whereArgs = {Integer.toString(index)};
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        boolean updateSuccessful = sqLiteDatabase.update(DataBase.NAME_TABLE,contentValues,where,whereArgs) > 0;
        sqLiteDatabase.close();
        return updateSuccessful;
    }

    public boolean delete(int index){
        boolean deleteSuccessful = false;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        deleteSuccessful = sqLiteDatabase.delete(DataBase.NAME_TABLE, DataBase.INDEX + " ='" + index + "'", null) > 0;
        sqLiteDatabase.close();
        return deleteSuccessful;
    }
}
