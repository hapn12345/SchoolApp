package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.datn_project.R;
import com.example.datn_project.databinding.ActivityFeeBinding;
import com.example.datn_project.databinding.ActivityMeetingBinding;
import com.example.datn_project.models.LeaveDay;
import com.example.datn_project.models.Meeting;
import com.example.datn_project.viewmodel.MeetingViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MeetingActivity extends AppCompatActivity {
    private ActivityMeetingBinding mBinding;
    public MeetingViewModel viewModel;
    public int classID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMeetingBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();

        classID = getIntent().getIntExtra("key_class", -1);

        viewModel = new ViewModelProvider(this).get(MeetingViewModel.class);
        viewModel.getMeeting().observe(this, meetings -> {
            List<EventDay> events = new ArrayList<>();
            for (int i = 0; i < meetings.size(); i++) {
                Date date = new Date(convertTime(meetings.get(i).getDate()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                events.add(new EventDay(calendar, R.drawable.ic_meeting));
                mBinding.calendarView.setEvents(events);

                int finalI = i;
                mBinding.calendarView.setOnDayClickListener(eventDay -> {
                    Calendar clickedDayCalendar = eventDay.getCalendar();
                    if (date != eventDay.getCalendar().getTime()) {
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String firstDate = formatter.format(new Date(clickedDayCalendar.getTime().getTime()));
                        String des = meetings.get(finalI).getDescription() + " -- " + firstDate;
                        mBinding.txtDescription.setText(des);
                    } else {
                        mBinding.txtDescription.setText("");
                    }
                });
            }

        });

    }

    private String convertTime(String time) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = inputFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assert date != null;
        return outputFormat.format(date);
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}