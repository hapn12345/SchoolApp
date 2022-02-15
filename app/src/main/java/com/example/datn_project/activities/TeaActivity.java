package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.datn_project.adapters.DishAdapter;
import com.example.datn_project.databinding.ActivityBreakfastBinding;
import com.example.datn_project.databinding.ActivityTeaBinding;
import com.example.datn_project.models.menu.StringMenu;

public class TeaActivity extends AppCompatActivity {
    private ActivityTeaBinding mBinding;
    private DishAdapter dishAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityTeaBinding.inflate(getLayoutInflater());
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
        mBinding.txtStartAt.setText(stringMenu.getMonday().getTea().getStartAt());
        mBinding.txtEndAt.setText(stringMenu.getMonday().getTea().getEndAt());
        switch (id) {
            case 1:
                dishAdapter.setData(stringMenu.getMonday().getTea().getDish());
                break;
            case 2:
                dishAdapter.setData(stringMenu.getTuesday().getTea().getDish());
                break;
            case 3:
                dishAdapter.setData(stringMenu.getWednesday().getTea().getDish());
                break;
            case 4:
                dishAdapter.setData(stringMenu.getThursday().getTea().getDish());
                break;
            case 5:
                dishAdapter.setData(stringMenu.getFriday().getTea().getDish());
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