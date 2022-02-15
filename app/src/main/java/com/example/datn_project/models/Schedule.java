package com.example.datn_project.models;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private String startAt;
    private String endAt;
    private String content;
    @Expose
    private boolean isExpandable;

    public boolean isExpandable() {
        return isExpandable;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public Schedule(String startAt, String endAt, String content) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.content = content;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
