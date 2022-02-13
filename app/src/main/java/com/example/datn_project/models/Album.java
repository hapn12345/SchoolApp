package com.example.datn_project.models;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable {
    private String id;
    private String name;
    private List<String> images;
    private String classID;

    public Album(String id, String name, List<String> images, String classID) {
        this.id = id;
        this.name = name;
        this.images = images;
        this.classID = classID;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }
}
