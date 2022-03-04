package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_project.databinding.ItemScheduleBinding;
import com.example.datn_project.models.Activity;
import com.example.datn_project.models.Schedule;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private List<Schedule> mListSchedule;
    private OnSchedule onSchedule;

    public void setListener(OnSchedule onSchedule) {
        this.onSchedule = onSchedule;
    }

    public void setData(List<Schedule> mListSchedule) {
        this.mListSchedule = mListSchedule;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScheduleViewHolder(
                ItemScheduleBinding.inflate(LayoutInflater.from(
                        parent.getContext()),
                        parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule schedule = mListSchedule.get(position);

        if (schedule.isExpandable()) {
            holder.mBinding.lnDescription.setVisibility(View.VISIBLE);
        } else {
            holder.mBinding.lnDescription.setVisibility(View.GONE);
        }

        String period = "Tiết " + (position + 1);
        holder.mBinding.txtTitlePeriod.setText(period);
        holder.mBinding.txtStartAt.setText(schedule.getStartAt());
        holder.mBinding.txtEndAt.setText(schedule.getEndAt());
        holder.mBinding.txtContent.setText(schedule.getContent());
        holder.itemView.setOnClickListener(v -> {
            onSchedule.onClick(position, schedule);
        });
    }

    @Override
    public int getItemCount() {
        if (mListSchedule != null) {
            return mListSchedule.size();
        }
        return 0;
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        ItemScheduleBinding mBinding;

        public ScheduleViewHolder(@NonNull ItemScheduleBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }

    public interface OnSchedule {
        void onClick(int position, Schedule schedule);
    }
}
