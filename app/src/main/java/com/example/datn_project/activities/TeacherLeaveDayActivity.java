package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.datn_project.adapters.LeaveDayAdapter;
import com.example.datn_project.databinding.ActivityTeacherLeaveDayBinding;
import com.example.datn_project.responses.LeaveDayResponse;
import com.example.datn_project.viewmodel.LeaveDayViewModel;
import com.example.datn_project.viewmodel.TeacherLeaveDayViewModel;

import java.util.ArrayList;
import java.util.List;

public class TeacherLeaveDayActivity extends AppCompatActivity {
    private ActivityTeacherLeaveDayBinding mBinding;
    private LeaveDayAdapter adapter;
    private TeacherLeaveDayViewModel viewModel;
    private int classID;
    private List<LeaveDayResponse> mListLeaveDay = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityTeacherLeaveDayBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        adapter = new LeaveDayAdapter();
        initToolbar();
        classID = getIntent().getIntExtra("key_class", -1);
        mBinding.rclActivity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.rclActivity.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(TeacherLeaveDayViewModel.class);
        viewModel.getListStudent().observe(this, leaveDayResponses -> {
            if (leaveDayResponses != null) {
                for (int i = 0; i < leaveDayResponses.size(); i++) {
                    if (classID == Integer.parseInt(leaveDayResponses.get(i).getClassID())) {
                        mListLeaveDay.add(leaveDayResponses.get(i));
                    }
                }
                adapter.setData(mListLeaveDay);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}