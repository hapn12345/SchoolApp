package com.example.datn_project.activities.fcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.example.datn_project.R;
import com.example.datn_project.activities.MainActivity;
import com.example.datn_project.activities.MyApplication;
import com.example.datn_project.database.AppDatabase;
import com.example.datn_project.database._Notification;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        if (notification == null) {
            return;
        }
        String title = notification.getTitle();
        String strMess = notification.getBody();

        sendNotification(title, strMess);
        _Notification notification1 = new _Notification();
        notification1.setTitle(title);
        notification1.setBody(strMess);
        new Thread(() -> {
            AppDatabase.getInstance(this).getNotification().insertNotification(notification1);
        }).start();
    }

    private void sendNotification(String title, String strMess) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_NAME)
                .setContentTitle(title)
                .setContentText(strMess)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent);
        Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(1, notification);
        }
    }
}
