package com.example.datn_project.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datn_project.models.LeaveDay;
import com.example.datn_project.models.Student;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;
import com.example.datn_project.responses.LeaveDayResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherLeaveDayRepository {
    private ApiService apiService;

    public TeacherLeaveDayRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<LeaveDayResponse>> getListLeaveDay() {
        MutableLiveData<List<LeaveDayResponse>> data = new MutableLiveData<>();
        apiService.getListLeaveDay().enqueue(new Callback<List<LeaveDayResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<LeaveDayResponse>> call, @NonNull Response<List<LeaveDayResponse>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<LeaveDayResponse>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
