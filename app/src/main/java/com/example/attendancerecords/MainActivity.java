package com.example.attendancerecords;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    Button start_button;
    Button stop_button;
    Button insertToDB_button;
    TextView hours_number;
    TextView hours_numeral;
    TextView minutes_number;
    TextView minutes_numeral;
    TextView seconds_number;
    TextView seconds_numeral;
    private boolean running;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    private Date start_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_button = (Button) findViewById(R.id.butStart);
        stop_button = (Button) findViewById(R.id.butStop);
        insertToDB_button = (Button) findViewById(R.id.insertToDB);
        seconds_number = (TextView) findViewById(R.id.seconds_number);
        seconds_numeral = (TextView) findViewById(R.id.seconds_numeral);
        minutes_number = (TextView) findViewById(R.id.minutes_number);
        minutes_numeral = (TextView) findViewById(R.id.minutes_numeral);
        hours_number = (TextView) findViewById(R.id.hours_number);
        hours_numeral = (TextView) findViewById(R.id.hours_numeral);
        Date date = new Date();
        start_date = date;
        runTimer();
        firstValueToDB(start_date); //Функция для записи начальныз значений в БД
        insertToDB_button.setEnabled(false);
        OnClickListener ocl_start = new OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
            }
        };
        start_button.setOnClickListener(ocl_start);

        OnClickListener ocl_stop = new OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                insertToDB_button.setEnabled(true);
            }
        };
        stop_button.setOnClickListener((ocl_stop));

        OnClickListener insertToDB = new OnClickListener() {
            @Override
            public void onClick(View v) {
                secondValueToDB();
                Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                intent.putExtra("hours", hours);
                startActivity(intent);
            }
        };
        insertToDB_button.setOnClickListener(insertToDB);


    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                if ((seconds % 10) == 0) {
                    int sec = seconds / 10;
                    String se = String.valueOf(sec);
                    Log.d("SECONDS", se);
                    seconds_numeral.setText(se);
                    seconds_number.setText("0");
                } else {
                    int sec = seconds % 10;
                    String se = String.valueOf(sec);
                    seconds_number.setText(se);
                }
                if (seconds == 60) {
                    minutes += 1;
                    seconds = 0;
                    seconds_numeral.setText("0");
                    if ((minutes % 10) == 0) {
                        int min = minutes / 10;
                        String mi = String.valueOf(min);
                        minutes_numeral.setText(mi);
                        minutes_number.setText("0");
                    } else {
                        int min = minutes % 10;
                        String mi = String.valueOf(min);
                        minutes_number.setText(mi);
                    }
                }
                if (minutes == 60) {
                    hours += 1;
                    minutes = 0;
                    seconds = 0;
                    minutes_numeral.setText("0");
                    if ((hours % 10) == 0) {
                        int hour = hours / 10;
                        String hou = String.valueOf(hour);
                        hours_numeral.setText(hou);
                        hours_number.setText("0");
                    } else {
                        int hour = hours % 10;
                        String hou = String.valueOf(hour);
                        hours_number.setText(hou);
                    }
                }
                 if (running) {
                      seconds++;
                 }
                 handler.postDelayed(this, 1);
                }
        });
    }

    private void firstValueToDB(Date date) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        SQLiteOpenHelper timeDatabaseHelper = new TimeDatabaseHelper(this);
        try {
            SQLiteDatabase db = timeDatabaseHelper.getWritableDatabase();
            ContentValues newValues = new ContentValues();
            String dat = dateFormat.format(date);
            String tim = timeFormat.format(date);
            newValues.put("NOW_DATE", dat);
            newValues.put("START_WORKING_TIME", tim);
            db.insert("WORK_TIME", null, newValues);
        } catch (SQLException e) {
            Toast toast = Toast.makeText(this, "Режим записи в БД недоступен", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private int secondValueToDB() {
        Date new_date = new Date();
        Date old_date = start_date;
        long milliseconds = old_date.getTime() - new_date.getTime();
        int minutes = (int) milliseconds / (60 * 1000);
        int hours = minutes / 60;
        int minut = minutes % 60;
        if (minut >= 30) {
            hours++;
        }
        return hours;
    }



    @Override
    protected void onStop() {
        super.onStop();
    }
}