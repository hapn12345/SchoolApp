package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_project.database._Notification;
import com.example.datn_project.databinding.ItemNotificationBinding;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
    private List<_Notification> mListNotification;

    public void setData(List<_Notification> mListNotification) {
        this.mListNotification = mListNotification;
        notifyItemRangeChanged(0, mListNotification.size());
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(ItemNotificationBinding.inflate(LayoutInflater.from(
                parent.getContext()),
                parent,
                false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        _Notification notification = mListNotification.get(position);
        holder.mBinding.txtTitle.setText(notification.getTitle());
        holder.mBinding.txtBody.setText(notification.getBody());
    }

    @Override
    public int getItemCount() {
        if (mListNotification != null) {
            return mListNotification.size();
        }
        return 0;
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        ItemNotificationBinding mBinding;

        public NotificationViewHolder(@NonNull ItemNotificationBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}
