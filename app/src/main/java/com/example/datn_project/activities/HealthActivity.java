package com.example.datn_project.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.datn_project.databinding.ActivityHealthBinding;
import com.example.datn_project.viewmodel.HealthViewModel;

public class HealthActivity extends AppCompatActivity {
    private ActivityHealthBinding mBinding;
    private HealthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHealthBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        viewModel = new ViewModelProvider(this).get(HealthViewModel.class);
        viewModel.getListHealth().observe(this, healthResponse -> {
            if (healthResponse != null) {
                Log.e("onCreate: ", healthResponse.size() + "");
            }

        });
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }
}