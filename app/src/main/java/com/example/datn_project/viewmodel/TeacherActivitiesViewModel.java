package com.example.datn_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.Participant;
import com.example.datn_project.repositories.ActivitiesRepository;
import com.example.datn_project.repositories.TeacherActivitiesRepository;
import com.example.datn_project.responses.ParticipantsResponse;

import java.util.List;

public class TeacherActivitiesViewModel extends ViewModel {
    private final TeacherActivitiesRepository teacherActivitiesRepository;

    public TeacherActivitiesViewModel() {
        teacherActivitiesRepository = new TeacherActivitiesRepository();
    }

    public LiveData<List<ParticipantsResponse>> getStudentParticipants() {
        return teacherActivitiesRepository.getStudentParticipants();
    }
}
