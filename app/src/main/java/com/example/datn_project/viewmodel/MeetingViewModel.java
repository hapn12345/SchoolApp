package com.example.datn_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.Meeting;
import com.example.datn_project.models.Participant;
import com.example.datn_project.repositories.ActivitiesRepository;
import com.example.datn_project.repositories.MeetingRepository;

import java.util.List;

public class MeetingViewModel extends ViewModel {
    private MeetingRepository meetingRepository;

    public MeetingViewModel() {
        meetingRepository = new MeetingRepository();
    }

    public LiveData<List<Meeting>> getMeeting() {
        return meetingRepository.getMeeting();
    }

}
