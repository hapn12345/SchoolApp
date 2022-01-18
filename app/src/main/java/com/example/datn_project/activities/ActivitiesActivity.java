package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.datn_project.R;
import com.example.datn_project.adapters.ActivitiesAdapter;
import com.example.datn_project.databinding.ActivityActivitiesBinding;
import com.example.datn_project.models.Activity;
import com.example.datn_project.viewmodel.ActivitiesViewModel;

public class ActivitiesActivity extends AppCompatActivity implements ActivitiesAdapter.OnActivitiesListener {
    private ActivityActivitiesBinding mBinding;
    private ActivitiesAdapter mAdapter;
    private ActivitiesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityActivitiesBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        getData();
    }

    private void getData() {
        mAdapter = new ActivitiesAdapter();
        viewModel = new ViewModelProvider(this).get(ActivitiesViewModel.class);
        viewModel.getListActivities().observe(this, mListActivities -> {
            mAdapter.setData(mListActivities.getActivities());
            mBinding.rclActivity.setAdapter(mAdapter);
            mAdapter.setListener(this);
        });
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(int position, Activity activity) {
        activity.setExpandable(!activity.isExpandable());
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}