package com.example.datn_project.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.Participant;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;
import com.example.datn_project.responses.ParticipantsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherActivitiesRepository {
    private ApiService apiService;

    public TeacherActivitiesRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<ParticipantsResponse>> getStudentParticipants() {
        MutableLiveData<List<ParticipantsResponse>> data = new MutableLiveData<>();
        apiService.getStudentParticipant().enqueue(new Callback<List<ParticipantsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<ParticipantsResponse>> call, @NonNull Response<List<ParticipantsResponse>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<ParticipantsResponse>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
