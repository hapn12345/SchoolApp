package com.example.datn_project.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datn_project.models.Health;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;
import com.example.datn_project.responses.HealthResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthRepository {
    private ApiService apiService;

    public HealthRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<Health>> getHealth() {
        MutableLiveData<List<Health>> data = new MutableLiveData<>();
        apiService.getListHealth().enqueue(new Callback<List<Health>>() {
            @Override
            public void onResponse(@NonNull Call<List<Health>> call, @NonNull Response<List<Health>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Health>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
