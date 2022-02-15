package com.example.datn_project.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.Activity;
import com.example.datn_project.models.Participant;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivitiesRepository {
    private ApiService apiService;

    public ActivitiesRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<Activities> getActivities() {
        MutableLiveData<Activities> data = new MutableLiveData<>();
        apiService.getListActivities().enqueue(new Callback<Activities>() {
            @Override
            public void onResponse(@NonNull Call<Activities> call, @NonNull Response<Activities> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Activities> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Participant> getParticipants(int id) {
        MutableLiveData<Participant> data = new MutableLiveData<>();
        apiService.getParticipants(id).enqueue(new Callback<Participant>() {
            @Override
            public void onResponse(@NonNull Call<Participant> call, @NonNull Response<Participant> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Participant> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
