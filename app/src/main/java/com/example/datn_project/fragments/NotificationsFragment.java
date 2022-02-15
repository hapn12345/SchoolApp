package com.example.datn_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datn_project.adapters.NotificationAdapter;
import com.example.datn_project.database.AppDatabase;
import com.example.datn_project.database._Notification;
import com.example.datn_project.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private NotificationAdapter notificationAdapter;
    private FragmentNotificationsBinding mBinding;
    private List<_Notification> mListNotification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentNotificationsBinding.inflate(inflater, container, false);
        notificationAdapter = new NotificationAdapter();
        mListNotification = new ArrayList<>();
        mBinding.rclNotification.setAdapter(notificationAdapter);
        mBinding.rclNotification.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            new Thread(() -> {
                mListNotification = AppDatabase.getInstance(getContext()).getNotification().loadAllNotification();
                getActivity().runOnUiThread(() -> notificationAdapter.setData(mListNotification));
            }).start();
        }
    }
}