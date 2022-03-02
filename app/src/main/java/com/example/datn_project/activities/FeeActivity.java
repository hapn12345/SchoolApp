package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.datn_project.adapters.FeeAdapter;
import com.example.datn_project.databinding.ActivityFeeBinding;
import com.example.datn_project.models.Fee;
import com.example.datn_project.models.FeeRequest;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;
import com.example.datn_project.viewmodel.FeeViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeActivity extends AppCompatActivity implements FeeAdapter.OnListener {
    private ActivityFeeBinding mBinding;
    public FeeViewModel viewModel;
    private FeeAdapter feeAdapter;
    public int studentId;
    private ApiService apiService;

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
        apiService = ApiClient.getRetrofit().create(ApiService.class);
        feeAdapter.setOnListener(this);
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

    @Override
    public void onApply(FeeRequest feeRequest, int position) {
        apiService.applyFees(UUID.fromString(feeRequest.getId()), feeRequest).enqueue(new Callback<FeeRequest>() {
            @Override
            public void onResponse(@NonNull Call<FeeRequest> call, @NonNull Response<FeeRequest> response) {
                feeAdapter.setUpdate(true);
                feeAdapter.notifyItemChanged(position);
                Toast.makeText(getApplicationContext(), "Đã xác nhận đóng học phí!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<FeeRequest> call, @NonNull Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(int position) {
        feeAdapter.setTeacher(!feeAdapter.isTeacher());
        feeAdapter.notifyItemChanged(position);
    }
}