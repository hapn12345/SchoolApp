package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datn_project.databinding.ItemAlbumBinding;
import com.example.datn_project.models.Album;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private List<Album> albumList = new ArrayList<>();
    private OnClickItemAlbumListener mListener;

    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
        notifyItemRangeChanged(0, albumList.size());
    }

    public void setListener(OnClickItemAlbumListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public AlbumViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new AlbumViewHolder(ItemAlbumBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumViewHolder holder, int position) {
        Album album = albumList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(album.getImages().get(0))
                .into(holder.binding.imgAlbum);

        holder.binding.textViewAlbumName.setText(album.getName());

        holder.itemView.setOnClickListener(v -> {
            mListener.onClick(album, position);
        });
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder {
        public ItemAlbumBinding binding;

        public AlbumViewHolder(@NonNull ItemAlbumBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnClickItemAlbumListener {
        void onClick(Album album, int pos);
    }
}
