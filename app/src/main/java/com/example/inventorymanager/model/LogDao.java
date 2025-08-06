package com.example.inventorymanager.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Update;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogDao {

    @Insert
    void insert(LogEntity log);

    @Delete
    void delete(LogEntity log);

    @Update
    void update(LogEntity log);

    @Query("SELECT * FROM logs ORDER BY id DESC")
    LiveData<List<LogEntity>> getAllLogs();

    @Query("SELECT * FROM logs WHERE isFinished = 1 ORDER BY id DESC")
    LiveData<List<LogEntity>> getFinishedLogs();

    @Query("SELECT * FROM logs WHERE isFinished = 0 ORDER BY id DESC")
    LiveData<List<LogEntity>> getUnfinishedLogs();
}