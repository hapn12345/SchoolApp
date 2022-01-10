package com.example.datn_project.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.datn_project.MyConstants;
import com.example.datn_project.R;
import com.example.datn_project.activities.NewsDetailActivity;
import com.example.datn_project.adapters.NewsAdapter;
import com.example.datn_project.databinding.FragmentHomeBinding;
import com.example.datn_project.databinding.FragmentNewsBinding;
import com.example.datn_project.models.News;
import com.example.datn_project.viewmodel.HomeViewModel;

public class NewsFragment extends Fragment implements NewsAdapter.OnNewsListener {
    private FragmentNewsBinding mBinding;
    private NewsAdapter newsAdapter;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentNewsBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        newsAdapter = new NewsAdapter();
        getData();
    }

    private void getData() {
        homeViewModel.getListNews().observe(getViewLifecycleOwner(), listNews -> {
            newsAdapter.setData(listNews);
            mBinding.layoutNews.rclNews.setAdapter(newsAdapter);
            newsAdapter.setListener(this);
            mBinding.layoutNews.txtViewAll.setVisibility(View.GONE);
        });
    }

    @Override
    public void onClick(News news) {
        Intent intent = new Intent(getContext(), NewsDetailActivity.class);
        intent.putExtra(MyConstants.KEY_NEWS, news);
        startActivity(intent);
    }
}