package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.datn_project.R;
import com.example.datn_project.adapters.TeacherHealthAdapter;
import com.example.datn_project.databinding.ActivityTeacherHealthBinding;
import com.example.datn_project.models.Health;
import com.example.datn_project.repositories.HealthRepository;
import com.example.datn_project.viewmodel.HealthViewModel;
import com.example.datn_project.viewmodel.HomeViewModel;

import java.util.List;

public class TeacherHealthActivity extends AppCompatActivity {
    private ActivityTeacherHealthBinding mBinding;
    private TeacherHealthAdapter adapter;
    private HealthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityTeacherHealthBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        adapter = new TeacherHealthAdapter();
        viewModel = new ViewModelProvider(this).get(HealthViewModel.class);
        viewModel.getListHealth().observe(this, health -> adapter.setData(health));
        mBinding.rclHealths.setAdapter(adapter);
        mBinding.rclHealths.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
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
                InfoDialog infoDialog = new InfoDialog(TeacherHealthActivity.this);
                infoDialog.show();
                break;
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