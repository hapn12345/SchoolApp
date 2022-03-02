package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.datn_project.adapters.TeacherActivitiesAdapter;
import com.example.datn_project.databinding.ActivityTeacherActivitiesBinding;
import com.example.datn_project.models.Participant;
import com.example.datn_project.responses.ParticipantsResponse;
import com.example.datn_project.viewmodel.ActivitiesViewModel;
import com.example.datn_project.viewmodel.TeacherActivitiesViewModel;

import java.util.ArrayList;
import java.util.List;

public class TeacherActivitiesActivity extends AppCompatActivity implements TeacherActivitiesAdapter.removeItem {
    private ActivityTeacherActivitiesBinding mBinding;
    private TeacherActivitiesViewModel viewModel;
    private TeacherActivitiesAdapter adapter;
    private List<ParticipantsResponse> mListParticipants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityTeacherActivitiesBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        viewModel = new ViewModelProvider(this).get(TeacherActivitiesViewModel.class);
        adapter = new TeacherActivitiesAdapter();
        mListParticipants = new ArrayList<>();
        mBinding.rclParticipants.setAdapter(adapter);
        mBinding.rclParticipants.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        viewModel.getStudentParticipants().observe(this, participants -> {
            this.mListParticipants = participants;
            adapter.setData(mListParticipants);
        });
        adapter.setClassID(getIntent().getIntExtra("key_class", -1));
        adapter.setRemoveItem(this);
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
    public void onRemove(int position) {
        mListParticipants.remove(position);
        mBinding.rclParticipants.post(() -> adapter.notifyDataSetChanged());
    }

}