package com.example.datn_project.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotificationDao {
    @Query("SELECT * FROM _Notification")
    List<_Notification> loadAllNotification();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNotification(_Notification notification);
}