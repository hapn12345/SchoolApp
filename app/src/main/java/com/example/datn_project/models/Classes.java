package com.example.datn_project.models;

public class Classes {
    private String id;
    private String name;
    private int fee;
    private String schedule;

    public Classes(String id, String name, int fee, String schedule) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.schedule = schedule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
