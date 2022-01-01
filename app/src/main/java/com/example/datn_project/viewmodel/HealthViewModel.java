package com.example.datn_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_project.models.Health;
import com.example.datn_project.repositories.HealthRepository;
import com.example.datn_project.responses.HealthResponse;

import java.util.List;

public class HealthViewModel extends ViewModel {
    private HealthRepository healthRepository;

    public HealthViewModel() {
        healthRepository = new HealthRepository();
    }

    public LiveData<List<Health>> getListHealth() {
        return healthRepository.getHealth();
    }
}
