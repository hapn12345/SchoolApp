package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.datn_project.adapters.FeeAdapter;
import com.example.datn_project.databinding.ActivityFeeBinding;
import com.example.datn_project.models.Fee;
import com.example.datn_project.models.LeaveDay;
import com.example.datn_project.viewmodel.FeeViewModel;
import com.example.datn_project.viewmodel.HomeViewModel;

import java.util.List;

public class FeeActivity extends AppCompatActivity {
    private ActivityFeeBinding mBinding;
    public FeeViewModel viewModel;
    private FeeAdapter feeAdapter;
    public int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityFeeBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        studentId = getIntent().getIntExtra("key_student", -1);
        feeAdapter = new FeeAdapter();
        mBinding.rclFee.setAdapter(feeAdapter);
        viewModel = new ViewModelProvider(this).get(FeeViewModel.class);
        viewModel.getFee(studentId).observe(this, fees -> feeAdapter.setData(fees));
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