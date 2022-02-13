package com.example.datn_project.models;

public class Fee {
    private String id;
    private int fee;
    private String month;
    private String status;
    private Student studentID;
    private String classID;

    public Fee(String id, int fee, String month, String status, Student student, String classID) {
        this.id = id;
        this.fee = fee;
        this.month = month;
        this.status = status;
        this.studentID = student;
        this.classID = classID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudentID() {
        return studentID;
    }

    public void setStudentID(Student studentID) {
        this.studentID = studentID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }
}
