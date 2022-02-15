package com.example.datn_project.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class _Notification {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String title;
    private String body;

    public _Notification() {
    }

    public _Notification(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
