package com.example.datn_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.Activity;
import com.example.datn_project.models.Participant;
import com.example.datn_project.repositories.ActivitiesRepository;

import java.util.List;

public class ActivitiesViewModel extends ViewModel {
    private final ActivitiesRepository activitiesRepository;

    public ActivitiesViewModel() {
        activitiesRepository = new ActivitiesRepository();
    }

    public LiveData<Activities> getListActivities() {
        return activitiesRepository.getActivities();
    }

    public LiveData<Participant> getParticipants(int id) {
        return activitiesRepository.getParticipants(id);
    }
}
