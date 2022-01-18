package com.example.datn_project.models;

import java.util.List;

public class Activities {
    private List<Activity> activities;
    private List<Participant> joinedActivities;

    public Activities(List<Activity> activities, List<Participant> joinedActivities) {
        this.activities = activities;
        this.joinedActivities = joinedActivities;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Participant> getJoinedActivities() {
        return joinedActivities;
    }

    public void setJoinedActivities(List<Participant> joinedActivities) {
        this.joinedActivities = joinedActivities;
    }
}
