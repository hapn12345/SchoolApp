package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.datn_project.adapters.ImageAdapter;
import com.example.datn_project.databinding.ActivityImageBinding;
import com.example.datn_project.models.Album;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity implements ImageAdapter.OnClickItemAlbumListener {
    private ActivityImageBinding mBinding;
    private ImageAdapter imageAdapter;
    private List<String> mListImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityImageBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        mListImage = new ArrayList<>();
        imageAdapter = new ImageAdapter();
        imageAdapter.setListener(this);
        mBinding.rclPhoto.setAdapter(imageAdapter);
        mBinding.rclPhoto.setLayoutManager(new GridLayoutManager(this, 3));
        getData();
    }

    private void getData() {
        Album album = (Album) getIntent().getSerializableExtra("album");
        mListImage.addAll(album.getImages());
        imageAdapter.setImageList(mListImage);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(String image, int pos) {
        Intent intent = new Intent(this, PhotoDetailActivity.class);
        intent.putExtra("image", image);
        startActivity(intent);
    }
}