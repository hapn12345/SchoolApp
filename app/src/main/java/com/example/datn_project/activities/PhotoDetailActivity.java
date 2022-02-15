package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.example.datn_project.databinding.ActivityPhotoDetailBinding;

public class PhotoDetailActivity extends AppCompatActivity {
    private ActivityPhotoDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityPhotoDetailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        Glide.with(this)
                .load(getIntent().getStringExtra("image"))
                .into(mBinding.imgPhoto);
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