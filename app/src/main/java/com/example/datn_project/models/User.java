package com.example.datn_project.models;


import java.util.List;

public class User {
    private String id;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private List<Classes> mListClasses;
    private List<Student> mListStudents;
    private List<School> mListSchools;

    public User(String id,
                String userName,
                String password,
                String firstName,
                String lastName,
                String phoneNumber,
                String email,
                List<Classes> mListClasses,
                List<Student> mListStudents,
                List<School> mListSchools) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.mListClasses = mListClasses;
        this.mListStudents = mListStudents;
        this.mListSchools = mListSchools;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Classes> getmListClasses() {
        return mListClasses;
    }

    public void setmListClasses(List<Classes> mListClasses) {
        this.mListClasses = mListClasses;
    }

    public List<Student> getmListStudents() {
        return mListStudents;
    }

    public void setmListStudents(List<Student> mListStudents) {
        this.mListStudents = mListStudents;
    }

    public List<School> getmListSchools() {
        return mListSchools;
    }

    public void setmListSchools(List<School> mListSchools) {
        this.mListSchools = mListSchools;
    }
}
