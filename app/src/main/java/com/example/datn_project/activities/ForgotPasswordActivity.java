package com.example.datn_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.datn_project.databinding.ActivityForgotPasswordBinding;

public class ForgotPasswordActivity extends AppCompatActivity {
    private ActivityForgotPasswordBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
    }
}