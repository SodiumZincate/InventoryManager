package com.example.inventorymanager.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogDao {

    @Insert
    void insert(LogEntity log);

    @Delete
    void delete(LogEntity log);

    @Query("SELECT * FROM logs ORDER BY id DESC")
    List<LogEntity> getAllLogs();
}