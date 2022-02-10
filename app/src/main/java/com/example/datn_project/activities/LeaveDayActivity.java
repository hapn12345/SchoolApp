package com.example.datn_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.example.datn_project.databinding.ActivityLeaveDayBinding;
import com.example.datn_project.models.LeaveDay;
import com.example.datn_project.network.ApiClient;
import com.example.datn_project.network.ApiService;
import com.example.datn_project.viewmodel.LeaveDayViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LeaveDayActivity extends AppCompatActivity {
    private ActivityLeaveDayBinding mBinding;
    private OnSelectDateListener listener;
    private LeaveDay leaveDay;
    private int studentId;
    private int classId;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityLeaveDayBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        apiService = ApiClient.getRetrofit().create(ApiService.class);
        listener = calendar -> {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            String firstDate = formatter.format(new Date(calendar.get(0).getTimeInMillis()));
            String lastDate = formatter.format(new Date(calendar.get(calendar.size() - 1).getTimeInMillis()));
            leaveDay.setFirstDay(firstDate);
            leaveDay.setLastDay(lastDate);
            leaveDay.setDaysOff(calendar.size());
            String text = firstDate + "  -  " + lastDate;
            mBinding.txtTime.setText(text);
            Toast.makeText(getApplicationContext(), "Số ngày nghỉ " + calendar.size(), Toast.LENGTH_SHORT).show();
        };
        getData();
        initToolbar();
        onListener();
    }

    private void getData() {
        leaveDay = new LeaveDay();
        studentId = getIntent().getIntExtra("key_student", -1);
        classId = getIntent().getIntExtra("key_class", -1);
        leaveDay.setStudentID(Integer.toString(studentId));
        leaveDay.setClassID(Integer.toString(classId));
    }

    private void onListener() {
        mBinding.datePicker.setOnClickListener(v -> {
            DatePickerBuilder builder = new DatePickerBuilder(this, listener)
                    .pickerType(CalendarView.RANGE_PICKER);
            DatePicker datePicker = builder.build();
            datePicker.show();

        });
        mBinding.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mBinding.edtContent.getText().toString().isEmpty() && leaveDay.getFirstDay() != null) {
                    leaveDay.setContent(mBinding.edtContent.getText().toString());
                    apiService.requestOff(leaveDay).enqueue(new Callback<LeaveDay>() {
                        @Override
                        public void onResponse(@NonNull Call<LeaveDay> call, @NonNull Response<LeaveDay> response) {
                            Toast.makeText(getApplicationContext(), "Xin nghi phep thanh cong", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(@NonNull Call<LeaveDay> call, @NonNull Throwable t) {
                            Toast.makeText(getApplicationContext(), "Fail", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    mBinding.edtContent.setError("Nhập lý do nghỉ");
                }
            }
        });

    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}