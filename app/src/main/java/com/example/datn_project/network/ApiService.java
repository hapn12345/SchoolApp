package com.example.datn_project.network;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.Activity;
import com.example.datn_project.models.Classes;
import com.example.datn_project.models.Health;
import com.example.datn_project.models.LeaveDay;
import com.example.datn_project.models.LoginRequest;
import com.example.datn_project.models.News;
import com.example.datn_project.models.Participant;
import com.example.datn_project.models.User;
import com.example.datn_project.responses.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("users/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @Headers({"Content-Type: application/json;charset=UTF-8"})
    @GET("users/me")
    Call<User> getInfoUser(@Header("Authorization") String auth);

    @GET("news")
    Call<List<News>> getListNews();

    @GET("health")
    Call<List<Health>> getListHealth();

    @GET("activities")
    Call<Activities> getListActivities();

    @GET("classes/{id}")
    Call<Classes> getClasses(@Path("id") int id);

    @POST("participants")
    Call<Participant> activitiesParticipants(@Body Participant participant);

    @GET("participants/{id}")
    Call<Participant> getParticipants(@Path("id") int id);

    @POST("leave-day")
    Call<LeaveDay> requestOff(@Body LeaveDay leaveDay);
}
