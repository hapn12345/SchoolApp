package com.example.datn_project.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.datn_project.R;
import com.example.datn_project.activities.dialog.AddPhotoDialog;
import com.example.datn_project.adapters.TeacherAlbumAdapter;
import com.example.datn_project.databinding.ActivityTeacherAlbumBinding;
import com.example.datn_project.viewmodel.ActivitiesViewModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TeacherAlbumActivity extends AppCompatActivity implements TeacherAlbumAdapter.OnListener, AddPhotoDialog.OnClick {
    private ActivityTeacherAlbumBinding mBinding;
    private StorageReference mStorage = FirebaseStorage.getInstance().getReference();
    private int classId;
    private TeacherAlbumAdapter adapter;
    private List<String> mListData;
    private String activityName;

    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    if (result.getData() != null) {
                        if (result.getData().getClipData() != null) {
                            int totalItemsSelected = result.getData().getClipData().getItemCount();
                            for (int i = 0; i < totalItemsSelected; i++) {

                                Uri fileUri = result.getData().getClipData().getItemAt(i).getUri();

                                String fileName = getFileName(fileUri);

                                StorageReference fileToUpload = mStorage.child(activityName).child(fileName);

                                fileToUpload.putFile(fileUri).addOnSuccessListener(taskSnapshot -> {
                                    Toast.makeText(this, "Upload ảnh thành công", Toast.LENGTH_LONG).show();
                                });

                            }
                        }

                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityTeacherAlbumBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        classId = getIntent().getIntExtra("key_class", -1);
        adapter = new TeacherAlbumAdapter();
        mListData = new ArrayList<>();

        initToolbar();
        getData();
        mBinding.rclAlbum.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mBinding.rclAlbum.setAdapter(adapter);
        adapter.setOnListener(this);
    }

    private void getData() {
        StorageReference listRef = FirebaseStorage.getInstance().getReference();
        listRef.listAll()
                .addOnSuccessListener(listResult -> {
                    for (int i = 0; i < listResult.getPrefixes().size(); i++) {
                        mListData.add(listResult.getPrefixes().get(i).getName());
                        adapter.setData(mListData);
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Load ảnh không thành công", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.album_menu, menu);
        if(getIntent().getStringExtra("role").equals("teacher")){
            menu.findItem(R.id.add_photo).setVisible(true);
        }else {
            menu.findItem(R.id.add_photo).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.add_photo:
                AddPhotoDialog dialog = new AddPhotoDialog(this);
                dialog.setOnClick(this);
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("Range")
    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void setOnClick(int position) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra("key_child", mListData.get(position));
        startActivity(intent);
    }

    @Override
    public void onClickAdd(String activityName) {
        this.activityName = activityName;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        launcher.launch(Intent.createChooser(intent, "Select Picture"));
    }
}