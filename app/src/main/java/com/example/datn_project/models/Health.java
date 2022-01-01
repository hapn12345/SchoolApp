package com.example.datn_project.models;

public class Health {
    private int id;
    private String weight;
    private String height;
    private String note;
    private String studentID;
    private String checkedAt;

    public Health(int id, String weight, String height, String note, String studentID, String checkedAt) {
        this.id = id;
        this.weight = weight;
        this.height = height;
        this.note = note;
        this.studentID = studentID;
        this.checkedAt = checkedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(String checkedAt) {
        this.checkedAt = checkedAt;
    }
}
