package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datn_project.databinding.ItemNewsBinding;
import com.example.datn_project.models.News;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> mListNews;
    private OnNewsListener onNewsListener;

    public void setData(List<News> mListNews) {
        this.mListNews = mListNews;
    }

    public void setListener(OnNewsListener onNewsListener) {
        this.onNewsListener = onNewsListener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = mListNews.get(position);
        holder.mBinding.txtTitle.setText(news.getContent());
        holder.mBinding.txtDescription.setText(news.getDescription());
        Glide.with(holder.itemView.getContext())
                .load(news.getThumbnail())
                .into(holder.mBinding.imgThumbnail);
        holder.itemView.setOnClickListener(v -> {
            onNewsListener.onClick(news);
        });
        try {
            String time = transformLocalDateTimeUTC(news.getReleaseDate());
            holder.mBinding.time.setText(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (mListNews != null) {
            return mListNews.size();
        }
        return 0;
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        ItemNewsBinding mBinding;

        public NewsViewHolder(ItemNewsBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }

    public interface OnNewsListener {
        void onClick(News news);
    }

    private String transformLocalDateTimeUTC(String dateJson) throws ParseException {
        String localDateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss";
        SimpleDateFormat formatInput = new SimpleDateFormat(localDateTimeFormat, Locale.getDefault());

        //Here is will set the time zone
        formatInput.setTimeZone(TimeZone.getTimeZone("UTC-03"));

        String dateFormat = "dd/MM/yyyy";
        SimpleDateFormat formatOutput = new SimpleDateFormat(dateFormat, Locale.getDefault());
        Date date = formatInput.parse(dateJson);
        return formatOutput.format(date);
    }
}
