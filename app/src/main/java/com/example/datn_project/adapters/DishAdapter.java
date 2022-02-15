package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_project.databinding.ItemDishBinding;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {
    private List<String> mListDish;

    public void setData(List<String> mListDish) {
        this.mListDish = mListDish;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DishViewHolder(ItemDishBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position) {
        String dish = mListDish.get(position);
        holder.mBinding.txtDish.setText(dish);
    }

    @Override
    public int getItemCount() {
        if (mListDish != null) {
            return mListDish.size();
        }
        return 0;
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder {
        ItemDishBinding mBinding;

        public DishViewHolder(@NonNull ItemDishBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}
