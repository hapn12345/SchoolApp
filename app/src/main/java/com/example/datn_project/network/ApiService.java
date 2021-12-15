package com.example.datn_project.network;

import com.example.datn_project.models.LoginRequest;
import com.example.datn_project.responses.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("users/login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
