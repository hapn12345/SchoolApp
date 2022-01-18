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
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.datn_project.MyConstants;
import com.example.datn_project.R;
import com.example.datn_project.activities.ActivitiesActivity;
import com.example.datn_project.activities.HealthActivity;
import com.example.datn_project.activities.MainActivity;
import com.example.datn_project.activities.NewsDetailActivity;
import com.example.datn_project.adapters.NewsAdapter;
import com.example.datn_project.databinding.FragmentHomeBinding;
import com.example.datn_project.databinding.LayoutNewsBinding;
import com.example.datn_project.models.News;
import com.example.datn_project.utilities.SharedPreferenceUtil;
import com.example.datn_project.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements NewsAdapter.OnNewsListener {
    private FragmentHomeBinding mBinding;
    private HomeViewModel viewModel;
    private NewsAdapter newsAdapter;

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
        onListener();
    }

    private void getData() {
        viewModel.getUsers(SharedPreferenceUtil.readAccessToken(getContext())).observe(getViewLifecycleOwner(), user -> {
            if (user != null) {
                Log.e("onViewCreated: ", user.getEmail());
            }
        });
        viewModel.getListNews().observe(getViewLifecycleOwner(), listNews -> {
            newsAdapter.setData(listNews);
            mBinding.layoutNews.rclNews.setAdapter(newsAdapter);
            newsAdapter.setListener(this);
        });
    }

    private void gotoNews() {
        if ((MainActivity) getActivity() != null) {
            ((MainActivity) getActivity()).mBinding.viewpager2.setCurrentItem(2);
        }
    }

    private void onListener() {
        mBinding.ctlHealth.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), HealthActivity.class));
        });
        mBinding.layoutNews.txtViewAll.setOnClickListener(v -> {
            gotoNews();
        });
        mBinding.ctlActivity.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), ActivitiesActivity.class));
        });
    }

    @Override
    public void onClick(News news) {
        Intent intent = new Intent(getContext(), NewsDetailActivity.class);
        intent.putExtra(MyConstants.KEY_NEWS, news);
        startActivity(intent);
    }
}