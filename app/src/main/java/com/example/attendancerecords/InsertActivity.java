package com.example.attendancerecords;

import android.app.Activity;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_activity);
        int hours = getIntent().getIntExtra("hours", 0);
        String hour = String.valueOf(hours);
        Toast toast = Toast.makeText(this, hour, Toast.LENGTH_LONG);
        toast.show();
    }

    private void secondValueToDB(int hours) {

        SQLiteOpenHelper timeDatabaseHelper = new TimeDatabaseHelper(this);
//        try {
//            SQLiteDatabase db = timeDatabaseHelper.getWritableDatabase();
//            ContentValues newValues = new ContentValues();
//            String tim = timeFormat.format(new_time);
//            newValues.put("END_WORKING_TIME", tim);
//            if (hours > 8) {
//                newValues.put("TIME_PROCESSING", hours - 8);
//            }
//            newValues.put("HOURS_WORKED", hours);
//            db.insert("WORK_TIME", null, newValues);
//        } catch (SQLException e) {
//            Toast toast = Toast.makeText(this, "Режим записи в БД недоступен", Toast.LENGTH_SHORT);
//            toast.show();
//        }
    }
}
