package com.example.datn_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datn_project.activities.HealthActivity;
import com.example.datn_project.databinding.FragmentHomeBinding;
import com.example.datn_project.utilities.SharedPreferenceUtil;
import com.example.datn_project.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding mBinding;
    private HomeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getUsers(SharedPreferenceUtil.readAccessToken(view.getContext())).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                Log.e("onViewCreated: ", user.getEmail());
            }
        });
        mBinding.ctlHealth.setOnClickListener(v -> {
            startActivity(new Intent(view.getContext(), HealthActivity.class));
        });
    }
}