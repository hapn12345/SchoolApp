package com.example.datn_project.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.datn_project.fragments.AccountFragment;
import com.example.datn_project.fragments.ChatFragment;
import com.example.datn_project.fragments.HomeFragment;
import com.example.datn_project.fragments.NewsFragment;
import com.example.datn_project.fragments.NotificationsFragment;

public class ViewpagerAdapter extends FragmentStateAdapter {
    public ViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ChatFragment();
            case 2:
                return new NewsFragment();
            case 3:
                return new NotificationsFragment();
            case 4:
                return new AccountFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
