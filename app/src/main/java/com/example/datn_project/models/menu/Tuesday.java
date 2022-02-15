package com.example.datn_project.models.menu;

import java.io.Serializable;

public class Tuesday implements Serializable {
    private Breakfast breakfast;
    private MorningSupplement morning_supplement;
    private Lunch lunch;
    private Tea tea;

    public Tuesday(Breakfast breakfast, MorningSupplement morning_supplement, Lunch lunch, Tea tea) {
        this.breakfast = breakfast;
        this.morning_supplement = morning_supplement;
        this.lunch = lunch;
        this.tea = tea;
    }

    public Tea getTea() {
        return tea;
    }

    public void setTea(Tea tea) {
        this.tea = tea;
    }

    public Breakfast getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Breakfast breakfast) {
        this.breakfast = breakfast;
    }

    public MorningSupplement getMorning_supplement() {
        return morning_supplement;
    }

    public void setMorning_supplement(MorningSupplement morning_supplement) {
        this.morning_supplement = morning_supplement;
    }

    public Lunch getLunch() {
        return lunch;
    }

    public void setLunch(Lunch lunch) {
        this.lunch = lunch;
    }
}
