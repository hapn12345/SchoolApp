package com.example.datn_project.models;

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private String classID;

    public Student(String id, String firstName, String lastName, String classID) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classID = classID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }
}
