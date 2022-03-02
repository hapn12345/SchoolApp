package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.datn_project.R;
import com.example.datn_project.adapters.ActivitiesAdapter;
import com.example.datn_project.databinding.ActivityActivitiesBinding;
import com.example.datn_project.models.Activity;
import com.example.datn_project.models.Participant;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;
import com.example.datn_project.viewmodel.ActivitiesViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitiesActivity extends AppCompatActivity implements ActivitiesAdapter.OnActivitiesListener {
    private ActivityActivitiesBinding mBinding;
    private ActivitiesAdapter mAdapter;
    private ActivitiesViewModel viewModel;
    private int studentId;
    private int classId;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityActivitiesBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        getData();
    }

    private void getData() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
        mAdapter = new ActivitiesAdapter();
        viewModel = new ViewModelProvider(this).get(ActivitiesViewModel.class);

        viewModel.getListActivities().observe(this, mListActivities -> {
            mAdapter.setData(mListActivities.getActivities());
            mBinding.rclActivity.setAdapter(mAdapter);
            mAdapter.setListener(this);
        });


        studentId = getIntent().getIntExtra("key_student", -1);
        classId = getIntent().getIntExtra("key_class", -1);
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(int position, Activity activity) {
        viewModel.getParticipants(activity.getId()).observe(this, participant -> {
            activity.setExpandable(!activity.isExpandable());
            if (participant != null) {
                activity.setRegistered(participant.getClassID() == classId);
            }
            mAdapter.notifyItemChanged(position);
        });
    }

    @Override
    public void onParticipants(int position, Activity activity) {
        Participant participant = new Participant();
        participant.setStudentID(studentId);
        participant.setActivityID(activity.getId());
        participant.setClassID(classId);

        apiService.activitiesParticipants(participant).enqueue(new Callback<Participant>() {
            @Override
            public void onResponse(@NonNull Call<Participant> call, @NonNull Response<Participant> response) {
                activity.setRegistered(!activity.isRegistered());
                mAdapter.notifyItemChanged(position);
            }

            @Override
            public void onFailure(@NonNull Call<Participant> call, @NonNull Throwable t) {
                Log.e("onFailure: ", t.getLocalizedMessage());
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
}