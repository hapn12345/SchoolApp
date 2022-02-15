package com.example.datn_project.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.Album;
import com.example.datn_project.repositories.AlbumRepository;

import java.util.List;

public class AlbumViewModel extends ViewModel {
    private AlbumRepository albumRepository;

    public AlbumViewModel() {
        albumRepository = new AlbumRepository();
    }

    public LiveData<List<Album>> getListAlbum() {
        return albumRepository.getListAlbum();
    }

}
