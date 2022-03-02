package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_project.databinding.ItemTeacherHealthBinding;
import com.example.datn_project.models.Health;
import com.example.datn_project.models.Student;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherHealthAdapter extends RecyclerView.Adapter<TeacherHealthAdapter.TeacherHealthViewHolder> {
    private List<Health> mListHealth;
    private ApiService apiService;

    public TeacherHealthAdapter() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public void setData(List<Health> mListHealth) {
        this.mListHealth = mListHealth;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeacherHealthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherHealthAdapter.TeacherHealthViewHolder(
                ItemTeacherHealthBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherHealthViewHolder holder, int position) {
        Health health = mListHealth.get(position);

        String height = health.getHeight() + " Cm";
        holder.mBinding.txtHeight.setText(height);

        String[] time = health.getCheckedAt().split(" ");
        holder.mBinding.txtTime.setText(time[0]);

        holder.mBinding.txtDes.setText(health.getNote());

        String weight = health.getWeight() + " Kg";
        holder.mBinding.txtWeight.setText(weight);

        int height_bmi = Integer.parseInt(health.getHeight()) / 100;
        int bmi = Integer.parseInt(health.getWeight()) / (height_bmi * height_bmi);
        holder.mBinding.txtBmi.setText(bmi + " ");

        apiService.getListStudents().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(@NonNull Call<List<Student>> call, @NonNull Response<List<Student>> response) {
                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        if (response.body().get(i).getId() == Integer.parseInt(health.getStudentID())) {
                            String name = response.body().get(i).getFirstName() + " " + response.body().get(i).getLastName();
                            holder.mBinding.txtName.setText(name);
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Student>> call, @NonNull Throwable t) {
                Toast.makeText(holder.itemView.getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListHealth != null) {
            return mListHealth.size();
        }
        return 0;
    }

    public static class TeacherHealthViewHolder extends RecyclerView.ViewHolder {
        ItemTeacherHealthBinding mBinding;

        public TeacherHealthViewHolder(@NonNull ItemTeacherHealthBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }
}
