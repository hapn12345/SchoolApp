package com.example.datn_project.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datn_project.models.Classes;
import com.example.datn_project.models.Health;
import com.example.datn_project.models.News;
import com.example.datn_project.models.User;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private ApiService apiService;

    public MainRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<User> getUsers(String auth) {
        MutableLiveData<User> data = new MutableLiveData<>();
        apiService.getInfoUser("Bearer " + auth).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<List<News>> getListNews() {
        MutableLiveData<List<News>> data = new MutableLiveData<>();
        apiService.getListNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Classes> getListClasses(int id) {
        MutableLiveData<Classes> data = new MutableLiveData<>();
        apiService.getClasses(id).enqueue(new Callback<Classes>() {
            @Override
            public void onResponse(@NonNull Call<Classes> call, @NonNull Response<Classes> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Classes> call, @NonNull Throwable t) {

            }
        });
        return data;
    }
}
