package com.example.datn_project.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.Fee;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeRepository {
    private ApiService apiService;

    public FeeRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<Fee>> getFee(int id) {
        MutableLiveData<List<Fee>> data = new MutableLiveData<>();
        apiService.getFees(id).enqueue(new Callback<List<Fee>>() {
            @Override
            public void onResponse(@NonNull Call<List<Fee>> call, @NonNull Response<List<Fee>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Fee>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
