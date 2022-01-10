package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.datn_project.R;
import com.example.datn_project.Utils.AppUtilKt;
import com.example.datn_project.adapters.ViewpagerAdapter;
import com.example.datn_project.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
    }

    private void initView() {
        ViewpagerAdapter viewpagerAdapter = new ViewpagerAdapter(this);
        mBinding.viewpager2.setOffscreenPageLimit(3);
        mBinding.viewpager2.setAdapter(viewpagerAdapter);
        mBinding.viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mBinding.navigationView.getMenu().findItem(R.id.bot_home).setChecked(true);
                        break;
                    case 1:
                        mBinding.navigationView.getMenu().findItem(R.id.bot_message).setChecked(true);
                        break;
                    case 2:
                        mBinding.navigationView.getMenu().findItem(R.id.bot_news).setChecked(true);
                        break;
                    case 3:
                        mBinding.navigationView.getMenu().findItem(R.id.bot_notification).setChecked(true);
                        break;
                    case 4:
                        mBinding.navigationView.getMenu().findItem(R.id.bot_account).setChecked(true);
                        break;
                }
            }
        });
        mBinding.navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bot_home:
                        mBinding.viewpager2.setCurrentItem(0);
                        break;
                    case R.id.bot_message:
                        mBinding.viewpager2.setCurrentItem(1);
                        break;
                    case R.id.bot_news:
                        mBinding.viewpager2.setCurrentItem(2);
                        break;
                    case R.id.bot_notification:
                        mBinding.viewpager2.setCurrentItem(3);
                        break;
                    case R.id.bot_account:
                        mBinding.viewpager2.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }
}