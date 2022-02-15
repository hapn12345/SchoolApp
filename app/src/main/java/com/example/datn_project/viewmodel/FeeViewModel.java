package com.example.datn_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.Fee;
import com.example.datn_project.repositories.FeeRepository;

import java.util.List;

public class FeeViewModel extends ViewModel {
    private final FeeRepository repository;

    public FeeViewModel() {
        repository = new FeeRepository();
    }

    public LiveData<List<Fee>> getFee(int id) {
        return repository.getFee(id);
    }

}
