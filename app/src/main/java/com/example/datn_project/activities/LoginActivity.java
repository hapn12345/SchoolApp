package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.datn_project.databinding.ActivityLoginBinding;
import com.example.datn_project.models.LoginRequest;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;
import com.example.datn_project.responses.LoginResponse;
import com.example.datn_project.utilities.SharedPreferenceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding mBinding;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(mBinding.getRoot());
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        apiService = ApiClient.getRetrofit().create(ApiService.class);
        mBinding.btnLogin.setOnClickListener(view -> {
            if (mBinding.editTextEmail.getText().toString().isEmpty() && mBinding.editTextPassword.getText().toString().isEmpty()) {
                mBinding.editTextEmail.setError("Please input UserName");
                mBinding.editTextPassword.setError("Please input password");
            } else {
                userLogin();
            }
        });
    }

    private void userLogin() {
        String userName = mBinding.editTextEmail.getText().toString();
        String password = mBinding.editTextPassword.getText().toString();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(userName);
        loginRequest.setPassword(password);
        apiService.userLogin(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call, @NonNull Response<LoginResponse> response) {
                if (response.body() != null) {
                    SharedPreferenceUtil.putKeyString(getApplicationContext(), "userId", response.body().getUserId());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, @NonNull Throwable t) {
                Log.e("onFailure: ", call.toString());
            }
        });
    }
}