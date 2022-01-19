package com.example.datn_project.models;

public class Classes {
    private int id;
    private String name;
    private String fee;
    private String schedule;

    public Classes(int id, String name, String fee, String schedule) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.schedule = schedule;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
