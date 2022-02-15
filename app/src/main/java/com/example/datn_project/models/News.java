package com.example.datn_project.models;

import java.io.Serializable;

public class News implements Serializable {
    private int id;
    private String content;
    private String description;
    private String thumbnail;
    private String releaseDate;

    public News(int id, String content, String description, String thumbnail, String releaseDate) {
        this.id = id;
        this.content = content;
        this.description = description;
        this.thumbnail = thumbnail;
        this.releaseDate = releaseDate;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
