package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_project.databinding.ItemTeacherFeeBinding;
import com.example.datn_project.models.Album;
import com.example.datn_project.models.Student;

import java.util.List;

public class TeacherFeeAdapter extends RecyclerView.Adapter<TeacherFeeAdapter.StudentViewHolder> {
    private List<Student> mListStudent;
    private OnClickItemListener OnClickItemListener;

    public void setOnClickItemListener(OnClickItemListener OnClickItemListener) {
        this.OnClickItemListener = OnClickItemListener;
    }

    public void setData(List<Student> mListStudent) {
        this.mListStudent = mListStudent;
        notifyItemRangeChanged(0, mListStudent.size());
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherFeeAdapter.StudentViewHolder(ItemTeacherFeeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = mListStudent.get(position);

        String name = student.getFirstName() + " " + student.getLastName();
        holder.mBinding.txtName.setText(name);
        holder.itemView.setOnClickListener(v -> OnClickItemListener.onClick(position));
    }

    @Override
    public int getItemCount() {
        if (mListStudent != null) {
            return mListStudent.size();
        }
        return 0;
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        ItemTeacherFeeBinding mBinding;

        public StudentViewHolder(@NonNull ItemTeacherFeeBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }

    public interface OnClickItemListener {
        void onClick(int position);
    }
}
