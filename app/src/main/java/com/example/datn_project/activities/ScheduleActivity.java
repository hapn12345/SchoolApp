package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.datn_project.adapters.ScheduleAdapter;
import com.example.datn_project.databinding.ActivityScheduleBinding;
import com.example.datn_project.models.Classes;
import com.example.datn_project.models.Schedule;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ScheduleActivity extends AppCompatActivity implements ScheduleAdapter.OnSchedule {
    private ActivityScheduleBinding mBinding;
    private ScheduleAdapter scheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityScheduleBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        scheduleAdapter = new ScheduleAdapter();
        mBinding.rclPeriod.setAdapter(scheduleAdapter);
        mBinding.rclPeriod.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Classes classes = (Classes) getIntent().getSerializableExtra("key_schedule");
        String jsonString = classes.getSchedule();
        Type collectionType = new TypeToken<List<Schedule>>() {
        }.getType();
        List<Schedule> lcs = new Gson()
                .fromJson(jsonString, collectionType);
        scheduleAdapter.setData(lcs);
        scheduleAdapter.setListener(this);
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

    @Override
    public void onClick(int position, Schedule schedule) {
        schedule.setExpandable(!schedule.isExpandable());
        scheduleAdapter.notifyItemChanged(position);
    }
}