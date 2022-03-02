package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_project.databinding.ItemTeacherLeavedayBinding;
import com.example.datn_project.models.LeaveDay;
import com.example.datn_project.responses.LeaveDayResponse;

import java.util.List;

public class LeaveDayAdapter extends RecyclerView.Adapter<LeaveDayAdapter.LeaveDayViewHolder> {
    private List<LeaveDayResponse> mListLeaveDay;

    public void setData(List<LeaveDayResponse> mListLeaveDay) {
        this.mListLeaveDay = mListLeaveDay;
        notifyItemChanged(0, mListLeaveDay.size());
    }

    @NonNull
    @Override
    public LeaveDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LeaveDayViewHolder(ItemTeacherLeavedayBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LeaveDayViewHolder holder, int position) {
        LeaveDayResponse leaveDay = mListLeaveDay.get(position);
        String name = leaveDay.getStudentID().getFirstName() + " " + leaveDay.getStudentID().getLastName();
        holder.mBinding.txtName.setText(name);
        String[] firstTime = leaveDay.getFirstDay().split("T");
        holder.mBinding.txtTimeFirstDay.setText(firstTime[0] + " " + firstTime[1]);

        if (!leaveDay.getLastDay().equals("")) {
            String[] lastTime = leaveDay.getLastDay().split("T");
            holder.mBinding.txtTimeLastDay.setText(lastTime[0] + " " + lastTime[1]);
        } else {
            holder.mBinding.txtTitleTime.setVisibility(View.GONE);
            holder.mBinding.txtTimeLastDay.setVisibility(View.GONE);
        }

        String dayOff = leaveDay.getDaysOff() + "";
        holder.mBinding.txtDayOff.setText(dayOff);
        holder.mBinding.txtContent.setText(leaveDay.getContent());
    }

    @Override
    public int getItemCount() {
        if (mListLeaveDay != null) {
            return mListLeaveDay.size();
        }
        return 0;
    }

    public static class LeaveDayViewHolder extends RecyclerView.ViewHolder {
        ItemTeacherLeavedayBinding mBinding;

        public LeaveDayViewHolder(@NonNull ItemTeacherLeavedayBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}
