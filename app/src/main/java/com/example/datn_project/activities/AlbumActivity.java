package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.datn_project.R;
import com.example.datn_project.adapters.AlbumAdapter;
import com.example.datn_project.databinding.ActivityAlbumBinding;
import com.example.datn_project.models.Album;
import com.example.datn_project.viewmodel.AlbumViewModel;

import java.util.List;

public class AlbumActivity extends AppCompatActivity implements AlbumAdapter.OnClickItemAlbumListener {
    private ActivityAlbumBinding mBinding;
    private AlbumViewModel viewModel;
    private AlbumAdapter albumAdapter;
    private int classId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityAlbumBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        albumAdapter = new AlbumAdapter();
        albumAdapter.setListener(this);
        mBinding.rclAlbum.setLayoutManager(new GridLayoutManager(this, 2));
        mBinding.rclAlbum.setAdapter(albumAdapter);
        classId = getIntent().getIntExtra("key_class", -1);
        viewModel = new ViewModelProvider(this).get(AlbumViewModel.class);
        viewModel.getListAlbum().observe(this, albums -> {
            for (int i = 0; i < albums.size(); i++) {
                if (Integer.parseInt(albums.get(i).getClassID()) == classId) {
                    albumAdapter.setAlbumList(albums);
                }
            }
        });
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
    public void onClick(Album album, int pos) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra("album", album);
        startActivity(intent);
    }
}