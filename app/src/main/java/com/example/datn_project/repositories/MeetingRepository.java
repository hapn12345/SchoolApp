package com.example.datn_project.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datn_project.models.Fee;
import com.example.datn_project.models.Meeting;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeetingRepository {
    private ApiService apiService;

    public MeetingRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<Meeting>> getMeeting() {
        MutableLiveData<List<Meeting>> data = new MutableLiveData<>();
        apiService.getMeeting().enqueue(new Callback<List<Meeting>>() {
            @Override
            public void onResponse(@NonNull Call<List<Meeting>> call, @NonNull Response<List<Meeting>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Meeting>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
