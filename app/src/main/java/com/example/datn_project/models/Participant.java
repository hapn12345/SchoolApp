package com.example.datn_project.models;

public class Participant {
    private int id;
    private int activityID;
    private int studentID;
    private int classID;

    public Participant() {
    }

    public Participant(int id, int activityID, int studentID, int classID) {
        this.id = id;
        this.activityID = activityID;
        this.studentID = studentID;
        this.classID = classID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActivityID() {
        return activityID;
    }

    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }
}
