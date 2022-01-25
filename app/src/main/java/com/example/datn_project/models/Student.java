package com.example.datn_project.models;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private int classID;

    public Student(int id, String firstName, String lastName, int classID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classID = classID;
    }

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
