package com.example.datn_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_project.models.Student;
import com.example.datn_project.repositories.TeacherFeeRepository;

import java.util.List;

public class TeacherFeeViewModel extends ViewModel {
    private final TeacherFeeRepository teacherFeeRepository;

    public TeacherFeeViewModel() {
        teacherFeeRepository = new TeacherFeeRepository();
    }

    public LiveData<List<Student>> getListStudent() {
        return teacherFeeRepository.getListStudent();
    }
}
