package com.example.datn_project.models.menu;

import java.io.Serializable;
import java.util.List;

public class MorningSupplement implements Serializable {
    private String startAt;
    private String endAt;
    private List<String> dish;

    public MorningSupplement(String startAt, String endAt, List<String> dish) {
        this.startAt = startAt;
        this.endAt = endAt;
        this.dish = dish;
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

    public List<String> getDish() {
        return dish;
    }

    public void setDish(List<String> dish) {
        this.dish = dish;
    }
}
