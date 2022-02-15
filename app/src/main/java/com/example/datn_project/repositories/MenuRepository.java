package com.example.datn_project.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.menu.Menu;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuRepository {
    private ApiService apiService;

    public MenuRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<Menu> getMenu(int id) {
        MutableLiveData<Menu> data = new MutableLiveData<>();
        apiService.getMenu(id).enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(@NonNull Call<Menu> call, @NonNull Response<Menu> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Menu> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

}
