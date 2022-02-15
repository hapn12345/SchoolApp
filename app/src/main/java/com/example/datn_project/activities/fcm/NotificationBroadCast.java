package com.example.datn_project.activities.fcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.datn_project.activities.MainActivity;

public class NotificationBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ((MainActivity) context).mBinding.viewpager2.setCurrentItem(2);
    }
}
