package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.datn_project.adapters.DishAdapter;
import com.example.datn_project.databinding.ActivityBreakfastBinding;
import com.example.datn_project.databinding.ActivityLunchBinding;
import com.example.datn_project.models.menu.Monday;
import com.example.datn_project.models.menu.StringMenu;

public class LunchActivity extends AppCompatActivity {
    private ActivityLunchBinding mBinding;
    private DishAdapter dishAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLunchBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        dishAdapter = new DishAdapter();
        mBinding.rclDish.setAdapter(dishAdapter);
        mBinding.rclDish.setLayoutManager(new LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
        ));
        initToolbar();
        getData();
    }

    private void getData() {
        StringMenu stringMenu = getIntent().getParcelableExtra("key_menu");
        int id = getIntent().getIntExtra("key_day", -1);
        mBinding.txtStartAt.setText(stringMenu.getMonday().getLunch().getStartAt());
        mBinding.txtEndAt.setText(stringMenu.getMonday().getLunch().getEndAt());
        switch (id) {
            case 1:
                dishAdapter.setData(stringMenu.getMonday().getLunch().getDish());
                break;
            case 2:
                dishAdapter.setData(stringMenu.getTuesday().getLunch().getDish());
                break;
            case 3:
                dishAdapter.setData(stringMenu.getWednesday().getLunch().getDish());
                break;
            case 4:
                dishAdapter.setData(stringMenu.getThursday().getLunch().getDish());
                break;
            case 5:
                dishAdapter.setData(stringMenu.getFriday().getLunch().getDish());
                break;
        }
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