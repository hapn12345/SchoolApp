package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datn_project.databinding.ItemImageBinding;
import com.example.datn_project.models.Album;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<String> mListImage = new ArrayList<>();
    private OnClickItemAlbumListener mListener;

    public void setImageList(List<String> mListAlbum) {
        this.mListImage = mListAlbum;
        notifyItemRangeChanged(0, mListAlbum.size());
    }

    public void setListener(OnClickItemAlbumListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new ImageViewHolder(ItemImageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String image = mListImage.get(position);

        Glide.with(holder.itemView.getContext())
                .load(image)
                .into(holder.mBinding.imgPhoto);

        holder.itemView.setOnClickListener(v -> {
            mListener.onClick(image, position);
        });
    }

    @Override
    public int getItemCount() {
        return mListImage.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        public ItemImageBinding mBinding;

        public ImageViewHolder(@NonNull ItemImageBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }

    public interface OnClickItemAlbumListener {
        void onClick(String image, int pos);
    }
}
