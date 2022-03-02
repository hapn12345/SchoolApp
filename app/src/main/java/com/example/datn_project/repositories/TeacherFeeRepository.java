package com.example.datn_project.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datn_project.models.Student;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;
import com.example.datn_project.responses.ParticipantsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherFeeRepository {
    private ApiService apiService;

    public TeacherFeeRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<List<Student>> getListStudent() {
        MutableLiveData<List<Student>> data = new MutableLiveData<>();
        apiService.getListStudents().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(@NonNull Call<List<Student>> call, @NonNull Response<List<Student>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Student>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
