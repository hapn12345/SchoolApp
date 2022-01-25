package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_project.databinding.ItemHealthBinding;
import com.example.datn_project.models.Health;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class HealthAdapter extends RecyclerView.Adapter<HealthAdapter.HealthViewHolder> {
    private ArrayList<Health> mListHealth;

    public void setData(ArrayList<Health> mListHealth) {
        this.mListHealth = mListHealth;
    }

    @NonNull
    @Override
    public HealthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HealthViewHolder(
                ItemHealthBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull HealthViewHolder holder, int position) {
        Health health = mListHealth.get(position);
        String height = health.getHeight() + " Cm";
        holder.mBinding.txtHeight.setText(height);

        String[] time = health.getCheckedAt().split(" ");
        holder.mBinding.txtTime.setText(time[0]);

        holder.mBinding.txtDes.setText(health.getNote());

        String weight = health.getWeight() + " Kg";
        holder.mBinding.txtWeight.setText(weight);

        int height_bmi = Integer.parseInt(health.getHeight()) / 100;
        int bmi = Integer.parseInt(health.getWeight()) / (height_bmi * height_bmi);
        holder.mBinding.txtBmi.setText(bmi + " ");
    }

    @Override
    public int getItemCount() {
        if (mListHealth != null) {
            return mListHealth.size();
        }
        return 0;
    }

    public static class HealthViewHolder extends RecyclerView.ViewHolder {
        ItemHealthBinding mBinding;

        public HealthViewHolder(ItemHealthBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}
