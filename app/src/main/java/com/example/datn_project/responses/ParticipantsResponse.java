package com.example.datn_project.responses;

import com.example.datn_project.models.Student;

public class ParticipantsResponse {
    private String id;
    private String activityID;
    private Student studentID;
    private String classID;

    public ParticipantsResponse(String id, String activityID, Student student, String classID) {
        this.id = id;
        this.activityID = activityID;
        this.studentID = student;
        this.classID = classID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public Student getStudent() {
        return studentID;
    }

    public void setStudent(Student student) {
        this.studentID = student;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }
}
