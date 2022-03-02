package com.example.datn_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_project.models.LeaveDay;
import com.example.datn_project.repositories.TeacherLeaveDayRepository;
import com.example.datn_project.responses.LeaveDayResponse;

import java.util.List;

public class TeacherLeaveDayViewModel extends ViewModel {
    private final TeacherLeaveDayRepository teacherLeaveDayRepository;

    public TeacherLeaveDayViewModel() {
        teacherLeaveDayRepository = new TeacherLeaveDayRepository();
    }

    public LiveData<List<LeaveDayResponse>> getListStudent() {
        return teacherLeaveDayRepository.getListLeaveDay();
    }
}
