package com.example.alexboicko.mygooglemapstest.Model.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alexboicko on 6/2/18.
 */

public class DataBase extends SQLiteOpenHelper {

    public final static String NAME_DATA_BASE = "DAILY_SCHEDULE";
    public final static int Version = 1;

    public final static String NAME_TABLE = "FORMS_FOR_DAILY_SCHEDULE";
    public final static String INDEX = "id";

    public final static String DAILY_SCHEDULE = "DAILY_SCHEDULE";


    private boolean isDroped;

    public DataBase(Context context){
        super(context,NAME_DATA_BASE,null,Version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " +
                                NAME_TABLE + " (" +
                                INDEX + " INTEGER PRIMARY KEY, " +
                                DAILY_SCHEDULE  + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            sqLiteDatabase.execSQL("DROP TABLE " + NAME_DATA_BASE + ";");
            dropTabelDataBase(sqLiteDatabase);

    }

    public void dropTabelDataBase(SQLiteDatabase sqLiteDatabase){
        try {

            if (sqLiteDatabase.query(NAME_TABLE,null,null,null,null,null,INDEX).getCount() != 0) {
                isDroped = false;
            } else {
                isDroped = true;
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

    public boolean isDroped() {
        return isDroped;
    }
}
