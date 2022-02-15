package com.example.datn_project.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {_Notification.class},
        version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "SchoolApp";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, context.getFilesDir() + "/" + AppDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return sInstance;
    }

    public abstract NotificationDao getNotification();

}
