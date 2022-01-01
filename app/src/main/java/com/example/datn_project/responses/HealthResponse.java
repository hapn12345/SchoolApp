package com.example.datn_project.responses;

import com.example.datn_project.models.Health;

import java.util.List;

public class HealthResponse {
    private List<Health> ListHealth;

    public List<Health> getListHealth() {
        return ListHealth;
    }

    public void setListHealth(List<Health> listHealth) {
        ListHealth = listHealth;
    }
}
