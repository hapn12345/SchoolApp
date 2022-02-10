package com.example.datn_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.LeaveDay;
import com.example.datn_project.models.Participant;
import com.example.datn_project.repositories.ActivitiesRepository;
import com.example.datn_project.repositories.LeaveDayRepository;

public class LeaveDayViewModel extends ViewModel {
    private final LeaveDayRepository leaveDayRepository;

    public LeaveDayViewModel() {
        leaveDayRepository = new LeaveDayRepository();
    }
}
