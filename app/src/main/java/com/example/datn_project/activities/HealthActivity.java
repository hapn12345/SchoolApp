package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.datn_project.R;
import com.example.datn_project.adapters.HealthAdapter;
import com.example.datn_project.databinding.ActivityHealthBinding;
import com.example.datn_project.models.Health;
import com.example.datn_project.viewmodel.HealthViewModel;

import java.util.ArrayList;

public class HealthActivity extends AppCompatActivity {
    private ActivityHealthBinding mBinding;
    private HealthAdapter healthAdapter;
    private ArrayList<Health> healthList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHealthBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        healthList = new ArrayList<>();
        healthAdapter = new HealthAdapter();
        mBinding.rclHealth.setAdapter(healthAdapter);
        mBinding.rclHealth.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        HealthViewModel viewModel = new ViewModelProvider(this).get(HealthViewModel.class);
        viewModel.getListHealth().observe(this, healthResponse -> {
            if (healthResponse != null) {
                Intent intent = getIntent();
                int id = intent.getIntExtra("key_student", -1);
                for (int i = 0; i < healthResponse.size(); i++) {
                    if (id == Integer.parseInt(healthResponse.get(i).getStudentID())) {
                        healthList.add(healthResponse.get(i));
                    }
                }
                healthAdapter.setData(healthList);
                healthAdapter.notifyDataSetChanged();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.action_bar_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.info:
                InfoDialog infoDialog = new InfoDialog(HealthActivity.this);
                infoDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
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