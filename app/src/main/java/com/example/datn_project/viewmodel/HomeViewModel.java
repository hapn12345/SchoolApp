package com.example.datn_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_project.models.Classes;
import com.example.datn_project.models.Health;
import com.example.datn_project.models.News;
import com.example.datn_project.models.User;
import com.example.datn_project.repositories.HealthRepository;
import com.example.datn_project.repositories.MainRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private MainRepository mainRepository;

    public HomeViewModel() {
        mainRepository = new MainRepository();
    }

    public LiveData<User> getUsers(String auth) {
        return mainRepository.getUsers(auth);
    }

    public LiveData<Classes> getClasses(int id) {
        return mainRepository.getListClasses(id);
    }

    public LiveData<List<News>> getListNews() {
        return mainRepository.getListNews();
    }
}
