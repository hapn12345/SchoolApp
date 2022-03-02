package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_project.databinding.ItemFeeBinding;
import com.example.datn_project.models.Fee;
import com.example.datn_project.models.FeeRequest;

import java.util.List;

public class FeeAdapter extends RecyclerView.Adapter<FeeAdapter.FeeViewHolder> {
    private List<Fee> mListFees;
    private boolean isTeacher = false;
    private boolean isUpdate = false;
    private OnListener onListener;

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public void setOnListener(OnListener onListener) {
        this.onListener = onListener;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

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
        FeeRequest feeRequest = new FeeRequest(fee.getId(), fee.getFee(), fee.getMonth(), "YET", String.valueOf(fee.getStudentID().getId()), fee.getClassID());
        holder.mBinding.txtMonth.setText(fee.getMonth());
        holder.mBinding.txtStatus.setText(fee.getStatus());
        String name = fee.getStudentID().getFirstName() + " " + fee.getStudentID().getLastName();
        holder.mBinding.txtStudent.setText(name);
        String fee1 = fee.getFee() + "";
        holder.mBinding.txtFee.setText(fee1);
        if (!isTeacher) {
            holder.mBinding.btnApplyFee.setVisibility(View.GONE);
        } else {
            holder.mBinding.btnApplyFee.setVisibility(View.VISIBLE);
        }
        holder.mBinding.btnApplyFee.setOnClickListener(v -> {
            onListener.onApply(feeRequest, position);
        });
        holder.itemView.setOnClickListener(v -> onListener.onClick(position));
        if (fee.getStatus().equals("NOT YET")) {
            holder.mBinding.btnApplyFee.setText("Đóng Học Phí");
            if (!isUpdate) {
                holder.mBinding.btnApplyFee.setText("Đóng Học Phí");
                holder.mBinding.txtStatus.setText("NOT YET");
            } else {
                holder.mBinding.btnApplyFee.setText("Đã Đóng Học Phí");
                holder.mBinding.txtStatus.setText("YET");
            }
        } else {
            holder.mBinding.btnApplyFee.setText("Đã Đóng Học Phí");
        }

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

    public interface OnListener {
        void onApply(FeeRequest fee, int position);

        void onClick(int position);
    }
}
