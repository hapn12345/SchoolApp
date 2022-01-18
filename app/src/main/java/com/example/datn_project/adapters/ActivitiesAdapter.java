package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_project.databinding.ItemActivitiesBinding;
import com.example.datn_project.models.Activity;

import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivitiesViewHolder> {
    private List<Activity> mListActivities;
    private OnActivitiesListener onActivitiesListener;

    public void setData(List<Activity> mListActivities) {
        this.mListActivities = mListActivities;
    }

    public void setListener(OnActivitiesListener onActivitiesListener) {
        this.onActivitiesListener = onActivitiesListener;
    }

    @NonNull
    @Override
    public ActivitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActivitiesViewHolder(
                ItemActivitiesBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActivitiesViewHolder holder, int position) {
        Activity activity = mListActivities.get(position);

        holder.mBinding.txtTitleActivity.setText(activity.getName());
        holder.mBinding.txtAddress.setText(activity.getAddress());

        if (activity.isExpandable()) {
            holder.mBinding.itemExpandable.setVisibility(View.VISIBLE);
        } else {
            holder.mBinding.itemExpandable.setVisibility(View.GONE);
        }

        holder.mBinding.cardViewItem.setOnClickListener(v -> {
            onActivitiesListener.onClick(position, activity);
        });


    }

    @Override
    public int getItemCount() {
        if (mListActivities != null) {
            return mListActivities.size();
        }
        return 0;
    }

    public static class ActivitiesViewHolder extends RecyclerView.ViewHolder {
        ItemActivitiesBinding mBinding;

        public ActivitiesViewHolder(ItemActivitiesBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }

    public interface OnActivitiesListener {
        void onClick(int position, Activity activity);
    }
}
