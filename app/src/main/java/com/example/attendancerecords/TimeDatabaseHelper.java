package com.example.attendancerecords;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TimeDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "time";
    private static final int DB_VERSION = 1;

    TimeDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE WORK_TIME ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NOW_DATE DATE, "//Сегодняшняя дата YYYY.MM.DD
                + "START_WORKING_TIME TIME, "//Начало рабочего дня HH:MM
                + "END_WORKING_TIME TIME, "//Конец рабочего дня HH:MM
                + "HOURS_WORKED INT, "//Выполнение дневной нормы в часах
                + "TIME_PROCESSING INT, "//Переработки в часах
                + "THE_PURPOSE_OF_THE_TRIP TEXT);");//Цель командировки
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}