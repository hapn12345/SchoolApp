package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datn_project.databinding.ItemAlbumBinding;
import com.example.datn_project.databinding.ItemAlbumTeacherBinding;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class TeacherAlbumAdapter extends RecyclerView.Adapter<TeacherAlbumAdapter.TeacherAlbumViewHolder> {
    private List<String> mListImage;
    private OnListener onListener;

    public void setOnListener(TeacherAlbumAdapter.OnListener onListener) {
        this.onListener = onListener;
    }

    public void setData(List<String> mListImage) {
        this.mListImage = mListImage;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeacherAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherAlbumViewHolder(ItemAlbumTeacherBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAlbumViewHolder holder, int position) {
        String storageReference = mListImage.get(position);
        holder.mBinding.textViewAlbumName.setText(storageReference);
        holder.itemView.setOnClickListener(v -> onListener.setOnClick(position));

    }

    @Override
    public int getItemCount() {
        if (mListImage != null) {
            return mListImage.size();
        }
        return 0;
    }

    public static class TeacherAlbumViewHolder extends RecyclerView.ViewHolder {
        ItemAlbumTeacherBinding mBinding;

        public TeacherAlbumViewHolder(ItemAlbumTeacherBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }

    public interface OnListener {
        void setOnClick(int position);
    }
}
