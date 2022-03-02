package com.example.datn_project.network;

import com.example.datn_project.models.Activities;
import com.example.datn_project.models.Album;
import com.example.datn_project.models.Classes;
import com.example.datn_project.models.Fee;
import com.example.datn_project.models.FeeRequest;
import com.example.datn_project.models.Health;
import com.example.datn_project.models.LeaveDay;
import com.example.datn_project.models.LoginRequest;
import com.example.datn_project.models.Meeting;
import com.example.datn_project.models.News;
import com.example.datn_project.models.Participant;
import com.example.datn_project.models.Student;
import com.example.datn_project.models.User;
import com.example.datn_project.models.menu.Menu;
import com.example.datn_project.responses.LoginResponse;
import com.example.datn_project.responses.ParticipantsResponse;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("activities/{id}")
    Call<Activities> getActivities(@Path("id") int id);

    @GET("classes/{id}")
    Call<Classes> getClasses(@Path("id") int id);

    @POST("participants")
    Call<Participant> activitiesParticipants(@Body Participant participant);

    @GET("participants/{id}")
    Call<Participant> getParticipants(@Path("id") int id);

    @POST("leave-day")
    Call<LeaveDay> requestOff(@Body LeaveDay leaveDay);

    @GET("fees/student/{id}")
    Call<List<Fee>> getFees(@Path("id") int id);

    @GET("fees")
    Call<List<Fee>> getFees();

    @GET("meeting")
    Call<List<Meeting>> getMeeting();

    @GET("albums")
    Call<List<Album>> getAlbum();

    @GET("menu/class/{id}")
    Call<Menu> getMenu(@Path("id") int id);

    @GET("participants")
    Call<List<ParticipantsResponse>> getStudentParticipant();

    @GET("students")
    Call<List<Student>> getListStudents();

    @PUT("fees/{id}")
    Call<FeeRequest> applyFees(@Path("id") UUID id, @Body FeeRequest feeRequest);
}
