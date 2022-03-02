package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.datn_project.adapters.TeacherFeeAdapter;
import com.example.datn_project.databinding.ActivityTeacherFeeBinding;
import com.example.datn_project.models.Student;
import com.example.datn_project.viewmodel.HealthViewModel;
import com.example.datn_project.viewmodel.TeacherFeeViewModel;

import java.util.ArrayList;
import java.util.List;

public class TeacherFeeActivity extends AppCompatActivity implements TeacherFeeAdapter.OnClickItemListener {
    private ActivityTeacherFeeBinding mBinding;
    private TeacherFeeAdapter feeAdapter;
    private TeacherFeeViewModel viewModel;
    private List<Student> mListStudent = new ArrayList<>();
    private int classID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityTeacherFeeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        feeAdapter = new TeacherFeeAdapter();
        viewModel = new ViewModelProvider(this).get(TeacherFeeViewModel.class);
        classID = getIntent().getIntExtra("key_class", -1);
        mBinding.rclFee.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.rclFee.setAdapter(feeAdapter);
        viewModel.getListStudent().observe(this, students -> {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getClassID() == classID) {
                    mListStudent.add(students.get(i));
                }
            }
            feeAdapter.setData(mListStudent);
        });
        feeAdapter.setOnClickItemListener(this);
        initToolbar();
    }

    @Override
    public void onClick(int position) {
        int id = mListStudent.get(position).getId();
        Intent intent = new Intent(getApplicationContext(), FeeActivity.class);
        intent.putExtra("key_student", id);
        intent.putExtra("role", "teacher");
        startActivity(intent);
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
