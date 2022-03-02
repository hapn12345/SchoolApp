package com.example.datn_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.datn_project.MyConstants;
import com.example.datn_project.R;
import com.example.datn_project.activities.ActivitiesActivity;
import com.example.datn_project.activities.AlbumActivity;
import com.example.datn_project.activities.FeeActivity;
import com.example.datn_project.activities.HealthActivity;
import com.example.datn_project.activities.LeaveDayActivity;
import com.example.datn_project.activities.MainActivity;
import com.example.datn_project.activities.MeetingActivity;
import com.example.datn_project.activities.MenuActivity;
import com.example.datn_project.activities.NewsDetailActivity;
import com.example.datn_project.activities.ScheduleActivity;
import com.example.datn_project.activities.TeacherActivitiesActivity;
import com.example.datn_project.activities.TeacherFeeActivity;
import com.example.datn_project.activities.TeacherHealthActivity;
import com.example.datn_project.activities.TeacherLeaveDayActivity;
import com.example.datn_project.adapters.NewsAdapter;
import com.example.datn_project.databinding.FragmentHomeBinding;
import com.example.datn_project.databinding.LayoutNewsBinding;
import com.example.datn_project.models.Classes;
import com.example.datn_project.models.News;
import com.example.datn_project.models.Schedule;
import com.example.datn_project.utilities.SharedPreferenceUtil;
import com.example.datn_project.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements NewsAdapter.OnNewsListener {
    private FragmentHomeBinding mBinding;
    private HomeViewModel viewModel;
    private NewsAdapter newsAdapter;
    private int studentId;
    private int classId;
    private Classes classes;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        newsAdapter = new NewsAdapter();
        getData();
    }

    private void getData() {
        viewModel.getUsers(SharedPreferenceUtil.readAccessToken(getContext())).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {

                if (user.getStudents().size() != 0) {
                    String name = user.getStudents().get(0).getFirstName() + " " +
                            user.getStudents().get(0).getLastName();
                    mBinding.txtNameChild1.setText(name);
                    studentId = user.getStudents().get(0).getId();
                    classId = user.getStudents().get(0).getClassID();
                    if (user.getClasses().size() < 2) {
                        viewModel.getClasses(user.getStudents().get(0).getClassID()).observe(getViewLifecycleOwner(), classes -> {
                            mBinding.txtClassSchool.setText(classes.getName());
                            this.classes = classes;
                        });
                    }
                    onListener(false);
                } else {
                    classId = user.getClasses().get(0).getId();
                    mBinding.txtClassSchool.setText(user.getClasses().get(0).getName());
                    mBinding.txtNameChild1.setText("");
                    if (user.getClasses().size() < 2) {
                        viewModel.getClasses(user.getClasses().get(0).getId()).observe(getViewLifecycleOwner(), classes -> {
                            mBinding.txtClassSchool.setText(classes.getName());
                            this.classes = classes;
                        });
                    }
                    onListener(true);
                }
            }
        });

        viewModel.getListNews().observe(getViewLifecycleOwner(), listNews -> {
            newsAdapter.setData(listNews);
            mBinding.layoutNews.rclNews.setAdapter(newsAdapter);
            newsAdapter.setListener(this);
        });
    }

    private void gotoNews() {
        if (getActivity() != null) {
            ((MainActivity) getActivity()).mBinding.viewpager2.setCurrentItem(1);
        }
    }

    private void onListener(boolean isTeacher) {
        if (!isTeacher) {
            mBinding.ctlHealth.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), HealthActivity.class);
                intent.putExtra("key_student", studentId);
                intent.putExtra("role", "parent");
                startActivity(intent);
            });
            mBinding.layoutNews.txtViewAll.setOnClickListener(v -> {
                gotoNews();
            });
            mBinding.ctlActivity.setOnClickListener(v -> {
                toActivity(studentId, classId, ActivitiesActivity.class);
            });
            mBinding.ctlLeaveDay.setOnClickListener(v -> {
                toActivity(studentId, classId, LeaveDayActivity.class);
            });
            mBinding.ctlFee.setOnClickListener(v -> {
                toActivity(studentId, classId, FeeActivity.class);
            });
            mBinding.ctlMeeting.setOnClickListener(v -> {
                toActivity(studentId, classId, MeetingActivity.class);
            });
            mBinding.ctlImage.setOnClickListener(v -> {
                toActivity(studentId, classId, AlbumActivity.class);
            });
            mBinding.ctlMenu.setOnClickListener(v -> {
                toActivity(studentId, classId, MenuActivity.class);
            });
            mBinding.ctlSchedule.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), ScheduleActivity.class);
                intent.putExtra("key_schedule", classes);
                startActivity(intent);
            });
        } else {
            mBinding.ctlActivity.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), TeacherActivitiesActivity.class);
                intent.putExtra("key_class", classId);
                startActivity(intent);
            });
            mBinding.ctlMenu.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), MenuActivity.class);
                intent.putExtra("key_class", classId);
                startActivity(intent);
            });
            mBinding.ctlSchedule.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), ScheduleActivity.class);
                intent.putExtra("key_schedule", classes);
                startActivity(intent);
            });
            mBinding.ctlHealth.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), TeacherHealthActivity.class);
                startActivity(intent);
            });
            mBinding.ctlFee.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), TeacherFeeActivity.class);
                intent.putExtra("key_class", classId);
                startActivity(intent);
            });
            mBinding.ctlLeaveDay.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), TeacherLeaveDayActivity.class);
                intent.putExtra("key_class", classId);
                startActivity(intent);
            });
        }
    }

    @Override
    public void onClick(News news) {
        Intent intent = new Intent(getContext(), NewsDetailActivity.class);
        intent.putExtra(MyConstants.KEY_NEWS, news);
        startActivity(intent);
    }

    public void toActivity(int studentId, int classId, Class<?> a) {
        Intent intent = new Intent(getContext(), a);
        intent.putExtra("key_student", studentId);
        intent.putExtra("key_class", classId);
        startActivity(intent);
    }
}