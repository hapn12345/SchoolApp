package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.datn_project.databinding.ActivityMenuBinding;
import com.example.datn_project.databinding.ActivityMenuStudentBinding;
import com.example.datn_project.models.Student;

public class MenuStudentActivity extends AppCompatActivity {
    private ActivityMenuStudentBinding mBinding;
    private int classId;
    private int classId1 = 0;
    private Student student;
    private Student student1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMenuStudentBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        initToolbar();
        getData();
    }

    private void getData() {
        classId = getIntent().getIntExtra("key_class", -1);
        classId1 = getIntent().getIntExtra("key_class_1", 0);
        student = (Student) getIntent().getSerializableExtra("key_student");
        student1 = (Student) getIntent().getSerializableExtra("key_student_1");
        String name = student.getFirstName() + " " + student.getLastName();
        mBinding.txtNameStudent1.setText(name);
        if (classId1 != 0) {
            String name1 = student1.getFirstName() + " " + student1.getLastName();
            mBinding.txtNameStudent2.setText(name1);
            mBinding.rlt2.setVisibility(View.VISIBLE);
        } else {
            mBinding.rlt2.setVisibility(View.GONE);
        }
        mBinding.rlt1.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
            intent.putExtra("key_class", classId);
            startActivity(intent);

        });
        mBinding.rlt2.setOnClickListener(v -> {
            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("key_class", classId1);
            startActivity(intent);
        });
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}