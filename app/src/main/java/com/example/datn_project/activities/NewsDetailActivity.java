package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.example.datn_project.MyConstants;
import com.example.datn_project.R;
import com.example.datn_project.databinding.ActivityNewsDetailBinding;
import com.example.datn_project.models.News;

public class NewsDetailActivity extends AppCompatActivity {
    private ActivityNewsDetailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityNewsDetailBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        getData();
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

    private void getData() {
        Intent intent = getIntent();
        News news = (News) intent.getSerializableExtra(MyConstants.KEY_NEWS);
        Glide.with(this)
                .load(news.getThumbnail())
                .into(mBinding.imgDescription);
        mBinding.txtDescription.setText(news.getDescription());
        mBinding.txtTitle.setText(news.getContent());
    }
}