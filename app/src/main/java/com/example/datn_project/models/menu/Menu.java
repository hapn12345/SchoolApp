package com.example.datn_project.models.menu;

import java.io.Serializable;

public class Menu implements Serializable {
    private int id;
    private String content;

    public Menu(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
