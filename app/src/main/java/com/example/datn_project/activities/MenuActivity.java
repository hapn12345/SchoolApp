package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.datn_project.databinding.ActivityMenuBinding;
import com.example.datn_project.models.menu.Menu;
import com.example.datn_project.models.menu.StringMenu;
import com.example.datn_project.models.menu.Tea;
import com.example.datn_project.viewmodel.MenuViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private ActivityMenuBinding mBinding;
    private MenuViewModel menuViewModel;
    private int classId;
    private boolean isShowItem = true;
    private boolean isShowItem1 = true;
    private boolean isShowItem2 = true;
    private boolean isShowItem3 = true;
    private boolean isShowItem4 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        Gson gson = new Gson();
        initToolbar();
        menuViewModel = new ViewModelProvider(this).get(MenuViewModel.class);
        classId = getIntent().getIntExtra("key_class", -1);
        menuViewModel.getMenu(classId).observe(this, new Observer<Menu>() {
            @Override
            public void onChanged(Menu menu) {
                 String jsonString = menu.getContent();
                StringMenu fooFromJson = gson.fromJson(jsonString, StringMenu.class);
                onListener(fooFromJson);
            }
        });
    }

    private void onListener(StringMenu fooFromJson) {
        mBinding.cardViewItem.setOnClickListener(v -> {
            if (isShowItem) {
                mBinding.itemExpandable.setVisibility(View.VISIBLE);
                isShowItem = false;
            } else {
                mBinding.itemExpandable.setVisibility(View.GONE);
                isShowItem = true;
            }
        });
        mBinding.cardViewItem1.setOnClickListener(v -> {
            if (isShowItem1) {
                mBinding.itemExpandable1.setVisibility(View.VISIBLE);
                isShowItem1 = false;
            } else {
                mBinding.itemExpandable1.setVisibility(View.GONE);
                isShowItem1 = true;
            }
        });
        mBinding.cardViewItem2.setOnClickListener(v -> {
            if (isShowItem2) {
                mBinding.itemExpandable2.setVisibility(View.VISIBLE);
                isShowItem2 = false;
            } else {
                mBinding.itemExpandable2.setVisibility(View.GONE);
                isShowItem2 = true;
            }
        });
        mBinding.cardViewItem3.setOnClickListener(v -> {
            if (isShowItem3) {
                mBinding.itemExpandable3.setVisibility(View.VISIBLE);
                isShowItem3 = false;
            } else {
                mBinding.itemExpandable3.setVisibility(View.GONE);
                isShowItem3 = true;
            }
        });
        mBinding.cardViewItem4.setOnClickListener(v -> {
            if (isShowItem4) {
                mBinding.itemExpandable4.setVisibility(View.VISIBLE);
                isShowItem4 = false;
            } else {
                mBinding.itemExpandable4.setVisibility(View.GONE);
                isShowItem4 = true;
            }
        });
        //breakfast
        toActivity(mBinding.txtStart, BreakfastActivity.class, fooFromJson, 1);
        toActivity(mBinding.txtStart1, BreakfastActivity.class, fooFromJson, 2);
        toActivity(mBinding.txtStart2, BreakfastActivity.class, fooFromJson, 3);
        toActivity(mBinding.txtStart3, BreakfastActivity.class, fooFromJson, 4);
        toActivity(mBinding.txtStart4, BreakfastActivity.class, fooFromJson, 5);
        //morning supplement
        toActivity(mBinding.txtEnd, MorningSupplementActivity.class, fooFromJson, 1);
        toActivity(mBinding.txtEnd1, MorningSupplementActivity.class, fooFromJson, 2);
        toActivity(mBinding.txtEnd2, MorningSupplementActivity.class, fooFromJson, 3);
        toActivity(mBinding.txtEnd3, MorningSupplementActivity.class, fooFromJson, 4);
        toActivity(mBinding.txtEnd4, MorningSupplementActivity.class, fooFromJson, 5);
        //lunch
        toActivity(mBinding.txtDish, LunchActivity.class, fooFromJson, 1);
        toActivity(mBinding.txtDish1, LunchActivity.class, fooFromJson, 2);
        toActivity(mBinding.txtDish2, LunchActivity.class, fooFromJson, 3);
        toActivity(mBinding.txtDish3, LunchActivity.class, fooFromJson, 4);
        toActivity(mBinding.txtDish4, LunchActivity.class, fooFromJson, 5);
        //tea
        toActivity(mBinding.txtTea, TeaActivity.class, fooFromJson, 1);
        toActivity(mBinding.txtTea1, TeaActivity.class, fooFromJson, 2);
        toActivity(mBinding.txtTea2, TeaActivity.class, fooFromJson, 3);
        toActivity(mBinding.txtTea3, TeaActivity.class, fooFromJson, 4);
        toActivity(mBinding.txtTea4, TeaActivity.class, fooFromJson, 5);
    }

    private void toActivity(TextView textView, Class<?> classes, StringMenu stringMenu, int day) {
        textView.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), classes);
            intent.putExtra("key_menu", (Parcelable) stringMenu);
            intent.putExtra("key_day", day);
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