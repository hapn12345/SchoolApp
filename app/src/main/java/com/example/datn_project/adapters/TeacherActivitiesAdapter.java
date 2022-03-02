package com.example.datn_project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datn_project.databinding.ItemActivitiesBinding;
import com.example.datn_project.databinding.ItemParticipantsBinding;
import com.example.datn_project.models.Activities;
import com.example.datn_project.models.Classes;
import com.example.datn_project.models.Participant;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;
import com.example.datn_project.responses.ParticipantsResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherActivitiesAdapter extends RecyclerView.Adapter<TeacherActivitiesAdapter.ParticipantViewHolder> {
    private ApiService apiService;
    private List<ParticipantsResponse> mListParticipants;
    private int classID;
    public removeItem removeItem;

    public void setRemoveItem(TeacherActivitiesAdapter.removeItem removeItem) {
        this.removeItem = removeItem;
    }

    public void setClassID(int classID) {
        this.classID = classID;
        notifyDataSetChanged();
    }

    public TeacherActivitiesAdapter() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public void setData(List<ParticipantsResponse> mListParticipants) {
        this.mListParticipants = mListParticipants;
        notifyItemRangeChanged(0, mListParticipants.size());
    }

    @NonNull
    @Override
    public ParticipantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeacherActivitiesAdapter.ParticipantViewHolder(
                ItemParticipantsBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantViewHolder holder, int position) {
        ParticipantsResponse participant = mListParticipants.get(position);
        if (classID == Integer.parseInt(participant.getClassID())) {
            String name = participant.getStudent().getFirstName() + " " + participant.getStudent().getLastName();
            holder.mBinding.txtName.setText(name);
            apiService.getListActivities().enqueue(new Callback<Activities>() {
                @Override
                public void onResponse(@NonNull Call<Activities> call, @NonNull Response<Activities> response) {
                    if (response.body() != null) {
                        for (int i = 0; i < response.body().getActivities().size(); i++) {
                            if (response.body().getActivities().get(i).getId() == Integer.parseInt(participant.getActivityID()))
                                holder.mBinding.txtActivityName.setText(response.body().getActivities().get(i).getName());
                        }
                    }

                }

                @Override
                public void onFailure(@NonNull Call<Activities> call, @NonNull Throwable t) {
                    Toast.makeText(holder.itemView.getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            removeItem.onRemove(position);
        }
    }

    @Override
    public int getItemCount() {
        if (mListParticipants != null) {
            return mListParticipants.size();
        }
        return 0;
    }

    public static class ParticipantViewHolder extends RecyclerView.ViewHolder {
        ItemParticipantsBinding mBinding;

        public ParticipantViewHolder(ItemParticipantsBinding mBinding) {
            super(mBinding.getRoot());
            this.mBinding = mBinding;
        }
    }

    public interface removeItem {
        void onRemove(int position);
    }
}
