package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_project.databinding.ItemFeeBinding;
import com.example.datn_project.models.Fee;

import java.util.List;

public class FeeAdapter extends RecyclerView.Adapter<FeeAdapter.FeeViewHolder> {
    private List<Fee> mListFees;

    public void setData(List<Fee> mListFees) {
        this.mListFees = mListFees;
        notifyItemRangeChanged(0, mListFees.size());
    }

    @NonNull
    @Override
    public FeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FeeViewHolder(ItemFeeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FeeViewHolder holder, int position) {
        Fee fee = mListFees.get(position);

        holder.mBinding.txtMonth.setText(fee.getMonth());
        holder.mBinding.txtStatus.setText(fee.getStatus());
        String name = fee.getStudentID().getFirstName() + " " + fee.getStudentID().getLastName();
        holder.mBinding.txtStudent.setText(name);
        String fee1 = fee.getFee() + "";
        holder.mBinding.txtFee.setText(fee1);
    }

    @Override
    public int getItemCount() {
        if (mListFees != null) {
            return mListFees.size();
        }
        return 0;
    }

    public static class FeeViewHolder extends RecyclerView.ViewHolder {
        ItemFeeBinding mBinding;

        public FeeViewHolder(ItemFeeBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}
